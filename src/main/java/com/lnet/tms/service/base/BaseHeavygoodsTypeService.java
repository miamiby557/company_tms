package com.lnet.tms.service.base;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.base.BaseHeavygoodsTypeDao;
import com.lnet.tms.model.base.BaseHeavygoodsType;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.utility.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BaseHeavygoodsTypeService extends CrudService<BaseHeavygoodsType, UUID, BaseHeavygoodsTypeDao> {

    @Autowired
    public void setBaseDao(BaseHeavygoodsTypeDao dao) {
        super.setDao(dao);
    }

    @Transactional
    public JsonResult createType(BaseHeavygoodsType model){
        if( model.getRatioMax()!=null && model.getRatioMin().compareTo(model.getRatioMax())>=0){
            return JsonResult.error("最大比值须大于最小比值！");
        }
        List<BaseHeavygoodsType>types=this.getExistTypeList(model);
        if(this.isRange(model,types)){
            return JsonResult.error("区间已存在！");
        }
        getDao().create(model);
        return JsonResult.success();
    }

    @Transactional
    public JsonResult updateType(BaseHeavygoodsType model){
        if(model.getRatioMax()!=null && model.getRatioMin().compareTo(model.getRatioMax())>=0){
            return JsonResult.error("最大比值须大于最小比值！");
        }
        List<BaseHeavygoodsType>types=this.getExistTypeList(model);
        if(this.isRange(model,types)){
            return JsonResult.error("区间已存在！");
        }
        getDao().update(model);
        return JsonResult.success();
    }

    @Transactional
    public DataSourceResult getDataSource(DataSourceRequest request,UUID clientId) {
        return getDao().getDataSource(request, clientId);
    }
    @Transactional
    public List<BaseHeavygoodsType> getExistTypeList(BaseHeavygoodsType model){
        List<BaseHeavygoodsType>types=getDao().getListByClientAndTypeId(model.getClientId(),model.getHeavygoodsTypeId());
        if(model.getClientId()!=null){
            return types;
        }else{
            List<BaseHeavygoodsType>types1=new ArrayList<>();
            if(types!=null && types.size()>0){
                for(BaseHeavygoodsType type:types){
                    if(StringUtils.isEmpty(type.getClientId())){
                        types1.add(type);
                    }
                }
            }
            return types1;
        }
    }

    @Transactional
    public boolean isRange(BaseHeavygoodsType baseHeavygoodsType,List<BaseHeavygoodsType> types){
        boolean flag=false;
        if(types!=null && types.size()>0){
            Double min=baseHeavygoodsType.getRatioMin();
            Double max=baseHeavygoodsType.getRatioMax();
            for(BaseHeavygoodsType type:types){
                Double typeMin=type.getRatioMin();
                Double typeMax=type.getRatioMax();
                if(max==null){
                    if(typeMax==null){
                        flag= true;
                    }else{
                        flag=(min<typeMax);
                    }
                }else{
                    if (typeMax == null){
                        flag=(max>typeMin);
                    }else{
                        if(max>typeMax){
                            flag=( min<typeMax);
                        }else if(max<typeMax){
                            flag=(max>typeMin);
                        }else{
                            flag= true;
                        }
                    }
                }
                if(flag){
                    return true;
                }
            }
        }
        return flag;
    }
}
