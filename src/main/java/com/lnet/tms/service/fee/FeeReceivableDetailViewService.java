package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeReceivableDetailViewDao;
import com.lnet.tms.model.fee.FeeReceivableDetailView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeeReceivableDetailViewService extends BaseService<FeeReceivableDetailView,UUID,FeeReceivableDetailViewDao> {

    @Autowired
    public void setBaseDao(FeeReceivableDetailViewDao dao){
        super.setDao(dao);
    }

}
