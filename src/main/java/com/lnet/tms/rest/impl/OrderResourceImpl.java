package com.lnet.tms.rest.impl;

import com.lnet.tms.dao.SequenceDao;
import com.lnet.tms.model.base.BaseExacct;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.model.crm.CrmClient;
import com.lnet.tms.model.dispatch.DispatchAssign;
import com.lnet.tms.model.dispatch.DispatchAssignDetail;
import com.lnet.tms.model.dispatch.DispatchVehicle;
import com.lnet.tms.model.fee.FeeOrderPayable;
import com.lnet.tms.model.fee.FeeOrderPayableDetail;
import com.lnet.tms.model.otd.*;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.model.sys.SysList;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.model.sys.SysOrganization;
import com.lnet.tms.rest.restUtil.*;
import com.lnet.tms.rest.spi.OrderResource;
import com.lnet.tms.service.base.BaseExacctService;
import com.lnet.tms.service.base.BaseRegionService;
import com.lnet.tms.service.crm.CrmClientService;
import com.lnet.tms.service.dispatch.DispatchAssignService;
import com.lnet.tms.service.dispatch.DispatchVehicleService;
import com.lnet.tms.service.fee.FeeOrderPayableDetailService;
import com.lnet.tms.service.fee.FeeOrderPayableService;
import com.lnet.tms.service.fee.PayableCalculator;
import com.lnet.tms.service.otd.*;
import com.lnet.tms.service.scm.ScmCarrierService;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.service.sys.SysListService;
import com.lnet.tms.service.sys.SysOrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by admin on 2015/7/10.
 */
@Service
public class OrderResourceImpl implements OrderResource{

    @Autowired
    private OtdTransportOrderService transportOrderService;

    @Autowired
    private OtdCarrierOrderService carrierOrderService;

    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    private BaseExacctService baseExacctService;

    @Autowired
    PayableCalculator payableCalculator;

    @Autowired
    private FeeOrderPayableService feeOrderPayableService;

    @Autowired
    private FeeOrderPayableDetailService payableDetailService;

    @Autowired
    OtdCarrierOrderDetailService detailService;

    @Autowired
    BaseRegionService baseRegionService;

    @Autowired
    SysOrganizationService organizationService;

    @Autowired
    private SysListService sysListService;

    @Autowired
    private SysListItemService listItemService;

    @Autowired
    private CrmClientService crmClientService;

    @Autowired
    private ScmCarrierService carrierService;

    @Autowired
    private DispatchVehicleService vehicleService;

    @Autowired
    private DispatchAssignService assignService;

    @Autowired
    private OtdOrderSignService orderSignService;

    @Override
    public ServiceResult getTransportOrderByNumber(String orderNumber) {
        if(transportOrderService.existsBy("clientOrderNumber",orderNumber)){
            OtdTransportOrder order = transportOrderService.getByField("clientOrderNumber",orderNumber);
            ServiceResult result = new ServiceResult(order);
            return result;
        }
        ServiceResult result = new ServiceResult(false);
        return result;
    }



    @Override
    public ServiceResult getCarrierOrderByNumber(String orderNumber) {
        if(carrierOrderService.existsBy("carrierOrderNumber",orderNumber)){
            ServiceResult result = new ServiceResult();
            return result;
        }
        ServiceResult result = new ServiceResult(false);
        return result;
    }

