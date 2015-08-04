package com.lnet.tms.web.scm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.scm.ScmCarrierLine;
import com.lnet.tms.service.scm.ScmCarrierLineService;
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
@RequestMapping("/scmCarrierLine")
public class ScmCarrierLineController extends CrudController<ScmCarrierLine, UUID, ScmCarrierLineService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(ScmCarrierLineService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "scm/carrierLine/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "scm/carrierLine/create";
    }

    @RequestMapping(value="/modify/{carrierLineId}")
    public String modify(@PathVariable("carrierLineId") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("UUIDJson",UUIDJson);
        return "scm/carrierLine/modify";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
        public String detail(@RequestParam("id") UUID id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String UUIDJson =mapper.writeValueAsString(service.get(id));
        model.addAttribute("UUIDJson",UUIDJson);
        return "/views/crm/crmClientLine/modify.ftl";
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

    @RequestMapping(value="/getLineByCity",method = RequestMethod.POST)
    public @ResponseBody ScmCarrierLine getLineByCity(@RequestBody ScmCarrierLine line){
        Map filter = new HashMap();
        filter.put("carrierId",line.getCarrierId());
        filter.put("startCityId",line.getStartCityId());
        filter.put("destCityId",line.getDestCityId());
        filter.put("transportType" ,line.getTransportType());
        List<ScmCarrierLine> list =service.getAllByField(filter);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}