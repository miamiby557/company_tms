package com.lnet.tms.service;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Transactional
public abstract class BaseService<T, ID extends Serializable, DAO extends BaseDao<T, ID>> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DAO dao;

    protected void setDao(DAO dao) {
        this.dao = dao;
    }

    public DAO getDao() {
        return this.dao;
    }

    // query methods

    public T load(ID id) {
        return dao.load(id);
    }


    public <S> List<S> sqlQuery(String sql, Object... parameters) {
        return dao.sqlQuery(sql, parameters);
    }

    public <S> List<S> hqlQuery(String hql, Object... parameters) {
        return dao.hqlQuery(hql, parameters);
    }


    public DataSourceResult getDataSource(DataSourceRequest request) {
        return dao.getDataSource(request);
    }


    public boolean exists(ID id) {
        return dao.exists(id);
    }

    public boolean existsBy(String field, Object value) {
        return dao.existsBy(field, value);
    }

    public boolean existsBy(String field, Object value, ID excludedId) {
        return dao.existsBy(field, value, excludedId);
    }


    public T getByField(String field, Object value) {
        return dao.getByField(field, value);
    }

    public T get(ID id) {
        return dao.get(id);
    }

    public T getWith(ID id, String... associationPaths) {
        return dao.getWith(id, associationPaths);
    }

    public List<T> get(ID... ids) {
        return dao.get(ids);
    }
    public List<T> get(List<ID> ids) {
        return dao.get(ids);
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public List<T> getAllWith(String... associationPaths) {
        return dao.getAllWith(associationPaths);
    }

    public List<T> getHierarchicalData(String parentField, String childrenField, ID id) {
        return dao.getHierarchicalData(parentField, childrenField, id);
    }

    public List<T> getAllByField(String field, Object value) {
        return dao.getAllByField(field, value);
    }

    public List<T> getAllByFieldDesc(String field, Object value,String descName) {
        return dao.getAllByFieldDesc(field, value,descName);
    }

    public List<T> getAllByAsc(String ascName) {
        return dao.getAllByAsc(ascName);
    }

    public List<T> getAllByFieldWith(String field, Object value, String... associationPaths) {
        return dao.getAllByFieldWith(field, value, associationPaths);
    }

    public List<T> getAllByField(Map<String, Object> map) {
        return dao.getAllByField(map);
    }

    public List<Map<String, Object>> getListItems(String textField, String valueField) {
        return dao.getListItems(textField, valueField);
    }

    public T getByField(Map<String, Object> map){
        return dao.getByField(map);
    }
}
