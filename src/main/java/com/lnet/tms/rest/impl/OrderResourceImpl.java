package com.lnet.tms.rest.impl;

import com.lnet.tms.dao.SequenceDao;
import com.lnet.tms.model.base.BaseExacct;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.model.crm.CrmClient;
import com.lnet.tms.model.fee.FeeOrderPayable;
import com.lnet.tms.model.fee.FeeOrderPayableDetail;
import com.lnet.tms.model.otd.OtdCarrierOrder;
import com.lnet.tms.model.otd.OtdCarrierOrderBean;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.model.sys.SysList;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.model.sys.SysOrganization;
import com.lnet.tms.rest.restUtil.*;
import com.lnet.tms.rest.spi.OrderResource;
import com.lnet.tms.service.base.BaseExacctService;
import com.lnet.tms.service.base.BaseRegionService;
import com.lnet.tms.service.crm.CrmClientService;
import com.lnet.tms.service.fee.FeeOrderPayableDetailService;
import com.lnet.tms.service.fee.FeeOrderPayableService;
import com.lnet.tms.service.fee.PayableCalculator;
import com.lnet.tms.service.otd.OtdCarrierOrderDetailService;
import com.lnet.tms.service.otd.OtdCarrierOrderService;
import com.lnet.tms.service.otd.OtdTransportOrderService;
import com.lnet.tms.service.scm.ScmCarrierService;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.service.sys.SysListService;
import com.lnet.tms.service.sys.SysOrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        transportOrderService.saveOrUpdate(otdTransportOrder);
        ServiceResult result = new ServiceResult(otdTransportOrder);
        return result;
    }

    @Override
    @Transactional
    public ServiceResult carrierOrderCreate(OtdCarrierOrderBean otdCarrierOrderBean) {
        if(carrierOrderService.existsBy("carrierOrderNumber", otdCarrierOrderBean.getCarrierOrderNumber())){
            ServiceResult result = new ServiceResult(false);
            result.addMessage("该托运单号已存在！");
            return result;
        }
        UUID orderPayableId = carrierOrderService.send(otdCarrierOrderBean);
        ServiceResult result = new ServiceResult(new FeeOrderPayable(orderPayableId));
        return result;
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
            //这一步更新可能修改的费用
            for(FeeOrderPayableJson payableJson:payables.getPayableJsonSet()){
                FeeOrderPayableDetail detail = new FeeOrderPayableDetail(UUID.fromString(payableJson.getExacctId()),payableJson.getExacctMoney(),orderPayableId,payable);
                payableDetailService.saveOrUpdate(detail);
                payable.getDetails().add(detail);
            }
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
}
