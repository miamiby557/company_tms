package com.lnet.tms.web.fee;

import com.lnet.tms.model.fee.FeeOrderReceivableDetail;
import com.lnet.tms.service.fee.FeeOrderReceivableDetailService;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/feeOrderReceivableDetail")
public class FeeOrderReceivableDetailController extends CrudController<FeeOrderReceivableDetail, UUID,FeeOrderReceivableDetailService> {
    @Autowired
    public void setBaseService(FeeOrderReceivableDetailService service) {
        super.setService(service);
    }
}
