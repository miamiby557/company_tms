
package com.lnet.tms.dao.base;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.base.BaseExacct;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class BaseExacctDao extends CrudDao<BaseExacct, UUID> {

    @Override
    public UUID create(BaseExacct model) {
        if(model.getSuperiorId()!=null) {
            model.setSuperiorExacct(super.get(model.getSuperiorId()));
        }
        return super.create(model);
    }

    @Override
    public void update(BaseExacct model) {
        if(model.getSuperiorId()!=null) {
            model.setSuperiorExacct(super.get(model.getSuperiorId()));
        }
        super.update(model);
    }
}