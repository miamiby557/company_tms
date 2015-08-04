package com.lnet.tms.service.fee;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.fee.FeeOrderReceivableViewDao;
import com.lnet.tms.model.fee.FeeOrderReceivableView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Service
public class FeeOrderReceivableViewService extends BaseService<FeeOrderReceivableView, UUID, FeeOrderReceivableViewDao> {

    @Autowired
    public void setBaseDao(FeeOrderReceivableViewDao dao) {
        super.setDao(dao);
    }

    @Override
    public DataSourceResult getDataSource(DataSourceRequest request) {
        List<DataSourceRequest.SortDescriptor> sorts = request.getSort();
        if(sorts==null || sorts.size()==0) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("status", "asc");
            DataSourceRequest.SortDescriptor sort2 = new DataSourceRequest.SortDescriptor("orderDate", "desc");
            sorts = new ArrayList<>();
            sorts.add(sort);
            sorts.add(sort2);
            request.setSort(sorts);
        }
        return super.getDataSource(request);
    }
}
