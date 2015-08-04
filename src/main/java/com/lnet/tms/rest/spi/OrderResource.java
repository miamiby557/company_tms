package com.lnet.tms.rest.spi;


import com.lnet.tms.model.otd.OtdCarrierOrder;
import com.lnet.tms.model.otd.OtdCarrierOrderBean;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.rest.restUtil.FeeOrderPayables;
import com.lnet.tms.rest.restUtil.OrderListRequest;
import com.lnet.tms.rest.restUtil.ServiceResult;

import javax.ws.rs.*;
import java.util.UUID;

/**
 * Created by admin on 2015/7/10.
 */
@Path(value = "/order")
@Produces({"application/json"})
public interface OrderResource {

    @GET
    @Path("/transportOrder/{orderNumber}")
    ServiceResult getTransportOrderByNumber(@PathParam("orderNumber")String orderNumber);

    @GET
    @Path("/getTransportOrderById/{orderId}")
    ServiceResult getTransportOrderById(@PathParam("orderId")UUID orderId);

    @GET
    @Path("/carrierOrder/{orderNumber}")
    ServiceResult getCarrierOrderByNumber(@PathParam("orderNumber")String orderNumber);

    @GET
    @Path("/getCarrierOrderById/{orderId}")
    ServiceResult getCarrierOrderById(@PathParam("orderId")UUID orderId);

    @POST
    @Path(value = "/transportOrderListByNumber")
    @Consumes({"application/json"})
    ServiceResult transportOrderListByNumber(OrderListRequest request);

    @GET
    @Path(value = "/transportOrderList/{userId}")
    ServiceResult getTransportOrderList(@PathParam("userId")UUID userId);


    @POST
    @Path(value = "/carrierOrderListByNumber")
    @Consumes({"application/json"})
    ServiceResult carrierOrderListByNumber(OrderListRequest request);

    @GET
    @Path(value = "/carrierOrderList/{userId}")
    ServiceResult getCarrierOrderList(@PathParam("userId")UUID userId);

    @POST
    @Path(value = "/transportOrderCreate")
    @Consumes({"application/json"})
    ServiceResult transportOrderCreate(OtdTransportOrder otdTransportOrder);

    @POST
    @Path(value = "/carrierOrderCreate")
    @Consumes({"application/json"})
    ServiceResult carrierOrderCreate(OtdCarrierOrderBean otdCarrierOrder);

    @POST
    @Path("/calculate")
    @Consumes({"application/json"})
    ServiceResult calculate(OtdCarrierOrder otdCarrierOrder);

    @POST
    @Path("/updatePayable/{orderPayableId}")
    @Consumes({"application/json"})
    ServiceResult updatePayable(@PathParam("orderPayableId")UUID orderPayableId,FeeOrderPayables payableList);

    @GET
    @Path("/getSupperBaseRegion")
    ServiceResult getSupperBaseRegion();

    @GET
    @Path("/getChildBaseRegion/{superRegionId}")
    ServiceResult getChildBaseRegion(@PathParam("superRegionId")UUID superRegionId);

    @GET
    @Path("/getOrganization")
    ServiceResult getOrganization();

}
