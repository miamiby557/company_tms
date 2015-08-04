package com.lnet.tms.service.sys;

import com.lnet.tms.dao.sys.SysFunctionDao;
import com.lnet.tms.model.sys.SysFunction;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SysFunctionService extends CrudService<SysFunction, UUID, SysFunctionDao> {

    @Autowired
    public void setBaseDao(SysFunctionDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
