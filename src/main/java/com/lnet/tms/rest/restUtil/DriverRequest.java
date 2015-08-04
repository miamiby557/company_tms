package com.lnet.tms.rest.restUtil;

import java.util.UUID;

/**
 * Created by Administrator on 2015/8/3.
 */
public class DriverRequest {
    private UUID userId;
    private String orderNumber;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
