package com.lnet.tms.model.fee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "FEE_ORDER_RECEIVABLE_DETAIL", schema = "LNET_TMS", catalog = "")
public class FeeOrderReceivableDetail {
    private UUID orderReceivableDetailId;
    private UUID orderReceivableId;
    private UUID exacctId;
    private Double amount;
    private UUID clientQuoteId;
    private String remark;
    private FeeOrderReceivable feeOrderReceivable;

    @Id
    @Column(name = "ORDER_RECEIVABLE_DETAIL_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderReceivableDetailId() {
        return orderReceivableDetailId;
    }

    public void setOrderReceivableDetailId(UUID orderReceivableDetailId) {
        this.orderReceivableDetailId = orderReceivableDetailId;
    }

    @Basic
    @Column(name = "ORDER_RECEIVABLE_ID",  insertable = false, updatable = false)
    public UUID getOrderReceivableId() {
        return orderReceivableId;
    }

    public void setOrderReceivableId(UUID orderReceivableId) {
        this.orderReceivableId = orderReceivableId;
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
    @Column(name = "CLIENT_QUOTE_ID", nullable = true, insertable = true, updatable = true)
    public UUID getClientQuoteId() {
        return clientQuoteId;
    }

    public void setClientQuoteId(UUID clientQuoteId) {
        this.clientQuoteId = clientQuoteId;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ORDER_RECEIVABLE_ID", nullable = false)
    @JsonIgnore
    public FeeOrderReceivable getFeeOrderReceivable() {
        return feeOrderReceivable;
    }
    @JsonProperty
    public void setFeeOrderReceivable(FeeOrderReceivable feeOrderReceivable) {
        this.feeOrderReceivable = feeOrderReceivable;
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
