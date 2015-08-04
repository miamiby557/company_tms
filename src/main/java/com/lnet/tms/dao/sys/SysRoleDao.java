
package com.lnet.tms.dao.sys;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.sys.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SysRoleDao extends CrudDao<SysRole, UUID> {

    public List<SysRole> getByUserId(UUID userId)
    {
        return null;
        //return getSession().createQuery("from SysRole where ")
    }
}