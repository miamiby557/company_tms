package com.lnet.tms.service.crm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.base.BaseAddressDao;
import com.lnet.tms.dao.base.CrmReceiverAddressViewDao;
import com.lnet.tms.dao.crm.CrmClientReceiverDao;
import com.lnet.tms.model.crm.CrmClientReceiver;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.base.CrmReceiverAddressViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/8/5.
 */
@Service
public class CrmClientReceiverService extends CrudService<CrmClientReceiver,UUID,CrmClientReceiverDao> {
    @Autowired
    public void setBaseDao(CrmClientReceiverDao dao) {
        super.setDao(dao);
    }
    @Autowired
    private BaseAddressDao baseAddressDao;
    @Autowired
    private CrmReceiverAddressViewDao crmReceiverAddressViewDao;
    @Transactional
    public JsonResult createByAddressIds(List<UUID> ids, UUID clientId) {
        List<CrmClientReceiver> model = getAll();
        boolean falg = true;
        if (ids != null && ids.size() > 0) {
            for (UUID addressId : ids) {
                for (CrmClientReceiver receiver : model) {
                    if (receiver.getClientId().equals(clientId) && receiver.getAddressId().equals(addressId)) {
                        falg = false;
                        return JsonResult.error("该数据已存在,请勿重复选择");
                    }
                }
                if (falg) {
                    CrmClientReceiver crmClientReceiver = new CrmClientReceiver();
                    crmClientReceiver.setAddressId(addressId);
                    crmClientReceiver.setClientId(clientId);
                    getDao().create(crmClientReceiver);
                    return JsonResult.success();
                }
            }
            falg=true;
        }
        return JsonResult.success();
    }
    @Override
    public DataSourceResult getDataSource(DataSourceRequest request) {
        return crmReceiverAddressViewDao.getDataSource(request);
    }


}
