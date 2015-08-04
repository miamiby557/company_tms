package com.lnet.tms.model.crm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/15.
 */
@Entity
@javax.persistence.Table(name = "CRM_CLIENT_QUOTE_VIEW", schema = "LNET_TMS", catalog = "")
public class CrmClientQuoteView {
    private UUID clientQuoteId;

    private String startCity;
    private String destCity;

    @Basic
    @Column(name = "START_CITY", nullable = true, insertable = true, updatable = true)
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    @Basic
    @Column(name = "DEST_CITY", nullable = true, insertable = true, updatable = true)
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }
    @Id
    @javax.persistence.Column(name = "CLIENT_QUOTE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientQuoteId() {
        return clientQuoteId;
    }

    public void setClientQuoteId(UUID clientQuoteId) {
        this.clientQuoteId = clientQuoteId;
    }

    private UUID clientId;

    @Basic
    @javax.persistence.Column(name = "CLIENT_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    private UUID clientLineId;

    @Basic
    @javax.persistence.Column(name = "CLIENT_LINE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientLineId() {
        return clientLineId;
    }

    public void setClientLineId(UUID clientLineId) {
        this.clientLineId = clientLineId;
    }

    private Long transportType;

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTransportType() {
        return transportType;
    }

    public void setTransportType(Long transportType) {
        this.transportType = transportType;
    }

    private Long calculateType;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Long calculateType) {
        this.calculateType = calculateType;
    }

    private UUID exacctId;

    @Basic
    @javax.persistence.Column(name = "EXACCT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getExacctId() {
        return exacctId;
    }

    public void setExacctId(UUID exacctId) {
        this.exacctId = exacctId;
    }

    private Double minimumFee;

    @Basic
    @javax.persistence.Column(name = "MINIMUM_FEE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getMinimumFee() {
        return minimumFee;
    }

    public void setMinimumFee(Double minimumFee) {
        this.minimumFee = minimumFee;
    }

    private Double minimumCondiction;

    @Basic
    @javax.persistence.Column(name = "MINIMUM_CONDICTION", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getMinimumCondiction() {
        return minimumCondiction;
    }

    public void setMinimumCondiction(Double minimumCondiction) {
        this.minimumCondiction = minimumCondiction;
    }

    private Double maxmumCondiction;

    @Basic
    @javax.persistence.Column(name = "MAXMUM_CONDICTION", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getMaxmumCondiction() {
        return maxmumCondiction;
    }

    public void setMaxmumCondiction(Double maxmumCondiction) {
        this.maxmumCondiction = maxmumCondiction;
    }

    private Double unitPrice;

    @Basic
    @javax.persistence.Column(name = "UNIT_PRICE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    private Double timeline;

    @Basic
    @javax.persistence.Column(name = "TIMELINE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTimeline() {
        return timeline;
    }

    public void setTimeline(Double timeline) {
        this.timeline = timeline;
    }

    private Integer isActive;

    @Basic
    @javax.persistence.Column(name = "IS_ACTIVE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    private String remark;

    @Basic
    @javax.persistence.Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private UUID createUserId;

    @Basic
    @javax.persistence.Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    private Timestamp createDate;

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    private UUID modifyUserId;

    @Basic
    @javax.persistence.Column(name = "MODIFY_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(UUID modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    private Timestamp modifyDate;

    @Basic
    @javax.persistence.Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    private String transportTypeName;

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getTransportTypeName() {
        return transportTypeName;
    }

    public void setTransportTypeName(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    private String calculateTypeName;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getCalculateTypeName() {
        return calculateTypeName;
    }

    public void setCalculateTypeName(String calculateTypeName) {
        this.calculateTypeName = calculateTypeName;
    }

    private String exacctCode;

    @Basic
    @javax.persistence.Column(name = "EXACCT_CODE", nullable = true, insertable = true, updatable = true)
    public String getExacctCode() {
        return exacctCode;
    }

    public void setExacctCode(String exacctCode) {
        this.exacctCode = exacctCode;
    }

    private String exacctName;

    @Basic
    @javax.persistence.Column(name = "EXACCT_NAME", nullable = true, insertable = true, updatable = true)
    public String getExacctName() {
        return exacctName;
    }

    public void setExacctName(String exacctName) {
        this.exacctName = exacctName;
    }

    private String exacctRemark;

    @Basic
    @javax.persistence.Column(name = "EXACCT_REMARK", nullable = true, insertable = true, updatable = true)
    public String getExacctRemark() {
        return exacctRemark;
    }

    public void setExacctRemark(String exacctRemark) {
        this.exacctRemark = exacctRemark;
    }

    private UUID exacctSuperiorId;

    @Basic
    @javax.persistence.Column(name = "EXACCT_SUPERIOR_ID", nullable = true, insertable = true, updatable = true)
    public UUID getExacctSuperiorId() {
        return exacctSuperiorId;
    }

    public void setExacctSuperiorId(UUID exacctSuperiorId) {
        this.exacctSuperiorId = exacctSuperiorId;
    }

    private String createUserName;

    @Basic
    @javax.persistence.Column(name = "CREATE_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    private String modifyUserName;

    @Basic
    @javax.persistence.Column(name = "MODIFY_USER_NAME", nullable = true, insertable = true, updatable = true)
    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }
}
