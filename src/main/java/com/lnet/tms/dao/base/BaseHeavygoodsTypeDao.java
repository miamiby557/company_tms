
package com.lnet.tms.dao.base;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.base.BaseHeavygoodsType;
import com.lnet.tms.utility.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BaseHeavygoodsTypeDao extends CrudDao<BaseHeavygoodsType, UUID> {

    public DataSourceResult getDataSource(DataSourceRequest request,UUID clientId){
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        if(StringUtils.isEmpty(clientId)){
            DataSourceResult result= super.getDataSource(request);
            List<BaseHeavygoodsType> list= (List<BaseHeavygoodsType>) result.getData();
            List<BaseHeavygoodsType> list1=new ArrayList<>();
            if(list!=null && list.size()>0){
                for(BaseHeavygoodsType type:list){
                    if(StringUtils.isEmpty(type.getClientId())){
                        list1.add(type);
                    }
                }
            }
            result.setData(list1);
            return result;
        }else{
            filters.add(new DataSourceRequest.FilterDescriptor("clientId","eq",clientId));
            return super.getDataSource(request);
        }
    }

    public List<BaseHeavygoodsType> getListByClientAndTypeId(UUID clientId,UUID heavygoodsTypeId){
        Criteria criteria = getSession().createCriteria(BaseHeavygoodsType.class);
        if(!StringUtils.isEmpty(clientId)){
            criteria = criteria.add(Restrictions.eq("clientId", clientId));
        }
        if(!StringUtils.isEmpty(heavygoodsTypeId)) {
            criteria.add(Restrictions.not(Restrictions.idEq(heavygoodsTypeId)));
        }
        return criteria.list();
    }

}