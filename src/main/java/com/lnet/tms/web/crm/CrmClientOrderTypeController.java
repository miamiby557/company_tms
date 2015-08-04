package com.lnet.tms.web.crm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.crm.CrmClientOrderType;
import com.lnet.tms.service.crm.CrmClientOrderTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/crmClientOrderType")
public class CrmClientOrderTypeController extends CrudController<CrmClientOrderType, UUID, CrmClientOrderTypeService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @Autowired
    public void setBaseService(CrmClientOrderTypeService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "crm/crmClientOrderType/index";
    }
    @RequestMapping(value = "/getDateSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult dataSource(@RequestParam("clientId")  UUID clientId,@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list=request.getFilter().getFilters();
        DataSourceRequest.FilterDescriptor filter =new DataSourceRequest.FilterDescriptor("and", "clientId", "eq", clientId, true);
        request.getFilter().setLogic("and");
        list.add(filter);

        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null|| sorts.size()==0){
            sorts=new ArrayList<>();
        }
        sorts.add(new DataSourceRequest.SortDescriptor("value","asc"));
        request.setSort(sorts);
        return service.getDataSource(request);
    }

    @RequestMapping(value = "/create/{clientId}", method = RequestMethod.GET)
    public String create(@PathVariable UUID clientId, Model model) {
        model.addAttribute("clientId", clientId);
        return "crm/crmClientOrderType/create";
    }
    @RequestMapping(value="create",method =RequestMethod.POST)
    public @ResponseBody
    JsonResult create(@RequestBody CrmClientOrderType crmClientOrderType){
        try {
            return  service.CrmClientOrderType(crmClientOrderType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "/modify/{clientOrderTypeId}")
    public String modify(@PathVariable UUID clientOrderTypeId,Model model)throws JsonProcessingException{
        CrmClientOrderType crmClientOrderType=service.get(clientOrderTypeId);
        model.addAttribute("crmClientOrderType",mapper.writeValueAsString(crmClientOrderType));
        return  "crm/crmClientOrderType/modify";
    }
    @RequestMapping(value = "updateOrderType",method = RequestMethod.POST)
    public @ResponseBody JsonResult updateOrderType(@RequestBody CrmClientOrderType crmClientOrderType){
        try {
            return service.updateOrderType(crmClientOrderType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}