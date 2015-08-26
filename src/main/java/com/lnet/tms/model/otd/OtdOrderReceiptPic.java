package com.lnet.tms.model.otd;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/8/25.
 */
@Entity
@Table(name = "OTD_ORDER_RECEIPT_PIC", schema = "LNET_TMS", catalog = "")
public class OtdOrderReceiptPic {
    private UUID orderReceiptPicId;
    private UUID transportOrderId;
    private String picName;
    private String picPath;
    private UUID createUserId;
    private Timestamp createDate;

    @Id
    @Column(name = "ORDER_RECEIPT_PIC_ID")
    public UUID getOrderReceiptPicId() {
        return orderReceiptPicId;
    }

    public void setOrderReceiptPicId(UUID orderReceiptPicId) {
        this.orderReceiptPicId = orderReceiptPicId;
    }

    @Basic
    @Column(name = "TRANSPORT_ORDER_ID")
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    @Basic
    @Column(name = "PIC_NAME")
    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    @Basic
    @Column(name = "PIC_PATH")
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Basic
    @Column(name = "CREATE_USER_ID")
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
