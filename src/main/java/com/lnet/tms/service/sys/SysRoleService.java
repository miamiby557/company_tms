package com.lnet.tms.service.sys;

import com.lnet.tms.dao.sys.SysRoleDao;
import com.lnet.tms.model.sys.SysRole;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SysRoleService extends CrudService<SysRole, UUID, SysRoleDao> {

    @Autowired
    public void setBaseDao(SysRoleDao dao) {
        super.setDao(dao);
    }

    // your service methods

    @Transactional
    public List<SysRole> getRolesByUserId(UUID userId)
    {
        return getDao().sqlQuery("");
    }
}
