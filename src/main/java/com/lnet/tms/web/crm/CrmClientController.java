package com.lnet.tms.web.crm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.crm.CrmClient;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.base.BaseRegionViewService;
import com.lnet.tms.service.crm.CrmClientService;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.GetPinyinUtil;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.web.FileManager;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Controller
@RequestMapping("/crmClient")
public class CrmClientController extends CrudController<CrmClient, UUID, CrmClientService> {
    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(CrmClientService service) {
        super.setService(service);
    }

    @Autowired
    private FileManager fileManager;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("CRMORDER_SELECT")
    public String index() {
        return "crm/crmClient/index";
    }

    @RequestMapping("/create")
    @RequiresRoles("SYSADMIN")
    @RequiresPermissions("CRMORDER_CREATE")
    public String create() {
        return "crm/crmClient/create";
    }

    @RequestMapping("/modify/{id}")
    @RequiresRoles("SYSADMIN")
    @RequiresPermissions("CRMORDER_MODIFY")
    public String modify(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        CrmClient client = service.get(id);
        model.addAttribute(client);
        model.addAttribute("clientId",id);
        model.addAttribute("crmClientJson",mapper.writeValueAsString(client));
        return "crm/crmClient/modify";
    }
    @RequestMapping("/details/{id}")
    public String details(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        CrmClient client = service.get(id);
        model.addAttribute(client);
        model.addAttribute("clientId",id);
        model.addAttribute("crmClientJson",mapper.writeValueAsString(client));
        return "crm/crmClient/details";
    }

    @Override
    public JsonResult create(@RequestBody CrmClient model) {
        try {
            UUID clientId = service.create(model);
            return JsonResult.success(clientId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="/existCode",method = RequestMethod.GET)
    public @ResponseBody
    boolean existCode(@RequestParam("code")String code,@RequestParam("clientId") UUID clientId){
        boolean result=service.existsBy("code", code);
        if(clientId!=null){
            result = service.existsBy("code", code,clientId);
        }
        return !result;
    }

    @RequestMapping("/createFile/{id}")
    public String createFile(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        CrmClient client = service.get(id);
        model.addAttribute(client);
        model.addAttribute("clientId",id);
        return "crm/crmClient/clientFile";
    }
    @RequestMapping(value = "/upload/{clientId}", method = RequestMethod.POST)
    public @ResponseBody JsonResult upload(@RequestParam MultipartFile file,@PathVariable UUID clientId, Model model,HttpServletResponse response) {
        try {
            UUID fileId = service.upload(Arrays.asList(file),clientId,model);
            return JsonResult.success(fileId);
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @Override
    @RequiresPermissions("CRMORDER_REMOVE")
    public JsonResult deleteById(@RequestBody UUID uuid) {
        return super.deleteById(uuid);
    }
}
