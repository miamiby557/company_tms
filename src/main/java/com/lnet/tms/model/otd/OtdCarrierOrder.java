package com.lnet.tms.model.otd;

import com.lnet.tms.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by admin on 2015/5/13.
 */
@Entity
@javax.persistence.Table(name = "OTD_CARRIER_ORDER", schema = "LNET_TMS", catalog = "")
public class OtdCarrierOrder extends BaseEntity {
    private UUID carrierOrderId;

    private UUID startCityId;
    private String startCity;
    private UUID destCityId;
    private String destCity;

    //send_date              DATE,
    private Date sendDate;
    private Integer wrapType;
    private String goodsName;
    private Integer handoverType;
    private Boolean isSign;
    private Boolean isUpstairs;
    /*consignee              NVARCHAR2(50),
    consignee_address      NVARCHAR2(50),
    consignee_phone        NVARCHAR2(50)*/
    private String consignee;
    private String consigneeAddress;
    private String consigneePhone;
    /*wrap_type              INTEGER,
    goods_name             NVARCHAR2(50),
    handover_type          INTEGER*/
    private Integer receiptPageNumber;

    private Set<OtdCarrierOrderDetail> details=new HashSet<>();

    //private Set<OtdCarrierOrderDetailView> detailViews=new HashSet<>();


    @Id
    @javax.persistence.Column(name = "CARRIER_ORDER_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getCarrierOrderId() {
        return carrierOrderId;
    }

    public void setCarrierOrderId(UUID carrierOrderId) {
        this.carrierOrderId = carrierOrderId;
    }

    //transfer_Organization_id
    private UUID transferOrganizationId;

    @Basic
    @javax.persistence.Column(name = "transfer_Organization_id", nullable = true, insertable = true, updatable = true)
    public UUID getTransferOrganizationId() {
        return transferOrganizationId;
    }

    public void setTransferOrganizationId(UUID transferOrganizationId) {
        this.transferOrganizationId = transferOrganizationId;
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
    @javax.persistence.Column(name = "IS_SIGN", nullable = true, insertable = true, updatable = true)
    public Boolean getIsSign() {
        return isSign;
    }

    public void setIsSign(Boolean isSign) {
        this.isSign = isSign;
    }

    @Basic
    @javax.persistence.Column(name = "IS_UPSTAIRS", nullable = true, insertable = true, updatable = true)
    public Boolean getIsUpstairs() {
        return isUpstairs;
    }

    public void setIsUpstairs(Boolean isUpstairs) {
        this.isUpstairs = isUpstairs;
    }

    private String carrierOrderNumber;

    @Basic
    @javax.persistence.Column(name = "send_date", nullable = true, insertable = true, updatable = true)
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Basic
    @javax.persistence.Column(name = "goods_name", nullable = true, insertable = true, updatable = true)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @javax.persistence.Column(name = "wrap_type", nullable = true, insertable = true, updatable = true)
    public Integer getWrapType() {
        return wrapType;
    }

    public void setWrapType(Integer wrapType) {
        this.wrapType = wrapType;
    }

    @Basic
    @javax.persistence.Column(name = "handover_type", nullable = true, insertable = true, updatable = true)
    public Integer getHandoverType() {
        return handoverType;
    }

    public void setHandoverType(Integer handoverType) {
        this.handoverType = handoverType;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIPT_PAGE_NUMBER", nullable = true, insertable = true, updatable = true)
    public Integer getReceiptPageNumber() {
        return receiptPageNumber;
    }

    public void setReceiptPageNumber(Integer receiptPageNumber) {
        this.receiptPageNumber = receiptPageNumber;
    }

    private Date expectedDate;

    @Basic
    @javax.persistence.Column(name = "expected_Date", nullable = true, insertable = true, updatable = true)
    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    @Basic
    @javax.persistence.Column(name = "START_CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getStartCityId() {
        return startCityId;
    }

    public void setStartCityId(UUID startCityId) {
        this.startCityId = startCityId;
    }

    @Basic
    @javax.persistence.Column(name = "START_CITY", nullable = true, insertable = true, updatable = true)
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    @Basic
    @javax.persistence.Column(name = "DEST_CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getDestCityId() {
        return destCityId;
    }

    public void setDestCityId(UUID destCityId) {
        this.destCityId = destCityId;
    }

    @Basic
    @javax.persistence.Column(name = "DEST_CITY", nullable = true, insertable = true, updatable = true)
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

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

    private Integer totalItemQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalItemQuantity() {
        return totalItemQuantity;
    }

    public void setTotalItemQuantity(Integer totalItemQuantity) {
        this.totalItemQuantity = totalItemQuantity;
    }

    private Integer totalPackageQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    public void setTotalPackageQuantity(Integer totalPackageQuantity) {
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

    private Integer paymentType;

    @Basic
    @javax.persistence.Column(name = "PAYMENT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    private Integer calculateType;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }

    private Integer transportType;

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
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

    private Integer status;

    @Basic
    @javax.persistence.Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @OneToMany(mappedBy = "otdCarrierOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<OtdCarrierOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<OtdCarrierOrderDetail> details) {
        this.details = details;
    }

/*    @OneToMany(mappedBy = "otdCarrierOrder", cascade = CascadeType.REMOVE, orphanRemoval = true)
    public Set<OtdCarrierOrderDetailView> getDetailViews() {
        return detailViews;
    }

    public void setDetailViews(Set<OtdCarrierOrderDetailView> detailViews) {
        this.detailViews = detailViews;
    }*/

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
