package com.lnet.tms.model.fee;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "FEE_ORDER_PAYABLE_LOG_VIEW", schema = "LNET_TMS", catalog = "")
public class FeeOrderPayableLogView {
    private UUID orderPayableLogId;
    private UUID orderPayableId;
    private Integer status;
    private String remark;
    private Date operationDate;
    private UUID operationUserId;
    private String operationUserName;
    private String statusName;

    @Id
    @Column(name = "ORDER_PAYABLE_LOG_ID", nullable = false, insertable = true, updatable = true)
    public UUID getOrderPayableLogId() {
        return orderPayableLogId;
    }

    public void setOrderPayableLogId(UUID orderPayableLogId) {
        this.orderPayableLogId = orderPayableLogId;
    }

    @Basic
    @Column(name = "ORDER_PAYABLE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getOrderPayableId() {
        return orderPayableId;
    }

    public void setOrderPayableId(UUID orderPayableId) {
        this.orderPayableId = orderPayableId;
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
    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
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

    @Basic
    @Column(name = "OPERATION_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    @Basic
    @Column(name = "STATUS_NAME", nullable = true, insertable = true, updatable = true)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
