package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdOrderSignDao;
import com.lnet.tms.dao.otd.OtdTransportOrderDao;
import com.lnet.tms.dao.otd.OtdTransportOrderLogDao;
import com.lnet.tms.model.otd.OtdOrderSign;
import com.lnet.tms.model.otd.OtdOrderSignBean;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class OtdOrderSignService extends CrudService<OtdOrderSign, UUID, OtdOrderSignDao> {

    @Autowired
    public void setBaseDao(OtdOrderSignDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private OtdTransportOrderDao otdTransportOrderDao;

    @Autowired
    private OtdTransportOrderLogDao otdTransportOrderLogDao;

    @Override
    @Transactional
    public UUID create(OtdOrderSign model) {
        OtdTransportOrder order=otdTransportOrderDao.get(model.getTransportOrderId());
        order.setStatus(6);
        otdTransportOrderDao.update(order);
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());

        //记录日志   是否异常
        otdTransportOrderLogDao.autoCreate(model.getTransportOrderId(),order.getStatus(),"签收订单",model.getIsAbnormal());
        getDao().saveOrUpdate(model);
        return  model.getTransportOrderId();
    }
    @Transactional
    public void reset(OtdOrderSign model) {
        OtdTransportOrder order=otdTransportOrderDao.get(model.getTransportOrderId());
        order.setStatus(5);
        otdTransportOrderDao.update(order);
        otdTransportOrderLogDao.autoCreate(model.getTransportOrderId(),order.getStatus(),"订单撤销签收");
        getDao().delete(model);
    }
    @Transactional
    public void batchReset(List<UUID> ids) {
        for(UUID id:ids){
            OtdTransportOrder order=otdTransportOrderDao.get(id);
            order.setStatus(5);
            otdTransportOrderDao.update(order);
            otdTransportOrderLogDao.autoCreate(id,order.getStatus(),"订单撤销签收");
            getDao().deleteById(id);
        }
    }
    @Transactional
    public void batchSign(OtdOrderSignBean model) throws InvocationTargetException, IllegalAccessException {
        Set<UUID> transportOrderIds =  model.getTransportOrderIds();
        if(transportOrderIds!=null&&transportOrderIds.size()>0){
            for(UUID id :transportOrderIds){
                OtdTransportOrder order=otdTransportOrderDao.get(id);
                if(order.getMergeStatus()!=null&&order.getMergeStatus()==3){
                    List<OtdTransportOrder> mergeList= otdTransportOrderDao.getAllByField("mergeTransportOrderId", order.getTransportOrderId());
                    if(mergeList!=null&&mergeList.size()>0) {
                        for (OtdTransportOrder transportOrder :mergeList) {
                            //修改订单状态
                            transportOrder.setStatus(6);//签收
                            otdTransportOrderDao.update(transportOrder);model.setTransportOrderId(id);
                            model.setCreateDate(DateUtils.getTimestampNow());
                            model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());

                            //记录日志
                            otdTransportOrderLogDao.autoCreate(model.getTransportOrderId(),order.getStatus(),"签收订单",model.getIsAbnormal());
                            OtdOrderSign sign = new OtdOrderSign();
                            BeanUtils.copyProperties(model,sign);
                            getDao().create(sign);
                        }
                    }
                }else{
                    order.setStatus(6);
                    otdTransportOrderDao.update(order);
                    model.setTransportOrderId(id);
                    model.setCreateDate(DateUtils.getTimestampNow());
                    model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());

                    //记录日志
                    otdTransportOrderLogDao.autoCreate(model.getTransportOrderId(),order.getStatus(),"签收订单",model.getIsAbnormal());
                    OtdOrderSign sign = new OtdOrderSign();
                    BeanUtils.copyProperties(model,sign);
                    getDao().create(sign);
                }
            }
        }

    }

}
