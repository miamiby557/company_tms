package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderReceivableDetailHDao;
import com.lnet.tms.model.fee.FeeOrderReceivableDetailH;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FeeOrderReceivableDetailHService extends CrudService<FeeOrderReceivableDetailH, UUID, FeeOrderReceivableDetailHDao> {

    @Autowired
    public void setBaseDao(FeeOrderReceivableDetailHDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
