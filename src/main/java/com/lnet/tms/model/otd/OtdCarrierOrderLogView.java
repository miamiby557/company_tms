package com.lnet.tms.model.otd;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "OTD_CARRIER_ORDER_LOG_VIEW", schema = "LNET_TMS", catalog = "")
public class OtdCarrierOrderLogView {
    private UUID carrierOrderLogId;
    private UUID carrierOrderId;
    private Integer currentStatus;
    private Timestamp operationDate;
    private String operationContent;
    private String remark;
    private Integer isPublic;
    private UUID createUserId;
    private Timestamp createDate;
    private Integer isAbnormal;
    private String statusName;
    private String createUserName;

    @Id
    @Column(name = "CARRIER_ORDER_LOG_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierOrderLogId() {
        return carrierOrderLogId;
    }

    public void setCarrierOrderLogId(UUID carrierOrderLogId) {
        this.carrierOrderLogId = carrierOrderLogId;
    }
    private Boolean isSystem;
    @Basic
    @Column(name = "is_System", nullable = true, insertable = true, updatable = true)
    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }
    @Basic
    @Column(name = "CARRIER_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierOrderId() {
        return carrierOrderId;
    }

    public void setCarrierOrderId(UUID carrierOrderId) {
        this.carrierOrderId = carrierOrderId;
    }

    @Basic
    @Column(name = "CURRENT_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
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
    @Column(name = "OPERATION_CONTENT", nullable = true, insertable = true, updatable = true)
    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
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
    @Column(name = "IS_PUBLIC", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    @Basic
    @Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "IS_ABNORMAL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Integer isAbnormal) {
        this.isAbnormal = isAbnormal;
    }

    @Basic
    @Column(name = "STATUS_NAME", nullable = true, insertable = true, updatable = true)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Basic
    @Column(name = "CREATE_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
