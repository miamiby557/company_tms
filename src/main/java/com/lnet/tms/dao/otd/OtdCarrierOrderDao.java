
package com.lnet.tms.dao.otd;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.otd.OtdCarrierOrder;
import com.lnet.tms.model.otd.OtdCarrierOrder;
import com.lnet.tms.model.otd.OtdCarrierOrderDetail;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OtdCarrierOrderDao extends CrudDao<OtdCarrierOrder, UUID> {

    public Boolean OrderNumberIsExist(UUID carrierId,String carrierOrderNumber,UUID carrierOrderId){
        Session session = getSession();
        Criteria criteria= session.createCriteria(OtdCarrierOrder.class)
                .add(Restrictions.eq("carrierId", carrierId)).add(Restrictions.eq("carrierOrderNumber", carrierOrderNumber));
                if(carrierOrderId!=null){
                    criteria.add(Restrictions.not(Restrictions.idEq(carrierOrderId)));
                }
        return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult() > 0L;
    }
    public Boolean OrderNumberIsExist(UUID carrierId,String carrierOrderNumber){
        Session session = getSession();
        Criteria criteria= session.createCriteria(OtdCarrierOrder.class)
                .add(Restrictions.eq("carrierId", carrierId)).add(Restrictions.eq("carrierOrderNumber", carrierOrderNumber));
        return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult() > 0L;
    }

    @Override
    public UUID create(OtdCarrierOrder model) {
        for(OtdCarrierOrderDetail detail:model.getDetails()){
            detail.setOtdCarrierOrder(model);
        }
        return super.create(model);
    }



    @Override
    public void update(OtdCarrierOrder model) {
        for(OtdCarrierOrderDetail detail:model.getDetails()){
            detail.setOtdCarrierOrder(model);
        }
        super.merge(model);
    }

    public void cancel(OtdCarrierOrder OtdCarrierOrder){
        super.update(OtdCarrierOrder);
    }
}