package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderPayableProportionHDao;
import com.lnet.tms.model.fee.FeeOrderPayableProportionH;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FeeOrderPayableProportionHService extends CrudService<FeeOrderPayableProportionH, UUID, FeeOrderPayableProportionHDao> {

    @Autowired
    public void setBaseDao(FeeOrderPayableProportionHDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
