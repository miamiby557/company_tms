package com.lnet.tms.web.fee;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.fee.FeeOrderReceivableLog;
import com.lnet.tms.service.fee.FeeOrderReceivableLogService;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/feeOrderReceivableLog")
public class FeeOrderReceivableLogController extends CrudController<FeeOrderReceivableLog,UUID,FeeOrderReceivableLogService>{

    @Autowired
    public void setBaseService(FeeOrderReceivableLogService service){
       super.setService(service);
    }

    @RequestMapping("/projectCreate/{orderReceivableId}")
    public String projectCreate(@PathVariable UUID orderReceivableId){
        return "fee/receivable/back";
    }

    @RequestMapping("/financingCreate/{orderReceivableId}")
    public String financingCreate(@PathVariable UUID orderReceivableId){
        return "fee/receivable/back";
    }

    @RequestMapping("/cancelFinish/{orderReceivableId}")
    public String cancelFinish(@PathVariable UUID orderReceivableId){
        return "fee/receivable/back";
    }

    @Override
    public JsonResult create(@RequestBody FeeOrderReceivableLog model) {
        service.back(model);
        return JsonResult.success();
    }
}
