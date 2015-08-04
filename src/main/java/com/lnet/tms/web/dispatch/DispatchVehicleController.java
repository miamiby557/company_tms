package com.lnet.tms.web.dispatch;

import com.lnet.tms.model.dispatch.DispatchVehicle;
import com.lnet.tms.service.dispatch.DispatchVehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/dispatchVehicle")
public class DispatchVehicleController extends CrudController<DispatchVehicle, UUID, DispatchVehicleService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(DispatchVehicleService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "dispatch/dispatchVehicle/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "dispatch/dispatchVehicle/create";
    }

    @RequestMapping(value="/modify/{id}")
    public String modify(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String DispatchVehicleJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("dispatchVehicleJson",DispatchVehicleJson);
        return "dispatch/dispatchVehicle/modify";
    }

    @RequestMapping(value="/detail/{id}")
        public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String DispatchVehicleJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("dispatchVehicleJson",DispatchVehicleJson);
        return "dispatch/dispatchVehicle/detail";
    }
}