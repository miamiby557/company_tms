package com.lnet.tms.model.scm;

import com.lnet.tms.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Entity
@javax.persistence.Table(name = "SCM_CARRIER", schema = "LNET_TMS", catalog = "")
public class ScmCarrier extends BaseEntity{
    private UUID carrierId;
//    private UUID branchId;
    private String code;
    private String name;
    private String namePinyin;
    private Integer type;
    private Integer grade;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private String contactMan;
    private String contactPhone;
    private String contactAddress;
    private String city;
    private UUID cityId;
    private String invoiceTitle;
    private String taxCode;
    private Integer settleCycle;
    private Integer paymentType;
    private String receiveBank;
    private String receiver;
    private String receiveAccount;
    private String remark;
    /*private UUID createUserId;
    private Timestamp createDate;
    private UUID modifyUserId;
    private Timestamp modifyDate;*/
    private String certificateAffixId;
    private Boolean isIncludeOrderDate;

    @Basic
    @Column(name = "IS_INCLUDE_ORDER_DATE", nullable = true, insertable = true, updatable = true)
    public Boolean getIsIncludeOrderDate() {
        return isIncludeOrderDate;
    }

    public void setIsIncludeOrderDate(Boolean isIncludeOrderDate) {
        this.isIncludeOrderDate = isIncludeOrderDate;
    }

    @Id
    @javax.persistence.Column(name = "CARRIER_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(UUID carrierId) {
        this.carrierId = carrierId;
    }
/*
    @Basic
    @javax.persistence.Column(name = "BRANCH_ID", nullable = true, insertable = true, updatable = true)
    public UUID getBranchId() {
        return branchId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }*/

    @Basic
    @javax.persistence.Column(name = "CODE", nullable = false, insertable = true, updatable = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @javax.persistence.Column(name = "NAME_PINYIN", nullable = true, insertable = true, updatable = true)
    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    @Basic
    @javax.persistence.Column(name = "TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @javax.persistence.Column(name = "GRADE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @javax.persistence.Column(name = "SERVICE_START_DATE", nullable = true, insertable = true, updatable = true)
    public Date getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(Date serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    @Basic
    @javax.persistence.Column(name = "SERVICE_END_DATE", nullable = true, insertable = true, updatable = true)
    public Date getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(Date serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    @Basic
    @javax.persistence.Column(name = "CONTACT_MAN", nullable = true, insertable = true, updatable = true)
    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    @Basic
    @javax.persistence.Column(name = "CONTACT_PHONE", nullable = true, insertable = true, updatable = true)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @javax.persistence.Column(name = "CONTACT_ADDRESS", nullable = true, insertable = true, updatable = true)
    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    @Basic
    @javax.persistence.Column(name = "CITY", nullable = true, insertable = true, updatable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @javax.persistence.Column(name = "CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    @Basic
    @javax.persistence.Column(name = "INVOICE_TITLE", nullable = true, insertable = true, updatable = true)
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    @Basic
    @javax.persistence.Column(name = "TAX_CODE", nullable = true, insertable = true, updatable = true)
    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @Basic
    @javax.persistence.Column(name = "SETTLE_CYCLE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSettleCycle() {
        return settleCycle;
    }

    public void setSettleCycle(Integer settleCycle) {
        this.settleCycle = settleCycle;
    }

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVE_BANK", nullable = true, insertable = true, updatable = true)
    public String getReceiveBank() {
        return receiveBank;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVER", nullable = true, insertable = true, updatable = true)
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVE_ACCOUNT", nullable = true, insertable = true, updatable = true)
    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    @Basic
    @javax.persistence.Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /*@Basic
    @javax.persistence.Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @javax.persistence.Column(name = "MODIFY_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(UUID modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    @Basic
    @javax.persistence.Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }*/

    @Basic
    @javax.persistence.Column(name = "CERTIFICATE_AFFIX_ID", nullable = true, insertable = true, updatable = true)
    public String getCertificateAffixId() {
        return certificateAffixId;
    }

    public void setCertificateAffixId(String certificateAffixId) {
        this.certificateAffixId = certificateAffixId;
    }
}
