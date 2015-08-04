package com.lnet.tms.web.dispatch;

import com.lnet.tms.model.dispatch.DispatchAssignStatusRecord;
import com.lnet.tms.service.dispatch.DispatchAssignStatusRecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/dispatchAssignStatusRecord")
public class DispatchAssignStatusRecordController extends CrudController<DispatchAssignStatusRecord, UUID, DispatchAssignStatusRecordService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(DispatchAssignStatusRecordService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "dispatch/dispatchAssignStatusRecord/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "dispatch/dispatchAssignStatusRecord/create";
    }

    @RequestMapping(value="/modify/{id}")
    public String modify(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String DispatchAssignStatusRecordJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("DispatchAssignStatusRecordJson",DispatchAssignStatusRecordJson);
        return "dispatch/dispatchAssignStatusRecord/modify";
    }

    @RequestMapping(value="/detail/{id}")
        public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String DispatchAssignStatusRecordJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("DispatchAssignStatusRecordJson",DispatchAssignStatusRecordJson);
        return "dispatch/dispatchAssignStatusRecord/modify";
    }
}