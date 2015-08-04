package com.lnet.tms.service;

import com.lnet.tms.dao.CrudDao;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Transactional
public abstract class CrudService<T, ID extends Serializable, DAO extends CrudDao<T, ID>> extends BaseService<T, ID, DAO> {

    // create

    public ID create(T model) {
        return getDao().create(model);
    }

    public List<ID> create(List<T> models) {
        List<ID> result = new ArrayList<>();
        for (T model : models) {
            result.add(create(model));
        }

        return result;
    }

    // update

    public void update(T model) {
        getDao().update(model);
    }

    // 更新包含的字段
    public void updateIncluded(T model, String... includedProperties) {
        getDao().updateIncluded(model, includedProperties);
    }

    // 更新排除的字段
    public void updateExcluded(T model, String... excludedProperties) {
        getDao().updateExcluded(model, excludedProperties);
    }

    public void update(List<T> models) {
        for (T model : models) {
            update(model);
        }
    }

    public T merge(T model) {
        return getDao().merge(model);
    }


    public void saveOrUpdate(T model) {
        getDao().saveOrUpdate(model);
    }


    public void saveOrUpdate(List<T> models) {
        getDao().saveOrUpdate(models);
    }

    // delete
    public void deleteById(ID id) {
        getDao().deleteById(id);
    }

    public void deleteById(List<ID> ids) {
        getDao().deleteById(ids);
    }

    public void feeAuditPayable(List<ID> ids){
        getDao().feeAuditPayable(ids);
    }

    public void feeAuditReceivable(List<ID> ids){
        getDao().feeAuditPayable(ids);
    }

    public void delete(T model) {
        getDao().delete(model);
    }

    public void delete(List<T> models) {
        getDao().delete(models);
    }

    public int deleteAll() {
        return getDao().deleteAll();
    }

    public List<T> getList(String field,String value,ID userId,String descName){
        return getDao().getList(field,value,userId,descName);
    }

}
