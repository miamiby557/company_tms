package com.lnet.tms.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "BASE_REGION", schema = "LNET_TMS", catalog = "")
public class BaseRegion {
    private UUID regionId;
    private String code;
    private String name;
    private String namePinyin;
    private Integer regionTypeId;
    private UUID superiorRegionId;
    private BaseRegion superiorRegion;
    private Set<BaseRegion> subRegions = new HashSet<>();

    @Id
    @Column(name = "REGION_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getRegionId() {
        return regionId;
    }

    public void setRegionId(UUID regionId) {
        this.regionId = regionId;
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
    @Column(name = "REGION_TYPE_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Integer getRegionTypeId() {
        return regionTypeId;
    }

    public void setRegionTypeId(Integer regionTypeId) {
        this.regionTypeId = regionTypeId;
    }

    @Basic
    @Column(name = "SUPERIOR_REGION_ID", insertable = false, updatable = false)
    public UUID getSuperiorRegionId() {
        return superiorRegionId;
    }

    public void setSuperiorRegionId(UUID superiorRegionId) {
        this.superiorRegionId = superiorRegionId;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERIOR_REGION_ID")
    @JsonIgnore
    public BaseRegion getSuperiorRegion() {
        return superiorRegion;
    }

    public void setSuperiorRegion(BaseRegion superiorRegion) {
        this.superiorRegion = superiorRegion;
    }

    @OrderBy("code asc")
    @OneToMany(mappedBy = "superiorRegion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<BaseRegion> getSubRegions() {
        return subRegions;
    }

    public void setSubRegions(Set<BaseRegion> subRegions) {
        this.subRegions = subRegions;
    }
}

