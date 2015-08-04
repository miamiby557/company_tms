package com.lnet.tms.web.fee;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.fee.FeeOrderReceivableView;
import com.lnet.tms.service.fee.FeeOrderReceivableViewService;
import com.lnet.tms.web.BaseController;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/feeOrderReceivableView")
public class FeeOrderReceivableViewController extends BaseController<FeeOrderReceivableView, UUID, FeeOrderReceivableViewService> {

    @Autowired
    public void setBaseService(FeeOrderReceivableViewService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("FEEORDERRECEIVABLE_SELECT")
    public String index() {
        return "fee/receivable/index";
    }

    @RequestMapping(value = "/projectAudit")
    @RequiresPermissions("FEEORDERRECEIVABLEPROJECTAUDIT_SELECT")
    public String projectAudit() {
        return "fee/receivable/projectAuditIndex";
    }

    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/getProjectAuditDataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getProjectAuditDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters = request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        if (filters != null && filters.size() > 0) {
            for (DataSourceRequest.FilterDescriptor filter : filters) {
                if ("clientId".equals(filter.getField())) {
                    filter.setValue(UUID.fromString(filter.getValue().toString()));
                }else if("orderDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse((String)filter.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        filters.add(new DataSourceRequest.FilterDescriptor("status", "gte", 2));
        return super.getDataSource(request);
    }

    @RequestMapping(value = "/financingAudit")
    @RequiresPermissions("FEEORDERRECEIVABLEFINANCINGAUDIT_SELECT")
    public String financingAudit() {
        return "fee/receivable/financingAuditIndex";
    }

    @RequestMapping(value = "/getFinancingAuditDataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getFinancingAuditDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters = request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        if (filters != null && filters.size() > 0) {
            for (DataSourceRequest.FilterDescriptor filter : filters) {
                if ("clientId".equals(filter.getField())) {
                    filter.setValue(UUID.fromString(filter.getValue().toString()));
                }else if("orderDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse((String)filter.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        filters.add(new DataSourceRequest.FilterDescriptor("status", "gte", 3));

        return super.getDataSource(request);
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters = request.getFilter().getFilters();
        if (filters != null && filters.size() > 0) {
            for (DataSourceRequest.FilterDescriptor filter : filters) {
                if ("clientId".equals(filter.getField())) {
                    filter.setValue(UUID.fromString(filter.getValue().toString()));
                }else if("orderDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse((String)filter.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else if("feeSaveMark".equals(filter.getField())){
                    if ("1".equals(filter.getValue())){
                        filter.setValue(false);
                    }else if("0".equals(filter.getValue())){
                        filter.setValue(true);
                    }
                }
            }
        }
        return super.getDataSource(request);
    }
}