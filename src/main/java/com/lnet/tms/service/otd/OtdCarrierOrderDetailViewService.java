package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdCarrierOrderDetailViewDao;
import com.lnet.tms.model.otd.OtdCarrierOrderDetailView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OtdCarrierOrderDetailViewService extends BaseService<OtdCarrierOrderDetailView, UUID, OtdCarrierOrderDetailViewDao> {

    @Autowired
    public void setBaseDao(OtdCarrierOrderDetailViewDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
