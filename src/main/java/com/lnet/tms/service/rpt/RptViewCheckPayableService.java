package com.lnet.tms.service.rpt;

import com.lnet.tms.dao.rpt.RptViewCheckPayableDao;
import com.lnet.tms.model.rpt.RptViewCheckPayable;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RptViewCheckPayableService extends CrudService<RptViewCheckPayable, UUID, RptViewCheckPayableDao> {

    @Autowired
    public void setBaseDao(RptViewCheckPayableDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
