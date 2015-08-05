package com.lnet.tms.rest.impl;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.model.crm.CrmClient;
import com.lnet.tms.rest.restUtil.ServiceResult;
import com.lnet.tms.rest.spi.CrmClientResource;
import com.lnet.tms.service.base.BaseRegionService;
import com.lnet.tms.service.crm.CrmClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by admin on 2015/7/9.
 */
public class CrmClientResourceImpl implements CrmClientResource{

    @Autowired
    private CrmClientService crmClientService;

    @Autowired
    BaseRegionService baseRegionService;

    @Override
    public ServiceResult getCrmClient() {

        DataSourceResult dataSource = crmClientService.getDataSource(new DataSourceRequest());
        List<CrmClient> crmClientList = (List<CrmClient>)dataSource.getData();
        ServiceResult result = new ServiceResult(crmClientList);
        return result;
    }

    @Override
    public ServiceResult getCityList() {

        DataSourceResult dataSourceResult = baseRegionService.getDataSource(new DataSourceRequest());
        List<BaseRegion> baseRegionList = (List<BaseRegion>)dataSourceResult.getData();
        ServiceResult result = new ServiceResult(baseRegionList);
        return result;
    }
}
