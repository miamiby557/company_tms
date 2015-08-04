package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdOrderCirculationViewDao;
import com.lnet.tms.model.otd.OtdOrderCirculationView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OtdOrderCirculationViewService extends BaseService<OtdOrderCirculationView, UUID, OtdOrderCirculationViewDao> {

    @Autowired
    public void setBaseDao(OtdOrderCirculationViewDao dao) {
        super.setDao(dao);
    }
}
