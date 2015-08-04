package com.lnet.tms.model.rpt;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by admin on 2015/7/24.
 */
@Entity
@javax.persistence.Table(name = "RPT_VIEW_PRODUCT_TRACKING", schema = "LNET_TMS", catalog = "")
public class RptViewProductTracking {
    private String logId;

    @Id
    @javax.persistence.Column(name = "LOG_ID")
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    private String clientOrderNumber;

    @Basic
    @javax.persistence.Column(name = "CLIENT_ORDER_NUMBER")
    public String getClientOrderNumber() {
        return clientOrderNumber;
    }

    public void setClientOrderNumber(String clientOrderNumber) {
        this.clientOrderNumber = clientOrderNumber;
    }

    private String lnetOrderNumber;

    @Basic
    @javax.persistence.Column(name = "LNET_ORDER_NUMBER")
    public String getLnetOrderNumber() {
        return lnetOrderNumber;
    }

    public void setLnetOrderNumber(String lnetOrderNumber) {
        this.lnetOrderNumber = lnetOrderNumber;
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

    private String clientName;

    @Basic
    @javax.persistence.Column(name = "CLIENT_NAME")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private UUID clientId;

    @Basic
    @javax.persistence.Column(name = "CLIENT_ID")
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
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

    private Timestamp expectedDate;

    @Basic
    @javax.persistence.Column(name = "EXPECTED_DATE")
    public Timestamp getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Timestamp expectedDate) {
        this.expectedDate = expectedDate;
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

    private Integer urgencyLevel;

    @Basic
    @javax.persistence.Column(name = "URGENCY_LEVEL")
    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(Integer urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
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

    private String signMan;

    @Basic
    @javax.persistence.Column(name = "SIGN_MAN")
    public String getSignMan() {
        return signMan;
    }

    public void setSignMan(String signMan) {
        this.signMan = signMan;
    }

    private String operationContent;

    @Basic
    @javax.persistence.Column(name = "OPERATION_CONTENT")
    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
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

    private String handoverTypeName;

    @Basic
    @javax.persistence.Column(name = "HANDOVER_TYPE_NAME")
    public String getHandoverTypeName() {
        return handoverTypeName;
    }

    public void setHandoverTypeName(String handoverTypeName) {
        this.handoverTypeName = handoverTypeName;
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

    private String receivePhone;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_PHONE")
    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    private String receiveAddress;

    @Basic
    @javax.persistence.Column(name = "RECEIVE_ADDRESS")
    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
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

    private String carrierName;

    @Basic
    @javax.persistence.Column(name = "CARRIER_NAME")
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    private UUID carrierId;

    @Basic
    @javax.persistence.Column(name = "CARRIER_ID")
    public UUID getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(UUID carrierId) {
        this.carrierId = carrierId;
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

    private String goodsCode;

    @Basic
    @javax.persistence.Column(name = "GOODS_CODE")
    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    private String goodsName;

    @Basic
    @javax.persistence.Column(name = "GOODS_NAME")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    private String goodsType;

    @Basic
    @javax.persistence.Column(name = "GOODS_TYPE")
    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    private String viceClientOrderNumber;

    @Basic
    @javax.persistence.Column(name = "VICE_CLIENT_ORDER_NUMBER")
    public String getViceClientOrderNumber() {
        return viceClientOrderNumber;
    }

    public void setViceClientOrderNumber(String viceClientOrderNumber) {
        this.viceClientOrderNumber = viceClientOrderNumber;
    }

    private String marketClientNumber;

    @Basic
    @javax.persistence.Column(name = "MARKET_CLIENT_NUMBER")
    public String getMarketClientNumber() {
        return marketClientNumber;
    }

    public void setMarketClientNumber(String marketClientNumber) {
        this.marketClientNumber = marketClientNumber;
    }

    private Timestamp operationDate;

    @Basic
    @javax.persistence.Column(name = "OPERATION_DATE")
    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    private Double isAbnormal;

    @Basic
    @javax.persistence.Column(name = "IS_ABNORMAL")
    public Double getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Double isAbnormal) {
        this.isAbnormal = isAbnormal;
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
