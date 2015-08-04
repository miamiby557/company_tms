package com.lnet.tms.service;

import com.lnet.tms.model.sys.SysOrganization;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.sys.SysOrganizationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class IdentityUtils {

    @Autowired
    private static SysOrganizationService sysOrganizationService;

    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }

    public static SysUser getCurrentUser()
    {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static SysOrganization getCurrentBranch()
    {
        return sysOrganizationService.get(getCurrentUser().getBranchId());
    }
}
