package com.lnet.tms.web.otd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.otd.*;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.otd.*;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdTransportOrderLog")
public class OtdTransportOrderLogController extends CrudController<OtdTransportOrderLog, UUID, OtdTransportOrderLogService> {

    @Autowired
    public void setBaseService(OtdTransportOrderLogService service) {
        super.setService(service);
    }

    @Autowired
    private OtdTransportOrderService otdTransportOrderService;

    @Autowired
    private OtdOrderSignService otdOrderSignService;
    @Autowired
    private OtdTransportOrderLogViewService otdTransportOrderLogViewService;

    @Autowired
    private OtdTransportOrderReceiptService otdTransportOrderReceiptService;

    @Autowired
    private OtdOrderLogListViewService otdOrderLogListViewService;

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @RequestMapping("/index/{transportOrderId}")
    public String index(@PathVariable UUID transportOrderId, ModelMap model)throws JsonProcessingException {
        model.addAttribute("transportOrderId",transportOrderId);
        return "otd/otdTransportOrderLog/index";
    }

    @RequestMapping("/create/{transportOrderId}")
    @RequiresPermissions("ORDERLOG_MESSAGEINPUT")
    public String create(@PathVariable("transportOrderId") UUID transportOrderId, ModelMap model) throws JsonProcessingException {
        model.addAttribute("transportOrderId",transportOrderId);
        OtdOrderLogListView otdOrderLogListView=otdOrderLogListViewService.get(transportOrderId);
        model.addAttribute("otdOrderLogListView",mapper.writeValueAsString(otdOrderLogListView));
        return "otd/otdTransportOrderLog/create";
    }
    @RequestMapping("/createBatch/{transportOrderIds}")
    @RequiresPermissions("ORDERLOG_MESSAGEINPUT")
    public String createBatch(@PathVariable("transportOrderIds") List<UUID> transportOrderIds, ModelMap model) throws JsonProcessingException {
        model.addAttribute("transportOrderIds",mapper.writeValueAsString(transportOrderIds));
        return "otd/otdTransportOrderLog/createBatch";
    }
    @RequestMapping(value = "/createBatch", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult createBatch(@RequestBody OtdTransportOrderLogBean model) {
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setIsSystem(true);
        model.setIsPublic(true);
        try {
            service.createBatch(model);
            return JsonResult.success();
        }catch (Exception e){
            return JsonResult.error(e.getMessage());
        }
    }
    @Override
    public JsonResult create(@RequestBody OtdTransportOrderLog model){
        model.setCurrentStatus(otdTransportOrderService.get(model.getTransportOrderId()).getStatus());
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setIsSystem(true);
        model.setIsPublic(true);
        return super.create(model);
    }

    @RequestMapping("/modify/{transportOrderLogId}")
//    @RequiresPermissions("ORDERLOG_MESSAGEINPUT")
    public String modify(@PathVariable("transportOrderLogId") UUID transportOrderLogId, ModelMap model) throws JsonProcessingException {
        model.addAttribute("transportOrderLogId",transportOrderLogId);
        OtdTransportOrderLog transportOrderLog =  service.get(transportOrderLogId);
        model.addAttribute(transportOrderLog);
        model.addAttribute("transportOrderLogJson",mapper.writeValueAsString(transportOrderLog));
        return "otd/otdTransportOrderLog/modify";
    }
    @Override
    public JsonResult update(@RequestBody OtdTransportOrderLog model) {
        return super.update(model);
    }

    @RequestMapping(value="/detail/{id}")
    public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdTransportOrder otdTransportOrder=otdTransportOrderService.getWith(id,"details");
        List<OtdTransportOrderLogView> logs=otdTransportOrderLogViewService.getAllByField("transportOrderId", id);
        OtdTransportOrderReceipt otdTransportOrderReceipt  = otdTransportOrderReceiptService.getByField("transportOrderId",id);
        OtdOrderSign sign =  otdOrderSignService.get(id);
        model.addAttribute("otdTransportOrder",otdTransportOrder);
        model.addAttribute("otdOrderSignJson",mapper.writeValueAsString(sign));
        model.addAttribute("otdTransportOrderReceipt",otdTransportOrderReceipt);
        model.addAttribute("otdTransportOrderJson",mapper.writeValueAsString(otdTransportOrder));
        model.addAttribute("otdTransportOrderDetailsJson",mapper.writeValueAsString(otdTransportOrder.getDetails()));
        model.addAttribute("otdTransportOrderLogsJson",mapper.writeValueAsString(logs));
        return "otd/otdTransportOrderLog/transportDetail";
    }
    @RequestMapping(value = "/dataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult dataSource(@RequestParam("transportOrderId")  UUID transportOrderId,@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(list.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:list){
                if("operationDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse((String)filter.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else if("currentStatus".equals(filter.getField())){
                    filter.setValue(Integer.parseInt((String) filter.getValue()));
                }
            }
        }
        list.add(new DataSourceRequest.FilterDescriptor("and", "transportOrderId", "eq", transportOrderId, true));

        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("operationDate", "desc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            request.setSort(sorts);
        }
        return otdTransportOrderLogViewService.getDataSource(request);
    }
}