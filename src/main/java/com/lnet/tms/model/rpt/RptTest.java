package com.lnet.tms.model.rpt;

import javax.persistence.*;

/**
 * Created by admin on 2015/8/10.
 */
@Entity
@Table(name = "RPT_TEST", schema = "LNET_TMS", catalog = "")
public class RptTest {
    private String clientName;

    @Id
    @Column(name = "CLIENT_NAME")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
