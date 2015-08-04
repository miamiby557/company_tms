package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderPayableLogViewDao;
import com.lnet.tms.model.fee.FeeOrderPayableLogView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeeOrderPayableLogViewService extends BaseService<FeeOrderPayableLogView, UUID, FeeOrderPayableLogViewDao> {

    @Autowired
    public void setBaseDao(FeeOrderPayableLogViewDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
