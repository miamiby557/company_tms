package com.lnet.tms.model.crm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Administrator on 2015/8/1.
 */
@Entity
@Table(name = "CRM_CLIENT_DELIVER_ADDRESS", schema = "LNET_TMS", catalog = "")
public class CrmClientDeliverAddress {
    private UUID crmClientDeliverAddressId;
    private UUID clientId;
    private String name;
    private UUID regionId;
    private String streetAddress;
    private String contact;
    private String telephone;
    private Integer type;
    private String remark;

    @Id
    @Column(name = "CRM_CLIENT_DELIVER_ADDRESS_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getCrmClientDeliverAddressId() {
        return crmClientDeliverAddressId;
    }

    public void setCrmClientDeliverAddressId(UUID crmClientDeliverAddressId) {
        this.crmClientDeliverAddressId = crmClientDeliverAddressId;
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
    }

}
