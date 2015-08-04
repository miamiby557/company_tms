package com.lnet.tms.web.rpt;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.rpt.RptViewOtdMonthsum;
import com.lnet.tms.service.rpt.RptViewOtdMonthsumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.org.mozilla.javascript.internal.regexp.SubString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/rptViewOtdMonthsum")
public class RptViewOtdMonthsumController extends CrudController<RptViewOtdMonthsum, UUID, RptViewOtdMonthsumService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(RptViewOtdMonthsumService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ORDERMONTHSUM_SELECT")
    public String index() {
      return "rpt/rptViewOtdMonthsum/index";
    }
    @RequestMapping("/crossProfit")
    @RequiresPermissions("ORDERTRANSPORTCROSS_SELECT")
    public String crossProfit() {
        return "rpt/rptViewOtdMonthsum/crossProfitIndex";
    }

    @RequestMapping("/create")
    public String create() {
     return "rpt/rptViewOtdMonthsum/create";
    }

    @RequestMapping(value="/modify",method = RequestMethod.GET)
    public String modify(@RequestParam("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("UUIDJson",UUIDJson);
        return "rpt/rptViewOtdMonthsum/modify";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
        public String detail(@RequestParam("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("UUIDJson",UUIDJson);
        return "rpt/rptViewOtdMonthsum/modify";
    }

    @RequestMapping(value = "/getDataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    @Override
    DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        String orderDate;
        if(list!=null&&list.size()>0){
            for(DataSourceRequest.FilterDescriptor filter: list){
                if(("beginDate".equals(filter.getField()))||"endDate".equals(filter.getField())){
                    filter.setField("orderMonth");
                    orderDate=filter.getValue().toString().substring(0,7);
                    filter.setValue(orderDate);
                }
                if("clientId".equals(filter.getField())){
                    filter.setValue(UUID.fromString(filter.getValue().toString()));
                }
            }
        }
        return service.getDataSource(request);
    }
}