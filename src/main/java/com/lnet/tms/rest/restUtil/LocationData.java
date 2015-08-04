package com.lnet.tms.rest.restUtil;

import java.util.UUID;

/**
 * Created by admin on 2015/7/20.
 */
public class LocationData {
    UUID userId;
    String city;

    public LocationData() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
