package com.lnet.tms.web.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.base.BaseHeavygoodsType;
import com.lnet.tms.service.base.BaseHeavygoodsTypeService;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/baseHeavygoodsType")
public class BaseHeavygoodsTypeController extends CrudController<BaseHeavygoodsType, UUID, BaseHeavygoodsTypeService> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(BaseHeavygoodsTypeService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "base/heavyGoodsType/index";
    }

    @RequestMapping(value="/create",method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        modelMap.addAttribute("clientId",null);
        return "base/heavyGoodsType/create";
    }


    @RequestMapping(value="/create/{clientId}")
    public String create(@PathVariable UUID clientId,ModelMap modelMap){
        modelMap.addAttribute("clientId",clientId);
        return "base/heavyGoodsType/create";
    }

    @RequestMapping("/modify/{heavygoodsTypeId}")
    public String modify(@PathVariable UUID heavygoodsTypeId,ModelMap model) throws JsonProcessingException {
        model.addAttribute("baseHeavygoodsType",mapper.writeValueAsString(service.get(heavygoodsTypeId)));
        return "base/heavyGoodsType/modify";
    }

    @Override
    public JsonResult create(@RequestBody BaseHeavygoodsType model) {
        return service.createType(model);
    }

    @Override
    public JsonResult update(@RequestBody BaseHeavygoodsType model) {
        return service.updateType(model);
    }

    @RequestMapping(value = "/dataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult dataSource(@RequestParam("clientId")  UUID clientId,@RequestBody DataSourceRequest request) {
        return service.getDataSource(request,clientId);
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        return service.getDataSource(request, null);
    }
}