package com.lnet.tms.service.rpt;

import com.lnet.tms.dao.rpt.RptViewCheckIncomeDao;
import com.lnet.tms.model.rpt.RptViewCheckIncome;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RptViewCheckIncomeService extends CrudService<RptViewCheckIncome, UUID, RptViewCheckIncomeDao> {

    @Autowired
    public void setBaseDao(RptViewCheckIncomeDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
