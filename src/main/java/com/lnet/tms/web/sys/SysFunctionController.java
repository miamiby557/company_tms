package com.lnet.tms.web.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.model.sys.SysFunction;
import com.lnet.tms.model.sys.SysRole;
import com.lnet.tms.service.sys.SysFunctionService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/sysFunction")
public class SysFunctionController extends CrudController<SysFunction, UUID, SysFunctionService> {


    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(SysFunctionService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresRoles("SYSADMIN")
    public String index() {
      return "sys/sysFunction/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "sys/sysFunction/create";
    }

    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        SysFunction sysFunction = service.get(id);
        String sysFunctionJson = mapper.writeValueAsString(sysFunction);
        model.addAttribute(sysFunctionJson);
        model.addAttribute("sysFunctionJson", sysFunctionJson);
        return "sys/sysFunction/modify";
    }

}