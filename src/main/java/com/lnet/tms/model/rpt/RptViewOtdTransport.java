package com.lnet.tms.model.rpt;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;
@Entity
@javax.persistence.Table(name = "RPT_VIEW_OTD_TRANSPORT", schema = "LNET_TMS", catalog = "")
public class RptViewOtdTransport {
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

    @Id
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

    private Integer handoverType;

    @Basic
    @javax.persistence.Column(name = "HANDOVER_TYPE")
    public Integer getHandoverType() {
        return handoverType;
    }

    public void setHandoverType(Integer handoverType) {
        this.handoverType = handoverType;
    }

    private Integer confirmedItemQuantity;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_ITEM_QUANTITY")
    public Integer getConfirmedItemQuantity() {
        return confirmedItemQuantity;
    }

    public void setConfirmedItemQuantity(Integer confirmedItemQuantity) {
        this.confirmedItemQuantity = confirmedItemQuantity;
    }

    private Integer confirmedPackageQuantity;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_PACKAGE_QUANTITY")
    public Integer getConfirmedPackageQuantity() {
        return confirmedPackageQuantity;
    }

    public void setConfirmedPackageQuantity(Integer confirmedPackageQuantity) {
        this.confirmedPackageQuantity = confirmedPackageQuantity;
    }

    private Double confirmedVolume;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_VOLUME")
    public Double getConfirmedVolume() {
        return confirmedVolume;
    }

    public void setConfirmedVolume(Double confirmedVolume) {
        this.confirmedVolume = confirmedVolume;
    }

    private Double confirmedWeight;

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_WEIGHT")
    public Double getConfirmedWeight() {
        return confirmedWeight;
    }

    public void setConfirmedWeight(Double confirmedWeight) {
        this.confirmedWeight = confirmedWeight;
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

    private String carrierCalculateTypeName;

    @Basic
    @javax.persistence.Column(name = "CARRIER_CALCULATE_TYPE_NAME")
    public String getCarrierCalculateTypeName() {
        return carrierCalculateTypeName;
    }

    public void setCarrierCalculateTypeName(String carrierCalculateTypeName) {
        this.carrierCalculateTypeName = carrierCalculateTypeName;
    }

    private Timestamp clientSentDate;

    @Basic
    @javax.persistence.Column(name = "CLIENT_SENT_DATE")
    public Timestamp getClientSentDate() {
        return clientSentDate;
    }

    public void setClientSentDate(Timestamp clientSentDate) {
        this.clientSentDate = clientSentDate;
    }

    private String sendNumber;

    @Basic
    @javax.persistence.Column(name = "SEND_NUMBER")
    public String getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber;
    }

    private Integer sendQuantity;

    @Basic
    @javax.persistence.Column(name = "SEND_QUANTITY")
    public Integer getSendQuantity() {
        return sendQuantity;
    }

    public void setSendQuantity(Integer sendQuantity) {
        this.sendQuantity = sendQuantity;
    }

    private Integer sendPackageQuantity;

    @Basic
    @javax.persistence.Column(name = "SEND_PACKAGE_QUANTITY")
    public Integer getSendPackageQuantity() {
        return sendPackageQuantity;
    }

    public void setSendPackageQuantity(Integer sendPackageQuantity) {
        this.sendPackageQuantity = sendPackageQuantity;
    }

    private Double sendVolume;

    @Basic
    @javax.persistence.Column(name = "SEND_VOLUME")
    public Double getSendVolume() {
        return sendVolume;
    }

    public void setSendVolume(Double sendVolume) {
        this.sendVolume = sendVolume;
    }

    private Double sendWeight;

    @Basic
    @javax.persistence.Column(name = "SEND_WEIGHT")
    public Double getSendWeight() {
        return sendWeight;
    }

