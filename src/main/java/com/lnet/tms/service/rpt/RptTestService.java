package com.lnet.tms.service.rpt;

import com.lnet.tms.dao.rpt.RptTestDao;
import com.lnet.tms.model.rpt.RptTest;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RptTestService extends CrudService<RptTest, UUID, RptTestDao> {

    @Autowired
    public void setBaseDao(RptTestDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
