package com.lnet.tms.web.base;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.model.sys.SysOrganization;
import com.lnet.tms.service.base.BaseRegionService;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/baseRegion")
public class BaseRegionController extends CrudController<BaseRegion, UUID, BaseRegionService> {

    @Autowired
    public void setBaseService(BaseRegionService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "base/region/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "region/create";
    }

    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable UUID id, ModelMap model) {
        model.addAttribute(service.get(id));
        return "baseRegion/modify";
    }

    @RequestMapping("/regions/{id}")
    public String getRegions(@PathVariable UUID id, ModelMap model) {
        model.addAttribute(service.get(id));
        return "baseRegion/details";
    }

    @RequestMapping("/details/{id}")
    public String details(@PathVariable UUID id, ModelMap model) {
        model.addAttribute(service.get(id));
        return "baseRegion/details";
    }

    /*@RequestMapping(value = "/getHierarchicalData", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    List<BaseRegion> getHierarchicalData(@RequestParam(value = "regionId", required = false) UUID regionId) {
        return service.getHierarchicalData("superiorRegionId", "subRegions", regionId);
    }*/
    @RequestMapping(value = "/getChildrenData", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    List<BaseRegion> getChildrenData(@RequestBody DataSourceRequest request) {
        UUID regionId  = null;
        if(request.getData().get("regionId")!=null) {
            regionId = UUID.fromString(request.getData().get("regionId").toString());
        }
        return service.getAllByField("superiorRegionId", regionId);
    }
    @RequestMapping(value = "/getRegions", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getRegions(@RequestBody DataSourceRequest request) {
        request.getFilter().setLogic("and");
        request.getSort().add(new DataSourceRequest.SortDescriptor("code", "asc"));
        if (!request.getFilter().exists("regionId"))
        {

        }

        return service.getDataSource(request);

    }

    @RequestMapping(value = "/getCityList", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getCityList(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        List<DataSourceRequest.SortDescriptor> listSort = new ArrayList<>();
        listSort.add(new DataSourceRequest.SortDescriptor("code", "asc"));
        request.setSort(listSort);//按照编码排序
        //设置 regionTypeId 是省  a8d9b8bf-f75e-47b6-9967-3bb57321cce5
        //第一个条件
        DataSourceRequest.FilterDescriptor filterDescriptor = new DataSourceRequest.FilterDescriptor();
        filterDescriptor.setLogic("or");
        DataSourceRequest.FilterDescriptor filterDescriptor1 = new DataSourceRequest.FilterDescriptor("or", "regionTypeId", "eq", 2, true);
        DataSourceRequest.FilterDescriptor filterDescriptor2 = new DataSourceRequest.FilterDescriptor("or", "regionTypeId", "eq", 3, true);
        filterDescriptor.getFilters().add(filterDescriptor1);
        filterDescriptor.getFilters().add(filterDescriptor2);
        list.add(0, filterDescriptor);
        //list.add(1, filterDescriptor2);
        if (list.size() > 1) {
            Object value = request.getFilter().getFilters().get(1).getValue();//获取页面输入关键字
            request.getFilter().getFilters().get(1).setLogic("or");//根据名称，拼音 编码查询
            request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("name", "contains", value));//过滤条件
            request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("code", "contains", value));//添加一个过滤条件
            request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("namePinyin", "contains", value));
        }
        return service.getDataSource(request);
    }

    @RequestMapping(value = "/getProvinceList", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getProvinceList(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        List<DataSourceRequest.SortDescriptor> listSort = new ArrayList<>();
        listSort.add(new DataSourceRequest.SortDescriptor("code", "asc"));
        request.setSort(listSort);
        DataSourceRequest.FilterDescriptor filterDescriptor = new DataSourceRequest.FilterDescriptor("and", "regionTypeId", "eq", 1, true);
        list.add(0, filterDescriptor);
        if (list.size() > 1) {
            Object value = request.getFilter().getFilters().get(1).getValue();//获取页面输入关键字
            request.getFilter().getFilters().get(1).setLogic("or");//根据名称，拼音 编码查询
            request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("name", "contains", value));//过滤条件
            request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("code", "contains", value));//添加一个过滤条件
            request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("namePinyin", "contains", value));
        }
        return service.getDataSource(request);
    }

    @RequestMapping(value = "/getRegionListBySupId", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getListBySupId(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> list = request.getFilter().getFilters();
        List<DataSourceRequest.SortDescriptor> listSort = new ArrayList<>();
        listSort.add(new DataSourceRequest.SortDescriptor("code", "asc"));
        request.setSort(listSort);//按照编码排序
        for (DataSourceRequest.FilterDescriptor filter : list) {
            if ("name".equals(filter.getField())) {
                Object value = request.getFilter().getFilters().get(1).getValue();
                request.getFilter().getFilters().get(1).setLogic("or");
                request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("name", "contains", value));//过滤条件
                request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("code", "contains", value));//添加一个过滤条件
                request.getFilter().getFilters().get(1).getFilters().add(new DataSourceRequest.FilterDescriptor("namePinyin", "contains", value));
            } else if ("regionId".equals(filter.getField())) {
                filter.setField("superiorRegionId");
                filter.setValue(UUID.fromString(filter.getValue().toString()));
                filter.setOperator("eq");
            }
        }
        return service.getDataSource(request);
    }

}