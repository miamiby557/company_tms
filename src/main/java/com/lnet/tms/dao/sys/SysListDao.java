
package com.lnet.tms.dao.sys;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.sys.SysList;
import com.lnet.tms.model.sys.SysListItem;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public class SysListDao extends CrudDao<SysList, UUID> {

    @Override
    public UUID create(SysList model) {
        for (SysListItem item : model.getItems()) {
            item.setSysList(model);
        }

        return super.create(model);
    }

    @Override
    public void update(SysList model) {
        SysList target = load(model.getListId());
        target.getItems().clear();

        for (SysListItem item : model.getItems()) {
            item.setSysList(model);
        }

        super.merge(model);
    }
}