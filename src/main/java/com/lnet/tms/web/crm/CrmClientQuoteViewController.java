package com.lnet.tms.web.crm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.crm.CrmClientLine;
import com.lnet.tms.model.crm.CrmClientQuote;
import com.lnet.tms.model.crm.CrmClientQuoteView;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.crm.CrmClientLineService;
import com.lnet.tms.service.crm.CrmClientQuoteService;
import com.lnet.tms.service.crm.CrmClientQuoteViewService;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Controller
@RequestMapping("/crmClientQuoteView")
public class CrmClientQuoteViewController extends BaseController<CrmClientQuoteView, UUID, CrmClientQuoteViewService> {

    @Autowired
    public void setBaseService(CrmClientQuoteViewService service) {
        super.setService(service);
    }
    @RequestMapping(value = "/dataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult dataSource(@RequestParam("clientId")  UUID clientId,@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list=request.getFilter().getFilters();
        DataSourceRequest.FilterDescriptor filter =new DataSourceRequest.FilterDescriptor("and", "clientId", "eq", clientId, true);
        request.getFilter().setLogic("and");
        list.add(filter);
        return super.getDataSource(request);
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        if(filters.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:filters){
                if("clientId".equals(filter.getField())){
                    filter.setValue(UUID.fromString((String)filter.getValue()));
                }
            }
        }
        return super.getDataSource(request);
    }
}
