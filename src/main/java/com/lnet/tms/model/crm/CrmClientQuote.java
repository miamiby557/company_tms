package com.lnet.tms.model.crm;

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
@Table(name = "CRM_CLIENT_QUOTE", schema = "LNET_TMS", catalog = "")
public class CrmClientQuote {
    private UUID clientQuoteId;
    private UUID clientLineId;

    private Integer calculateType;
    private UUID exacctId;
    private Double minimumFee;
    private Double minimumCondiction;
    private Double maxmumCondiction;
    private Double unitPrice;

    private UUID createUserId;
    private Timestamp createDate;
    private UUID modifyUserId;
    private Timestamp modifyDate;
    private Boolean isActive;
    private String remark;
    private CrmClientLine crmClientLine;

    @Id
    @Column(name = "CLIENT_QUOTE_ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getClientQuoteId() {
        return clientQuoteId;
    }

    public void setClientQuoteId(UUID clientQuoteId) {
        this.clientQuoteId = clientQuoteId;
    }
    @ManyToOne()
    @JoinColumn(name = "CLIENT_LINE_ID" ,nullable = false, insertable =false, updatable=false)
    public CrmClientLine getCrmClientLine() {
        return crmClientLine;
    }
    public void setCrmClientLine(CrmClientLine crmClientLine) {
        this.crmClientLine = crmClientLine;
    }

    @Basic
    @Column(name = "CLIENT_LINE_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientLineId() {
        return clientLineId;
    }

    public void setClientLineId(UUID clientLineId) {
        this.clientLineId = clientLineId;
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
}
