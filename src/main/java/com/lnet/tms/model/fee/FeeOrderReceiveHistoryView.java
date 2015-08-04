package com.lnet.tms.model.fee;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "FEE_ORDER_RECEIVE_HISTORY_VIEW", schema = "LNET_TMS", catalog = "")
public class FeeOrderReceiveHistoryView {
    private UUID id;
    private UUID orderReceivableId;
    private Integer version;
    private Double receivableFreight;
    private Double receivablePickupfee;
    private Double receivableDelivery;
    private Double receivableOther;
    private Double receivablePacking;
    private Double receivablePremium;
    private Double receivableUpstairs;
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
    @Column(name = "ORDER_RECEIVABLE_ID", nullable = true, insertable = true, updatable = true)
    public UUID getOrderReceivableId() {
        return orderReceivableId;
    }

    public void setOrderReceivableId(UUID orderReceivableId) {
        this.orderReceivableId = orderReceivableId;
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
    @Column(name = "RECEIVABLE_FREIGHT", nullable = true, insertable = true, updatable = true, precision = -127)
    public Double getReceivableFreight() {
        return receivableFreight;
    }

    public void setReceivableFreight(Double receivableFreight) {
        this.receivableFreight = receivableFreight;
    }

    @Basic
    @Column(name = "RECEIVABLE_PICKUPFEE", nullable = true, insertable = true, updatable = true, precision = -127)
    public Double getReceivablePickupfee() {
        return receivablePickupfee;
    }

    public void setReceivablePickupfee(Double receivablePickupfee) {
        this.receivablePickupfee = receivablePickupfee;
    }

    @Basic
    @Column(name = "RECEIVABLE_DELIVERY", nullable = true, insertable = true, updatable = true, precision = -127)
    public Double getReceivableDelivery() {
        return receivableDelivery;
    }

    public void setReceivableDelivery(Double receivableDelivery) {
        this.receivableDelivery = receivableDelivery;
    }

    @Basic
    @Column(name = "RECEIVABLE_OTHER", nullable = true, insertable = true, updatable = true, precision = -127)
    public Double getReceivableOther() {
        return receivableOther;
    }

    public void setReceivableOther(Double receivableOther) {
        this.receivableOther = receivableOther;
    }

    @Basic
    @Column(name = "RECEIVABLE_PACKING", nullable = true, insertable = true, updatable = true, precision = -127)
    public Double getReceivablePacking() {
        return receivablePacking;
    }

    public void setReceivablePacking(Double receivablePacking) {
        this.receivablePacking = receivablePacking;
    }

    @Basic
    @Column(name = "RECEIVABLE_PREMIUM", nullable = true, insertable = true, updatable = true, precision = -127)
    public Double getReceivablePremium() {
        return receivablePremium;
    }

    public void setReceivablePremium(Double receivablePremium) {
        this.receivablePremium = receivablePremium;
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
    @Column(name = "RECEIVABLE_UPSTAIRS", nullable = true, insertable = true, updatable = true)
    public Double getReceivableUpstairs() {
        return receivableUpstairs;
    }

    public void setReceivableUpstairs(Double receivableUpstairs) {
        this.receivableUpstairs = receivableUpstairs;
    }
}
