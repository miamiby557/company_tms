package com.lnet.tms.web.otd;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.otd.OtdTransportOrderView;
import com.lnet.tms.service.otd.OtdTransportOrderViewService;
import com.lnet.tms.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/otdTransportOrderView")
public class OtdTransportOrderViewController extends BaseController<OtdTransportOrderView, UUID, OtdTransportOrderViewService> {

    @Autowired
    public void setBaseService(OtdTransportOrderViewService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("TRANSPORTORDER_SELECT")
    public String index(){
        return "otd/otdTransportOrder/index";
    }

    @RequestMapping(value="/receipt",method = RequestMethod.GET)
    @RequiresPermissions("ORDERBACK_MANAGE")
//    @RequiresPermissions("ORDERBACK_SELECT")
    public String receipt(){
        return "receipt/index";
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        request.getFilter().setLogic("and");
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
                }
            }
        }

        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("orderDate", "desc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            request.setSort(sorts);
        }
        return super.getDataSource(request);
    }

    @RequestMapping(value="/getNotMergeOrder",method = RequestMethod.POST)
    public @ResponseBody DataSourceResult getNotMergeOrder(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        request.getFilter().setLogic("and");
        filters.add(new DataSourceRequest.FilterDescriptor("mergeStatus","lt",3));
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
                }
            }
        }

        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("orderDate", "desc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            request.setSort(sorts);
        }
        return super.getDataSource(request);
    }

    /**
     * 查询已确认的订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/getOkTransportOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getOkTransportOrder(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters = request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        filters.add(new DataSourceRequest.FilterDescriptor("status","eq",2));
              return service.getDataSource(request);
    }
    @RequestMapping(value = "/getOutCityOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getOutCityOrder(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters = request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        filters.add(new DataSourceRequest.FilterDescriptor("status","eq",2));
        filters.add(new DataSourceRequest.FilterDescriptor("orderDispatchType","eq",2));
        return service.getDataSource(request);
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public    @ResponseBody    ModelMap getCount(){
        return service.getCount();
    }


    @RequestMapping("/getMergeSearchDataSource/{clientId}")
    public @ResponseBody  DataSourceResult  getMergeSearchDataSource(@PathVariable UUID clientId,@RequestBody DataSourceRequest request){
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        filters.add(new DataSourceRequest.FilterDescriptor("clientId","eq",clientId));
        filters.add(new DataSourceRequest.FilterDescriptor("mergeStatus","eq",1));
        filters.add(new DataSourceRequest.FilterDescriptor("status","neq",-1));
        filters.add(new DataSourceRequest.FilterDescriptor("status","neq",6));

        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null || sorts.size()==0)
        {
            sorts=new ArrayList<>();
        }
        sorts.add(new DataSourceRequest.SortDescriptor("orderDate","desc"));
        sorts.add(new DataSourceRequest.SortDescriptor("receiveMan","asc"));
        sorts.add(new DataSourceRequest.SortDescriptor("transportType","asc"));
        request.setSort(sorts);
        return super.getDataSource(request);
    }
}

