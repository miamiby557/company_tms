package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdOrderReceiptPicDao;
import com.lnet.tms.model.otd.OtdOrderReceiptPic;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OtdOrderReceiptPicService extends CrudService<OtdOrderReceiptPic, UUID, OtdOrderReceiptPicDao> {

    @Autowired
    public void setBaseDao(OtdOrderReceiptPicDao dao) {
        super.setDao(dao);
    }

    // your service methods
}
