package com.lnet.tms.web.fee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.fee.FeeOrderPayable;
import com.lnet.tms.model.fee.FeeOrderPayableLogView;
import com.lnet.tms.model.fee.FeeOrderPayableView;
import com.lnet.tms.service.fee.*;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Controller
@RequestMapping("/feeOrderPayable")
public class FeeOrderPayableController extends CrudController<FeeOrderPayable,UUID,FeeOrderPayableService>{

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(FeeOrderPayableService service){
        super.setService(service);
    }

    @Autowired
    private FeeOrderPayableDetailViewService feeOrderPayableDetailViewService;
    @Autowired
    private FeeOrderPayableViewService feeOrderPayableViewService;
    @Autowired
    private FeePayableProportionViewService feePayableProportionViewService;
    @Autowired
    private FeeOrderPayableLogViewService feeOrderPayableLogViewService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("FEEORDERPAYABLE_SELECT")
    public String index() {
        return "fee/feeOrderPayable/index";
    }

    @RequestMapping(value = "/modify/{id}")
    public String modify(@PathVariable UUID id,ModelMap model)throws JsonProcessingException {
        FeeOrderPayableView payable=feeOrderPayableViewService.get(id);
        model.addAttribute(payable);
        String feeOrderPayableDetailsJson =mapper.writeValueAsString(feeOrderPayableDetailViewService.getAllByField("orderPayableId", payable.getOrderPayableId()));
        String feePayableProportionDetailsJson =mapper.writeValueAsString(feePayableProportionViewService.getAllByField("orderPayableId", payable.getOrderPayableId()));
        List<FeeOrderPayableLogView>logs=feeOrderPayableLogViewService.getAllByField("orderPayableId", id);
        model.addAttribute("FeeOrderPayableLogsJson",mapper.writeValueAsString(logs));
        model.addAttribute("feeOrderPayableDetailsJson",feeOrderPayableDetailsJson);
        model.addAttribute("feePayableProportionDetailsJson",feePayableProportionDetailsJson);
        model.addAttribute("feeOrderPayableJson", mapper.writeValueAsString(payable));
        return "fee/feeOrderPayable/modify";
    }

    @RequestMapping(value="/confirm",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLE_MODIFY")
    public @ResponseBody
    JsonResult confirm(@RequestBody FeeOrderPayable order){
        try {
            service.confirm(order);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }


    @RequestMapping(value="/confirmBatch",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLE_MODIFY")
    public @ResponseBody
    JsonResult confirmBatch(@RequestBody List<UUID> feeOrderPayableIds){
        try {
            for(UUID feeOrderPayableId:feeOrderPayableIds){
                FeeOrderPayable order=service.getWith(feeOrderPayableId, "details","proportionDetails");
                service.confirm(order);
            }
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/updatePayable",method = RequestMethod.POST)
    public @ResponseBody
    JsonResult updatePayable(@RequestBody FeeOrderPayable order){
        try {
            service.updatePayable(order);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/projectAudit/{orderPayableId}")
    public String projectAudit(@PathVariable("orderPayableId") UUID orderPayableId,ModelMap model) throws JsonProcessingException {
        FeeOrderPayableView payable=feeOrderPayableViewService.get(orderPayableId);
        model.addAttribute(payable);
        String feeOrderPayableDetailsJson =mapper.writeValueAsString(feeOrderPayableDetailViewService.getAllByField("orderPayableId", payable.getOrderPayableId()));
        String feePayableProportionDetailsJson =mapper.writeValueAsString(feePayableProportionViewService.getAllByField("orderPayableId", payable.getOrderPayableId()));
        List<FeeOrderPayableLogView>logs=feeOrderPayableLogViewService.getAllByField("orderPayableId", orderPayableId);
        model.addAttribute("FeeOrderPayableLogsJson",mapper.writeValueAsString(logs));
        model.addAttribute("feeOrderPayableDetailsJson",feeOrderPayableDetailsJson);
        model.addAttribute("feePayableProportionDetailsJson",feePayableProportionDetailsJson);
        model.addAttribute("feeOrderPayableJson", mapper.writeValueAsString(payable));
        return "fee/FeeOrderPayable/projectAudit";
    }

    @RequestMapping(value="/financingAudit/{orderPayableId}")
    public String financingAudit(@PathVariable("orderPayableId") UUID orderPayableId,ModelMap model) throws JsonProcessingException {
        FeeOrderPayableView payable=feeOrderPayableViewService.get(orderPayableId);
        model.addAttribute(payable);
        String feeOrderPayableDetailsJson =mapper.writeValueAsString(feeOrderPayableDetailViewService.getAllByField("orderPayableId", payable.getOrderPayableId()));
        String feePayableProportionDetailsJson =mapper.writeValueAsString(feePayableProportionViewService.getAllByField("orderPayableId", payable.getOrderPayableId()));
        List<FeeOrderPayableLogView>logs=feeOrderPayableLogViewService.getAllByField("orderPayableId", orderPayableId);
        model.addAttribute("FeeOrderPayableLogsJson",mapper.writeValueAsString(logs));
        model.addAttribute("feeOrderPayableDetailsJson",feeOrderPayableDetailsJson);
        model.addAttribute("feePayableProportionDetailsJson",feePayableProportionDetailsJson);
        model.addAttribute("feeOrderPayableJson", mapper.writeValueAsString(payable));
        return "fee/FeeOrderPayable/financingAudit";
    }

    @RequestMapping(value="/projectPass",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLEPROJECTAUDIT_PASS")
    public @ResponseBody
    JsonResult projectPass(@RequestBody UUID orderPayableId){
        try {
            service.pass(orderPayableId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/financingPass",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLEFINANCINGAUDIT_PASS")
    public @ResponseBody
    JsonResult financingPass(@RequestBody UUID orderPayableId){
        try {
            service.pass(orderPayableId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/financingFinish",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLEFINANCINGAUDIT_FINISH")
    public @ResponseBody
    JsonResult financingFinish(@RequestBody UUID orderPayableId){
        try {
            service.pass(orderPayableId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/projectPassBatch",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLEPROJECTAUDIT_PASS")
    public @ResponseBody
    JsonResult projectPassBatch(@RequestBody List<UUID> ids){
        try {
            service.pass(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/financingPassBatch",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLEFINANCINGAUDIT_PASS")
    public @ResponseBody
    JsonResult financingPassBatch(@RequestBody List<UUID> ids){
        try {
            service.pass(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/financingFinishBatch",method = RequestMethod.POST)
    @RequiresPermissions("FEEORDERPAYABLEFINANCINGAUDIT_FINISH")
    public @ResponseBody
    JsonResult financingFinishBatch(@RequestBody List<UUID> ids){
        try {
            service.pass(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }


    @RequestMapping(value="/back",method = RequestMethod.POST)
    public @ResponseBody JsonResult back(@RequestBody UUID orderPayableId){
        try {
            service.back(orderPayableId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="/backBatch",method = RequestMethod.POST)
    public @ResponseBody JsonResult backBatch(@RequestBody List<UUID> ids){
        try {
            service.back(ids);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}
