package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderPayableDetailHDao;
import com.lnet.tms.model.fee.FeeOrderPayableDetailH;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FeeOrderPayableDetailHService extends CrudService<FeeOrderPayableDetailH, UUID, FeeOrderPayableDetailHDao> {

    @Autowired
    public void setBaseDao(FeeOrderPayableDetailHDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
