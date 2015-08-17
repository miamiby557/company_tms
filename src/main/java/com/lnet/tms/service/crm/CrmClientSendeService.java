package com.lnet.tms.service.crm;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.base.BaseAddressDao;
import com.lnet.tms.dao.crm.CrmClientSendeDao;
import com.lnet.tms.dao.crm.CrmClientSenderViewDao;
import com.lnet.tms.model.base.BaseAddress;
import com.lnet.tms.model.crm.CrmClientSender;
import com.lnet.tms.model.crm.CrmClientSenderView;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.PrintRequestAttributeSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by develop on 2015/8/6.
 */

@Service
public class CrmClientSendeService extends CrudService<CrmClientSender,UUID,CrmClientSendeDao> {
    @Autowired
    public void setBaseDao(CrmClientSendeDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private BaseAddressDao baseAddressDao;

    @Autowired
    private CrmClientSenderViewDao crmClientSenderView;

    @Transactional
    public JsonResult createByAddressIds(List<UUID> ids,UUID clientId){
        List<CrmClientSender> model=getAll();
        boolean flag=true;
        if(ids!=null&&ids.size()>0){
            for(UUID addressId :ids){
                for(CrmClientSender sender:model){
                    if(sender.getClientId().equals(clientId) &&sender.getAddressId().equals(addressId)){
                        flag=false;
                        return JsonResult.error("地址已存在");
                    }
                }
                if(flag){
                    CrmClientSender clientSender = new CrmClientSender();
                    clientSender.setAddressId(addressId);
                    clientSender.setClientId(clientId);
                    getDao().create(clientSender);
                    return  JsonResult.success();
                }
                flag=true;
            }
        }
        return JsonResult.success();
    }

    @Override
    public DataSourceResult getDataSource(DataSourceRequest request) {

        return crmClientSenderView.getDataSource(request);
    }

}
