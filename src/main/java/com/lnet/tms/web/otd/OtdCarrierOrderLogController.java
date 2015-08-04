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
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/otdCarrierOrderLog")
public class OtdCarrierOrderLogController extends CrudController<OtdCarrierOrderLog, UUID, OtdCarrierOrderLogService> {

    @Autowired
    public void setBaseService(OtdCarrierOrderLogService service) {
        super.setService(service);
    }

    @Autowired
    private OtdCarrierOrderService otdCarrierOrderService;
    @Autowired
    private OtdCarrierOrderDetailViewService otdCarrierOrderDetailViewService;
    @Autowired
    private HibernateAwareObjectMapper mapper;

    @RequestMapping("/index")
    public String index(){
        return "otd/otdCarrierOrderLog/index";
    }

    @RequestMapping("/create/{carrierOrderId}")
    @RequiresPermissions("ORDERLOG_CARRIERINPUT")
    public String create(@PathVariable("carrierOrderId") UUID carrierOrderId, ModelMap model) throws JsonProcessingException{
        model.addAttribute("carrierOrderId",carrierOrderId);
        OtdCarrierOrderLog otdCarrierOrderLog=service.findLastLog(carrierOrderId);
        model.addAttribute("otdCarrierOrderLog",mapper.writeValueAsString(otdCarrierOrderLog));
        return "otd/otdCarrierOrderLog/create";
    }
    @RequestMapping("/createBatch/{carrierOrderIds}")
    @RequiresPermissions("ORDERLOG_CARRIERINPUT")
    public String createBatch(@PathVariable("carrierOrderIds") List<UUID> carrierOrderIds, ModelMap model) throws JsonProcessingException {
        model.addAttribute("carrierOrderIds",mapper.writeValueAsString(carrierOrderIds));
        return "otd/otdCarrierOrderLog/createBatch";
    }
    @RequestMapping(value = "/createBatch", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult createBatch(@RequestBody OtdCarrierOrderLogBean model) {
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
    public JsonResult create(@RequestBody OtdCarrierOrderLog model) {
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setCreateDate(DateUtils.getTimestampNow());
        OtdCarrierOrder carrierOrder = otdCarrierOrderService.get(model.getCarrierOrderId());
        model.setCurrentStatus(carrierOrder.getStatus());
        model.setIsSystem(true);
        model.setIsPublic(true);
        return super.create(model);
    }

    @RequestMapping("/modify/{carrierOrderLogId}")
//    @RequiresPermissions("ORDERLOG_MESSAGEINPUT")
    public String modify(@PathVariable("carrierOrderLogId") UUID carrierOrderLogId, ModelMap model) throws JsonProcessingException {
        model.addAttribute("carrierOrderLogId",carrierOrderLogId);
        OtdCarrierOrderLog orderLog = service.get(carrierOrderLogId);
        model.addAttribute(orderLog);
        model.addAttribute("carrierOrderLogJson",mapper.writeValueAsString(orderLog));
        return "otd/otdCarrierOrderLog/modify";
    }
    @Override
    public JsonResult update(@RequestBody OtdCarrierOrderLog model) {
        return super.update(model);
    }


    @RequestMapping(value = "/dataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult dataSource(@RequestParam("carrierOrderId")  UUID carrierOrderId,@RequestBody DataSourceRequest request) {
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
        list.add(new DataSourceRequest.FilterDescriptor("and", "carrierOrderId", "eq", carrierOrderId, true));
        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("operationDate", "desc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            request.setSort(sorts);
        }
        return super.getDataSource(request);
    }

    @RequestMapping(value="/detail/{id}")
    public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdCarrierOrder otdCarrierOrder=otdCarrierOrderService.get(id);
        List<OtdCarrierOrderDetailView> carrierOrderDetailList= otdCarrierOrderDetailViewService.getAllByField("carrierOrderId", otdCarrierOrder.getCarrierOrderId());
        model.addAttribute("otdCarrierOrder",otdCarrierOrder);
        model.addAttribute("otdCarrierOrderJson",mapper.writeValueAsString(otdCarrierOrder));
        model.addAttribute("transportOrderListJson",mapper.writeValueAsString(carrierOrderDetailList));
        /*List<OtdCarrierOrderLogView>logs=otdCarrierOrderLogViewService.getAllByField("carrierOrderId",id);
        model.addAttribute("otdCarrierOrderLogsJson",mapper.writeValueAsString(logs));*/
        return "otd/otdCarrierOrderLog/carrierDetail";
    }
}