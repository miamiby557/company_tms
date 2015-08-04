package com.lnet.tms.dao.scm;

import com.lnet.tms.dao.BaseDao;
import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.model.scm.ScmCarrierView;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ScmCarrierViewDao extends BaseDao<ScmCarrierView,UUID> {
}
