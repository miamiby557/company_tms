package com.lnet.tms.dao.sys;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.utility.GetPinyinUtil;
import com.lnet.tms.utility.PasswordHash;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@Repository
public class SysUserDao extends CrudDao<SysUser, UUID> {

    public SysUser getByUsername(String username)
    {
        return getByField("username", username);
    }

    @Override
    public UUID create(SysUser model) {
        //加密用户输入的密码
        try {
            model.setPassword(PasswordHash.createHash(model.getPassword()));
            model.setFullNamePinyin(GetPinyinUtil.getAllCharPinYin(model.getFullName()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return super.create(model);
    }

    @Override
    public void update(SysUser model) {

        if (!StringUtils.isEmpty(model.getPassword())) {
            //加密用户输入的密码
            try {
                model.setPassword(PasswordHash.createHash(model.getPassword()));
                model.setFullNamePinyin(GetPinyinUtil.getAllCharPinYin(model.getFullName()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

            super.updateExcluded(model, "username");
        }
        else {
            super.updateExcluded(model, "username", "password");
        }
    }
}
