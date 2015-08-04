package com.lnet.tms.model.sys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SYS_LIST", schema = "LNET_TMS", catalog = "")
public class SysList {
    private UUID listId;
    private String code;
    private String name;
    private String remark;
    private Set<SysListItem> items = new HashSet<>();

    @Id
    @Column(name = "LIST_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getListId() {
        return listId;
    }

    public void setListId(UUID listId) {
        this.listId = listId;
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
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(mappedBy = "sysList", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("value asc")
    public Set<SysListItem> getItems() {
        return items;
    }

    public void setItems(Set<SysListItem> items) {
        this.items = items;
    }
}
