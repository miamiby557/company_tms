package com.lnet.tms.model.crm;

import com.lnet.tms.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Entity
@javax.persistence.Table(name = "CRM_CLIENT", schema = "LNET_TMS", catalog = "")
public class CrmClient extends BaseEntity{
    private UUID clientId;

    @Id
    @javax.persistence.Column(name = "CLIENT_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

   /* private UUID branchId;

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

    private String fullName;

    @Basic
    @javax.persistence.Column(name = "FULL_NAME", nullable = true, insertable = true, updatable = true)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private Integer grade;

    @Basic
    @javax.persistence.Column(name = "GRADE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
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

    private UUID contractDocumentId;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_DOCUMENT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getContractDocumentId() {
        return contractDocumentId;
    }

    public void setContractDocumentId(UUID contractDocumentId) {
        this.contractDocumentId = contractDocumentId;
    }

    private Integer businessModel;

    @Basic
    @javax.persistence.Column(name = "BUSINESS_MODEL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(Integer businessModel) {
        this.businessModel = businessModel;
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
