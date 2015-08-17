package com.lnet.tms.model.base;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Administrator on 2015/8/11.
 */
@Entity
@Table(name = "CRM_RECEIVER_ADDRESS_VIEW", schema = "LNET_TMS", catalog = "")
public class CrmReceiverAddressView {
    private Integer addressType;
    private String contactPhone;
    private String contactMan;
    private UUID region;
    private String address;
    private UUID clientReceiverId;
    private UUID clientId;
    private UUID addressId;
    private UUID regionId;
    private String name;
    private String namePinyin;

    @Basic
    @Column(name = "ADDRESS_TYPE")
    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    @Basic
    @Column(name = "CONTACT_PHONE")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "CONTACT_MAN")
    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    @Basic
    @Column(name = "REGION")
    public UUID getRegion() {
        return region;
    }

    public void setRegion(UUID region) {
        this.region = region;
    }

    @Basic
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "CLIENT_RECEIVER_ID")
    public UUID getClientReceiverId() {
        return clientReceiverId;
    }

    public void setClientReceiverId(UUID clientReceiverId) {
        this.clientReceiverId = clientReceiverId;
    }

    @Basic
    @Column(name = "CLIENT_ID")
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Id
    @Column(name = "ADDRESS_ID")
    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "REGION_ID")
    public UUID getRegionId() {
        return regionId;
    }

    public void setRegionId(UUID regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "NAME_PINYIN")
    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }
}
