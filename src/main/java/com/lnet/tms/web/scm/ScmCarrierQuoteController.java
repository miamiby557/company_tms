package com.lnet.tms.web.scm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.ImportResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.scm.ScmCarrierLine;
import com.lnet.tms.model.scm.ScmCarrierQuote;
import com.lnet.tms.service.scm.ScmCarrierLineService;
import com.lnet.tms.service.scm.ScmCarrierQuoteService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/scmCarrierQuote")
public class ScmCarrierQuoteController extends CrudController<ScmCarrierQuote,UUID,ScmCarrierQuoteService>{

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(ScmCarrierQuoteService service) {
        super.setService(service);
    }
    @Autowired
    private ScmCarrierLineService scmCarrierLineService;

    private static List<ImportResult>results=new ArrayList<>();

    @RequestMapping("/create/{carrierId}")
    public String create(@PathVariable UUID carrierId){
        return "scm/carrierQuote/create";
    }

    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable UUID id, ModelMap model) throws Exception {
        ScmCarrierQuote scmCarrierQuote = service.get(id);
        ScmCarrierLine carrierLine = scmCarrierLineService.get(scmCarrierQuote.getCarrierLineId());
        scmCarrierQuote.setScmCarrierLine(carrierLine);
        model.addAttribute(scmCarrierQuote);
        model.addAttribute("scmCarrierQuoteJson",mapper.writeValueAsString(scmCarrierQuote));
        return "scm/carrierQuote/modify";
    }
    @RequestMapping("/copy/{id}")
    public String copy(@PathVariable UUID id, ModelMap model) throws Exception {
        ScmCarrierQuote carrierQuote = service.get(id);
        ScmCarrierLine carrierLine = scmCarrierLineService.get(carrierQuote.getCarrierLineId());
        carrierQuote.setScmCarrierLine(carrierLine);
        model.addAttribute(carrierQuote);
        carrierQuote.setModifyUserId(null);
        carrierQuote.setModifyDate(null);
        carrierQuote.setCreateDate(null);
        carrierQuote.setCreateUserId(null);
        carrierQuote.setCarrierQuoteId(null);
        model.addAttribute("scmCarrierQuoteJson",mapper.writeValueAsString(carrierQuote));
        return "scm/carrierQuote/modify";
    }

    @RequestMapping(value = "/createScmCarrierQuote",method = RequestMethod.POST)
    public @ResponseBody JsonResult createScmCarrierQuote(@RequestBody ScmCarrierQuote model) {
        try {
            JsonResult jsonResult=service.createScmCarrier(model);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/updateScmCarrierQuote",method = RequestMethod.POST)
    public @ResponseBody JsonResult updateScmCarrierQuote(@RequestBody ScmCarrierQuote model) {
        try {
            JsonResult jsonResult=service.updateScmCarrierQuote(model);
            return jsonResult;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping("/importQuote")
    @RequiresPermissions("SCMORDER_IMPORTQUOTE")
    public String importQuote(){
        return "scm/carrier/uploadQuote";
    }


    @RequestMapping("/importData")
    public String importData(@RequestParam MultipartFile file,ModelMap model) throws IOException {
        results=service.readExcel(file);
        return "redirect:/#scmCarrierQuote/importResult";
    }

    @RequestMapping("/importResult")
    public String importResult(){
        return "scm/carrier/importResult";
    }

    @RequestMapping(value = "/getImportResult", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getResult(@RequestBody DataSourceRequest request){
        DataSourceResult dataSourceResult=new DataSourceResult();
        dataSourceResult.setData(results);
        results=new ArrayList<>();
        return dataSourceResult;
    }

}
