package com.lnet.tms.service.otd;

import com.lnet.tms.dao.otd.OtdTransportOrderViewDao;
import com.lnet.tms.model.otd.OtdTransportOrderView;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.UUID;

@Service
public class OtdTransportOrderViewService extends BaseService<OtdTransportOrderView, UUID, OtdTransportOrderViewDao> {
    @Autowired
    public void setBaseDao(OtdTransportOrderViewDao dao) {
        super.setDao(dao);
    }


    @Transactional
    public     ModelMap getCount(){
        ModelMap map = new ModelMap();
        List<OtdTransportOrderView> list = getDao().getAll();
        Integer sum = 0;
        Integer count1=0;//未回
        Integer count2=0;//已回待补
        Integer count3=0;//已回
        Integer RepairCount = 0;
        if(list!=null&&list.size()>0){
            for(OtdTransportOrderView order :list){
                if(order.getStatus()!=-1){
                    sum++;
                    switch (order.getReceiptStatus()){
                        case 1:{
                            count1++;
                            break;
                        } case 2:{
                            count2++;
                            break;
                        } case 3:{
                            count3++;
                            break;
                        }
                    }
                    if (order.getIsRepair()!=null&&order.getIsRepair()==1){
                        RepairCount++;
                    }
                }
            }
        }
        map.put("sum",sum);
        map.put("count1",count1);
        map.put("count2",count2);
        map.put("count3",count3);
        map.put("RepairCount",RepairCount);
        return map;
    }

}
