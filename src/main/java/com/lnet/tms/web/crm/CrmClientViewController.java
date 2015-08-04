package com.lnet.tms.web.crm;

import com.lnet.tms.model.crm.CrmClientView;
import com.lnet.tms.service.crm.CrmClientViewService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Controller
@RequestMapping("/CrmClientView")
public class CrmClientViewController extends BaseController<CrmClientView, UUID, CrmClientViewService> {
    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(CrmClientViewService service) {
        super.setService(service);
    }
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "crm/CrmClient/index";
    }

}
