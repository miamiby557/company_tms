package com.lnet.tms.service.dispatch;

import com.lnet.tms.dao.dispatch.DispatchVehicleDao;
import com.lnet.tms.model.dispatch.DispatchVehicle;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DispatchVehicleService extends CrudService<DispatchVehicle, UUID, DispatchVehicleDao> {

    @Autowired
    public void setBaseDao(DispatchVehicleDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
