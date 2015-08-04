package com.lnet.tms.model.fee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Entity
@Table(name = "FEE_ORDER_PAYABLE_DETAIL", schema = "LNET_TMS", catalog = "")
public class FeeOrderPayableDetail {
    private UUID orderPayableDetailId;
    private UUID exacctId;
    private Double amount;
    private UUID carrierQuoteId;
    private UUID orderPayableId;

    public FeeOrderPayableDetail() {
    }

    public FeeOrderPayableDetail(UUID exacctId, Double amount, UUID orderPayableId, FeeOrderPayable feeOrderPayable) {
        this.exacctId = exacctId;
        this.amount = amount;
        this.orderPayableId = orderPayableId;
        this.feeOrderPayable = feeOrderPayable;
    }

    @Basic
    @Column(name = "ORDER_PAYABLE_ID", nullable = false, insertable = false, updatable = false)
    public UUID getOrderPayableId() {
        return orderPayableId;
    }

    private String remark;
    @Basic
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setOrderPayableId(UUID orderPayableId) {
        this.orderPayableId = orderPayableId;
    }

    private FeeOrderPayable feeOrderPayable;
    @Id
    @Column(name = "ORDER_PAYABLE_DETAIL_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderPayableDetailId() {
        return orderPayableDetailId;
    }

    public void setOrderPayableDetailId(UUID orderPayableDetailId) {
        this.orderPayableDetailId = orderPayableDetailId;
    }

    @Basic
    @Column(name = "EXACCT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getExacctId() {
        return exacctId;
    }

    public void setExacctId(UUID exacctId) {
        this.exacctId = exacctId;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = false, insertable = true, updatable = true, precision = 2)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "CARRIER_QUOTE_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCarrierQuoteId() {
        return carrierQuoteId;
    }

    public void setCarrierQuoteId(UUID carrierQuoteId) {
        this.carrierQuoteId = carrierQuoteId;
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
}
