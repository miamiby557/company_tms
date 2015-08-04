package com.lnet.tms.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "BASE_EXACCT", schema = "LNET_TMS", catalog = "")
public class BaseExacct {
    private UUID exacctId;
    private String code;
    private String name;
    private UUID superiorId;
    private Integer indexNumber;
    private String remark;
    private UUID createUserId;
    private Timestamp createDate;
    private UUID modifyUserId;
    private Timestamp modifyDate;

    private BaseExacct superiorExacct;
    private Set<BaseExacct> subExaccts = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERIOR_ID")
    @JsonIgnore
    public BaseExacct getSuperiorExacct() {
        return superiorExacct;
    }

    public void setSuperiorExacct(BaseExacct superiorExacct) {
        this.superiorExacct = superiorExacct;
    }
    @OneToMany(mappedBy = "superiorExacct", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<BaseExacct> getSubExaccts() {
        return subExaccts;
    }

    public void setSubExaccts(Set<BaseExacct> subExaccts) {
        this.subExaccts = subExaccts;
    }

    @Id
    @Column(name = "EXACCT_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getExacctId() {
        return exacctId;
    }

    public void setExacctId(UUID exacctId) {
        this.exacctId = exacctId;
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

    @Basic
    @Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "MODIFY_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(UUID modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "SUPERIOR_ID", nullable = true, insertable = false, updatable = false)
    public UUID getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(UUID superiorId) {
        this.superiorId = superiorId;
    }

    @Basic
    @Column(name = "INDEX_NUMBER", nullable = true, insertable = true, updatable = true)
    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }
}
