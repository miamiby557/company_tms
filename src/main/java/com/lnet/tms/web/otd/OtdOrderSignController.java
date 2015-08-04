package com.lnet.tms.web.otd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.otd.OtdOrderSign;
import com.lnet.tms.model.otd.OtdOrderSignBean;
import com.lnet.tms.service.otd.OtdOrderSignService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.utility.StringUtils;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdOrderSign")
public class OtdOrderSignController extends CrudController<OtdOrderSign, UUID, OtdOrderSignService> {
    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(OtdOrderSignService service) {
        super.setService(service);
    }

    @RequestMapping(value="/dataSource",method=RequestMethod.POST)
    public @ResponseBody DataSourceResult getDataSource(@RequestBody DataSourceRequest request,@RequestParam("transportOrderId")UUID transportOrderId){
        List<DataSourceRequest.FilterDescriptor> list=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        list.add(new DataSourceRequest.FilterDescriptor("and", "transportOrderId", "eq", transportOrderId, true));
        return service.getDataSource(request);
    }
    @RequestMapping(value = "/batchSign",method = RequestMethod.POST)
    public @ResponseBody JsonResult batchSign(@RequestBody OtdOrderSignBean model ){
        try{
            service.batchSign(model);
            return JsonResult.success();
        }catch (Exception e){
            return  JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "/reset",method = RequestMethod.POST)
    public @ResponseBody JsonResult reset(@RequestBody OtdOrderSign model ){
        try{
            service.reset(model);
            return JsonResult.success();
        }catch (Exception e){
            return  JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="/batchReset",method = RequestMethod.POST)
    @RequiresPermissions("TRANSPORTORDER_SIGN")
    public @ResponseBody JsonResult batchReset(@RequestBody List<UUID> transportOrderIds)  {
        try{
            service.batchReset(transportOrderIds);
            return JsonResult.success();
        }catch (Exception e){
            return  JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="/batchSign/{transportOrderIds}",method = RequestMethod.GET)
    @RequiresPermissions("TRANSPORTORDER_SIGN")
    public String batchSign(@PathVariable("transportOrderIds")List<UUID> transportOrderIds,ModelMap model) throws JsonProcessingException {
        model.addAttribute("transportOrderIds",mapper.writeValueAsString(transportOrderIds));
        return "otd/sign/batchSign";
    }

}