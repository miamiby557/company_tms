package com.lnet.tms.web.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.sys.SysFunction;
import com.lnet.tms.model.sys.SysRole;
import com.lnet.tms.service.sys.SysFunctionService;
import com.lnet.tms.service.sys.SysRoleService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends CrudController<SysRole, UUID, SysRoleService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(SysRoleService service) {
        super.setService(service);
    }

    @Autowired
    private SysFunctionService functionService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresRoles("SYSADMIN")
    public String index() {
      return "sys/sysRole/index";
    }

    @RequestMapping("/create")
    public String create(ModelMap model) throws JsonProcessingException {
     model.addAttribute("allFunctions",mapper.writeValueAsString(functionService.getAll()));
     return "sys/sysRole/create";
    }

    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        SysRole sysRole = service.getWith(id, "sysFunctions");
        String sysRoleJson = mapper.writeValueAsString(sysRole);
        model.addAttribute(sysRole);
        model.addAttribute("sysRoleJson", sysRoleJson);
        model.addAttribute("allFunctions",mapper.writeValueAsString(functionService.getAll()));
        return "sys/sysRole/modify";
    }

    @RequestMapping("/details/{id}")
    public String details(@PathVariable UUID id, ModelMap model) {
        model.addAttribute(service.get(id));
        return "sys/sysRole/details";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult create(@RequestBody SysRole model) {
        try {
            service.create(model);
            return JsonResult.success(model);
        } catch (Exception e) {
            return JsonResult.error(e.getMessage());
        }
    }
}