package com.lnet.tms.web;

import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.service.CommonDataService;
import com.lnet.tms.service.base.BaseRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/commonData")
public class CommonDataController {

    @Autowired
    CommonDataService commonDataService;

    @Autowired
    BaseRegionService baseRegionService;

    @RequestMapping("/sysList")
    public
    @ResponseBody
    Map getSysList() {
        return commonDataService.getSysList();
    }

    @RequestMapping("/sysOrganization")
    public
    @ResponseBody
    List getOrganization() {
        return commonDataService.getSysOrganizations();
    }

    @RequestMapping(value = "/provinces")
    public
    @ResponseBody
    List<BaseRegion> getProvinces() {
        return baseRegionService.getRegions(null);
    }

    @RequestMapping(value = "/regions")
    public
    @ResponseBody
    List<BaseRegion> getRegions(@RequestParam(value = "parentId", required = false) UUID superiorRegionId) {
        return baseRegionService.getRegions(superiorRegionId);
    }
}
