
package com.lnet.tms.dao.sys;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.sys.SysFunction;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class SysFunctionDao extends CrudDao<SysFunction, UUID> {
    @Override
    public DataSourceResult getDataSource(DataSourceRequest request) {
        List<DataSourceRequest.SortDescriptor> sorts =  request.getSort();
        if(sorts==null||sorts.size()==0){
            sorts = new ArrayList<>();
            sorts.add(new DataSourceRequest.SortDescriptor("code","asc"));
        }
        request.setSort(sorts);
        return super.getDataSource(request);
    }

    @Override
    public List<SysFunction> getAll() {
        return getSession().createCriteria(SysFunction.class).addOrder(Order.asc("code")).list();
    }
}