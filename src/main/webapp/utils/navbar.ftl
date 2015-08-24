    <ul id="navbar" data-role="panelbar">
    <li>
        <a href="/#/home"><i class="fa fa-home"></i> <span>主页</span></a>
    </li>
    <li>
        <a> <i class="fa fa-list-alt"></i> <span>订单管理</span> </a>
        <ul>
            <li><a href="/#/otdPickupOrder"><i class="fa fa-list-alt"></i> <span>提货指令</span></a>
            </li>
            <li><a href="/#/otdTransportOrderView"><i class="fa fa-list-alt"></i> <span>运输订单</span></a>
            </li>
        </ul>
    </li>
    <li>
        <a> <i class="fa fa-truck"></i><span>运输管理</span> </a>
        <ul>
            <li>
                <a href="/#/dispatchVehicle"><i class="fa fa-bus"></i><span>车辆管理</span></a>
            </li>
            <li>
                <a href="/#/dispatchAssign/inCity"><i class="fa fa-bus"></i><span>市内配送</span></a>
            </li>
            <li>
                <a href="/#/dispatchAssign/pickUpAndSend"><i class="fa fa-truck"></i><span>提货发运</span></a>
            </li>
            <li>
                <a href="/#/otdCarrierOrder"><i class="fa fa-list-alt"></i><span>托运单</span></a>
            </li>
            <li>
                <a href="/#/dispatchAssign"><i class="fa fa-list-alt"></i><span>派车单</span></a>
            </li>
        </ul>
    </li>
    <li>
        <a href="/#/otdOrderLogListView"><i class="fa fa-comments"></i><span>信息跟踪</span></a>
    </li>

       <#-- <li>
            <a href="/#/test"><i class="fa fa-comments"></i><span>测试</span></a>
        </li>-->
    <li>
        <a> <i class="fa fa-jpy"></i><span>费用审核</span> </a>
        <ul>
            <li><a href="/#/feeOrderReceivableView"><i class="fa fa-check-square-o"></i><span>应收确认</a></li>
            <li><a href="/#/feeOrderPayable"><i class="fa fa-check-square-o"></i><span>应付确认</span></a></li>
            <li><a href="/#/feeOrderReceivableView/projectAudit"><i class="fa fa-check-square-o"></i><span>应收客服经理审核</a></li>
            <li><a href="/#/feeOrderPayableView/projectAudit"><i class="fa fa-check-square-o"></i><span>应付客服经理审核</span></a></li>
            <li><a href="/#/feeOrderReceivableView/financingAudit"><i class="fa fa-check-square-o"></i><span>应收财务审核</a></li>
            <li><a href="/#/feeOrderPayableView/financingAudit"><i class="fa fa-check-square-o"></i><span>应付财务审核</span></a></li>
            </li>
        </ul>
    </li>
    <li>
        <a href="/#/otdTransportOrderView/receipt"> <i class="fa fa-edit fw"></i><span>回单管理</span> </a>
    </li>
    <li>
        <a href="/#/crmClient">
            <i class="fa fa-suitcase"></i>
            <span>客户</span>
        </a>
    </li>
    <li>
        <a href="/#/scmCarrier">
            <i class="fa fa-list-alt fw"></i>
            <span>承运商</span>
        </a>
    </li>
    <li>
        <a> <i class="fa fa-bar-chart-o fw"></i><span>报表</span> </a>
        <ul>
            <li><a href="/#/rptViewOtdTransport"><span>运作报表</span></a></li>
        </ul>
        <ul>
            <li><a href="/#/report/reportIndex"><span>动态报表</span></a></li>
        </ul>
        <ul>
            <li><a href="/#/report/transportReportIndex"><span>动态运作报表</span></a></li>
        </ul>
        <ul>
            <li><a href="/#/rptViewInformationTracking"><span>信息跟踪报表</span></a></li>
        </ul>
        <ul>
            <li><a href="/#/rptViewProductTracking"><span>物料跟踪报表</span></a></li>
        </ul>
        <ul>
            <li><a href="/#/rptViewOtdMonthsum"><span>月单量统计</span></a></li>
            <li><a href="/#/rptViewOtdMonthsum/crossProfit"> 月毛利统计</a></li>
        </ul>
        <ul>
            <li><a href="/#/rptViewCheckIncome"><span>应收对账单</span></a></li>
            <li><a href="/#/rptViewCheckPayable"><span>应付对账单</span></a></li>
        </ul>
        <ul>
            <li><a href="/#/otdOrderCirculationView"><span>运输订单流转报表</span></a></li>
        </ul>
    </li>
    <li>
        <a> <i class="fa fa-database"></i><span>基础数据</span> </a>
        <ul>
            <li>
                <a href="/#/baseRegionView"><i class="fa fa-map-marker"></i><span>地址区域</span></a>
            </li>
            <li>
                <a href="/#/baseExacct"><i class="fa fa-gift"></i><span>费用科目</span></a>
            </li>
            <li>
                <a href="/#/baseHeavygoodsType"><i class="fa fa-gift"></i><span>重货类型</span></a>
            </li>
            <li>
                <a href="/#/baseAddress"><i class="fa fa-gift"></i><span>地址</span></a>
            </li>
        </ul>
    </li>

    <li>
        <a> <i class="fa fa-gears fw"></i><span>系统设置</span> </a>
        <ul>
            <li>
                <a href="/#/sysOrganization"> <i class="fa fa-sitemap"></i> <span>组织架构</span></a>
            </li>
            <li>
                <a href="/#/sysUser"> <i class="fa fa-user"></i> <span>用户管理</span> </a>
            </li>
            <li>
                <a href="/#/sysRole"> <i class="fa fa-users"></i> <span>角色管理</span> </a>
            </li>
            <li>
                <a href="/#/sysFunction"> <i class="fa fa-th"></i> <span>功能管理</span> </a>
            </li>
            <li>
                <a href="/#/sysList"> <i class="fa fa-list-ol"></i> <span>系统列表</span> </a>
            </li>
            <li>
                <a href="/#/sysSetting"> <i class="fa fa-cog"></i> <span>系统参数</span></a>
            </li>
        </ul>
    </li>

</ul>