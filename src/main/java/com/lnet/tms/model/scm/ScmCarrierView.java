package com.lnet.tms.model.scm;

import com.lnet.tms.model.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@javax.persistence.Table(name = "SCM_CARRIER_VIEW", schema = "LNET_TMS", catalog = "")
public class ScmCarrierView extends BaseEntity{
    private UUID carrierId;

    @Id
    @javax.persistence.Column(name = "CARRIER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(UUID carrierId) {
        this.carrierId = carrierId;
    }
/*
    private UUID branchId;

    @Basic
    @javax.persistence.Column(name = "BRANCH_ID", nullable = true, insertable = true, updatable = true)
    public UUID getBranchId() {
        return branchId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }*/

    private String code;

    @Basic
    @javax.persistence.Column(name = "CODE", nullable = false, insertable = true, updatable = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String namePinyin;

    @Basic
    @javax.persistence.Column(name = "NAME_PINYIN", nullable = true, insertable = true, updatable = true)
    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    private Integer type;

    @Basic
    @javax.persistence.Column(name = "TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private Integer grade;

    @Basic
    @javax.persistence.Column(name = "GRADE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    private Timestamp serviceStartDate;

    @Basic
    @javax.persistence.Column(name = "SERVICE_START_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(Timestamp serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    private Timestamp serviceEndDate;

    @Basic
    @javax.persistence.Column(name = "SERVICE_END_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(Timestamp serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    private String contactMan;

    @Basic
    @javax.persistence.Column(name = "CONTACT_MAN", nullable = true, insertable = true, updatable = true)
    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    private String contactPhone;

    @Basic
    @javax.persistence.Column(name = "CONTACT_PHONE", nullable = true, insertable = true, updatable = true)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    private String contactAddress;

    @Basic
    @javax.persistence.Column(name = "CONTACT_ADDRESS", nullable = true, insertable = true, updatable = true)
    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    private String city;

    @Basic
    @javax.persistence.Column(name = "CITY", nullable = true, insertable = true, updatable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private UUID cityId;

    @Basic
    @javax.persistence.Column(name = "CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    private String invoiceTitle;

    @Basic
    @javax.persistence.Column(name = "INVOICE_TITLE", nullable = true, insertable = true, updatable = true)
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    private String taxCode;

    @Basic
    @javax.persistence.Column(name = "TAX_CODE", nullable = true, insertable = true, updatable = true)
    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    private Integer settleCycle;

    @Basic
    @javax.persistence.Column(name = "SETTLE_CYCLE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSettleCycle() {
        return settleCycle;
    }

    public void setSettleCycle(Integer settleCycle) {
        this.settleCycle = settleCycle;
    }

    private Integer paymentType;

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    private String receiveBank;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_BANK", nullable = true, insertable = true, updatable = true)
    public String getReceiveBank() {
        return receiveBank;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    private String receiver;

    @Basic
    @javax.persistence.Column(name = "RECEIVER", nullable = true, insertable = true, updatable = true)
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    private String receiveAccount;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_ACCOUNT", nullable = true, insertable = true, updatable = true)
    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
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

    /*private UUID createUserId;

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
    }*/

    private String branchName;

    @Basic
    @javax.persistence.Column(name = "BRANCH_NAME", nullable = true, insertable = true, updatable = true)
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    private String carrierTypeName;

    @Basic
    @javax.persistence.Column(name = "CARRIER_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getCarrierTypeName() {
        return carrierTypeName;
    }

    public void setCarrierTypeName(String carrierTypeName) {
        this.carrierTypeName = carrierTypeName;
    }

    private String carrierGradeName;

    @Basic
    @javax.persistence.Column(name = "CARRIER_GRADE_NAME", nullable = true, insertable = true, updatable = true)
    public String getCarrierGradeName() {
        return carrierGradeName;
    }

    public void setCarrierGradeName(String carrierGradeName) {
        this.carrierGradeName = carrierGradeName;
    }

    private String settleCycleName;

    @Basic
    @javax.persistence.Column(name = "SETTLE_CYCLE_NAME", nullable = true, insertable = true, updatable = true)
    public String getSettleCycleName() {
        return settleCycleName;
    }

    public void setSettleCycleName(String settleCycleName) {
        this.settleCycleName = settleCycleName;
    }

    private String paymentTypeName;

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
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

    private Boolean isIncludeOrderDate;

    @Basic
    @Column(name = "IS_INCLUDE_ORDER_DATE", nullable = true, insertable = true, updatable = true)
    public Boolean getIsIncludeOrderDate() {
        return isIncludeOrderDate;
    }

    public void setIsIncludeOrderDate(Boolean isIncludeOrderDate) {
        this.isIncludeOrderDate = isIncludeOrderDate;
    }
}
