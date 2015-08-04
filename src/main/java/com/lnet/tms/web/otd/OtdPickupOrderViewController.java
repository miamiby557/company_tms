package com.lnet.tms.web.otd;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.otd.OtdPickupOrderView;
import com.lnet.tms.service.otd.OtdPickupOrderViewService;
import com.lnet.tms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/otdPickerOrderView")
public class OtdPickupOrderViewController extends BaseController<OtdPickupOrderView, UUID, OtdPickupOrderViewService> {

    @Autowired
    public void setBaseService(OtdPickupOrderViewService service) {
        super.setService(service);
    }


    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        if(list!=null&&list.size()>0){
            for(DataSourceRequest.FilterDescriptor filter: list){
                if("clientId".equals(filter.getField())){
                    filter.setValue(UUID.fromString(filter.getValue().toString()));
                }
                if("processStatus".equals(filter.getField())){
                    filter.setValue(Integer.parseInt(filter.getValue().toString()));
                }
            }
        }
        return super.getDataSource(request);
    }
}