package com.lnet.tms.model.otd;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;
public class OtdTransportOrderLogBean {
    private UUID transportOrderLogId;
    private Integer currentStatus;
    private Timestamp operationDate;
    private String operationContent;
    private String remark;
    private Boolean isPublic;
    private UUID createUserId;
    private Timestamp createDate;
    private UUID transportOrderId;
    private Set<UUID> transportOrderIds;

    private Boolean isAbnormal;
    private Boolean isSystem;

    public Boolean getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Boolean isAbnormal) {
        this.isAbnormal = isAbnormal;
    }
    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    public UUID getTransportOrderLogId() {
        return transportOrderLogId;
    }

    public void setTransportOrderLogId(UUID transportOrderLogId) {
        this.transportOrderLogId = transportOrderLogId;
    }

    public Set<UUID> getTransportOrderIds() {
        return transportOrderIds;
    }

    public void setTransportOrderIds(Set<UUID> transportOrderIds) {
        this.transportOrderIds = transportOrderIds;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
