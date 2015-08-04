package com.lnet.tms.web.fee;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.fee.FeeOrderReceiveHistoryView;
import com.lnet.tms.service.fee.FeeOrderReceiveHistoryViewService;
import com.lnet.tms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/feeOrderReceiveHistoryView")
public class FeeOrderReceiveHistoryViewController extends BaseController<FeeOrderReceiveHistoryView, UUID, FeeOrderReceiveHistoryViewService> {

    @Autowired
    public void setBaseService(FeeOrderReceiveHistoryViewService service) {
        super.setService(service);
    }

    @RequestMapping("/index/{orderReceivableId}")
    public String index(@PathVariable UUID orderReceivableId, ModelMap model){
        model.addAttribute("orderReceivableId",orderReceivableId);
        return "fee/receivable/history";
    }

    @RequestMapping(value="getDataSourceById",method = RequestMethod.POST)
    public @ResponseBody
    DataSourceResult getDataSourceById(@RequestBody DataSourceRequest request,@RequestParam("orderReceivableId")UUID orderReceivableId){
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        filters.add(new DataSourceRequest.FilterDescriptor("orderReceivableId","eq",orderReceivableId));
        return service.getDataSource(request);
    }
}