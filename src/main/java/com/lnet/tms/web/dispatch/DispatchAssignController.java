package com.lnet.tms.web.dispatch;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.dispatch.DispatchAssign;
import com.lnet.tms.model.dispatch.DispatchAssignDetail;
import com.lnet.tms.model.otd.OtdPickupOrder;
import com.lnet.tms.service.dispatch.DispatchAssignService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.service.otd.OtdPickupOrderService;
import com.lnet.tms.service.otd.OtdTransportOrderService;
import com.lnet.tms.service.otd.OtdTransportOrderViewService;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/dispatchAssign")
public class DispatchAssignController extends CrudController<DispatchAssign, UUID, DispatchAssignService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;

    @Autowired
    public void setBaseService(DispatchAssignService service) {
        super.setService(service);
    }

    @Autowired
    private OtdTransportOrderViewService otdTransportOrderViewService;

    @Autowired
    private OtdPickupOrderService otdPickupOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "dispatch/dispatchAssign/index";
    }

    @RequestMapping(value = "/inCity", method = RequestMethod.GET)
    public String inCity() {
        return "dispatch/inCity/index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "dispatch/inCity/search";
    }

    @RequestMapping("/create")
    public String create() {
        return "dispatch/dispatchAssign/create";
    }


    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        DispatchAssign dispatchAssign = service.getWith(id, "details");
        model.addAttribute(dispatchAssign);
        String dispatchAssignJson = mapper.writeValueAsString(dispatchAssign);
        model.addAttribute("dispatchAssignJson", dispatchAssignJson);
        if (dispatchAssign.getDispatchType() == 1) {
            List<UUID> transportOrderIds = new ArrayList<UUID>();
            Set<DispatchAssignDetail> details = dispatchAssign.getDetails();
            if (details != null && details.size() > 0) {
                for (DispatchAssignDetail detail : details) {
                    transportOrderIds.add(detail.getOrderId());
                }
            }
            List list = otdTransportOrderViewService.get(transportOrderIds);
            if (list != null) {
                model.addAttribute("transportOrders", mapper.writeValueAsString(list));
            }
            return "dispatch/dispatchAssign/modify";
        } else {
            List<UUID> pickOrderIds = new ArrayList<UUID>();
            Set<DispatchAssignDetail> details = dispatchAssign.getDetails();
            if (details != null && details.size() > 0) {
                for (DispatchAssignDetail detail : details) {
                    pickOrderIds.add(detail.getOrderId());
                }
            }
            List list = otdPickupOrderService.get(pickOrderIds);
            if (list != null) {
                model.addAttribute("pickupOrders", mapper.writeValueAsString(list));
            }
            return "dispatch/pickUpAndSend/modify";
        }
    }

    @RequestMapping(value = "/send/{id}", method = RequestMethod.GET)
    public String inCitySend(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        DispatchAssign dispatchAssign = service.getWith(id, "details");
        model.addAttribute(dispatchAssign);
        String dispatchAssignJson = mapper.writeValueAsString(dispatchAssign);
        model.addAttribute("dispatchAssignJson", dispatchAssignJson);
        if (dispatchAssign.getDispatchType() == 1) {
            List<UUID> transportOrderIds = new ArrayList<UUID>();
            Set<DispatchAssignDetail> details = dispatchAssign.getDetails();
            if (details != null && details.size() > 0) {
                for (DispatchAssignDetail detail : details) {
                    transportOrderIds.add(detail.getOrderId());
                }
            }
            List list = otdTransportOrderViewService.get(transportOrderIds);
            if (list != null) {
                model.addAttribute("transportOrders", mapper.writeValueAsString(list));
            }
            return "dispatch/inCity/confirm";
        } else {
            List<UUID> pickOrderIds = new ArrayList<UUID>();
            Set<DispatchAssignDetail> details = dispatchAssign.getDetails();
            if (details != null && details.size() > 0) {
                for (DispatchAssignDetail detail : details) {
                    pickOrderIds.add(detail.getOrderId());
                }
            }
            List list = otdPickupOrderService.get(pickOrderIds);
            if (list != null) {
                model.addAttribute("pickupOrders", mapper.writeValueAsString(list));
            }
            return "dispatch/pickUpAndSend/confirmSend";
        }
    }

    @RequestMapping(value = "/createAssign", method = RequestMethod.GET)
    public String createAssign(ModelMap model) throws JsonProcessingException {
        //model.addAttribute("transportOrders", mapper.writeValueAsString(otdTransportOrderViewService.get(transportOrderIds)));
        return "dispatch/dispatchAssign/create";
    }

    @RequestMapping(value = "/createInCity", method = RequestMethod.GET)
    public String createInCity() throws JsonProcessingException {
        //model.addAttribute("transportOrders", mapper.writeValueAsString(otdTransportOrderViewService.get(transportOrderIds)));
        return "dispatch/inCity/create";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        DispatchAssign dispatchAssign = service.getWith(id, "details");
        model.addAttribute(dispatchAssign);
        String dispatchAssignJson = mapper.writeValueAsString(service.get(id));
        model.addAttribute("dispatchAssignJson", dispatchAssignJson);
        model.addAttribute(service.get(id));
        if (dispatchAssign.getDispatchType() == 1) {
            List<UUID> transportOrderIds = new ArrayList<UUID>();
            Set<DispatchAssignDetail> details = dispatchAssign.getDetails();
            if (details != null && details.size() > 0) {
                for (DispatchAssignDetail detail : details) {
                    transportOrderIds.add(detail.getOrderId());
                }
            }
            List list = otdTransportOrderViewService.get(transportOrderIds);
            if (list != null) {
                model.addAttribute("transportOrders", mapper.writeValueAsString(list));
            }
            return "dispatch/dispatchAssign/detail";
        } else {
            List<UUID> pickupOrderIds = new ArrayList<UUID>();
            Set<DispatchAssignDetail> details = dispatchAssign.getDetails();
            if (details != null && details.size() > 0) {
                for (DispatchAssignDetail detail : details) {
                    pickupOrderIds.add(detail.getOrderId());
                }
            }
            List list = otdPickupOrderService.get(pickupOrderIds);
            if (list != null) {
                model.addAttribute("pickupOrders", mapper.writeValueAsString(list));
            }
            return "dispatch/pickUpAndSend/detail";
        }
    }

    @RequestMapping(value = "/pickUpAndSend", method = RequestMethod.GET)
    public String pickUpAndSend() {
        return "dispatch/pickUpAndSend/index";
    }

    @RequestMapping(value = "/getConfirmPickUpOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getConfirmPickUpOrder(@RequestBody DataSourceRequest request) {
        return service.getConfirmPickUpOrder(request);
    }

    @RequestMapping(value = "/confirmPickUpOrder/{pickupOrderId}")
    public String confirmPickUpOrder(@PathVariable UUID pickupOrderId, ModelMap model) throws
            JsonProcessingException {
        OtdPickupOrder OtdPickupOrder = otdPickupOrderService.get(pickupOrderId);
        model.addAttribute(OtdPickupOrder);
        model.addAttribute("otdPickupOrderJson", mapper.writeValueAsString(OtdPickupOrder));
        return "dispatch/pickUpAndSend/confirm";
    }

    @RequestMapping(value = "/pickUpOrderAssign", method = RequestMethod.GET)
    public String pickUpOrderAssign() {
        return "dispatch/pickUpAndSend/createAssign";
    }

    @RequestMapping(value = "/inCitySend", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult inCitySend(@RequestBody DispatchAssign model) {
        try {
            service.inCitySend(model);
            return JsonResult.success();
        } catch (Exception e) {
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/pickupSend", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult pickupSend(@RequestBody DispatchAssign model) {
        try {
            service.pickupSend(model);
            return JsonResult.success();
        } catch (Exception e) {
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/backAssign", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult backAssign(@RequestBody List<UUID> ids) {
        try {
            service.backAssign(ids);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}