package com.lnet.tms.service.fee;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.dao.fee.FeeOrderPayableViewDao;
import com.lnet.tms.dao.fee.FeeOrderReceivableDao;
import com.lnet.tms.dao.otd.OtdCarrierOrderDetailDao;
import com.lnet.tms.dao.otd.OtdTransportOrderDao;
import com.lnet.tms.model.fee.FeeOrderPayableView;
import com.lnet.tms.model.fee.FeeOrderReceivable;
import com.lnet.tms.model.otd.OtdCarrierOrderDetail;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FeeOrderPayableViewService extends BaseService<FeeOrderPayableView,UUID,FeeOrderPayableViewDao>{

    @Autowired
    public void setBaseDao(FeeOrderPayableViewDao dao) {
        super.setDao(dao);
    }

}
