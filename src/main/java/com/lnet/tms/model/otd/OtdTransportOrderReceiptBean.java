package com.lnet.tms.model.otd;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class OtdTransportOrderReceiptBean {
    private UUID orderReceiptId;
    private UUID transportOrderId;
    private Set<UUID> transportOrderIds;
    private Date receiptDate;
    private Integer status;
    private String remark;
    private UUID createUserId;
    private Timestamp createDate;
    private Integer isRepair;

    public Integer getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Integer isRepair) {
        this.isRepair = isRepair;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Set<UUID> getTransportOrderIds() {
        return transportOrderIds;
    }

    public void setTransportOrderIds(Set<UUID> transportOrderIds) {
        this.transportOrderIds = transportOrderIds;
    }

    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    public UUID getOrderReceiptId() {
        return orderReceiptId;
    }

    public void setOrderReceiptId(UUID orderReceiptId) {
        this.orderReceiptId = orderReceiptId;
    }
}
