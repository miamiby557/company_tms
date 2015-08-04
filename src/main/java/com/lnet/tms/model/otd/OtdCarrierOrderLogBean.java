package com.lnet.tms.model.otd;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class OtdCarrierOrderLogBean {
    private UUID carrierOrderLogId;
    private Integer currentStatus;
    private Timestamp operationDate;
    private String operationContent;
    private String remark;
    private Boolean isPublic;
    private UUID createUserId;
    private Timestamp createDate;
    private UUID carrierOrderId;
    private Set<UUID> carrierOrderIds;

    private Boolean isAbnormal;

    private Boolean isSystem;

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Boolean getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Boolean isAbnormal) {
        this.isAbnormal = isAbnormal;
    }

    public UUID getCarrierOrderId() {
        return carrierOrderId;
    }

    public void setCarrierOrderId(UUID carrierOrderId) {
        this.carrierOrderId = carrierOrderId;
    }

    public UUID getCarrierOrderLogId() {
        return carrierOrderLogId;
    }

    public void setCarrierOrderLogId(UUID carrierOrderLogId) {
        this.carrierOrderLogId = carrierOrderLogId;
    }

    public Set<UUID> getCarrierOrderIds() {
        return carrierOrderIds;
    }

    public void setCarrierOrderIds(Set<UUID> carrierOrderIds) {
        this.carrierOrderIds = carrierOrderIds;
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
