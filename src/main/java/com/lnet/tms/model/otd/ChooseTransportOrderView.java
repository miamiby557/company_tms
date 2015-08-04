package com.lnet.tms.model.otd;

import com.lnet.tms.model.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@javax.persistence.Table(name = "CHOOSE_TRANSPORT_ORDER_VIEW", schema = "LNET_TMS", catalog = "")
public class ChooseTransportOrderView extends BaseEntity{
    private UUID transportOrderId;

    private UUID pickupOrderId;

    private String clientOrderNumber;

    private String lnetOrderNumber;

    //private UUID branchId;

    private UUID clientId;

    private Date orderDate;

    private Integer orderType;

    private UUID startCityId;

    private String startCity;

    private UUID destCityId;

    private String destCity;

    private String receiveCompany;

    private String receiveMan;

    private String receivePhone;

    private String receiveAddress;

    private Integer handoverType;

    private Integer totalItemQuantity;

    private Integer totalPackageQuantity;

    private Double totalVolume;

    private Double totalWeight;

    private Integer confirmedItemQuantity;

    private Integer confirmedPackageQuantity;

    private Double confirmedVolume;

    private Double confirmedWeight;

    private String specialRequest;

    private Integer billingCycle;

    private Integer paymentType;

    private Integer calculateType;

    private Integer transportType;

    private Date sentDate;

    private String remark;

    private UUID createUserId;

    private Date createDate;

    private UUID modifyUserId;

    private Date modifyDate;

    private Integer status;

    private Integer receiptPageNumber;

    private Integer urgencyLevel;

    private Date expectedDate;

    private Integer wrapType;

    private UUID mergeTransportOrderId;

    private Integer mergeStatus;

    private String viceClientOrderNumber;

    private String marketClientNumber;

    private String obligateClientNumber;

    private Integer mergeSendStatus;

    private String pickupOrderNumber;

    private String branchName;

    private String clientName;

    private String createUserName;

    private String modifyUserName;

    private String transportTypeName;

    private String calculateTypeName;

    private String handoverTypeName;

    private String orderTypeName;

    private String paymentTypeName;

    private String billingCycleName;

    private String statusName;

    private String receiptStatusName;

    private UUID carrierOrderId;

    private String carrierOrderNumber;

    private UUID transferOrganizationId;

    private Integer carrierStatus;

    private String carrierStatusName;

    private String transferOrganizationName;

    private Date receiptDate;

    private Integer isRepair;

    private Integer receiptStatus;

    private String carrierName;

    private String receiptRemark;

