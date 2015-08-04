package com.lnet.tms.service.rpt;

import com.lnet.tms.dao.rpt.RptViewOtdMonthsumDao;
import com.lnet.tms.model.rpt.RptViewOtdMonthsum;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RptViewOtdMonthsumService extends CrudService<RptViewOtdMonthsum, UUID, RptViewOtdMonthsumDao> {

    @Autowired
    public void setBaseDao(RptViewOtdMonthsumDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
