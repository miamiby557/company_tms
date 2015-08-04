package com.lnet.tms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Repository
public class SequenceDao {

    @Autowired
    protected SessionFactory sessionFactory;

    public Long getSequenceNumber(String seqName)
    {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select " + seqName + ".nextval as id from dual";
        BigDecimal result = (BigDecimal) session.createSQLQuery(sql)
                .addScalar("id").uniqueResult();
        return result.longValue();
    }

    public String formatSequenceNumber(String seqName, String format, String prefix, String suffix)
    {
        String id = new DecimalFormat(format).format(getSequenceNumber(seqName));
        return prefix + id + suffix;
    }
}
