package com.lnet.tms.model.otd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Entity
@Table(name = "OTD_CARRIER_ORDER_DETAIL", schema = "LNET_TMS", catalog = "")
public class OtdCarrierOrderDetail {
    private UUID carrierOrderDetailId;
    private UUID carrierOrderId;
    private UUID transportOrderId;
    private Integer confirmedItemQuantity;
    private Integer confirmedPackageQuantity;
    private Double confirmedVolume;
    private Double confirmedWeight;

    private OtdCarrierOrder otdCarrierOrder;

    @Id
    @Column(name = "CARRIER_ORDER_DETAIL_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getCarrierOrderDetailId() {
        return carrierOrderDetailId;
    }

    public void setCarrierOrderDetailId(UUID carrierOrderDetailId) {
        this.carrierOrderDetailId = carrierOrderDetailId;
    }

    @Basic
    @Column(name = "CARRIER_ORDER_ID", insertable = false, updatable = false)
    public UUID getCarrierOrderId() {
        return carrierOrderId;
    }

    public void setCarrierOrderId(UUID carrierOrderId) {
        this.carrierOrderId = carrierOrderId;
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
    @javax.persistence.Column(name = "CONFIRMED_ITEM_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedItemQuantity() {
        return confirmedItemQuantity;
    }

    public void setConfirmedItemQuantity(Integer confirmedItemQuantity) {
        this.confirmedItemQuantity = confirmedItemQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_PACKAGE_QUANTITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getConfirmedPackageQuantity() {
        return confirmedPackageQuantity;
    }

    public void setConfirmedPackageQuantity(Integer confirmedPackageQuantity) {
        this.confirmedPackageQuantity = confirmedPackageQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_VOLUME", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getConfirmedVolume() {
        return confirmedVolume;
    }

    public void setConfirmedVolume(Double confirmedVolume) {
        this.confirmedVolume = confirmedVolume;
    }

    @Basic
    @javax.persistence.Column(name = "CONFIRMED_WEIGHT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getConfirmedWeight() {
        return confirmedWeight;
    }

    public void setConfirmedWeight(Double confirmedWeight) {
        this.confirmedWeight = confirmedWeight;
    }


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CARRIER_ORDER_ID", nullable = false)
    @JsonIgnore
    public OtdCarrierOrder getOtdCarrierOrder() {
        return otdCarrierOrder;
    }
    @JsonProperty
    public void setOtdCarrierOrder(OtdCarrierOrder otdCarrierOrder) {
        this.otdCarrierOrder = otdCarrierOrder;
    }
}
