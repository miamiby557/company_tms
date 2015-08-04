package com.lnet.tms.web.crm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.crm.CrmClientLine;
import com.lnet.tms.service.crm.CrmClientLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/crmClientLine")
public class CrmClientLineController extends CrudController<CrmClientLine, UUID, CrmClientLineService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(CrmClientLineService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "crm/crmClientLine/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "crm/crmClientLine/create";
    }

    @RequestMapping(value="/modify/{clientLineId}")
    public String modify(@PathVariable("clientLineId") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("UUIDJson",UUIDJson);
        return "crm/crmClientLine/modify";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
        public String detail(@RequestParam("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("UUIDJson",UUIDJson);
        return "crm/crmClientLine/modify";
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

    @RequestMapping(value="/getLineByCity",method = RequestMethod.POST)
    public @ResponseBody CrmClientLine getLineByCity(@RequestBody CrmClientLine line){
        Map filter = new HashMap();
        filter.put("clientId",line.getClientId());
        filter.put("startCityId",line.getStartCityId());
        filter.put("destCityId",line.getDestCityId());
        filter.put("transportType" ,line.getTransportType());
        List<CrmClientLine> list =service.getAllByField(filter);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}