<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/rptViewOtdMonthsum/index"><i class="fa fa-users"></i> 月单量统计</a></li>
</ol>

<div id="grid-wrap-rptOrderNumbers">
    <div class="grid-toolbar">
        <div>
            <label for="clientId">客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
        </div>
        <div>
            <label for="orderBeginDate">订单开始日期</label><input id="orderBeginDate" data-bind="value: orderBeginDate"/>
            <label for="orderEndDate">订单结束日期</label><input id="orderEndDate" data-bind="value:orderEndDate"/>
        </div>
        <div>
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
    </div>
    <div id="order-sum-grid" data-bind="kendoGrid: { data: dataSource, widget: widget,columns:columns,sortable: true,groupable: true, toolbar:['excel','pdf'],editable:false, pageable: pageable, selectable: 'multiple,row'}"></div>
    <div id="order-sum-chart" data-bind="kendoChart: {data:dataSource,tooltip:tooltip, series:series,categoryAxis:{field:'orderMonth'},theme:'silver'}"></div>
    <div id="order-clientSum-grid"  style="display: none" data-bind="kendoGrid: { data: dataSource2, widget: widget2,columns:columns2,sortable: true,groupable: true, toolbar:['excel','pdf'],editable:false, pageable: pageable2, selectable: 'multiple,row'}"></div>
    <div id="order-clientSum-chart"  style="display: none" data-bind="kendoChart: {data:dataSource2,tooltip:tooltip2, series:series2,categoryAxis:{field:'orderMonth'},theme:'silver'}"></div>
</div>

<script>
    var RoleGridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.orderBeginDate = ko.observable();
        self.orderEndDate = ko.observable();
        self.clientId=ko.observable();
        self.dataSource = kendo.utils.createDataSource("orderMonth", "/rptViewOtdMonthsum/getDataSource");
        self.pageable = {refresh: true, pageSize: 15, pageSizes: [10, 20, 50, 100, 200]};
        self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
        self.clearFilter = function () {
            self.orderBeginDate("");
            self.orderEndDate("");
            self.clientId("");
        };
        self.columns=[{
            field: "orderMonth",
            title: "月度",
            width: 120
        },{
            field: "orderSum",
            title: "单量",
            width: 120
        }];
        self.tooltip={
            visible: true,
            template: "#= series.name #: #= value #"
        };
        self.series = [{
            type:"line",
            name:"月单量",
            field: "orderSum"
        }];
        self.widget2 = ko.observable();
        self.dataSource2 = kendo.utils.createDataSource("orderMonth", "/rptViewClientMonthsum/getDataSource");
        self.pageable2 = {refresh: true, pageSize: 15, pageSizes: [10, 20, 50, 100, 200]};
        self.columns2=[{
            field: "orderMonth",
            title: "月度",
            width: 120
        },{
            field: "orderSum",
            title: "单量",
            width: 120
        }];
        self.tooltip2={
            visible: true,
            template: "#= series.name #: #= value #"
        };
        self.series2 = [{
            type:"line",
            name:"月单量",
            field: "orderSum",
        }];
        self.search = function (){
            var beginDate = $.trim(self.orderBeginDate());
            var endDate = $.trim(self.orderEndDate());
            var filters = new Array();
            var clientId= $.trim(self.clientId());

            if (beginDate) {
                var begin=new Date(beginDate);
                filters.push({field: "beginDate", operator: "gte", value: begin});
            }
            if (endDate) {
                var end = new Date(endDate);
                filters.push({field: "endDate", operator: "lte", value: end});
            }
            if(clientId){
                $("#order-sum-grid").css("display","none");
                $("#order-sum-chart").css("display","none");
                $("#order-clientSum-grid").css("display","block");
                $("#order-clientSum-chart").css("display","block");
                filters.push({field:"clientId",operator:"eq",value:clientId});
                self.dataSource2.filter({
                    logic: "and",
                    filters: filters
                });
            }
            else{
                $("#order-sum-grid").css("display","block");
                $("#order-sum-chart").css("display","block");
                $("#order-clientSum-grid").css("display","none");
                $("#order-clientSum-chart").css("display","none");
                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            }
        };
    };
    $(function () {
        $("#orderBeginDate").kendoDatePicker({
            format:"yyyy-MM"
        });
        $("#orderEndDate").kendoDatePicker({
            format:"yyyy-MM"
        });
        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap-rptOrderNumbers"));
    });
</script>