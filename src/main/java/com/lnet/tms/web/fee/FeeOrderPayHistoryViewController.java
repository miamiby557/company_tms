package com.lnet.tms.web.fee;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.fee.FeeOrderPayHistoryView;
import com.lnet.tms.service.fee.FeeOrderPayHistoryViewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.BaseController;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/feeOrderPayHistoryView")
public class FeeOrderPayHistoryViewController extends BaseController<FeeOrderPayHistoryView, UUID, FeeOrderPayHistoryViewService> {

    @Autowired
    public void setBaseService(FeeOrderPayHistoryViewService service) {
        super.setService(service);
    }

    @RequestMapping("/index/{orderPayableId}")
    public String index(@PathVariable UUID orderPayableId, ModelMap model){
        model.addAttribute("orderPayableId",orderPayableId);
        return "fee/FeeOrderPayable/history";
    }

    @RequestMapping(value="getDataSourceById",method = RequestMethod.POST)
    public @ResponseBody
    DataSourceResult getDataSourceById(@RequestBody DataSourceRequest request,@RequestParam("orderPayableId")UUID orderPayableId){
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        filters.add(new DataSourceRequest.FilterDescriptor("orderPayableId","eq",orderPayableId));
        return service.getDataSource(request);
    }
}