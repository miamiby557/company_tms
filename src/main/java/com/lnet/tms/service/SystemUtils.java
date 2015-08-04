package com.lnet.tms.service;

import com.lnet.tms.model.sys.SysFunction;
import com.lnet.tms.model.sys.SysRole;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.sys.SysFunctionService;
import com.lnet.tms.service.sys.SysRoleService;
import com.lnet.tms.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/7/4.
 */
@Service
public class SystemUtils {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysFunctionService functionService;

    @Autowired
    private SysRoleService roleService;
    @Transactional
    public  void initFunctionAndRole(){
        //提货单
        SysFunction pickupOrderCreate = new SysFunction("PICKUPORDER_CREATE","提货单_开单",true);
        SysFunction pickupOrderConfirm = new SysFunction("PICKUPORDER_CONFIRM","提货单_审单",true);
        SysFunction pickupOrder = new SysFunction("PICKUPORDER","提货单_发车",true);
        //运输单
        SysFunction transportOrderCreate = new SysFunction("TRANSPORTORDER_CREATE","运输单_开单",true);
        SysFunction transportOrderConfirm = new SysFunction("TRANSPORTORDER_CONFIRM","运输单_审单",true);
        SysFunction transportOrderMerge=new SysFunction("TRANSPORTORDER_MERGE","运输单_合单",true);
        SysFunction transportOrderModify=new SysFunction("TRANSPORTORDER_MODIFY","运输单_修改",true);
        SysFunction transportOrderImportOrder=new SysFunction("TRANSPORTORDER_IMPORTORDER","运输单_导入",true);
        SysFunction transprotOrderCancel=new SysFunction("TRANSPORTORDER_CANCEL","运输单_取消",true);
        SysFunction transportOrderExportExcel=new SysFunction("TRANSPORTORDER_EXPORTEXCEL","运输单_导出EXCEL",true);
        SysFunction transprotOrderSign=new SysFunction("TRANSPORTORDER_SIGN","运输单_签收",true);
        SysFunction transprotOrderSelect=new SysFunction("TRANSPORTORDER_SELECT","运输单_查询",true);

        //调度
        SysFunction carrierOrderCreate = new SysFunction("CARRIERORDER_CREATE","托运单_开单",true);
        SysFunction carrierOrderSend = new SysFunction("CARRIERORDER_SEND","托运单_发运",true);
        SysFunction carrierOrderModify = new SysFunction("CARRIERORDER_MODIFY","托运单_修改",true);
        SysFunction carrierOrderExportExcel = new SysFunction("CARRIERORDER_EXPORTEXCEL","托运单_导出EXCEL",true);
        SysFunction carrierOrderCancel = new SysFunction("CARRIERORDER_CANCEL","托运单_取消",true);
        SysFunction carrierOrderOk = new SysFunction("CARRIERORDER_OK","托运单_完成",true);
        SysFunction carrierOrderSelect= new SysFunction("CARRIERORDER_SELECT","托运单_查询",true);
        //信息跟踪
        SysFunction orderLogMassageInput = new SysFunction("ORDERLOG_MESSAGEINPUT","信息跟踪_运输订单信息录入",true);
        SysFunction orderLogCarrierInput= new SysFunction("ORDERLOG_CARRIERINPUT","信息跟踪_托运单信息录入",true);
        SysFunction orderLogConfirm = new SysFunction("ORDERLOG_CONFIRM","信息跟踪_签收",true);
        SysFunction orderLogArrive = new SysFunction("ORDERLOG_ARRIVE","信息跟踪_到达",true);
        SysFunction orderLogOrderExportExcel=new SysFunction("ORDERLOG_EXPORTEXCEL","信息跟踪_导出EXCEL",true);
        SysFunction orderLogOrderSelect=new SysFunction("ORDERLOG_SELECT","信息跟踪_查询",true);

        //费用录入
        SysFunction feeOrderReceivableInput = new SysFunction("FEEORDER_RECEIVABLEINPUT","费用录入_应收录入",true);
        SysFunction feeOrderPayableInput = new SysFunction("FEEORDER_PAYABLEINPUT","费用录入_应付录入",true);
        //费用审核
        SysFunction feeAuditReceivable = new SysFunction("FEEORDER_RECEIVABLE","费用审核_应收",true);
        SysFunction feeAuditReceivableModify=new SysFunction("FEEORDERRECEIVABLE_MODIFY","应收确认_确认",true);
        SysFunction feeAuditReceivableUpdate=new SysFunction("FEEORDERRECEIVABLE_UPDATE","应收确认_修改",true);
        SysFunction feeAuditReceivableExportExcel=new SysFunction("FEEORDERRECEIVABLE_EXPORTEXCEL","应收确认_导出EXCEL",true);
        SysFunction feeAuditReceivableSelect=new SysFunction("FEEORDERRECEIVABLE_SELECT","应收确认_查询",true);

        SysFunction feeAuditPayable = new SysFunction("FEEORDER_PAYABLE","费用审核_应付",true);
        SysFunction feeAuditPayableModify=new SysFunction("FEEORDERPAYABLE_MODIFY","应付确认_确认",true);
        SysFunction feeAuditPayableUpdate=new SysFunction("FEEORDERPAYABLE_UPDATE","应付确认_修改",true);
        SysFunction feeAuditPayableExportExcel=new SysFunction("FEEORDERPAYABLE_EXPORTEXCEL","应付确认_导出EXCEL",true);
        SysFunction feeAuditPayableSelect=new SysFunction("FEEORDERPAYABLE_SELECT","应付确认_查询",true);

        SysFunction feeAuditReceivableProjectAudit = new SysFunction("FEEORDER_RECEIVABLEPROJECTAUDIT","费用审核_应收客服经理审核",true);
        SysFunction feeAuditReceivableProjectAuditPass=new SysFunction("FEEORDERRECEIVABLEPROJECTAUDIT_PASS","应收客服经理审核_确认",true);
        SysFunction feeAuditReceivableProjectAuditUpdate=new SysFunction("FEEORDERRECEIVABLEPROJECTAUDIT_UPDATE","应收客服经理审核_修改",true);
        SysFunction feeAuditReceivableProjectAuditExportExcel=new SysFunction("FEEORDERRECEIVABLEPROJECTAUDIT_EXPORTEXCEL","应收客服经理审核_导出EXCEL",true);
        SysFunction feeAuditReceivableProjectAuditSelect=new SysFunction("FEEORDERRECEIVABLEPROJECTAUDIT_SELECT","应收客服经理审核_查询",true);

        SysFunction feeAuditPayableProjectAudit = new SysFunction("FEEORDER_PAYABLEPROJECTAUDIT","费用审核_应付客服经理审核",true);
        SysFunction feeAuditPayableProjectAuditPass=new SysFunction("FEEORDERPAYABLEPROJECTAUDIT_PASS","应付客服经理审核_确认",true);
        SysFunction feeAuditPayableProjectAuditUpdate=new SysFunction("FEEORDERPAYABLEPROJECTAUDIT_UPDATE","应付客服经理审核_修改",true);
        SysFunction feeAuditPayableProjectAuditExportExcel=new SysFunction("FEEORDERPAYABLEPROJECTAUDIT_EXPORTEXCEL","应付客服经理审核_导出EXCEL",true);
        SysFunction feeAuditPayableProjectAuditSelect=new SysFunction("FEEORDERPAYABLEPROJECTAUDIT_SELECT","应付客服经理审核_查询",true);

        SysFunction feeAuditReceivableFinancingAudit = new SysFunction("FEEORDER_RECEIVABLEFINANCINGAUDIT","费用审核_应收财务审核",true);
        SysFunction feeAuditReceivableFinancingAuditPass=new SysFunction("FEEORDERRECEIVABLEFINANCINGAUDIT_PASS","应收财务审核_确认",true);
        SysFunction feeAuditReceivableFinancingAuditUpdate=new SysFunction("FEEORDERRECEIVABLEFINANCINGAUDIT_UPDATE","应收财务审核_修改",true);
        SysFunction feeAuditReceivableFinancingAuditFinish=new SysFunction("FEEORDERRECEIVABLEFINANCINGAUDIT_FINISH","应收财务审核_完结",true);
        SysFunction feeAuditReceivableFinancingAuditExportExcel=new SysFunction("FEEORDERRECEIVABLEFINANCINGAUDIT_EXPORTEXCEL","应收财务审核_导出EXCEL",true);
        SysFunction feeAuditReceivableFinancingAuditSelect=new SysFunction("FEEORDERRECEIVABLEFINANCINGAUDIT_SELECT","应收财务审核_查询",true);

        SysFunction feeAuditPayableFinancingAudit = new SysFunction("FEEORDER_PAYABLEFINANCINGAUDIT","费用审核_应付财务审核",true);
        SysFunction feeAuditPayableFinancingAuditPass=new SysFunction("FEEORDERPAYABLEFINANCINGAUDIT_PASS","应付财务审核_确认",true);
        SysFunction feeAuditPayableFinancingAuditUpdate=new SysFunction("FEEORDERPAYABLEFINANCINGAUDIT_UPDATE","应付财务审核_修改",true);
        SysFunction feeAuditPayableFinancingAuditFinish=new SysFunction("FEEORDERPAYABLEFINANCINGAUDIT_FINISH","应付财务审核_完结",true);
        SysFunction feeAuditPayableFinancingAuditExportExcel=new SysFunction("FEEORDERPAYABLEFINANCINGAUDIT_EXPORTEXCEL","应付财务审核_导出EXCEL",true);
        SysFunction feeAuditPayableFinancingAuditSelect=new SysFunction("FEEORDERPAYABLEFINANCINGAUDIT_SELECT","应付财务审核_查询",true);
        //回单管理
        SysFunction orderBackManage = new SysFunction("ORDERBACK_MANAGE","回单管理",true);
        SysFunction orderBackReceipt = new SysFunction("ORDERBACK_RECEIPT","回单管理_已回",true);
        SysFunction orderBackRepair = new SysFunction("ORDERBACK_REPAIR","回单管理_待补签",true);
        SysFunction orderBackSelect = new SysFunction("ORDERBACK_SELECT","回单管理_查询",true);

        //客户
//        SysFunction crmOrderClient=new SysFunction("CRMORDER_Client","客户",true);
        SysFunction crmOrderCreate=new SysFunction("CRMORDER_CREATE","客户_添加",true);
        SysFunction crmOrderRemove=new SysFunction("CRMORDER_REMOVE","客户_删除",true);
        SysFunction crmOrderModify=new SysFunction("CRMORDER_MODIFY","客户_修改",true);
        SysFunction crmOrderImportAttachment=new SysFunction("CRMORDER_IMPORTATTACHMENT","客户_导入附件",true);
        SysFunction crmOrderImportQuote=new SysFunction("CRMORDER_IMPORTQUOTE","客户_导入报价",true);
        SysFunction crmOrderSelect=new SysFunction("CRMORDER_SELECT","客户_查询",true);
        //承运商
//        SysFunction scmOrderCarrier=new SysFunction("SCMORDER_Carrier","承运商",true);
        SysFunction scmOrderCreate=new SysFunction("SCMORDER_CREATE","承运商_添加",true);
        SysFunction scmOrderRemove=new SysFunction("SCMORDER_REMOVE","承运商_删除",true);
        SysFunction scmOrderModify=new SysFunction("SCMORDER_MODIFY","承运商_修改",true);
        SysFunction scmOrderImportAttachment=new SysFunction("SCMORDER_IMPORTATTACHMENT","承运商_导入附件",true);
        SysFunction scmOrderImportQuote=new SysFunction("SCMORDER_IMPORTQUOTE","承运商_导入报价",true);
        SysFunction scmOrderSelect=new SysFunction("SCMORDER_SELECT","承运商_查询",true);

        //报表
        SysFunction orderTransportExportExcel=new SysFunction("ORDERTRANSPORT_EXPORTEXCEL","运作报表_导出EXCEL",true);
        SysFunction orderTransportSelect=new SysFunction("ORDERTRANSPORT_SELECT","运作报表_查询",true);

        SysFunction orderMonthsumExportExcel=new SysFunction("ORDERMONTHSUM_EXPORTEXCEL","月单量统计_导出EXCEL",true);
        SysFunction orderMonthsumSelect=new SysFunction("ORDERMONTHSUM_SELECT","月单量统计_查询",true);

        SysFunction orderMonthsumCrossExportExcel=new SysFunction("ORDERTRANSPORTCROSS_EXPORTEXCEL","月毛利统计_导出EXCEL",true);
        SysFunction orderMonthsumCrossSelect=new SysFunction("ORDERTRANSPORTCROSS_SELECT","月毛利统计_查询",true);

        SysFunction orderCheckIncomeExportExcel=new SysFunction("ORDERCHECKINCOME_EXPORTEXCEL","应收对账单_导出EXCEL",true);
        SysFunction orderCheckIncomeSelect=new SysFunction("ORDERCHECKINCOME_SELECT","应收对账单_查询",true);

        SysFunction orderCheckPayableExportExcel=new SysFunction("ORDERCHECKPAYABLE_EXPORTEXCEL","应付对账单_导出EXCEL",true);
        SysFunction orderCheckPayableSelect=new SysFunction("ORDERCHECKPAYABLE_SELECT","应付对账单_查询",true);

        SysFunction orderBaseRegionSelect=new SysFunction("ORDERBASEREGION_SELECT","地址区域_查询",true);

        SysFunction orderBaseExacctSelect=new SysFunction("ORDERBASEEXACCT_SELECT","费用科目_查询",true);
        SysFunction orderBaseExacctCreate=new SysFunction("ORDERBASEEXACCT_CREATE","费用科目_添加",true);
        SysFunction orderBaseExacctRemove=new SysFunction("ORDERBASEEXACCT_REMOVE","费用科目_删除",true);
        SysFunction orderBaseExacctModify=new SysFunction("ORDERBASEEXACCT_MODIFY","费用科目_修改",true);
        SysFunction orderBaseExacctExportExcel=new SysFunction("ORDERBASEEXACCT_EXPORTEXCEL","费用科目_导出EXCEL",true);








        Set<SysFunction> allFunctions = new HashSet<>();
        allFunctions.add(pickupOrderCreate);
        allFunctions.add(pickupOrderConfirm);
        allFunctions.add(pickupOrder);
        allFunctions.add(transportOrderCreate);
        allFunctions.add(transportOrderConfirm);
        allFunctions.add(carrierOrderCreate);
        allFunctions.add(carrierOrderSend);
        allFunctions.add(orderLogMassageInput);
        allFunctions.add(orderLogConfirm);
        allFunctions.add(feeOrderReceivableInput);
        allFunctions.add(feeOrderPayableInput);
        allFunctions.add(feeAuditReceivable);
        allFunctions.add(feeAuditPayable);
        allFunctions.add(orderBackManage);

        allFunctions.add(transportOrderMerge);
        allFunctions.add(transportOrderModify);
        allFunctions.add(transportOrderImportOrder);
        allFunctions.add(transprotOrderCancel);
        allFunctions.add(transportOrderExportExcel);
        allFunctions.add(transprotOrderSign);
        allFunctions.add(carrierOrderModify);
        allFunctions.add(carrierOrderExportExcel);
        allFunctions.add(carrierOrderCancel);
        allFunctions.add(carrierOrderOk);
        allFunctions.add(orderLogCarrierInput);
        allFunctions.add(orderLogArrive);
        allFunctions.add(orderLogOrderExportExcel);
        allFunctions.add(feeAuditReceivableModify);
        allFunctions.add(feeAuditReceivableUpdate);
        allFunctions.add(feeAuditReceivableExportExcel);
        allFunctions.add(feeAuditPayableModify);
        allFunctions.add(feeAuditPayableUpdate);
        allFunctions.add(feeAuditPayableExportExcel);
        allFunctions.add(feeAuditReceivableProjectAudit);
        allFunctions.add(feeAuditReceivableProjectAuditPass);
        allFunctions.add(feeAuditReceivableProjectAuditUpdate);
        allFunctions.add(feeAuditReceivableProjectAuditExportExcel);
        allFunctions.add(feeAuditPayableProjectAudit);
        allFunctions.add(feeAuditPayableProjectAuditPass);
        allFunctions.add(feeAuditPayableProjectAuditUpdate);
        allFunctions.add(feeAuditPayableProjectAuditExportExcel);
        allFunctions.add(feeAuditReceivableFinancingAudit);
        allFunctions.add(feeAuditReceivableFinancingAuditPass);
        allFunctions.add(feeAuditReceivableFinancingAuditUpdate);
        allFunctions.add(feeAuditReceivableFinancingAuditExportExcel);
        allFunctions.add(feeAuditPayableFinancingAudit);
        allFunctions.add(feeAuditPayableFinancingAuditPass);
        allFunctions.add(feeAuditPayableFinancingAuditUpdate);
        allFunctions.add(feeAuditPayableFinancingAuditExportExcel);
        allFunctions.add(orderBackReceipt);
        allFunctions.add(orderBackRepair);
        allFunctions.add(crmOrderCreate);
        allFunctions.add(crmOrderRemove);
        allFunctions.add(crmOrderModify);
        allFunctions.add(crmOrderImportAttachment);
        allFunctions.add(crmOrderImportQuote);
        allFunctions.add(scmOrderCreate);
        allFunctions.add(scmOrderRemove);
        allFunctions.add(scmOrderModify);
        allFunctions.add(scmOrderImportAttachment);
        allFunctions.add(scmOrderImportQuote);
        allFunctions.add(orderTransportExportExcel);
        allFunctions.add(orderMonthsumExportExcel);
        allFunctions.add(orderMonthsumCrossExportExcel);
        allFunctions.add(orderCheckIncomeExportExcel);
        allFunctions.add(orderCheckPayableExportExcel);
        allFunctions.add(feeAuditReceivableFinancingAuditFinish);
        allFunctions.add(feeAuditPayableFinancingAuditFinish);

        allFunctions.add(transprotOrderSelect);
        allFunctions.add(carrierOrderSelect);
        allFunctions.add(orderLogOrderSelect);
        allFunctions.add(feeAuditReceivableSelect);
        allFunctions.add(feeAuditPayableSelect);
        allFunctions.add(feeAuditReceivableProjectAuditSelect);
        allFunctions.add(feeAuditPayableProjectAuditSelect);
        allFunctions.add(feeAuditReceivableFinancingAuditSelect);
        allFunctions.add(feeAuditPayableFinancingAuditSelect);
        allFunctions.add(orderBackSelect);
        allFunctions.add(crmOrderSelect);
        allFunctions.add(scmOrderSelect);
        allFunctions.add(orderTransportSelect);
        allFunctions.add(orderMonthsumSelect);
        allFunctions.add(orderMonthsumCrossSelect);
        allFunctions.add(orderCheckIncomeSelect);
        allFunctions.add(orderCheckPayableSelect);

        allFunctions.add(orderBaseRegionSelect);
        allFunctions.add(orderBaseExacctSelect);
        allFunctions.add(orderBaseExacctCreate);
        allFunctions.add(orderBaseExacctRemove);
        allFunctions.add(orderBaseExacctModify);
        allFunctions.add(orderBaseExacctExportExcel);

        List<SysFunction> sysFunctionList = new ArrayList<>();
        sysFunctionList.add(pickupOrderCreate);
        sysFunctionList.add(pickupOrderConfirm);
        sysFunctionList.add(pickupOrder);
        sysFunctionList.add(transportOrderCreate);
        sysFunctionList.add(transportOrderConfirm);
        sysFunctionList.add(carrierOrderCreate);
        sysFunctionList.add(carrierOrderSend);
        sysFunctionList.add(orderLogMassageInput);
        sysFunctionList.add(orderLogConfirm);
        sysFunctionList.add(feeOrderReceivableInput);
        sysFunctionList.add(feeOrderPayableInput);
        sysFunctionList.add(feeAuditReceivable);
        sysFunctionList.add(feeAuditPayable);
        sysFunctionList.add(orderBackManage);

        sysFunctionList.add(transportOrderMerge);
        sysFunctionList.add(transportOrderModify);
        sysFunctionList.add(transportOrderImportOrder);
        sysFunctionList.add(transprotOrderCancel);
        sysFunctionList.add(transportOrderExportExcel);
        sysFunctionList.add(transprotOrderSign);
        sysFunctionList.add(carrierOrderModify);
        sysFunctionList.add(carrierOrderExportExcel);
        sysFunctionList.add(carrierOrderCancel);
        sysFunctionList.add(carrierOrderOk);
        sysFunctionList.add(orderLogCarrierInput);
        sysFunctionList.add(orderLogArrive);
        sysFunctionList.add(orderLogOrderExportExcel);
        sysFunctionList.add(feeAuditReceivableModify);
        sysFunctionList.add(feeAuditReceivableUpdate);
        sysFunctionList.add(feeAuditReceivableExportExcel);
        sysFunctionList.add(feeAuditPayableModify);
        sysFunctionList.add(feeAuditPayableUpdate);
        sysFunctionList.add(feeAuditPayableExportExcel);
        sysFunctionList.add(feeAuditReceivableProjectAudit);
        sysFunctionList.add(feeAuditReceivableProjectAuditPass);
        sysFunctionList.add(feeAuditReceivableProjectAuditUpdate);
        sysFunctionList.add(feeAuditReceivableProjectAuditExportExcel);
        sysFunctionList.add(feeAuditPayableProjectAudit);
        sysFunctionList.add(feeAuditPayableProjectAuditPass);
        sysFunctionList.add(feeAuditPayableProjectAuditUpdate);
        sysFunctionList.add(feeAuditPayableProjectAuditExportExcel);
        sysFunctionList.add(feeAuditReceivableFinancingAudit);
        sysFunctionList.add(feeAuditReceivableFinancingAuditPass);
        sysFunctionList.add(feeAuditReceivableFinancingAuditUpdate);
        sysFunctionList.add(feeAuditReceivableFinancingAuditExportExcel);
        sysFunctionList.add(feeAuditPayableFinancingAudit);
        sysFunctionList.add(feeAuditPayableFinancingAuditPass);
        sysFunctionList.add(feeAuditPayableFinancingAuditUpdate);
        sysFunctionList.add(feeAuditPayableFinancingAuditExportExcel);
        sysFunctionList.add(orderBackReceipt);
        sysFunctionList.add(orderBackRepair);
        sysFunctionList.add(crmOrderCreate);
        sysFunctionList.add(crmOrderRemove);
        sysFunctionList.add(crmOrderModify);
        sysFunctionList.add(crmOrderImportAttachment);
        sysFunctionList.add(crmOrderImportQuote);
        sysFunctionList.add(scmOrderCreate);
        sysFunctionList.add(scmOrderRemove);
        sysFunctionList.add(scmOrderModify);
        sysFunctionList.add(scmOrderImportAttachment);
        sysFunctionList.add(scmOrderImportQuote);
        sysFunctionList.add(orderTransportExportExcel);
        sysFunctionList.add(orderMonthsumExportExcel);
        sysFunctionList.add(orderMonthsumCrossExportExcel);
        sysFunctionList.add(orderCheckIncomeExportExcel);
        sysFunctionList.add(orderCheckPayableExportExcel);
        sysFunctionList.add(feeAuditReceivableFinancingAuditFinish);
        sysFunctionList.add(feeAuditPayableFinancingAuditFinish);

        sysFunctionList.add(transprotOrderSelect);
        sysFunctionList.add(carrierOrderSelect);
        sysFunctionList.add(orderLogOrderSelect);
        sysFunctionList.add(feeAuditReceivableSelect);
        sysFunctionList.add(feeAuditPayableSelect);
        sysFunctionList.add(feeAuditReceivableProjectAuditSelect);
        sysFunctionList.add(feeAuditPayableProjectAuditSelect);
        sysFunctionList.add(feeAuditReceivableFinancingAuditSelect);
        sysFunctionList.add(feeAuditPayableFinancingAuditSelect);
        sysFunctionList.add(orderBackSelect);
        sysFunctionList.add(crmOrderSelect);
        sysFunctionList.add(scmOrderSelect);
        sysFunctionList.add(orderTransportSelect);
        sysFunctionList.add(orderMonthsumSelect);
        sysFunctionList.add(orderMonthsumCrossSelect);
        sysFunctionList.add(orderCheckIncomeSelect);
        sysFunctionList.add(orderCheckPayableSelect);

        sysFunctionList.add(orderBaseRegionSelect);
        sysFunctionList.add(orderBaseExacctSelect);
        sysFunctionList.add(orderBaseExacctCreate);
        sysFunctionList.add(orderBaseExacctRemove);
        sysFunctionList.add(orderBaseExacctModify);
        sysFunctionList.add(orderBaseExacctExportExcel);

        //save function
        functionService.create(sysFunctionList);


        // create admin account
        SysRole admin = admin = new SysRole("SYSADMIN","系统管理员",true);
        admin.getSysFunctions().addAll(allFunctions);
        roleService.create(admin);

        // create admin user
        SysUser adminUser = new SysUser();
        adminUser.setUsername("admin");
        adminUser.setFullName("admin system");
        adminUser.setEmail("noreply@56-net.com");
        adminUser.setPassword("admin123456");
        adminUser.setIsActive(true);
        adminUser.setIsAllowLogin(true);
        adminUser.getSysRoles().add(admin);
        userService.create(adminUser);


//        List<SysRole> sysRoles = new ArrayList<>();

        // create roles

        //客服专员
        SysRole customerCommissioner = new SysRole("CUSTOMER_SERVICE_COMMISSIONER","客服专员",true);
        roleService.create(customerCommissioner);
        customerCommissioner.getSysFunctions().add(pickupOrderCreate);
        customerCommissioner.getSysFunctions().add(pickupOrderConfirm);
        customerCommissioner.getSysFunctions().add(pickupOrder);
        customerCommissioner.getSysFunctions().add(transportOrderCreate);
        customerCommissioner.getSysFunctions().add(transportOrderConfirm);
        customerCommissioner.getSysFunctions().add(carrierOrderCreate);
        customerCommissioner.getSysFunctions().add(carrierOrderSend);
        customerCommissioner.getSysFunctions().add(feeOrderReceivableInput);
        customerCommissioner.getSysFunctions().add(feeAuditReceivable);
        roleService.saveOrUpdate(customerCommissioner);
        SysUser ccUser = new SysUser("cusCommissioner","客服专员","123456",true,true);
        ccUser.getSysRoles().add(customerCommissioner);
        userService.create(ccUser);
        //信息专员
        SysRole informationCommissioner = new SysRole("INFORMATION_COMMISSIONER","信息专员",true);
        roleService.create(informationCommissioner);
        informationCommissioner.getSysFunctions().add(orderLogMassageInput);
        informationCommissioner.getSysFunctions().add(orderLogConfirm);
        informationCommissioner.getSysFunctions().add(feeOrderPayableInput);
        roleService.saveOrUpdate(informationCommissioner);
        SysUser icUser = new SysUser("infoCommissioner","信息专员","123456",true,true);
        icUser.getSysRoles().add(informationCommissioner);
        userService.create(icUser);
        //统计专员
        SysRole statisticalCommissioner = new SysRole("STATISTICAL_COMMISSIONER","统计专员",true);
        roleService.create(statisticalCommissioner);
        statisticalCommissioner.getSysFunctions().add(feeOrderReceivableInput);
        statisticalCommissioner.getSysFunctions().add(feeOrderPayableInput);
        roleService.saveOrUpdate(statisticalCommissioner);
        //客服主管
        SysRole customerServiceSupervisor = new SysRole("CUSTOMER_SERVICE_SUPERVISOR","客服主管",true);
        roleService.create(customerServiceSupervisor);
        customerServiceSupervisor.getSysFunctions().add(pickupOrderCreate);
        customerServiceSupervisor.getSysFunctions().add(pickupOrderConfirm);
        customerServiceSupervisor.getSysFunctions().add(pickupOrder);
        customerServiceSupervisor.getSysFunctions().add(transportOrderCreate);
        customerServiceSupervisor.getSysFunctions().add(transportOrderConfirm);
        customerServiceSupervisor.getSysFunctions().add(carrierOrderCreate);
        customerServiceSupervisor.getSysFunctions().add(carrierOrderSend);
        customerServiceSupervisor.getSysFunctions().add(orderLogMassageInput);
        customerServiceSupervisor.getSysFunctions().add(orderLogConfirm);
        customerServiceSupervisor.getSysFunctions().add(feeOrderReceivableInput);
        customerServiceSupervisor.getSysFunctions().add(feeOrderPayableInput);
        customerServiceSupervisor.getSysFunctions().add(feeAuditReceivable);
        customerServiceSupervisor.getSysFunctions().add(feeAuditPayable);
        roleService.saveOrUpdate(customerServiceSupervisor);
        SysUser csUser = new SysUser("ccs","客服主管","123456",true,true);
        csUser.getSysRoles().add(customerServiceSupervisor);
        userService.create(csUser);
        //客服经理
        SysRole serviceManager = new SysRole("CUSTOMER_SERVICE_MANAGER","分公司客服经理",true);
        roleService.create(serviceManager);
        serviceManager.getSysFunctions().addAll(allFunctions);
        roleService.saveOrUpdate(serviceManager);
        //运作经理
        SysRole operationManager = new SysRole("OPERATION_MANAGER","运作经理",true);
        roleService.create(operationManager);
        operationManager.getSysFunctions().add(pickupOrderCreate);
        operationManager.getSysFunctions().add(pickupOrderConfirm);
        operationManager.getSysFunctions().add(pickupOrder);
        operationManager.getSysFunctions().add(carrierOrderCreate);
        operationManager.getSysFunctions().add(carrierOrderSend);
        operationManager.getSysFunctions().add(feeAuditPayable);
        roleService.saveOrUpdate(operationManager);
        //运作主管
        SysRole operationSupervisor = new SysRole("OPERATION_SUPERVISOR","运作主管",true);
        roleService.create(operationSupervisor);
        operationSupervisor.getSysFunctions().add(pickupOrderCreate);
        operationSupervisor.getSysFunctions().add(pickupOrderConfirm);
        operationSupervisor.getSysFunctions().add(pickupOrder);
        operationSupervisor.getSysFunctions().add(carrierOrderCreate);
        operationSupervisor.getSysFunctions().add(carrierOrderSend);
        operationSupervisor.getSysFunctions().add(feeAuditPayable);
        roleService.saveOrUpdate(operationSupervisor);
        //市内调度
        SysRole cityDispatch = new SysRole("CITY_DISPATCH","市内调度",true);
        roleService.create(cityDispatch);
        cityDispatch.getSysFunctions().add(pickupOrderCreate);
        cityDispatch.getSysFunctions().add(pickupOrderConfirm);
        cityDispatch.getSysFunctions().add(pickupOrder);
        cityDispatch.getSysFunctions().add(carrierOrderCreate);
        cityDispatch.getSysFunctions().add(carrierOrderSend);
        cityDispatch.getSysFunctions().add(feeOrderPayableInput);
        roleService.saveOrUpdate(cityDispatch);
        //打包专员
        SysRole packingCommissioner = new SysRole("PACKING_COMMISSIONER","打包专员",true);
        roleService.create(packingCommissioner);
        //提货专员
        SysRole packupCommissioner = new SysRole("PICKUP_COMMISSIONER","提货专员",true);
        roleService.create(packupCommissioner);
        packupCommissioner.getSysFunctions().add(pickupOrderCreate);
        packupCommissioner.getSysFunctions().add(carrierOrderCreate);
        packupCommissioner.getSysFunctions().add(carrierOrderSend);
        packupCommissioner.getSysFunctions().add(feeOrderPayableInput);
        roleService.saveOrUpdate(packupCommissioner);
        //发运专员
        SysRole transportCommissioner = new SysRole("TRANSPORT_COMMISSIONER","发运专员",true);
        roleService.create(transportCommissioner);
        transportCommissioner.getSysFunctions().add(pickupOrderCreate);
        transportCommissioner.getSysFunctions().add(carrierOrderCreate);
        transportCommissioner.getSysFunctions().add(carrierOrderSend);
        transportCommissioner.getSysFunctions().add(feeOrderPayableInput);
        roleService.saveOrUpdate(transportCommissioner);
        //调配专员
        SysRole deploymentCommissioner = new SysRole("DEPLOYMENT_COMMISSIONER","调配专员",true);
        roleService.create(deploymentCommissioner);
        deploymentCommissioner.getSysFunctions().add(orderBackManage);
        roleService.saveOrUpdate(deploymentCommissioner);
        //回单专员
        SysRole orderBackCommissioner = new SysRole("ORDERBACK_COMMISSIONER","回单专员",true);
        roleService.create(orderBackCommissioner);
        orderBackCommissioner.getSysFunctions().add(orderBackManage);
        roleService.saveOrUpdate(orderBackCommissioner);
        //财务专员
        SysRole financeCommissioner = new SysRole("FINANCE_COMMISSIONER","财务专员",true);
        roleService.create(financeCommissioner);
        financeCommissioner.getSysFunctions().add(feeAuditReceivable);
        financeCommissioner.getSysFunctions().add(feeAuditPayable);
        roleService.saveOrUpdate(financeCommissioner);
        //财务主管
        SysRole financeSupervisor = new SysRole("FINANCE_SUPERVISOR","财务主管",true);
        roleService.create(financeSupervisor);
        financeSupervisor.getSysFunctions().add(feeAuditReceivable);
        financeSupervisor.getSysFunctions().add(feeAuditPayable);
        roleService.saveOrUpdate(financeSupervisor);
        //财务经理
        SysRole financeManager = new SysRole("FINANCE_MANAGER","财务经理",true);
        roleService.create(financeManager);
        financeManager.getSysFunctions().add(feeAuditReceivable);
        financeManager.getSysFunctions().add(feeAuditPayable);
        roleService.saveOrUpdate(financeManager);
        //运营经理
        SysRole operationManager1 = new SysRole("OPERATION_MANAGER","运营经理",true);
        roleService.create(operationManager1);
        //分总
        SysRole subManager = new SysRole("SUB_MANAGER","分总",true);
        roleService.create(subManager);
        //运营中心助理
        SysRole operationCenterAssistant = new SysRole("OPERATION_CENTER_ASSISTANT","运营中心助理",true);
        roleService.create(operationCenterAssistant);
        operationCenterAssistant.getSysFunctions().add(orderBackManage);
        roleService.saveOrUpdate(operationCenterAssistant);
        //异常专员
        SysRole abnormalCommissioner = new SysRole("ABNORMAL_COMMISSIONER","异常专员",true);
        roleService.create(abnormalCommissioner);
        //项目专员
        SysRole projectCommissioner = new SysRole("PROJECT_COMMISSIONER","运营——项目专员",true);
        roleService.create(projectCommissioner);
        //客服经理
        SysRole serviceManager1 = new SysRole("CUSTOMER_SERVICE_MANAGER","总公司客服经理",true);
        roleService.create(serviceManager1);
        serviceManager1.getSysFunctions().add(orderBackManage);
        roleService.saveOrUpdate(serviceManager1);
        //运输经理
        SysRole transportManager = new SysRole("TRANSPORT_MANAGER","运输经理",true);
        roleService.create(transportManager);
        transportManager.getSysFunctions().add(orderBackManage);
        roleService.saveOrUpdate(transportManager);
        //渠道经理
        SysRole channelManager = new SysRole("CHANNEL_MANAGER","渠道经理",true);
        roleService.create(channelManager);
        //运营副总监
        SysRole operationDirector = new SysRole("OPERATION_DIRECTOR","运营副总监",true);
        roleService.create(operationDirector);
        //运营总监
        SysRole operationDirector1 = new SysRole("OPERATION_DIRECTOR","运营总监",true);
        roleService.create(operationDirector1);
        //项目专员
        SysRole projectCommissioner1 = new SysRole("PROJECT_COMMISSIONER","营销——项目专员",true);
        roleService.create(projectCommissioner1);
        //项目经理
        SysRole projectManager = new SysRole("PROJECT_MANAGER","项目经理",true);
        roleService.create(projectManager);
        //项目经理助理
        SysRole assistantProjectManager = new SysRole("ASSISTANT_PROJECT_MANAGER","项目经理助理",true);
        roleService.create(assistantProjectManager);
        //质控经理
        SysRole qualityControlManager = new SysRole("QUALITY_CONTROL_MANAGER","质控经理",true);
        roleService.create(qualityControlManager);
        //区域项目经理
        SysRole regionalProjectManager = new SysRole("REGIONAL_PROJECT_MANAGER","区域项目经理",true);
        roleService.create(regionalProjectManager);
        //项目总监
        SysRole projectDirector = new SysRole("PROJECT_DIRECTOR","项目总监",true);
        roleService.create(projectDirector);
        //费用会计
        SysRole expenseAccounting = new SysRole("EXPANSE_ACCOUNTING","费用会计",true);
        roleService.create(expenseAccounting);
        //成本会计
        SysRole costAccounting = new SysRole("COST_ACCOUNTING","成本会计",true);
        roleService.create(costAccounting);
        //税务会计
        SysRole taxAccounting = new SysRole("TAX_ACCOUNTING","税务会计",true);
        roleService.create(taxAccounting);
        //财务经理
        SysRole financialManager = new SysRole("FINANCIAL_MANAGER","财务经理",true);
        roleService.create(financialManager);
        //核算部经理
        SysRole accountingDepartmentManager = new SysRole("ACCOUNTING_DEPARTMENT_MANAGER","核算部经理",true);
        roleService.create(accountingDepartmentManager);
        //财务中心总监
        SysRole financialCenterDirector = new SysRole("FINANCIAL_CENTER_DIRECTOR","财务中心总监",true);
        roleService.create(financialCenterDirector);
    }
}
