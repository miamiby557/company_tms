package com.lnet.tms.web.dispatch;

import com.lnet.tms.model.dispatch.DispatchAssignDetail;
import com.lnet.tms.service.dispatch.DispatchAssignDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/dispatchAssignDetail")
public class DispatchAssignDetailController extends CrudController<DispatchAssignDetail, UUID, DispatchAssignDetailService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(DispatchAssignDetailService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "dispatch/dispatchAssignDetail/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "dispatch/dispatchAssignDetail/create";
    }

    @RequestMapping(value="/modify",method = RequestMethod.GET)
    public String modify(@RequestParam("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("UUIDJson",UUIDJson);
        return "dispatch/dispatchAssignDetail/modify";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
        public String detail(@RequestParam("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("UUIDJson",UUIDJson);
        return "dispatch/dispatchAssignDetail/modify";
    }
}