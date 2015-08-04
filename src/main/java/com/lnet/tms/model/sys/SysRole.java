package com.lnet.tms.model.sys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by admin on 2015/5/13.
 */
@Entity
@Table(name = "SYS_ROLE", schema = "LNET_TMS", catalog = "")
public class SysRole implements Serializable{
    private UUID roleId;
    private String code;
    private String name;
    private Boolean isActive;
    private String remark;
    private Boolean isSystemDefined;

    private Set<SysUser> sysUsers = new HashSet<>();

    private Set<SysFunction> sysFunctions = new HashSet<>();

    public SysRole() {
    }

    public SysRole(String code, String name, Boolean isActive) {
        this.code = code;
        this.name = name;
        this.isActive = isActive;
    }

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(name = "SYS_ROLE_FUNCTION", joinColumns = {@JoinColumn(name = "ROLE_ID")}, inverseJoinColumns = {@JoinColumn(name = "FUNCTION_ID")})
    public Set<SysFunction> getSysFunctions() {
        return sysFunctions;
    }

    public void setSysFunctions(Set<SysFunction> sysFunctions) {
        this.sysFunctions = sysFunctions;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sysRoles")
    public Set<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(Set<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    @Id
    @Column(name = "ROLE_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
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
    @Column(name = "IS_ACTIVE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
    @Column(name = "IS_SYSTEM_DEFINED", nullable = true, insertable = true, updatable = true, precision = 0)
    public Boolean getIsSystemDefined() {
        return isSystemDefined;
    }

    public void setIsSystemDefined(Boolean isSystemDefined) {
        this.isSystemDefined = isSystemDefined;
    }

}
