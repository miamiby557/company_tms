package com.lnet.tms.service.base;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.dao.base.BaseRegionViewDao;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.model.base.BaseRegionView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BaseRegionViewService extends BaseService<BaseRegionView, UUID, BaseRegionViewDao> {

    @Autowired
    public void setBaseDao(BaseRegionViewDao dao) {
        super.setDao(dao);
    }

    @Override
    public DataSourceResult getDataSource(DataSourceRequest request) {
        List<DataSourceRequest.SortDescriptor> sorts=request.getSort();
        if(sorts==null){
            sorts = new ArrayList<>();
            DataSourceRequest.SortDescriptor sort=new DataSourceRequest.SortDescriptor();
            sort.setField("code");
            sort.setDir("asc");
            sorts.add(sort);
            request.setSort(sorts);
        }
        return super.getDataSource(request);
    }

    @Transactional
    public String getCityName(UUID uuid){
        BaseRegionView baseRegionView=super.get(uuid);
        if(baseRegionView.getRegionTypeId()==3){
            return baseRegionView.getName();
        }else if(baseRegionView.getRegionTypeId()==4){
            return baseRegionView.getSuperiorRegionName();
        }
        return null;
    }
}
