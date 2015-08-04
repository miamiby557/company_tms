package com.lnet.tms.web.otd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.otd.*;
import com.lnet.tms.service.otd.*;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.utility.StringUtils;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/otdCarrierOrder")
public class OtdCarrierOrderController extends CrudController<OtdCarrierOrder, UUID, OtdCarrierOrderService> {

    @Autowired
    public void setBaseService(OtdCarrierOrderService service) {
        super.setService(service);
    }
    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    private OtdCarrierOrderDetailViewService otdCarrierOrderDetailViewService;
    @Autowired
    private OtdCarrierOrderLogViewService otdCarrierOrderLogViewService;

    @Autowired
    private OtdTransportOrderService otdTransportOrderService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("CARRIERORDER_SELECT")
    public String index() {
      return "otd/otdCarrierOrder/index";
    }

    @RequestMapping("/create")
    @RequiresPermissions("CARRIERORDER_CREATE")
    public String create(){
         return "otd/otdCarrierOrder/create";
    }

    @RequestMapping(value="/modify/{id}")
    @RequiresPermissions("CARRIERORDER_MODIFY")
    public String modify(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdCarrierOrder otdCarrierOrder=service.get(id);
        List<OtdCarrierOrderDetailView> carrierOrderDetailList= otdCarrierOrderDetailViewService.getAllByField("carrierOrderId", otdCarrierOrder.getCarrierOrderId());
        model.addAttribute("otdCarrierOrder",otdCarrierOrder);
        model.addAttribute("carrierOrderDetailList",mapper.writeValueAsString(carrierOrderDetailList));
        model.addAttribute("otdCarrierOrderJson",mapper.writeValueAsString(otdCarrierOrder));
        return "otd/otdCarrierOrder/modify";
    }