    @Override
    public ServiceResult getTransportOrderByClient(OrderRequest orderRequest) {
        Map<String ,Object> map = new HashMap<>();
        map.put("clientId",orderRequest.getId());
        map.put("clientOrderNumber",orderRequest.getOrderNumber());
        OtdTransportOrder order = transportOrderService.getByField(map);
        if(order!=null){
           return new ServiceResult();
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult getCarrierOrderByCarrier(OrderRequest orderRequest) {
        Map<String ,Object> map = new HashMap<>();
        map.put("carrierId",orderRequest.getId());
        map.put("carrierOrderNumber",orderRequest.getOrderNumber());
        OtdCarrierOrder order = carrierOrderService.getByField(map);
        if(order!=null){
           return new ServiceResult();
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult getTransportOrderByCarrierOrder(String orderNumber) {
        Map<String,Object> map = new HashMap<>();
        map.put("clientOrderNumber",orderNumber);
        map.put("status",2);
        List<OtdTransportOrder> orders = transportOrderService.getListByField(map);
        int size = orders.size();
        if(size==0){
           return new ServiceResult(false);
        }else if(size==1){
            return new ServiceResult(orders);
        }else {
            List<HelpItem> items = new ArrayList<>();
            for(OtdTransportOrder order:orders){
                CrmClient crmClient = crmClientService.get(order.getClientId());
                String clientName = crmClient.getName();
                HelpItem item = new HelpItem(order.getTransportOrderId(),clientName,order.getClientOrderNumber());
                items.add(item);
            }
            return new ServiceResult(items);
        }
    }


    @Override
    public ServiceResult getTransportOrderById(UUID orderId) {
        OtdTransportOrder otdTransportOrder = transportOrderService.getByField("transportOrderId",orderId);
        AppTransportOrder transportOrder = new AppTransportOrder();
        BeanUtils.copyProperties(otdTransportOrder,transportOrder);
        fillOtherData(transportOrder);
        ServiceResult result = new ServiceResult(transportOrder);
        return result;
    }

    private void fillOtherData(AppTransportOrder transportOrder) {
        CrmClient client = crmClientService.getByField("clientId",transportOrder.getClientId());
        if(client!=null){
            transportOrder.setClientName(client.getName());
        }
        BaseRegion startCity = baseRegionService.getByField("regionId",transportOrder.getStartCityId());
        if(startCity!=null){
            transportOrder.setStartCity(startCity.getName());
        }
        BaseRegion destCity = baseRegionService.getByField("regionId",transportOrder.getDestCityId());
        if(destCity!=null){
            transportOrder.setDestCity(destCity.getName());
        }
        UUID settleId = sysListService.getByField("code","settleCycle").getListId();
        List<SysListItem> settleList = listItemService.getAllByField("listId",settleId);
        for(SysListItem item:settleList){
            if(item.getValue().equals(transportOrder.getBillingCycle())){
                transportOrder.setBillingCycleName(item.getName());
                break;
            }
        }
        UUID paymentId = sysListService.getByField("code","paymentType").getListId();
        List<SysListItem> paymentList = listItemService.getAllByField("listId",paymentId);
        for(SysListItem item:paymentList){
            if(item.getValue().equals(transportOrder.getPaymentType())){
                transportOrder.setPaymentTypeName(item.getName());
                break;
            }
        }
        UUID calculateId = sysListService.getByField("code","calculateType").getListId();
        List<SysListItem> calculateList = listItemService.getAllByField("listId",calculateId);
        for(SysListItem item:calculateList){
            if(item.getValue().equals(transportOrder.getCalculateType())){
                transportOrder.setCalculateTypeName(item.getName());
                break;
            }
        }
        UUID transportId = sysListService.getByField("code","transportType").getListId();
        List<SysListItem> transportList = listItemService.getAllByField("listId",transportId);
        for(SysListItem item:transportList){
            if(item.getValue().equals(transportOrder.getTransportType())){
                transportOrder.setTransportTypeName(item.getName());
                break;
            }
        }
        UUID urgencyLevelId = sysListService.getByField("code","urgencyLevel").getListId();
        List<SysListItem> urgencyList = listItemService.getAllByField("listId",urgencyLevelId);
        for(SysListItem item:urgencyList){
            if(item.getValue().equals(transportOrder.getUrgencyLevel())){
                transportOrder.setUrgencyLevelName(item.getName());
                break;
            }
        }
        UUID wrapTypeId = sysListService.getByField("code","wrapType").getListId();
        List<SysListItem> wrapTypeList = listItemService.getAllByField("listId",wrapTypeId);
        for(SysListItem item:wrapTypeList){
            if(item.getValue().equals(transportOrder.getWrapType())){
                transportOrder.setUrgencyLevelName(item.getName());
                break;
            }
        }
    }

    @Override
    public ServiceResult getCarrierOrderById(UUID orderId) {
        OtdCarrierOrder otdCarrierOrder = carrierOrderService.getByField("carrierOrderId",orderId);
        AppCarrierOrder carrierOrder = new AppCarrierOrder();
        BeanUtils.copyProperties(otdCarrierOrder,carrierOrder);
        fillOtherData2(carrierOrder);
        ServiceResult result = new ServiceResult(carrierOrder);
        return result;
    }

    private void fillOtherData2(AppCarrierOrder carrierOrder) {
        ScmCarrier carrier = carrierService.getByField("carrierId",carrierOrder.getCarrierId());
        if(carrier!=null){
            carrierOrder.setCarrierName(carrier.getName());
        }
        SysOrganization organization = organizationService.getByField("organizationId",carrierOrder.getTransferOrganizationId());
        if(organization!=null){
            carrierOrder.setTransferOrganizationValue(organization.getName());
        }

        BaseRegion startCity = baseRegionService.getByField("regionId",carrierOrder.getStartCityId());
        if(startCity!=null){
            carrierOrder.setStartCity(startCity.getName());
        }
        BaseRegion destCity = baseRegionService.getByField("regionId",carrierOrder.getDestCityId());
        if(destCity!=null){
            carrierOrder.setDestCity(destCity.getName());
        }
        UUID settleId = sysListService.getByField("code","settleCycle").getListId();
        List<SysListItem> settleList = listItemService.getAllByField("listId",settleId);
        for(SysListItem item:settleList){
            if(item.getValue().equals(carrierOrder.getBillingCycle())){
                carrierOrder.setBillingCycleName(item.getName());
                break;
            }
        }
        UUID wrapTypeId = sysListService.getByField("code","wrapType").getListId();
        List<SysListItem> wrapTypeList = listItemService.getAllByField("listId",wrapTypeId);
        for(SysListItem item:wrapTypeList){
            if(item.getValue().equals(carrierOrder.getWrapType())){
                carrierOrder.setWrapTypeName(item.getName());
                break;
            }
        }
        UUID handoverId = sysListService.getByField("code","handoverType").getListId();
        List<SysListItem> handoverList = listItemService.getAllByField("listId",handoverId);
        for(SysListItem item:handoverList){
            if(item.getValue().equals(carrierOrder.getWrapType())){
                carrierOrder.setHandoverTypeName(item.getName());
                break;
            }
        }
        UUID paymentId = sysListService.getByField("code","paymentType").getListId();
        List<SysListItem> paymentList = listItemService.getAllByField("listId",paymentId);
        for(SysListItem item:paymentList){
            if(item.getValue().equals(carrierOrder.getPaymentType())){
                carrierOrder.setPaymentTypeName(item.getName());
                break;
            }
        }
        UUID calculateId = sysListService.getByField("code","calculateType").getListId();
        List<SysListItem> calculateList = listItemService.getAllByField("listId",calculateId);
        for(SysListItem item:calculateList){
            if(item.getValue().equals(carrierOrder.getCalculateType())){
                carrierOrder.setCalculateTypeName(item.getName());
                break;
            }
        }
        UUID transportId = sysListService.getByField("code","transportType").getListId();
        List<SysListItem> transportList = listItemService.getAllByField("listId",transportId);
        for(SysListItem item:transportList){
            if(item.getValue().equals(carrierOrder.getTransportType())){
                carrierOrder.setTransportTypeName(item.getName());
                break;
            }
        }
    }

    @Override
    public ServiceResult transportOrderListByNumber(OrderListRequest request) {
        System.out.println("userId:"+request.getUserId()+",number:"+request.getNumber());
        List<OtdTransportOrder> orderList = transportOrderService.getList("clientOrderNumber",request.getNumber(),request.getUserId(),"createDate");
        ServiceResult result = new ServiceResult(orderList);
        return result;
    }

    @Override
    public ServiceResult carrierOrderListByNumber(OrderListRequest request) {
        List<OtdCarrierOrder> orderList = carrierOrderService.getList("carrierOrderNumber",request.getNumber(),request.getUserId(),"createDate");
        ServiceResult result = new ServiceResult(orderList);
        return result;
    }

    @Override
    @Transactional
    public ServiceResult transportOrderCreate(OtdTransportOrder otdTransportOrder) {
        otdTransportOrder.setLnetOrderNumber(sequenceDao.formatSequenceNumber("SEQ_TRANORDER_NUMBER", "00000000", "LNET", ""));
        if(otdTransportOrder.getStartCityId()!=null){
            BaseRegion baseRegion = baseRegionService.get(otdTransportOrder.getStartCityId());
            otdTransportOrder.setStartCity(baseRegion.getName());
        }
        if(otdTransportOrder.getDestCityId()!=null){
            BaseRegion baseRegion = baseRegionService.get(otdTransportOrder.getDestCityId());
            otdTransportOrder.setDestCity(baseRegion.getName());
        }
        transportOrderService.saveOrUpdate(otdTransportOrder);
        //计算应收
        transportOrderService.confirm(otdTransportOrder,"confirm");
        ServiceResult result = new ServiceResult(otdTransportOrder);
        return result;
    }

    @Override
    @Transactional
    public ServiceResult carrierOrderCreate(OtdCarrierOrderBean otdCarrierOrderBean) {
        OtdCarrierOrder otdCarrierOrder = carrierOrderService.getByField("carrierOrderNumber",otdCarrierOrderBean.getCarrierOrderNumber());
        if(otdCarrierOrder!=null){
           return new ServiceResult(false);
        }
        if(otdCarrierOrderBean.getStartCityId()!=null){
            BaseRegion baseRegion = baseRegionService.get(otdCarrierOrderBean.getStartCityId());
            otdCarrierOrderBean.setStartCity(baseRegion.getName());
        }
        if(otdCarrierOrderBean.getDestCityId()!=null){
            BaseRegion baseRegion = baseRegionService.get(otdCarrierOrderBean.getDestCityId());
            otdCarrierOrderBean.setDestCity(baseRegion.getName());
        }
        updateTransportReceivePageNumberAndWrapType(otdCarrierOrderBean);
        UUID orderPayableId = carrierOrderService.send(otdCarrierOrderBean);
        ServiceResult result = new ServiceResult(new FeeOrderPayable(orderPayableId));
        return result;
    }

    private void updateTransportReceivePageNumberAndWrapType(OtdCarrierOrderBean otdCarrierOrderBean) {
        for(OtdCarrierOrderDetailView detailView:otdCarrierOrderBean.getDetailViews()){
            UUID uuid = detailView.getTransportOrderId();
            OtdTransportOrder transportOrder = transportOrderService.get(uuid);
            transportOrder.setReceiptPageNumber(detailView.getReceiptPageNumber());
            transportOrder.setWrapType(detailView.getWrapType());
            transportOrderService.update(transportOrder);
        }
    }

    @Override
    @Transactional
    public ServiceResult calculate(OtdCarrierOrder otdCarrierOrder) {
        FeeOrderPayable orderPayable = payableCalculator.calculate(otdCarrierOrder);
        List<FeeOrderPayableJson> payableJsonList = new ArrayList<>();
        for(FeeOrderPayableDetail detail:orderPayable.getDetails()){
            BaseExacct baseExacct = baseExacctService.get(detail.getExacctId());
            if(baseExacct!=null){
                FeeOrderPayableJson payableJson = new FeeOrderPayableJson(baseExacct.getName(),detail.getAmount(),baseExacct.getExacctId().toString());
                payableJsonList.add(payableJson);
            }
        }
        ServiceResult result = new ServiceResult(payableJsonList);
        return result;
    }


    @Override
    @Transactional
    public ServiceResult updatePayable(UUID orderPayableId,FeeOrderPayables payables) {
        FeeOrderPayable payable = feeOrderPayableService.get(orderPayableId);
        if(payable!=null){
            //把已保存的费用先清空
            payable.getDetails().clear();
            double count = new Double(0);
            //这一步更新可能修改的费用
            for(FeeOrderPayableJson payableJson:payables.getPayableJsonSet()){
                FeeOrderPayableDetail detail = new FeeOrderPayableDetail(UUID.fromString(payableJson.getExacctId()),new Double(payableJson.getExacctMoney()),orderPayableId,payable);
                payableDetailService.saveOrUpdate(detail);
                payable.getDetails().add(detail);
                count = add(count,payableJson.getExacctMoney());
            }
            payable.setTotalAmount(count);
            feeOrderPayableService.saveOrUpdate(payable);
            ServiceResult result = new ServiceResult(payable);
            return result;
        }
        return new ServiceResult(false);
    }


    @Override
    public ServiceResult getTransportOrderList(UUID userId) {
        List<OtdTransportOrder> otdTransportOrderList = transportOrderService.getAllByFieldDesc("createUserId", userId,"createDate");
        ServiceResult result = new ServiceResult(otdTransportOrderList);
        return result;
    }

    @Override
    public ServiceResult getSupperBaseRegion() {
        List<BaseRegion> baseRegionList = baseRegionService.getAllByField("regionTypeId",1);
        ServiceResult result = new ServiceResult(baseRegionList);
        return result;
    }

    @Override
    public ServiceResult getChildBaseRegion(UUID superRegionId) {
        List<BaseRegion> baseRegionList = baseRegionService.getAllByField("superiorRegionId", superRegionId);
        ServiceResult result = new ServiceResult(baseRegionList);
        return result;
    }

    @Override
    public ServiceResult getCarrierOrderList(UUID userId) {
        List<OtdCarrierOrder> carrierOrderList = carrierOrderService.getAllByFieldDesc("createUserId", userId, "createDate");
        ServiceResult result = new ServiceResult(carrierOrderList);
        return result;
    }

    @Override
    public ServiceResult getOrganization() {
        List<SysOrganization> organizationList = organizationService.getAll();
        ServiceResult result = new ServiceResult(organizationList);
        return result;
    }

    @Override
    public ServiceResult createFeeDeclare(FeeDeclare feeDeclare) {
        FileSaveUtil.save(UUID.randomUUID().toString(),feeDeclare.getImagesString());
        return new ServiceResult();
    }

    @Override
    public ServiceResult getCars() {
        List<DispatchVehicle> vehicles = vehicleService.getAllByAsc("vehicleNumber");
        ServiceResult result = new ServiceResult(vehicles);
        return result;
    }

    @Override
    public ServiceResult createDispatchAssign(DispatchAssign assign) {
        try {
            //计算总数
            countTransportOrder(assign);
            assignService.create(assign);
            return new ServiceResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult(false);
        }
    }

    private void countTransportOrder(DispatchAssign assign) {
        Set<DispatchAssignDetail> details = assign.getDetails();
        for(DispatchAssignDetail detail:details){
            UUID transportOrderId = detail.getOrderId();
            OtdTransportOrder otdTransportOrder = transportOrderService.get(transportOrderId);
            assign.setTotalItemQuantity(assign.getTotalItemQuantity()==null?0:assign.getTotalItemQuantity()+changeInteger(otdTransportOrder.getTotalItemQuantity()));
            assign.setTotalPackageQuantity(assign.getTotalPackageQuantity()==null?0:assign.getTotalPackageQuantity() + changeInteger(otdTransportOrder.getTotalPackageQuantity()));
            assign.setTotalVolume(add(assign.getTotalVolume()==null?new Double(0):assign.getTotalVolume(),otdTransportOrder.getConfirmedVolume()));
            assign.setTotalWeight(add(assign.getTotalWeight()==null?new Double(0):assign.getTotalWeight(),otdTransportOrder.getConfirmedWeight()));
        }
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static int changeInteger(Integer integer){
       if(integer==null){
           return 0;
       }
       return integer.intValue();
    }

    @Override
    public ServiceResult getDispatchAssignList(UUID userId) {
        List<DispatchAssign> assigns = assignService.getAllByFieldDesc("createUserId", userId, "createDate");
        ServiceResult result = new ServiceResult(assigns);
        return result;
    }

    @Override
    public ServiceResult getDispatchAssignListByNumber(OrderListRequest request) {
        List<DispatchAssign> assigns = assignService.getList("dispatchAssignNumber",request.getNumber(),request.getUserId(),"createDate");
        return new ServiceResult(assigns);
    }

    @Override
    public ServiceResult getDispatchAssignById(UUID assignById) {
        DispatchAssign assign = assignService.get(assignById);
        return new ServiceResult(assign);
    }

    @Override
    public ServiceResult orderSignUp(AppOrderSign appOrderSign) {
        try {
            OtdOrderSign otdOrderSign = new OtdOrderSign();
            List<String> photos = appOrderSign.getPhotoStrings();
            int size = appOrderSign.getPhotoStrings().size();
            for(int i =0;i<size;i++){
                FileSaveUtil.save(appOrderSign.getTransportOrderNumber()+"-"+(i+1),photos.get(i));
            }
            OtdTransportOrder transportOrder = transportOrderService.getByField("clientOrderNumber",appOrderSign.getTransportOrderNumber());
            appOrderSign.setTransportOrderId(transportOrder.getTransportOrderId());
            BeanUtils.copyProperties(appOrderSign,otdOrderSign);
            orderSignService.create(otdOrderSign);
            return new ServiceResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult(false);
        }
    }

}
