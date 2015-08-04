package com.lnet.tms.model.crm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

/**
 * Created by admin on 2015/5/13.
 */
@Entity
@Table(name = "CRM_CLIENT_LINE", schema = "LNET_TMS", catalog = "")
public class CrmClientLine {
    private UUID clientLineId;
    private UUID clientId;
    private String startCity;
    private String destCity;
    private UUID startCityId;
    private UUID destCityId;
    private Integer transportType;
    private Double timeline;

    //private Set<CrmClientQuote> clientQuotes;

    @Id
    @Column(name = "CLIENT_LINE_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getClientLineId() {
        return clientLineId;
    }

    public void setClientLineId(UUID clientLineId) {
        this.clientLineId = clientLineId;
    }

    /*@OneToMany(mappedBy = "crmClientLine")
    @JsonIgnore
    public Set<CrmClientQuote> getClientQuotes() {
        return clientQuotes;
    }
    @JsonProperty
    public void setClientQuotes(Set<CrmClientQuote> clientQuotes) {
        this.clientQuotes = clientQuotes;
    }*/
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

    @Basic
    @Column(name = "CLIENT_ID", nullable = false, insertable = true, updatable = true)
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
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


}
