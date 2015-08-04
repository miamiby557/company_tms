package com.lnet.tms.model.rpt;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by admin on 2015/7/20.
 */
@Entity
@Table(name = "RPT_VIEW_CLIENT_MONTHSUM", schema = "LNET_TMS", catalog = "")
public class RptViewClientMonthsum {
    private UUID clientId;
    private String orderMonth;
    private Double orderSum;
    private Double crossProfitSum;

    @Basic
    @Column(name = "CLIENT_ID")
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Id
    @Column(name = "ORDER_MONTH")
    public String getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(String orderMonth) {
        this.orderMonth = orderMonth;
    }

    @Basic
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
}
