package com.lnet.tms.service.dispatch;

import com.lnet.tms.dao.dispatch.DispatchAssignStatusRecordDao;
import com.lnet.tms.model.dispatch.DispatchAssignStatusRecord;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DispatchAssignStatusRecordService extends CrudService<DispatchAssignStatusRecord, UUID, DispatchAssignStatusRecordDao> {

    @Autowired
    public void setBaseDao(DispatchAssignStatusRecordDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
