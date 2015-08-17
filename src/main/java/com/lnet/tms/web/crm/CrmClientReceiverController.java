package com.lnet.tms.web.crm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.crm.CrmClientReceiver;
import com.lnet.tms.service.crm.CrmClientReceiverService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/8/5.
 */
@Controller
@RequestMapping("/crmClientReceiver")
public class CrmClientReceiverController extends CrudController<CrmClientReceiver,UUID,CrmClientReceiverService> {
    @Autowired
    private HibernateAwareObjectMapper mapper;

    @Autowired
    public void setBaseService(CrmClientReceiverService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "crm/crmClientReceiver/index";
    }
    @RequestMapping(value = "/getDateSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult dataSource(@RequestParam("clientId")  UUID clientId,@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list=request.getFilter().getFilters();
        DataSourceRequest.FilterDescriptor filter =new DataSourceRequest.FilterDescriptor("and", "clientId", "eq", clientId, true);
        request.getFilter().setLogic("and");
        list.add(filter);
        return service.getDataSource(request);
    }

    @RequestMapping(value = "/create/{clientId}", method = RequestMethod.GET)
    public String create(@PathVariable UUID clientId, Model model) {
        model.addAttribute("clientId", clientId);
        return "crm/crmClientReceiver/create";
    }

    @RequestMapping(value = "/createByAddressIds/{clientId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult createByAddressIds(@PathVariable("clientId") UUID clientId, @RequestBody List<UUID> addressIds) {
        try {
            return service.createByAddressIds(addressIds, clientId);
        } catch (Exception e) {
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "/deleteByAddressId",method = RequestMethod.POST)
    public @ResponseBody  JsonResult deleteByAddressId(@RequestBody UUID clientReceiverId){
        service.deleteById(clientReceiverId);
        return  JsonResult.success();
    }
   /* @RequestMapping(value = "/deleteByAddressId",method = RequestMethod.POST)
    public JsonResult deleteByAddressId(@RequestBody List<UUID> clientReceiverId){
        service.deleteById(clientReceiverId);
        return  JsonResult.success();
    }*/
}

