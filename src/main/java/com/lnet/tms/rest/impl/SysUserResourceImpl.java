package com.lnet.tms.rest.impl;

import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.rest.restUtil.AppLoginReturnInfo;
import com.lnet.tms.rest.restUtil.ServiceResult;
import com.lnet.tms.rest.restUtil.UpdatePwd;
import com.lnet.tms.rest.spi.SysUserResource;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.FormParam;
import java.util.UUID;


public class SysUserResourceImpl implements SysUserResource {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public ServiceResult getUserInfo(UUID id) {
        SysUser sysUser = sysUserService.get(id);
        ServiceResult result = new ServiceResult(sysUser);
        return result;
    }

    @Override
    public ServiceResult validate(@FormParam("username") String username, @FormParam("password") String password) {

        ServiceResult result = new ServiceResult();

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            SecurityUtils.getSubject().login(token);


            SysUser user = IdentityUtils.getCurrentUser();
            result.setContent(user);

        } catch (Exception e) {

            result.setSuccess(false);
            result.addMessage(e.getMessage());
        }

        return result;
    }

    @Override
    public ServiceResult login(SysUser user) {
        ServiceResult result = new ServiceResult();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(false);
            SecurityUtils.getSubject().login(token);
            SysUser sysUser = IdentityUtils.getCurrentUser();
            AppLoginReturnInfo info = new AppLoginReturnInfo();
            BeanUtils.copyProperties(sysUser,info);
            info.setNextTime(15*60*1000L);
            result.setContent(info);
        } catch (Exception e) {
            result.setSuccess(false);
            result.addMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public ServiceResult logout() {
        IdentityUtils.getSubject().logout();
        return new ServiceResult();
    }

    @Override
    public ServiceResult testService() {
        System.out.println("test");
        ServiceResult result = new ServiceResult(true);
        return result;
    }

    @Override
    public ServiceResult updatePwd(UpdatePwd updatePwd) {
        SysUser user = IdentityUtils.getCurrentUser();
        if(updatePwd.getUserId().equals(user.getUserId())){
            user.setPassword(updatePwd.getNewPwd());
            sysUserService.update(user);
            ServiceResult result = new ServiceResult(user);
            return result;
        }
        ServiceResult result = new ServiceResult(false);
        result.addMessage("修改不成功!");
        return result;
    }
}
