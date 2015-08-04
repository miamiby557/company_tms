package com.lnet.tms.web.scm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.scm.ScmCarrierQuoteView;
import com.lnet.tms.model.scm.ScmCarrierView;
import com.lnet.tms.service.scm.ScmCarrierQuoteViewService;
import com.lnet.tms.service.scm.ScmCarrierViewService;
import com.lnet.tms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/scmCarrierQuoteView")
public class ScmCarrierQuoteViewController extends BaseController<ScmCarrierQuoteView,UUID,ScmCarrierQuoteViewService> {

    @Autowired
    public void setBaseService(ScmCarrierQuoteViewService service) {
        super.setService(service);
    }

    @RequestMapping("/quote/{carrierId}")
    public String quote(@PathVariable UUID carrierId, ModelMap model){
        model.addAttribute("carrierId",carrierId);
        return "scm/carrierQuote/index";
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        if(filters.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:filters){
                if("carrierId".equals(filter.getField())){
                    filter.setValue(UUID.fromString((String)filter.getValue()));
                }
            }
        }
        return super.getDataSource(request);
    }
}
