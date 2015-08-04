package com.lnet.tms.service.rpt;

import com.lnet.tms.dao.rpt.RptViewInformationTrackingDao;
import com.lnet.tms.model.rpt.RptViewInformationTracking;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RptViewInformationTrackingService extends CrudService<RptViewInformationTracking, UUID, RptViewInformationTrackingDao> {

    @Autowired
    public void setBaseDao(RptViewInformationTrackingDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
