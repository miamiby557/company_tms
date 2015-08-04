package com.lnet.tms.service.base;

import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BaseRegionService extends CrudService<BaseRegion, UUID, BaseRegionDao> {

    @Autowired
    public void setBaseDao(BaseRegionDao dao) {
        super.setDao(dao);
    }

    @Transactional
    public List<BaseRegion> getRegions(UUID superiorRegionId){
        return getDao().getRegions(superiorRegionId);
    }

}
