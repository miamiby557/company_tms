package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderReceivableDao;
import com.lnet.tms.dao.fee.FeeOrderReceivableLogDao;
import com.lnet.tms.dao.otd.OtdTransportOrderDao;
import com.lnet.tms.dao.otd.OtdTransportOrderLogDao;
import com.lnet.tms.model.fee.FeeOrderReceivable;
import com.lnet.tms.model.fee.FeeOrderReceivableLog;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Created by admin on 2015/5/19.
 */
@Service
public class FeeOrderReceivableLogService extends CrudService<FeeOrderReceivableLog,UUID,FeeOrderReceivableLogDao>{

    @Autowired
    public void setBaseDao(FeeOrderReceivableLogDao dao){
       super.setDao(dao);
    }

    @Autowired
    private FeeOrderReceivableDao feeOrderReceivableDao;

    /*@Autowired
    private OtdTransportOrderLogDao otdTransportOrderLogDao;*/

    @Autowired
    private OtdTransportOrderDao otdTransportOrderDao;

    @Transactional
    public void back(FeeOrderReceivableLog feeOrderReceivableLog){
        FeeOrderReceivable feeOrderReceivable = feeOrderReceivableDao.get(feeOrderReceivableLog.getOrderReceivableId());
        Integer status=feeOrderReceivable.getStatus();
        feeOrderReceivable.setStatus(1);
        feeOrderReceivable.setBackStatus(status);
        feeOrderReceivableDao.update(feeOrderReceivable);
        //订单操作日志
        if(feeOrderReceivable.getSourceOrderType()==3){
            String str="";
            if(status==2){
                str="应收客服经理审核拒绝,拒绝原因：";
            }else if(status==3){
                str="应收财务审核拒绝,拒绝原因：";
            }else if(status==4){
                str="应收财务审核撤销,撤销原因：";
            }
            str+=feeOrderReceivableLog.getRemark();
            getDao().autoCreate(feeOrderReceivableLog.getOrderReceivableId(), 1,str);
            /*otdTransportOrderLogDao.autoCreate(feeOrderReceivable.getSourceOrderId(),order.getStatus(),str);*/
        }
    }

}
