package com.lnet.tms.model.otd;

import com.lnet.tms.model.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Entity
@javax.persistence.Table(name = "OTD_CARRIER_ORDER_VIEW", schema = "LNET_TMS", catalog = "")
public class OtdCarrierOrderView  extends BaseEntity {
    private UUID carrierOrderId;

    @Id
    @javax.persistence.Column(name = "CARRIER_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierOrderId() {
        return carrierOrderId;
    }
    //transfer_Organization_id
    private UUID transferOrganizationId ;
    @Basic
    @javax.persistence.Column(name = "transfer_Organization_id", nullable = true, insertable = true, updatable = true)
    public UUID getTransferOrganizationId() {
        return transferOrganizationId;
    }

    public void setTransferOrganizationId(UUID transferOrganizationId) {
        this.transferOrganizationId = transferOrganizationId;
    }
    private String statusName;

    private Integer receiptPageNumber;

    private String consignee;
    private String consigneeAddress;
    private String consigneePhone;
    private Date expectedDate;
    private Date sendDate;
    private Integer payableStatus;
    private String payableStatusName;
    private Double totalAmount;
    private Integer version;
    private Integer backStatus;
    private Integer feeSaveMark;
    @Basic
    @javax.persistence.Column(name = "payable_Status", nullable = true, insertable = true, updatable = true)
    public Integer getPayableStatus() {
        return payableStatus;
    }

    public void setPayableStatus(Integer payableStatus) {
        this.payableStatus = payableStatus;
    }
    @Basic
    @javax.persistence.Column(name = "payable_status_Name", nullable = true, insertable = true, updatable = true)
    public String getPayableStatusName() {
        return payableStatusName;
    }

    public void setPayableStatusName(String payableStatusName) {
        this.payableStatusName = payableStatusName;
    }
    @Basic
    @javax.persistence.Column(name = "total_Amount", nullable = true, insertable = true, updatable = true)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Basic
    @javax.persistence.Column(name = "version", nullable = true, insertable = true, updatable = true)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    @Basic
    @javax.persistence.Column(name = "back_Status", nullable = true, insertable = true, updatable = true)
    public Integer getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(Integer backStatus) {
        this.backStatus = backStatus;
    }
    @Basic
    @javax.persistence.Column(name = "fee_Save_Mark", nullable = true, insertable = true, updatable = true)
    public Integer getFeeSaveMark() {
        return feeSaveMark;
    }

    public void setFeeSaveMark(Integer feeSaveMark) {
        this.feeSaveMark = feeSaveMark;
    }

    /* PAYABLE.STATUS PAYABLE_STATUS,
        get_item_name('feeAuditStatus',PAYABLE.STATUS) PAYABLE_STATUS_NAME,
        PAYABLE.TOTAL_AMOUNT ,
        PAYABLE.VERSION,
        PAYABLE.BACK_STATUS,
        PAYABLE.FEE_SAVE_MARK,*/
    @Basic
    @javax.persistence.Column(name = "send_Date", nullable = true, insertable = true, updatable = true)
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Basic
    @javax.persistence.Column(name = "expected_Date", nullable = true, insertable = true, updatable = true)
    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }
    @Basic
    @javax.persistence.Column(name = "consignee", nullable = true, insertable = true, updatable = true)
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    @Basic
    @javax.persistence.Column(name = "consignee_Phone", nullable = true, insertable = true, updatable = true)
    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }
    @Basic
    @javax.persistence.Column(name = "consignee_Address", nullable = true, insertable = true, updatable = true)
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIPT_PAGE_NUMBER", nullable = true, insertable = true, updatable = true)
    public Integer getReceiptPageNumber() {
        return receiptPageNumber;
    }

    public void setReceiptPageNumber(Integer receiptPageNumber) {
        this.receiptPageNumber = receiptPageNumber;
    }
    @Basic
    @javax.persistence.Column(name = "status_Name", nullable = false, insertable = true, updatable = true)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setCarrierOrderId(UUID carrierOrderId) {
        this.carrierOrderId = carrierOrderId;
    }

    private String carrierOrderNumber;

    @Basic
    @javax.persistence.Column(name = "CARRIER_ORDER_NUMBER", nullable = false, insertable = true, updatable = true)
    public String getCarrierOrderNumber() {
        return carrierOrderNumber;
    }

    public void setCarrierOrderNumber(String carrierOrderNumber) {
        this.carrierOrderNumber = carrierOrderNumber;
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

    private String driver;

    @Basic
    @javax.persistence.Column(name = "DRIVER", nullable = true, insertable = true, updatable = true)
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    private String driverPhone;

    @Basic
    @javax.persistence.Column(name = "DRIVER_PHONE", nullable = true, insertable = true, updatable = true)
    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    private String carType;

    @Basic
    @javax.persistence.Column(name = "CAR_TYPE", nullable = true, insertable = true, updatable = true)
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    private String carNumber;

    @Basic
    @javax.persistence.Column(name = "CAR_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    private Long totalItemQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTotalItemQuantity() {
        return totalItemQuantity;
    }

    public void setTotalItemQuantity(Long totalItemQuantity) {
        this.totalItemQuantity = totalItemQuantity;
    }

    private Long totalPackageQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    public void setTotalPackageQuantity(Long totalPackageQuantity) {
        this.totalPackageQuantity = totalPackageQuantity;
    }

    private Double totalVolume;

    @Basic
    @javax.persistence.Column(name = "TOTAL_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    private Double totalWeight;

    @Basic
    @javax.persistence.Column(name = "TOTAL_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
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

    private Long calculateType;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Long calculateType) {
        this.calculateType = calculateType;
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

    private Long status;

    @Basic
    @javax.persistence.Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

    private String calculateTypeName;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getCalculateTypeName() {
        return calculateTypeName;
    }

    public void setCalculateTypeName(String calculateTypeName) {
        this.calculateTypeName = calculateTypeName;
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

    private String code;

    @Basic
    @javax.persistence.Column(name = "CODE", nullable = true, insertable = true, updatable = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "NAME", nullable = true, insertable = true, updatable = true)
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

    private Long type;

    @Basic
    @javax.persistence.Column(name = "TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    private Long grade;

    @Basic
    @javax.persistence.Column(name = "GRADE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
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

    private Integer billingCycle;

    @Basic
    @javax.persistence.Column(name = "BILLING_CYCLE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(Integer billingCycle) {
        this.billingCycle = billingCycle;
    }

}
