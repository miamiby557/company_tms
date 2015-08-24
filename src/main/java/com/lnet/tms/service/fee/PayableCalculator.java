package com.lnet.tms.service.fee;

import com.lnet.tms.model.base.BaseExacct;
import com.lnet.tms.model.crm.CrmClientLine;
import com.lnet.tms.model.crm.CrmClientQuote;
import com.lnet.tms.model.fee.FeeOrderPayable;
import com.lnet.tms.model.fee.FeeOrderPayableDetail;
import com.lnet.tms.model.otd.OtdCarrierOrder;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.model.scm.ScmCarrierLine;
import com.lnet.tms.model.scm.ScmCarrierQuote;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.base.BaseExacctService;
import com.lnet.tms.service.scm.ScmCarrierLineService;
import com.lnet.tms.service.scm.ScmCarrierQuoteService;
import com.lnet.tms.service.scm.ScmCarrierService;
import com.lnet.tms.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PayableCalculator {
    @Autowired
    private BaseExacctService baseExacctService;
    @Autowired
    private ScmCarrierQuoteService scmCarrierQuoteService;
    @Autowired
    private ScmCarrierLineService scmCarrierLineService;
    @Autowired
    private ScmCarrierService scmCarrierService;


    private boolean isInRange(Double targetValue, Double minValue, Double maxValue) {
        if (minValue != null && maxValue != null)
            return (targetValue >= minValue && targetValue < maxValue);
        else if (minValue == null && maxValue != null)
            return targetValue < maxValue;
        else if (minValue != null && maxValue == null)
            return targetValue >= minValue;
        else return true;
    }

    /**
     * @param quotes
     * @param exacctId 费用科目ID
     * @return
     */
    private List<ScmCarrierQuote> findQuotesByExacctId(List<ScmCarrierQuote> quotes, UUID exacctId) {
        List<ScmCarrierQuote> result = new ArrayList<>();

        if (quotes != null && quotes.size() > 0) {
            for (ScmCarrierQuote quote : quotes) {
                if (exacctId.equals(quote.getExacctId())) {
                    result.add(quote);
                }
            }
        }

        return result;
    }

    private List<ScmCarrierQuote> findUseableQuote(List<ScmCarrierQuote> quotes, OtdCarrierOrder order) {
        List<ScmCarrierQuote> result = new ArrayList<>();

        for (ScmCarrierQuote quote : quotes) {
            switch (order.getCalculateType()) {
                case 1:
                    if (isInRange(order.getTotalWeight(), quote.getMinimumCondiction(), quote.getMaxmumCondiction()))
                        result.add(quote);
                    break;
                case 2:
                    if (isInRange(order.getTotalPackageQuantity().doubleValue(), quote.getMinimumCondiction(), quote.getMaxmumCondiction()))
                        result.add(quote);
                    break;
                case 3:
                    if (isInRange(order.getTotalVolume(), quote.getMinimumCondiction(), quote.getMaxmumCondiction()))
                        result.add(quote);
                    break;
            }
        }

        return result;
    }

    public FeeOrderPayable calculate(OtdCarrierOrder order) {
        FeeOrderPayable orderPayable = new FeeOrderPayable();
        //查询应付科目
        BaseExacct exacct = baseExacctService.getByField("code", "payable");
        List<BaseExacct> baseExacctList = baseExacctService.getAllByField("superiorId", exacct.getExacctId());
        //查询承运商线路信息
        //List<ScmCarrierLine> scmCarrierLineList=scmCarrierLineService.getAllByField("carrierId",order.getCarrierId());
        Map<String, Object> lineFilter = new HashMap<>();
        lineFilter.put("carrierId", order.getCarrierId());
        lineFilter.put("startCityId", order.getStartCityId());
        lineFilter.put("destCityId", order.getDestCityId());
        lineFilter.put("transportType", order.getTransportType());
        List<ScmCarrierLine> scmCarrierLineList = scmCarrierLineService.getAllByField(lineFilter);
        //查询承运商报价信息
        List<ScmCarrierQuote> scmCarrierQuotesList = null;
        if (scmCarrierLineList != null && scmCarrierLineList.size() > 0) {
            Map<String, Object> quoteFilter = new HashMap<>();
            quoteFilter.put("carrierLineId", scmCarrierLineList.get(0).getCarrierLineId());
            quoteFilter.put("calculateType", order.getCalculateType());
            scmCarrierQuotesList = scmCarrierQuoteService.getAllByField(quoteFilter);
        }

        //查询承运商信息
        List<ScmCarrier> scmCarrierList = scmCarrierService.getAllByField("carrierId", order.getCarrierId());

        Set<FeeOrderPayableDetail> details = new HashSet<>();

        //费用计算
        double sum = 0;
        for (BaseExacct baseExacct : baseExacctList) {
            FeeOrderPayableDetail detail = new FeeOrderPayableDetail();
            detail.setExacctId(baseExacct.getExacctId());
            double calTypeSum = 0;
            List<ScmCarrierQuote> quotes = findQuotesByExacctId(scmCarrierQuotesList, baseExacct.getExacctId());
            if (quotes != null && quotes.size() > 0) {
                List<ScmCarrierQuote> baseQuote = findUseableQuote(quotes, order);//获取有效区间的一条报价
                if (baseQuote != null && baseQuote.size() > 0) {
                    Double unitPrice = 0.0;
                    if (baseQuote.get(0).getUnitPrice() != null) {//单价不是null
                        unitPrice = baseQuote.get(0).getUnitPrice();
                    }
                    switch (order.getCalculateType()) {
                        case 1:
                            calTypeSum = unitPrice * order.getTotalWeight();
                            break;
                        case 2:
                            calTypeSum = unitPrice * order.getTotalPackageQuantity();
                            break;
                        case 3:
                            calTypeSum = unitPrice * order.getTotalVolume();
                            break;
                    }
                    calTypeSum = calTypeSum > baseQuote.get(0).getMinimumFee() ? calTypeSum : baseQuote.get(0).getMinimumFee();
                    detail.setCarrierQuoteId(baseQuote.get(0).getCarrierQuoteId());
                }
            }
            //判断当时上楼费时，如托运单有中转或是不产生上楼费时，将上楼费的应付设为0
            if("payable_upstairs".equals(baseExacct.getCode())){
                if(order.getTransferOrganizationId()!=null || !order.getIsUpstairs()){
                    calTypeSum=0;
                    detail.setCarrierQuoteId(null);
                }
            }

            detail.setAmount(calTypeSum);
            sum = calTypeSum + sum;

            detail.setExacctId(baseExacct.getExacctId());
            details.add(detail);
        }

        orderPayable.setCarrierId(order.getCarrierId());
        orderPayable.setSourceOrderId(order.getCarrierOrderId());
        orderPayable.setSourceOrderType(2);//托运单
        orderPayable.setStatus(1);//待确认
        orderPayable.setTotalAmount(sum);
        orderPayable.setBillingCycle(order.getBillingCycle());
        orderPayable.setPaymentType(order.getPaymentType());
        orderPayable.setCalculateType(order.getCalculateType());
        SysUser sysUser = IdentityUtils.getCurrentUser();
        orderPayable.setCreateUserId(sysUser.getUserId());
        orderPayable.setCreateDate(DateUtils.getTimestampNow());
        orderPayable.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        orderPayable.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        orderPayable.setVersion(0);//版本号0
        orderPayable.setDetails(details);
        return orderPayable;
    }
}
