package com.lnet.tms.web.fee;

import com.lnet.tms.model.fee.FeeOrderReceivableLogView;
import com.lnet.tms.service.fee.FeeOrderReceivableLogViewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.BaseController;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/feeOrderReceivableLogView")
public class FeeOrderReceivableLogViewController extends BaseController<FeeOrderReceivableLogView, UUID, FeeOrderReceivableLogViewService> {

}