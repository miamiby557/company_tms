
package com.lnet.tms.dao.dispatch;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.dispatch.DispatchAssignStatusRecord;
import com.lnet.tms.service.IdentityUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public class DispatchAssignStatusRecordDao extends CrudDao<DispatchAssignStatusRecord, UUID> {

    public void auotCreate(UUID assignId,Integer status,String content){
        DispatchAssignStatusRecord record =new DispatchAssignStatusRecord();
        record.setDispatchAssignId(assignId);
        record.setStatus(status);
        record.setOperator(IdentityUtils.getCurrentUser().getUserId());
        record.setOperationDate(new Date());
        record.setOperatorContent(content);

        super.create(record);
    }
}