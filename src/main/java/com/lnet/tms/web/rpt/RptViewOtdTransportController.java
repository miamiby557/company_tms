package com.lnet.tms.web.rpt;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.rpt.RptViewOtdTransport;
import com.lnet.tms.service.rpt.RptViewOtdTransportService;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/rptViewOtdTransport")
public class RptViewOtdTransportController extends CrudController<RptViewOtdTransport, UUID, RptViewOtdTransportService> {

    @Autowired
    public void setBaseService(RptViewOtdTransportService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ORDERTRANSPORT_SELECT")
    public String index() {
      return "rpt/rptViewOtdTransport/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "rpt/rptViewOtdTransport/create";
    }

    @RequestMapping(value="/modify",method = RequestMethod.GET)
    public String modify(@RequestParam("id") UUID id, ModelMap model) {
        model.addAttribute("id",id);
        return "rpt/rptViewOtdTransport/modify";
    }

    @RequestMapping(value="/details",method = RequestMethod.GET)
    @ResponseBody
    public RptViewOtdTransport details(@RequestParam("id") UUID id) {
        return service.get(id);
    }

    @RequestMapping(value = "/getDataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    @Override
    DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(list!=null&&list.size()>0){
            for(DataSourceRequest.FilterDescriptor filter: list){
                if(("beginDate".equals(filter.getField()))||"endDate".equals(filter.getField())){
                    filter.setField("orderDate");
                    try {
                        filter.setValue(sdf.parse(filter.getValue().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if("clientId".equals(filter.getField())){
                    filter.setValue(UUID.fromString(filter.getValue().toString()));
                }
            }
        }
        return service.getDataSource(request);
    }
}