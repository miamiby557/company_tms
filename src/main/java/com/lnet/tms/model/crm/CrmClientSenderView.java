package com.lnet.tms.model.crm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by develop on 2015/8/11.
 */
@Entity
@Table(name = "CRM_CLIENT_SENDER_VIEW", schema = "LNET_TMS", catalog = "")
public class CrmClientSenderView {
    private UUID clientSenderId;
    private UUID clientId;
    private UUID addressId;
    private BigDecimal addressType;
    private String contactMan;
    private String contactPhone;
    private UUID region;
    private String address;
    private String name;

    @Id
    @javax.persistence.Column(name = "CLIENT_SENDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientSenderId() {
        return clientSenderId;
    }

    public void setClientSenderId(UUID clientSenderId) {
        this.clientSenderId = clientSenderId;
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
    @Column(name = "ADDRESS_ID")
    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "ADDRESS_TYPE")
    public BigDecimal getAddressType() {
        return addressType;
    }

    public void setAddressType(BigDecimal addressType) {
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
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
