package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderPayableDetailDao;
import com.lnet.tms.model.fee.FeeOrderPayableDetail;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Service
public class FeeOrderPayableDetailService extends CrudService<FeeOrderPayableDetail,UUID,FeeOrderPayableDetailDao>{

    @Autowired
    public void setBaseDao(FeeOrderPayableDetailDao dao){
       super.setDao(dao);
    }
}
