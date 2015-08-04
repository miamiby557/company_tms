package com.lnet.tms.model.scm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by admin on 2015/5/13.
 */
@Entity
@Table(name = "SCM_CARRIER_LINE", schema = "LNET_TMS", catalog = "")
public class ScmCarrierLine {
    private UUID carrierLineId;
    private UUID carrierId;
    private String startCity;
    private String destCity;
    private UUID startCityId;
    private UUID destCityId;
    private Double timeline;
    private Integer transportType;


    @Id
    @Column(name = "CARRIER_LINE_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getCarrierLineId() {
        return carrierLineId;
    }

    public void setCarrierLineId(UUID carrierLineId) {
        this.carrierLineId = carrierLineId;
    }

    @Basic
    @Column(name = "CARRIER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(UUID carrierId) {
        this.carrierId = carrierId;
    }

    @Basic
    @Column(name = "START_CITY", nullable = true, insertable = true, updatable = true)
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    @Basic
    @Column(name = "DEST_CITY", nullable = true, insertable = true, updatable = true)
    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    @Basic
    @Column(name = "START_CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getStartCityId() {
        return startCityId;
    }

    public void setStartCityId(UUID startCityId) {
        this.startCityId = startCityId;
    }

    @Basic
    @Column(name = "DEST_CITY_ID", nullable = true, insertable = true, updatable = true)
    public UUID getDestCityId() {
        return destCityId;
    }

    public void setDestCityId(UUID destCityId) {
        this.destCityId = destCityId;
    }

    @Basic
    @Column(name = "TRANSPORT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    @Basic
    @Column(name = "TIMELINE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTimeline() {
        return timeline;
    }

    public void setTimeline(Double timeline) {
        this.timeline = timeline;
    }


}
