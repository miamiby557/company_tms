
package com.lnet.tms.dao.sys;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.sys.SysListItem;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SysListItemDao extends CrudDao<SysListItem, UUID> {
   /* *//**
     * 按照页面输入显示顺序排序  serialNumber
     * @param field
     * @param value
     * @return
     *//*
    @Override
    public List<SysListItem> getAllByField(String field, Object value) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(SysListItem.class).add(Restrictions.eq(field, value)).addOrder(Order.asc("serialNumber"));
        return criteria.list();
    }
*/

}