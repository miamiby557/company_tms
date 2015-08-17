package com.lnet.tms.service.base;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.base.BaseAddressDao;
import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.dao.base.CrmReceiverAddressViewDao;
import com.lnet.tms.model.base.BaseAddress;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.utility.GetPinyinUtil;
import com.lnet.tms.utility.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Administrator on 2015/8/4.
 */
@Service
public class BaseAddressService extends CrudService<BaseAddress, UUID, BaseAddressDao> {
    @Autowired
    public void setBaseDao(BaseAddressDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private BaseRegionDao baseRegionDao;

    @Autowired
    private CrmReceiverAddressViewDao crmReceiverAddressViewDao;

    @Override
    public UUID create(BaseAddress model) {
        /*if (!StringUtils.isEmpty(model.getRegion())){
         model.setRegion(baseRegionDao.get(model.getRegion()).getName());
        }*/
        return super.create(model);
    }

    @Override
    public DataSourceResult getDataSource(DataSourceRequest request) {
        return crmReceiverAddressViewDao.getDataSource(request);
    }
}
