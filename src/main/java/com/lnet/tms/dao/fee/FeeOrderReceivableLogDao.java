package com.lnet.tms.dao.fee;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.fee.FeeOrderReceivableLog;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Repository
public class FeeOrderReceivableLogDao extends CrudDao<FeeOrderReceivableLog,UUID>{

    public void autoCreate(UUID orderReceivableId,Integer status,String remark){
        FeeOrderReceivableLog log=new FeeOrderReceivableLog();
        log.setOrderReceivableId(orderReceivableId);
        log.setStatus(status);
        log.setRemark(remark);
        log.setOperationDate(DateUtils.getTimestampNow());
        log.setOperationUserId(IdentityUtils.getCurrentUser().getUserId());
        super.create(log);
    }
}
