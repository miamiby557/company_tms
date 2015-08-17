package com.lnet.tms.service.otd;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.dao.fee.FeeOrderPayableDao;
import com.lnet.tms.dao.fee.FeeOrderPayableDetailHDao;
import com.lnet.tms.dao.fee.FeeOrderPayableLogDao;
import com.lnet.tms.dao.fee.FeeOrderPayableProportionHDao;
import com.lnet.tms.dao.otd.*;
import com.lnet.tms.dao.scm.ScmCarrierDao;
import com.lnet.tms.dao.scm.ScmCarrierLineDao;
import com.lnet.tms.model.fee.*;
import com.lnet.tms.model.otd.*;
import com.lnet.tms.model.scm.ScmCarrierLine;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.fee.FeeOrderPayableService;
import com.lnet.tms.service.fee.PayableCalculator;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OtdCarrierOrderService extends CrudService<OtdCarrierOrder, UUID, OtdCarrierOrderDao> {

    @Autowired
    public void setBaseDao(OtdCarrierOrderDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private BaseRegionDao baseRegionDao;

    @Autowired
    private OtdTransportOrderDao otdTransportOrderDao;

    @Autowired
    private OtdCarrierOrderDetailDao otdCarrierOrderDetailDao;

    @Autowired
    private OtdCarrierOrderLogDao otdCarrierOrderLogDao;

    @Autowired
    private FeeOrderPayableLogDao feeOrderPayableLogDao;
    @Autowired
    private FeeOrderPayableDao feeOrderPayableDao;
    @Autowired
    private PayableCalculator payableCalculator;
    @Autowired
    private ScmCarrierLineDao scmCarrierLineDao;
    @Autowired
    private FeeOrderPayableDetailHDao feeOrderPayableDetailHDao;
    @Autowired
    private OtdCarrierOrderDetailService otdCarrierOrderDetailService;
    @Autowired
    private ScmCarrierDao scmCarrierDao;
    @Autowired
    private FeeOrderPayableService feeOrderPayableService;
    @Autowired
    private FeeOrderPayableProportionHDao feeOrderPayableProportionHDao;
    @Autowired
    private OtdTransportOrderViewDao otdTransportOrderViewDao;

    @Transactional
    public JsonResult createCarrierOrder(OtdCarrierOrderBean bean) {
        OtdCarrierOrder model = new OtdCarrierOrder();
        BeanUtils.copyProperties(bean,model);
        model.setStartCity(baseRegionDao.get(model.getStartCityId()).getName());
        model.setDestCity(baseRegionDao.get(model.getDestCityId()).getName());
        if(getDao().existsBy("carrierOrderNumber", model.getCarrierOrderNumber())){
            return JsonResult.error("该托运单号已存在！");
        }
        //判断是否重复选择合单和被合单的运输订单
        if(bean.getDetailViews()!=null){
            if(!this.checkTheSameMerge(bean)){
                return JsonResult.error("重复选择合单与被合单！");
            }
        }

        model.setStatus(1);//如果null设置默认为已开单状态
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        SysUser sysUser = IdentityUtils.getCurrentUser();
        if(sysUser!=null){
            model.setCreateUserId(sysUser.getUserId());
        }
        if(bean.getDetailViews()!=null){
            this.chooseTransportOrderUpdateStatus(bean);
            //model.setDetailViews(null);
        }
        getDao().create(model);
        otdCarrierOrderLogDao.autoCreate(model.getCarrierOrderId(),model.getStatus(),"新增托运单");
        return JsonResult.success(model);
    }

    @Transactional
    public     JsonResult updateCarrierOrder(OtdCarrierOrderBean bean) {
        OtdCarrierOrder model = new OtdCarrierOrder();
        BeanUtils.copyProperties(bean,model);
        model.setStartCity(baseRegionDao.get(model.getStartCityId()).getName());
        model.setDestCity(baseRegionDao.get(model.getDestCityId()).getName());
        if(getDao().existsBy("carrierOrderNumber", model.getCarrierOrderNumber(), model.getCarrierOrderId())){
            return JsonResult.error("该托运单号已存在！");
        }

        //判断是否重复选择合单和被合单的运输订单
        if(bean.getDetailViews()!=null){
            if(!this.checkTheSameMerge(bean)){
                return JsonResult.error("重复选择合单与被合单！");
            }
        }
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        model.setModifyDate(DateUtils.getTimestampNow());
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        //将所有运输订单状态改为已审单，后面代码再将已选择的状态改为调度中
        List<OtdCarrierOrderDetail>detailList=otdCarrierOrderDetailService.getAllByField("carrierOrderId", model.getCarrierOrderId());
        this.statusBackBeforeUpdate(detailList);
        //otdCarrierOrderDetailDao.deleteByCarrierID(model.getCarrierOrderId());
        if(bean.getDetailViews()!=null){
            this.chooseTransportOrderUpdateStatus(bean);
        }
        otdCarrierOrderLogDao.autoCreate(model.getCarrierOrderId(),model.getStatus(),"修改托运单");
        getDao().update(model);
        return JsonResult.success();
    }


    public Boolean checkTheSameMerge(OtdCarrierOrderBean bean){
        for(OtdCarrierOrderDetailView detailView: bean.getDetailViews()) {
            //修改订单状态
            OtdTransportOrder t1 = otdTransportOrderDao.get(detailView.getTransportOrderId());
            if(t1.getMergeStatus()==3){
                for(OtdCarrierOrderDetailView detailView1: bean.getDetailViews()){
                    OtdTransportOrder t2 = otdTransportOrderDao.get(detailView1.getTransportOrderId());
                    if(t2.getMergeStatus()==2 && t2.getMergeTransportOrderId().equals(t1.getTransportOrderId())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void chooseTransportOrderUpdateStatus(OtdCarrierOrderBean bean){
        for(OtdCarrierOrderDetailView detailView: bean.getDetailViews()){
            //修改订单状态
            OtdTransportOrder t= otdTransportOrderDao.get(detailView.getTransportOrderId());
            if(t.getMergeStatus()==3){//合单：将合单发运标记为1，被合单更新状态
                List<OtdTransportOrder>orders=otdTransportOrderDao.getAllByField("mergeTransportOrderId", t.getTransportOrderId());
                for(OtdTransportOrder otdTransportOrder:orders){
                    otdTransportOrder.setStatus(3);
                    otdTransportOrderDao.update(otdTransportOrder);
                }
                t.setMergeSendStatus(1);
                t.setStatus(null);
            }else {
                t.setStatus(3);
                if(t.getMergeStatus()==2){//将合单发运标记更新为1
                    OtdTransportOrder otdTransportOrder=otdTransportOrderDao.get(t.getMergeTransportOrderId());
                    otdTransportOrder.setMergeSendStatus(1);
                    otdTransportOrderDao.update(otdTransportOrder);
                }
            }
            t.setReceiptPageNumber(detailView.getReceiptPageNumber());
            t.setWrapType(detailView.getWrapType());
            otdTransportOrderDao.updateStatus(t);
        }
    }

    private void  statusBackBeforeUpdate(List<OtdCarrierOrderDetail>detailList){
        for(OtdCarrierOrderDetail detail:detailList){
            OtdTransportOrder t= otdTransportOrderDao.get(detail.getTransportOrderId());
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

    private Date getExpectedDate(OtdCarrierOrder order){
        Map<String, Object> lineFilter = new HashMap<>();
        lineFilter.put("carrierId", order.getCarrierId());
        lineFilter.put("startCityId",order.getStartCityId());
        lineFilter.put("destCityId",order.getDestCityId());
        lineFilter.put("transportType",order.getTransportType());
        ScmCarrierLine line= scmCarrierLineDao.getByField(lineFilter);
        Boolean isIncludeThatDay=scmCarrierDao.get(order.getCarrierId()).getIsIncludeOrderDate();
        if(line!=null){
            Double timeLine=line.getTimeline();
            int expectedDay= (int) (timeLine/24);
            double hour=timeLine%24;
            Calendar c = Calendar.getInstance();
            c.setTime(order.getSendDate());
            c.set(Calendar.HOUR_OF_DAY,0);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            if(isIncludeThatDay){
                c.add((Calendar.DAY_OF_MONTH),expectedDay);
            }else{
                c.add((Calendar.DAY_OF_MONTH),expectedDay+1);
            }
            c.add(Calendar.HOUR, (int) Math.ceil(hour));
            return c.getTime();

        }
        return null;
    }

    /**
     *
     * @param order
     * @return
     */
    private FeeOrderPayable calculatorProportion(OtdCarrierOrder order ,FeeOrderPayable orderPayable){
        Set<OtdCarrierOrderDetail> details = order.getDetails();
        /*重量	1
        件数	2
        体积	3*/
        if(orderPayable!=null) {
            Set<FeeOrderPayableProportion> set = new HashSet<>();
            double totalAmount = orderPayable.getTotalAmount();
            if (details != null && details.size() > 0) {
                for (OtdCarrierOrderDetail detail : details) {
                    double scale = 0;
                    switch (order.getCalculateType()){
                        case 1: {
                            scale = detail.getConfirmedWeight()/order.getTotalWeight();
                            break;
                        }
                        case 2: {
                            scale = detail.getConfirmedPackageQuantity()/order.getTotalPackageQuantity();
                            break;
                        }
                        case 3: {
                            scale = detail.getConfirmedVolume()/order.getTotalVolume();
                            break;
                        }
                        default:
                            break;
                    }

                    BigDecimal b = new BigDecimal(scale);
                    double s = b.setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();

                    FeeOrderPayableProportion proportion = new FeeOrderPayableProportion();
                    proportion.setOrderId(detail.getTransportOrderId());
                    proportion.setOrderPayableId(orderPayable.getOrderPayableId());
                    proportion.setOrderType(1);//先设置 表示
                    proportion.setScale(s);
                    proportion.setProportionType(1);
                    proportion.setAmount(totalAmount * scale);
                    proportion.setFeeOrderPayable(orderPayable);
                    set.add(proportion);
                }
            }
            orderPayable.setProportionDetails(set);
        }
        return orderPayable;
    }
    @Transactional
    public  UUID send( OtdCarrierOrderBean bean) {
        SysUser sysUser = IdentityUtils.getCurrentUser();
        OtdCarrierOrder model = new OtdCarrierOrder();
        BeanUtils.copyProperties(bean,model);
        if(model.getSendDate()==null){
            model.setSendDate(new Date());
        }
        model.setStartCity(baseRegionDao.get(model.getStartCityId()).getName());
        model.setDestCity(baseRegionDao.get(model.getDestCityId()).getName());
        model.setExpectedDate(getExpectedDate(model));//预计到达时间
        model.setStatus(2);//在途

        if(model.getCarrierOrderId()!=null&&!"".equals(model.getCarrierOrderId())){
            //将所有运输订单状态改为已审单，后面代码再将已选择的状态改为调度中
            List<OtdCarrierOrderDetail>detailList=otdCarrierOrderDetailService.getAllByField("carrierOrderId", model.getCarrierOrderId());
            this.statusBackBeforeUpdate(detailList);

            model.setModifyDate(DateUtils.getTimestampNow());
            model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
            getDao().update(model);
        }else{
            model.setCreateDate(DateUtils.getTimestampNow());
            if(sysUser!=null){
                model.setCreateUserId(sysUser.getUserId());
            }
            getDao().create(model);
        }

        FeeOrderPayable orderPayable=payableCalculator.calculate(model);

        orderPayable= calculatorProportion(model,orderPayable);
        orderPayable.setSourceOrderId(model.getCarrierOrderId());
        UUID orderPayableId=feeOrderPayableDao.create(orderPayable);

        //保存到明细历史表中
        Set<FeeOrderPayableDetail> details=orderPayable.getDetails();
        for(FeeOrderPayableDetail detail:details){
            FeeOrderPayableDetailH h=new FeeOrderPayableDetailH();
            BeanUtils.copyProperties(detail, h);
            h.setVersion(0);
            h.setOrderPayableId(orderPayableId);
            h.setOperationDate(DateUtils.getTimestampNow());
            if(sysUser!=null){
                h.setOperationUserId(sysUser.getUserId());
            }else{
                h.setOperationUserId(bean.getCreateUserId());
            }
            feeOrderPayableDetailHDao.create(h);
        }

        //保存到分摊历史表中
        Set<FeeOrderPayableProportion> proportions=orderPayable.getProportionDetails();
        for(FeeOrderPayableProportion proportion:proportions){
            FeeOrderPayableProportionH h=new FeeOrderPayableProportionH();
            BeanUtils.copyProperties(proportion, h);
            h.setVersion(0);
            h.setOrderPayableId(orderPayableId);
            feeOrderPayableProportionHDao.create(h);
        }

        //保存应付费用日志
        feeOrderPayableLogDao.autoCreate(orderPayable.getOrderPayableId(), orderPayable.getStatus(), "创建应付费用");
        if(bean.getDetailViews() !=null){
            //计算分摊
            //保存订单
            for(OtdCarrierOrderDetailView  detailView: bean.getDetailViews()){
                //修改订单状态
                OtdTransportOrder t= otdTransportOrderDao.get(detailView.getTransportOrderId());
                if(t.getMergeStatus()==3){//合单：将合单发运标记为1，被合单更新状态
                    List<OtdTransportOrder>orders=otdTransportOrderDao.getAllByField("mergeTransportOrderId", t.getTransportOrderId());
                    for(OtdTransportOrder otdTransportOrder:orders){
                        otdTransportOrder.setStatus(4);
                        otdTransportOrder.setSentDate(model.getSendDate());
                        otdTransportOrderDao.update(otdTransportOrder);
                    }
                    t.setMergeSendStatus(1);
                    t.setStatus(null);
                }else {
                    t.setStatus(4);
                    if(t.getMergeStatus()==2){//将合单发运标记更新为1
                        OtdTransportOrder otdTransportOrder=otdTransportOrderDao.get(t.getMergeTransportOrderId());
                        otdTransportOrder.setMergeSendStatus(1);
                        otdTransportOrderDao.update(otdTransportOrder);
                    }
                }
                t.setReceiptPageNumber(detailView.getReceiptPageNumber());
                t.setWrapType(detailView.getWrapType());
                t.setSentDate(model.getSendDate());//发运时间
                otdTransportOrderDao.update(t);
            }
        }

        otdCarrierOrderLogDao.autoCreate(model.getCarrierOrderId(),model.getStatus(),"托运单发运");
        return orderPayableId;

    }

    /**
     *
     * @param carrierId   托运单ID
     * @param status   当前状态
     * @param message  日志信息
     */
    @Transactional
    public void updateStatus(UUID carrierId ,Integer status,String message){
        OtdCarrierOrder order = getDao().get(carrierId);
        order.setStatus(status);
        if(status==-1){
            otdCarrierOrderDetailDao.deleteByCarrierID(order.getCarrierOrderId());//
            getDao().cancel(order);
        }
        otdCarrierOrderLogDao.autoCreate(order.getCarrierOrderId(),order.getStatus(),message);
        super.update(order);
    }
    @Transactional
    public void ok(UUID carrierId)
    {
        OtdCarrierOrder otdCarrierOrder = getDao().get(carrierId);

        otdCarrierOrder.setStatus(4);
        otdCarrierOrderLogDao.autoCreate(otdCarrierOrder.getCarrierOrderId(),otdCarrierOrder.getStatus(),"托运单完成");
        super.update(otdCarrierOrder);
    }
    @Transactional
    public void resetSend(UUID carrierId)
    {
        OtdCarrierOrder otdCarrierOrder = getDao().get(carrierId);
        otdCarrierOrder.setStatus(1);
        otdCarrierOrder.setSendDate(null);
        otdCarrierOrderLogDao.autoCreate(otdCarrierOrder.getCarrierOrderId(),otdCarrierOrder.getStatus(),"托运单发运撤销");
        super.update(otdCarrierOrder);
        for(OtdCarrierOrderDetail  detail: otdCarrierOrder.getDetails()){
            //修改订单状态
            OtdTransportOrder t= otdTransportOrderDao.get(detail.getTransportOrderId());
            if(t.getMergeStatus()==3){
                List<OtdTransportOrder>orders=otdTransportOrderDao.getAllByField("mergeTransportOrderId", t.getTransportOrderId());
                for(OtdTransportOrder otdTransportOrder:orders){
                    otdTransportOrder.setStatus(3);
                    otdTransportOrderDao.update(otdTransportOrder);
                }
            }else {
                t.setStatus(3);//在途
                otdTransportOrderDao.update(t);
            }
        }
        feeOrderPayableService.deleteChildrenById(carrierId);
    }
    @Transactional
    public void batchArrive(Set<UUID> carrierIds){
        if(carrierIds!=null&&carrierIds.size()>0){
            for(UUID carrierId:carrierIds){
                OtdCarrierOrder otdCarrierOrder = getDao().get(carrierId);

                otdCarrierOrder.setStatus(3);
                otdCarrierOrderLogDao.autoCreate(otdCarrierOrder.getCarrierOrderId(),otdCarrierOrder.getStatus(),"托运单到达");
                super.update(otdCarrierOrder);

                List<OtdCarrierOrderDetail> carrierOrderDetailList= otdCarrierOrderDetailDao.getAllByField("carrierOrderId", otdCarrierOrder.getCarrierOrderId());
                if(carrierOrderDetailList!=null&&carrierOrderDetailList.size()>0) {
                    for (OtdCarrierOrderDetail detail :carrierOrderDetailList) {
                        //修改订单状态
                        OtdTransportOrder t = otdTransportOrderDao.get(detail.getTransportOrderId());
                        if(t.getMergeStatus()!=null&&t.getMergeStatus()==3){
                            List<OtdTransportOrder> mergeList= otdTransportOrderDao.getAllByField("mergeTransportOrderId", t.getTransportOrderId());
                            if(mergeList!=null&&mergeList.size()>0) {
                                for (OtdTransportOrder transportOrder :mergeList) {
                                    //修改订单状态
                                    transportOrder.setStatus(5);
                                    otdTransportOrderDao.update(transportOrder);
                                }
                            }
                        }else {
                            t.setStatus(5);//到达
                        }
                        otdTransportOrderDao.update(t);
                    }
                }
            }
        }
    }

    @Transactional
    public void updateTransportOrderStatus(UUID transportOrderId){
        OtdTransportOrder order= otdTransportOrderDao.get(transportOrderId);
        order.setStatus(2);
        otdTransportOrderDao.saveOrUpdate(order);
    }

    @Transactional
    public boolean carrierOrderNumberIsExist(UUID carrierId,String number) {
        return getDao().OrderNumberIsExist(carrierId,number);
    }

    @Transactional
    public boolean carrierOrderNumberIsExist(UUID carrierId,String number,UUID carrierOrderId) {
        return getDao().OrderNumberIsExist(carrierId,number,carrierOrderId);
    }

    @Transactional
    public JsonResult repealCancel(UUID carrierId){
        OtdCarrierOrder otdCarrierOrder=getDao().get(carrierId);
        List<OtdCarrierOrderDetail>details=otdCarrierOrderDetailDao.getAllByField("carrierOrderId",carrierId);
        //判断是否存在其他托运单中
        boolean flag=false;
        for(OtdCarrierOrderDetail otdCarrierOrderDetail:details) {
            OtdTransportOrder t = otdTransportOrderDao.get(otdCarrierOrderDetail.getTransportOrderId());
            if (t.getMergeStatus() == 3) {//合单：将合单发运标记为1，被合单更新状态
                List<OtdTransportOrder> orders = otdTransportOrderDao.getAllByField("mergeTransportOrderId", t.getTransportOrderId());
                for (OtdTransportOrder otdTransportOrder : orders) {
                    OtdTransportOrderView otdTransportOrderView=otdTransportOrderViewDao.get(otdTransportOrder.getTransportOrderId());
                    if(!StringUtils.isEmpty(otdTransportOrderView.getCarrierOrderNumber())){
                        flag=true;
                        break;
                    }
                }
            } else {
                OtdTransportOrderView otdTransportOrderView=otdTransportOrderViewDao.get(otdCarrierOrderDetail.getTransportOrderId());
                if(!StringUtils.isEmpty(otdTransportOrderView.getCarrierOrderNumber())){
                    flag=true;
                    break;
                }
            }
        }
        if(flag){
            return JsonResult.error("明细中运输订单已存在于其他托运单中，不能进行还原操作！");
        }
        //更新运输订单状态
        for(OtdCarrierOrderDetail otdCarrierOrderDetail:details) {
            OtdTransportOrder t = otdTransportOrderDao.get(otdCarrierOrderDetail.getTransportOrderId());
            if (t.getMergeStatus() == 3) {//合单：将合单发运标记为1，被合单更新状态
                List<OtdTransportOrder> orders = otdTransportOrderDao.getAllByField("mergeTransportOrderId", t.getTransportOrderId());
                for (OtdTransportOrder otdTransportOrder : orders) {
                    otdTransportOrder.setStatus(3);
                    otdTransportOrderDao.update(otdTransportOrder);
                }
                t.setMergeSendStatus(1);
                t.setStatus(null);
            } else {
                t.setStatus(3);
                if (t.getMergeStatus() == 2) {//将合单发运标记更新为1
                    OtdTransportOrder otdTransportOrder = otdTransportOrderDao.get(t.getMergeTransportOrderId());
                    otdTransportOrder.setMergeSendStatus(1);
                    otdTransportOrderDao.update(otdTransportOrder);
                }
            }
            otdTransportOrderDao.updateStatus(t);
        }
        otdCarrierOrder.setStatus(1);
        getDao().update(otdCarrierOrder);
        otdCarrierOrderLogDao.autoCreate(carrierId,1,"托运单取消还原");
        return JsonResult.success();
    }
}
