package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdCarrierOrderDao;
import com.lnet.tms.dao.otd.OtdCarrierOrderLogDao;
import com.lnet.tms.model.otd.OtdCarrierOrderLog;
import com.lnet.tms.model.otd.OtdCarrierOrderLogBean;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class OtdCarrierOrderLogService extends CrudService<OtdCarrierOrderLog, UUID, OtdCarrierOrderLogDao> {

    @Autowired
    public void setBaseDao(OtdCarrierOrderLogDao dao) {
        super.setDao(dao);
    }
    @Autowired
    private OtdCarrierOrderDao otdCarrierOrderDao;
    // your service methods
    @Transactional
    public void createBatch(OtdCarrierOrderLogBean model) {
        Set<UUID> set = model.getCarrierOrderIds();
        if(set!=null&&set.size()>0){
            for(UUID id :set){
                model.setCarrierOrderId(id);
                model.setCurrentStatus(otdCarrierOrderDao.get(model.getCarrierOrderId()).getStatus());
                OtdCarrierOrderLog log = new OtdCarrierOrderLog();
                BeanUtils.copyProperties(model, log);
                getDao().create(log);
            }
        }
    }

    @Transactional
    public OtdCarrierOrderLog findLastLog(UUID carrierOrderId){
        return getDao().findLastLog(carrierOrderId);
    }
}
