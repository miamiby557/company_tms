package com.lnet.tms.web.crm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.crm.CrmClientSender;
import com.lnet.tms.service.crm.CrmClientSendeService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * Created by develop on 2015/8/5.
 */

@Controller
@RequestMapping("/crmClientSender")
public class CrmClientSendeController<T> extends CrudController<CrmClientSender,UUID,CrmClientSendeService>{

    @Autowired
    public void setBaseService(CrmClientSendeService service) {
        super.setService(service);
    }

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @RequestMapping(value = "/create/{clientId}")
    public String create(@PathVariable UUID clientId) {
        return "crm/crmClientSender/create";
    }

    @RequestMapping(value = "/createByAddressIds/{clientId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult createByAddressIds(@PathVariable("clientId") UUID clientId,@RequestBody List<UUID> addressIds) {
        try{
           return  service.createByAddressIds(addressIds,clientId);
        }catch (Exception e){
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/deletByClientIdAndAddressId", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deletByClientIdAndAddressId(@RequestBody UUID clientSenderId){
        service.deleteById(clientSenderId);
        return JsonResult.success();
    }

    @RequestMapping(value="/modify/{clientSenderId}")
    public String modify(@PathVariable UUID clientSenderId,Model model) throws JsonProcessingException {
        CrmClientSender crmClientSender=service.get(clientSenderId);
        model.addAttribute("crmClientSenderJson", mapper.writeValueAsString(crmClientSender));
        return "crm/crmClientSender/modify";
    }

    @RequestMapping(value="/myCreate",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult myCreate(@RequestBody CrmClientSender model){
        try {
            CrmClientSender sender=model;
            System.out.println(model.getClientSenderId());
            System.out.println(model.getClientId());
            System.out.println(model.getAddressId());
            service.update(model);
            return JsonResult.success();
        }catch(Exception e){
            e.printStackTrace();
            return JsonResult.error("失败");
        }
    }

}
