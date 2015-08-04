package com.lnet.tms.service.dispatch;

import com.lnet.tms.dao.dispatch.DispatchAssignDetailDao;
import com.lnet.tms.model.dispatch.DispatchAssignDetail;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DispatchAssignDetailService extends CrudService<DispatchAssignDetail, UUID, DispatchAssignDetailDao> {

    @Autowired
    public void setBaseDao(DispatchAssignDetailDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
