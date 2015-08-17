package com.lnet.tms.dao.crm;

import com.lnet.tms.dao.BaseDao;
import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.base.BaseAddress;
import com.lnet.tms.model.crm.CrmClientSender;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by develop on 2015/8/6.
 */
@Repository
public class CrmClientSendeDao extends CrudDao<CrmClientSender, UUID> {

}
