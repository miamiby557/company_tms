package com.lnet.tms.model.fee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by admin on 2015/5/25.
 */
@Entity
@Table(name = "FEE_PAYABLE_PROPORTION_VIEW", schema = "LNET_TMS", catalog = "")
public class FeePayableProportionView {
    private UUID orderPayableProportionId;
    private UUID orderPayableId;
    private UUID orderId;
    private Integer orderType;
    private Double amount;
    private Double scale;
    private Integer proportionType;
    private String remark;

    private String clientOrderNumber;
    private String clientName;

    private Integer confirmedItemQuantity;
    private Integer confirmedPackageQuantity;
    private Double confirmedVolume;
    private Double confirmedWeight;
    @Id
    @Column(name = "ORDER_PAYABLE_PROPORTION_ID", nullable = true, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderPayableProportionId() {
        return orderPayableProportionId;
    }

    public void setOrderPayableProportionId(UUID orderPayableProportionId) {
        this.orderPayableProportionId = orderPayableProportionId;
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
    @javax.persistence.Column(name = "CLIENT_NAME", nullable = false, insertable = true, updatable = true)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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
    @Column(name = "ORDER_PAYABLE_ID", nullable = true, insertable = true, updatable = true)
    public UUID getOrderPayableId() {
        return orderPayableId;
    }

    public void setOrderPayableId(UUID orderPayableId) {
        this.orderPayableId = orderPayableId;
    }

    @Basic
    @Column(name = "ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "ORDER_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "SCALE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    @Basic
    @Column(name = "PROPORTION_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getProportionType() {
        return proportionType;
    }

    public void setProportionType(Integer proportionType) {
        this.proportionType = proportionType;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
