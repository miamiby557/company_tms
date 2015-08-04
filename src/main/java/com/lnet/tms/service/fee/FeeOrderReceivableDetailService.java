package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderReceivableDetailDao;
import com.lnet.tms.model.fee.FeeOrderReceivableDetail;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeeOrderReceivableDetailService extends CrudService<FeeOrderReceivableDetail, UUID, FeeOrderReceivableDetailDao> {

    @Autowired
    public void setBaseDao(FeeOrderReceivableDetailDao dao) {
        super.setDao(dao);
    }
}
