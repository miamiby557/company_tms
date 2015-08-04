package com.lnet.tms.web.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.sys.SysList;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.service.sys.SysListService;
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
@RequestMapping("/sysList")
public class SysListController extends CrudController<SysList, UUID, SysListService> {

    private static final Logger logger = LoggerFactory.getLogger(SysListController.class);

    @Autowired
    public void setBaseService(SysListService service) {
        super.setService(service);
    }

    @Autowired
    private SysListItemService sysListItemService;

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresRoles("SYSADMIN")
    public String index() {
        return "sys/sysList/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "sys/sysList/create";
    }

    @RequestMapping("/details/{id}")
    public String details(@PathVariable UUID id, ModelMap model) {
        model.addAttribute(service.get(id));
        return "sys/sysList/details";
    }

    @Override
    public JsonResult create(@RequestBody SysList model) {
        try {
            if (service.existsBy("code", model.getCode())) {
                return JsonResult.error("编码已存在!");
            }
            service.create(model);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping("/modify/{listId}")
    public String modify(@PathVariable UUID listId, ModelMap model) throws JsonProcessingException {
        SysList sysList = service.getWith(listId, "items");
        String sysListJson = mapper.writeValueAsString(sysList);
        String listItemsJson = mapper.writeValueAsString(sysList.getItems());

        model.addAttribute(sysList);
        model.addAttribute("sysListJson", sysListJson);
        model.addAttribute("listItemsJson", listItemsJson);

        return "sys/sysList/modify";
    }

    @Override
    public JsonResult update(@RequestBody SysList model) {
        try {
            if (service.existsBy("code", model.getCode(), model.getListId())) {
                return JsonResult.error("编码已存在!");
            }
            service.update(model);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}