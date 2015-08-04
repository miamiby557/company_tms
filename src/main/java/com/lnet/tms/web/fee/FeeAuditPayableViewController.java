package com.lnet.tms.web.fee;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.fee.FeeOrderReceivableView;
import com.lnet.tms.service.fee.FeeOrderReceivableViewService;
import com.lnet.tms.web.BaseController;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Controller
@RequestMapping("/feeAuditPayableView")
public class FeeAuditPayableViewController extends BaseController<FeeOrderReceivableView,UUID,FeeOrderReceivableViewService> {

    @Autowired
    public void setBaseService(FeeOrderReceivableViewService service){
       super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
       return "fee/feeAuditPayableView/index";
    }

}
