package com.lnet.tms.model.scm;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by admin on 2015/5/15.
 */
@Entity
@javax.persistence.Table(name = "SCM_CARRIER_QUOTE_VIEW", schema = "LNET_TMS", catalog = "")
public class ScmCarrierQuoteView {
    private UUID carrierQuoteId;

    @Id
    @javax.persistence.Column(name = "CARRIER_QUOTE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierQuoteId() {
        return carrierQuoteId;
    }

    public void setCarrierQuoteId(UUID carrierQuoteId) {
        this.carrierQuoteId = carrierQuoteId;
    }

    private UUID carrierId;

    @Basic
    @javax.persistence.Column(name = "CARRIER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(UUID carrierId) {
        this.carrierId = carrierId;
    }

    private UUID carrierLineId;

    @Basic
    @javax.persistence.Column(name = "CARRIER_LINE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierLineId() {
        return carrierLineId;
    }

    public void setCarrierLineId(UUID carrierLineId) {
        this.carrierLineId = carrierLineId;
    }

    private Integer transportType;

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    private Integer calculateType;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
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

    private Boolean isActive;

    @Basic
    @javax.persistence.Column(name = "IS_ACTIVE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
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

    private String exacctName;

    @Basic
    @javax.persistence.Column(name = "EXACCT_NAME", nullable = true, insertable = true, updatable = true)
    public String getExacctName() {
        return exacctName;
    }

    public void setExacctName(String exacctName) {
        this.exacctName = exacctName;
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

    private String startCity;

    private String destCity;

    @Basic
    @javax.persistence.Column(name = "START_CITY", nullable = true, insertable = true, updatable = true)
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    @Basic
    @javax.persistence.Column(name = "DEST_CITY", nullable = true, insertable = true, updatable = true)
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }
}
