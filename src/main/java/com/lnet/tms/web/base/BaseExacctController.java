package com.lnet.tms.web.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.base.BaseExacct;
import com.lnet.tms.service.base.BaseExacctService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/baseExacct")
public class BaseExacctController extends CrudController<BaseExacct, UUID, BaseExacctService> {
    @Autowired
    private HibernateAwareObjectMapper mapper;

    @Autowired
    public void setBaseService(BaseExacctService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ORDERBASEEXACCT_SELECT")
    public String index() {
      return "base/exacct/index";
    }

    @RequestMapping(value="/create",method = RequestMethod.GET)
    @RequiresPermissions("ORDERBASEEXACCT_CREATE")
    public String create() {
        return "base/exacct/create";
    }

    @RequestMapping("/modify/{id}")
    @RequiresPermissions("ORDERBASEEXACCT_MODIFY")
    public String modify(@PathVariable UUID id, ModelMap model) throws JsonProcessingException {
        BaseExacct baseExacct=service.get(id);
        model.addAttribute("baseExacct",baseExacct);
        model.addAttribute("baseExacctJson",mapper.writeValueAsString(baseExacct));
        return "base/Exacct/modify";
    }

    @Override
    public JsonResult create(@RequestBody BaseExacct model) {
        if(service.existsBy("code",model.getCode())){
            return JsonResult.error("费用科目代码已存在！");
        }
        return super.create(model);
    }

    @Override
    public JsonResult update(@RequestBody BaseExacct model) {
        if(service.existsBy("code",model.getCode(),model.getExacctId())){
            return JsonResult.error("费用科目代码已存在！");
        }
        return super.update(model);
    }

    @RequestMapping(value = "/getDataSourceByType",method = RequestMethod.POST)
    public @ResponseBody
    DataSourceResult getDataSourceByType(@RequestParam("type")String type,@RequestBody DataSourceRequest request){
        BaseExacct exacct=service.getByField("code",type);
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        filters.add(new DataSourceRequest.FilterDescriptor("superiorId","eq",exacct.getExacctId()));
        return super.getDataSource(request);
    }

    @RequestMapping(value = "/getHierarchicalData", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<BaseExacct> getHierarchicalData(@RequestBody DataSourceRequest request){
        UUID exacctId  = null;
        if(request.getData().get("exacctId")!=null) {
            exacctId = UUID.fromString(request.getData().get("exacctId").toString());
        }
        return service.getHierarchicalData("superiorId", "subExaccts", exacctId);
    }
    @RequestMapping(value = "/getExacctList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<BaseExacct> getExacctList(){
        return  service.getHierarchicalData("superiorId", "subExaccts", null);
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.SortDescriptor>sorts=request.getSort();
        if(sorts==null) {
            DataSourceRequest.SortDescriptor sort = new DataSourceRequest.SortDescriptor("code", "asc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            request.setSort(sorts);
        }
        return super.getDataSource(request);
    }
    @Override
    @RequiresPermissions("ORDERBASEEXACCT_REMOVE")
    public JsonResult deleteById(@RequestBody UUID uuid){
        return  super.deleteById(uuid);
    }
}