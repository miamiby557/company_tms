package com.lnet.tms.dao;

import com.lnet.tms.model.BaseEntity;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.HibernateUtils;
import org.hibernate.Session;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.*;

public abstract class CrudDao<T, ID extends Serializable> extends BaseDao<T, ID> {

    public void persist(T model) {
        getSession().persist(model);
    }

    // create

    public ID create(T model) {
        if(model instanceof BaseEntity){
            ((BaseEntity) model).setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
            ((BaseEntity) model).setCreateDate(DateUtils.getTimestampNow());
            ((BaseEntity) model).setSiteId(IdentityUtils.getCurrentUser().getSiteId());
            ((BaseEntity) model).setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        }
        return (ID) getSession().save(model);
    }

    public List<ID> create(List<T> models) {
        List<ID> result = new ArrayList<>();
        if (models == null) return Collections.EMPTY_LIST;

        for (T model : models) {
            result.add(create(model));
        }

        return result;
    }

    // update

    public void update(T model) {
        if(model instanceof BaseEntity){
            ((BaseEntity) model).setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
            ((BaseEntity) model).setModifyDate(DateUtils.getTimestampNow());
            ((BaseEntity) model).setSiteId(IdentityUtils.getCurrentUser().getSiteId());
            ((BaseEntity) model).setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        }
        getSession().update(model);
    }

    // 更新包含的字段
    public void updateIncluded(T model, String... includedProperties) {
        ID id = getId(model);
        if (id == null) return;

        Session session = getSession();
        T target = (T) session.load(persistentClass, id);
        HibernateUtils.updateIncluded(getPersistentClassMetaData(), model, target, includedProperties);
        if(model instanceof BaseEntity){
            ((BaseEntity) model).setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
            ((BaseEntity) model).setModifyDate(DateUtils.getTimestampNow());
            ((BaseEntity) model).setSiteId(IdentityUtils.getCurrentUser().getSiteId());
            ((BaseEntity) model).setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        }
        session.update(target);
    }

    // 更新排除的字段
    public void updateExcluded(T model, String... excludedProperties) {
        ID id = getId(model);
        if (id == null) return;

        Session session = getSession();
        T target = (T) session.load(persistentClass, id);
        HibernateUtils.updateExcluded(getPersistentClassMetaData(), model, target, excludedProperties);
        if(model instanceof BaseEntity){
            ((BaseEntity) model).setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
            ((BaseEntity) model).setModifyDate(DateUtils.getTimestampNow());
            ((BaseEntity) model).setSiteId(IdentityUtils.getCurrentUser().getSiteId());
            ((BaseEntity) model).setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        }
        session.update(target);
    }

    public void update(List<T> models) {
        if (models == null) return;
        for (T model : models) {
            update(model);
        }
    }

    public T merge(T model) {
        if(model instanceof BaseEntity){
            if(((BaseEntity) model).getCreateUserId()==null){
                ((BaseEntity) model).setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
                ((BaseEntity) model).setCreateDate(DateUtils.getTimestampNow());
                ((BaseEntity) model).setSiteId(IdentityUtils.getCurrentUser().getSiteId());
                ((BaseEntity) model).setBranchId(IdentityUtils.getCurrentUser().getBranchId());
            }else {
                ((BaseEntity) model).setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
                ((BaseEntity) model).setModifyDate(DateUtils.getTimestampNow());
                ((BaseEntity) model).setSiteId(IdentityUtils.getCurrentUser().getSiteId());
                ((BaseEntity) model).setBranchId(IdentityUtils.getCurrentUser().getBranchId());
            }
        }
        return (T) getSession().merge(model);
    }


    public void saveOrUpdate(T model) {
        if(model instanceof BaseEntity){
            if(((BaseEntity) model).getCreateUserId()==null){
                ((BaseEntity) model).setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
                ((BaseEntity) model).setCreateDate(DateUtils.getTimestampNow());
                ((BaseEntity) model).setSiteId(IdentityUtils.getCurrentUser().getSiteId());
                ((BaseEntity) model).setBranchId(IdentityUtils.getCurrentUser().getBranchId());
            }else {
                ((BaseEntity) model).setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
                ((BaseEntity) model).setModifyDate(DateUtils.getTimestampNow());
                ((BaseEntity) model).setSiteId(IdentityUtils.getCurrentUser().getSiteId());
                ((BaseEntity) model).setBranchId(IdentityUtils.getCurrentUser().getBranchId());
            }
        }
        getSession().saveOrUpdate(model);
    }


    public void saveOrUpdate(List<T> models) {
        if (models == null) return;
        for (T model : models) {
            saveOrUpdate(model);
        }
    }

    // delete
    public void deleteById(ID id) {
        Session session = getSession();
        T model = (T) session.load(persistentClass, id);
        session.delete(model);
    }

    public void deleteById(List<ID> ids) {
        Session session = getSession();
        for (ID id : ids) {
            T model = (T) session.load(persistentClass, id);
            session.delete(model);
        }
    }

    public void feeAuditPayable(List<ID> ids){
       //审核应付
    }

    public void feeAuditReceivable(List<ID> ids){
       //审核应收
    }

    public void delete(T model) {
        getSession().delete(model);
    }

    public void delete(List<T> models) {
        if (models == null) return;

        Session session = getSession();
        for (T model : models) {
            session.delete(model);
        }
    }

    public int deleteByField(String field, Collection filterValues)
    {
        String hql = MessageFormat.format("delete from {0} where {1} in :filters", getPersistentClassMetaData().getEntityName(), field);
        return getSession().createQuery(hql).setParameterList("filters", filterValues).executeUpdate();
    }


    public int deleteAll() {
        String hql = MessageFormat.format("delete from {0}", getPersistentClassMetaData().getEntityName());
        return getSession().createQuery(hql).executeUpdate();
    }


    public void flush() {
        getSession().flush();
    }

    public void evict(Object object) {
        getSession().evict(object);
    }


}
