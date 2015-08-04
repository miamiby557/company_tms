package com.lnet.tms.service;

import com.lnet.tms.model.sys.SysFunction;
import com.lnet.tms.model.sys.SysRole;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.sys.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class TmsAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    SysUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("doGetAuthorizationInfo" + principals);
        SysUser sysUser = (SysUser) principals.fromRealm(getName()).iterator().next();
        if (sysUser != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole("SYS_USER");

            for(SysRole sysRole: sysUser.getSysRoles()){
                info.addRole(sysRole.getCode());
            }

            // 从数据库查询用户的功能授权
            for(SysRole sysRole:sysUser.getSysRoles()){
                Set<SysFunction> sysFunctions = sysRole.getSysFunctions();
                for(SysFunction sysFunction:sysFunctions){
                    info.addStringPermission(sysFunction.getCode());
                }
            }

            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo" + authenticationToken);
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        if (userName != null && !"".equals(userName)) {
            SysUser user = userService.getByUsername(userName);
            if (user != null)
                return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        return null;


    }
}