package com.lnet.tms.model.fee;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/22.
 */
@Entity
@Table(name = "FEE_ORDER_PAYABLE_DETAIL_VIEW", schema = "LNET_TMS", catalog = "")
public class FeeOrderPayableDetailView {
    private UUID orderPayableDetailId;
    private UUID orderPayableId;
    private UUID exacctId;
    private BigDecimal amount;
    private UUID carrierQuoteId;
    private String exacctName;
    private Long calculateType;
    private BigDecimal minimumFee;
    private BigDecimal minimumCondiction;
    private BigDecimal maxmumCondiction;
    private BigDecimal unitPrice;
    private String remark;
    private String indexNumber;

    @Id
    @Column(name = "ORDER_PAYABLE_DETAIL_ID", nullable = false, insertable = true, updatable = true)
    public UUID getOrderPayableDetailId() {
        return orderPayableDetailId;
    }

    public void setOrderPayableDetailId(UUID orderPayableDetailId) {
        this.orderPayableDetailId = orderPayableDetailId;
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
    @Column(name = "EXACCT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getExacctId() {
        return exacctId;
    }

    public void setExacctId(UUID exacctId) {
        this.exacctId = exacctId;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = false, insertable = true, updatable = true, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    @Basic
    @Column(name = "EXACCT_NAME", nullable = true, insertable = true, updatable = true)
    public String getExacctName() {
        return exacctName;
    }

    public void setExacctName(String exacctName) {
        this.exacctName = exacctName;
    }

    @Basic
    @Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Long calculateType) {
        this.calculateType = calculateType;
    }

    @Basic
    @Column(name = "MINIMUM_FEE", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getMinimumFee() {
        return minimumFee;
    }

    public void setMinimumFee(BigDecimal minimumFee) {
        this.minimumFee = minimumFee;
    }

    @Basic
    @Column(name = "MINIMUM_CONDICTION", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getMinimumCondiction() {
        return minimumCondiction;
    }

    public void setMinimumCondiction(BigDecimal minimumCondiction) {
        this.minimumCondiction = minimumCondiction;
    }

    @Basic
    @Column(name = "MAXMUM_CONDICTION", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getMaxmumCondiction() {
        return maxmumCondiction;
    }

    public void setMaxmumCondiction(BigDecimal maxmumCondiction) {
        this.maxmumCondiction = maxmumCondiction;
    }

    @Basic
    @Column(name = "UNIT_PRICE", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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
