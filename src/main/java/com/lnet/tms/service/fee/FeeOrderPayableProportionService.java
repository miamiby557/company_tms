package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderPayableProportionDao;
import com.lnet.tms.model.fee.FeeOrderPayableProportion;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FeeOrderPayableProportionService extends CrudService<FeeOrderPayableProportion, UUID, FeeOrderPayableProportionDao> {

    @Autowired
    public void setBaseDao(FeeOrderPayableProportionDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
