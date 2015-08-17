package com.lnet.tms.service.crm;

import com.lnet.tms.dao.crm.CrmClientSenderViewDao;
import com.lnet.tms.dao.crm.CrmClientViewDao;
import com.lnet.tms.model.crm.CrmClientSenderView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by develop on 2015/8/11.
 */
public class CrmClientSenderViewService extends BaseService<CrmClientSenderView, UUID,CrmClientSenderViewDao> {
    @Autowired
    public void setBaseDao(CrmClientSenderViewDao dao) {
        super.setDao(dao);
    }
}
