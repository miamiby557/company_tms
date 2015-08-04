package com.lnet.tms.model.fee;

import com.lnet.tms.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "FEE_ORDER_RECEIVABLE", schema = "LNET_TMS", catalog = "")
public class FeeOrderReceivable extends BaseEntity{
    private UUID orderReceivableId;
    private UUID clientId;
    private UUID sourceOrderId;
    private Integer sourceOrderType;
    private Double totalAmount;
    private Integer billingCycle;
    private Integer paymentType;
    private Integer calculateType;
   /* private UUID createUserId;
    private Timestamp createDate;
    private UUID modifyUserId;
    private Timestamp modifyDate;*/
    private Integer version;
    private Integer status;
    private Integer backStatus;
    private Boolean feeSaveMark;
    private Set<FeeOrderReceivableDetail> details = new HashSet<>();

    @Id
    @Column(name = "ORDER_RECEIVABLE_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getOrderReceivableId() {
        return orderReceivableId;
    }

    public void setOrderReceivableId(UUID orderReceivableId) {
        this.orderReceivableId = orderReceivableId;
    }
    @Basic
    @Column(name = "back_Status", nullable = true, insertable = true, updatable = true)
    public Integer getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(Integer backStatus) {
        this.backStatus = backStatus;
    }
    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CLIENT_ID", nullable = true, insertable = true, updatable = true)
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "SOURCE_ORDER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getSourceOrderId() {
        return sourceOrderId;
    }

    public void setSourceOrderId(UUID sourceOrderId) {
        this.sourceOrderId = sourceOrderId;
    }

    @Basic
    @Column(name = "SOURCE_ORDER_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSourceOrderType() {
        return sourceOrderType;
    }

    public void setSourceOrderType(Integer sourceOrderType) {
        this.sourceOrderType = sourceOrderType;
    }

    @Basic
    @Column(name = "TOTAL_AMOUNT", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "BILLING_CYCLE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(Integer billingCycle) {
        this.billingCycle = billingCycle;
    }

    @Basic
    @Column(name = "PAYMENT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "CALCULATE_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }

   /* @Basic
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
    }*/

    @OneToMany(mappedBy = "feeOrderReceivable", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FeeOrderReceivableDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<FeeOrderReceivableDetail> details) {
        this.details = details;
    }

    @Basic
    @Column(name = "VERSION", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "FEE_SAVE_MARK", nullable = true, insertable = true, updatable = true)
    public Boolean getFeeSaveMark() {
        return feeSaveMark;
    }

    public void setFeeSaveMark(Boolean feeSaveMark) {
        this.feeSaveMark = feeSaveMark;
    }
}
