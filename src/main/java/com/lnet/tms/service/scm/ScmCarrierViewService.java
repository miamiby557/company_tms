package com.lnet.tms.service.scm;

import com.lnet.tms.dao.scm.ScmCarrierDao;
import com.lnet.tms.dao.scm.ScmCarrierViewDao;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.model.scm.ScmCarrierView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScmCarrierViewService extends BaseService<ScmCarrierView,UUID,ScmCarrierViewDao> {
    @Autowired
    public void setBaseDao(ScmCarrierViewDao dao) {
        super.setDao(dao);
    }

}
