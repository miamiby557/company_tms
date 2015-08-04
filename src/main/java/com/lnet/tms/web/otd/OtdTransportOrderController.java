package com.lnet.tms.web.otd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.ImportResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.fee.FeeOrderReceivable;
import com.lnet.tms.model.otd.*;
import com.lnet.tms.service.otd.OtdTransportOrderDetailService;
import com.lnet.tms.service.otd.OtdTransportOrderLogViewService;
import com.lnet.tms.service.otd.OtdTransportOrderService;
import com.lnet.tms.service.fee.FeeOrderReceivableService;
import com.lnet.tms.service.otd.*;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/otdTransportOrder")
public class OtdTransportOrderController extends CrudController<OtdTransportOrder, UUID, OtdTransportOrderService> {

    @Autowired
    public void setBaseService(OtdTransportOrderService service) {
        super.setService(service);
    }

    @Autowired
    public OtdTransportOrderDetailService otdTransportOrderDetailService;

    @Autowired
    public OtdTransportOrderLogViewService otdTransportOrderLogViewService;

    @Autowired
    private OtdCarrierOrderDetailService otdCarrierOrderDetailService;

    @Autowired
    private FeeOrderReceivableService feeOrderReceivableService;

    @Autowired
    private HibernateAwareObjectMapper mapper;

    private static List<ImportResult>results=new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "otd/otdTransportOrder/index";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search() {
        return "otd/otdTransportOrder/search";
    }

    @RequestMapping("/mergeSearch/{clientId}")
    public String mergeSearch(@PathVariable UUID clientId, ModelMap model){
        model.addAttribute("clientId",clientId);
        return "otd/otdTransportOrder/mergeSearch";
    }

    @RequestMapping("/create")
    @RequiresPermissions("TRANSPORTORDER_CREATE")
    public String create(){
//        SysUser sysUser = IdentityUtils.getCurrentUser();
//    UUID branchId = sysUser.getBranchId();
//    model.addAttribute("branchId",branchId);
    return "otd/otdTransportOrder/create";
}



