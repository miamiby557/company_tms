package com.lnet.tms.dao;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.BaseEntity;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.ReflectUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.InstantiationException;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class BaseDao<T, ID extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;
    protected final Class<T> persistentClass;

    private ClassMetadata persistentClassMetaData;

    public ClassMetadata getPersistentClassMetaData() {
        if (persistentClassMetaData == null) persistentClassMetaData = sessionFactory.getClassMetadata(persistentClass);
        return persistentClassMetaData;
    }

    public BaseDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    public T load(ID id) {
        return (T) getSession().load(persistentClass, id);
    }


    public ID getId(T model) {
        String idName = getPersistentClassMetaData().getIdentifierPropertyName();
        return (ID) ReflectUtils.getProperty(model, idName);

    }
    private String getIdName() {
        return getPersistentClassMetaData().getIdentifierPropertyName();
    }
    public <S> List<S> sqlQuery(String sql, Object... parameters) {
        SQLQuery query = getSession().createSQLQuery(sql);

        for (int i = 0; i < parameters.length; i++) {
            query = (SQLQuery) query.setParameter(i, parameters[i]);
        }

        return query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public <S> List<S> hqlQuery(String hql, Object... parameters) {
        Query query = getSession().createQuery(hql);

        for (int i = 0; i < parameters.length; i++) {
            query = query.setParameter(i, parameters[i]);
        }

        return query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public DataSourceResult getDataSource(DataSourceRequest request){
        T t = null;
        try {
            t = persistentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        UUID branchId = IdentityUtils.getCurrentUser().getBranchId();
        if(t instanceof BaseEntity&&branchId!=null){
            List<DataSourceRequest.FilterDescriptor> filters = request.getFilter().getFilters();
            request.getFilter().setLogic("and");
            filters.add(new DataSourceRequest.FilterDescriptor("branchId","eq",branchId));
        }
        return DataSourceBuilder.build(request, getSession(), persistentClass);
    }


    public boolean exists(ID id) {
        Session session = getSession();
        return (Long) session.createCriteria(persistentClass)
                .add(Restrictions.idEq(id))
                .setProjection(Projections.rowCount()).uniqueResult() > 0L;
    }

    public boolean existsBy(String field, Object value) {
        Session session = getSession();
        return (Long) session.createCriteria(persistentClass)
                .add(Restrictions.eq(field, value))
                .setProjection(Projections.rowCount()).uniqueResult() > 0L;
    }

    public boolean existsBy(String field, Object value, ID excludedId) {
        Session session = getSession();
        return (Long) session.createCriteria(persistentClass)
                .add(Restrictions.eq(field, value))
                .add(Restrictions.not(Restrictions.idEq(excludedId)))
                .setProjection(Projections.rowCount()).uniqueResult() > 0L;
    }


    public T getByField(String field, Object value) {
        Session session = getSession();
        return (T) session.createCriteria(persistentClass)
                .add(Restrictions.eq(field, value)).setMaxResults(1).uniqueResult();
    }

    public T getByFieldWith(String field, Object value, String... associationPaths) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(persistentClass).add(Restrictions.eq(field, value));
        for (String path : associationPaths) {
            criteria = criteria.setFetchMode(path, FetchMode.JOIN);
        }

        return (T) criteria.setMaxResults(1).list();
    }

    public T getByField(Map<String, Object> map) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(persistentClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            criteria = criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }
        return (T) criteria.setMaxResults(1).uniqueResult();
    }

    public T get(ID id) {
        return (T) getSession().get(persistentClass, id);
    }

    public T getWith(ID id, String... associationPaths) {
        Criteria criteria = getSession().createCriteria(persistentClass).add(Restrictions.idEq(id));
        for (String path : associationPaths) {
            criteria = criteria.setFetchMode(path, FetchMode.JOIN);
        }

        return (T) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
    }

    public List<T> getList(String field,String number,ID userId,String descName){
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("createUserId",userId));
        criteria.add(Restrictions.like(field,"%"+number+"%"));
        criteria.addOrder(Order.desc(descName));
        return criteria.list();
    }

    public List<T> get(ID... ids) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        if(ids!=null&&ids.length>0){
            criteria = criteria.add(Restrictions.in(getIdName(),ids));
            return criteria.list();
        }
        return  null;
    }
    public List<T> get(List<ID> ids) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        if(ids!=null&&ids.size()>0){
            criteria = criteria.add(Restrictions.in(getIdName(),ids));
            return criteria.list();
        }
       return  null;
    }

    public List<T> getAll() {
        return getSession().createCriteria(persistentClass).list();
    }

    public List<T> getAllWith(String... associationPaths) {
        Criteria criteria = getSession().createCriteria(persistentClass);

        for (String path : associationPaths) {
            criteria = criteria.setFetchMode(path, FetchMode.JOIN);
        }

        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public List<T> getHierarchicalData(String parentField, String childrenField, ID id) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        if (id == null)
            criteria = criteria.add(Restrictions.isNull(parentField));
        else
            criteria = criteria.add(Restrictions.idEq(id));

        return criteria.setFetchMode(childrenField, FetchMode.JOIN).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public List<T> getAllByField(String field, Object value) {
        Criteria criteria = getSession().createCriteria(persistentClass).add(Restrictions.eqOrIsNull(field, value));
        return criteria.list();
    }

    public List<T> getAllByFieldDesc(String field, Object value,String descName) {
        Criteria criteria = getSession().createCriteria(persistentClass).add(Restrictions.eqOrIsNull(field, value));
        criteria.addOrder(Order.desc(descName));
        return criteria.list();
    }
    public List<T> getAllByAsc(String ascName) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.addOrder(Order.asc(ascName));
        return criteria.list();
    }


    public List<T> getAllByField(Map<String, Object> map) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(persistentClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            criteria = criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }
        return criteria.list();
    }

    public List<T> getAllByFieldWith(String field, Object value, String... associationPaths) {
        Criteria criteria = getSession().createCriteria(persistentClass).add(Restrictions.eqOrIsNull(field, value));
        for (String path : associationPaths) {
            criteria = criteria.setFetchMode(path, FetchMode.JOIN);
        }
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }


    public List<Map<String, Object>> getListItems(String textField, String valueField) {
        String hql = MessageFormat.format("select {0} as text, {1} as value from {2} ", textField, valueField, getPersistentClassMetaData().getEntityName());
        return getSession().createQuery(hql).list();
    }


}
