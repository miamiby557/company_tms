package com.lnet.tms.web.rpt;

import com.lnet.tms.model.rpt.RptTest;
import com.lnet.tms.service.rpt.RptTestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/rptTest")
public class RptTestController extends CrudController<RptTest, UUID, RptTestService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(RptTestService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "rpt/rptTest/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "rpt/rptTest/create";
    }

    @RequestMapping(value="/modify/{id}")
    public String modify(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String RptTestJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("RptTestJson",RptTestJson);
        return "rpt/rptTest/modify";
    }

    @RequestMapping(value="/detail/{id}")
        public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String RptTestJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("RptTestJson",RptTestJson);
        return "rpt/rptTest/modify";
    }
}