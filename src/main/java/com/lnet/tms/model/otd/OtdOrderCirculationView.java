package com.lnet.tms.model.otd;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "OTD_ORDER_CIRCULATION_VIEW", schema = "LNET_TMS", catalog = "")
public class OtdOrderCirculationView {
    private UUID id;
    private UUID transportOrderId;
    private Double currentStatus;
    private Date operationDate;
    private String operationContent;
    private String remark;
    private Integer isPublic;
    private Integer isAbnormal;
    private UUID createUserId;
    private Date createDate;
    private String fullName;
    private String statusName;

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
    @Column(name = "CURRENT_STATUS", nullable = true, insertable = true, updatable = true, precision = -127)
    public Double getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Double currentStatus) {
        this.currentStatus = currentStatus;
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
    @Column(name = "IS_ABNORMAL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Integer isAbnormal) {
        this.isAbnormal = isAbnormal;
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

    @Basic
    @Column(name = "FULL_NAME", nullable = true, insertable = true, updatable = true)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
