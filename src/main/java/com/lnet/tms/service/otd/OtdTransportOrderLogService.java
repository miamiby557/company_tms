package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdTransportOrderDao;
import com.lnet.tms.dao.otd.OtdTransportOrderLogDao;
import com.lnet.tms.model.otd.OtdTransportOrderLog;
import com.lnet.tms.model.otd.OtdTransportOrderLogBean;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class OtdTransportOrderLogService extends CrudService<OtdTransportOrderLog, UUID, OtdTransportOrderLogDao> {

    @Autowired
    public void setBaseDao(OtdTransportOrderLogDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private OtdTransportOrderDao otdTransportOrderDao;
    @Transactional
    public void createBatch(OtdTransportOrderLogBean model) {
        Set<UUID> list = model.getTransportOrderIds();
        if(list!=null&&list.size()>0){
            for(UUID id :list){
                model.setTransportOrderId(id);
                model.setCurrentStatus(otdTransportOrderDao.get(model.getTransportOrderId()).getStatus());
                OtdTransportOrderLog log = new OtdTransportOrderLog();
                BeanUtils.copyProperties(model,log);
                getDao().create(log);
            }
        }
    }

    // your service methods
}
