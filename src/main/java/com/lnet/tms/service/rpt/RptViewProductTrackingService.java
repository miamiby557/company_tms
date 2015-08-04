package com.lnet.tms.service.rpt;

import com.lnet.tms.dao.rpt.RptViewProductTrackingDao;
import com.lnet.tms.model.rpt.RptViewProductTracking;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RptViewProductTrackingService extends CrudService<RptViewProductTracking, UUID, RptViewProductTrackingDao> {

    @Autowired
    public void setBaseDao(RptViewProductTrackingDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
