package com.lnet.tms.model.otd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

@Entity
@javax.persistence.Table(name = "OTD_TRANSPORT_ORDER_DETAIL", schema = "LNET_TMS", catalog = "")
public class OtdTransportOrderDetail {

    private UUID transportOrderDetailId;
    private UUID transportOrderId;
    private String goodsCode;
    private String goodsName;
    private String goodsType;
    private Long totalItemQuantity;
    private Long totalPackageQuantity;
    private Double totalWeight;
    private Double totalVolume;

    private String remark;
    private UUID createUserId;
    private Timestamp createDate;
    private UUID modifyUserId;
    private Timestamp modifyDate;

    private OtdTransportOrder otdTransportOrder;

    @Id
    @javax.persistence.Column(name = "TRANSPORT_ORDER_DETAIL_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getTransportOrderDetailId() {
        return transportOrderDetailId;
    }

    public void setTransportOrderDetailId(UUID transportOrderDetailId) {
        this.transportOrderDetailId = transportOrderDetailId;
    }

    @Basic
    @javax.persistence.Column(name = "transport_order_id", insertable = false, updatable = false)
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }


    @Basic
    @javax.persistence.Column(name = "GOODS_CODE", nullable = true, insertable = true, updatable = true)
    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    @Basic
    @javax.persistence.Column(name = "GOODS_NAME", nullable = true, insertable = true, updatable = true)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @javax.persistence.Column(name = "GOODS_TYPE", nullable = true, insertable = true, updatable = true)
    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTotalItemQuantity() {
        return totalItemQuantity;
    }

    public void setTotalItemQuantity(Long totalItemQuantity) {
        this.totalItemQuantity = totalItemQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    public void setTotalPackageQuantity(Long totalPackageQuantity) {
        this.totalPackageQuantity = totalPackageQuantity;
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
    @javax.persistence.Column(name = "TOTAL_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }



    @Basic
    @javax.persistence.Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
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
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "transport_order_id", nullable = false)
    @JsonIgnore
    public OtdTransportOrder getOtdTransportOrder() {
        return otdTransportOrder;
    }
    @JsonProperty
    public void setOtdTransportOrder(OtdTransportOrder otdTransportOrder) {
        this.otdTransportOrder = otdTransportOrder;
    }
}
