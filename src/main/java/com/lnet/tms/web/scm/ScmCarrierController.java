package com.lnet.tms.web.scm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.base.BaseRegionService;
import com.lnet.tms.service.scm.ScmCarrierService;
import com.lnet.tms.service.sys.SysFileService;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.GetPinyinUtil;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.web.FileManager;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/scmCarrier")
public class ScmCarrierController extends CrudController<ScmCarrier,UUID,ScmCarrierService>{

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(ScmCarrierService service) {
        super.setService(service);
    }
    @Autowired
    private BaseRegionService baseRegionService;

    @Autowired
    private SysFileService sysFileService;
    @Autowired
    private FileManager fileManager;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SCMORDER_SELECT")
    public String index() {
        return "scm/carrier/index";
    }

    @RequestMapping("/create")
    @RequiresPermissions("SCMORDER_CREATE")
    public String create(){
        return "scm/carrier/create";
    }

    @Override
    public JsonResult create(@RequestBody ScmCarrier model) {
        try {
            UUID carrierId = service.create(model);
            return JsonResult.success(carrierId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/modify/{id}")
    @RequiresPermissions("SCMORDER_MODIFY")
    public String modify(@PathVariable UUID id,ModelMap model)throws JsonProcessingException{
        ScmCarrier scmCarrier=service.get(id);
        model.addAttribute("carrierId",id);
        model.addAttribute("scmCarrier",scmCarrier);
        model.addAttribute("scmCarrierJson", mapper.writeValueAsString(scmCarrier));
        return "scm/carrier/modify";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable UUID id,ModelMap model)throws JsonProcessingException{
        ScmCarrier scmCarrier=service.get(id);
        model.addAttribute("carrierId",id);
        model.addAttribute("scmCarrier",scmCarrier);
        model.addAttribute("scmCarrierJson", mapper.writeValueAsString(scmCarrier));
        return "scm/carrier/detail";
    }
    @RequestMapping(value="/existCode",method = RequestMethod.GET)
    public @ResponseBody
    boolean existCode(@RequestParam("code")String code,@RequestParam("carrierId") UUID carrierId){
        boolean result=service.existsBy("code", code);
        if(carrierId!=null){
            result = service.existsBy("code", code,carrierId);
        }
        return !result;
    }
    @RequestMapping("/createFile/{id}")
    public String createFile(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        ScmCarrier carrier = service.get(id);
        model.addAttribute(carrier);
        model.addAttribute("carrierId",id);
        return "scm/carrier/createFile";
    }
    @RequestMapping(value = "/upload/{carrierId}", method = RequestMethod.POST)
    public @ResponseBody JsonResult upload(@RequestParam List<MultipartFile> files,@PathVariable UUID carrierId, Model model) {
      try{
          List<UUID> fileIds = service.upload(files, carrierId);
          return JsonResult.success(fileIds);
      }catch (Exception e){
          return JsonResult.error(e.getMessage());
      }
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult deleteFile(@RequestParam("carrierId") UUID carrierId,@RequestParam("fileId") UUID fileId) {
        try {
            service.deleteFile(carrierId,fileId);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @Override
    @RequiresPermissions("SCMORDER_REMOVE")
    public JsonResult deleteById(@RequestBody UUID uuid) {
        return super.deleteById(uuid);
    }
}
