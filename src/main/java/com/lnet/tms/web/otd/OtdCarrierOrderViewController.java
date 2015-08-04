package com.lnet.tms.web.otd;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.otd.OtdCarrierOrderView;
import com.lnet.tms.service.otd.OtdCarrierOrderViewService;
import com.lnet.tms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdCarrierOrderView")
public class OtdCarrierOrderViewController extends BaseController<OtdCarrierOrderView, UUID, OtdCarrierOrderViewService> {

    @Autowired
    public void setBaseService(OtdCarrierOrderViewService service) {
        super.setService(service);
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        if(list!=null&&list.size()>0){
            for(DataSourceRequest.FilterDescriptor filter: list){
                if("carrierId".equals(filter.getField())){
                    filter.setValue(UUID.fromString(filter.getValue().toString()));
                }
                if("sendDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse((String)filter.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("createDate", "desc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            request.setSort(sorts);
        }
        return super.getDataSource(request);
    }
}