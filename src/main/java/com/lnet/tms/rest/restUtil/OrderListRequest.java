package com.lnet.tms.rest.restUtil;

import java.util.UUID;

/**
 * Created by Administrator on 2015/7/27.
 */
public class OrderListRequest {
    private UUID userId;
    private String number;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
