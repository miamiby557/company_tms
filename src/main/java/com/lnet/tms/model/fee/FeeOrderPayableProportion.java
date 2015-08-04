package com.lnet.tms.model.fee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "FEE_ORDER_PAYABLE_PROPORTION", schema = "LNET_TMS", catalog = "")
public class FeeOrderPayableProportion {
    private UUID orderPayableProportionId;
    private UUID orderPayableId;
    private UUID orderId;
    private Integer orderType;
    private Double amount;
    private Double scale;
    private Integer proportionType;
    private String remark;
    private FeeOrderPayable feeOrderPayable;

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
    @Column(name = "ORDER_PAYABLE_ID", nullable = true, insertable = false, updatable = false)
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ORDER_PAYABLE_ID", nullable = false)
    @JsonIgnore
    public FeeOrderPayable getFeeOrderPayable() {
        return feeOrderPayable;
    }
    @JsonProperty
    public void setFeeOrderPayable(FeeOrderPayable feeOrderPayable) {
        this.feeOrderPayable = feeOrderPayable;
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
