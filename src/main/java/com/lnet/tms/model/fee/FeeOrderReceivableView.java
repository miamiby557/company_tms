package com.lnet.tms.model.fee;

import com.lnet.tms.model.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by admin on 2015/5/26.
 */
@Entity
@javax.persistence.Table(name = "FEE_ORDER_RECEIVABLE_VIEW", schema = "LNET_TMS", catalog = "")
public class FeeOrderReceivableView extends BaseEntity{
    private UUID orderReceivableId;

    @Id
    @javax.persistence.Column(name = "ORDER_RECEIVABLE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getOrderReceivableId() {
        return orderReceivableId;
    }

    public void setOrderReceivableId(UUID orderReceivableId) {
        this.orderReceivableId = orderReceivableId;
    }

    private UUID clientId;

    @Basic
    @javax.persistence.Column(name = "CLIENT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    private UUID sourceOrderId;

    @Basic
    @javax.persistence.Column(name = "SOURCE_ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getSourceOrderId() {
        return sourceOrderId;
    }

    public void setSourceOrderId(UUID sourceOrderId) {
        this.sourceOrderId = sourceOrderId;
    }

    private Integer sourceOrderType;
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
    @javax.persistence.Column(name = "SOURCE_ORDER_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSourceOrderType() {
        return sourceOrderType;
    }

    public void setSourceOrderType(Integer sourceOrderType) {
        this.sourceOrderType = sourceOrderType;
    }

    private Double totalAmount;

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

    private Integer status;

    @Basic
    @javax.persistence.Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    private String clientOrderNumber;

    @Basic
    @javax.persistence.Column(name = "CLIENT_ORDER_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getClientOrderNumber() {
        return clientOrderNumber;
    }

    public void setClientOrderNumber(String clientOrderNumber) {
        this.clientOrderNumber = clientOrderNumber;
    }

    private String lnetOrderNumber;

    @Basic
    @javax.persistence.Column(name = "LNET_ORDER_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getLnetOrderNumber() {
        return lnetOrderNumber;
    }

    public void setLnetOrderNumber(String lnetOrderNumber) {
        this.lnetOrderNumber = lnetOrderNumber;
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

    private String startCity;

    @Basic
    @javax.persistence.Column(name = "START_CITY", nullable = true, insertable = true, updatable = true)
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
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

    private Double payAmount;

    @Basic
    @javax.persistence.Column(name = "PAY_AMOUNT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
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

    private String receiveMan;
    @Basic
    @javax.persistence.Column(name = "RECEIVE_MAN", nullable = true, insertable = true, updatable = true)
    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
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


    public Integer transportType;
    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE", nullable = true, insertable = true, updatable = true)
    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    private Boolean feeSaveMark;

    @Basic
    @Column(name = "FEE_SAVE_MARK", nullable = true, insertable = true, updatable = true)
    public Boolean getFeeSaveMark() {
        return feeSaveMark;
    }

    public void setFeeSaveMark(Boolean feeSaveMark) {
        this.feeSaveMark = feeSaveMark;
    }
}