    @Id
    @javax.persistence.Column(name = "TRANSPORT_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    @Basic
    @javax.persistence.Column(name = "PICKUP_ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getPickupOrderId() {
        return pickupOrderId;
    }

    public void setPickupOrderId(UUID pickupOrderId) {
        this.pickupOrderId = pickupOrderId;
    }

    @Basic
    @javax.persistence.Column(name = "CLIENT_ORDER_NUMBER", nullable = false, insertable = true, updatable = true)
    public String getClientOrderNumber() {
        return clientOrderNumber;
    }

    public void setClientOrderNumber(String clientOrderNumber) {
        this.clientOrderNumber = clientOrderNumber;
    }

    @Basic
    @javax.persistence.Column(name = "LNET_ORDER_NUMBER", nullable = false, insertable = true, updatable = true)
    public String getLnetOrderNumber() {
        return lnetOrderNumber;
    }

    public void setLnetOrderNumber(String lnetOrderNumber) {
        this.lnetOrderNumber = lnetOrderNumber;
    }
/*

    @Basic
    @javax.persistence.Column(name = "BRANCH_ID", nullable = true, insertable = true, updatable = true)
    public UUID getBranchId() {
        return branchId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }
*/

    @Basic
    @javax.persistence.Column(name = "CLIENT_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Basic
    @javax.persistence.Column(name = "ORDER_DATE", nullable = true, insertable = true, updatable = true)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @javax.persistence.Column(name = "ORDER_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    @Basic
    @javax.persistence.Column(name = "START_CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getStartCityId() {
        return startCityId;
    }

    public void setStartCityId(UUID startCityId) {
        this.startCityId = startCityId;
    }

    @Basic
    @javax.persistence.Column(name = "START_CITY", nullable = true, insertable = true, updatable = true)
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    @Basic
    @javax.persistence.Column(name = "DEST_CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getDestCityId() {
        return destCityId;
    }

    public void setDestCityId(UUID destCityId) {
        this.destCityId = destCityId;
    }

    @Basic
    @javax.persistence.Column(name = "DEST_CITY", nullable = true, insertable = true, updatable = true)
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVE_COMPANY", nullable = true, insertable = true, updatable = true)
    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVE_MAN", nullable = true, insertable = true, updatable = true)
    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVE_PHONE", nullable = true, insertable = true, updatable = true)
    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVE_ADDRESS", nullable = true, insertable = true, updatable = true)
    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    @Basic
    @javax.persistence.Column(name = "HANDOVER_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getHandoverType() {
        return handoverType;
    }

    public void setHandoverType(Integer handoverType) {
        this.handoverType = handoverType;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalItemQuantity() {
        return totalItemQuantity;
    }

    public void setTotalItemQuantity(Integer totalItemQuantity) {
        this.totalItemQuantity = totalItemQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    public void setTotalPackageQuantity(Integer totalPackageQuantity) {
        this.totalPackageQuantity = totalPackageQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedItemQuantity() {
        return confirmedItemQuantity;
    }

    public void setConfirmedItemQuantity(Integer confirmedItemQuantity) {
        this.confirmedItemQuantity = confirmedItemQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedPackageQuantity() {
        return confirmedPackageQuantity;
    }

    public void setConfirmedPackageQuantity(Integer confirmedPackageQuantity) {
        this.confirmedPackageQuantity = confirmedPackageQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getConfirmedVolume() {
        return confirmedVolume;
    }

    public void setConfirmedVolume(Double confirmedVolume) {
        this.confirmedVolume = confirmedVolume;
    }

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getConfirmedWeight() {
        return confirmedWeight;
    }

    public void setConfirmedWeight(Double confirmedWeight) {
        this.confirmedWeight = confirmedWeight;
    }

    @Basic
    @javax.persistence.Column(name = "SPECIAL_REQUEST", nullable = true, insertable = true, updatable = true)
    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    @Basic
    @javax.persistence.Column(name = "BILLING_CYCLE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(Integer billingCycle) {
        this.billingCycle = billingCycle;
    }

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    @Basic
    @javax.persistence.Column(name = "SENT_DATE", nullable = true, insertable = true, updatable = true)
    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    @Basic
    @javax.persistence.Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /*@Basic
    @javax.persistence.Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @javax.persistence.Column(name = "MODIFY_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(UUID modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    @Basic
    @javax.persistence.Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }*/

    @Basic
    @javax.persistence.Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIPT_PAGE_NUMBER", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReceiptPageNumber() {
        return receiptPageNumber;
    }

    public void setReceiptPageNumber(Integer receiptPageNumber) {
        this.receiptPageNumber = receiptPageNumber;
    }

    @Basic
    @javax.persistence.Column(name = "URGENCY_LEVEL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(Integer urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    @Basic
    @javax.persistence.Column(name = "EXPECTED_DATE", nullable = true, insertable = true, updatable = true)
    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    @Basic
    @javax.persistence.Column(name = "WRAP_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getWrapType() {
        return wrapType;
    }

    public void setWrapType(Integer wrapType) {
        this.wrapType = wrapType;
    }

    @Basic
    @javax.persistence.Column(name = "MERGE_TRANSPORT_ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getMergeTransportOrderId() {
        return mergeTransportOrderId;
    }

    public void setMergeTransportOrderId(UUID mergeTransportOrderId) {
        this.mergeTransportOrderId = mergeTransportOrderId;
    }

    @Basic
    @javax.persistence.Column(name = "MERGE_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getMergeStatus() {
        return mergeStatus;
    }

    public void setMergeStatus(Integer mergeStatus) {
        this.mergeStatus = mergeStatus;
    }

    @Basic
    @javax.persistence.Column(name = "VICE_CLIENT_ORDER_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getViceClientOrderNumber() {
        return viceClientOrderNumber;
    }

    public void setViceClientOrderNumber(String viceClientOrderNumber) {
        this.viceClientOrderNumber = viceClientOrderNumber;
    }

    @Basic
    @javax.persistence.Column(name = "MARKET_CLIENT_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getMarketClientNumber() {
        return marketClientNumber;
    }

    public void setMarketClientNumber(String marketClientNumber) {
        this.marketClientNumber = marketClientNumber;
    }

    @Basic
    @javax.persistence.Column(name = "OBLIGATE_CLIENT_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getObligateClientNumber() {
        return obligateClientNumber;
    }

    public void setObligateClientNumber(String obligateClientNumber) {
        this.obligateClientNumber = obligateClientNumber;
    }

    @Basic
    @javax.persistence.Column(name = "MERGE_SEND_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getMergeSendStatus() {
        return mergeSendStatus;
    }

    public void setMergeSendStatus(Integer mergeSendStatus) {
        this.mergeSendStatus = mergeSendStatus;
    }

    @Basic
    @javax.persistence.Column(name = "PICKUP_ORDER_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getPickupOrderNumber() {
        return pickupOrderNumber;
    }

    public void setPickupOrderNumber(String pickupOrderNumber) {
        this.pickupOrderNumber = pickupOrderNumber;
    }

    @Basic
    @javax.persistence.Column(name = "BRANCH_NAME", nullable = true, insertable = true, updatable = true)
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Basic
    @javax.persistence.Column(name = "CLIENT_NAME", nullable = true, insertable = true, updatable = true)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Basic
    @javax.persistence.Column(name = "MODIFY_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getTransportTypeName() {
        return transportTypeName;
    }

    public void setTransportTypeName(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getCalculateTypeName() {
        return calculateTypeName;
    }

    public void setCalculateTypeName(String calculateTypeName) {
        this.calculateTypeName = calculateTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "HANDOVER_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getHandoverTypeName() {
        return handoverTypeName;
    }

    public void setHandoverTypeName(String handoverTypeName) {
        this.handoverTypeName = handoverTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "ORDER_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "BILLING_CYCLE_NAME", nullable = true, insertable = true, updatable = true)
    public String getBillingCycleName() {
        return billingCycleName;
    }

    public void setBillingCycleName(String billingCycleName) {
        this.billingCycleName = billingCycleName;
    }

    @Basic
    @javax.persistence.Column(name = "STATUS_NAME", nullable = true, insertable = true, updatable = true)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIPT_STATUS_NAME", nullable = true, insertable = true, updatable = true)
    public String getReceiptStatusName() {
        return receiptStatusName;
    }

    public void setReceiptStatusName(String receiptStatusName) {
        this.receiptStatusName = receiptStatusName;
    }

    @Basic
    @javax.persistence.Column(name = "CARRIER_ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCarrierOrderId() {
        return carrierOrderId;
    }

    public void setCarrierOrderId(UUID carrierOrderId) {
        this.carrierOrderId = carrierOrderId;
    }

    @Basic
    @javax.persistence.Column(name = "CARRIER_ORDER_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getCarrierOrderNumber() {
        return carrierOrderNumber;
    }

    public void setCarrierOrderNumber(String carrierOrderNumber) {
        this.carrierOrderNumber = carrierOrderNumber;
    }

    @Basic
    @javax.persistence.Column(name = "TRANSFER_ORGANIZATION_ID", nullable = true, insertable = true, updatable = true)
    public UUID getTransferOrganizationId() {
        return transferOrganizationId;
    }

    public void setTransferOrganizationId(UUID transferOrganizationId) {
        this.transferOrganizationId = transferOrganizationId;
    }

    @Basic
    @javax.persistence.Column(name = "CARRIER_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCarrierStatus() {
        return carrierStatus;
    }

    public void setCarrierStatus(Integer carrierStatus) {
        this.carrierStatus = carrierStatus;
    }

    @Basic
    @javax.persistence.Column(name = "CARRIER_STATUS_NAME", nullable = true, insertable = true, updatable = true)
    public String getCarrierStatusName() {
        return carrierStatusName;
    }

    public void setCarrierStatusName(String carrierStatusName) {
        this.carrierStatusName = carrierStatusName;
    }

    @Basic
    @javax.persistence.Column(name = "TRANSFER_ORGANIZATION_NAME", nullable = true, insertable = true, updatable = true)
    public String getTransferOrganizationName() {
        return transferOrganizationName;
    }

    public void setTransferOrganizationName(String transferOrganizationName) {
        this.transferOrganizationName = transferOrganizationName;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIPT_DATE", nullable = true, insertable = true, updatable = true)
    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Basic
    @javax.persistence.Column(name = "IS_REPAIR", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Integer isRepair) {
        this.isRepair = isRepair;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIPT_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(Integer receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    @Basic
    @javax.persistence.Column(name = "CARRIER_NAME", nullable = true, insertable = true, updatable = true)
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIPT_REMARK", nullable = true, insertable = true, updatable = true)
    public String getReceiptRemark() {
        return receiptRemark;
    }

    public void setReceiptRemark(String receiptRemark) {
        this.receiptRemark = receiptRemark;
    }
}
