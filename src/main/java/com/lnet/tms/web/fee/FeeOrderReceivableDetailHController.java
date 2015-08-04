package com.lnet.tms.web.fee;

import com.lnet.tms.model.fee.FeeOrderReceivableDetailH;
import com.lnet.tms.service.fee.FeeOrderReceivableDetailHService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/feeOrderReceivableDetailH")
public class FeeOrderReceivableDetailHController extends CrudController<FeeOrderReceivableDetailH, UUID, FeeOrderReceivableDetailHService> {
    @Autowired
    public void setBaseService(FeeOrderReceivableDetailHService service) {
        super.setService(service);
    }
}