package com.lnet.tms.service.sys;

import com.lnet.tms.dao.sys.SysUserDao;
import com.lnet.tms.model.sys.SysFunction;
import com.lnet.tms.model.sys.SysRole;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.utility.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class SysUserService extends CrudService<SysUser, UUID, SysUserDao> {

    @Autowired
    public void setBaseDao(SysUserDao dao) {
        super.setDao(dao);
    }

    @Transactional
    public SysUser getByUsername(String username) {
        SysUser sysUser= getDao().getByUsername(username);

        for (SysRole sysRole: sysUser.getSysRoles()){
            sysRole.getSysFunctions().size();
        }

        return sysUser;
    }



}
