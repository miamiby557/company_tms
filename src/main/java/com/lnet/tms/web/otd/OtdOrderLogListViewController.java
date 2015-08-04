package com.lnet.tms.web.otd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.otd.OtdOrderLogListView;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.model.otd.OtdTransportOrderLogView;
import com.lnet.tms.service.otd.OtdOrderLogListViewService;
import com.lnet.tms.service.otd.OtdTransportOrderLogViewService;
import com.lnet.tms.service.otd.OtdTransportOrderService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdOrderLogListView")
public class OtdOrderLogListViewController extends BaseController<OtdOrderLogListView, UUID, OtdOrderLogListViewService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    private OtdTransportOrderLogViewService otdTransportOrderLogViewService;
    @Autowired
    private OtdTransportOrderService otdTransportOrderService;

    @Autowired
    public void setBaseService(OtdOrderLogListViewService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ORDERLOG_SELECT")
    public String index(){
        return "otd/otdTransportOrderLog/orderIndex";
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if(filters.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:filters){
                if("orderDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse((String)filter.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else if("sentDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse((String)filter.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else if("clientId".equals(filter.getField())){
                    filter.setValue(UUID.fromString((String)filter.getValue()));
                }else if("isAbnormal".equals(filter.getField())){
                    if("0".equals(filter.getValue())){
                        filter.setValue(false);
                    }else if("1".equals(filter.getValue())){
                        filter.setValue(true);
                    }
                }
            }
        }
        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("orderDate", "desc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            sorts.add( new DataSourceRequest.SortDescriptor("sentDate", "desc"));
            sorts.add( new DataSourceRequest.SortDescriptor("receiveMan", "asc"));
            request.setSort(sorts);
        }

        return super.getDataSource(request);
    }
}

