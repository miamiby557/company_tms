
package com.lnet.tms.dao.otd;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.otd.OtdPickupOrder;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OtdPickupOrderDao extends CrudDao<OtdPickupOrder, UUID> {

}