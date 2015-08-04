package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderReceiveHistoryViewDao;
import com.lnet.tms.model.fee.FeeOrderReceiveHistoryView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FeeOrderReceiveHistoryViewService extends BaseService<FeeOrderReceiveHistoryView, UUID, FeeOrderReceiveHistoryViewDao> {

    @Autowired
    public void setBaseDao(FeeOrderReceiveHistoryViewDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
