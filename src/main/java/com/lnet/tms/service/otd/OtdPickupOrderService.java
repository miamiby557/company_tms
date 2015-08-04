package com.lnet.tms.service.otd;

import com.lnet.tms.dao.SequenceDao;
import com.lnet.tms.dao.otd.OtdPickupOrderDao;
import com.lnet.tms.model.otd.OtdPickupOrder;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OtdPickupOrderService extends CrudService<OtdPickupOrder, UUID, OtdPickupOrderDao> {

    @Autowired
    public void setBaseDao(OtdPickupOrderDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public UUID create(OtdPickupOrder model) {
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        String number = sequenceDao.formatSequenceNumber("SEQ_PICKUPORDER_NUMBER", "00000000", "PI", "");
        model.setPickupOrderNumber(number);
        return super.create(model);
    }


}
