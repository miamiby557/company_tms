package com.lnet.tms.web.otd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.otd.OtdPickupOrder;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.base.BaseRegionService;
import com.lnet.tms.service.otd.OtdPickupOrderService;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdPickupOrder")
public class OtdPickupOrderController extends CrudController<OtdPickupOrder, UUID, OtdPickupOrderService> {

    @Autowired
    public void setBaseService(OtdPickupOrderService service) {
        super.setService(service);
    }
    @Autowired
    private BaseRegionService baseRegionService;
    @Autowired
    private HibernateAwareObjectMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "otd/otdPickupOrder/index";
    }

    @RequestMapping("/create")
    @RequiresPermissions("PICKUPORDER_CREATE")
    public String create() {
     return "otd/otdPickupOrder/create";
    }

    @RequestMapping(value="/modify/{id}")
    public String modify(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdPickupOrder OtdPickupOrder=service.get(id);
        model.addAttribute(OtdPickupOrder);
        model.addAttribute("otdPickupOrderJson",mapper.writeValueAsString(OtdPickupOrder));
        return "otd/otdPickupOrder/modify";
    }

    @RequestMapping(value="/confirm/{id}")
    public String confirm(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdPickupOrder OtdPickupOrder=service.get(id);
        model.addAttribute(OtdPickupOrder);
        model.addAttribute("otdPickupOrderJson",mapper.writeValueAsString(OtdPickupOrder));
        return "otd/otdPickupOrder/confirm";
    }


    @RequestMapping(value="/details/{id}")
    public String details(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdPickupOrder OtdPickupOrder=service.get(id);
        model.addAttribute(OtdPickupOrder);
        model.addAttribute("otdPickupOrderJson",mapper.writeValueAsString(OtdPickupOrder));
        return "otd/otdPickupOrder/details";
    }

    @Override
    public JsonResult create(@RequestBody OtdPickupOrder model) {
        if(!StringUtils.isEmpty(model.getCityId())){
            model.setCity(baseRegionService.get(model.getCityId()).getName());
        }
        model.setProcessStatus(1);//   1 接单
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        return super.create(model);
    }

    @Override
    public JsonResult update(@RequestBody OtdPickupOrder model) {
        if(service.existsBy("pickupOrderNumber",model.getPickupOrderNumber(),model.getPickupOrderId())){
            return JsonResult.error("该提货单号已存在！");
        }
        if(!StringUtils.isEmpty(model.getCityId())){
            model.setCity(baseRegionService.get(model.getCityId()).getName());
        }
        model.setModifyDate(DateUtils.getTimestampNow());
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        return super.update(model);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @RequiresPermissions("PICKUPORDER_CONFIRM")
    public @ResponseBody JsonResult confirm(@RequestBody OtdPickupOrder model) {
        if(service.existsBy("pickupOrderNumber",model.getPickupOrderNumber(),model.getPickupOrderId())){
            return JsonResult.error("该提货单号已存在！");
        }
        if(!StringUtils.isEmpty(model.getCityId())){
            model.setCity(baseRegionService.get(model.getCityId()).getName());
        }
        model.setProcessStatus(2);
        model.setModifyDate(DateUtils.getTimestampNow());
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        return super.update(model);
    }

    @RequestMapping(value = "/ok", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult ok(@RequestBody UUID pickId) {
        try {
            OtdPickupOrder model= service.get(pickId);
            model.setProcessStatus(4);//完成
            service.update(model);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "/pick", method = RequestMethod.POST)
    @RequiresPermissions("PICKUPORDER")
    public
    @ResponseBody
    JsonResult pick(@RequestBody UUID pickId) {
        try {
            OtdPickupOrder model= service.get(pickId);
            model.setProcessStatus(3);//提货
            service.update(model);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult cancel(@RequestBody UUID pickId) {
        try {
            OtdPickupOrder model= service.get(pickId);
            model.setProcessStatus(-1);//取消
            service.update(model);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/getDataSourceByStatus", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getDataSourceByStatus(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        if(list!=null && list.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:list){
                if("clientId".equals(filter.getField())){
                    filter.setValue(UUID.fromString((String)filter.getValue()));
                }
            }
        }
        request.getFilter().setLogic("and");
        list.add(new DataSourceRequest.FilterDescriptor("processStatus","gt",1));
        return service.getDataSource(request);
    }


    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search() {
        return "otd/otdPickupOrder/search";
    }
}