package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdCarrierOrderDetailDao;
import com.lnet.tms.model.otd.OtdCarrierOrderDetail;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OtdCarrierOrderDetailService extends CrudService<OtdCarrierOrderDetail, UUID, OtdCarrierOrderDetailDao> {

    @Autowired
    public void setBaseDao(OtdCarrierOrderDetailDao dao) {
        super.setDao(dao);
    }

    // your service methods

}
