package com.lnet.tms.model.crm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by develop on 2015/8/1.
 */
@Entity
@Table(name = "CRM_CLIENT_PICKUP_ADDRESS", schema = "LNET_TMS", catalog = "")
public class CrmClientPickupAddress {
    private UUID clientPickupAddressId;
    private UUID clientId;
    private String name;
    private UUID regionId;
    private String streetAddress;
    private String contact;
    private String telephone;
    private Integer type;
    private String remark;

    @Id
    @Column(name = "CLIENT_PICKUP_ADDRESS_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getClientPickupAddressId() {
        return clientPickupAddressId;
    }

    public void setClientPickupAddressId(UUID clientPickupAddressId) {
        this.clientPickupAddressId = clientPickupAddressId;
    }

    @Basic
    @Column(name = "CLIENT_ID")
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
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
    @Column(name = "REGION_ID")
    public UUID getRegionId() {
        return regionId;
    }

    public void setRegionId(UUID regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "STREET_ADDRESS")
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Basic
    @Column(name = "CONTACT")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "TELEPHONE")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }}


