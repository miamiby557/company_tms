package com.lnet.tms.web.fee;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.fee.FeeOrderPayableLog;
import com.lnet.tms.service.fee.FeeOrderPayableLogService;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/feeOrderPayableLog")
public class FeeOrderPayableLogController extends CrudController<FeeOrderPayableLog,UUID,FeeOrderPayableLogService>{

    @Autowired
    public void setBaseService(FeeOrderPayableLogService service){
        super.setService(service);
    }

    @RequestMapping("/projectCreate/{orderPayableId}")
    public String projectCreate(@PathVariable UUID orderPayableId){
        return "fee/FeeOrderPayable/back";
    }

    @RequestMapping("/financingCreate/{orderPayableId}")
    public String financingCreate(@PathVariable UUID orderPayableId){
        return "fee/FeeOrderPayable/back";
    }

    @RequestMapping("/cancelFinish/{orderPayableId}")
    public String cancelFinish(@PathVariable UUID orderPayableId){
        return "fee/FeeOrderPayable/back";
    }

    @Override
    public JsonResult create(@RequestBody FeeOrderPayableLog model) {
        service.back(model);
        return JsonResult.success();
    }
}
