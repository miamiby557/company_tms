
package com.lnet.tms.dao.base;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.base.BaseRegion;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BaseRegionDao extends CrudDao<BaseRegion, UUID> {

    public List<BaseRegion> getRegions(UUID superiorRegionId) {
        return getSession()
                .createCriteria(persistentClass)
                .add(Restrictions.eqOrIsNull("superiorRegionId", superiorRegionId))
                .addOrder(Order.asc("code"))
                .list();
    }

    @Override
    public List<BaseRegion> getAllByField(String field, Object value) {
        Criteria criteria = getSession().createCriteria(BaseRegion.class).add(Restrictions.eqOrIsNull(field, value));
        criteria.addOrder(Order.asc("code"));
        return criteria.list();
    }
}