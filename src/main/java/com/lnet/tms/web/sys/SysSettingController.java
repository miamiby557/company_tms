package com.lnet.tms.web.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.sys.SysSetting;
import com.lnet.tms.service.sys.SysSettingService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/sysSetting")
public class SysSettingController extends CrudController<SysSetting, String, SysSettingService> {

    private static final Logger logger = LoggerFactory.getLogger(SysSettingController.class);

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @Autowired
    public void setBaseService(SysSettingService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresRoles("SYSADMIN")
    public String index() {
        return "sys/sysSetting/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "sys/sysSetting/create";
    }

    @Override
    public JsonResult create(@RequestBody SysSetting model) {
        try {
            boolean b = service.existsBy("name", model.getName());
            if (b) {
                return JsonResult.error("参数名称已存在");
            }
            service.create(model);
            return JsonResult.success(model);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/modify/{name}")
    public String modify(@PathVariable String name, ModelMap model) throws JsonProcessingException {
        SysSetting sysSetting = service.get(name);
        model.addAttribute("sysSettingJSON", mapper.writeValueAsString(sysSetting));
        return "sys/sysSetting/modify";
    }
}