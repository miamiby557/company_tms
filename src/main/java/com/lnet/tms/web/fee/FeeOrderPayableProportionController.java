package com.lnet.tms.web.fee;

import com.lnet.tms.model.fee.FeeOrderPayableProportion;
import com.lnet.tms.service.fee.FeeOrderPayableProportionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/feeOrderPayableProportion")
public class FeeOrderPayableProportionController extends CrudController<FeeOrderPayableProportion, UUID, FeeOrderPayableProportionService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(FeeOrderPayableProportionService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "fee/feeOrderPayableProportion/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "fee/feeOrderPayableProportion/create";
    }

    @RequestMapping(value="/modify",method = RequestMethod.GET)
    public String modify(@RequestParam("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        return "fee/feeOrderPayableProportion/modify";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
        public String detail(@RequestParam("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        return "fee/feeOrderPayableProportion/modify";
    }
}