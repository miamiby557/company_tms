package com.lnet.tms.model.sys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@XmlRootElement(name = "SysUser")
@Entity
@Table(name = "SYS_USER", schema = "LNET_TMS", catalog = "")
public class SysUser implements Serializable{
    private UUID userId;
    private String username;
    private String password;
    private String email;
    private UUID branchId;
    private UUID siteId;
    private UUID departmentId;
    private String employeeNumber;
    private String fullName;
    private String fullNamePinyin;
    private Boolean isActive;
    private Boolean isAllowLogin;
    private String remark;
    private Boolean isSystemDefined;
    private Set<SysRole> sysRoles = new HashSet<>();

    public SysUser() {
    }

    public SysUser(String username,String fullName, String password, Boolean isActive, Boolean isAllowLogin) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.isActive = isActive;
        this.isAllowLogin = isAllowLogin;
    }

    @Id
    @Column(name = "USER_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "site_ID", nullable = false, insertable = true, updatable = true)
    public UUID getSiteId() {
        return siteId;
    }

    public void setSiteId(UUID siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, insertable = true, updatable = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, insertable = true, updatable = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, insertable = true, updatable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "BRANCH_ID", nullable = true, insertable = true, updatable = true)
    public UUID getBranchId() {
        return branchId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }

    @Basic
    @Column(name = "DEPARTMENT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "EMPLOYEE_NUMBER", nullable = true, insertable = true, updatable = true)
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Basic
    @Column(name = "FULL_NAME", nullable = true, insertable = true, updatable = true)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "FULL_NAME_PINYIN", nullable = true, insertable = true, updatable = true)
    public String getFullNamePinyin() {
        return fullNamePinyin;
    }

    public void setFullNamePinyin(String fullNamePinyin) {
        this.fullNamePinyin = fullNamePinyin;
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
    @Column(name = "IS_ALLOW_LOGIN", nullable = true, insertable = true, updatable = true, precision = 0)
    public Boolean getIsAllowLogin() {
        return isAllowLogin;
    }

    public void setIsAllowLogin(Boolean isAllowLogin) {
        this.isAllowLogin = isAllowLogin;
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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "SYS_USER_ROLE", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    public Set<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(Set<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }



}
