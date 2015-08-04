
package com.lnet.tms.dao.crm;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.crm.CrmClientProductType;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CrmClientProductTypeDao extends CrudDao<CrmClientProductType, UUID> {

    public Boolean codeIsExist(UUID clientId,Integer value,UUID clientProductTypeId){
        Session session = getSession();
        if(clientProductTypeId!=null){
            return (Long) session.createCriteria(CrmClientProductType.class)
                    .add(Restrictions.eq("clientId", clientId)).add(Restrictions.eq("value", value))
                    .add(Restrictions.not(Restrictions.idEq(clientProductTypeId)))
                    .setProjection(Projections.rowCount()).uniqueResult() > 0L;
        }else {
            return (Long) session.createCriteria(CrmClientProductType.class)
                    .add(Restrictions.eq("clientId", clientId)).add(Restrictions.eq("value", value))
                    .setProjection(Projections.rowCount()).uniqueResult() > 0L;
        }
    }

}