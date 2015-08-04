package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderPayableDetailViewDao;
import com.lnet.tms.model.fee.FeeOrderPayableDetailView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Service
public class FeeOrderPayableDetailViewService extends BaseService<FeeOrderPayableDetailView,UUID,FeeOrderPayableDetailViewDao>{

    @Autowired
    public void setBaseDao(FeeOrderPayableDetailViewDao dao){
       super.setDao(dao);
    }
}
