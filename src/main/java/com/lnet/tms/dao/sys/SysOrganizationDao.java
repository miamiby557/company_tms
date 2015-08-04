
package com.lnet.tms.dao.sys;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.sys.SysOrganization;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SysOrganizationDao extends CrudDao<SysOrganization, UUID> {
    @Override
    public UUID create(SysOrganization model) {
        if(model.getSuperiorOrganizationId()!=null) {
            model.setSuperiorOrganization(super.get(model.getSuperiorOrganizationId()));
        }
        return super.create(model);
    }


    @Override
    public void update(SysOrganization model) {
        if(model.getSuperiorOrganizationId()!=null) {
            model.setSuperiorOrganization(super.get(model.getSuperiorOrganizationId()));
        }
        super.update(model);
    }
}