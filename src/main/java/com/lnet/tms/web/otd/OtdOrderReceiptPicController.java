package com.lnet.tms.web.otd;

import com.lnet.tms.model.otd.OtdOrderReceiptPic;
import com.lnet.tms.service.otd.OtdOrderReceiptPicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/otdOrderReceiptPic")
public class OtdOrderReceiptPicController extends CrudController<OtdOrderReceiptPic, UUID, OtdOrderReceiptPicService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(OtdOrderReceiptPicService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "otd/otdOrderReceiptPic/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "otd/otdOrderReceiptPic/create";
    }

    @RequestMapping(value="/modify/{id}")
    public String modify(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String OtdOrderReceiptPicJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("OtdOrderReceiptPicJson",OtdOrderReceiptPicJson);
        return "otd/otdOrderReceiptPic/modify";
    }

    @RequestMapping(value="/detail/{id}")
        public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String OtdOrderReceiptPicJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("OtdOrderReceiptPicJson",OtdOrderReceiptPicJson);
        return "otd/otdOrderReceiptPic/modify";
    }
}