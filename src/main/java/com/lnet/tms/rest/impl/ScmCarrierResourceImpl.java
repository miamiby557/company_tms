package com.lnet.tms.rest.impl;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.rest.restUtil.ServiceResult;
import com.lnet.tms.rest.spi.ScmCarrierResource;
import com.lnet.tms.service.scm.ScmCarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2015/7/14.
 */
@Service
public class ScmCarrierResourceImpl implements ScmCarrierResource{
    @Autowired
    private ScmCarrierService scmCarrierService;

    @Override
    public ServiceResult getResource() {
        DataSourceResult result = scmCarrierService.getDataSource(new DataSourceRequest());
        List<ScmCarrier> scmCarrierList = (List<ScmCarrier>)result.getData();
        ServiceResult serviceResult = new ServiceResult(scmCarrierList);
        return serviceResult;
    }

}
