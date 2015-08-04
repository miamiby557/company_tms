package com.lnet.tms.model.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SYS_ORGANIZATION", schema = "LNET_TMS", catalog = "")
public class SysOrganization {
    private UUID organizationId;
    private String code;
    private String name;
    private String namePinyin;
    private Integer type;
    private String city;
    private UUID cityId;
    private String remark;
    private Boolean isActive;
    private UUID superiorOrganizationId;
    private SysOrganization superiorOrganization;
    private Set<SysOrganization> subOrganizations = new HashSet<>();

    @Id
    @Column(name = "ORGANIZATION_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "CODE", nullable = false, insertable = true, updatable = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "NAME_PINYIN", nullable = true, insertable = true, updatable = true)
    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    @Basic
    @Column(name = "TYPE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "CITY", nullable = true, insertable = true, updatable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "IS_ACTIVE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "SUPERIOR_ORGANIZATION_ID", insertable = false, updatable = false)
    public UUID getSuperiorOrganizationId() {
        return superiorOrganizationId;
    }


    public void setSuperiorOrganizationId(UUID superiorOrganizationId) {
        this.superiorOrganizationId = superiorOrganizationId;
    }


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERIOR_ORGANIZATION_ID")
    @JsonIgnore
    public SysOrganization getSuperiorOrganization() {
        return superiorOrganization;
    }

    public void setSuperiorOrganization(SysOrganization superiorOrganization) {
        this.superiorOrganization = superiorOrganization;
    }

    @OneToMany(mappedBy = "superiorOrganization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<SysOrganization> getSubOrganizations() {
        return subOrganizations;
    }

    public void setSubOrganizations(Set<SysOrganization> subOrganizations) {
        this.subOrganizations = subOrganizations;
    }
}
