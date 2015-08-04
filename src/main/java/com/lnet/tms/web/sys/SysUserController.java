package com.lnet.tms.web.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnet.tms.model.sys.SysRole;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.sys.SysRoleService;
import com.lnet.tms.service.sys.SysUserService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Controller
@RequestMapping("/sysUser")
public class SysUserController extends CrudController<SysUser, UUID, SysUserService> {

    @Autowired
    public void setBaseService(SysUserService userService) {
        super.setService(userService);
    }

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @Autowired
    private SysRoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresRoles("SYSADMIN")
    public String index() {
        return "sys/sysUser/index";
    }

    @RequestMapping("/create")
        public String create(ModelMap model) throws JsonProcessingException {
            model.addAttribute("allRoles", mapper.writeValueAsString(roleService.getAll()));
            return "sys/sysUser/create";
    }


    @RequestMapping("/modify/{userId}")
    public String modify(@PathVariable UUID userId, ModelMap model) throws JsonProcessingException {
        SysUser sysUser = service.getWith(userId, "sysRoles");
        String sysUserJson = mapper.writeValueAsString(sysUser);
        model.addAttribute(sysUser);
        model.addAttribute("allRoles", mapper.writeValueAsString(roleService.getAll()));
        model.addAttribute("sysUserJson", sysUserJson);
        return "sys/sysUser/modify";
    }


}
