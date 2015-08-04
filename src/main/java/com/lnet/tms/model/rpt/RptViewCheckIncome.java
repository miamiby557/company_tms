package com.lnet.tms.model.rpt;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by admin on 2015/6/26.
 */
@Entity
@javax.persistence.Table(name = "RPT_VIEW_CHECK_INCOME", schema = "LNET_TMS", catalog = "")
public class RptViewCheckIncome {
    private UUID clientId;

    @Basic
    @javax.persistence.Column(name = "CLIENT_ID")
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String contactAddress;

    @Basic
    @javax.persistence.Column(name = "CONTACT_ADDRESS")
    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    private String transportTypeName;

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_TYPE_NAME")
    public String getTransportTypeName() {
        return transportTypeName;
    }

    public void setTransportTypeName(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    private String calculateTypeName;

    @Basic
    @javax.persistence.Column(name = "CALCULATE_TYPE_NAME")
    public String getCalculateTypeName() {
        return calculateTypeName;
    }

    public void setCalculateTypeName(String calculateTypeName) {
        this.calculateTypeName = calculateTypeName;
    }

    private String startCity;

    @Basic
    @javax.persistence.Column(name = "START_CITY")
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    private String carrierOrderNumber;

    @Basic
    @javax.persistence.Column(name = "CARRIER_ORDER_NUMBER")
    public String getCarrierOrderNumber() {
        return carrierOrderNumber;
    }

    public void setCarrierOrderNumber(String carrierOrderNumber) {
        this.carrierOrderNumber = carrierOrderNumber;
    }

    private Timestamp expectedDate;

    @Basic
    @javax.persistence.Column(name = "EXPECTED_DATE")
    public Timestamp getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Timestamp expectedDate) {
        this.expectedDate = expectedDate;
    }

    private Timestamp signDate;

    @Basic
    @javax.persistence.Column(name = "SIGN_DATE")
    public Timestamp getSignDate() {
        return signDate;
    }

    public void setSignDate(Timestamp signDate) {
        this.signDate = signDate;
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

    private String clientOrderNumber;

    @Id
    @javax.persistence.Column(name = "CLIENT_ORDER_NUMBER")
    public String getClientOrderNumber() {
        return clientOrderNumber;
    }

    public void setClientOrderNumber(String clientOrderNumber) {
        this.clientOrderNumber = clientOrderNumber;
    }

    private Timestamp orderDate;

    @Basic
    @javax.persistence.Column(name = "ORDER_DATE")
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    private Timestamp sentDate;

    @Basic
    @javax.persistence.Column(name = "SENT_DATE")
    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    private String destCity;

    @Basic
    @javax.persistence.Column(name = "DEST_CITY")
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    private String receiveCompany;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_COMPANY")
    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    private String receiveMan;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_MAN")
    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    private String goodsCode;

    @Basic
    @javax.persistence.Column(name = "GOODS_CODE")
    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    private Integer totalItemQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_ITEM_QUANTITY")
    public Integer getTotalItemQuantity() {
        return totalItemQuantity;
    }

    public void setTotalItemQuantity(Integer totalItemQuantity) {
        this.totalItemQuantity = totalItemQuantity;
    }

    private Integer totalPackageQuantity;

    @Basic
    @javax.persistence.Column(name = "TOTAL_PACKAGE_QUANTITY")
    public Integer getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    public void setTotalPackageQuantity(Integer totalPackageQuantity) {
        this.totalPackageQuantity = totalPackageQuantity;
    }

    private Double totalVolume;

    @Basic
    @javax.persistence.Column(name = "TOTAL_VOLUME")
    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    private Double totalWeight;

    @Basic
    @javax.persistence.Column(name = "TOTAL_WEIGHT")
    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    private Double receivableFreightAmount;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_FREIGHT_AMOUNT")
    public Double getReceivableFreightAmount() {
        return receivableFreightAmount;
    }

    public void setReceivableFreightAmount(Double receivableFreightAmount) {
        this.receivableFreightAmount = receivableFreightAmount;
    }

    private Double receivablePickupfeeAmount;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_PICKUPFEE_AMOUNT")
    public Double getReceivablePickupfeeAmount() {
        return receivablePickupfeeAmount;
    }

    public void setReceivablePickupfeeAmount(Double receivablePickupfeeAmount) {
        this.receivablePickupfeeAmount = receivablePickupfeeAmount;
    }

    private Double receivableDeliveryAmount;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_DELIVERY_AMOUNT")
    public Double getReceivableDeliveryAmount() {
        return receivableDeliveryAmount;
    }

    public void setReceivableDeliveryAmount(Double receivableDeliveryAmount) {
        this.receivableDeliveryAmount = receivableDeliveryAmount;
    }

    private Double receivableOtherAmount;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_OTHER_AMOUNT")
    public Double getReceivableOtherAmount() {
        return receivableOtherAmount;
    }

    public void setReceivableOtherAmount(Double receivableOtherAmount) {
        this.receivableOtherAmount = receivableOtherAmount;
    }

    private Double receivablePackingAmount;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_PACKING_AMOUNT")
    public Double getReceivablePackingAmount() {
        return receivablePackingAmount;
    }

    public void setReceivablePackingAmount(Double receivablePackingAmount) {
        this.receivablePackingAmount = receivablePackingAmount;
    }

    private Double receivablePremiumAmount;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_PREMIUM_AMOUNT")
    public Double getReceivablePremiumAmount() {
        return receivablePremiumAmount;
    }

    public void setReceivablePremiumAmount(Double receivablePremiumAmount) {
        this.receivablePremiumAmount = receivablePremiumAmount;
    }

    private Double totalAmount;

    @Basic
    @javax.persistence.Column(name = "TOTAL_AMOUNT")
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
