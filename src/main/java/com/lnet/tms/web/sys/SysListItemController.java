package com.lnet.tms.web.sys;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.service.sys.SysListService;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/sysListItem")
public class SysListItemController extends CrudController<SysListItem, UUID, SysListItemService> {

    @Autowired
    public void setBaseService(SysListItemService service) {
        super.setService(service);
    }

    @Autowired
    private SysListService sysListService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam("listId") String listId, ModelMap model) {
        model.addAttribute("listId", listId);
        return "sys/sysListItem/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getDataSource(@RequestParam("listId") String listId, @RequestBody DataSourceRequest request) {
        request.getFilter().setLogic("and");
        DataSourceRequest.FilterDescriptor filter = new DataSourceRequest.FilterDescriptor();
        filter.setField("listId");
        filter.setOperator("eq");
        filter.setValue(UUID.fromString(listId));
        request.getFilter().getFilters().add(filter);
        DataSourceResult result = service.getDataSource(request);
        return result;
    }

    @RequestMapping("/create")
    public String create() {
        return "sysListItem/create";
    }

    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable UUID id, ModelMap model) {
        model.addAttribute(service.get(id));
        return "sysListItem/modify";
    }

    @RequestMapping("/details/{id}")
    public String details(@PathVariable UUID id, ModelMap model) {
        model.addAttribute(service.get(id));
        return "sysListItem/details";
    }

    @RequestMapping(value = "/getSysListItems", method = RequestMethod.GET)
    public
    @ResponseBody
    List<SysListItem> getSysListItems(@RequestParam("code") String code) {
        UUID listId = sysListService.getByField("code", code).getListId();
        return service.getAllByField("listId", listId);
    }

}