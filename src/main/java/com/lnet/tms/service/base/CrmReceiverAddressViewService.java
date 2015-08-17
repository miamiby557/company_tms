package com.lnet.tms.service.base;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.base.BaseAddressDao;
import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.model.base.BaseAddress;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Administrator on 2015/8/4.
 */
@Service
public class CrmReceiverAddressViewService extends CrudService<BaseAddress, UUID, BaseAddressDao> {
    @Autowired
    public void setBaseDao(BaseAddressDao dao) {
        super.setDao(dao);
    }

}
