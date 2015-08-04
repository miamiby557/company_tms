package com.lnet.tms.model.otd;
import com.lnet.tms.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2015/5/13.
 */
@Entity
@javax.persistence.Table(name = "OTD_PICKUP_ORDER", schema = "LNET_TMS", catalog = "")
public class OtdPickupOrder  extends BaseEntity {
    private UUID pickupOrderId;

    @Id
    @javax.persistence.Column(name = "PICKUP_ORDER_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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

    private Integer totalItemQty;

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalItemQty() {
        return totalItemQty;
    }

    public void setTotalItemQty(Integer totalItemQty) {
        this.totalItemQty = totalItemQty;
    }

    private Integer totalPackageQty;

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalPackageQty() {
        return totalPackageQty;
    }

    public void setTotalPackageQty(Integer totalPackageQty) {
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

    private Integer confirmedTotalItemQty;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_TOTAL_ITEM_QTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedTotalItemQty() {
        return confirmedTotalItemQty;
    }

    public void setConfirmedTotalItemQty(Integer confirmedTotalItemQty) {
        this.confirmedTotalItemQty = confirmedTotalItemQty;
    }

    private Integer confirmedTotalPackageQty;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_TOTAL_PACKAGE_QTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedTotalPackageQty() {
        return confirmedTotalPackageQty;
    }

    public void setConfirmedTotalPackageQty(Integer confirmedTotalPackageQty) {
        this.confirmedTotalPackageQty = confirmedTotalPackageQty;
    }

    private Integer processStatus;

    @Basic
    @javax.persistence.Column(name = "PROCESS_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
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
