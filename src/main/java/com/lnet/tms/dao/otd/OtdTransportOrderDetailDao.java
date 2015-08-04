
package com.lnet.tms.dao.otd;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.otd.OtdTransportOrderDetail;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OtdTransportOrderDetailDao extends CrudDao<OtdTransportOrderDetail, UUID> {


}