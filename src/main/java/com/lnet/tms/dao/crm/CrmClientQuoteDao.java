package com.lnet.tms.dao.crm;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.crm.CrmClientQuote;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Repository
public class CrmClientQuoteDao extends CrudDao<CrmClientQuote, UUID> {

    public List<CrmClientQuote> getByFieldExistId(Map<String, Object> map,UUID id) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            criteria = criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }
        criteria.add(Restrictions.not(Restrictions.idEq(id)));
        return criteria.list();
    }

    @Override
    public void deleteById(List<UUID> uuids) {
        Session session = getSession();
        for (UUID id : uuids) {
            CrmClientQuote model = (CrmClientQuote) session.load(CrmClientQuote.class, id);
            model.setCrmClientLine(null);
            session.delete(model);
        }
    }
}
