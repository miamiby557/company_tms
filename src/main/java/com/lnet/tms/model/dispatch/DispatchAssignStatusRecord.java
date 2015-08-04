package com.lnet.tms.model.dispatch;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "DISPATCH_ASSIGN_STATUS_RECORD", schema = "LNET_TMS", catalog = "")
public class DispatchAssignStatusRecord {
    private UUID dispatchAssignStatusRecord;
    private UUID dispatchAssignId;
    private Integer status;
    private Date operationDate;
    private UUID operator;

    private String operatorContent;


    @Basic
    @Column(name = "operator_Content", nullable = true, insertable = true, updatable = true)
    public String getOperatorContent() {
        return operatorContent;
    }

    public void setOperatorContent(String operatorContent) {
        this.operatorContent = operatorContent;
    }

    @Id
    @Column(name = "DISPATCH_ASSIGN_STATUS_RECORD_", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getDispatchAssignStatusRecord() {
        return dispatchAssignStatusRecord;
    }

    public void setDispatchAssignStatusRecord(UUID dispatchAssignStatusRecord) {
        this.dispatchAssignStatusRecord = dispatchAssignStatusRecord;
    }

    @Basic
    @Column(name = "DISPATCH_ASSIGN_ID", nullable = false, insertable = true, updatable = true)
    public UUID getDispatchAssignId() {
        return dispatchAssignId;
    }

    public void setDispatchAssignId(UUID dispatchAssignId) {
        this.dispatchAssignId = dispatchAssignId;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "OPERATOR", nullable = true, insertable = true, updatable = true)
    public UUID getOperator() {
        return operator;
    }

    public void setOperator(UUID operator) {
        this.operator = operator;
    }
}
