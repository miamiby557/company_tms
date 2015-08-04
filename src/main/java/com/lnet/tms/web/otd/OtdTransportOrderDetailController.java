package com.lnet.tms.web.otd;

import com.lnet.tms.model.otd.OtdTransportOrderDetail;
import com.lnet.tms.service.otd.OtdTransportOrderDetailService;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/otdTransportOrderDetail")
public class OtdTransportOrderDetailController extends CrudController<OtdTransportOrderDetail, UUID, OtdTransportOrderDetailService> {

    @Autowired
    public void setBaseService(OtdTransportOrderDetailService service) {
        super.setService(service);
    }

}