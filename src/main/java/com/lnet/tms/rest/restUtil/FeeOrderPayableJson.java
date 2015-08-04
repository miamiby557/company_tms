package com.lnet.tms.rest.restUtil;

/**
 * Created by admin on 2015/7/15.
 */
public class FeeOrderPayableJson {
    private String exacctName;
    private Double exacctMoney;
    private String exacctId;

    public FeeOrderPayableJson() {
    }

    public FeeOrderPayableJson(String exacctName, Double exacctMoney, String exacctId) {
        this.exacctName = exacctName;
        this.exacctMoney = exacctMoney;
        this.exacctId = exacctId;
    }

    public String getExacctName() {
        return exacctName;
    }

    public void setExacctName(String exacctName) {
        this.exacctName = exacctName;
    }

    public Double getExacctMoney() {
        return exacctMoney;
    }

    public void setExacctMoney(Double exacctMoney) {
        this.exacctMoney = exacctMoney;
    }

    public String getExacctId() {
        return exacctId;
    }

    public void setExacctId(String exacctId) {
        this.exacctId = exacctId;
    }
}
