package com.lnet.tms.dao.scm;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.crm.CrmClientQuote;
import com.lnet.tms.model.scm.ScmCarrierLine;
import com.lnet.tms.model.scm.ScmCarrierQuote;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ScmCarrierQuoteDao extends CrudDao<ScmCarrierQuote,UUID>{

    public List<ScmCarrierQuote> getByFieldExistId(Map<String, Object> map,UUID id) {
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
            ScmCarrierQuote model = (ScmCarrierQuote) session.load(ScmCarrierQuote.class, id);
            model.setScmCarrierLine(null);
            session.delete(model);
        }
    }
}
