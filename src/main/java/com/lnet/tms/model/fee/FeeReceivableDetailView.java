package com.lnet.tms.model.fee;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "FEE_RECEIVABLE_DETAIL_VIEW", schema = "LNET_TMS", catalog = "")
public class FeeReceivableDetailView {
    private UUID orderReceivableDetailId;
    private UUID orderReceivableId;
    private UUID exacctId;
    private Double amount;
    private UUID clientQuoteId;
    private String name;
    private String remark;
    private String indexNumber;

    @Id
    @Column(name = "ORDER_RECEIVABLE_DETAIL_ID", nullable = false, insertable = true, updatable = true)
    public UUID getOrderReceivableDetailId() {
        return orderReceivableDetailId;
    }

    public void setOrderReceivableDetailId(UUID orderReceivableDetailId) {
        this.orderReceivableDetailId = orderReceivableDetailId;
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

    @Basic
    @Column(name = "NAME", nullable = true, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "INDEX_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }
}
