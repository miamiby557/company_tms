package com.lnet.tms.model.base;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Entity
@Table(name = "BASE_REGION_VIEW", schema = "LNET_TMS", catalog = "")
public class BaseRegionView {
    private UUID regionId;
    private String code;
    private String name;
    private String namePinyin;
    private UUID superiorRegionId;
    private Integer regionTypeId;
    private String regionTypeName;
    private String superiorRegionName;

    @Id
    @Column(name = "REGION_ID", nullable = false, insertable = true, updatable = true)
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
    @Column(name = "SUPERIOR_REGION_ID", nullable = true, insertable = true, updatable = true)
    public UUID getSuperiorRegionId() {
        return superiorRegionId;
    }

    public void setSuperiorRegionId(UUID superiorRegionId) {
        this.superiorRegionId = superiorRegionId;
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
    @Column(name = "REGION_TYPE_NAME", nullable = true, insertable = true, updatable = true)
    public String getRegionTypeName() {
        return regionTypeName;
    }

    public void setRegionTypeName(String regionTypeName) {
        this.regionTypeName = regionTypeName;
    }

    @Basic
    @Column(name = "SUPERIOR_REGION_NAME", nullable = true, insertable = true, updatable = true)
    public String getSuperiorRegionName() {
        return superiorRegionName;
    }

    public void setSuperiorRegionName(String superiorRegionName) {
        this.superiorRegionName = superiorRegionName;
    }
}
