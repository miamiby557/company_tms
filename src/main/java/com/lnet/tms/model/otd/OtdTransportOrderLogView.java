package com.lnet.tms.model.otd;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2015/5/22.
 */
@Entity
@Table(name = "OTD_TRANSPORT_ORDER_LOG_VIEW", schema = "LNET_TMS", catalog = "")
public class OtdTransportOrderLogView {
    private UUID id;
    private UUID transportOrderId;
    private Integer currentStatus;
    private Timestamp operationDate;
    private String operationContent;
    private String remark;
    private Boolean isPublic;
    private Boolean isAbnormal;
    private String createUserName;
    private UUID createUserId;
    private Date createDate;
    private Boolean isSystem;

    @Basic
    @Column(name = "IS_ABNORMAL", nullable = true, insertable = true, updatable = true)
    public Boolean getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Boolean isAbnormal) {
        this.isAbnormal = isAbnormal;
    }
    @Basic
    @Column(name = "is_System", nullable = true, insertable = true, updatable = true)
    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    @Id
    @Column(name = "ID", nullable = true, insertable = true, updatable = true)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TRANSPORT_ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
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
    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Basic
    @Column(name = "CREATE_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
