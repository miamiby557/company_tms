package com.lnet.tms.service.sys;

import com.lnet.tms.dao.sys.SysSettingDao;
import com.lnet.tms.model.sys.SysSetting;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysSettingService extends CrudService<SysSetting, String, SysSettingDao> {

    @Autowired
    public void setBaseDao(SysSettingDao dao) {
        super.setDao(dao);
    }

}
