package com.lnet.tms.model.fee;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "FEE_ORDER_PAYABLE_DETAIL_H", schema = "LNET_TMS", catalog = "")
public class FeeOrderPayableDetailH {
    private UUID orderPayableDetailHId;
    private UUID orderPayableId;
    private UUID exacctId;
    private Double amount;
    private UUID carrierQuoteId;
    private String remark;
    private Integer version;
    private Timestamp operationDate;
    private UUID operationUserId;

    @Id
    @Column(name = "ORDER_PAYABLE_DETAIL_H_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderPayableDetailHId() {
        return orderPayableDetailHId;
    }

    public void setOrderPayableDetailHId(UUID orderPayableDetailHId) {
        this.orderPayableDetailHId = orderPayableDetailHId;
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

    @Basic
    @Column(name = "OPERATION_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    @Basic
    @Column(name = "OPERATION_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(UUID operationUserId) {
        this.operationUserId = operationUserId;
    }
}
