package com.lnet.tms.service.otd;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.otd.OtdTransportOrderReceiptDao;
import com.lnet.tms.model.otd.OtdTransportOrderReceipt;
import com.lnet.tms.model.otd.OtdTransportOrderReceiptBean;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class OtdTransportOrderReceiptService extends CrudService<OtdTransportOrderReceipt, UUID, OtdTransportOrderReceiptDao> {

    @Autowired
    public void setBaseDao(OtdTransportOrderReceiptDao dao) {
        super.setDao(dao);
    }

    @Transactional
    public void updateBatch( OtdTransportOrderReceiptBean model) {
        Set<UUID> list = model.getTransportOrderIds();
        if(list!=null&&list.size()>0){
            for(UUID transportOrderId :list){
                OtdTransportOrderReceipt orderReceipt = new OtdTransportOrderReceipt();
                OtdTransportOrderReceipt receipt=getDao().getByField("transportOrderId", transportOrderId);
                if(receipt.getStatus()!=null&&receipt.getStatus()==2){//已回待补
                    receipt.setIsRepair(1);//已补签
                }
                receipt.setStatus(3);
                receipt.setReceiptDate(model.getReceiptDate());
                receipt.setRemark(model.getRemark());
                super.update(receipt);
            }
        }
    }
}
