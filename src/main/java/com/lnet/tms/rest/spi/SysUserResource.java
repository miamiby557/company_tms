package com.lnet.tms.rest.spi;

import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.rest.restUtil.ServiceResult;
import com.lnet.tms.rest.restUtil.UpdatePwd;

import javax.ws.rs.*;
import java.util.UUID;

@Path(value = "/sysUser")
@Produces({"application/json"})
public interface SysUserResource {

    @GET
    @Path(value = "/{id}")
    ServiceResult getUserInfo(@PathParam("id")UUID id);

    @POST
    @Path(value = "/validate")
    @Consumes({"application/json"})
    ServiceResult validate(@FormParam("username") String username, @FormParam("password") String password);

    @POST
    @Path(value = "/login")
    @Consumes({"application/json"})
    ServiceResult login(SysUser user);

    @POST
    @Path("/logout")
    ServiceResult logout();

    @GET
    @Path("/testService")
    ServiceResult testService();

    @POST
    @Path("/updatePwd")
    @Consumes({"application/json"})
    ServiceResult updatePwd(UpdatePwd updatePwd);
}