    @RequestMapping(value="/detail/{id}")
    public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdCarrierOrder otdCarrierOrder=service.get(id);
        List<OtdCarrierOrderDetailView> carrierOrderDetailList= otdCarrierOrderDetailViewService.getAllByField("carrierOrderId", otdCarrierOrder.getCarrierOrderId());
        model.addAttribute("carrierOrderDetailList",mapper.writeValueAsString(carrierOrderDetailList));
        model.addAttribute("otdCarrierOrder",otdCarrierOrder);
        model.addAttribute("otdCarrierOrderJson",mapper.writeValueAsString(otdCarrierOrder));
        List<OtdCarrierOrderLogView>logs=otdCarrierOrderLogViewService.getAllByField("carrierOrderId",id);
        model.addAttribute("otdCarrierOrderLogsJson",mapper.writeValueAsString(logs));
        return "otd/otdCarrierOrder/detail";
    }

    @RequestMapping(value = "/createCarrierOrder", method = RequestMethod.POST)
    public    @ResponseBody JsonResult createCarrierOrder(@RequestBody OtdCarrierOrderBean model) {
        return service.createCarrierOrder(model);
    }

    /**
     * 修改托运单
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateCarrierOrder", method = RequestMethod.POST)
    public    @ResponseBody JsonResult updateCarrierOrder(@RequestBody OtdCarrierOrderBean model) {
        return service.updateCarrierOrder(model);
    }

    @RequestMapping(value="/send/{id}")
    @RequiresPermissions("CARRIERORDER_SEND")
    public String send(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdCarrierOrder otdCarrierOrder=service.get(id);
        List<OtdCarrierOrderDetailView> carrierOrderDetailList= otdCarrierOrderDetailViewService.getAllByField("carrierOrderId", otdCarrierOrder.getCarrierOrderId());
        model.addAttribute("otdCarrierOrder",otdCarrierOrder);
        model.addAttribute("carrierOrderDetailList",mapper.writeValueAsString(carrierOrderDetailList));
        model.addAttribute("otdCarrierOrderJson",mapper.writeValueAsString(otdCarrierOrder));

        //默认发送时间
        if(carrierOrderDetailList!=null && carrierOrderDetailList.size()>0){
            Date defaultDate=otdTransportOrderService.get(carrierOrderDetailList.get(0).getTransportOrderId()).getOrderDate();
            for(int i=1;i<carrierOrderDetailList.size();i++){
                Date date=otdTransportOrderService.get(carrierOrderDetailList.get(i).getTransportOrderId()).getOrderDate();
                if(date.compareTo(defaultDate)>0){
                    defaultDate=date;
                }
            }
            Calendar c = Calendar.getInstance();
            c.setTime(defaultDate);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            model.addAttribute("defaultDate",mapper.writeValueAsString(c.getTime()));
        }
        return "otd/otdCarrierOrder/send";
    }
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public    @ResponseBody JsonResult send(@RequestBody OtdCarrierOrderBean model) {
        OtdCarrierOrder otdCarrierOrder = new OtdCarrierOrder();
        BeanUtils.copyProperties(model,otdCarrierOrder);
        boolean flag=false;
        if(StringUtils.isEmpty(otdCarrierOrder.getCarrierOrderId())){
            flag=service.carrierOrderNumberIsExist(otdCarrierOrder.getCarrierId(),otdCarrierOrder.getCarrierOrderNumber());
        }else{
            flag=service.carrierOrderNumberIsExist(otdCarrierOrder.getCarrierId(),otdCarrierOrder.getCarrierOrderNumber(),otdCarrierOrder.getCarrierOrderId());
        }
        if(flag){
            return JsonResult.error("该托运单号已存在！");
        }
        //判断是否重复选择合单和被合单的运输订单
        if(model.getDetailViews()!=null){
            if(!service.checkTheSameMerge(model)){
                return JsonResult.error("重复选择合单与被合单！");
            }
        }

        UUID feeOrderPayableId= service.send(model);
        return JsonResult.success(feeOrderPayableId);
    }

    @RequestMapping(value="/cancel" ,method = RequestMethod.POST)
    @RequiresPermissions("CARRIERORDER_CANCEL")
    public @ResponseBody JsonResult cancel(@RequestBody UUID carrierId){
        try {
            service.updateStatus(carrierId,-1,"托运单取消");//取消
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="/repealCancel" ,method = RequestMethod.POST)
    public @ResponseBody JsonResult repealCancel(@RequestBody UUID carrierId){
        try {
            return service.repealCancel(carrierId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/updateTransportOrderStatus" ,method = RequestMethod.POST)
    public @ResponseBody JsonResult updateTransportOrderStatus(@RequestBody UUID transportOrderId) {
        try {
            service.updateTransportOrderStatus(transportOrderId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="/batchArrive" ,method = RequestMethod.POST)
    @RequiresPermissions("ORDERLOG_ARRIVE")
    public @ResponseBody JsonResult batchArrive(@RequestBody Set<UUID> carrierIds){
        try {
            service.batchArrive(carrierIds);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="/resetSend" ,method = RequestMethod.POST)
    //@RequiresPermissions("CARRIERORDER_OK")
    public @ResponseBody JsonResult resetSend(@RequestBody UUID carrierId){
        try {
            service.resetSend(carrierId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="/ok" ,method = RequestMethod.POST)
    @RequiresPermissions("CARRIERORDER_OK")
    public @ResponseBody JsonResult ok(@RequestBody UUID carrierId){
        try {
            service.ok(carrierId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/carrierOrderNumberIsExist" ,method = RequestMethod.GET)
    public @ResponseBody boolean carrierOrderNumberIsExist(@RequestParam("carrierId") UUID carrierId,
                                                           @RequestParam("carrierOrderId") UUID carrierOrderId,@RequestParam("carrierOrderNumber") String number) {
        boolean  result = service.carrierOrderNumberIsExist(carrierId, number);
        if(carrierOrderId!=null){
            result=service.carrierOrderNumberIsExist(carrierId, number, carrierOrderId);
        }
        return !result;
    }
}























