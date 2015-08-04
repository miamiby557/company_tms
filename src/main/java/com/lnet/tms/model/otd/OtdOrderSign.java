package com.lnet.tms.model.otd;

import com.lnet.tms.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/13.
 */
@Entity
@Table(name = "OTD_ORDER_SIGN", schema = "LNET_TMS", catalog = "")
public class OtdOrderSign{
    private UUID transportOrderId;
    private String signMan;
    private Timestamp signDate;
    private String signManCard;
    private String agentSignMan;
    private String agentSignManCard;
    private String remark;
    private Boolean isAbnormal;

    private UUID createUserId;
    private Timestamp createDate;
    private UUID modifyUserId;
    private Timestamp modifyDate;
    @Basic
    @Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "MODIFY_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(UUID modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "IS_ABNORMAL", nullable = true, insertable = true, updatable = true)
    public Boolean getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Boolean isAbnormal) {
        this.isAbnormal = isAbnormal;
    }
    @Id
    @Column(name = "TRANSPORT_ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getTransportOrderId() {
        return transportOrderId;
    }

    public void setTransportOrderId(UUID transportOrderId) {
        this.transportOrderId = transportOrderId;
    }

    @Basic
    @Column(name = "SIGN_MAN", nullable = true, insertable = true, updatable = true)
    public String getSignMan() {
        return signMan;
    }

    public void setSignMan(String signMan) {
        this.signMan = signMan;
    }

    @Basic
    @Column(name = "SIGN_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getSignDate() {
        return signDate;
    }

    public void setSignDate(Timestamp signDate) {
        this.signDate = signDate;
    }

    @Basic
    @Column(name = "SIGN_MAN_CARD", nullable = true, insertable = true, updatable = true)
    public String getSignManCard() {
        return signManCard;
    }

    public void setSignManCard(String signManCard) {
        this.signManCard = signManCard;
    }

    @Basic
    @Column(name = "AGENT_SIGN_MAN", nullable = true, insertable = true, updatable = true)
    public String getAgentSignMan() {
        return agentSignMan;
    }

    public void setAgentSignMan(String agentSignMan) {
        this.agentSignMan = agentSignMan;
    }

    @Basic
    @Column(name = "AGENT_SIGN_MAN_CARD", nullable = true, insertable = true, updatable = true)
    public String getAgentSignManCard() {
        return agentSignManCard;
    }

    public void setAgentSignManCard(String agentSignManCard) {
        this.agentSignManCard = agentSignManCard;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
