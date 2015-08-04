package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.FeeOrderReceivableDao;
import com.lnet.tms.dao.fee.FeeOrderReceivableDetailHDao;
import com.lnet.tms.dao.fee.FeeOrderReceivableLogDao;
import com.lnet.tms.dao.fee.FeeOrderReceivableViewDao;
import com.lnet.tms.dao.otd.OtdTransportOrderDao;
import com.lnet.tms.dao.otd.OtdTransportOrderLogDao;
import com.lnet.tms.model.fee.FeeOrderReceivable;
import com.lnet.tms.model.fee.FeeOrderReceivableDetail;
import com.lnet.tms.model.fee.FeeOrderReceivableDetailH;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class FeeOrderReceivableService extends CrudService<FeeOrderReceivable, UUID, FeeOrderReceivableDao> {

    @Autowired
    public void setBaseDao(FeeOrderReceivableDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private FeeOrderReceivableLogDao feeOrderReceivableLogDao;
/*
    @Autowired
    private OtdTransportOrderLogDao otdTransportOrderLogDao;*/

    @Autowired
    private OtdTransportOrderDao otdTransportOrderDao;

    @Autowired
    private FeeOrderReceivableDetailHDao feeOrderReceivableDetailHDao;

    @Autowired
    private ReceivableCalculator receivableCalculator;

    @Transactional
    public void confirm(FeeOrderReceivable model){
        //增加明细历史记录
        Integer version=model.getVersion()+1;
        Set<FeeOrderReceivableDetail> details=model.getDetails();
        for(FeeOrderReceivableDetail detail:details){
            FeeOrderReceivableDetailH h=new FeeOrderReceivableDetailH();
            BeanUtils.copyProperties(detail,h);
            h.setVersion(version);
            h.setOperationDate(DateUtils.getTimestampNow());
            h.setOperationUserId(IdentityUtils.getCurrentUser().getUserId());
            feeOrderReceivableDetailHDao.create(h);
        }
        //确认应收时，大于计算值并大于应付时，跳过客服经理审核
        Double oldTotalAmount=0.0;
        HashMap<String,Object>map=new HashMap<>();
        map.put("orderReceivableId",model.getOrderReceivableId());
        map.put("version",0);
        List<FeeOrderReceivableDetailH>detailHs=feeOrderReceivableDetailHDao.getAllByField(map);
        for(FeeOrderReceivableDetailH h:detailHs){
            oldTotalAmount+=h.getAmount();
        }
        //版本号自增
        model.setVersion(version);
        model.setBackStatus(null);
        if (oldTotalAmount.compareTo(0.0)!=0&& model.getTotalAmount().compareTo(oldTotalAmount)==0){
            model.setStatus(3);//客服经理已审核
        }else{
            model.setStatus(2);//已确认
        }
        getDao().update(model);
        feeOrderReceivableLogDao.autoCreate(model.getOrderReceivableId(),model.getStatus(),"确认应收费用");
        //订单操作日志
        /*if(model.getSourceOrderType()==3){
            OtdTransportOrder order=otdTransportOrderDao.get(model.getSourceOrderId());
            otdTransportOrderLogDao.autoCreate(model.getSourceOrderId(),order.getStatus(),"确认应收费用");
        }*/
    }

    @Transactional
    public void updateReceivable(FeeOrderReceivable model){
        model.setBackStatus(null);
        model.setFeeSaveMark(true);
        getDao().update(model);
    }

    @Transactional
    public FeeOrderReceivable againCalculator(OtdTransportOrder model){
        OtdTransportOrder model1=otdTransportOrderDao.getWith(model.getTransportOrderId(),"details");
        model1.setConfirmedItemQuantity(model.getConfirmedItemQuantity());
        model1.setConfirmedPackageQuantity(model.getConfirmedPackageQuantity());
        model1.setConfirmedVolume(model.getConfirmedVolume());
        model1.setConfirmedWeight(model.getConfirmedWeight());
        model1.setTransportType(model.getTransportType());
        model1.setCalculateType(model.getCalculateType());
        otdTransportOrderDao.update(model1);
        FeeOrderReceivable newReceivable = receivableCalculator.calculate(model1, 3);
        FeeOrderReceivable oldReceivable = getDao().getByField("sourceOrderId",model.getTransportOrderId());
        oldReceivable.setCalculateType(model.getCalculateType());
        oldReceivable=getDao().getWith(oldReceivable.getOrderReceivableId(),"details");
        oldReceivable.setTotalAmount(newReceivable.getTotalAmount());
        for(FeeOrderReceivableDetail detail:oldReceivable.getDetails()){
            UUID exacctID=detail.getExacctId();
            for(FeeOrderReceivableDetail detail1:newReceivable.getDetails()){
                if(detail1.getExacctId().equals(exacctID)){
                    detail.setAmount(detail1.getAmount());
                    detail.setClientQuoteId(detail1.getClientQuoteId());
                }
            }
        }
        getDao().update(oldReceivable);
        return oldReceivable;
    }

    @Transactional
    public void pass(List<UUID> orderReceivableIds){
        for(UUID orderReceivableId:orderReceivableIds){
            FeeOrderReceivable feeOrderReceivable=getDao().get(orderReceivableId);
            feeOrderReceivable.setStatus(feeOrderReceivable.getStatus() + 1);
            getDao().update(feeOrderReceivable);

            //订单操作日志
            if(feeOrderReceivable.getSourceOrderType()==3){
                OtdTransportOrder order=otdTransportOrderDao.get(feeOrderReceivable.getSourceOrderId());
                String str="";
                if(feeOrderReceivable.getStatus()==3){
                    str="应收客服经理审核通过";
                }else if(feeOrderReceivable.getStatus()==4){
                    str="应收财务审核通过";
                }else if(feeOrderReceivable.getStatus()==5){
                    str="应收财务审核完结";
                }
                feeOrderReceivableLogDao.autoCreate(orderReceivableId,feeOrderReceivable.getStatus(),str);
                /*otdTransportOrderLogDao.autoCreate(feeOrderReceivable.getSourceOrderId(),order.getStatus(),str);*/
            }
        }
    }

    @Transactional
    public void back(List<UUID> orderReceivableIds){
        for(UUID orderReceivableId:orderReceivableIds) {
            FeeOrderReceivable feeOrderReceivable = getDao().get(orderReceivableId);
            Integer status=feeOrderReceivable.getStatus();
            feeOrderReceivable.setStatus(1);
            feeOrderReceivable.setBackStatus(status);
            getDao().update(feeOrderReceivable);
            //订单操作日志
            if(feeOrderReceivable.getSourceOrderType()==3){
                OtdTransportOrder order=otdTransportOrderDao.get(feeOrderReceivable.getSourceOrderId());
                String str="";
                if(status==2){
                    str="应收客服经理审核拒绝";
                }else if(status==3){
                    str="应收财务审核拒绝";
                }else if(status==4){
                    str="应收财务审核撤销";
                }
                feeOrderReceivableLogDao.autoCreate(orderReceivableId, 1,str);
                /*otdTransportOrderLogDao.autoCreate(feeOrderReceivable.getSourceOrderId(),order.getStatus(),str);*/
            }
        }
    }
}
