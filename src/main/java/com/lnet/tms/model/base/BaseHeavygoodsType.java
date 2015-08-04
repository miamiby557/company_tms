package com.lnet.tms.model.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "BASE_HEAVYGOODS_TYPE", schema = "LNET_TMS", catalog = "")
public class BaseHeavygoodsType {
    private UUID heavygoodsTypeId;
    private String name;
    private Integer value;
    private UUID clientId;
    private Double ratioMin;
    private Double ratioMax;
    private String remark;

    @Id
    @Column(name = "HEAVYGOODS_TYPE_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getHeavygoodsTypeId() {
        return heavygoodsTypeId;
    }

    public void setHeavygoodsTypeId(UUID heavygoodsTypeId) {
        this.heavygoodsTypeId = heavygoodsTypeId;
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
    @Column(name = "CLIENT_ID")
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "RATIO_MIN")
    public Double getRatioMin() {
        return ratioMin;
    }

    public void setRatioMin(Double ratioMin) {
        this.ratioMin = ratioMin;
    }

    @Basic
    @Column(name = "RATIO_MAX")
    public Double getRatioMax() {
        return ratioMax;
    }

    public void setRatioMax(Double ratioMax) {
        this.ratioMax = ratioMax;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "VALUE")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
