package com.lnet.tms.model.scm;

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
@Table(name = "SCM_CARRIER_QUOTE", schema = "LNET_TMS", catalog = "")
public class ScmCarrierQuote {
    private UUID carrierQuoteId;
    private UUID carrierLineId;

    private Integer calculateType;
    private UUID exacctId;
    private Double minimumFee;
    private Double minimumCondiction;
    private Double maxmumCondiction;
    private Double unitPrice;

    private String remark;
    private UUID createUserId;
    private Timestamp createDate;
    private UUID modifyUserId;
    private Timestamp modifyDate;
    private Boolean isActive;

    private ScmCarrierLine scmCarrierLine;

    @Id
    @Column(name = "CARRIER_QUOTE_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getCarrierQuoteId() {
        return carrierQuoteId;
    }

    public void setCarrierQuoteId(UUID carrierQuoteId) {
        this.carrierQuoteId = carrierQuoteId;
    }

    @Basic
    @Column(name = "CARRIER_LINE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierLineId() {
        return carrierLineId;
    }

    public void setCarrierLineId(UUID carrierLineId) {
        this.carrierLineId = carrierLineId;
    }



    @Basic
    @Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }

    @Basic
    @Column(name = "EXACCT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getExacctId() {
        return exacctId;
    }

    public void setExacctId(UUID exacctId) {
        this.exacctId = exacctId;
    }

    @Basic
    @Column(name = "MINIMUM_FEE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getMinimumFee() {
        return minimumFee;
    }

    public void setMinimumFee(Double minimumFee) {
        this.minimumFee = minimumFee;
    }

    @Basic
    @Column(name = "MINIMUM_CONDICTION", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getMinimumCondiction() {
        return minimumCondiction;
    }

    public void setMinimumCondiction(Double minimumCondiction) {
        this.minimumCondiction = minimumCondiction;
    }

    @Basic
    @Column(name = "MAXMUM_CONDICTION", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getMaxmumCondiction() {
        return maxmumCondiction;
    }

    public void setMaxmumCondiction(Double maxmumCondiction) {
        this.maxmumCondiction = maxmumCondiction;
    }

    @Basic
    @Column(name = "UNIT_PRICE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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
    @Column(name = "MODIFY_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(UUID modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "IS_ACTIVE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }



    @Basic
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne()
    @JoinColumn(name = "CARRIER_LINE_ID" ,nullable = false, insertable =false, updatable=false)
    public ScmCarrierLine getScmCarrierLine() {
        return scmCarrierLine;
    }

    public void setScmCarrierLine(ScmCarrierLine scmCarrierLine) {
        this.scmCarrierLine = scmCarrierLine;
    }
}
