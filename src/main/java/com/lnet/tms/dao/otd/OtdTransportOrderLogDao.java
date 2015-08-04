
package com.lnet.tms.dao.otd;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.otd.OtdTransportOrderLog;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OtdTransportOrderLogDao extends CrudDao<OtdTransportOrderLog, UUID> {

    public void autoCreate(UUID transportOrderId,Integer currentStatus,String operationContent){
        OtdTransportOrderLog orderLog=new OtdTransportOrderLog();
        orderLog.setCreateDate(DateUtils.getTimestampNow());
        orderLog.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        orderLog.setCurrentStatus(currentStatus);
        orderLog.setIsPublic(true);
        orderLog.setOperationContent(operationContent);
        orderLog.setOperationDate(DateUtils.getTimestampNow());
        orderLog.setTransportOrderId(transportOrderId);
        super.create(orderLog);
    }
    public void autoCreate(UUID transportOrderId,Integer currentStatus,String operationContent,Boolean isAbnormal){
        OtdTransportOrderLog orderLog=new OtdTransportOrderLog();
        orderLog.setCreateDate(DateUtils.getTimestampNow());
        orderLog.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        orderLog.setCurrentStatus(currentStatus);
        orderLog.setIsPublic(true);
        orderLog.setOperationContent(operationContent);
        orderLog.setOperationDate(DateUtils.getTimestampNow());
        orderLog.setTransportOrderId(transportOrderId);
        orderLog.setIsAbnormal(isAbnormal);
        super.create(orderLog);
    }
}