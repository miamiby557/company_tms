package com.lnet.tms.web.otd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.model.otd.OtdOrderCirculationView;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.otd.OtdOrderCirculationViewService;
import com.lnet.tms.service.otd.OtdTransportOrderService;
import com.lnet.tms.web.BaseController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdOrderCirculationView")
public class OtdOrderCirculationViewController extends BaseController<OtdOrderCirculationView, UUID, OtdOrderCirculationViewService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(OtdOrderCirculationViewService service) {
        super.setService(service);
    }
    @Autowired
    private OtdTransportOrderService otdTransportOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "rpt/rptTransportOrderCirculation/index";
    }

    @RequestMapping(value="/detail/{id}")
    public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdTransportOrder otdTransportOrder=otdTransportOrderService.get(id);
        List<OtdOrderCirculationView> circulations=service.getAllByField("transportOrderId",id);
        model.addAttribute("otdTransportOrder",otdTransportOrder);
        model.addAttribute("otdTransportOrderJson",mapper.writeValueAsString(otdTransportOrder));
        model.addAttribute("OtdOrderCirculationsJson",mapper.writeValueAsString(circulations));
        return "rpt/rptTransportOrderCirculation/detail";
    }


}