package com.lnet.tms.model.otd;

import com.lnet.tms.model.BaseEntity;
import com.lnet.tms.utility.StringUtils;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Entity
@javax.persistence.Table(name = "OTD_ORDER_LOG_LIST_VIEW", schema = "LNET_TMS", catalog = "")
public class OtdOrderLogListView  extends BaseEntity {
    private UUID transportOrderId;
    private UUID carrierOrderId;
    private Integer carrierStatus;
    private String carrierStatusName;
    private Boolean isSystem;
    private Integer receiptStatus;
    private String receiptStatusName;
    private Date receiptDate;
    private UUID orderReceiptId;
    private Boolean isRepair;
    @Basic
    @Column(name = "receipt_Status", nullable = true, insertable = true, updatable = true)
    public Integer getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(Integer receiptStatus) {
        this.receiptStatus = receiptStatus;
    }
    @Basic
    @Column(name = "receipt_Status_Name", nullable = true, insertable = true, updatable = true)
    public String getReceiptStatusName() {
        return receiptStatusName;
    }

    public void setReceiptStatusName(String receiptStatusName) {
        this.receiptStatusName = receiptStatusName;
    }
    @Basic
    @Column(name = "receipt_Date", nullable = true, insertable = true, updatable = true)
    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }
    @Basic
    @Column(name = "order_Receipt_Id", nullable = true, insertable = true, updatable = true)
    public UUID getOrderReceiptId() {
        return orderReceiptId;
    }

    public void setOrderReceiptId(UUID orderReceiptId) {
        this.orderReceiptId = orderReceiptId;
    }
    @Basic
    @Column(name = "is_Repair", nullable = true, insertable = true, updatable = true)
    public Boolean getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Boolean isRepair) {
        this.isRepair = isRepair;
    }

    @Basic
    @Column(name = "is_System", nullable = true, insertable = true, updatable = true)
    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }
    @Basic
    @javax.persistence.Column(name = "carrier_Status", nullable = true, insertable = true, updatable = true)
    public Integer getCarrierStatus() {
        return carrierStatus;
    }

    public void setCarrierStatus(Integer carrierStatus) {
        this.carrierStatus = carrierStatus;
    }
    @Basic
    @javax.persistence.Column(name = "carrier_Status_Name", nullable = true, insertable = true, updatable = true)
    public String getCarrierStatusName() {
        return carrierStatusName;
    }

    public void setCarrierStatusName(String carrierStatusName) {
        this.carrierStatusName = carrierStatusName;
    }

    @Id
    @javax.persistence.Column(name = "TRANSPORT_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    private UUID pickupOrderId;

    private Date expectedDate;


    @Basic
    @javax.persistence.Column(name = "EXPECTED_DATE", nullable = true, insertable = true, updatable = true)
    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
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
    @javax.persistence.Column(name = "CARRIER_ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCarrierOrderId() {
        return carrierOrderId;
    }

    public void setCarrierOrderId(UUID carrierOrderId) {
        this.carrierOrderId = carrierOrderId;
    }

    private String clientOrderNumber;

    @Basic
    @javax.persistence.Column(name = "CLIENT_ORDER_NUMBER", nullable = false, insertable = true, updatable = true)
    public String getClientOrderNumber() {
        return clientOrderNumber;
    }

    public void setClientOrderNumber(String clientOrderNumber) {
        this.clientOrderNumber = clientOrderNumber;
    }

    private String lnetOrderNumber;

    @Basic
    @javax.persistence.Column(name = "LNET_ORDER_NUMBER", nullable = false, insertable = true, updatable = true)
    public String getLnetOrderNumber() {
        return lnetOrderNumber;
    }

    public void setLnetOrderNumber(String lnetOrderNumber) {
        this.lnetOrderNumber = lnetOrderNumber;
    }
/*
    private UUID branchId;

    @Basic
    @javax.persistence.Column(name = "BRANCH_ID", nullable = true, insertable = true, updatable = true)
    public UUID getBranchId() {
        return branchId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }*/

    private UUID clientId;

    @Basic
    @javax.persistence.Column(name = "CLIENT_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    private Timestamp orderDate;

    @Basic
    @javax.persistence.Column(name = "ORDER_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    private Integer orderType;

    @Basic
    @javax.persistence.Column(name = "ORDER_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /*private String consignerMan;

    @Basic
    @javax.persistence.Column(name = "CONSIGNER_MAN", nullable = true, insertable = true, updatable = true)
    public String getConsignerMan() {
        return consignerMan;
    }

    public void setConsignerMan(String consignerMan) {
        this.consignerMan = consignerMan;
    }

    private String consignerPhone;

    @Basic
    @javax.persistence.Column(name = "CONSIGNER_PHONE", nullable = true, insertable = true, updatable = true)
    public String getConsignerPhone() {
        return consignerPhone;
    }

    public void setConsignerPhone(String consignerPhone) {
        this.consignerPhone = consignerPhone;
    }

    private String consignerAddress;

    @Basic
    @javax.persistence.Column(name = "CONSIGNER_ADDRESS", nullable = true, insertable = true, updatable = true)
    public String getConsignerAddress() {
        return consignerAddress;
    }

    public void setConsignerAddress(String consignerAddress) {
        this.consignerAddress = consignerAddress;
    }*/

    private UUID startCityId;

    @Basic
    @javax.persistence.Column(name = "START_CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getStartCityId() {
        return startCityId;
    }

    public void setStartCityId(UUID startCityId) {
        this.startCityId = startCityId;
    }

    private String startCity;

    @Basic
    @javax.persistence.Column(name = "START_CITY", nullable = true, insertable = true, updatable = true)
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    private UUID destCityId;

    @Basic
    @javax.persistence.Column(name = "DEST_CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getDestCityId() {
        return destCityId;
    }

    public void setDestCityId(UUID destCityId) {
        this.destCityId = destCityId;
    }

    private String destCity;

    @Basic
    @javax.persistence.Column(name = "DEST_CITY", nullable = true, insertable = true, updatable = true)
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    private String receiveCompany;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_COMPANY", nullable = true, insertable = true, updatable = true)
    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    private String receiveMan;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_MAN", nullable = true, insertable = true, updatable = true)
    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    private String receivePhone;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_PHONE", nullable = true, insertable = true, updatable = true)
    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    private String receiveAddress;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_ADDRESS", nullable = true, insertable = true, updatable = true)
    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    private Integer handoverType;

    @Basic
    @javax.persistence.Column(name = "HANDOVER_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getHandoverType() {
        return handoverType;
    }

    public void setHandoverType(Integer handoverType) {
        this.handoverType = handoverType;
    }

    private Integer totalItemQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalItemQuantity() {
        return totalItemQuantity;
    }

    public void setTotalItemQuantity(Integer totalItemQuantity) {
        this.totalItemQuantity = totalItemQuantity;
    }

    private Integer totalPackageQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    public void setTotalPackageQuantity(Integer totalPackageQuantity) {
        this.totalPackageQuantity = totalPackageQuantity;
    }

    private BigDecimal totalVolume;

    @Basic
    @javax.persistence.Column(name = "TOTAL_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    private BigDecimal totalWeight;

    @Basic
    @javax.persistence.Column(name = "TOTAL_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    private Integer confirmedItemQuantity;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedItemQuantity() {
        return confirmedItemQuantity;
    }

    public void setConfirmedItemQuantity(Integer confirmedItemQuantity) {
        this.confirmedItemQuantity = confirmedItemQuantity;
    }

    private Integer confirmedPackageQuantity;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedPackageQuantity() {
        return confirmedPackageQuantity;
    }

    public void setConfirmedPackageQuantity(Integer confirmedPackageQuantity) {
        this.confirmedPackageQuantity = confirmedPackageQuantity;
    }

    private BigDecimal confirmedVolume;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getConfirmedVolume() {
        return confirmedVolume;
    }

    public void setConfirmedVolume(BigDecimal confirmedVolume) {
        this.confirmedVolume = confirmedVolume;
    }

    private BigDecimal confirmedWeight;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getConfirmedWeight() {
        return confirmedWeight;
    }

    public void setConfirmedWeight(BigDecimal confirmedWeight) {
        this.confirmedWeight = confirmedWeight;
    }

    private String specialRequest;

    @Basic
    @javax.persistence.Column(name = "SPECIAL_REQUEST", nullable = true, insertable = true, updatable = true)
    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    private Integer billingCycle;

    @Basic
    @javax.persistence.Column(name = "BILLING_CYCLE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(Integer billingCycle) {
        this.billingCycle = billingCycle;
    }

    private Integer paymentType;

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    private Integer calculateType;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }

    private Integer transportType;

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    private Timestamp sentDate;

    @Basic
    @javax.persistence.Column(name = "SENT_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    private String remark;

    @Basic
    @javax.persistence.Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /*private UUID createUserId;

    @Basic
    @javax.persistence.Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    private Timestamp createDate;

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    private UUID modifyUserId;

    @Basic
    @javax.persistence.Column(name = "MODIFY_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(UUID modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    private Timestamp modifyDate;

    @Basic
    @javax.persistence.Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }*/

    private Integer status;

    @Basic
    @javax.persistence.Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer receiptPageNumber;

    @Basic
    @javax.persistence.Column(name = "RECEIPT_PAGE_NUMBER", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReceiptPageNumber() {
        return receiptPageNumber;
    }

    public void setReceiptPageNumber(Integer receiptPageNumber) {
        this.receiptPageNumber = receiptPageNumber;
    }

    private Integer urgencyLevel;

    @Basic
    @javax.persistence.Column(name = "URGENCY_LEVEL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(Integer urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    private String pickupOrderNumber;

    @Basic
    @javax.persistence.Column(name = "PICKUP_ORDER_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getPickupOrderNumber() {
        return pickupOrderNumber;
    }

    public void setPickupOrderNumber(String pickupOrderNumber) {
        this.pickupOrderNumber = pickupOrderNumber;
    }

    private String branchName;

    @Basic
    @javax.persistence.Column(name = "BRANCH_NAME", nullable = true, insertable = true, updatable = true)
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    private String clientName;

    @Basic
    @javax.persistence.Column(name = "CLIENT_NAME", nullable = true, insertable = true, updatable = true)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String createUserName;

    @Basic
    @javax.persistence.Column(name = "CREATE_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    private String modifyUserName;

    @Basic
    @javax.persistence.Column(name = "MODIFY_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    private String transportTypeName;

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getTransportTypeName() {
        return transportTypeName;
    }

    public void setTransportTypeName(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    private String calculateTypeName;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getCalculateTypeName() {
        return calculateTypeName;
    }

    public void setCalculateTypeName(String calculateTypeName) {
        this.calculateTypeName = calculateTypeName;
    }

    private String handoverTypeName;

    @Basic
    @javax.persistence.Column(name = "HANDOVER_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getHandoverTypeName() {
        return handoverTypeName;
    }

    public void setHandoverTypeName(String handoverTypeName) {
        this.handoverTypeName = handoverTypeName;
    }

    private String orderTypeName;

    @Basic
    @javax.persistence.Column(name = "ORDER_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    private String paymentTypeName;

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    private String billingCycleName;

    @Basic
    @javax.persistence.Column(name = "BILLING_CYCLE_NAME", nullable = true, insertable = true, updatable = true)
    public String getBillingCycleName() {
        return billingCycleName;
    }

    public void setBillingCycleName(String billingCycleName) {
        this.billingCycleName = billingCycleName;
    }

    private String statusName;

    @Basic
    @javax.persistence.Column(name = "STATUS_NAME", nullable = true, insertable = true, updatable = true)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    private String carrierOrderNumber;

    @Basic
    @javax.persistence.Column(name = "CARRIER_ORDER_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getCarrierOrderNumber() {
        return carrierOrderNumber;
    }

    public void setCarrierOrderNumber(String carrierOrderNumber) {
        this.carrierOrderNumber = carrierOrderNumber;
    }

    private Timestamp operationDate;

    @Basic
    @javax.persistence.Column(name = "OPERATION_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    private String operationContent;

    @Basic
    @javax.persistence.Column(name = "OPERATION_CONTENT", nullable = true, insertable = true, updatable = true)
    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    private String carrierName;
    @Basic
    @javax.persistence.Column(name = "CARRIER_NAME", nullable = true, insertable = true, updatable = true)
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }


    private String viceClientOrderNumber;
    @Basic
    @javax.persistence.Column(name = "vice_client_order_number", nullable = true, insertable = true, updatable = true)
    public String getViceClientOrderNumber() {
        return viceClientOrderNumber;
    }

    public void setViceClientOrderNumber(String viceClientOrderNumber) {
        this.viceClientOrderNumber = viceClientOrderNumber;
    }

    private Boolean isAbnormal;
    @Basic
    @javax.persistence.Column(name = "IS_ABNORMAL", nullable = true, insertable = true, updatable = true)
    public Boolean getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Boolean isAbnormal) {
        this.isAbnormal = isAbnormal;
    }

    private Integer receivablePackageQuantity;

    private Integer payablePackageQuantity;
    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true)
    public Integer getReceivablePackageQuantity() {
        return receivablePackageQuantity;
    }

    public void setReceivablePackageQuantity(Integer receivablePackageQuantity) {
        this.receivablePackageQuantity = receivablePackageQuantity;
    }
    @Basic
    @javax.persistence.Column(name = "PAYABLE_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true)
    public Integer getPayablePackageQuantity() {
        return payablePackageQuantity;
    }

    public void setPayablePackageQuantity(Integer payablePackageQuantity) {
        this.payablePackageQuantity = payablePackageQuantity;
    }

    private String marketClientNumber;

    @Basic
    @javax.persistence.Column(name = "MARKET_CLIENT_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getMarketClientNumber() {
        return marketClientNumber;
    }

    public void setMarketClientNumber(String marketClientNumber) {
        this.marketClientNumber = marketClientNumber;
    }

    private String transferOrganizationName;
    @Basic
    @javax.persistence.Column(name = "transfer_Organization_name", nullable = true, insertable = true, updatable = true, precision = 0)
    public String getTransferOrganizationName() {
        return transferOrganizationName;
    }

    public void setTransferOrganizationName(String transferOrganizationName) {
        this.transferOrganizationName = transferOrganizationName;
    }
}
