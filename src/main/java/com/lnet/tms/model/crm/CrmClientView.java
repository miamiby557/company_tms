package com.lnet.tms.model.crm;

import com.lnet.tms.model.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/15.
 */
@Entity
@javax.persistence.Table(name = "CRM_CLIENT_VIEW", schema = "LNET_TMS", catalog = "")
public class CrmClientView extends BaseEntity {
    private UUID clientId;

    @Id
    @javax.persistence.Column(name = "CLIENT_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
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
    }
*/
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

    private String fullName;

    @Basic
    @javax.persistence.Column(name = "FULL_NAME", nullable = true, insertable = true, updatable = true)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private long grade;

    @Basic
    @javax.persistence.Column(name = "GRADE", nullable = false, insertable = true, updatable = true, precision = 0)
    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
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

    private Long settleCycle;

    @Basic
    @javax.persistence.Column(name = "SETTLE_CYCLE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getSettleCycle() {
        return settleCycle;
    }

    public void setSettleCycle(Long settleCycle) {
        this.settleCycle = settleCycle;
    }

    private Long paymentType;

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    private UUID contractDocumentId;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_DOCUMENT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getContractDocumentId() {
        return contractDocumentId;
    }

    private String contractDocumentName;
    @Basic
    @javax.persistence.Column(name = "contract_document_name", nullable = true, insertable = true, updatable = true)
    public String getContractDocumentName() {
        return contractDocumentName;
    }

    public void setContractDocumentName(String contractDocumentName) {
        this.contractDocumentName = contractDocumentName;
    }

    public void setContractDocumentId(UUID contractDocumentId) {
        this.contractDocumentId = contractDocumentId;
    }

    private Long businessModel;

    @Basic
    @javax.persistence.Column(name = "BUSINESS_MODEL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(Long businessModel) {
        this.businessModel = businessModel;
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

    private String remark;

    @Basic
    @javax.persistence.Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String branchName;

    @Basic
    @javax.persistence.Column(name = "BRANCH_NAME", nullable = true, insertable = true, updatable = true)
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    private String businessModelname;

    @Basic
    @javax.persistence.Column(name = "BUSINESS_MODELNAME", nullable = true, insertable = true, updatable = true)
    public String getBusinessModelname() {
        return businessModelname;
    }

    public void setBusinessModelname(String businessModelname) {
        this.businessModelname = businessModelname;
    }

    private String clientGradeName;

    @Basic
    @javax.persistence.Column(name = "CLIENT_GRADE_NAME", nullable = true, insertable = true, updatable = true)
    public String getClientGradeName() {
        return clientGradeName;
    }

    public void setClientGradeName(String clientGradeName) {
        this.clientGradeName = clientGradeName;
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