    @RequestMapping(value="/modify/{id}")
    @RequiresPermissions("TRANSPORTORDER_MODIFY")
    public String modify(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException{
        OtdTransportOrder otdTransportOrder=service.getWith(id,"details");
        model.addAttribute("otdTransportOrder",otdTransportOrder);
        model.addAttribute("otdTransportOrderJson",mapper.writeValueAsString(otdTransportOrder));
        model.addAttribute("otdTransportOrderDetailsJson",mapper.writeValueAsString(otdTransportOrder.getDetails()));
        return "otd/otdTransportOrder/modify";
    }

    @RequestMapping(value="/confirm/{id}")
    @RequiresPermissions("TRANSPORTORDER_CONFIRM")
    public String confirm(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException{
        OtdTransportOrder otdTransportOrder=service.getWith(id,"details");
        model.addAttribute("otdTransportOrder",otdTransportOrder);
        model.addAttribute("otdTransportOrderJson", mapper.writeValueAsString(otdTransportOrder));
        return "otd/otdTransportOrder/confirm";
    }

    @RequestMapping(value="/confirm",method = RequestMethod.POST)
    public @ResponseBody JsonResult confirm(@RequestBody OtdTransportOrder model) {
        UUID orderReceivableId=service.confirm(model,"confirm");
        return JsonResult.success(orderReceivableId);
    }

    @RequestMapping(value="/updateAndConfirm",method = RequestMethod.POST)
    @RequiresPermissions("TRANSPORTORDER_CONFIRM")
    public @ResponseBody JsonResult updateAndConfirm(@RequestBody OtdTransportOrder model) {
        if(service.clientOrderNumberIsExist(model.getClientId(),model.getClientOrderNumber(),model.getTransportOrderId())){
            return JsonResult.error("此客户单号已存在！");
        }
        UUID feeOrderReceivableId=service.updateAndConfirm(model);
        return JsonResult.success(feeOrderReceivableId);
    }

    @RequestMapping(value="/repealConfirm",method = RequestMethod.POST)
    public @ResponseBody JsonResult repealConfirm(@RequestBody UUID transportOrderId) {
        return service.repealConfirm(transportOrderId);
    }

    @RequestMapping(value="/sign/{transportOrderId}")
    @RequiresPermissions("ORDERLOG_CONFIRM")
    public String sign(@PathVariable UUID transportOrderId){
        return "otd/sign/create";
    }

    @Override
    public JsonResult create(@RequestBody OtdTransportOrder model) {
        return service.createOrder(model);
    }

    @Override
    public JsonResult update(@RequestBody OtdTransportOrder model) {
        if(service.clientOrderNumberIsExist(model.getClientId(),model.getClientOrderNumber(),model.getTransportOrderId())){
            return JsonResult.error("此客户单号已存在！");
        }
        return super.update(model);
    }

    /**
     * 查询已确认的订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/getOkTransportOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getOkTransportOrder(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        list.add(new DataSourceRequest.FilterDescriptor("status","eq",2));
        return service.getDataSource(request);
    }
    @RequestMapping(value = "/getTransportOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getTransportOrder(@RequestBody DataSourceRequest request) {
        if(request.getFilter().getFilters()==null||request.getFilter().getFilters().size()<1 ){
            return null;
        }
        return service.getDataSource(request);
    }

    @RequestMapping(value="/cancel" ,method = RequestMethod.POST)
    @RequiresPermissions("TRANSPORTORDER_CANCEL")
    public @ResponseBody JsonResult cancel(@RequestBody UUID otdTransportOrderId){
        try {
            service.cancel(otdTransportOrderId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/repealCancel" ,method = RequestMethod.POST)
    @RequiresPermissions("TRANSPORTORDER_CANCEL")
    public @ResponseBody JsonResult repealCancel(@RequestBody UUID otdTransportOrderId){
        try {
            service.repealCancel(otdTransportOrderId);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }


    @RequestMapping(value="/detail/{id}")
    public String detail(@PathVariable("id") UUID id, ModelMap model) throws JsonProcessingException {
        OtdTransportOrder otdTransportOrder=service.getWith(id,"details");
        model.addAttribute("otdTransportOrder",otdTransportOrder);
        model.addAttribute("otdTransportOrderJson",mapper.writeValueAsString(otdTransportOrder));
        if(otdTransportOrder.getMergeStatus()<3){//订单
            List<OtdTransportOrderLogView>logs=otdTransportOrderLogViewService.getAllByField("transportOrderId",id);
            model.addAttribute("otdTransportOrderDetailsJson",mapper.writeValueAsString(otdTransportOrder.getDetails()));
            model.addAttribute("otdTransportOrderLogsJson",mapper.writeValueAsString(logs));
            return "otd/otdTransportOrder/detail";  
        }else{//合单
            List<OtdTransportOrderDetailBean>details=new ArrayList<>();
            List<OtdTransportOrder>orders=service.getAllByField("mergeTransportOrderId",id);
            for(OtdTransportOrder order:orders){
                OtdTransportOrder otdTransportOrder1=service.getWith(order.getTransportOrderId(),"details");
                for(OtdTransportOrderDetail otdTransportOrderDetail:otdTransportOrder1.getDetails()){
                    OtdTransportOrderDetailBean detailBean=new OtdTransportOrderDetailBean();
                    BeanUtils.copyProperties(otdTransportOrderDetail,detailBean);
                    detailBean.setClientOrderNumber(otdTransportOrder1.getClientOrderNumber());
                    details.add(detailBean);
                }
            }
            int flag=0;
            OtdCarrierOrderDetail otdCarrierOrderDetail=otdCarrierOrderDetailService.getByField("transportOrderId",id);
            if(otdCarrierOrderDetail!=null){
                flag=1;//合单已发运
            }
            FeeOrderReceivable feeOrderReceivable=feeOrderReceivableService.getByField("sourceOrderId", id);
            if (feeOrderReceivable.getStatus() != 1) {
                flag=1;//应收已确认
            }
            model.addAttribute("flag",mapper.writeValueAsString(flag));
            model.addAttribute("otdTransportOrderDetailsJson",mapper.writeValueAsString(details));
            return "otd/otdTransportOrder/mergeDetail";
        }
    }

    @RequestMapping(value="/getExpectedDate",method = RequestMethod.GET)
    public @ResponseBody Date getExpectedDate(@RequestParam("clientId") UUID clientId,@RequestParam("transportType") Integer transportType,
                                              @RequestParam("startCityId") UUID startCityId,@RequestParam("destCityId") UUID destCityId,
                                              @RequestParam("orderDate")String orderDate) throws ParseException {
        return service.getExpectedDate(clientId,transportType,startCityId,destCityId,orderDate);
    }

    @RequestMapping(value="/clientOrderNumberIsExist",method = RequestMethod.GET)
    public @ResponseBody JsonResult clientOrderNumberIsExist(@RequestParam("clientId") UUID clientId,@RequestParam("clientOrderNumber") String clientOrderNumber){
        if(service.clientOrderNumberIsExist(clientId,clientOrderNumber)){
            return JsonResult.error("此客户单号已存在！");
        }
        return JsonResult.success();
    }

    @RequestMapping(value="/clientOrderNumberModifyIsExist",method = RequestMethod.GET)
    public @ResponseBody JsonResult clientOrderNumberModifyIsExist(@RequestParam("clientId") UUID clientId,@RequestParam("clientOrderNumber") String clientOrderNumber,@RequestParam("transportOrderId") UUID transportOrderId){
        if(service.clientOrderNumberIsExist(clientId,clientOrderNumber,transportOrderId)){
            return JsonResult.error("此客户单号已存在！");
        }
        return JsonResult.success();
    }

    @RequestMapping("/importOrder")
    @RequiresPermissions("TRANSPORTORDER_IMPORTORDER")
    public String importOrder(){
        return "otd/otdTransportOrder/uploadOrder";
    }

    @RequestMapping("/importData")
    public String importData(@RequestParam MultipartFile file,ModelMap model) throws IOException {
        results=service.readExcel(file);
        return "redirect:/#otdTransportOrder/importResult";
    }

    @RequestMapping("/importResult")
    public String importResult(){
        return "otd/otdTransportOrder/importResult";
    }

    @RequestMapping(value = "/getImportResult", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getResult(@RequestBody DataSourceRequest request){
        DataSourceResult dataSourceResult=new DataSourceResult();
        dataSourceResult.setData(results);
        results=new ArrayList<>();
        return dataSourceResult;
    }

    @RequestMapping(value="/merge",method = RequestMethod.GET)
    @RequiresPermissions("TRANSPORTORDER_MERGE")
    public String merge(ModelMap modelMap) throws JsonProcessingException {
        return "otd/otdTransportOrder/merge";
    }
   /* @RequestMapping(value="/merge/{selectIds}",method = RequestMethod.GET)
    @RequiresPermissions("TRANSPORTORDER_MERGE")
    public String merge(@PathVariable("selectIds")List<UUID>transportOrderIds,ModelMap modelMap) throws JsonProcessingException {
        OtdTransportOrder order=service.get(transportOrderIds.get(0));
        String clientOrderNumber=order.getClientOrderNumber();
        String lnetOrderNumber=order.getLnetOrderNumber();
        int itemQuantity=order.getConfirmedItemQuantity()==null?0:order.getConfirmedItemQuantity();
        int packageQuantity=order.getConfirmedPackageQuantity()==null?0:order.getConfirmedPackageQuantity();
        double volume=order.getConfirmedVolume()==null?0:order.getConfirmedVolume();
        double weight=order.getConfirmedWeight()==null?0:order.getConfirmedWeight();
        String remark=transportOrderIds.get(0).toString();//保存所有被合单的订单ID
        int receiptPageNumber=1;
        for(int i=1;i<transportOrderIds.size();i++){
            OtdTransportOrder otdTransportOrder=service.get(transportOrderIds.get(i));
            clientOrderNumber=clientOrderNumber+","+otdTransportOrder.getClientOrderNumber();
            lnetOrderNumber=lnetOrderNumber+","+otdTransportOrder.getLnetOrderNumber();
            itemQuantity=itemQuantity+otdTransportOrder.getConfirmedItemQuantity();
            packageQuantity=packageQuantity+otdTransportOrder.getConfirmedPackageQuantity();
            volume=volume+otdTransportOrder.getConfirmedVolume();
            weight=weight+otdTransportOrder.getConfirmedWeight();
            remark=remark+","+transportOrderIds.get(i);
            receiptPageNumber+=1;
        }
        order.setClientOrderNumber(clientOrderNumber);
        order.setLnetOrderNumber(lnetOrderNumber);
        order.setConfirmedItemQuantity(itemQuantity);
        order.setTotalItemQuantity(itemQuantity);
        order.setConfirmedPackageQuantity(packageQuantity);
        order.setTotalPackageQuantity(packageQuantity);
        order.setConfirmedVolume(volume);
        order.setTotalVolume(volume);
        order.setConfirmedWeight(weight);
        order.setTotalWeight(weight);
        order.setRemark(remark);
        order.setSentDate(null);
        order.setExpectedDate(null);
        order.setCreateDate(null);
        order.setCreateUserId(null);
        order.setModifyUserId(null);
        order.setModifyDate(null);
        order.setPickupOrderId(null);
        order.setStatus(null);
        order.setTransportOrderId(null);
        order.setDetails(null);
        order.setReceiptPageNumber(receiptPageNumber);
        modelMap.addAttribute("otdTransportOrderJson",mapper.writeValueAsString(order));
        //明細
        List<OtdTransportOrderDetailBean>details=new ArrayList<>();
        for(UUID transportOrderId:transportOrderIds){
            OtdTransportOrder otdTransportOrder1=service.getWith(transportOrderId,"details");
            for(OtdTransportOrderDetail otdTransportOrderDetail:otdTransportOrder1.getDetails()){
                OtdTransportOrderDetailBean detailBean=new OtdTransportOrderDetailBean();
                BeanUtils.copyProperties(otdTransportOrderDetail,detailBean);
                detailBean.setClientOrderNumber(otdTransportOrder1.getClientOrderNumber());
                details.add(detailBean);
            }
        }
        modelMap.addAttribute("otdTransportOrderDetailsJson",mapper.writeValueAsString(details));
        return "otd/otdTransportOrder/merge";
    }*/

    @RequestMapping(value="/mergeAdd",method = RequestMethod.POST)
    @RequiresPermissions("TRANSPORTORDER_MERGE")
    public @ResponseBody JsonResult mergeAdd(@RequestBody OtdTransportOrderBean bean){
        if(service.clientOrderNumberIsExist(bean.getClientId(),bean.getClientOrderNumber(),bean.getTransportOrderId())){
            return JsonResult.error("此客户单号已存在！");
        }
        for (UUID id : bean.getTransportOrderIds()) {
            OtdTransportOrder otdTransportOrder = service.get(id);
            if (otdTransportOrder.getStatus() > 1) {
                FeeOrderReceivable feeOrderReceivable= feeOrderReceivableService.getByField("sourceOrderId", id);
                if(feeOrderReceivable.getStatus()>1){
                    return JsonResult.error("订单号"+otdTransportOrder.getClientOrderNumber()+"的应收费用已确认，不能进行合单操作！");
                }
            }
        }

        return JsonResult.success(service.mergeAdd(bean));
    }


    @RequestMapping(value="/merge",method = RequestMethod.POST)
    public @ResponseBody JsonResult merge(@RequestBody OtdTransportOrderBean bean){
        if (service.clientOrderNumberIsExist(bean.getClientId(), bean.getClientOrderNumber())) {
            return JsonResult.error("此客户单号已存在！");
        }
        for (UUID id : bean.getTransportOrderIds()) {
            OtdTransportOrder otdTransportOrder = service.get(id);
            if (otdTransportOrder.getStatus() > 1) {
                FeeOrderReceivable feeOrderReceivable= feeOrderReceivableService.getByField("sourceOrderId", id);
                if(feeOrderReceivable.getStatus()>1){
                    return JsonResult.error("订单号"+otdTransportOrder.getClientOrderNumber()+"的应收费用已确认，不能进行合单操作！");
                }
            }
        }

        UUID clientId=bean.getClientId();
        for(UUID transportOrderId:bean.getTransportOrderIds()){
            UUID clientId1=service.get(transportOrderId).getClientId();
            if(!clientId1.equals(clientId)){
                return JsonResult.error("合单只能为同一客户运输订单！");
            }
        }
        UUID orderReceivableId = service.mergeOrder(bean);
        return JsonResult.success(orderReceivableId);
    }


    @RequestMapping(value="/repealMerge",method = RequestMethod.POST)
    @RequiresPermissions("TRANSPORTORDER_MERGE")
    public @ResponseBody JsonResult repealMerge(@RequestBody UUID transportOrderId){
        return service.repealMerge(transportOrderId);
    }

    @RequestMapping(value="/batchConfirm",method = RequestMethod.POST)
    public @ResponseBody JsonResult batchConfirm(@RequestBody List<UUID> transportOrderIds){
        try {
            for(UUID otdTransportOrderId:transportOrderIds){
                service.confirm(service.getWith(otdTransportOrderId,"details"),"confirm");
            }
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/addMarketNumber/{transportOrderIds}")
    public String addMarketNumber (@PathVariable("transportOrderIds") List<UUID> transportOrderIds, ModelMap model) throws JsonProcessingException {
        model.addAttribute("transportOrderIds", mapper.writeValueAsString(transportOrderIds));
        return "otd/otdTransportOrderLog/addMarketNumber";
    }

    @RequestMapping(value="/addMarketNumber",method = RequestMethod.POST)
    public @ResponseBody JsonResult addMarketNumber(@RequestBody OtdTransportOrderBean otdTransportOrderBean){
        try {
            service.addMarketNumber(otdTransportOrderBean);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="/modifyNumber/{transportOrderId}")
    public String modifyNumber (@PathVariable("transportOrderId") UUID transportOrderId, ModelMap model) throws JsonProcessingException {

        OtdTransportOrder otdTransportOrder=service.get(transportOrderId);
        model.addAttribute("otdTransportOrder",otdTransportOrder);
        model.addAttribute("otdTransportOrderJson",mapper.writeValueAsString(otdTransportOrder));
        model.addAttribute("otdTransportOrderDetailsJson",mapper.writeValueAsString(service.getWith(transportOrderId,"details").getDetails()));
        return "otd/otdTransportOrder/modifyNumber";
    }

}

