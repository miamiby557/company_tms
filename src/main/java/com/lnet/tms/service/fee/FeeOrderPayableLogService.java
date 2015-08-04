package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderPayableDao;
import com.lnet.tms.dao.fee.FeeOrderPayableLogDao;
import com.lnet.tms.dao.fee.FeeOrderPayableViewDao;
import com.lnet.tms.dao.otd.OtdCarrierOrderDao;
import com.lnet.tms.dao.otd.OtdCarrierOrderLogDao;
import com.lnet.tms.model.fee.FeeOrderPayable;
import com.lnet.tms.model.fee.FeeOrderPayableLog;
import com.lnet.tms.model.otd.OtdCarrierOrder;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class FeeOrderPayableLogService extends CrudService<FeeOrderPayableLog,UUID,FeeOrderPayableLogDao>{

    @Autowired
    public void setBaseDao(FeeOrderPayableLogDao dao){
       super.setDao(dao);
    }

    @Autowired
    private FeeOrderPayableLogDao feeOrderPayableLogDao;

    @Autowired
    private OtdCarrierOrderDao otdCarrierOrderDao;

    /*@Autowired
    private OtdCarrierOrderLogDao otdCarrierOrderLogDao;*/

    @Autowired
    private FeeOrderPayableDao feeOrderPayableDao;

    @Transactional
    public void back(FeeOrderPayableLog model){
        FeeOrderPayable feeOrderPayable=feeOrderPayableDao.get(model.getOrderPayableId());
        Integer status=feeOrderPayable.getStatus();
        feeOrderPayable.setStatus( 1);
        feeOrderPayable.setBackStatus(status);
        feeOrderPayableDao.saveOrUpdate(feeOrderPayable);


        //托运单操作日志
        if(feeOrderPayable.getSourceOrderType()==2){
            /*OtdCarrierOrder order=otdCarrierOrderDao.get(feeOrderPayable.getSourceOrderId());*/
            String str="";
            if(status==2){
                str="应付客服经理审核拒绝，拒绝原因：";
            }else if(status==3){
                str="应付财务审核拒绝，拒绝原因：";
            }else if(status==4){
                str="应付财务审核撤销，撤销原因：";
            }
            str+=model.getRemark();
            feeOrderPayableLogDao.autoCreate(model.getOrderPayableId(), 1,str);
            /*otdCarrierOrderLogDao.autoCreate(feeOrderPayable.getSourceOrderId(),order.getStatus(),str);*/
        }
    }


}
