package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdTransportOrderLogViewDao;
import com.lnet.tms.model.otd.OtdTransportOrderLogView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OtdTransportOrderLogViewService extends BaseService<OtdTransportOrderLogView, UUID, OtdTransportOrderLogViewDao> {
    @Autowired
    public void setBaseDao(OtdTransportOrderLogViewDao dao) {
        super.setDao(dao);
    }


}
