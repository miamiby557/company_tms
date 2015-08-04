package com.lnet.tms.service.scm;

import com.lnet.tms.dao.scm.ScmCarrierQuoteViewDao;
import com.lnet.tms.dao.scm.ScmCarrierViewDao;
import com.lnet.tms.model.scm.ScmCarrierQuoteView;
import com.lnet.tms.model.scm.ScmCarrierView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScmCarrierQuoteViewService extends BaseService<ScmCarrierQuoteView,UUID,ScmCarrierQuoteViewDao> {
    @Autowired
    public void setBaseDao(ScmCarrierQuoteViewDao dao) {
        super.setDao(dao);
    }

}
