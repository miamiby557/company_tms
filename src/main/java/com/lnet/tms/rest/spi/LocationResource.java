package com.lnet.tms.rest.spi;

import com.lnet.tms.rest.restUtil.DriverRequest;
import com.lnet.tms.rest.restUtil.LocationRequest;
import com.lnet.tms.rest.restUtil.ServiceResult;

import javax.ws.rs.*;

/**
 * Created by admin on 2015/7/18.
 */
@Path(value = "/location")
@Produces({"application/json"})
public interface LocationResource {

    @POST
    @Path("/receiveLocation")
    @Consumes({"application/json"})
    public ServiceResult receiveLocation(LocationRequest locationRequest);

    @POST
    @Path("/carSend")
    ServiceResult carSend(DriverRequest request);

    @POST
    @Path("/carBack")
    ServiceResult carBack(DriverRequest request);

}
