package com.lnet.tms.web.scm;

import com.lnet.tms.model.scm.ScmCarrierView;
import com.lnet.tms.service.scm.ScmCarrierViewService;
import com.lnet.tms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/scmCarrierView")
public class ScmCarrierViewController extends BaseController<ScmCarrierView,UUID,ScmCarrierViewService> {

    @Autowired
    public void setBaseService(ScmCarrierViewService service) {
        super.setService(service);
    }



    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "scm/carrier/index";
    }
}
