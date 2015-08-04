package com.lnet.tms.dao.fee;

import com.lnet.tms.dao.BaseDao;
import com.lnet.tms.model.fee.FeeOrderReceivableView;
import com.lnet.tms.model.fee.FeeReceivableDetailView;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Repository
public class FeeReceivableDetailViewDao extends BaseDao<FeeReceivableDetailView,UUID> {
    @Override
    public List<FeeReceivableDetailView> getAllByField(String field, Object value) {
        Criteria criteria = getSession().createCriteria(FeeReceivableDetailView.class).add(Restrictions.eqOrIsNull(field, value));
        criteria.addOrder(Order.asc("indexNumber"));
        return criteria.list();
    }
}