    public void setSendWeight(Double sendWeight) {
        this.sendWeight = sendWeight;
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

    private Double sendAmount;

    @Basic
    @javax.persistence.Column(name = "SEND_AMOUNT")
    public Double getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(Double sendAmount) {
        this.sendAmount = sendAmount;
    }

    private Double crossProfit;

    @Basic
    @javax.persistence.Column(name = "CROSS_PROFIT")
    public Double getCrossProfit() {
        return crossProfit;
    }

    public void setCrossProfit(Double crossProfit) {
        this.crossProfit = crossProfit;
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

    private String destCity;

    @Basic
    @javax.persistence.Column(name = "DEST_CITY")
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
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

    private Double receivableFreight;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_FREIGHT")
    public Double getReceivableFreight() {
        return receivableFreight;
    }

    public void setReceivableFreight(Double receivableFreight) {
        this.receivableFreight = receivableFreight;
    }

    private Double receivablePickupfee;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_PICKUPFEE")
    public Double getReceivablePickupfee() {
        return receivablePickupfee;
    }

    public void setReceivablePickupfee(Double receivablePickupfee) {
        this.receivablePickupfee = receivablePickupfee;
    }

    private Double receivableDelivery;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_DELIVERY")
    public Double getReceivableDelivery() {
        return receivableDelivery;
    }

    public void setReceivableDelivery(Double receivableDelivery) {
        this.receivableDelivery = receivableDelivery;
    }

    private Double receivableOther;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_OTHER")
    public Double getReceivableOther() {
        return receivableOther;
    }

    public void setReceivableOther(Double receivableOther) {
        this.receivableOther = receivableOther;
    }

    private Double receivableCartfee;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_CARTFEE")
    public Double getReceivableCartfee() {
        return receivableCartfee;
    }

    public void setReceivableCartfee(Double receivableCartfee) {
        this.receivableCartfee = receivableCartfee;
    }

    private Double receivableUpstairs;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_UPSTAIRS")
    public Double getReceivableUpstairs() {
        return receivableUpstairs;
    }

    public void setReceivableUpstairs(Double receivableUpstairs) {
        this.receivableUpstairs = receivableUpstairs;
    }

    private Double receivablePremium;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_PREMIUM")
    public Double getReceivablePremium() {
        return receivablePremium;
    }

    public void setReceivablePremium(Double receivablePremium) {
        this.receivablePremium = receivablePremium;
    }

    private Double payableFreight;

    @Basic
    @javax.persistence.Column(name = "PAYABLE_FREIGHT")
    public Double getPayableFreight() {
        return payableFreight;
    }

    public void setPayableFreight(Double payableFreight) {
        this.payableFreight = payableFreight;
    }

    private Double payablePickupfee;

    @Basic
    @javax.persistence.Column(name = "PAYABLE_PICKUPFEE")
    public Double getPayablePickupfee() {
        return payablePickupfee;
    }

    public void setPayablePickupfee(Double payablePickupfee) {
        this.payablePickupfee = payablePickupfee;
    }

    private Double payableDelivery;

    @Basic
    @javax.persistence.Column(name = "PAYABLE_DELIVERY")
    public Double getPayableDelivery() {
        return payableDelivery;
    }

    public void setPayableDelivery(Double payableDelivery) {
        this.payableDelivery = payableDelivery;
    }

    private Double payableOther;

    @Basic
    @javax.persistence.Column(name = "PAYABLE_OTHER")
    public Double getPayableOther() {
        return payableOther;
    }

    public void setPayableOther(Double payableOther) {
        this.payableOther = payableOther;
    }

    private Double payableUpstairs;

    @Basic
    @javax.persistence.Column(name = "PAYABLE_UPSTAIRS")
    public Double getPayableUpstairs() {
        return payableUpstairs;
    }

    public void setPayableUpstairs(Double payableUpstairs) {
        this.payableUpstairs = payableUpstairs;
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

    private Double receivableUnitPrice;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_UNIT_PRICE")
    public Double getReceivableUnitPrice() {
        return receivableUnitPrice;
    }

    public void setReceivableUnitPrice(Double receivableUnitPrice) {
        this.receivableUnitPrice = receivableUnitPrice;
    }

    private Double receivableMinimumFee;

    @Basic
    @javax.persistence.Column(name = "RECEIVABLE_MINIMUM_FEE")
    public Double getReceivableMinimumFee() {
        return receivableMinimumFee;
    }

    public void setReceivableMinimumFee(Double receivableMinimumFee) {
        this.receivableMinimumFee = receivableMinimumFee;
    }

    private Double payableMinimumFee;

    @Basic
    @javax.persistence.Column(name = "PAYABLE_MINIMUM_FEE")
    public Double getPayableMinimumFee() {
        return payableMinimumFee;
    }

    public void setPayableMinimumFee(Double payableMinimumFee) {
        this.payableMinimumFee = payableMinimumFee;
    }

    private Double payableUnitPrice;

    @Basic
    @javax.persistence.Column(name = "PAYABLE_UNIT_PRICE")
    public Double getPayableUnitPrice() {
        return payableUnitPrice;
    }

    public void setPayableUnitPrice(Double payableUnitPrice) {
        this.payableUnitPrice = payableUnitPrice;
    }

    private Timestamp carrierSentDate;

    @Basic
    @javax.persistence.Column(name = "CARRIER_SENT_DATE")
    public Timestamp getCarrierSentDate() {
        return carrierSentDate;
    }

    public void setCarrierSentDate(Timestamp carrierSentDate) {
        this.carrierSentDate = carrierSentDate;
    }

    private String clientBillingCycle;

    @Basic
    @javax.persistence.Column(name = "CLIENT_BILLING_CYCLE")
    public String getClientBillingCycle() {
        return clientBillingCycle;
    }

    public void setClientBillingCycle(String clientBillingCycle) {
        this.clientBillingCycle = clientBillingCycle;
    }

    private String carrierBillingCycle;

    @Basic
    @javax.persistence.Column(name = "CARRIER_BILLING_CYCLE")
    public String getCarrierBillingCycle() {
        return carrierBillingCycle;
    }

    public void setCarrierBillingCycle(String carrierBillingCycle) {
        this.carrierBillingCycle = carrierBillingCycle;
    }

    private String transportOrderReportRemark;

    @Basic
    @javax.persistence.Column(name = "TRANSPORT_ORDER_REPORT_REMARK")
    public String getTransportOrderReportRemark() {
        return transportOrderReportRemark;
    }

    public void setTransportOrderReportRemark(String transportOrderReportRemark) {
        this.transportOrderReportRemark = transportOrderReportRemark;
    }

    private String carrierOrderReportRemark;

    @Basic
    @javax.persistence.Column(name = "CARRIER_ORDER_REPORT_REMARK")
    public String getCarrierOrderReportRemark() {
        return carrierOrderReportRemark;
    }

    public void setCarrierOrderReportRemark(String carrierOrderReportRemark) {
        this.carrierOrderReportRemark = carrierOrderReportRemark;
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

    private String goodsValue;

    @Basic
    @javax.persistence.Column(name = "GOODS_VALUE")
    public String getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue;
    }

    private String netMargin;

    @Basic
    @javax.persistence.Column(name = "NET_MARGIN")
    public String getNetMargin() {
        return netMargin;
    }

    public void setNetMargin(String netMargin) {
        this.netMargin = netMargin;
    }

    private String handlingCharge;

    @Basic
    @javax.persistence.Column(name = "HANDLING_CHARGE")
    public String getHandlingCharge() {
        return handlingCharge;
    }

    public void setHandlingCharge(String handlingCharge) {
        this.handlingCharge = handlingCharge;
    }

    private String carRental;

    @Basic
    @javax.persistence.Column(name = "CAR_RENTAL")
    public String getCarRental() {
        return carRental;
    }

    public void setCarRental(String carRental) {
        this.carRental = carRental;
    }
}
