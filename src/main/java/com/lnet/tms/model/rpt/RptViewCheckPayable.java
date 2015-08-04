package com.lnet.tms.model.rpt;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by admin on 2015/6/8.
 */
@Entity
@javax.persistence.Table(name = "RPT_VIEW_CHECK_PAYABLE", schema = "LNET_TMS", catalog = "")
public class RptViewCheckPayable {
    private UUID carrierId;

    @Basic
    @javax.persistence.Column(name = "CARRIER_ID")
    public UUID getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(UUID carrierId) {
        this.carrierId = carrierId;
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

    private String carrierOrderNumber;

    @Id
    @javax.persistence.Column(name = "CARRIER_ORDER_NUMBER")
    public String getCarrierOrderNumber() {
        return carrierOrderNumber;
    }

    public void setCarrierOrderNumber(String carrierOrderNumber) {
        this.carrierOrderNumber = carrierOrderNumber;
    }

    private Timestamp createDate;

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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

    private String goodsName;

    @Basic
    @javax.persistence.Column(name = "GOODS_NAME")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    private Double payableCartfee;

    @Basic
    @javax.persistence.Column(name = "PAYABLE_CARTFEE")
    public Double getPayableCartfee() {
        return payableCartfee;
    }

    public void setPayableCartfee(Double payableCartfee) {
        this.payableCartfee = payableCartfee;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RptViewCheckPayable that = (RptViewCheckPayable) o;

        if (carrierId != null ? !carrierId.equals(that.carrierId) : that.carrierId != null) return false;
        if (carrierOrderNumber != null ? !carrierOrderNumber.equals(that.carrierOrderNumber) : that.carrierOrderNumber != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (destCity != null ? !destCity.equals(that.destCity) : that.destCity != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (payableCartfee != null ? !payableCartfee.equals(that.payableCartfee) : that.payableCartfee != null)
            return false;
        if (payableDelivery != null ? !payableDelivery.equals(that.payableDelivery) : that.payableDelivery != null)
            return false;
        if (payableFreight != null ? !payableFreight.equals(that.payableFreight) : that.payableFreight != null)
            return false;
        if (payableOther != null ? !payableOther.equals(that.payableOther) : that.payableOther != null) return false;
        if (payablePickupfee != null ? !payablePickupfee.equals(that.payablePickupfee) : that.payablePickupfee != null)
            return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (totalItemQuantity != null ? !totalItemQuantity.equals(that.totalItemQuantity) : that.totalItemQuantity != null)
            return false;
        if (totalPackageQuantity != null ? !totalPackageQuantity.equals(that.totalPackageQuantity) : that.totalPackageQuantity != null)
            return false;
        if (totalVolume != null ? !totalVolume.equals(that.totalVolume) : that.totalVolume != null) return false;
        if (totalWeight != null ? !totalWeight.equals(that.totalWeight) : that.totalWeight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carrierId != null ? carrierId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (carrierOrderNumber != null ? carrierOrderNumber.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (destCity != null ? destCity.hashCode() : 0);
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (totalItemQuantity != null ? totalItemQuantity.hashCode() : 0);
        result = 31 * result + (totalPackageQuantity != null ? totalPackageQuantity.hashCode() : 0);
        result = 31 * result + (totalVolume != null ? totalVolume.hashCode() : 0);
        result = 31 * result + (totalWeight != null ? totalWeight.hashCode() : 0);
        result = 31 * result + (payableFreight != null ? payableFreight.hashCode() : 0);
        result = 31 * result + (payablePickupfee != null ? payablePickupfee.hashCode() : 0);
        result = 31 * result + (payableDelivery != null ? payableDelivery.hashCode() : 0);
        result = 31 * result + (payableOther != null ? payableOther.hashCode() : 0);
        result = 31 * result + (payableCartfee != null ? payableCartfee.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        return result;
    }
}
