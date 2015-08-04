package com.lnet.tms.service.crm;

import com.lnet.tms.dao.crm.CrmClientViewDao;
import com.lnet.tms.model.crm.CrmClientView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Service
public class CrmClientViewService extends BaseService<CrmClientView, UUID,CrmClientViewDao> {
    @Autowired
    public void setBaseDao(CrmClientViewDao dao) {
        super.setDao(dao);
    }
}
