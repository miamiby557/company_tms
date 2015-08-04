package com.lnet.tms.model.fee;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "FEE_ORDER_RECEIVABLE_DETAIL_H", schema = "LNET_TMS", catalog = "")
public class FeeOrderReceivableDetailH {
    private UUID orderReceivableDetailHId;
    private UUID orderReceivableId;
    private UUID exacctId;
    private Double amount;
    private UUID clientQuoteId;
    private String remark;
    private Integer version;
    private Timestamp operationDate;
    private UUID operationUserId;

    @Id
    @Column(name = "ORDER_RECEIVABLE_DETAIL_H_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderReceivableDetailHId() {
        return orderReceivableDetailHId;
    }

    public void setOrderReceivableDetailHId(UUID orderReceivableDetailHId) {
        this.orderReceivableDetailHId = orderReceivableDetailHId;
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
