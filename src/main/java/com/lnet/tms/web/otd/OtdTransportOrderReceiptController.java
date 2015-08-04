package com.lnet.tms.web.otd;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.otd.OtdTransportOrderReceipt;
import com.lnet.tms.model.otd.OtdTransportOrderReceiptBean;
import com.lnet.tms.service.otd.OtdTransportOrderReceiptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdTransportOrderReceipt")
public class OtdTransportOrderReceiptController extends CrudController<OtdTransportOrderReceipt, UUID, OtdTransportOrderReceiptService> {

    @Autowired
    public void setBaseService(OtdTransportOrderReceiptService service) {
        super.setService(service);
    }

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @RequestMapping(value="/receipt/{transportOrderId}",method = RequestMethod.GET)
    @RequiresPermissions("ORDERBACK_RECEIPT")
    public String receipt(@PathVariable("transportOrderId")UUID transportOrderId,ModelMap model) throws JsonProcessingException {
        OtdTransportOrderReceipt receipt=service.getByField("transportOrderId",transportOrderId);
        if(receipt.getStatus()!=null&&receipt.getStatus()==2){//已回待补
            receipt.setIsRepair(1);//已补签
        }
        receipt.setStatus(3);
        model.addAttribute(receipt);
        model.addAttribute("receiptJson",mapper.writeValueAsString(receipt));
        return "receipt/modify";
    }

    @RequestMapping(value="/receiptBatch/{transportOrderId}",method = RequestMethod.GET)
    public String receiptBatch(@PathVariable("transportOrderId")List<UUID> transportOrderIds,ModelMap model) throws JsonProcessingException {
        model.addAttribute("transportOrderIds",mapper.writeValueAsString(transportOrderIds));
        return "receipt/modifyBatch";
    }

    @RequestMapping(value="/repair/{transportOrderId}",method = RequestMethod.GET)
    @RequiresPermissions("ORDERBACK_REPAIR")
    public String repair(@PathVariable("transportOrderId")UUID transportOrderId,ModelMap model) throws JsonProcessingException {
        OtdTransportOrderReceipt receipt=service.getByField("transportOrderId",transportOrderId);
        receipt.setStatus(2);
        model.addAttribute(receipt);
        model.addAttribute("receiptJson",mapper.writeValueAsString(receipt));
        return "receipt/modify";
    }
    @RequestMapping(value = "/updateBatch", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult updateBatch(@RequestBody OtdTransportOrderReceiptBean model) {
        try {
            service.updateBatch(model);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}