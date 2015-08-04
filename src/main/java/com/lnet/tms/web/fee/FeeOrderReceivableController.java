package com.lnet.tms.web.fee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.fee.FeeOrderReceivableLogView;
import com.lnet.tms.model.fee.FeeOrderReceivableView;
import com.lnet.tms.model.fee.FeeOrderReceivable;
import com.lnet.tms.model.fee.FeeReceivableDetailView;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.fee.FeeOrderReceivableLogViewService;
import com.lnet.tms.service.fee.FeeOrderReceivableViewService;
import com.lnet.tms.service.fee.FeeOrderReceivableService;
import com.lnet.tms.service.fee.FeeReceivableDetailViewService;
import com.lnet.tms.service.otd.OtdTransportOrderService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/feeOrderReceivable")
public class FeeOrderReceivableController extends CrudController<FeeOrderReceivable, UUID,FeeOrderReceivableService> {
    @Autowired
    public void setBaseService(FeeOrderReceivableService service) {
        super.setService(service);
    }

    @Autowired
    private FeeOrderReceivableViewService feeOrderReceivableViewService;

    @Autowired
    private FeeReceivableDetailViewService feeReceivableDetailViewService;

    @Autowired
    private FeeOrderReceivableLogViewService feeOrderReceivableLogViewService;

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @Autowired
    private OtdTransportOrderService otdTransportOrderService;

    @RequestMapping(value="/modify/{orderReceivableId}")
    public String modify(@PathVariable UUID orderReceivableId,ModelMap model) throws JsonProcessingException {
        FeeOrderReceivableView fee= feeOrderReceivableViewService.get(orderReceivableId);
        List<FeeReceivableDetailView> details=feeReceivableDetailViewService.getAllByField("orderReceivableId",orderReceivableId);
        List<FeeOrderReceivableLogView>logs=feeOrderReceivableLogViewService.getAllByField("orderReceivableId",orderReceivableId);
        OtdTransportOrder otdTransportOrder=otdTransportOrderService.get(fee.getSourceOrderId());
        model.addAttribute("FeeOrderReceivableLogsJson",mapper.writeValueAsString(logs));
        model.addAttribute("orderReceivable",fee);
        model.addAttribute("orderReceivableJson",mapper.writeValueAsString(fee));
        model.addAttribute("FeeReceivableDetailsJson",mapper.writeValueAsString(details));
        model.addAttribute("otdTransportOrder",mapper.writeValueAsString(otdTransportOrder));
        return "fee/receivable/modify";
    }

    @RequestMapping(value="/confirm",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLE_MODIFY")
    public @ResponseBody JsonResult confirm(@RequestBody FeeOrderReceivable model) {
        try {
            service.confirm(model);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/confirmBatch",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLE_MODIFY")
    public @ResponseBody JsonResult confirmBatch(@RequestBody List<UUID> feeOrderReceivableIds) {
        try {
            for(UUID feeOrderReceivableId:feeOrderReceivableIds){
                FeeOrderReceivable model=service.getWith(feeOrderReceivableId,"details");
                service.confirm(model);
            }
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/updateReceivable",method = RequestMethod.POST)
    public @ResponseBody JsonResult updateReceivable(@RequestBody FeeOrderReceivable model) {
        try {
            service.updateReceivable(model);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/againCalculator",method = RequestMethod.POST)
    public @ResponseBody JsonResult againCalculator(@RequestBody OtdTransportOrder model) {
        try {
            FeeOrderReceivable feeOrderReceivable=service.againCalculator(model);
            List<FeeReceivableDetailView> details=feeReceivableDetailViewService.getAllByField("orderReceivableId",feeOrderReceivable.getOrderReceivableId());
            return JsonResult.success(details);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/projectAudit/{orderReceivableId}")
    public String projectAudit(@PathVariable("orderReceivableId") UUID orderReceivableId,ModelMap model) throws JsonProcessingException {
        FeeOrderReceivableView fee= feeOrderReceivableViewService.get(orderReceivableId);
        List<FeeReceivableDetailView> details=feeReceivableDetailViewService.getAllByField("orderReceivableId",orderReceivableId);
        List<FeeOrderReceivableLogView>logs=feeOrderReceivableLogViewService.getAllByField("orderReceivableId",orderReceivableId);
        OtdTransportOrder otdTransportOrder=otdTransportOrderService.get(fee.getSourceOrderId());
        model.addAttribute("FeeOrderReceivableLogsJson",mapper.writeValueAsString(logs));
        model.addAttribute("orderReceivable",fee);
        model.addAttribute("orderReceivableJson",mapper.writeValueAsString(fee));
        model.addAttribute("FeeReceivableDetailsJson",mapper.writeValueAsString(details));
        model.addAttribute("otdTransportOrder",mapper.writeValueAsString(otdTransportOrder));
        return "fee/receivable/projectAudit";
    }

    @RequestMapping(value="/financingAudit/{orderReceivableId}")
    public String financingAudit(@PathVariable("orderReceivableId") UUID orderReceivableId,ModelMap model) throws JsonProcessingException {
        FeeOrderReceivableView fee= feeOrderReceivableViewService.get(orderReceivableId);
        List<FeeReceivableDetailView> details=feeReceivableDetailViewService.getAllByField("orderReceivableId",orderReceivableId);
        List<FeeOrderReceivableLogView>logs=feeOrderReceivableLogViewService.getAllByField("orderReceivableId",orderReceivableId);
        OtdTransportOrder otdTransportOrder=otdTransportOrderService.get(fee.getSourceOrderId());
        model.addAttribute("FeeOrderReceivableLogsJson",mapper.writeValueAsString(logs));
        model.addAttribute("orderReceivable",fee);
        model.addAttribute("orderReceivableJson",mapper.writeValueAsString(fee));
        model.addAttribute("FeeReceivableDetailsJson",mapper.writeValueAsString(details));
        model.addAttribute("otdTransportOrder",mapper.writeValueAsString(otdTransportOrder));
        return "fee/receivable/financingAudit";
    }

    @RequestMapping(value="/projectPass",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERRECEIVABLEPROJECTAUDIT_PASS")
    public @ResponseBody JsonResult projectPass(@RequestBody List<UUID> orderReceivableIds){
        try {
            service.pass(orderReceivableIds);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }


    @RequestMapping(value="/financingPass",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERRECEIVABLEFINANCINGAUDIT_PASS")
    public @ResponseBody JsonResult financingPass(@RequestBody List<UUID> orderReceivableIds){
        try {
            service.pass(orderReceivableIds);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }


    @RequestMapping(value="/financingFinish",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERRECEIVABLEFINANCINGAUDIT_FINISH")
    public @ResponseBody JsonResult financingFinish(@RequestBody List<UUID> orderReceivableIds){
        try {
            service.pass(orderReceivableIds);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/back",method = RequestMethod.POST)
    public @ResponseBody JsonResult back(@RequestBody List<UUID> orderReceivableIds){
        try {
            service.back(orderReceivableIds);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}
