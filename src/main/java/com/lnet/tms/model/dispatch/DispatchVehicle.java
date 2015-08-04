package com.lnet.tms.model.dispatch;

import com.lnet.tms.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Administrator on 2015/7/31.
 */
@Entity
@javax.persistence.Table(name = "DISPATCH_VEHICLE", schema = "LNET_TMS", catalog = "")
public class DispatchVehicle extends BaseEntity{
    private UUID vehicleId;

    @Id
    @javax.persistence.Column(name = "VEHICLE_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(UUID vehicleId) {
        this.vehicleId = vehicleId;
    }

    private String driver;

    @Basic
    @javax.persistence.Column(name = "DRIVER")
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    private String driverPhone;

    @Basic
    @javax.persistence.Column(name = "DRIVER_PHONE")
    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    private Integer vehicleType;

    @Basic
    @javax.persistence.Column(name = "VEHICLE_TYPE")
    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    private String vehicleSize;

    @Basic
    @javax.persistence.Column(name = "VEHICLE_SIZE")
    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    private String maxWeight;

    @Basic
    @javax.persistence.Column(name = "MAX_WEIGHT")
    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    private String vehicleNumber;

    @Basic
    @javax.persistence.Column(name = "VEHICLE_NUMBER")
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    private String vehicleContent;

    @Basic
    @javax.persistence.Column(name = "VEHICLE_CONTENT")
    public String getVehicleContent() {
        return vehicleContent;
    }

    public void setVehicleContent(String vehicleContent) {
        this.vehicleContent = vehicleContent;
    }

    private Timestamp compactStartDate;

    @Basic
    @javax.persistence.Column(name = "COMPACT_START_DATE")
    public Timestamp getCompactStartDate() {
        return compactStartDate;
    }

    public void setCompactStartDate(Timestamp compactStartDate) {
        this.compactStartDate = compactStartDate;
    }

    private Timestamp compactEndDate;

    @Basic
    @javax.persistence.Column(name = "COMPACT_END_DATE")
    public Timestamp getCompactEndDate() {
        return compactEndDate;
    }

    public void setCompactEndDate(Timestamp compactEndDate) {
        this.compactEndDate = compactEndDate;
    }

    private Integer status;

    @Basic
    @javax.persistence.Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private String remark;

    @Basic
    @javax.persistence.Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
