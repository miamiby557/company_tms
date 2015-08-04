package com.lnet.tms.dao.fee;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.fee.FeeOrderPayable;
import com.lnet.tms.model.fee.FeeOrderPayableDetail;
import com.lnet.tms.model.fee.FeeOrderPayableProportion;
import com.lnet.tms.model.fee.FeePayableProportionView;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FeeOrderPayableDao extends CrudDao<FeeOrderPayable,UUID>{
    @Override
    public UUID create(FeeOrderPayable model) {
        for(FeeOrderPayableDetail detail:model.getDetails()){
            detail.setFeeOrderPayable(model);
        }
        for(FeeOrderPayableProportion detail:model.getProportionDetails()){
            detail.setFeeOrderPayable(model);
        }
        return super.create(model);
    }


    @Override
    public void update(FeeOrderPayable model) {
        FeeOrderPayable target = load(model.getOrderPayableId());
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setModifyDate(DateUtils.getTimestampNow());
        target.getDetails().clear();
        target.getProportionDetails().clear();
        for (FeeOrderPayableDetail item : model.getDetails()) {
            item.setFeeOrderPayable(model);
        }
        for(FeeOrderPayableProportion detail:model.getProportionDetails()){
            detail.setFeeOrderPayable(model);
        }
        super.merge(model);
    }
}
