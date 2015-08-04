package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdCarrierOrderViewDao;
import com.lnet.tms.model.otd.OtdCarrierOrderView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OtdCarrierOrderViewService extends BaseService<OtdCarrierOrderView, UUID, OtdCarrierOrderViewDao> {

    @Autowired
    public void setBaseDao(OtdCarrierOrderViewDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
