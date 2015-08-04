package com.lnet.tms.model.dispatch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "DISPATCH_ASSIGN_DETAIL", schema = "LNET_TMS", catalog = "")
public class DispatchAssignDetail {
    private UUID dispatchAssignDetailId;
    private UUID dispatchAssignId;
    private UUID orderId;
    private DispatchAssign dispatchAssign;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "DISPATCH_ASSIGN_ID", nullable = false)
    @JsonIgnore
    public DispatchAssign getDispatchAssign() {
        return dispatchAssign;
    }
    @JsonProperty
    public void setDispatchAssign(DispatchAssign dispatchAssign) {
        this.dispatchAssign = dispatchAssign;
    }

    @Id
    @Column(name = "DISPATCH_ASSIGN_DETAIL_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getDispatchAssignDetailId() {
        return dispatchAssignDetailId;
    }

    public void setDispatchAssignDetailId(UUID dispatchAssignDetailId) {
        this.dispatchAssignDetailId = dispatchAssignDetailId;
    }
    @Basic
    @Column(name = "DISPATCH_ASSIGN_ID", nullable = false, insertable = false, updatable = false)
    public UUID getDispatchAssignId() {
        return dispatchAssignId;
    }

    public void setDispatchAssignId(UUID dispatchAssignId) {
        this.dispatchAssignId = dispatchAssignId;
    }

    @Basic
    @Column(name = "ORDER_ID", nullable = false, insertable = true, updatable = true)
    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}
