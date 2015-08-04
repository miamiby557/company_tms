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
@javax.persistence.Table(name = "OTD_PICKUP_ORDER_VIEW", schema = "LNET_TMS", catalog = "")
public class OtdPickupOrderView  extends BaseEntity {
    private UUID pickupOrderId;

    @Id
    @javax.persistence.Column(name = "PICKUP_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getPickupOrderId() {
        return pickupOrderId;
    }

    public void setPickupOrderId(UUID pickupOrderId) {
        this.pickupOrderId = pickupOrderId;
    }

    private String pickupOrderNumber;

    @Basic
    @javax.persistence.Column(name = "PICKUP_ORDER_NUMBER", nullable = false, insertable = true, updatable = true)
    public String getPickupOrderNumber() {
        return pickupOrderNumber;
    }

    public void setPickupOrderNumber(String pickupOrderNumber) {
        this.pickupOrderNumber = pickupOrderNumber;
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

    private String address;

    @Basic
    @javax.persistence.Column(name = "ADDRESS", nullable = true, insertable = true, updatable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    private String city;

    @Basic
    @javax.persistence.Column(name = "CITY", nullable = true, insertable = true, updatable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /*private UUID branchId;

    @Basic
    @javax.persistence.Column(name = "BRANCH_ID", nullable = true, insertable = true, updatable = true)
    public UUID getBranchId() {
        return branchId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }*/

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

    private Long totalItemQty;

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTotalItemQty() {
        return totalItemQty;
    }

    public void setTotalItemQty(Long totalItemQty) {
        this.totalItemQty = totalItemQty;
    }

    private Long totalPackageQty;

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTotalPackageQty() {
        return totalPackageQty;
    }

    public void setTotalPackageQty(Long totalPackageQty) {
        this.totalPackageQty = totalPackageQty;
    }

    private Double confirmedTotalVolume;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_TOTAL_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getConfirmedTotalVolume() {
        return confirmedTotalVolume;
    }

    public void setConfirmedTotalVolume(Double confirmedTotalVolume) {
        this.confirmedTotalVolume = confirmedTotalVolume;
    }

    private Double confirmedTotalWeight;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_TOTAL_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getConfirmedTotalWeight() {
        return confirmedTotalWeight;
    }

    public void setConfirmedTotalWeight(Double confirmedTotalWeight) {
        this.confirmedTotalWeight = confirmedTotalWeight;
    }

    private Long confirmedTotalItemQty;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_TOTAL_ITEM_QTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getConfirmedTotalItemQty() {
        return confirmedTotalItemQty;
    }

    public void setConfirmedTotalItemQty(Long confirmedTotalItemQty) {
        this.confirmedTotalItemQty = confirmedTotalItemQty;
    }

    private Long confirmedTotalPackageQty;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_TOTAL_PACKAGE_QTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getConfirmedTotalPackageQty() {
        return confirmedTotalPackageQty;
    }

    public void setConfirmedTotalPackageQty(Long confirmedTotalPackageQty) {
        this.confirmedTotalPackageQty = confirmedTotalPackageQty;
    }

  /*  private UUID createUserId;

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

    private Long processStatus;

    @Basic
    @javax.persistence.Column(name = "PROCESS_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Long processStatus) {
        this.processStatus = processStatus;
    }

    private String processStatusName;

    @Basic
    @javax.persistence.Column(name = "PROCESS_STATUS_NAME", nullable = true, insertable = true, updatable = true)
    public String getProcessStatusName() {
        return processStatusName;
    }

    public void setProcessStatusName(String processStatusName) {
        this.processStatusName = processStatusName;
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

    private String clientName;

    @Basic
    @javax.persistence.Column(name = "CLIENT_NAME", nullable = true, insertable = true, updatable = true)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String clientCode;

    @Basic
    @javax.persistence.Column(name = "CLIENT_CODE", nullable = true, insertable = true, updatable = true)
    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
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

    private String clientCity;

    @Basic
    @javax.persistence.Column(name = "CLIENT_CITY", nullable = true, insertable = true, updatable = true)
    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
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

    private Date reservationTime;
    @Basic
    @javax.persistence.Column(name = "RESERVATION_TIME", nullable = true, insertable = true, updatable = true)
    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }
}
