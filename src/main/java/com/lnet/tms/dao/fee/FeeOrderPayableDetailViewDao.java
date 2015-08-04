package com.lnet.tms.dao.fee;

import com.lnet.tms.dao.BaseDao;
import com.lnet.tms.model.fee.FeeOrderPayableDetail;
import com.lnet.tms.model.fee.FeeOrderPayableDetailView;
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
public class FeeOrderPayableDetailViewDao extends BaseDao<FeeOrderPayableDetailView,UUID>{
    @Override
    public List<FeeOrderPayableDetailView> getAllByField(String field, Object value) {
        Criteria criteria = getSession().createCriteria(FeeOrderPayableDetailView.class).add(Restrictions.eqOrIsNull(field, value));
        criteria.addOrder(Order.asc("indexNumber"));
        return criteria.list();
    }
}
