package com.lnet.tms.web.fee;

import com.lnet.tms.model.fee.FeeOrderPayableDetail;
import com.lnet.tms.service.fee.FeeOrderPayableDetailService;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Controller
@RequestMapping("/feeOrderPayableDetail")
public class FeeOrderPayableDetailController extends CrudController<FeeOrderPayableDetail,UUID,FeeOrderPayableDetailService>{

    @Autowired
    public void setBaseService(FeeOrderPayableDetailService service){
        super.setService(service);
    }
}
