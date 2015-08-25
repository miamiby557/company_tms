package com.lnet.tms.rest.restUtil;

import java.util.UUID;

/**
 * Created by Administrator on 2015/8/24.
 */
public class AppLoginReturnInfo {

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
    private Long nextTime;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getBranchId() {
        return branchId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }

    public UUID getSiteId() {
        return siteId;
    }

    public void setSiteId(UUID siteId) {
        this.siteId = siteId;
    }

    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNamePinyin() {
        return fullNamePinyin;
    }

    public void setFullNamePinyin(String fullNamePinyin) {
        this.fullNamePinyin = fullNamePinyin;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsAllowLogin() {
        return isAllowLogin;
    }

    public void setIsAllowLogin(Boolean isAllowLogin) {
        this.isAllowLogin = isAllowLogin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getIsSystemDefined() {
        return isSystemDefined;
    }

    public void setIsSystemDefined(Boolean isSystemDefined) {
        this.isSystemDefined = isSystemDefined;
    }

    public Long getNextTime() {
        return nextTime;
    }

    public void setNextTime(Long nextTime) {
        this.nextTime = nextTime;
    }
}
