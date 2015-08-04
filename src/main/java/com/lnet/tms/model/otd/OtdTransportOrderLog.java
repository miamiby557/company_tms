package com.lnet.tms.model.otd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/13.
 */
@Entity
@Table(name = "OTD_TRANSPORT_ORDER_LOG", schema = "LNET_TMS", catalog = "")
public class OtdTransportOrderLog {
    private UUID transportOrderLogId;
    private Integer currentStatus;
    private Timestamp operationDate;
    private String operationContent;
    private String remark;
    private Boolean isPublic;
    private UUID createUserId;
    private Timestamp createDate;
    private UUID transportOrderId;
    private Boolean isAbnormal;
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

    @Basic
    @Column(name = "transport_Order_Id", nullable = true, insertable = true, updatable = true)
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    @Id
    @Column(name = "TRANSPORT_ORDER_LOG_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getTransportOrderLogId() {
        return transportOrderLogId;
    }

    public void setTransportOrderLogId(UUID transportOrderLogId) {
        this.transportOrderLogId = transportOrderLogId;
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
}
