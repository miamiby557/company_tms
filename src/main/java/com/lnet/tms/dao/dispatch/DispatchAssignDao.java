
package com.lnet.tms.dao.dispatch;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.dispatch.DispatchAssign;
import com.lnet.tms.model.dispatch.DispatchAssignDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public class DispatchAssignDao extends CrudDao<DispatchAssign, UUID> {
    @Override
    public UUID create(DispatchAssign model) {
        Set<DispatchAssignDetail> details =  model.getDetails();
        if(details!=null&&details.size()>0){
            for(DispatchAssignDetail detail: details){
                detail.setDispatchAssign(model);
            }
        }
        return super.create(model);
    }

    @Override
    public void update(DispatchAssign model) {
        Set<DispatchAssignDetail> details =  model.getDetails();
        if(details!=null&&details.size()>0){
            for(DispatchAssignDetail detail: details){
                detail.setDispatchAssign(model);
            }
        }
        super.merge(model);
    }
}