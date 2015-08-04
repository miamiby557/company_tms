package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderReceivableLogViewDao;
import com.lnet.tms.model.fee.FeeOrderReceivableLogView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FeeOrderReceivableLogViewService extends BaseService<FeeOrderReceivableLogView, UUID, FeeOrderReceivableLogViewDao> {

    @Autowired
    public void setBaseDao(FeeOrderReceivableLogViewDao dao) {
        super.setDao(dao);
    }
}
