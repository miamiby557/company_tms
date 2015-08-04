
package com.lnet.tms.dao.otd;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.dao.SequenceDao;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.model.otd.OtdTransportOrderDetail;
import com.lnet.tms.model.otd.OtdTransportOrderLog;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OtdTransportOrderDao extends CrudDao<OtdTransportOrder, UUID> {
    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public UUID create(OtdTransportOrder model) {
        String number = sequenceDao.formatSequenceNumber("SEQ_TRANORDER_NUMBER", "00000000", "LNET", "");
        model.setLnetOrderNumber(number);
        if(model.getDetails()!=null) {
            for (OtdTransportOrderDetail detail : model.getDetails()) {
                detail.setCreateDate(DateUtils.getTimestampNow());
                detail.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
                detail.setOtdTransportOrder(model);
            }
        }
        return super.create(model);
    }



    @Override
    public void update(OtdTransportOrder model) {
        if(model.getDetails()!=null) {
            for (OtdTransportOrderDetail detail : model.getDetails()) {
                detail.setModifyDate(DateUtils.getTimestampNow());
                detail.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
                detail.setOtdTransportOrder(model);
            }
        }
        super.merge(model);
    }

    public void updateStatus (OtdTransportOrder model) {
        super.update(model);
    }

    public Boolean clientOrderNumberIsExist(UUID clientId,String clientOrderNumber){
        Session session = getSession();
        return (Long) session.createCriteria(OtdTransportOrder.class)
                .add(Restrictions.eq("clientId", clientId)).add(Restrictions.eq("clientOrderNumber", clientOrderNumber))
                .setProjection(Projections.rowCount()).uniqueResult() > 0L;
    }

    public Boolean clientOrderNumberIsExist(UUID clientId,String clientOrderNumber,UUID transportOrderId){
        Session session = getSession();
        return (Long) session.createCriteria(OtdTransportOrder.class)
                .add(Restrictions.eq("clientId", clientId)).add(Restrictions.eq("clientOrderNumber", clientOrderNumber))
                .add(Restrictions.not(Restrictions.idEq(transportOrderId)))
                .setProjection(Projections.rowCount()).uniqueResult() > 0L;
    }
}