package com.lnet.tms.model.fee;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "FEE_ORDER_PAYABLE_PROPORTION_H", schema = "LNET_TMS", catalog = "")
public class FeeOrderPayableProportionH {
    private UUID orderPayableProportionHId;
    private UUID orderPayableId;
    private UUID orderId;
    private Integer orderType;
    private Double amount;
    private Double scale;
    private Integer proportionType;
    private String remark;
    private Integer version;

    @Id
    @Column(name = "ORDER_PAYABLE_PROPORTION_H_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderPayableProportionHId() {
        return orderPayableProportionHId;
    }

    public void setOrderPayableProportionHId(UUID orderPayableProportionHId) {
        this.orderPayableProportionHId = orderPayableProportionHId;
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

    @Basic
    @Column(name = "VERSION", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
