package com.lnet.tms.service.base;

import com.lnet.tms.dao.base.BaseExacctDao;
import com.lnet.tms.model.base.BaseExacct;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BaseExacctService extends CrudService<BaseExacct, UUID, BaseExacctDao> {

    @Autowired
    public void setBaseDao(BaseExacctDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
