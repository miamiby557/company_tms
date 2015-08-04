
package com.lnet.tms.dao.otd;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.otd.OtdCarrierOrderLog;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OtdCarrierOrderLogDao extends CrudDao<OtdCarrierOrderLog, UUID> {
    public void autoCreate(UUID transportOrderId,Integer currentStatus,String operationContent){
        OtdCarrierOrderLog orderLog=new OtdCarrierOrderLog();
        orderLog.setCreateDate(DateUtils.getTimestampNow());
        SysUser sysUser = IdentityUtils.getCurrentUser();
        if(sysUser!=null){
            orderLog.setCreateUserId(sysUser.getUserId());
        }
        orderLog.setCurrentStatus(currentStatus);
        orderLog.setIsPublic(true);
        orderLog.setOperationContent(operationContent);
        orderLog.setOperationDate(DateUtils.getTimestampNow());
        orderLog.setCarrierOrderId(transportOrderId);
        super.create(orderLog);
    }

    @Override
    public List<OtdCarrierOrderLog> getAllByField(String field, Object value) {
        Criteria criteria = getSession().createCriteria(OtdCarrierOrderLog.class).add(Restrictions.eqOrIsNull(field, value)).addOrder(Order.desc("operationDate"));
        return criteria.list();
    }

    public OtdCarrierOrderLog findLastLog(UUID carrierOrderId){
        Criteria criteria = getSession().createCriteria(OtdCarrierOrderLog.class).add(Restrictions.eqOrIsNull("carrierOrderId", carrierOrderId)).addOrder(Order.desc("operationDate"));
        return (OtdCarrierOrderLog) criteria.list().get(0);
    }
}