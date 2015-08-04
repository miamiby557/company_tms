package com.lnet.tms.service.crm;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.crm.CrmClientOrderTypeDao;
import com.lnet.tms.model.crm.CrmClientOrderType;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CrmClientOrderTypeService extends CrudService<CrmClientOrderType, UUID, CrmClientOrderTypeDao> {

    @Autowired
    public void setBaseDao(CrmClientOrderTypeDao dao) {
        super.setDao(dao);
    }
    @Transactional
    public JsonResult CrmClientOrderType(CrmClientOrderType crmClientOrderType){
        if (getDao().codeIsExist(crmClientOrderType.getClientId(),crmClientOrderType.getClientOrderTypeId(),crmClientOrderType.getValue())){
            return  JsonResult.error("编码已存在！");
        }
        getDao().create(crmClientOrderType);
        return  JsonResult.success();
      }
    @Transactional
    public JsonResult updateOrderType(CrmClientOrderType crmClientOrderType){
        if(getDao().codeIsExist(crmClientOrderType.getClientId(),crmClientOrderType.getClientOrderTypeId(),crmClientOrderType.getValue())){
            return  JsonResult.error("编码已存在！ ");
        }
        getDao().update(crmClientOrderType);
        return JsonResult.success();
    }
}
