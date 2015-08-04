package com.lnet.tms.web.fee;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.model.fee.FeeOrderPayableView;
import com.lnet.tms.model.otd.OtdCarrierOrderDetail;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.service.fee.FeeOrderPayableViewService;
import com.lnet.tms.service.fee.FeeOrderReceivableService;
import com.lnet.tms.service.otd.OtdCarrierOrderDetailService;
import com.lnet.tms.service.otd.OtdTransportOrderService;
import com.lnet.tms.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2015/5/19.`
 */
@Controller
@RequestMapping("/feeOrderPayableView")
public class FeeOrderPayableViewController extends BaseController<FeeOrderPayableView,UUID,FeeOrderPayableViewService>{

    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    public void setBaseService(FeeOrderPayableViewService service){
        super.setService(service);
    }

    @Autowired
    private OtdCarrierOrderDetailService otdCarrierOrderDetailService;

    @Autowired
    private OtdTransportOrderService otdTransportOrderDaoService;

    @Autowired
    private FeeOrderReceivableService feeOrderReceivableDaoService;

    @RequestMapping(value = "/projectAudit")
    @RequiresPermissions("FEEORDERPAYABLEPROJECTAUDIT_SELECT")
    public String projectAudit(){
        return "fee/FeeOrderPayable/projectAuditIndex";
    }

    @RequestMapping(value="/getProjectAuditDataSource",method = RequestMethod.POST)
    public @ResponseBody
    DataSourceResult getProjectAuditDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        request.getFilter().setLogic("and");

        if(filters!=null && filters.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:filters){
                if("status".equals(filter.getField())){
                    filter.setValue(Integer.parseInt(filter.getValue().toString()));
                }else if("sendDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse(filter.getValue().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        filters.add(new DataSourceRequest.FilterDescriptor("status", "gte",2));
        return this.operateDataSource(request);
    }

    @RequestMapping(value = "/financingAudit")
    @RequiresPermissions("FEEORDERPAYABLEFINANCINGAUDIT_SELECT")
    public String financingAudit(){
        return "fee/FeeOrderPayable/financingAuditIndex";
    }

    @RequestMapping(value="/getFinancingAuditDataSource",method = RequestMethod.POST)
    public @ResponseBody DataSourceResult getFinancingAuditDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor> filters=request.getFilter().getFilters();
        request.getFilter().setLogic("and");
        if(filters!=null && filters.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:filters){
                if("status".equals(filter.getField())){
                    filter.setValue(Integer.parseInt(filter.getValue().toString()));
                }else if("sendDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse(filter.getValue().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        filters.add(new DataSourceRequest.FilterDescriptor("status", "gte",3));
        return this.operateDataSource(request);
    }

    @Override
    public DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        List<DataSourceRequest.FilterDescriptor>filters=request.getFilter().getFilters();
        if(filters!=null && filters.size()>0){
            for(DataSourceRequest.FilterDescriptor filter:filters){
                if("status".equals(filter.getField())){
                    filter.setValue(Integer.parseInt(filter.getValue().toString()));
                }else if("sendDate".equals(filter.getField())){
                    try {
                        filter.setValue(sdf.parse(filter.getValue().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else if("feeSaveMark".equals(filter.getField())){
                    if ("1".equals(filter.getValue())){
                        filter.setValue(false);
                    }else if("0".equals(filter.getValue())){
                        filter.setValue(true);
                    }
                }
            }
        }
        return this.operateDataSource(request);
    }


    public DataSourceResult operateDataSource(DataSourceRequest request) {
        List<DataSourceRequest.SortDescriptor> sorts=request.getSort();
        if(sorts==null || sorts.size()==0){
            DataSourceRequest.SortDescriptor sort=new DataSourceRequest.SortDescriptor("status","asc");
            DataSourceRequest.SortDescriptor sort2=new DataSourceRequest.SortDescriptor("sendDate","desc");
            sorts=new ArrayList<>();
            sorts.add(sort);
            sorts.add(sort2);
            request.setSort(sorts);
        }
        DataSourceResult dataSourceResult=service.getDataSource(request);
        List<FeeOrderPayableView>feeOrderPayableViews= (List<FeeOrderPayableView>) dataSourceResult.getData();
        if(feeOrderPayableViews!=null){
            for(FeeOrderPayableView feeOrderPayableView:feeOrderPayableViews){
                //当应付对应的托运单里有合单的情况时，重新计算应收
                if(feeOrderPayableView.getMergeStatus()!=null&&feeOrderPayableView.getMergeStatus()==2){
                    double receivTotalAmount=feeOrderPayableView.getReceivTotalAmount()==null?0.0:feeOrderPayableView.getReceivTotalAmount();
                    UUID carrierOrderId=feeOrderPayableView.getSourceOrderId();
                    List<OtdCarrierOrderDetail>details=otdCarrierOrderDetailService.getAllByField("carrierOrderId",carrierOrderId);
                    Set<UUID> mergeTransportOrderIdSet=new HashSet<>();
                    if(details!=null){
                        for(OtdCarrierOrderDetail detail:details){
                            int mergerStatus=otdTransportOrderDaoService.get(detail.getTransportOrderId()).getMergeStatus();
                            if(mergerStatus==2){
                                mergeTransportOrderIdSet.add(detail.getTransportOrderId());
                            }
                        }
                    }
                    Set<UUID>afterMergeTransportOrderIdSet=new HashSet<>();
                    if(mergeTransportOrderIdSet.size()>0){
                        for(UUID transportOrderId:mergeTransportOrderIdSet){
                            UUID mergerTransportOrderId=otdTransportOrderDaoService.get(transportOrderId).getMergeTransportOrderId();
                            afterMergeTransportOrderIdSet.add(mergerTransportOrderId);
                        }
                    }
                    if(afterMergeTransportOrderIdSet.size()>0){
                        for(UUID afterMergeTransportOrderId:afterMergeTransportOrderIdSet){
                            //将对应所有的合单的应收计算到总应收里
                            Double totalAmount=feeOrderReceivableDaoService.getByField("sourceOrderId",afterMergeTransportOrderId).getTotalAmount();
                            receivTotalAmount+=totalAmount;
                            OtdTransportOrder mergerOrder=otdTransportOrderDaoService.get(afterMergeTransportOrderId);
                            List<OtdTransportOrder>otdTransportOrders=otdTransportOrderDaoService.getAllByField("mergeTransportOrderId",afterMergeTransportOrderId);
                            for(OtdTransportOrder otdTransportOrder:otdTransportOrders){
                                if(!mergeTransportOrderIdSet.contains(otdTransportOrder.getTransportOrderId())){
                                    //按百分比减去不在当前托运单里的应收
                                    int calculateType=mergerOrder.getCalculateType();
                                    double percentage=0.0;
                                    switch (calculateType) {
                                        case 1:
                                            percentage = otdTransportOrder.getConfirmedWeight()/mergerOrder.getConfirmedWeight();
                                            break;
                                        case 2:
                                            percentage = otdTransportOrder.getConfirmedPackageQuantity()/mergerOrder.getConfirmedPackageQuantity();
                                            break;
                                        case 3:
                                            percentage = otdTransportOrder.getConfirmedVolume()/mergerOrder.getConfirmedVolume();
                                            break;
                                    }
                                    receivTotalAmount=receivTotalAmount-totalAmount*percentage;
                                }
                            }
                        }
                    }
                    feeOrderPayableView.setReceivTotalAmount(receivTotalAmount);
                }
            }
        }
        dataSourceResult.setData(feeOrderPayableViews);
        return dataSourceResult;
    }
}
