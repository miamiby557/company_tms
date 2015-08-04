package com.lnet.tms.web.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.sys.SysOrganization;
import com.lnet.tms.service.sys.SysOrganizationService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.utility.UUIDConverter;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/sysOrganization")
public class SysOrganizationController extends CrudController<SysOrganization, UUID, SysOrganizationService> {
    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(SysOrganizationService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresRoles("SYSADMIN")
    public String index() {
        return "sys/sysOrganization/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "sys/sysOrganization/create";
    }


    @RequestMapping("/modify/{id}")
        public String modify(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        SysOrganization sysOrganization=service.get(id);
        model.addAttribute("sysOrganization",sysOrganization);
        model.addAttribute("sysOrganizationJson",mapper.writeValueAsString(sysOrganization));
        return "sys/sysOrganization/modify";
    }

    @RequestMapping("/details/{id}")
    public String details(@PathVariable UUID id, ModelMap model) {
        model.addAttribute(service.get(id));
        return "sys/sysOrganization/details";
    }

    @RequestMapping(value = "/getChildrenData", method = {RequestMethod.GET, RequestMethod.POST})
    public  @ResponseBody  List<SysOrganization> getChildrenData(@RequestBody DataSourceRequest request) {
        UUID organizationId = null;
        if (request.getData().get("organizationId") != null) {
            organizationId = UUID.fromString(request.getData().get("organizationId").toString());
        }
        return service.getAllByField("superiorOrganizationId", organizationId);
    }

    @RequestMapping(value = "/getHierarchicalData", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody List<SysOrganization> getHierarchicalData() {
        return service.getHierarchicalData("superiorOrganizationId", "subOrganizations", null);
    }



    @RequestMapping(value = "/getOrganizationList", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getOrganizationList(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        if (list != null && list.size() > 0) {
            request.getFilter().getFilters().get(0).setField("name");
        }
        return service.getDataSource(request);
    }
}