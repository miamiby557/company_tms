package com.lnet.tms.model.otd;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "OTD_TRANSPORT_ORDER_RECEIPT", schema = "LNET_TMS", catalog = "")
public class OtdTransportOrderReceipt {
    private UUID orderReceiptId;
    private UUID transportOrderId;
    private Date receiptDate;
    private Integer status;
    private String remark;
    private UUID createUserId;
    private Timestamp createDate;
    private Integer isRepair;
    @Id
    @Column(name = "ORDER_RECEIPT_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderReceiptId() {
        return orderReceiptId;
    }

    public void setOrderReceiptId(UUID orderReceiptId) {
        this.orderReceiptId = orderReceiptId;
    }
    @Basic
    @Column(name = "is_Repair", nullable = false, insertable = true, updatable = true)
    public Integer getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Integer isRepair) {
        this.isRepair = isRepair;
    }

    @Basic
    @Column(name = "TRANSPORT_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    @Basic
    @Column(name = "RECEIPT_DATE", nullable = true, insertable = true, updatable = true)
    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
