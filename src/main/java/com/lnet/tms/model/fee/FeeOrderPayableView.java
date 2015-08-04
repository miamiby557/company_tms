package com.lnet.tms.model.fee;

import com.lnet.tms.model.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2015/5/22.
 */
@Entity
@javax.persistence.Table(name = "FEE_ORDER_PAYABLE_VIEW", schema = "LNET_TMS", catalog = "")
public class FeeOrderPayableView extends BaseEntity{
    private UUID orderPayableId;

    private Integer status;
    private UUID startCityId;
    private String startCity;
    private UUID destCityId;
    private String destCity;
    private Integer wrapType;
    private Integer transportType;
    private String wrapTypeName;
    private String transportTypeName;
    private Integer mergeStatus;
    private Boolean feeSaveMark;
    private Integer backStatus;
    private String backStatusName;
    @Basic
    @Column(name = "back_Status", nullable = true, insertable = true, updatable = true)
    public Integer getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(Integer backStatus) {
        this.backStatus = backStatus;
    }
    @Basic
    @Column(name = "back_Status_name", nullable = true, insertable = true, updatable = true)
    public String getBackStatusName() {
        return backStatusName;
    }

    public void setBackStatusName(String backStatusName) {
        this.backStatusName = backStatusName;
    }
    @Basic
    @javax.persistence.Column(name = "MERGE_STATUS", nullable = true, insertable = true, updatable = true)
    public Integer getMergeStatus() {
        return mergeStatus;
    }

    public void setMergeStatus(Integer mergeStatus) {
        this.mergeStatus = mergeStatus;
    }

    @Basic
    @javax.persistence.Column(name = "wrap_Type", nullable = true, insertable = true, updatable = true)
    public Integer getWrapType() {
        return wrapType;
    }
    public void setWrapType(Integer wrapType) {
        this.wrapType = wrapType;
    }

    @Basic
    @javax.persistence.Column(name = "transport_Type_Name", nullable = true, insertable = true, updatable = true)
    public String getTransportTypeName() {
        return transportTypeName;
    }

    public void setTransportTypeName(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "wrap_Type_Name", nullable = true, insertable = true, updatable = true)
    public String getWrapTypeName() {
        return wrapTypeName;
    }

    public void setWrapTypeName(String wrapTypeName) {
        this.wrapTypeName = wrapTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "transport_Type", nullable = true, insertable = true, updatable = true)
    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    @Id
    @javax.persistence.Column(name = "ORDER_PAYABLE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getOrderPayableId() {
        return orderPayableId;
    }

    public void setOrderPayableId(UUID orderPayableId) {
        this.orderPayableId = orderPayableId;
    }

    private UUID carrierId;

    @Basic
    @javax.persistence.Column(name = "CARRIER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(UUID carrierId) {
        this.carrierId = carrierId;
    }

    private UUID sourceOrderId;
    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    private String statusName;

    private String calculateTypeName;
    private String paymentTypeName;
    private String billingCycleName;
    private String sourceOrderTypeName;
    @Basic
    @javax.persistence.Column(name = "payment_Type_Name", nullable = true, insertable = true, updatable = true)
    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }
    @Basic
    @javax.persistence.Column(name = "billing_Cycle_Name", nullable = true, insertable = true, updatable = true)
    public String getBillingCycleName() {
        return billingCycleName;
    }

    public void setBillingCycleName(String billingCycleName) {
        this.billingCycleName = billingCycleName;
    }
    @Basic
    @javax.persistence.Column(name = "source_Order_Type_Name", nullable = true, insertable = true, updatable = true)
    public String getSourceOrderTypeName() {
        return sourceOrderTypeName;
    }

    public void setSourceOrderTypeName(String sourceOrderTypeName) {
        this.sourceOrderTypeName = sourceOrderTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "status_Name", nullable = true, insertable = true, updatable = true)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Basic
    @javax.persistence.Column(name = "calculate_Type_Name", nullable = true, insertable = true, updatable = true)
    public String getCalculateTypeName() {
        return calculateTypeName;
    }

    public void setCalculateTypeName(String calculateTypeName) {
        this.calculateTypeName = calculateTypeName;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Basic
    @javax.persistence.Column(name = "SOURCE_ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getSourceOrderId() {
        return sourceOrderId;
    }

    public void setSourceOrderId(UUID sourceOrderId) {
        this.sourceOrderId = sourceOrderId;
    }

    private Integer sourceOrderType;

    @Basic
    @javax.persistence.Column(name = "SOURCE_ORDER_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSourceOrderType() {
        return sourceOrderType;
    }

    public void setSourceOrderType(Integer sourceOrderType) {
        this.sourceOrderType = sourceOrderType;
    }

    private Double totalAmount;

    private Double receivTotalAmount;


    @Basic
    @javax.persistence.Column(name = "receiv_TOTAL_AMOUNT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getReceivTotalAmount() {
        return receivTotalAmount;
    }

    public void setReceivTotalAmount(Double receivTotalAmount) {
        this.receivTotalAmount = receivTotalAmount;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_AMOUNT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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

    private String carrierName;

    @Basic
    @javax.persistence.Column(name = "CARRIER_NAME", nullable = true, insertable = true, updatable = true)
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
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

    private Integer totalPackageQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    public void setTotalPackageQuantity(Integer totalPackageQuantity) {
        this.totalPackageQuantity = totalPackageQuantity;
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

    private Double totalVolume;

    @Basic
    @javax.persistence.Column(name = "TOTAL_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    private Double totalWeight;

    @Basic
    @javax.persistence.Column(name = "TOTAL_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
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

    private String consignee;

    @Basic
    @javax.persistence.Column(name = "consignee", nullable = true, insertable = true, updatable = true)
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }


    private Date sendDate;
    @Basic
    @javax.persistence.Column(name = "send_date", nullable = true, insertable = true, updatable = true)
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    private Integer version;
    @Basic
    @Column(name = "VERSION", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "FEE_SAVE_MARK", nullable = true, insertable = true, updatable = true)
    public Boolean getFeeSaveMark() {
        return feeSaveMark;
    }

    public void setFeeSaveMark(Boolean feeSaveMark) {
        this.feeSaveMark = feeSaveMark;
    }

}
