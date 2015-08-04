package com.lnet.tms.model.dispatch;

import com.lnet.tms.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@javax.persistence.Table(name = "DISPATCH_ASSIGN", schema = "LNET_TMS", catalog = "")
public class DispatchAssign extends BaseEntity{
    private UUID dispatchAssignId;

    private String dispatchAssignNumber;

    private Integer vehicleType;

    private String vehicleCode;

    private String dirver;

    private String dirverPhone;

    private Double totalFee;

    private Integer status;

    private Integer dispatchType;

    private Integer totalItemQuantity;

    private Integer totalPackageQuantity;

    private Double totalVolume;

    private Double totalWeight;

    private String startAddress;

    private String destAddress;

    private Date expectFinishTime;

    private String remark;
    private Set<DispatchAssignDetail> details=new HashSet<DispatchAssignDetail>();
    @OneToMany(mappedBy = "dispatchAssign", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<DispatchAssignDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<DispatchAssignDetail> details) {
        this.details = details;
    }

    @Id
    @javax.persistence.Column(name = "DISPATCH_ASSIGN_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getDispatchAssignId() {
        return dispatchAssignId;
    }

    public void setDispatchAssignId(UUID dispatchAssignId) {
        this.dispatchAssignId = dispatchAssignId;
    }

    @Basic
    @javax.persistence.Column(name = "DISPATCH_ASSIGN_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getDispatchAssignNumber() {
        return dispatchAssignNumber;
    }

    public void setDispatchAssignNumber(String dispatchAssignNumber) {
        this.dispatchAssignNumber = dispatchAssignNumber;
    }

    @Basic
    @javax.persistence.Column(name = "VEHICLE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Basic
    @javax.persistence.Column(name = "VEHICLE_CODE", nullable = true, insertable = true, updatable = true)
    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    @Basic
    @javax.persistence.Column(name = "DIRVER", nullable = true, insertable = true, updatable = true)
    public String getDirver() {
        return dirver;
    }

    public void setDirver(String dirver) {
        this.dirver = dirver;
    }

    @Basic
    @javax.persistence.Column(name = "DIRVER_PHONE", nullable = true, insertable = true, updatable = true)
    public String getDirverPhone() {
        return dirverPhone;
    }

    public void setDirverPhone(String dirverPhone) {
        this.dirverPhone = dirverPhone;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_FEE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    @Basic
    @javax.persistence.Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @javax.persistence.Column(name = "DISPATCH_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(Integer dispatchType) {
        this.dispatchType = dispatchType;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalItemQuantity() {
        return totalItemQuantity;
    }

    public void setTotalItemQuantity(Integer totalItemQuantity) {
        this.totalItemQuantity = totalItemQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    public void setTotalPackageQuantity(Integer totalPackageQuantity) {
        this.totalPackageQuantity = totalPackageQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    @Basic
    @javax.persistence.Column(name = "TOTAL_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Basic
    @javax.persistence.Column(name = "START_ADDRESS", nullable = true, insertable = true, updatable = true)
    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    @Basic
    @javax.persistence.Column(name = "DEST_ADDRESS", nullable = true, insertable = true, updatable = true)
    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    @Basic
    @javax.persistence.Column(name = "EXPECT_FINISH_TIME", nullable = true, insertable = true, updatable = true)
    public Date getExpectFinishTime() {
        return expectFinishTime;
    }

    public void setExpectFinishTime(Date expectFinishTime) {
        this.expectFinishTime = expectFinishTime;
    }

    @Basic
    @javax.persistence.Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
