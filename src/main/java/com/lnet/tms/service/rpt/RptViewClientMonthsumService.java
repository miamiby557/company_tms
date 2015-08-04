package com.lnet.tms.service.rpt;

import com.lnet.tms.dao.rpt.RptViewClientMonthsumDao;
import com.lnet.tms.model.rpt.RptViewClientMonthsum;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RptViewClientMonthsumService extends CrudService<RptViewClientMonthsum, UUID, RptViewClientMonthsumDao> {

    @Autowired
    public void setBaseDao(RptViewClientMonthsumDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
