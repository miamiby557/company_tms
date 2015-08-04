
package com.lnet.tms.dao.fee;

import com.lnet.tms.dao.BaseDao;
import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.fee.FeeOrderReceivableLogView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FeeOrderReceivableLogViewDao extends BaseDao<FeeOrderReceivableLogView, UUID> {

    @Override
    public List<FeeOrderReceivableLogView> getAllByField(String field, Object value) {
        Criteria criteria = getSession().createCriteria(persistentClass).add(Restrictions.eqOrIsNull(field, value));
        criteria.addOrder(Order.desc("operationDate"));
        return criteria.list();
    }
}