package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdCarrierOrderLogViewDao;
import com.lnet.tms.model.otd.OtdCarrierOrderLogView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OtdCarrierOrderLogViewService extends BaseService<OtdCarrierOrderLogView, UUID, OtdCarrierOrderLogViewDao> {

    @Autowired
    public void setBaseDao(OtdCarrierOrderLogViewDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
