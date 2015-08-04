package com.lnet.tms.service.sys;

import com.lnet.tms.dao.sys.SysListDao;
import com.lnet.tms.model.sys.SysList;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.service.CrudService;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SysListService extends CrudService<SysList, UUID, SysListDao> {

    @Autowired
    public void setBaseDao(SysListDao dao) {
        super.setDao(dao);
    }
    
}
