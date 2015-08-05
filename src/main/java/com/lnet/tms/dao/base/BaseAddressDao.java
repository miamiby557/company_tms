package com.lnet.tms.dao.base;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.base.BaseAddress;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by Administrator on 2015/8/4.
 */
@Repository
public class BaseAddressDao extends CrudDao<BaseAddress,UUID> {
}
