package com.lnet.tms.service.crm;

import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.dao.crm.CrmClientLineDao;
import com.lnet.tms.model.crm.CrmClientLine;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CrmClientLineService extends CrudService<CrmClientLine, UUID, CrmClientLineDao> {

    @Autowired
    public void setBaseDao(CrmClientLineDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private BaseRegionDao baseRegionDao;

    @Override
    public UUID create(CrmClientLine model) {
        model.setStartCity(baseRegionDao.get(model.getStartCityId()).getName());
        model.setDestCity(baseRegionDao.get(model.getDestCityId()).getName());
        return super.create(model);
    }
}
