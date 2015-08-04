package com.lnet.tms.rest.spi;

import com.lnet.tms.rest.restUtil.ServiceResult;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by admin on 2015/7/9.
 */
@Path(value = "/crmClient")
@Produces({"application/json"})
public interface CrmClientResource {
    @POST
    @Path(value = "/getResource")
    ServiceResult getCrmClient();

    @GET
    @Path(value = "/getCityList")
    ServiceResult getCityList();
}
