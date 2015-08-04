package com.lnet.tms.model.crm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CRM_CLIENT_PRODUCT_TYPE", schema = "LNET_TMS", catalog = "")
public class CrmClientProductType {
    private UUID clientProductTypeId;
    private UUID clientId;
    private Integer value;
    private String name;
    private String remark;

    @Id
    @Column(name = "CLIENT_PRODUCT_TYPE_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getClientProductTypeId() {
        return clientProductTypeId;
    }

    public void setClientProductTypeId(UUID clientProductTypeId) {
        this.clientProductTypeId = clientProductTypeId;
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
    @Column(name = "VALUE")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
