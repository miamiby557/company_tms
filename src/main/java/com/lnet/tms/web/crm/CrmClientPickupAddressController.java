package com.lnet.tms.web.crm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.crm.CrmClientPickupAddress;
import com.lnet.tms.service.crm.CrmClientPickupAddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/crmClientPickupAddress")
public class CrmClientPickupAddressController extends CrudController<CrmClientPickupAddress, UUID, CrmClientPickupAddressService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(CrmClientPickupAddressService service) {
        super.setService(service);
    }
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "crm/crmClientPickupAddress/index";
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
        return "crm/crmClientPickupAddress/create";
    }
    @RequestMapping(value = "/modify/{clientPickupAddressId}")
    public String modify(@PathVariable UUID clientPickupAddressId,Model model)throws JsonProcessingException{
        CrmClientPickupAddress crmClientPickupAddress=service.get(clientPickupAddressId);
        model.addAttribute("crmClientPickupAddress",mapper.writeValueAsString(crmClientPickupAddress));
        return  "crm/crmClientPickupAddress/modify";
    }
}