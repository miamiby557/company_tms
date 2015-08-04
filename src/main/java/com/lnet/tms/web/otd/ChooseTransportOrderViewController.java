package com.lnet.tms.web.otd;

import com.lnet.tms.model.otd.ChooseTransportOrderView;
import com.lnet.tms.service.otd.ChooseTransportOrderViewService;
import com.lnet.tms.web.BaseController;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/chooseTransportOrderView")
public class ChooseTransportOrderViewController extends BaseController<ChooseTransportOrderView, UUID, ChooseTransportOrderViewService> {
    @Autowired
    public void setBaseService(ChooseTransportOrderViewService service) {
        super.setService(service);
    }

}