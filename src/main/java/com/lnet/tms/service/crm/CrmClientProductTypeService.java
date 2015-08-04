package com.lnet.tms.service.crm;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.crm.CrmClientProductTypeDao;
import com.lnet.tms.model.crm.CrmClientProductType;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CrmClientProductTypeService extends CrudService<CrmClientProductType, UUID, CrmClientProductTypeDao> {

    @Autowired
    public void setBaseDao(CrmClientProductTypeDao dao) {
        super.setDao(dao);
    }

    @Transactional
    public JsonResult CrmClientProductType(CrmClientProductType crmClientProductType){
        if(getDao().codeIsExist(crmClientProductType.getClientId(),crmClientProductType.getValue(),crmClientProductType.getClientProductTypeId())){
            return JsonResult.error("编码已存在！");
        }
        getDao().create(crmClientProductType);
        return JsonResult.success();
    }

    @Transactional
    public JsonResult updateProductType(CrmClientProductType crmClientProductType){
        if(getDao().codeIsExist(crmClientProductType.getClientId(),crmClientProductType.getValue(),crmClientProductType.getClientProductTypeId())){
            return JsonResult.error("编码已存在！");
        }
        getDao().update(crmClientProductType);
        return JsonResult.success();
    }
}
