package com.lnet.tms.service.dispatch;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.SequenceDao;
import com.lnet.tms.dao.dispatch.DispatchAssignDao;
import com.lnet.tms.dao.dispatch.DispatchAssignDetailDao;
import com.lnet.tms.dao.dispatch.DispatchAssignStatusRecordDao;
import com.lnet.tms.dao.otd.OtdPickupOrderDao;
import com.lnet.tms.dao.otd.OtdPickupOrderViewDao;
import com.lnet.tms.dao.otd.OtdTransportOrderDao;
import com.lnet.tms.dao.otd.OtdTransportOrderLogDao;
import com.lnet.tms.model.dispatch.DispatchAssign;
import com.lnet.tms.model.dispatch.DispatchAssignDetail;
import com.lnet.tms.model.otd.OtdPickupOrder;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class DispatchAssignService extends CrudService<DispatchAssign, UUID, DispatchAssignDao> {

    @Autowired
    public void setBaseDao(DispatchAssignDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private OtdTransportOrderDao otdTransportOrderDao;

    @Autowired
    private DispatchAssignDetailDao dispatchAssignDetailDao;

    @Autowired
    private OtdPickupOrderDao otdPickupOrderDao;

    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    private OtdPickupOrderViewDao otdPickupOrderViewDao;

    @Autowired
    private DispatchAssignStatusRecordDao dispatchAssignStatusRecordDao;


    @Override
    public UUID create(DispatchAssign model) {
        String number = sequenceDao.formatSequenceNumber("SEQ_ASSIGN_NUMBER", "00000000", "DI", "");
        model.setDispatchAssignNumber(number);
        Set<DispatchAssignDetail> details = model.getDetails();
        if(model.getDispatchType()==1&&details!=null&&details.size()>0){
            for(DispatchAssignDetail detail:details){
                OtdTransportOrder order = otdTransportOrderDao.get(detail.getOrderId());
                order.setStatus(3);//調度中
                otdTransportOrderDao.update(order);
            }
        }else if(model.getDispatchType()==2&&details!=null&&details.size()>0){
            for(DispatchAssignDetail detail:details){
                OtdPickupOrder order = otdPickupOrderDao.get(detail.getOrderId());
                order.setProcessStatus(3);//調度中
                otdPickupOrderDao.update(order);
            }
        }
        model.setStatus(1);
        getDao().create(model);
        if(model.getDispatchType()==1){
            dispatchAssignStatusRecordDao.auotCreate(model.getDispatchAssignId(),model.getStatus(),"新建市内配送派车单");
        }
        if(model.getDispatchType()==2){
            dispatchAssignStatusRecordDao.auotCreate(model.getDispatchAssignId(),model.getStatus(),"新建提货发运派车单");
        }
        return model.getDispatchAssignId();
    }

    @Override
    public void update(DispatchAssign model) {
        List<DispatchAssignDetail> oldDetails = dispatchAssignDetailDao.getAllByField("dispatchAssignId", model.getDispatchAssignId());
        if(model.getDispatchType()==1){
            if(oldDetails!=null&&oldDetails.size()>0) {
                for (DispatchAssignDetail detail : oldDetails) {
                    OtdTransportOrder order = otdTransportOrderDao.get(detail.getOrderId());
                    order.setStatus(2);//审单
                    otdTransportOrderDao.update(order);
                }
            }
            Set<DispatchAssignDetail> details = model.getDetails();
            if(details!=null&&details.size()>0){
                for(DispatchAssignDetail detail:details){
                    OtdTransportOrder order = otdTransportOrderDao.get(detail.getOrderId());
                    order.setStatus(3);//調度中
                    otdTransportOrderDao.update(order);
                }
            }
        }else if(model.getDispatchType()==2){
            if(oldDetails!=null&&oldDetails.size()>0) {
                for (DispatchAssignDetail detail : oldDetails) {
                    OtdPickupOrder order = otdPickupOrderDao.get(detail.getOrderId());
                    order.setProcessStatus(2);//审单
                    otdPickupOrderDao.update(order);
                }
            }
            Set<DispatchAssignDetail> details = model.getDetails();
            if(details!=null&&details.size()>0) {
                for(DispatchAssignDetail detail:details){
                    OtdPickupOrder order = otdPickupOrderDao.get(detail.getOrderId());
                    order.setProcessStatus(3);//調度中
                    otdPickupOrderDao.update(order);
                }
            }
        }
        model.setStatus(1);
        if(model.getDispatchType()==1){
            dispatchAssignStatusRecordDao.auotCreate(model.getDispatchAssignId(),model.getStatus(),"修改市内配送派车单");
        }
        if(model.getDispatchType()==2){
            dispatchAssignStatusRecordDao.auotCreate(model.getDispatchAssignId(),model.getStatus(),"修改提货发运派车单");
        }
        getDao().update(model);
    }

    @Transactional
    public DataSourceResult getConfirmPickUpOrder(DataSourceRequest request){
        List<DataSourceRequest.SortDescriptor> sorts=request.getSort();
        if(sorts ==null || sorts.size()==0){
            sorts=new ArrayList<>();
        }
        sorts.add(new DataSourceRequest.SortDescriptor("reservationTime","desc"));
        request.setSort(sorts);
        return otdPickupOrderViewDao.getDataSource(request);
    }

    @Transactional
    public void inCitySend(DispatchAssign model) {
        List<DispatchAssignDetail> oldDetails = dispatchAssignDetailDao.getAllByField("dispatchAssignId", model.getDispatchAssignId());
        if(model.getDispatchType()==1&&oldDetails!=null&&oldDetails.size()>0){
            for(DispatchAssignDetail detail:oldDetails){
                OtdTransportOrder order = otdTransportOrderDao.get(detail.getOrderId());
                order.setStatus(2);//审单
                otdTransportOrderDao.update(order);
            }
        }
        Set<DispatchAssignDetail> details = model.getDetails();
        if(model.getDispatchType()==1&&details!=null&&details.size()>0){
            for(DispatchAssignDetail detail:details){
                OtdTransportOrder order = otdTransportOrderDao.get(detail.getOrderId());
                order.setStatus(4);//在途
                otdTransportOrderDao.update(order);
            }
        }
        model.setStatus(2);//已发车
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        getDao().update(model);
        if(model.getDispatchType()==1){
            dispatchAssignStatusRecordDao.auotCreate(model.getDispatchAssignId(),model.getStatus(),"市内配送派车单确认发车");
        }
    }

    @Transactional
    public void pickupSend(DispatchAssign model) {
        List<DispatchAssignDetail> oldDetails = dispatchAssignDetailDao.getAllByField("dispatchAssignId", model.getDispatchAssignId());
        if(model.getDispatchType()==2&&oldDetails!=null&&oldDetails.size()>0){
            for(DispatchAssignDetail detail:oldDetails){
                OtdPickupOrder order = otdPickupOrderDao.get(detail.getOrderId());
                order.setProcessStatus(2);//审单
                otdPickupOrderDao.update(order);
            }
        }
        Set<DispatchAssignDetail> details = model.getDetails();
        if(model.getDispatchType()==2&&details!=null&&details.size()>0){
            for(DispatchAssignDetail detail:details){
                OtdPickupOrder order = otdPickupOrderDao.get(detail.getOrderId());
                order.setProcessStatus(4);//提货中
                otdPickupOrderDao.update(order);
            }
        }
        model.setStatus(2);//已发车
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        getDao().update(model);
        if(model.getDispatchType()==2){
            dispatchAssignStatusRecordDao.auotCreate(model.getDispatchAssignId(),model.getStatus(),"提货发运派车单确认发车");
        }
    }
    @Transactional
    public void backAssign(List<UUID> ids){
        if(ids!=null&&ids.size()>0){
            for(UUID id :ids){
                DispatchAssign model = getDao().get(id);
                Set<DispatchAssignDetail> details = model.getDetails();
                if(model.getDispatchType()==1){//运输订单
                    if(details!=null&&details.size()>0) {
                        for(DispatchAssignDetail detail:details){
                            OtdTransportOrder order = otdTransportOrderDao.get(detail.getOrderId());
                            order.setStatus(5);//已完成
                            otdTransportOrderDao.update(order);
                        }
                    }
                }else if(model.getDispatchType()==2){//提货单
                    if(details!=null&&details.size()>0) {
                        for(DispatchAssignDetail detail:details){
                            OtdPickupOrder order = otdPickupOrderDao.get(detail.getOrderId());
                            order.setProcessStatus(5);//已完成
                            otdPickupOrderDao.update(order);
                        }
                    }
                }
                model.setStatus(3);//回场
                getDao().update(model);
                dispatchAssignStatusRecordDao.auotCreate(model.getDispatchAssignId(),model.getStatus(),"提货单完成回场");
            }
        }
    }

}