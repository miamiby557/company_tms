package com.lnet.tms.web.fee;

import com.lnet.tms.model.fee.FeeOrderPayableDetailH;
import com.lnet.tms.service.fee.FeeOrderPayableDetailHService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/feeOrderPayableDetailH")
public class FeeOrderPayableDetailHController extends CrudController<FeeOrderPayableDetailH, UUID, FeeOrderPayableDetailHService> {

    @Autowired
    public void setBaseService(FeeOrderPayableDetailHService service) {
        super.setService(service);
    }

}