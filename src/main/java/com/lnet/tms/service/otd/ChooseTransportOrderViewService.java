package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.ChooseTransportOrderViewDao;
import com.lnet.tms.model.otd.ChooseTransportOrderView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChooseTransportOrderViewService extends BaseService<ChooseTransportOrderView, UUID, ChooseTransportOrderViewDao> {

    @Autowired
    public void setBaseDao(ChooseTransportOrderViewDao dao) {
        super.setDao(dao);
    }

}
