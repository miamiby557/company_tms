package com.lnet.tms.rest.restUtil;

import java.util.UUID;

/**
 * Created by Administrator on 2015/8/25.
 */
public class HelpItem {
    private UUID transportOrderId;
    private String clientName;
    private String orderNumber;

    public HelpItem(UUID transportOrderId, String clientName, String orderNumber) {
        this.transportOrderId = transportOrderId;
        this.clientName = clientName;
        this.orderNumber = orderNumber;
    }

    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
