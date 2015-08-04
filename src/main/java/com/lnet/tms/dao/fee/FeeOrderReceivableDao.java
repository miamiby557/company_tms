package com.lnet.tms.dao.fee;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.fee.FeeOrderReceivable;
import com.lnet.tms.model.fee.FeeOrderReceivableDetail;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FeeOrderReceivableDao extends CrudDao<FeeOrderReceivable,UUID>{
    @Override
    public UUID create(FeeOrderReceivable model) {
        for(FeeOrderReceivableDetail detail:model.getDetails()){
            detail.setFeeOrderReceivable(model);
        }
        return super.create(model);
    }

    @Override
    public void update(FeeOrderReceivable model) {
        for(FeeOrderReceivableDetail detail:model.getDetails()){
            detail.setFeeOrderReceivable(model);
        }
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setModifyDate(DateUtils.getTimestampNow());
        super.merge(model);
    }
}
