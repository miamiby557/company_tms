package com.lnet.tms.rest.spi;

import com.lnet.tms.rest.restUtil.ServiceResult;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by admin on 2015/7/14.
 */
@Path(value = "/scmCarrier")
@Produces({"application/json"})
public interface ScmCarrierResource {
    @POST
    @Path(value = "/getResource")
    public ServiceResult getResource();

}
