package com.lnet.tms.model.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/13.
 */
@Entity
@Table(name = "SYS_LIST_ITEM", schema = "LNET_TMS", catalog = "")
public class SysListItem {
    private UUID listItemId;
    private Integer value;
    private String name;
    private String remark;
    private Boolean isActive;
    private UUID listId;
    private SysList sysList;

    @Id
    @Column(name = "LIST_ITEM_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getListItemId() {
        return listItemId;
    }

    public void setListItemId(UUID listItemId) {
        this.listItemId = listItemId;
    }

    @Basic
    @Column(name = "VALUE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Basic
    @Column(name = "NAME", nullable = true, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "LIST_ID", insertable = false, updatable = false)
    public UUID getListId() {
        return listId;
    }

    public void setListId(UUID listId) {
        this.listId = listId;
    }
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "LIST_ID", nullable = false)
    @JsonIgnore
    public SysList getSysList() {
        return sysList;
    }
    @JsonProperty
    public void setSysList(SysList sysList) {
        this.sysList = sysList;
    }
}
