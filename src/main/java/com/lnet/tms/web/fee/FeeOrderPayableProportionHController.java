package com.lnet.tms.web.fee;

import com.lnet.tms.model.fee.FeeOrderPayableProportionH;
import com.lnet.tms.service.fee.FeeOrderPayableProportionHService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/feeOrderPayableProportionH")
public class FeeOrderPayableProportionHController extends CrudController<FeeOrderPayableProportionH, UUID, FeeOrderPayableProportionHService> {

    @Autowired
    public void setBaseService(FeeOrderPayableProportionHService service) {
        super.setService(service);
    }

}