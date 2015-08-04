package com.lnet.tms.model.fee;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "FEE_ORDER_PAY_HISTORY_VIEW", schema = "LNET_TMS", catalog = "")
public class FeeOrderPayHistoryView {
    private UUID id;
    private UUID orderPayableId;
    private Integer version;
    private Double payableFreight;
    private Double payablePickupfee;
    private Double payableDelivery;
    private Double payableOther;
    private Double payableCartfee;
    private Double payableUpstairs;
    private Timestamp operationDate;
    private UUID operationUserId;
    private String operationUserName;

    @Id
    @Column(name = "ID", nullable = true, insertable = true, updatable = true)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORDER_PAYABLE_ID", nullable = true, insertable = true, updatable = true)
    public UUID getOrderPayableId() {
        return orderPayableId;
    }

    public void setOrderPayableId(UUID orderPayableId) {
        this.orderPayableId = orderPayableId;
    }

    @Basic
    @Column(name = "VERSION", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "PAYABLE_FREIGHT", nullable = true, insertable = true, updatable = true)
    public Double getPayableFreight() {
        return payableFreight;
    }

    public void setPayableFreight(Double payableFreight) {
        this.payableFreight = payableFreight;
    }

    @Basic
    @Column(name = "PAYABLE_PICKUPFEE", nullable = true, insertable = true, updatable = true)
    public Double getPayablePickupfee() {
        return payablePickupfee;
    }

    public void setPayablePickupfee(Double payablePickupfee) {
        this.payablePickupfee = payablePickupfee;
    }

    @Basic
    @Column(name = "PAYABLE_DELIVERY", nullable = true, insertable = true, updatable = true)
    public Double getPayableDelivery() {
        return payableDelivery;
    }

    public void setPayableDelivery(Double payableDelivery) {
        this.payableDelivery = payableDelivery;
    }

    @Basic
    @Column(name = "PAYABLE_OTHER", nullable = true, insertable = true, updatable = true)
    public Double getPayableOther() {
        return payableOther;
    }

    public void setPayableOther(Double payableOther) {
        this.payableOther = payableOther;
    }

    @Basic
    @Column(name = "PAYABLE_CARTFEE", nullable = true, insertable = true, updatable = true)
    public Double getPayableCartfee() {
        return payableCartfee;
    }

    public void setPayableCartfee(Double payableCartfee) {
        this.payableCartfee = payableCartfee;
    }

    @Basic
    @Column(name = "OPERATION_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    @Basic
    @Column(name = "OPERATION_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(UUID operationUserId) {
        this.operationUserId = operationUserId;
    }

    @Basic
    @Column(name = "OPERATION_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    @Basic
    @Column(name = "PAYABLE_UPSTAIRS", nullable = true, insertable = true, updatable = true)
    public Double getPayableUpstairs() {
        return payableUpstairs;
    }

    public void setPayableUpstairs(Double payableUpstairs) {
        this.payableUpstairs = payableUpstairs;
    }
}
