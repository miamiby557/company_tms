package com.lnet.tms.service.fee;

import com.lnet.tms.dao.fee.*;
import com.lnet.tms.dao.otd.OtdCarrierOrderDao;
import com.lnet.tms.dao.otd.OtdCarrierOrderLogDao;
import com.lnet.tms.model.fee.*;
import com.lnet.tms.model.otd.OtdCarrierOrder;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by admin on 2015/5/19.
 */
@Service
public class FeeOrderPayableService extends CrudService<FeeOrderPayable,UUID,FeeOrderPayableDao>{

    @Autowired
    public void setBaseDao(FeeOrderPayableDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private FeeOrderPayableLogDao feeOrderPayableLogDao;
    @Autowired
    private OtdCarrierOrderDao otdCarrierOrderDao;
    @Autowired
    private FeeOrderPayableDetailHDao feeOrderPayableDetailHDao;
    @Autowired
    private FeeOrderPayableDetailDao feeOrderPayableDetailDao;
    @Autowired
    private FeeOrderPayableProportionDao feeOrderPayableProportionDao;
    @Autowired
    private FeeOrderPayableProportionHDao feeOrderPayableProportionHDao;
    @Transactional
    public void confirm(FeeOrderPayable model) {
        //增加明细及分摊历史记录
        Integer version=model.getVersion()+1;
        Set<FeeOrderPayableDetail> details=model.getDetails();
        for(FeeOrderPayableDetail detail:details){
            FeeOrderPayableDetailH h=new FeeOrderPayableDetailH();
            BeanUtils.copyProperties(detail, h);
            h.setVersion(version);
            h.setOperationDate(DateUtils.getTimestampNow());
            h.setOperationUserId(IdentityUtils.getCurrentUser().getUserId());
            feeOrderPayableDetailHDao.create(h);
        }
        Set<FeeOrderPayableProportion>proportions=model.getProportionDetails();
        for(FeeOrderPayableProportion proportion:proportions){
            FeeOrderPayableProportionH p=new FeeOrderPayableProportionH();
            BeanUtils.copyProperties(proportion,p);
            p.setVersion(version);
            feeOrderPayableProportionHDao.create(p);
        }

        //确认应付时，修改值小于计算值并小于应收时，跳过客服经理审核
        Double oldTotalAmount=0.0;
        HashMap<String,Object> map=new HashMap<>();
        map.put("orderPayableId",model.getOrderPayableId());
        map.put("version",0);
        List<FeeOrderPayableDetailH>detailHs=feeOrderPayableDetailHDao.getAllByField(map);
        for(FeeOrderPayableDetailH h:detailHs){
            oldTotalAmount+=h.getAmount();
        }
        //版本号自增
        model.setVersion(version);
        model.setBackStatus(null);
        if(oldTotalAmount.compareTo(0.0)!=0&& model.getTotalAmount().compareTo(oldTotalAmount)==0){
            model.setStatus(3);//项目已审核
        }else{
            model.setStatus(2);//已确认
        }
        super.update(model);
        feeOrderPayableLogDao.autoCreate(model.getOrderPayableId(),model.getStatus(),"应付费用确认");


        //托运单操作日志
       /* if(model.getSourceOrderType()==2){
            OtdCarrierOrder order=otdCarrierOrderDao.get(model.getSourceOrderId());
            otdCarrierOrderLogDao.autoCreate(model.getSourceOrderId(),order.getStatus(),"确认应付费用");
        }*/
    }

    @Transactional
    public void updatePayable(FeeOrderPayable model) {
        model.setBackStatus(null);
        model.setFeeSaveMark(true);
        getDao().update(model);
    }

    @Transactional
    public void pass(UUID orderPayableId){
        FeeOrderPayable feeOrderPayable=getDao().get(orderPayableId);
        feeOrderPayable.setStatus(feeOrderPayable.getStatus() + 1);
        getDao().saveOrUpdate(feeOrderPayable);



        //托运单操作日志
        if(feeOrderPayable.getSourceOrderType()==2){
            OtdCarrierOrder order=otdCarrierOrderDao.get(feeOrderPayable.getSourceOrderId());
            String str="";
            if(feeOrderPayable.getStatus()==3){
                str="应付客服经理审核通过";
            }else if(feeOrderPayable.getStatus()==4){
                str="应付财务审核通过";
            }
            feeOrderPayableLogDao.autoCreate(orderPayableId,feeOrderPayable.getStatus(),str);
            /*otdCarrierOrderLogDao.autoCreate(feeOrderPayable.getSourceOrderId(),order.getStatus(),str);*/
        }
    }
    @Transactional
    public void pass(List<UUID> ids){
       for(UUID orderPayableId:ids){
           FeeOrderPayable feeOrderPayable=getDao().get(orderPayableId);
           feeOrderPayable.setStatus(feeOrderPayable.getStatus() + 1);
           getDao().saveOrUpdate(feeOrderPayable);

           //托运单操作日志
           if(feeOrderPayable.getSourceOrderType()==2){
               OtdCarrierOrder order=otdCarrierOrderDao.get(feeOrderPayable.getSourceOrderId());
               String str="";
               if(feeOrderPayable.getStatus()==3){
                   str="应付客服经理审核通过";
               }else if(feeOrderPayable.getStatus()==4){
                   str="应付财务审核通过";
               }else if(feeOrderPayable.getStatus()==5){
                   str="应付财务审核完结";
               }
               feeOrderPayableLogDao.autoCreate(orderPayableId,feeOrderPayable.getStatus(),str);
               /*otdCarrierOrderLogDao.autoCreate(feeOrderPayable.getSourceOrderId(),order.getStatus(),str);*/
           }
       }
    }

    @Transactional
    public void back(List<UUID> ids){
        for(UUID orderPayableId:ids){
            FeeOrderPayable feeOrderPayable=getDao().get(orderPayableId);
            Integer status=feeOrderPayable.getStatus();
            feeOrderPayable.setBackStatus(status);
            feeOrderPayable.setStatus(1);
            getDao().saveOrUpdate(feeOrderPayable);



            //托运单操作日志
            if(feeOrderPayable.getSourceOrderType()==2){
                OtdCarrierOrder order=otdCarrierOrderDao.get(feeOrderPayable.getSourceOrderId());
                String str="";
                if(status==2){
                    str="应付客服经理审核拒绝";
                }else if(status==3){
                    str="应付财务审核拒绝";
                }else if(status==4){
                    str="应付财务审核撤销";
                }
                feeOrderPayableLogDao.autoCreate(orderPayableId,feeOrderPayable.getStatus(),str);
                /*otdCarrierOrderLogDao.autoCreate(feeOrderPayable.getSourceOrderId(),order.getStatus(),str);*/
            }
        }
    }
    @Transactional
    public void back(UUID orderPayableId){
        FeeOrderPayable feeOrderPayable=getDao().get(orderPayableId);
        Integer status=feeOrderPayable.getStatus();
        feeOrderPayable.setStatus( 1);
        feeOrderPayable.setBackStatus(status);
        getDao().saveOrUpdate(feeOrderPayable);


        //托运单操作日志
        if(feeOrderPayable.getSourceOrderType()==2){
            OtdCarrierOrder order=otdCarrierOrderDao.get(feeOrderPayable.getSourceOrderId());
            String str="";
            if(status==2){
                str="应付客服经理审核拒绝";
            }else if(status==3){
                str="应付财务审核拒绝";
            }else if(status==4){
                str="应付财务审核撤销";
            }
            feeOrderPayableLogDao.autoCreate(orderPayableId, 1,str);
            /*otdCarrierOrderLogDao.autoCreate(feeOrderPayable.getSourceOrderId(),order.getStatus(),str);*/
        }
    }
    @Transactional
    public void deleteChildrenById(UUID carrierOrderId){
        FeeOrderPayable feeOrderPayable =getDao().getByField("sourceOrderId", carrierOrderId);
        if(feeOrderPayable!=null){
            UUID orderPayableId =feeOrderPayable.getOrderPayableId();
            List filter = new ArrayList();
            filter.add(orderPayableId);
            feeOrderPayableDetailDao.deleteByField("orderPayableId",filter);
            feeOrderPayableDetailHDao.deleteByField("orderPayableId",filter);
            feeOrderPayableLogDao.deleteByField("orderPayableId",filter);
            feeOrderPayableProportionDao.deleteByField("orderPayableId",filter);
            feeOrderPayableProportionHDao.deleteByField("orderPayableId",filter);
            getDao().deleteById(orderPayableId);
        }
    }

}
