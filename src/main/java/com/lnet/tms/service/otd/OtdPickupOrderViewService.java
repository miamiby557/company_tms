package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdPickupOrderViewDao;
import com.lnet.tms.model.otd.OtdPickupOrderView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OtdPickupOrderViewService extends BaseService<OtdPickupOrderView, UUID, OtdPickupOrderViewDao> {

    @Autowired
    public void setBaseDao(OtdPickupOrderViewDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
