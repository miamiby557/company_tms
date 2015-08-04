package com.lnet.tms.rest.impl;

import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.rest.restUtil.DriverRequest;
import com.lnet.tms.rest.restUtil.LocationRequest;
import com.lnet.tms.rest.restUtil.ServiceResult;
import com.lnet.tms.rest.spi.LocationResource;
import com.lnet.tms.service.base.BaseRegionService;
import com.lnet.tms.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * Created by admin on 2015/7/18.
 */
public class LocationResourceImpl implements LocationResource{
    @Autowired
    private BaseRegionService baseRegionService;

    @Override
    public ServiceResult receiveLocation(LocationRequest locationRequest) {
        Timestamp timestamp= DateUtils.getTimestampNow();
        System.out.println("city:"+ locationRequest.getCity()+" time:"+timestamp.toString());
        BaseRegion baseRegion = baseRegionService.getByField("name", locationRequest.getCity());
        ServiceResult result = new ServiceResult(baseRegion);
        System.out.println("返回："+baseRegion.getRegionId());
        return result;
    }

    @Override
    public ServiceResult carSend(DriverRequest request) {
        ServiceResult result = new ServiceResult();
        return result;
    }

    @Override
    public ServiceResult carBack(DriverRequest request) {
        ServiceResult result = new ServiceResult();
        return result;
    }
}
