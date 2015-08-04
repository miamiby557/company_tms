package com.lnet.tms.model.otd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "OTD_CARRIER_ORDER_DETAIL_VIEW", schema = "LNET_TMS", catalog = "")
public class OtdCarrierOrderDetailView {
    private UUID carrierOrderDetailId;
    private UUID carrierOrderId;
    private UUID transportOrderId;
    private Integer confirmedItemQuantity;
    private Integer confirmedPackageQuantity;
    private Double confirmedVolume;
    private Double confirmedWeight;
    private String clientOrderNumber;
    private String clientName;
    private Integer receiptPageNumber;
    private Integer wrapType;
    private String receiveCompany;
    private String receiveMan;
    private String destCity;

    private OtdCarrierOrder otdCarrierOrder;
    @Id
    @Column(name = "CARRIER_ORDER_DETAIL_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierOrderDetailId() {
        return carrierOrderDetailId;
    }

    public void setCarrierOrderDetailId(UUID carrierOrderDetailId) {
        this.carrierOrderDetailId = carrierOrderDetailId;
    }

    @Basic
    @Column(name = "CARRIER_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getCarrierOrderId() {
        return carrierOrderId;
    }

    public void setCarrierOrderId(UUID carrierOrderId) {
        this.carrierOrderId = carrierOrderId;
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
    @javax.persistence.Column(name = "WRAP_TYPE", nullable = true, insertable = true, updatable = true)
    public Integer getWrapType() {
        return wrapType;
    }

    public void setWrapType(Integer wrapType) {
        this.wrapType = wrapType;
    }
    @Basic
    @Column(name = "TRANSPORT_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    @Basic
    @Column(name = "CONFIRMED_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedItemQuantity() {
        return confirmedItemQuantity;
    }

    public void setConfirmedItemQuantity(Integer confirmedItemQuantity) {
        this.confirmedItemQuantity = confirmedItemQuantity;
    }

    @Basic
    @Column(name = "CONFIRMED_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedPackageQuantity() {
        return confirmedPackageQuantity;
    }

    public void setConfirmedPackageQuantity(Integer confirmedPackageQuantity) {
        this.confirmedPackageQuantity = confirmedPackageQuantity;
    }

    @Basic
    @Column(name = "CONFIRMED_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getConfirmedVolume() {
        return confirmedVolume;
    }

    public void setConfirmedVolume(Double confirmedVolume) {
        this.confirmedVolume = confirmedVolume;
    }

    @Basic
    @Column(name = "CONFIRMED_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getConfirmedWeight() {
        return confirmedWeight;
    }

    public void setConfirmedWeight(Double confirmedWeight) {
        this.confirmedWeight = confirmedWeight;
    }

    @Basic
    @Column(name = "CLIENT_ORDER_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getClientOrderNumber() {
        return clientOrderNumber;
    }

    public void setClientOrderNumber(String clientOrderNumber) {
        this.clientOrderNumber = clientOrderNumber;
    }

    @Basic
    @Column(name = "CLIENT_NAME", nullable = true, insertable = true, updatable = true)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /*@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CARRIER_ORDER_ID", nullable = false)
    @JsonIgnore
    public OtdCarrierOrder getOtdCarrierOrder() {
        return otdCarrierOrder;
    }
    @JsonProperty
    public void setOtdCarrierOrder(OtdCarrierOrder otdCarrierOrder) {
        this.otdCarrierOrder = otdCarrierOrder;
    }*/

    @Basic
    @javax.persistence.Column(name = "DEST_CITY", nullable = true, insertable = true, updatable = true)
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVE_COMPANY", nullable = true, insertable = true, updatable = true)
    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    @Basic
    @javax.persistence.Column(name = "RECEIVE_MAN", nullable = true, insertable = true, updatable = true)
    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }
}
