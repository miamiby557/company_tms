package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderPayHistoryViewDao;
import com.lnet.tms.model.fee.FeeOrderPayHistoryView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FeeOrderPayHistoryViewService extends BaseService<FeeOrderPayHistoryView, UUID, FeeOrderPayHistoryViewDao> {

    @Autowired
    public void setBaseDao(FeeOrderPayHistoryViewDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
