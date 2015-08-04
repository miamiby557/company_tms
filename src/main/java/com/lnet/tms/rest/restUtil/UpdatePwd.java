package com.lnet.tms.rest.restUtil;


import java.util.UUID;

/**
 * Created by Administrator on 2015/7/31.
 */
public class UpdatePwd {
    private UUID userId;
    private String oldPwd;
    private String newPwd;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
