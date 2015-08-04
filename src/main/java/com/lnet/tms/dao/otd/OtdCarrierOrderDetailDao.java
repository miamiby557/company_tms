
package com.lnet.tms.dao.otd;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.otd.OtdCarrierOrder;
import com.lnet.tms.model.otd.OtdCarrierOrderDetail;
import com.lnet.tms.model.otd.OtdTransportOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class OtdCarrierOrderDetailDao extends CrudDao<OtdCarrierOrderDetail, UUID> {


    @Autowired
    private OtdTransportOrderDao otdTransportOrderDao;

    public void deleteByCarrierID(UUID carrierId){//删除明细   并且修改状态为2
        List<OtdCarrierOrderDetail> listDetail = super.getAllByField("carrierOrderId",carrierId);
        if(listDetail!=null&&listDetail.size()>0){
            for(OtdCarrierOrderDetail detail : listDetail){
                OtdTransportOrder t = otdTransportOrderDao.get(detail.getTransportOrderId());
                if(t.getMergeStatus()==3){
                    t.setMergeSendStatus(0);
                    List<OtdTransportOrder>orders=otdTransportOrderDao.getAllByField("mergeTransportOrderId", t.getTransportOrderId());
                    for(OtdTransportOrder otdTransportOrder:orders){
                        otdTransportOrder.setStatus(2);
                        otdTransportOrderDao.update(otdTransportOrder);
                    }
                    otdTransportOrderDao.update(t);
                }else {
                    t.setStatus(2);
                    otdTransportOrderDao.update(t);
                    if(t.getMergeStatus()==2){
                        OtdTransportOrder otdTransportOrder=otdTransportOrderDao.get(t.getMergeTransportOrderId());
                        List<OtdTransportOrder>orders=otdTransportOrderDao.getAllByField("mergeTransportOrderId", otdTransportOrder.getTransportOrderId());
                        int mergeSendStatus=0;
                        for(OtdTransportOrder order:orders){
                            if(order.getStatus()>2){
                                mergeSendStatus=1;
                            }
                        }
                        otdTransportOrder.setMergeSendStatus(mergeSendStatus);
                        otdTransportOrderDao.update(otdTransportOrder);
                    }
                }
            }
        }
        //super.delete(listDetail);
    }
}