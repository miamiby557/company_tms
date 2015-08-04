
package com.lnet.tms.dao.crm;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.crm.CrmClientOrderType;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Properties;
import java.util.UUID;

@Repository
public class CrmClientOrderTypeDao extends CrudDao<CrmClientOrderType, UUID> {
    public boolean codeIsExist(UUID clientId, UUID clientOrderTypeId, Integer value) {
        Session session = getSession();
        if (clientOrderTypeId != null) {
            return (Long) session.createCriteria(CrmClientOrderType.class)
                    .add(Restrictions.eq("clientId", clientId)).add(Restrictions.eq("value", value))
                    .add(Restrictions.not(Restrictions.idEq(clientOrderTypeId)))
                    .setProjection(Projections.rowCount()).uniqueResult() > 0L;
        } else {
            return (Long) session.createCriteria(CrmClientOrderType.class)
                    .add(Restrictions.eq("clientId", clientId)).add(Restrictions.eq("value", value))
                    .setProjection(Projections.rowCount()).uniqueResult() > 0L;
        }

    }

}