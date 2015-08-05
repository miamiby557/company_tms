package com.lnet.tms.web.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.model.base.BaseAddress;
import com.lnet.tms.service.base.BaseAddressService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Created by Administrator on 2015/8/4.
 */
@Controller
@RequestMapping("/baseAddress")
public class BaseAddressController extends CrudController<BaseAddress, UUID, BaseAddressService> {
    @Autowired
    private HibernateAwareObjectMapper mapper;

    @Autowired
    public void setBaseService(BaseAddressService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "base/address/index";
    }

    @RequestMapping(value = "/create")
    public String create() {
        return "base/address/create";
    }

    @RequestMapping("/modify/{addressId}")
    public String modify(@PathVariable UUID addressId, ModelMap model) throws JsonProcessingException {
        BaseAddress baseAddress = service.get(addressId);
        model.addAttribute("baseAddress", baseAddress);
        model.addAttribute("baseAddress", mapper.writeValueAsString(baseAddress));
        return "base/address/modify";
    }
}
