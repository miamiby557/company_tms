package com.lnet.tms.web.otd;

import com.lnet.tms.model.otd.OtdCarrierOrderDetailView;
import com.lnet.tms.service.otd.OtdCarrierOrderDetailViewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.BaseController;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/otdCarrierOrderDetailView")
public class OtdCarrierOrderDetailViewController extends BaseController<OtdCarrierOrderDetailView, UUID, OtdCarrierOrderDetailViewService> {

    @Autowired
    public void setBaseService(OtdCarrierOrderDetailViewService service) {
        super.setService(service);
    }
}