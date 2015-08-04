package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdTransportOrderDetailDao;
import com.lnet.tms.model.otd.OtdTransportOrderDetail;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class OtdTransportOrderDetailService extends CrudService<OtdTransportOrderDetail, UUID, OtdTransportOrderDetailDao> {

    @Autowired
    public void setBaseDao(OtdTransportOrderDetailDao dao) {
        super.setDao(dao);
    }

}
