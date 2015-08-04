package com.lnet.tms.web.crm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.crm.CrmClientProductType;
import com.lnet.tms.service.crm.CrmClientProductTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/crmClientProductType")
public class CrmClientProductTypeController extends CrudController<CrmClientProductType, UUID, CrmClientProductTypeService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(CrmClientProductTypeService service) {
        super.setService(service);
    }

    @RequestMapping(value = "/dataSource", method = RequestMethod.POST)
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

    @RequestMapping(value="/createProductType/{clientId}")
    public String createProductType(@PathVariable UUID clientId,ModelMap model){
        model.addAttribute("clientId",clientId);
        return "crm/productType/create";
    }

    @RequestMapping(value="/createProductType",method = RequestMethod.POST)
    public @ResponseBody JsonResult createProductType(@RequestBody CrmClientProductType CrmClientProductType){
        try {
            return service.CrmClientProductType(CrmClientProductType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/modifyProductType/{clientProductTypeId}")
    public String modifyProductType(@PathVariable UUID clientProductTypeId,ModelMap model) throws JsonProcessingException {
        CrmClientProductType crmClientProductType=service.get(clientProductTypeId);
        model.addAttribute("crmClientProductType",mapper.writeValueAsString(crmClientProductType));
        return "crm/productType/modify";
    }

    @RequestMapping(value="/updateProductType",method = RequestMethod.POST)
    public @ResponseBody JsonResult updateProductType(@RequestBody CrmClientProductType CrmClientProductType){
        try {
            return service.updateProductType(CrmClientProductType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}