package com.lnet.tms.model.otd;
import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by admin on 2015/5/13.
 */
public class OtdOrderSignBean {
    private UUID transportOrderId;
    private String signMan;
    private Timestamp signDate;
    private String signManCard;
    private String agentSignMan;
    private String agentSignManCard;
    private String remark;
    private UUID createUserId;
    private Timestamp createDate;
    private UUID modifyUserId;
    private Timestamp modifyDate;
    private Boolean isAbnormal;
    @Basic
    @Column(name = "IS_ABNORMAL", nullable = true, insertable = true, updatable = true)
    public Boolean getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Boolean isAbnormal) {
        this.isAbnormal = isAbnormal;
    }
    private Set<UUID> transportOrderIds;

    public Set<UUID> getTransportOrderIds() {
        return transportOrderIds;
    }

    public void setTransportOrderIds(Set<UUID> transportOrderIds) {
        this.transportOrderIds = transportOrderIds;
    }
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    public String getSignMan() {
        return signMan;
    }

    public void setSignMan(String signMan) {
        this.signMan = signMan;
    }

    public Timestamp getSignDate() {
        return signDate;
    }

    public void setSignDate(Timestamp signDate) {
        this.signDate = signDate;
    }

    public String getSignManCard() {
        return signManCard;
    }

    public void setSignManCard(String signManCard) {
        this.signManCard = signManCard;
    }

    public String getAgentSignMan() {
        return agentSignMan;
    }

    public void setAgentSignMan(String agentSignMan) {
        this.agentSignMan = agentSignMan;
    }

    public String getAgentSignManCard() {
        return agentSignManCard;
    }

    public void setAgentSignManCard(String agentSignManCard) {
        this.agentSignManCard = agentSignManCard;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public UUID getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(UUID modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }
}
