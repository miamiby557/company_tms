package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeePayableProportionViewDao;
import com.lnet.tms.model.fee.FeePayableProportionView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FeePayableProportionViewService extends BaseService<FeePayableProportionView, UUID, FeePayableProportionViewDao> {

    @Autowired
    public void setBaseDao(FeePayableProportionViewDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
