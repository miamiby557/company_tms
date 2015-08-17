package com.lnet.tms.model.crm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by develop on 2015/8/5.
 */
@Entity
@Table(name = "CRM_CLIENT_SENDER", schema = "LNET_TMS", catalog = "")
public class CrmClientSender {
    private UUID clientSenderId;
    private UUID clientId;
    private UUID addressId;

    @Id
    @Column(name = "CLIENT_SENDER_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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
}

