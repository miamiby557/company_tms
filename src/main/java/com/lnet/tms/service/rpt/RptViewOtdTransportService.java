package com.lnet.tms.service.rpt;

import com.lnet.tms.dao.rpt.RptViewOtdTransportDao;
import com.lnet.tms.model.rpt.RptViewOtdTransport;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RptViewOtdTransportService extends CrudService<RptViewOtdTransport, UUID, RptViewOtdTransportDao> {

    @Autowired
    public void setBaseDao(RptViewOtdTransportDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
