package com.lnet.tms.model.fee;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Entity
@Table(name = "FEE_ORDER_RECEIVABLE_LOG", schema = "LNET_TMS", catalog = "")
public class FeeOrderReceivableLog {
    private UUID orderReceivableLogId;
    private UUID orderReceivableId;
    private Integer status;
    private String remark;
    private Timestamp operationDate;
    private UUID operationUserId;

    @Id
    @Column(name = "ORDER_RECEIVABLE_LOG_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderReceivableLogId() {
        return orderReceivableLogId;
    }

    public void setOrderReceivableLogId(UUID orderReceivableLogId) {
        this.orderReceivableLogId = orderReceivableLogId;
    }

    @Basic
    @Column(name = "ORDER_RECEIVABLE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getOrderReceivableId() {
        return orderReceivableId;
    }

    public void setOrderReceivableId(UUID orderReceivableId) {
        this.orderReceivableId = orderReceivableId;
    }

    @Basic
    @Column(name = "STATUS", nullable = false, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
