package com.lnet.tms.model.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Administrator on 2015/8/4.
 */
@Entity
@Table(name = "BASE_ADDRESS", schema = "LNET_TMS", catalog = "")
public class BaseAddress {
    private UUID addressId;
    private Integer addressType;
    private String contactMan;
    private String contactPhone;
    private String region;
    private String address;

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "ADDRESS_TYPE")
    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
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
    @Column(name = "CONTACT_PHONE")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "REGION")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
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

}
