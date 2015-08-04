package com.lnet.tms.service.sys;

import com.lnet.tms.dao.sys.SysListItemDao;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SysListItemService extends CrudService<SysListItem, UUID, SysListItemDao> {

    @Autowired
    public void setBaseDao(SysListItemDao dao) {
        super.setDao(dao);
    }
    @Autowired
    private SysListService sysListService;


    public void setSysListService(SysListService sysListService) {
        this.sysListService = sysListService;
    }

    @Transactional
    public List<SysListItem> getSysListItems(String code){
        UUID listId=sysListService.getByField("code",code).getListId();
        return getDao().getAllByField("listId",listId);
    }
}
