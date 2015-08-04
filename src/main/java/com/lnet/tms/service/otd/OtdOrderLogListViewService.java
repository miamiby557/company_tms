package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdOrderLogListViewDao;
import com.lnet.tms.dao.otd.OtdTransportOrderViewDao;
import com.lnet.tms.model.otd.OtdOrderLogListView;
import com.lnet.tms.model.otd.OtdTransportOrderView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OtdOrderLogListViewService extends BaseService<OtdOrderLogListView, UUID, OtdOrderLogListViewDao> {
    @Autowired
    public void setBaseDao(OtdOrderLogListViewDao dao) {
        super.setDao(dao);
    }


}
