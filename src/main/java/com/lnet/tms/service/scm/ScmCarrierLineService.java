package com.lnet.tms.service.scm;

import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.dao.scm.ScmCarrierLineDao;
import com.lnet.tms.model.scm.ScmCarrierLine;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ScmCarrierLineService extends CrudService<ScmCarrierLine, UUID, ScmCarrierLineDao> {

    @Autowired
    public void setBaseDao(ScmCarrierLineDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private BaseRegionDao baseRegionDao;
    // your service methods

    @Override
    public UUID create(ScmCarrierLine model) {
        model.setStartCity(baseRegionDao.get(model.getStartCityId()).getName());
        model.setDestCity(baseRegionDao.get(model.getDestCityId()).getName());
        return super.create(model);
    }

}
