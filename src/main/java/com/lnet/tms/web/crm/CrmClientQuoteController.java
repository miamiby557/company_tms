package com.lnet.tms.web.crm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.ImportResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.crm.CrmClientLine;
import com.lnet.tms.model.crm.CrmClientQuote;
import com.lnet.tms.service.crm.CrmClientLineService;
import com.lnet.tms.service.crm.CrmClientQuoteService;
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
@RequestMapping("/crmClientQuote")
public class CrmClientQuoteController extends CrudController<CrmClientQuote, UUID, CrmClientQuoteService> {
    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(CrmClientQuoteService service) {
        super.setService(service);
    }
    @Autowired
    private CrmClientLineService crmClientLineService;

    private static List<ImportResult>results=new ArrayList<>();

    @RequestMapping("/quote/{clientId}")
    public String quote(@PathVariable UUID clientId, ModelMap model){
        model.addAttribute("clientId",clientId);
        return "crm/crmClientQuote/index";
    }

    @RequestMapping("/create/{clientId}")
    public String create(@PathVariable UUID clientId,ModelMap model) {
        model.addAttribute("clientId",clientId);
        return "crm/crmClientQuote/create";
    }

    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable UUID id, ModelMap model) throws Exception {
        CrmClientQuote clientQuote = service.get(id);
        CrmClientLine clientLine = crmClientLineService.get(clientQuote.getClientLineId());
        clientQuote.setCrmClientLine(clientLine);
        model.addAttribute(clientQuote);

        model.addAttribute("crmClientQuoteJson",mapper.writeValueAsString(clientQuote));
        return "crm/crmClientQuote/modify";
    }

    @RequestMapping("/copy/{id}")
    public String copy(@PathVariable UUID id, ModelMap model) throws Exception {
        CrmClientQuote clientQuote = service.get(id);
        CrmClientLine clientLine = crmClientLineService.get(clientQuote.getClientLineId());
        clientQuote.setCrmClientLine(clientLine);
        model.addAttribute(clientQuote);
        clientQuote.setModifyUserId(null);
        clientQuote.setModifyDate(null);
        clientQuote.setCreateDate(null);
        clientQuote.setCreateUserId(null);
        clientQuote.setClientQuoteId(null);
        model.addAttribute("crmClientQuoteJson",mapper.writeValueAsString(clientQuote));
        return "crm/crmClientQuote/modify";
    }

    @RequestMapping(value = "/createCrmClientQuote",method = RequestMethod.POST)
    public @ResponseBody JsonResult createCrmClientQuote(@RequestBody CrmClientQuote model) {
        try {
            JsonResult jsonResult=service.createCrmClientQuote(model);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/updateCrmClientQuote",method = RequestMethod.POST)
    public @ResponseBody JsonResult updateCrmClientQuote(@RequestBody CrmClientQuote model) {
        try {
            JsonResult jsonResult=service.updateCrmClientQuote(model);
            return jsonResult;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping("/importQuote")
    @RequiresPermissions("CRMORDER_IMPORTQUOTE")
    public String importQuote(){
        return "crm/crmClientQuote/uploadClientQuote";
    }


    @RequestMapping("/importData")
    public String importData(@RequestParam MultipartFile file,ModelMap model) throws IOException {
        results=service.readExcel(file);
        return "redirect:/#crmClientQuote/importResult";
    }

    @RequestMapping("/importResult")
    public String importResult(){
        return "crm/crmClientQuote/importResult";
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
