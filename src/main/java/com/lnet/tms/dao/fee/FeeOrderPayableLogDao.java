package com.lnet.tms.dao.fee;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.fee.FeeOrderPayableLog;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Repository
public class FeeOrderPayableLogDao extends CrudDao<FeeOrderPayableLog,UUID>{

    public void autoCreate(UUID orderPayableId,Integer status,String remark){
        FeeOrderPayableLog log=new FeeOrderPayableLog();
        log.setOrderPayableId(orderPayableId);
        log.setStatus(status);
        log.setRemark(remark);
        log.setOperationDate(DateUtils.getTimestampNow());
        SysUser user = IdentityUtils.getCurrentUser();
        if(user!=null){
            log.setOperationUserId(user.getUserId());
        }
        super.create(log);
    }
}
