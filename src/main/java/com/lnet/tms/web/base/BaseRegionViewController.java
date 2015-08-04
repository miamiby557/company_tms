package com.lnet.tms.web.base;

import com.lnet.tms.model.base.BaseRegionView;
import com.lnet.tms.service.base.BaseRegionViewService;
import com.lnet.tms.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/baseRegionView")
public class BaseRegionViewController extends BaseController<BaseRegionView, UUID, BaseRegionViewService> {

    @Autowired
    public void setBaseService(BaseRegionViewService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ORDERBASEREGION_SELECT")
    public String index() {
      return "base/region/index";
    }

}