package com.lnet.tms.service.fee;

import com.lnet.tms.model.base.BaseExacct;
import com.lnet.tms.model.crm.CrmClientLine;
import com.lnet.tms.model.crm.CrmClientQuote;
import com.lnet.tms.model.fee.FeeOrderReceivable;
import com.lnet.tms.model.fee.FeeOrderReceivableDetail;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.base.BaseExacctService;
import com.lnet.tms.service.crm.CrmClientLineService;
import com.lnet.tms.service.crm.CrmClientQuoteService;
import com.lnet.tms.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class ReceivableCalculator {

    @Autowired
    private BaseExacctService baseExacctService;
    @Autowired
    private CrmClientQuoteService crmClientQuoteService;
    @Autowired
    private CrmClientLineService crmClientLineService;

    private boolean isInRange(Double targetValue, Double minValue, Double maxValue) {
        if (minValue != null && maxValue != null)
            return (targetValue >= minValue && targetValue < maxValue);
        else if (minValue == null && maxValue != null)
            return targetValue < maxValue;
        else if (minValue != null && maxValue == null)
            return targetValue >= minValue;
        else return true;
    }

    private List<CrmClientQuote> findQuotesByExacctId(List<CrmClientQuote> quotes, UUID exacctId) {
        List<CrmClientQuote> result = new ArrayList<>();

        if (quotes != null && quotes.size() > 0) {
            for (CrmClientQuote quote : quotes) {
                if (exacctId.equals(quote.getExacctId())) {
                    result.add(quote);
                }
            }
        }
        return result;
    }

    private List<CrmClientQuote> findUseableQuote(List<CrmClientQuote> quotes, OtdTransportOrder order) {
        List<CrmClientQuote> result = new ArrayList<>();

        for (CrmClientQuote quote : quotes) {
            switch (order.getCalculateType()) {
                case 1:
                    if (isInRange(order.getConfirmedWeight(), quote.getMinimumCondiction(), quote.getMaxmumCondiction()))
                        result.add(quote);
                    break;
                case 2:
                    if (isInRange(order.getConfirmedPackageQuantity().doubleValue(), quote.getMinimumCondiction(), quote.getMaxmumCondiction()))
                        result.add(quote);
                    break;
                case 3:
                    if (isInRange(order.getConfirmedVolume(), quote.getMinimumCondiction(), quote.getMaxmumCondiction()))
                        result.add(quote);
                    break;
            }
        }

        return result;
    }

    public FeeOrderReceivable calculate(OtdTransportOrder order, Integer sourceOrderType) {
        FeeOrderReceivable orderReceivable = new FeeOrderReceivable();

        // 查询应收科目
        BaseExacct exacct = baseExacctService.getByField("code", "receivable");
        List<BaseExacct> baseExacctList = baseExacctService.getAllByField("superiorId", exacct.getExacctId());
        // 匹配客户线路信息, 始发城市，目的城市
        Map<String, Object> lineFilter = new HashMap<>();
        lineFilter.put("clientId", order.getClientId());
        lineFilter.put("startCityId", order.getStartCityId());
        lineFilter.put("destCityId", order.getDestCityId());
        lineFilter.put("transportType", order.getTransportType());
        List<CrmClientLine> cmrClientLineList = crmClientLineService.getAllByField(lineFilter);

        List<CrmClientQuote> crmClientQuotesList = new ArrayList<>();
        if (cmrClientLineList != null && cmrClientLineList.size() > 0) {

            Map<String, Object> quoteFilter = new HashMap<>();
            quoteFilter.put("clientLineId", cmrClientLineList.get(0).getClientLineId());
            quoteFilter.put("calculateType", order.getCalculateType());
            crmClientQuotesList = crmClientQuoteService.getAllByField(quoteFilter);
        }
        // 查询订单客户报价信息
        double sum = 0;
        //费用计算
        for (BaseExacct baseExacct : baseExacctList) {
            double calTypeSum = 0;

            FeeOrderReceivableDetail detail = new FeeOrderReceivableDetail();
            detail.setExacctId(baseExacct.getExacctId());

            List<CrmClientQuote> quotes = findQuotesByExacctId(crmClientQuotesList, baseExacct.getExacctId());

            if (quotes != null && quotes.size() > 0) {
                List<CrmClientQuote> baseQuote = findUseableQuote(quotes, order);//获取有效区间的一条报价
                if (baseQuote != null && baseQuote.size() > 0) {
                    if (baseQuote.get(0).getUnitPrice() == null) {//无单价，设置为0，计算后取最小收费
                        baseQuote.get(0).setUnitPrice(0.00);
                    }
                    switch (order.getCalculateType()) {
                        case 1:
                            calTypeSum = baseQuote.get(0).getUnitPrice() * order.getConfirmedWeight();
                            break;
                        case 2:
                            calTypeSum = baseQuote.get(0).getUnitPrice() * order.getConfirmedPackageQuantity();
                            break;
                        case 3:
                            calTypeSum = baseQuote.get(0).getUnitPrice() * order.getConfirmedVolume();
                            break;
                    }
                    calTypeSum = calTypeSum > baseQuote.get(0).getMinimumFee() ? calTypeSum : baseQuote.get(0).getMinimumFee();
                    detail.setClientQuoteId(baseQuote.get(0).getClientQuoteId());
                }
            }
            sum = sum + calTypeSum;
            detail.setAmount(calTypeSum);
            orderReceivable.getDetails().add(detail);
        }
        orderReceivable.setClientId(order.getClientId());
        orderReceivable.setSourceOrderId(order.getTransportOrderId());
        orderReceivable.setSourceOrderType(sourceOrderType);//提货单
        orderReceivable.setTotalAmount(sum);
        orderReceivable.setBillingCycle(order.getBillingCycle());
        orderReceivable.setPaymentType(order.getPaymentType());
        orderReceivable.setCalculateType(order.getCalculateType());
        orderReceivable.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        orderReceivable.setCreateDate(DateUtils.getTimestampNow());
        orderReceivable.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        orderReceivable.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        orderReceivable.setStatus(1);
        orderReceivable.setVersion(0);//修改版本号
        return orderReceivable;
    }
}
