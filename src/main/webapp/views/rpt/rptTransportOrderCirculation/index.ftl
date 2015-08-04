<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdOrderCirculationView"><i class="fa fa-users"></i> 运输订单流转报表</a></li>
</ol>


<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="clientOrderNumber">客户单号</label><input class="k-textbox" data-bind="value:clientOrderNumber">
            <label for="orderBeginDate">订单日期</label><input data-bind="kendoDatePicker:{value:orderBeginDate, max:orderEndDate}">
            <label for="orderEndDate" class="nospace">至</label><input data-bind="kendoDatePicker:{value:orderEndDate, min: orderBeginDate}">
            <label for="clientId">客户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
        </div>
        <div>
            <label for="lnetOrderNumber">新易泰单号</label><input class="k-textbox" data-bind="value:lnetOrderNumber">
            <label for="sentDateBegin">发运日期</label><input data-bind="kendoDatePicker:{value:sentDateBegin, max:sentDateEnd} ">
            <label for="sentDateEnd" class="nospace">至</label><input data-bind="kendoDatePicker:{value:sentDateEnd, min:sentDateBegin}">
            <label for="orderStatus">状态</label><@utils.koDropDown data="$root.orderStatuList" value="orderStatus"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,
     toolbar:['excel','pdf'],editable:false, pageable: pageable }"></div>
</div>


<script>

    var GridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("transportOrderId", "/otdTransportOrderView/getNotMergeOrder");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.clearFilter = function () {
            self.clientOrderNumber("");
            self.lnetOrderNumber("");
            self.orderBeginDate("");
            self.orderEndDate("");
            self.sentDateBegin("");
            self.sentDateEnd("");
            self.orderStatus("");
            self.clientId("");
            self.dataSource.filter([]);
        };

        self.columns = [{
            field: "clientName",
            title: "客户",
            width: 120
        }, {
            field: "clientOrderNumber",
            title: "客户单号",
            template: "<a href='\\#/otdOrderCirculationView/detail/#= transportOrderId #' >#= clientOrderNumber# </a>",
            width: 120
        }, {
            field: "lnetOrderNumber",
            title: "新易泰单号",
            width: 120
        }, {
            field: "carrierOrderNumber",
            title: "托运单号",
            width: 120
        }, {
            field: "orderDate",
            title: "订单日期",
            template: function (dataItem) {
                return kendo.toString(kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
            },
            width: 90
        }, {
            field: "sentDate",
            title: "发运时间",
            template: function (dataItem) {
                if (dataItem.sentDate)  return kendo.toString(kendo.parseDate(dataItem.sentDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
            width: 90
        }, {
            field: "startCity",
            title: "始发地",
            width: 90
        }, {
            field: "destCity",
            title: "目的地",
            width: 90
        }, {
            field: "receiveMan",
            title: "收货人",
            width: 90
        }, {
            field: "statusName",
            title: "状态",
            width: 90
        }];

        self.clientOrderNumber = ko.observable();
        self.lnetOrderNumber = ko.observable();
        self.orderBeginDate = ko.observable();
        self.orderEndDate = ko.observable();
        self.sentDateBegin = ko.observable();
        self.sentDateEnd = ko.observable();
        self.clientId = ko.observable();
        self.orderStatus = ko.observable("");
        self.orderStatuList = commonData.orderStatus;
        self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
        self.search = function () {
            var clientOrderNumber = $.trim(self.clientOrderNumber());
            var lnetOrderNumber = $.trim(self.lnetOrderNumber());
            var orderBeginDate = $.trim(self.orderBeginDate());
            var orderEndDate = $.trim(self.orderEndDate());
            var sentDateBegin = $.trim(self.sentDateBegin());
            var sentDateEnd = $.trim(self.sentDateEnd());
            var clientId = $.trim(self.clientId());
            var orderStatus = $.trim(self.orderStatus());
            var filters = new Array();
            if (clientOrderNumber) {
                filters.push({
                    field: "clientOrderNumber",
                    operator: "contains",
                    value: clientOrderNumber
                });
            }
            if (lnetOrderNumber) {
                filters.push({
                    field: "lnetOrderNumber",
                    operator: "contains",
                    value: lnetOrderNumber
                });
            }
            if (clientId) {
                filters.push({
                    field: "clientId",
                    operator: "eq",
                    value: clientId
                });
            }
            if (orderBeginDate) {
                filters.push({
                    field: "orderDate",
                    operator: "gte",
                    value: new Date(orderBeginDate)
                });
            }
            if (orderEndDate) {
                filters.push({
                    field: "orderDate",
                    operator: "lte",
                    value: new Date(orderEndDate)
                });
            }
            if (sentDateBegin) {
                filters.push({
                    field: "sentDate",
                    operator: "gte",
                    value: new Date(sentDateBegin)
                });
            }
            if (sentDateEnd) {
                filters.push({
                    field: "sentDate",
                    operator: "lte",
                    value: new Date(sentDateEnd)
                });
            }
            if (orderStatus) {
                filters.push({
                    field: "status",
                    operator: "eq",
                    value: orderStatus
                });
            }

            if (filters.length > 0)
                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            else
                self.dataSource.filter([]);
        }
    };

    $(function () {
        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
        $(document).keydown(function(event){
            if(event.keyCode==13){
                viewModel.search();
            }
        });
    });
</script>