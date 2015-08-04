package com.lnet.tms.service.crm;

import com.lnet.tms.dao.crm.CrmClientPickupAddressDao;
import com.lnet.tms.model.crm.CrmClientPickupAddress;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CrmClientPickupAddressService extends CrudService<CrmClientPickupAddress, UUID, CrmClientPickupAddressDao> {

    @Autowired
    public void setBaseDao(CrmClientPickupAddressDao dao) {
        super.setDao(dao);
    }


    // your service methods
}
