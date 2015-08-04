package com.lnet.tms.web.otd;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.otd.OtdCarrierOrderLogView;
import com.lnet.tms.service.otd.OtdCarrierOrderLogViewService;
import com.lnet.tms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdCarrierOrderLogView")
public class OtdCarrierOrderLogViewController extends BaseController<OtdCarrierOrderLogView, UUID, OtdCarrierOrderLogViewService> {

    @Autowired
    public void setBaseService(OtdCarrierOrderLogViewService service) {
        super.setService(service);
    }


    @RequestMapping(value = "/dataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult dataSource(@RequestParam("carrierOrderId")  UUID carrierOrderId,@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(list.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:list){
                if("operationDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse((String)filter.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else if("currentStatus".equals(filter.getField())){
                    filter.setValue(Integer.parseInt((String) filter.getValue()));
                }
            }
        }
        list.add(new DataSourceRequest.FilterDescriptor("and", "carrierOrderId", "eq", carrierOrderId, true));
        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("operationDate", "desc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            request.setSort(sorts);
        }
        return super.getDataSource(request);
    }

}