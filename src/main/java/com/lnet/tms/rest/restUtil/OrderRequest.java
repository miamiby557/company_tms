package com.lnet.tms.rest.restUtil;

import java.util.UUID;

/**
 * Created by Administrator on 2015/8/21.
 */
public class OrderRequest {

    private UUID id;
    private String orderNumber;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
