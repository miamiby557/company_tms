package com.lnet.tms.service.otd;

import com.lnet.tms.common.ImportResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.SequenceDao;
import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.dao.crm.CrmClientDao;
import com.lnet.tms.dao.crm.CrmClientLineDao;
import com.lnet.tms.dao.fee.FeeOrderReceivableDao;
import com.lnet.tms.dao.fee.FeeOrderReceivableDetailHDao;
import com.lnet.tms.dao.fee.FeeOrderReceivableLogDao;
import com.lnet.tms.dao.otd.*;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.model.crm.CrmClient;
import com.lnet.tms.model.crm.CrmClientLine;
import com.lnet.tms.model.fee.FeeOrderReceivable;
import com.lnet.tms.model.fee.FeeOrderReceivableDetail;
import com.lnet.tms.model.fee.FeeOrderReceivableDetailH;
import com.lnet.tms.model.fee.FeeOrderReceivableLog;
import com.lnet.tms.model.otd.*;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.fee.ReceivableCalculator;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.ExcelUtils;
import com.lnet.tms.utility.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OtdTransportOrderService extends CrudService<OtdTransportOrder, UUID, OtdTransportOrderDao> {

    @Autowired
    public void setBaseDao(OtdTransportOrderDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private BaseRegionDao baseRegionDao;

    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    private OtdTransportOrderLogDao otdTransportOrderLogDao;

    @Autowired
    private ReceivableCalculator receivableCalculator;

    @Autowired
    private FeeOrderReceivableDao feeOrderReceivableDao;

    @Autowired

    private CrmClientLineDao crmClientLineDao;

    @Autowired
    private FeeOrderReceivableLogDao feeOrderReceivableLogDao;

    @Autowired
    private OtdTransportOrderReceiptDao otdTransportOrderReceiptDao;

    @Autowired
    private FeeOrderReceivableDetailHDao feeOrderReceivableDetailHDao;

    @Autowired
    private CrmClientDao crmClientDao;

    @Autowired
    private OtdPickupOrderDao otdPickupOrderDao;

    @Autowired
    private OtdCarrierOrderDetailDao otdCarrierOrderDetailDao;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public JsonResult createOrder(OtdTransportOrder model) {
        if (this.clientOrderNumberIsExist(model.getClientId(), model.getClientOrderNumber())) {
            return JsonResult.error("此客户单号已存在！");
        }
        if (model.getStartCityId() != null) {
            model.setStartCity(baseRegionDao.get(model.getStartCityId()).getName());
        }
        if (model.getDestCityId() != null) {
            model.setDestCity(baseRegionDao.get(model.getDestCityId()).getName());
        }
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        model.setConfirmedWeight(model.getTotalWeight());
        model.setConfirmedItemQuantity(model.getTotalItemQuantity());
        model.setConfirmedPackageQuantity(model.getTotalPackageQuantity());
        model.setConfirmedVolume(model.getTotalVolume());
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        model.setStatus(1);//订单状态：接单中
        model.setMergeStatus(1);//合单状态：未合单
        String number = sequenceDao.formatSequenceNumber("SEQ_TRANORDER_NUMBER", "00000000", "LNET", "");
        model.setLnetOrderNumber(number);

        UUID otdTransportOrderId = super.create(model);
        //记录日志
        otdTransportOrderLogDao.autoCreate(otdTransportOrderId, 1, "增加订单");

        //回单
        OtdTransportOrderReceipt receipt = new OtdTransportOrderReceipt();
        receipt.setTransportOrderId(otdTransportOrderId);
        receipt.setStatus(1);
        receipt.setCreateDate(DateUtils.getTimestampNow());
        receipt.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        otdTransportOrderReceiptDao.create(receipt);

        return JsonResult.success();
    }

    @Transactional
    public UUID mergeOrder(OtdTransportOrderBean bean) {
        OtdTransportOrder order=new OtdTransportOrder();
        BeanUtils.copyProperties(bean,order);
        List<UUID>transportOrderIds=bean.getTransportOrderIds();

        if (order.getStartCityId() != null) {
            order.setStartCity(baseRegionDao.get(order.getStartCityId()).getName());
        }
        if (order.getDestCityId() != null) {
            order.setDestCity(baseRegionDao.get(order.getDestCityId()).getName());
        }
        order.setCreateDate(DateUtils.getTimestampNow());
        order.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        order.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        String number = sequenceDao.formatSequenceNumber("SEQ_TRANORDER_NUMBER", "00000000", "LNET", "");
        order.setLnetOrderNumber(number);
        order.setMergeStatus(3);//合单
        //判断被合单的运输订单中是否有已开托运单的，如有标记合单发运状态为1，否之为0
        int mergeSendStatus=0;
        for (UUID id : transportOrderIds) {
            OtdTransportOrder otdTransportOrder = getDao().get(id);
            if(otdTransportOrder.getStatus()>2){
                mergeSendStatus=1;
                break;
            }
        }
        order.setMergeSendStatus(mergeSendStatus);
        UUID mergeTransportOrderId = getDao().create(order);//创建合单

        //修改被合单的订单
        for (UUID id : transportOrderIds) {
            OtdTransportOrder otdTransportOrder = getDao().get(id);
            otdTransportOrder.setRemark("已被合单，合单单号["+order.getClientOrderNumber()+"]");
            otdTransportOrder.setMergeStatus(2);//已合单
            otdTransportOrder.setMergeTransportOrderId(mergeTransportOrderId);
            //删除原来的应付
            if (otdTransportOrder.getStatus() > 1) {
                FeeOrderReceivable feeOrderReceivable = feeOrderReceivableDao.getByField("sourceOrderId", id);
                this.deleteFeeOrderReceivable(feeOrderReceivable);
            } else if (otdTransportOrder.getStatus() == 1) {
                otdTransportOrder.setStatus(2);//把接单中的订单改为已审单
            }
            getDao().update(otdTransportOrder);
            otdTransportOrderLogDao.autoCreate(id, otdTransportOrder.getStatus(), "已合单");
        }
        //增加合单的应付
        return this.confirm(getDao().get(mergeTransportOrderId), "merge");
    }


    @Transactional
    public UUID mergeAdd(OtdTransportOrderBean bean){
        OtdTransportOrder order=new OtdTransportOrder();
        BeanUtils.copyProperties(bean,order);
        List<UUID>mergeTransportOrderIds=bean.getTransportOrderIds();
        if (order.getStartCityId() != null) {
            order.setStartCity(baseRegionDao.get(order.getStartCityId()).getName());
        }
        if (order.getDestCityId() != null) {
            order.setDestCity(baseRegionDao.get(order.getDestCityId()).getName());
        }
        if(order.getTransportOrderId()==null){
            order.setCreateDate(DateUtils.getTimestampNow());
            order.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
            getDao().create(order);
        }else{
            //删除原来应收
            FeeOrderReceivable feeOrderReceivable = feeOrderReceivableDao.getByField("sourceOrderId", order.getTransportOrderId());
            this.deleteFeeOrderReceivable(feeOrderReceivable);
            order.setModifyDate(DateUtils.getTimestampNow());
            order.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        }
        int mergeSendStatus=order.getMergeSendStatus();
        if(mergeSendStatus==0){
            for (UUID id : mergeTransportOrderIds) {
                OtdTransportOrder otdTransportOrder = getDao().get(id);
                if(otdTransportOrder.getStatus()>2){
                    mergeSendStatus=1;
                    break;
                }
            }
        }
        order.setMergeSendStatus(mergeSendStatus);
        getDao().update(order);
        //修改原来被合单备注
        List<OtdTransportOrder>oldMergeTransportOrders=getDao().getAllByField("mergeTransportOrderId",order.getTransportOrderId());
        for(OtdTransportOrder otdTransportOrder:oldMergeTransportOrders){
            otdTransportOrder.setRemark("--已被合单，["+order.getClientOrderNumber()+"]");
            getDao().update(otdTransportOrder);
        }
        //修改被合单的订单
        for (UUID id : mergeTransportOrderIds) {
            OtdTransportOrder otdTransportOrder = getDao().get(id);
            otdTransportOrder.setRemark( "已被合单，合单单号["+order.getClientOrderNumber()+"]");
            otdTransportOrder.setMergeStatus(2);//已合单
            otdTransportOrder.setMergeTransportOrderId(order.getTransportOrderId());
            //删除原来的应付
            if (otdTransportOrder.getStatus() > 1) {
                FeeOrderReceivable feeOrderReceivable1= feeOrderReceivableDao.getByField("sourceOrderId", id);
                this.deleteFeeOrderReceivable(feeOrderReceivable1);
            } else if (otdTransportOrder.getStatus() == 1) {
                otdTransportOrder.setStatus(2);//把接单中的订单改为已审单
            }
            getDao().update(otdTransportOrder);
            otdTransportOrderLogDao.autoCreate(id, otdTransportOrder.getStatus(), "已合单");
        }
        //增加合单的应付
        return this.confirm(order, "merge");
    }

    @Transactional
    public JsonResult repealMerge(UUID transportOrderId) {
        OtdCarrierOrderDetail otdCarrierOrderDetail=otdCarrierOrderDetailDao.getByField("transportOrderId", transportOrderId);
        if(otdCarrierOrderDetail!=null){
            return JsonResult.error("合单已发运，不能进行撤销操作！");
        }
        FeeOrderReceivable feeOrderReceivable = feeOrderReceivableDao.getByField("sourceOrderId", transportOrderId);
        if (feeOrderReceivable.getStatus() != 1) {
            return JsonResult.error("该合单应收费用已确认，不能进行撤销操作！");
        }
        this.deleteFeeOrderReceivable(feeOrderReceivable);//删除应收费用
        List<OtdTransportOrder> orders = getDao().getAllByField("mergeTransportOrderId", transportOrderId);//被合单的运输订单
        for (OtdTransportOrder order : orders) {
            order = getDao().getWith(order.getTransportOrderId(), "details");
            order.setRemark(null);
            order.setMergeStatus(1);
            order.setMergeTransportOrderId(null);
            getDao().update(order);
            otdTransportOrderLogDao.autoCreate(order.getTransportOrderId(), order.getStatus(), "撤销合单");
            if (order.getStatus() > 1) {
                //重新计算应收费用
                this.confirm(order, "merge");
            }
        }
        getDao().deleteById(transportOrderId);
        return JsonResult.success();
    }

    @Override
    @Transactional
    public void update(OtdTransportOrder model) {
        model.setStartCity(baseRegionDao.get(model.getStartCityId()).getName());
        model.setDestCity(baseRegionDao.get(model.getDestCityId()).getName());
        model.setConfirmedWeight(model.getTotalWeight());
        model.setConfirmedItemQuantity(model.getTotalItemQuantity());
        model.setConfirmedPackageQuantity(model.getTotalPackageQuantity());
        model.setConfirmedVolume(model.getTotalVolume());
        model.setModifyDate(DateUtils.getTimestampNow());
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        super.update(model);
        //记录日志
        otdTransportOrderLogDao.autoCreate(model.getTransportOrderId(), model.getStatus(), "修改订单");
    }

    @Transactional
    public UUID updateAndConfirm(OtdTransportOrder model){
        model.setStartCity(baseRegionDao.get(model.getStartCityId()).getName());
        model.setDestCity(baseRegionDao.get(model.getDestCityId()).getName());
        model.setConfirmedWeight(model.getTotalWeight());
        model.setConfirmedItemQuantity(model.getTotalItemQuantity());
        model.setConfirmedPackageQuantity(model.getTotalPackageQuantity());
        model.setConfirmedVolume(model.getTotalVolume());
        model.setModifyDate(DateUtils.getTimestampNow());
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        getDao().update(model);
        return this.confirm(model,"confirm");
    }

    @Transactional
    public UUID confirm(OtdTransportOrder model, String flag) {
        UUID orderReceivableId = null;
        //计算应收费用
        if (!("confirm".equals(flag) && model.getMergeStatus() == 2)) {
            FeeOrderReceivable feeOrderReceivable = receivableCalculator.calculate(model, 3);
            orderReceivableId = feeOrderReceivableDao.create(feeOrderReceivable);

            //保存到明细历史表中
            Set<FeeOrderReceivableDetail> details = feeOrderReceivable.getDetails();
            for (FeeOrderReceivableDetail detail : details) {
                FeeOrderReceivableDetailH h = new FeeOrderReceivableDetailH();
                BeanUtils.copyProperties(detail, h);
                h.setOrderReceivableId(orderReceivableId);
                h.setVersion(0);
                h.setOperationDate(DateUtils.getTimestampNow());
                h.setOperationUserId(IdentityUtils.getCurrentUser().getUserId());
                feeOrderReceivableDetailHDao.create(h);
            }
            feeOrderReceivableLogDao.autoCreate(orderReceivableId, feeOrderReceivable.getStatus(), "创建应收费用");
        }
        if ("confirm".equals(flag)) {
            //保存应收费用日志
            //修改确认状态
            model.setStatus(2);
            getDao().update(model);
            //记录日志
            otdTransportOrderLogDao.autoCreate(model.getTransportOrderId(), model.getStatus(), "确认订单");
        }
        return orderReceivableId;
    }

    @Transactional
    public JsonResult repealConfirm(UUID transportOrderId) {
        OtdTransportOrder order = getDao().get(transportOrderId);
        if (order.getMergeStatus() == 1) {
            FeeOrderReceivable feeOrderReceivable = feeOrderReceivableDao.getByField("sourceOrderId", transportOrderId);
            if (feeOrderReceivable.getStatus() != 1) {
                return JsonResult.error("该订单应收费用已确认，不能进行撤销操作！");
            }
            this.deleteFeeOrderReceivable(feeOrderReceivable);
        }
        order.setStatus(1);
        getDao().update(order);
        //记录日志
        otdTransportOrderLogDao.autoCreate(order.getTransportOrderId(), order.getStatus(), "撤销确认");
        return JsonResult.success();
    }

    //删除应收费用
    @Transactional
    public void deleteFeeOrderReceivable(FeeOrderReceivable feeOrderReceivable) {
        UUID orderReceivableId = feeOrderReceivable.getOrderReceivableId();
        List<FeeOrderReceivableDetailH> feeOrderReceivableDetailHs = feeOrderReceivableDetailHDao.getAllByField("orderReceivableId", orderReceivableId);//删除应收明细历史
        if (feeOrderReceivableDetailHs != null && feeOrderReceivableDetailHs.size() > 0) {
            for (FeeOrderReceivableDetailH feeOrderReceivableDetailH : feeOrderReceivableDetailHs) {
                feeOrderReceivableDetailHDao.delete(feeOrderReceivableDetailH);
            }
        }
        List<FeeOrderReceivableLog> logs = feeOrderReceivableLogDao.getAllByField("orderReceivableId", orderReceivableId);//删除应收日志
        if (logs != null && logs.size() > 0) {
            for (FeeOrderReceivableLog log : logs) {
                feeOrderReceivableLogDao.delete(log);
            }
        }
        feeOrderReceivableDao.delete(feeOrderReceivable);
    }

    @Transactional
    public void cancel(UUID otdTransportOrderId) {
        //修改订单状态为取消
        OtdTransportOrder order = getDao().get(otdTransportOrderId);
        order.setStatus(-1);
        getDao().update(order);
        //增加一条订单操作记录
        otdTransportOrderLogDao.autoCreate(otdTransportOrderId, -1, "取消订单");
    }

    @Transactional
    public void repealCancel(UUID otdTransportOrderId) {
        OtdTransportOrder order = getDao().get(otdTransportOrderId);
        order.setStatus(1);
        getDao().update(order);
        otdTransportOrderLogDao.autoCreate(otdTransportOrderId, 1, "订单取消还原");
    }

    @Transactional
    public void updateStatus(OtdTransportOrder model) {
        super.update(model);
    }


    @Transactional
    public Date getExpectedDate(UUID clientId, Integer transportType, UUID startCityId, UUID destCityId, String orderDate) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("clientId", clientId);
        map.put("transportType", transportType);
        map.put("startCityId", startCityId);
        map.put("destCityId", destCityId);
        CrmClientLine line = crmClientLineDao.getByField(map);
        Boolean isIncludeThatDay = crmClientDao.get(clientId).getIsIncludeOrderDate();
        if (line != null) {
            Double timeLine = line.getTimeline();
            int expectedDay = (int) (timeLine / 24);
            double hour = timeLine % 24;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(orderDate));
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            if (isIncludeThatDay) {
                c.add((Calendar.DAY_OF_MONTH), expectedDay);
            } else {
                c.add((Calendar.DAY_OF_MONTH), expectedDay+1);
            }
            c.add(Calendar.HOUR, (int) Math.ceil(hour));
            return c.getTime();
        }
        return null;
    }

    @Transactional
    public Boolean clientOrderNumberIsExist(UUID clientId, String clientOrderNumber) {
        return getDao().clientOrderNumberIsExist(clientId, clientOrderNumber);
    }

    @Transactional
    public Boolean clientOrderNumberIsExist(UUID clientId, String clientOrderNumber, UUID transportOrderId) {
        return getDao().clientOrderNumberIsExist(clientId, clientOrderNumber, transportOrderId);
    }

    @Transactional
    public List<ImportResult> readExcel(MultipartFile file) throws IOException {
        ExcelUtils.Data orderData = ExcelUtils.readExcel(file.getInputStream(), 0);
        List<Map<String, Object>> orderDataMapList = orderData.getRows();
        List<OtdTransportOrderBean> orders = new ArrayList<>();
        List<ImportResult> results = new ArrayList<>();
        Integer rowsNum = orderData.getRowsNum();

        //判断模板是否正确
        List<ExcelUtils.Column> OrderColumns = orderData.getColumns();
        boolean template = true;
        if (OrderColumns != null && OrderColumns.size() > 0) {
            for (ExcelUtils.Column column : OrderColumns) {
                String title = column.getTitle();
                if (!("客户名称".equals(title) || "提货单号".equals(title) || "客户单号".equals(title) || "副单号".equals(title) || "商超单号".equals(title) ||"订单日期".equals(title) || "订单类型".equals(title)
                        || "运输方式".equals(title) || "始发城市".equals(title) || "目的城市".equals(title) || "收货公司".equals(title) || "收货人".equals(title) || "收货人电话".equals(title)
                        || "收货地址".equals(title) || "交接方式".equals(title) || "特殊要求".equals(title) || "计费方式".equals(title) || "紧急程度".equals(title) || "备注".equals(title)
                        || "客户单号".equals(title) || "货物编码".equals(title) || "货物名称".equals(title) || "货物型号".equals(title) || "数量".equals(title) || "件数".equals(title)
                        || "体积".equals(title) || "重量".equals(title) || "明细备注".equals(title))) {
                    template = false;
                }
            }
        } else {
            template = false;
        }
        if (!template) {
            ImportResult importResult = new ImportResult();
            importResult.setRowIndex(-1);
            importResult.setImportResult("异常");
            importResult.setFailReason("模板错误！");
            results.add(importResult);
            return results;
        }
        //判断是否填入数据
        if (rowsNum > 0) {
            for (int i = 0; i < rowsNum; i++) {
                OtdTransportOrderBean order = new OtdTransportOrderBean();
                List<OtdTransportOrderDetail> details = new ArrayList<>();
                OtdTransportOrderDetail detail = new OtdTransportOrderDetail();
                details.add(detail);
                order.setOrderDetails(details);
                orders.add(order);

                ImportResult importResult = new ImportResult();
                importResult.setRowIndex(i);
                importResult.setImportResult("成功");
                importResult.setFailReason("");
                results.add(importResult);
            }
        } else {
            ImportResult importResult = new ImportResult();
            importResult.setRowIndex(0);
            importResult.setImportResult("异常");
            importResult.setFailReason("无数据！");
            results.add(importResult);
            return results;
        }

        for (Map<String, Object> map : orderDataMapList) {
            int index = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("rowIndex".equals(entry.getKey().trim())) {
                    index = Integer.parseInt(String.valueOf(entry.getValue()).trim()) - 1;
                }
            }
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey().trim();
                String value = "";
                if (!StringUtils.isEmpty(entry.getValue())) {
                    value = entry.getValue().toString().trim();
                    if (value.matches("\\w*\\.\\w*E\\w*")) {
                        results = new ArrayList<>();
                        ImportResult importResult = new ImportResult();
                        importResult.setRowIndex(-1);
                        importResult.setImportResult("异常");
                        importResult.setFailReason("请将EXCEL所有单元格改为文本格式！");
                        results.add(importResult);
                        return results;
                    }else  if (value.matches("\\d+\\.0")) {
                        value = value.split("\\.")[0];//整数读入时会以.0结尾
                    }
                }
                if ("rowIndex".equals(key)) {
                    continue;
                } else if ("客户名称".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "客户名称不能为空；");
                    } else {
                        CrmClient crmClient = crmClientDao.getByField("name", value);
                        if (crmClient != null) {
                            orders.get(index).setClientId(crmClient.getClientId());
                            orders.get(index).setBillingCycle(crmClient.getSettleCycle());
                            orders.get(index).setPaymentType(crmClient.getPaymentType());
                        } else {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "客户名称不存在；");
                        }
                    }
                } else if ("提货单号".equals(key)) {
                    if (!StringUtils.isEmpty(value)) {
                        OtdPickupOrder otdPickupOrder = otdPickupOrderDao.getByField("pickupOrderNumber", value);
                        if (otdPickupOrder != null) {
                            orders.get(index).setPickupOrderId(otdPickupOrder.getPickupOrderId());
                        } else {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "提货单号不存在；");
                        }
                    }
                } else if ("客户单号".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "客户单号不能为空；");
                    } else {
                        if (value.length() > 50) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "客户单号长度不能超过50；");
                        } else {
                            orders.get(index).setClientOrderNumber(value);
                        }
                    }
                } else if ("副单号".equals(key)) {
                    if (!StringUtils.isEmpty(value) && value.length() > 50) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "副单号长度不能超过50；");
                    } else {
                        orders.get(index).setViceClientOrderNumber(value);
                    }
                } else if ("商超单号".equals(key)) {
                    if (!StringUtils.isEmpty(value) && value.length() > 50) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "副单号长度不能超过50；");
                    } else {
                        orders.get(index).setMarketClientNumber(value);
                    }
                }else if ("订单日期".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "订单日期不能为空；");
                    } else {
                        if (value.matches("20\\d{2}/[0,1][0-9]/[0-3][0-9]")) {
                            try {
                                Date orderDate = format.parse(value);
                                Date date = new Date();
                                if (orderDate.compareTo(date) > 0) {
                                    results.get(index).setImportResult("失败");
                                    results.get(index).setFailReason(results.get(index).getFailReason() + "订单日期不能大于当前日期；");
                                } else {
                                    Calendar c = Calendar.getInstance();
                                    int day = c.get(Calendar.DAY_OF_MONTH);
                                    if (day <= 7) {
                                        c.add(Calendar.MONTH, -1);
                                    }
                                    c.set(Calendar.DAY_OF_MONTH, 01);
                                    c.set(Calendar.HOUR_OF_DAY, 0);
                                    c.set(Calendar.MINUTE, 0);
                                    c.set(Calendar.SECOND, 0);
                                    Date minDate = c.getTime();
                                    if (minDate.compareTo(orderDate) > 0) {
                                        results.get(index).setImportResult("失败");
                                        results.get(index).setFailReason(results.get(index).getFailReason() + "订单日期不在有效范围内；");
                                    } else {
                                        orders.get(index).setOrderDate(orderDate);
                                    }
                                }
                            } catch (ParseException e) {
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason() + "订单日期格转换异常；");
                                e.printStackTrace();
                            }
                        } else {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "订单日期格式错误；");
                        }
                    }
                } else if ("订单类型".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "订单类型不能为空；");
                    } else {
                        if (!value.matches("\\d+(\\.0)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "订单类型请填写对应数值；");
                        } else {
                            Integer orderType = (int) Double.parseDouble(value);
                            if (orderType > 6) {
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason() + "订单类型不存在的数值；");
                            } else {
                                orders.get(index).setOrderType(orderType);
                            }
                        }
                    }
                } else if ("运输方式".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "运输方式不能为空；");
                    } else {
                        if (!value.matches("\\d+(\\.0)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "运输方式请填写对应数值；");
                        } else {
                            Integer transportType = (int) Double.parseDouble(value);
                            if (transportType > 9) {
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason() + "运输方式不存在的数值；");
                            } else {
                                orders.get(index).setTransportType(transportType);
                            }
                        }
                    }
                } else if ("始发城市".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "始发城市不能为空；");
                    } else {
                        BaseRegion baseRegion = baseRegionDao.getByField("name", value);
                        if (baseRegion != null) {
                            orders.get(index).setStartCity(value);
                            orders.get(index).setStartCityId(baseRegion.getRegionId());
                        } else {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "始发城市名称不正确；");
                        }
                    }
                } else if ("目的城市".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "目的城市不能为空；");
                    } else {
                        BaseRegion baseRegion = baseRegionDao.getByField("name", value);
                        if (baseRegion != null) {
                            orders.get(index).setDestCity(value);
                            orders.get(index).setDestCityId(baseRegion.getRegionId());
                        } else {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "目的城市名称不正确；");
                        }
                    }
                } else if ("收货公司".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "收货公司不能为空；");
                    } else {
                        if (value.length() > 250) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "收货公司长度不能超过250；");
                        } else {
                            orders.get(index).setReceiveCompany(value);
                        }
                    }
                } else if ("收货人".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "收货人不能为空；");
                    } else {
                        if (value.length() > 50) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "收货人长度不能超过50；");
                        } else {
                            orders.get(index).setReceiveMan(value);
                        }
                    }
                } else if ("收货人电话".equals(key)) {
                    if(!StringUtils.isEmpty(value)){
                        if (value.length() > 50) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "收货人电话长度不能超过50；");
                        } else {
                            orders.get(index).setReceivePhone(value);
                        }
                    }
                } else if ("收货地址".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "收货地址不能为空；");
                    } else {
                        if (value.length() > 250) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "收货地址长度不能超过250；");
                        } else {
                            orders.get(index).setReceiveAddress(value);
                        }
                    }
                } else if ("交接方式".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "交接方式不能为空；");
                    } else {
                        if (!value.matches("\\d+(\\.0)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "交接方式请填写对应数值；");
                        } else {
                            Integer handoverType = (int) Double.parseDouble(value);
                            if (handoverType > 3) {
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason() + "交接方式不存在的数值；");
                            } else {
                                orders.get(index).setHandoverType(handoverType);
                            }
                        }
                    }
                } else if ("特殊要求".equals(key)) {
                    if (!StringUtils.isEmpty(value) && value.length() > 250) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "特殊要求长度不能超过250；");
                    } else {
                        orders.get(index).setSpecialRequest(value);
                    }
                } else if ("计费方式".equals(key)) {
                    if (StringUtils.isEmpty(value)) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "计费方式不能为空；");
                    } else {
                        if (!value.matches("\\d+(\\.0)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "计费方式请填写对应数值；");
                        } else {
                            Integer calculateType = (int) Double.parseDouble(value);
                            if (calculateType > 7) {
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason() + "计费方式不存在的数值；");
                            } else {
                                orders.get(index).setCalculateType(calculateType);
                            }
                        }
                    }
                } else if ("紧急程度".equals(key)) {
                    if (!StringUtils.isEmpty(value)) {
                        if (!value.matches("\\d+(\\.0)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "紧急程度请填写对应数值；");
                        } else {
                            Integer urgencaLevel = (int) Double.parseDouble(value);
                            if (urgencaLevel > 1) {
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason() + "紧急程度不存在的数值；");
                            } else {
                                orders.get(index).setUrgencyLevel(urgencaLevel);
                            }
                        }
                    }
                } else if ("备注".equals(key)) {
                    if (!StringUtils.isEmpty(value) && value.length() > 250) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "备注长度不能超过250；");
                    } else {
                        orders.get(index).setRemark(value);
                    }
                } else if ("货物编码".equals(key)) {
                    if (!StringUtils.isEmpty(value) && value.length() > 100) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "货物编码长度不超过100；");
                    } else {
                        orders.get(index).getOrderDetails().get(0).setGoodsCode(value);
                    }
                } else if ("货物名称".equals(key)) {
                    if (!StringUtils.isEmpty(value) && value.length() > 100) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "货物名称长度不超过100；");
                    } else {
                        orders.get(index).getOrderDetails().get(0).setGoodsName(value);
                    }
                } else if ("货物型号".equals(key)) {
                    if (!StringUtils.isEmpty(value) && value.length() >100) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "货物型号长度不超过100；");
                    } else {
                        orders.get(index).getOrderDetails().get(0).setGoodsType(value);
                    }
                } else if ("数量".equals(key)) {
                    if (!StringUtils.isEmpty(value)) {
                        if (!value.matches("\\d+(\\.0)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "数量格式不正确；");
                        } else {
                            orders.get(index).getOrderDetails().get(0).setTotalItemQuantity((long) Double.parseDouble(value));
                        }
                    }
                } else if ("件数".equals(key)) {
                    if (!StringUtils.isEmpty(value)) {
                        if (!value.matches("\\d+(\\.0)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "件数格式不正确；");
                        } else {
                            orders.get(index).getOrderDetails().get(0).setTotalPackageQuantity((long) Double.parseDouble(value));
                        }
                    }
                } else if ("体积".equals(key)) {
                    if (!StringUtils.isEmpty(value)) {
                        if (!value.matches("(\\d+)(\\.\\d+)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "体积格式不正确；");
                        } else {
                            Double totalVolume = Double.parseDouble(value);
                            orders.get(index).getOrderDetails().get(0).setTotalVolume(totalVolume);
                        }
                    }
                } else if ("重量".equals(key)) {
                    if (!StringUtils.isEmpty(value)) {
                        if (!value.matches("(\\d+)(\\.\\d+)?")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason() + "重量格式不正确；");
                        } else {
                            orders.get(index).getOrderDetails().get(0).setTotalWeight(Double.parseDouble(value));
                        }
                    }
                } else if ("明细备注".equals(key)) {
                    if (!StringUtils.isEmpty(value) && value.length() > 250) {
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason() + "备注长度不能超过250；");
                    } else {
                        orders.get(index).getOrderDetails().get(0).setRemark(value);
                    }
                }
            }
        }
        //判断至少包含一条明细
        for (int i = 0; i < results.size(); i++) {
            ImportResult result = results.get(i);
            if ("成功".equals(result.getImportResult())) {
                OtdTransportOrderDetail detail = orders.get(i).getOrderDetails().get(0);
                if (detail.getTotalPackageQuantity() == null && StringUtils.isEmpty(detail.getGoodsCode()) && StringUtils.isEmpty(detail.getGoodsName()) && StringUtils.isEmpty(detail.getGoodsType())
                        && StringUtils.isEmpty(detail.getRemark()) && detail.getTotalItemQuantity() == null && detail.getTotalVolume() == null && detail.getTotalWeight() == null) {
                    results.get(i).setImportResult("失败");
                    results.get(i).setFailReason(results.get(i).getFailReason() + "明细记录不能为空；");
                }
            }
        }
        //同一个订单，有一条数据有问题，即不导入

        for (int i = 0; i < results.size(); i++) {
            ImportResult result = results.get(i);
            if ("成功".equals(result.getImportResult())) {
                String clientOrderNumber = orders.get(i).getClientOrderNumber();
                for (int j = 0; j < results.size(); j++) {
                    String clientOrderNumber1 = orders.get(j).getClientOrderNumber();
                    if(clientOrderNumber.equals(clientOrderNumber1)){
                        if(!"成功".equals(results.get(j).getImportResult())){
                            results.get(i).setImportResult("失败");
                            results.get(i).setFailReason("同订单号其他行数据异常");
                        }
                    }
                }
            }
        }
        //数据正确时，再封装otdTransportOrder的明细
        for(int i=0;i<results.size();i++){

            ImportResult result = results.get(i);
            if ("成功".equals(result.getImportResult())) {
                OtdTransportOrder otdTransportOrder=new OtdTransportOrder();
                Set<OtdTransportOrderDetail>details=new HashSet<>();
                BeanUtils.copyProperties(orders.get(i),otdTransportOrder);
                details.add(orders.get(i).getOrderDetails().get(0));
                String clientOrderNumber = orders.get(i).getClientOrderNumber();
                if(i<results.size()-1) {
                    for (int j = i + 1; j < results.size(); j++) {
                        String clientOrderNumber1 = orders.get(j).getClientOrderNumber();
                        if (clientOrderNumber.equals(clientOrderNumber1)) {
                            details.add(orders.get(j).getOrderDetails().get(0));
                            results.get(j).setImportResult("同第" + (i + 2) + "行结果");
                        }
                    }
                }
                otdTransportOrder.setDetails(details);
                //保存
                int itemNum=0;
                int packageNum=0;
                double volumeNum=0.0;
                double weightNum=0.0;
                for(OtdTransportOrderDetail detail:otdTransportOrder.getDetails()){
                    itemNum+=(detail.getTotalItemQuantity()==null?0:detail.getTotalItemQuantity());
                    packageNum+=(detail.getTotalPackageQuantity()==null?0:detail.getTotalPackageQuantity());
                    volumeNum+=detail.getTotalVolume()==null?0.0:detail.getTotalVolume();
                    weightNum+=(detail.getTotalWeight()==null?0.0:detail.getTotalWeight());
                }
                otdTransportOrder.setTotalItemQuantity(itemNum);
                otdTransportOrder.setTotalPackageQuantity(packageNum);
                otdTransportOrder.setTotalVolume(volumeNum);
                otdTransportOrder.setTotalWeight(weightNum);
                try {
                    otdTransportOrder.setExpectedDate(this.getExpectedDate(otdTransportOrder.getClientId(),otdTransportOrder.getTransportType(),
                            otdTransportOrder.getStartCityId(),otdTransportOrder.getDestCityId(), format1.format(otdTransportOrder.getOrderDate())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JsonResult jsonResult=this.createOrder(otdTransportOrder);
                if(!jsonResult.getSuccess()){
                    results.get(i).setImportResult("失败");
                    results.get(i).setFailReason(jsonResult.getMessage());
                }
            }
        }
        return results;
    }

    @Transactional
    public void addMarketNumber(OtdTransportOrderBean model){
        List<UUID>transportOrderIds=model.getTransportOrderIds();
        for(UUID transportOrderId:transportOrderIds){
            OtdTransportOrder order=getDao().getWith(transportOrderId,"details");
            order.setMarketClientNumber(model.getMarketClientNumber());
            getDao().update(order);
        }
    }

}
