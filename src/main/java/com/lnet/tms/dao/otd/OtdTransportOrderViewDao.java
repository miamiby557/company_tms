
package com.lnet.tms.dao.otd;

import com.lnet.tms.dao.BaseDao;
import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.model.otd.OtdTransportOrderDetail;
import com.lnet.tms.model.otd.OtdTransportOrderView;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OtdTransportOrderViewDao extends BaseDao<OtdTransportOrderView, UUID> {
}