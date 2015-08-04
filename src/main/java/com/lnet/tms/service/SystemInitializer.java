package com.lnet.tms.service;

import com.lnet.tms.dao.sys.SysListDao;
import com.lnet.tms.dao.sys.SysSettingDao;
import com.lnet.tms.dao.sys.SysUserDao;
import com.lnet.tms.model.sys.SysList;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.model.sys.SysSetting;
import com.lnet.tms.model.sys.SysUser;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
public class SystemInitializer implements InitializingBean {

    private final String initializedName = "system.initialized";

    @Autowired
    private SysSettingDao sysSettingDao;
    @Autowired
    private SysListDao sysListDao;

    @Autowired
    private SysUserDao sysUserDao;


    public boolean isInitialized() {
        SysSetting initSetting = sysSettingDao.get(initializedName);
        if (initSetting == null) return false;
        return "true".equalsIgnoreCase(initSetting.getValue());
    }

    public void initialize() {

        System.out.println("SystemInitializer.initialize");

        SysSetting initSetting = sysSettingDao.get(initializedName);
        if (initSetting != null && "true".equalsIgnoreCase(initSetting.getValue())) return;

        // 管理员
        SysUser admin = sysUserDao.getByUsername("admin");
        if (admin == null) {
            admin = new SysUser();
            admin.setUsername("admin");
            admin.setEmail("noreply@56-net.com");
            admin.setPassword("admin123456");
            admin.setIsActive(true);
            admin.setIsAllowLogin(true);
            sysUserDao.create(admin);
        } else {
            admin.setPassword("admin123456");
            sysUserDao.update(admin);
        }

        // 组织类型
        SysList organizationType = new SysList();
        organizationType.setCode("organizationType");
        organizationType.setName("组织类型");

        SysListItem jtItem = new SysListItem();
        jtItem.setName("集团");
        jtItem.setValue(1);
        organizationType.getItems().add(jtItem);

        SysListItem jtItem1 = new SysListItem();
        jtItem1.setName("区域");
        jtItem1.setValue(2);
        organizationType.getItems().add(jtItem1);

        SysListItem jtItem2 = new SysListItem();
        jtItem2.setName("分公司");
        jtItem2.setValue(3);
        organizationType.getItems().add(jtItem2);

        SysListItem jtItem3 = new SysListItem();
        jtItem3.setName("办事处");
        jtItem3.setValue(4);
        organizationType.getItems().add(jtItem3);

//        SysListItem jtItem4 = new SysListItem();
//        jtItem4.setName("部门");
//        jtItem4.setValue(5);
//        organizationType.getItems().add(jtItem4);
//
//        SysListItem jtItem5 = new SysListItem();
//        jtItem5.setName("中心部门");
//        jtItem5.setValue(6);
//        organizationType.getItems().add(jtItem5);

        sysListDao.create(organizationType);

        // 客户级别
        SysList clientGrade = new SysList();
        clientGrade.setCode("clientGrade");
        clientGrade.setName("客户级别");

        SysListItem clItem = new SysListItem();
        clItem.setName("A");
        clItem.setValue(1);
        clientGrade.getItems().add(clItem);

        SysListItem clItem1 = new SysListItem();
        clItem1.setName("B");
        clItem1.setValue(2);
        clientGrade.getItems().add(clItem1);

        SysListItem clItem2 = new SysListItem();
        clItem2.setName("C");
        clItem2.setValue(3);
        clientGrade.getItems().add(clItem2);

        sysListDao.create(clientGrade);

        // 行业
        SysList industryType = new SysList();
        industryType.setCode("industryType");
        industryType.setName("行业");

        SysListItem itItem = new SysListItem();
        itItem.setName("饮食行业");
        itItem.setValue(1);
        industryType.getItems().add(itItem);

        SysListItem itItem1 = new SysListItem();
        itItem1.setName("服装行业");
        itItem1.setValue(2);
        industryType.getItems().add(itItem1);

        SysListItem itItem2 = new SysListItem();
        itItem2.setName("机械行业");
        itItem2.setValue(3);
        industryType.getItems().add(itItem2);

        SysListItem itItem3 = new SysListItem();
        itItem3.setName("金融行业");
        itItem3.setValue(4);
        industryType.getItems().add(itItem3);

        SysListItem itItem4 = new SysListItem();
        itItem4.setName("移动互联网行业");
        itItem4.setValue(5);
        industryType.getItems().add(itItem4);

        sysListDao.create(industryType);

        // 结算周期
        SysList settleCycle = new SysList();
        settleCycle.setCode("settleCycle");
        settleCycle.setName("结算周期");

        SysListItem slItem = new SysListItem();
        slItem.setName("现结");
        slItem.setValue(1);
        settleCycle.getItems().add(slItem);

        SysListItem slItem1 = new SysListItem();
        slItem1.setName("到付");
        slItem1.setValue(2);
        settleCycle.getItems().add(slItem1);

        SysListItem slItem2 = new SysListItem();
        slItem2.setName("月结");
        slItem2.setValue(3);
        settleCycle.getItems().add(slItem2);

        SysListItem slItem3 = new SysListItem();
        slItem3.setName("免单");
        slItem3.setValue(4);
        settleCycle.getItems().add(slItem3);

        SysListItem slItem4 = new SysListItem();
        slItem4.setName("回单付");
        slItem4.setValue(5);
        settleCycle.getItems().add(slItem4);

        sysListDao.create(settleCycle);

        // 付款方式
        SysList paymentType = new SysList();
        paymentType.setCode("paymentType");
        paymentType.setName("支付方式");

        SysListItem ptItem = new SysListItem();
        ptItem.setName("现金");
        ptItem.setValue(1);
        paymentType.getItems().add(ptItem);

        SysListItem ptItem1 = new SysListItem();
        ptItem1.setName("支票");
        ptItem1.setValue(2);
        paymentType.getItems().add(ptItem1);

        SysListItem ptItem2 = new SysListItem();
        ptItem2.setName("转账");
        ptItem2.setValue(3);
        paymentType.getItems().add(ptItem2);

        sysListDao.create(paymentType);
        // 承运商级别
        SysList carrierGrade = new SysList();
        carrierGrade.setCode("carrierGrade");
        carrierGrade.setName("承运商等级");

        SysListItem cgItem = new SysListItem();
        cgItem.setName("A");
        cgItem.setValue(1);
        carrierGrade.getItems().add(cgItem);

        SysListItem cgItem1 = new SysListItem();
        cgItem1.setName("B");
        cgItem1.setValue(2);
        carrierGrade.getItems().add(cgItem1);

        SysListItem cgItem2 = new SysListItem();
        cgItem2.setName("C");
        cgItem2.setValue(3);
        carrierGrade.getItems().add(cgItem2);

        sysListDao.create(carrierGrade);
        // 承运商类型
        SysList carrierType = new SysList();
        carrierType.setCode("carrierType");
        carrierType.setName("承运商类型");

        SysListItem ctItem = new SysListItem();
        ctItem.setName("专线");
        ctItem.setValue(1);
        carrierType.getItems().add(ctItem);

        SysListItem ctItem1 = new SysListItem();
        ctItem1.setName("快递");
        ctItem1.setValue(1);
        carrierType.getItems().add(ctItem1);

        sysListDao.create(carrierType);
        //订单状态
        SysList orderStatus = new SysList();
        orderStatus.setCode("orderStatus");
        orderStatus.setName("订单状态");

        SysListItem osItem = new SysListItem();
        osItem.setName("接单");
        osItem.setValue(1);
        orderStatus.getItems().add(osItem);

        SysListItem osItem1 = new SysListItem();
        osItem1.setName("确认");
        osItem1.setValue(2);
        orderStatus.getItems().add(osItem1);

        SysListItem osItem2 = new SysListItem();
        osItem2.setName("调度");
        osItem2.setValue(3);
        orderStatus.getItems().add(osItem2);

        SysListItem osItem3 = new SysListItem();
        osItem3.setName("发运");
        osItem3.setValue(4);
        orderStatus.getItems().add(osItem3);

        SysListItem osItem4 = new SysListItem();
        osItem4.setName("签收");
        osItem4.setValue(5);
        orderStatus.getItems().add(osItem4);

        SysListItem osItem5 = new SysListItem();
        osItem5.setName("取消");
        osItem5.setValue(6);
        orderStatus.getItems().add(osItem5);

        sysListDao.create(orderStatus);

        //区域类型
        SysList regionType = new SysList();
        regionType.setCode("regionType");
        regionType.setName("区域类型");

        SysListItem rtItem = new SysListItem();
        rtItem.setName("省");
        rtItem.setValue(1);
        regionType.getItems().add(rtItem);

        SysListItem rtItem1 = new SysListItem();
        rtItem1.setName("城市");
        rtItem1.setValue(2);
        regionType.getItems().add(rtItem1);

        SysListItem rtItem2 = new SysListItem();
        rtItem2.setName("县");
        rtItem2.setValue(3);
        regionType.getItems().add(rtItem2);

        SysListItem rtItem3 = new SysListItem();
        rtItem3.setName("区");
        rtItem3.setValue(4);
        regionType.getItems().add(rtItem3);

        sysListDao.create(regionType);
        //订单类型
        SysList orderType = new SysList();
        orderType.setCode("orderType");
        orderType.setName("订单类型");

        SysListItem otItem = new SysListItem();
        otItem.setName("销售订单");
        otItem.setValue(1);
        orderType.getItems().add(otItem);

        SysListItem otItem1 = new SysListItem();
        otItem1.setName("调拨订单");
        otItem1.setValue(2);
        orderType.getItems().add(otItem1);

        SysListItem otItem2 = new SysListItem();
        otItem2.setName("采购订单");
        otItem2.setValue(3);
        orderType.getItems().add(otItem2);

        SysListItem otItem3 = new SysListItem();
        otItem3.setName("借物订单");
        otItem3.setValue(4);
        orderType.getItems().add(otItem3);

        SysListItem otItem4 = new SysListItem();
        otItem4.setName("提货订单");
        otItem4.setValue(5);
        orderType.getItems().add(otItem4);

        SysListItem otItem5 = new SysListItem();
        otItem5.setName("退货订单");
        otItem5.setValue(6);
        orderType.getItems().add(otItem5);

        SysListItem otItem6 = new SysListItem();
        otItem6.setName("调拨订单");
        otItem6.setValue(7);
        orderType.getItems().add(otItem6);

        sysListDao.create(orderType);
        //商业模式
        SysList businessModel = new SysList();
        businessModel.setCode("businessModel");
        businessModel.setName("商业模式");

        SysListItem bmItem = new SysListItem();
        bmItem.setName("运营模式");
        bmItem.setValue(1);
        businessModel.getItems().add(bmItem);

        SysListItem bmItem1 = new SysListItem();
        bmItem1.setName("盈利模式");
        bmItem1.setValue(2);
        businessModel.getItems().add(bmItem1);

        SysListItem bmItem2 = new SysListItem();
        bmItem2.setName("B2B模式");
        bmItem2.setValue(3);
        businessModel.getItems().add(bmItem2);

        SysListItem bmItem3 = new SysListItem();
        bmItem3.setName("B2C模式");
        bmItem3.setValue(4);
        businessModel.getItems().add(bmItem3);

        SysListItem bmItem4 = new SysListItem();
        bmItem4.setName("广告收益模式");
        bmItem4.setValue(5);
        businessModel.getItems().add(bmItem4);

        sysListDao.create(businessModel);


        //交接方式
        SysList handoverType = new SysList();
        handoverType.setCode("handoverType");
        handoverType.setName("交接方式");

        SysListItem htItem = new SysListItem();
        htItem.setName("门到门");
        htItem.setValue(1);
        handoverType.getItems().add(htItem);

        SysListItem htItem1 = new SysListItem();
        htItem1.setName("自提");
        htItem1.setValue(2);
        handoverType.getItems().add(htItem1);

        sysListDao.create(handoverType);
        //计价方式
        SysList calculateType = new SysList();
        calculateType.setCode("calculateType");
        calculateType.setName("计价方式");

        SysListItem cltItem = new SysListItem();
        cltItem.setName("重量");
        cltItem.setValue(1);
        calculateType.getItems().add(cltItem);

        SysListItem cltItem1 = new SysListItem();
        cltItem1.setName("件数");
        cltItem1.setValue(2);
        calculateType.getItems().add(cltItem1);

        SysListItem cltItem2 = new SysListItem();
        cltItem2.setName("体积");
        cltItem2.setValue(3);
        calculateType.getItems().add(cltItem2);

        SysListItem cltItem3 = new SysListItem();
        cltItem3.setName("货值");
        cltItem3.setValue(4);
        calculateType.getItems().add(cltItem3);

        SysListItem cltItem4 = new SysListItem();
        cltItem4.setName("车次");
        cltItem4.setValue(5);
        calculateType.getItems().add(cltItem4);

        SysListItem cltItem5 = new SysListItem();
        cltItem5.setName("里程");
        cltItem5.setValue(6);
        calculateType.getItems().add(cltItem5);

        SysListItem cltItem6 = new SysListItem();
        cltItem6.setName("其他");
        cltItem6.setValue(7);
        calculateType.getItems().add(cltItem6);

        sysListDao.create(calculateType);
        //运输方式
        SysList transportType = new SysList();
        transportType.setCode("transportType");
        transportType.setName("运输方式");

        SysListItem ttItem = new SysListItem();
        ttItem.setName("公路整车");
        ttItem.setValue(1);
        transportType.getItems().add(ttItem);

        SysListItem ttItem1 = new SysListItem();
        ttItem1.setName("公路零担");
        ttItem1.setValue(2);
        transportType.getItems().add(ttItem1);

        SysListItem ttItem2 = new SysListItem();
        ttItem2.setName("空运");
        ttItem2.setValue(3);
        transportType.getItems().add(ttItem2);

        SysListItem ttItem3 = new SysListItem();
        ttItem3.setName("海运");
        ttItem3.setValue(4);
        transportType.getItems().add(ttItem3);

        SysListItem ttItem4 = new SysListItem();
        ttItem4.setName("铁路");
        ttItem4.setValue(5);
        transportType.getItems().add(ttItem4);

        SysListItem ttItem5 = new SysListItem();
        ttItem5.setName("内河航运");
        ttItem5.setValue(6);
        transportType.getItems().add(ttItem5);

        SysListItem ttItem6 = new SysListItem();
        ttItem6.setName("快递");
        ttItem6.setValue(7);
        transportType.getItems().add(ttItem6);

        SysListItem ttItem7 = new SysListItem();
        ttItem7.setName("快线");
        ttItem7.setValue(8);
        transportType.getItems().add(ttItem7);

        SysListItem ttItem8 = new SysListItem();
        ttItem8.setName("空运早班");
        ttItem8.setValue(9);
        transportType.getItems().add(ttItem8);

        SysListItem ttItem9 = new SysListItem();
        ttItem9.setName("空运晚班");
        ttItem9.setValue(10);
        transportType.getItems().add(ttItem9);

        SysListItem ttItem10 = new SysListItem();
        ttItem10.setName("空运加急");
        ttItem10.setValue(11);
        transportType.getItems().add(ttItem10);

        sysListDao.create(transportType);


        // 设置系统初始化状态
        if (initSetting == null) {
            initSetting = new SysSetting();

            initSetting.setName(initializedName);
            initSetting.setRemark("系统是否已经初始化");
            initSetting.setValue("true");
            sysSettingDao.create(initSetting);
        } else {
            initSetting.setValue("true");
            sysSettingDao.update(initSetting);
        }

    }

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        initialize();
    }
}
