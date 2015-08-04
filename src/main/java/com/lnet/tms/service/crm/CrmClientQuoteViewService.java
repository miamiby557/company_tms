package com.lnet.tms.service.crm;

import com.lnet.tms.dao.crm.CrmClientQuoteDao;
import com.lnet.tms.dao.crm.CrmClientQuoteViewDao;
import com.lnet.tms.model.crm.CrmClientQuote;
import com.lnet.tms.model.crm.CrmClientQuoteView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Service
public class CrmClientQuoteViewService extends BaseService<CrmClientQuoteView, UUID,CrmClientQuoteViewDao> {
    @Autowired
    public void setBaseDao(CrmClientQuoteViewDao dao) {
        super.setDao(dao);
    }
}
