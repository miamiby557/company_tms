package com.lnet.tms.model.rpt;

import javax.persistence.*;

/**
 * Created by admin on 2015/7/20.
 */
@Entity
@Table(name = "RPT_VIEW_OTD_MONTHSUM", schema = "LNET_TMS", catalog = "")
public class RptViewOtdMonthsum {
    private String orderMonth;
    private Double orderSum;
    private Double crossProfitSum;

    @Basic
    @Column(name = "ORDER_MONTH")
    public String getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(String orderMonth) {
        this.orderMonth = orderMonth;
    }

    @Id
    @Column(name = "ORDER_SUM")
    public Double getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Double orderSum) {
        this.orderSum = orderSum;
    }

    @Basic
    @Column(name = "CROSS_PROFIT_SUM")
    public Double getCrossProfitSum() {
        return crossProfitSum;
    }

    public void setCrossProfitSum(Double crossProfitSum) {
        this.crossProfitSum = crossProfitSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RptViewOtdMonthsum that = (RptViewOtdMonthsum) o;

        if (crossProfitSum != null ? !crossProfitSum.equals(that.crossProfitSum) : that.crossProfitSum != null)
            return false;
        if (orderMonth != null ? !orderMonth.equals(that.orderMonth) : that.orderMonth != null) return false;
        if (orderSum != null ? !orderSum.equals(that.orderSum) : that.orderSum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderMonth != null ? orderMonth.hashCode() : 0;
        result = 31 * result + (orderSum != null ? orderSum.hashCode() : 0);
        result = 31 * result + (crossProfitSum != null ? crossProfitSum.hashCode() : 0);
        return result;
    }
}
