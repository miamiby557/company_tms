<#import "/utils/dropdown.ftl" as utils >
<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="clientOrderNumber">客户单号</label><input class="k-textbox" data-bind="value:clientOrderNumber">
            <label for="orderBeginDate">订单日期</label><input data-bind="kendoDatePicker:{value:orderBeginDate, max:orderEndDate}">
            <label for="orderEndDate">至</label><input data-bind="kendoDatePicker:{value:orderEndDate, min: orderBeginDate}">
        </div>
        <div>
            <label for="clientId">客户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
            <label for="sentDateBegin">发运日期</label><input data-bind="kendoDatePicker:{value:sentDateBegin, max:sentDateEnd} ">
            <label for="sentDateEnd">至</label><input data-bind="kendoDatePicker:{value:sentDateEnd, min:sentDateBegin}">
        </div>
        <div>
            <label for="receiveCompany">收货公司</label><input class="k-textbox" data-bind="value:receiveCompany">
            <label for="receiveMan">收货人</label><input class="k-textbox" data-bind="value:receiveMan">
            <label for="mergeStatus">合单状态</label><@utils.koDropDown data="$root.mergeStatusList" value="mergeStatus"/>
        </div>
        <#--<div>
            <label for="orderDispatchType">调度类型</label><@utils.koDropDown data="$root.orderDispatchTypeList" value="orderDispatchType"/>
        </div>-->
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>

        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:select">
                <i class="fa fa-save"></i> 选择
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,
    editable:false, pageable: pageable, selectable: 'multiple,row',change:onChange }"></div>
</div>


<script>
    var GridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createFilterDataSource("transportOrderId", "/otdTransportOrderView/getOkTransportOrder",
                {field: "orderDispatchType",operator: "eq",value: 1});
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.clearFilter = function () {
            self.clientOrderNumber("");
            self.lnetOrderNumber("");
            self.orderBeginDate("");
            self.orderEndDate("");
            self.sentDateBegin("");
            self.sentDateEnd("");
            //self.orderDispatchType("");
            self.mergeStatus("");
            self.clientId("");
            self.transportType("");
            self.receiveMan("");
            self.receiveCompany("");
            self.dataSource.filter([]);
        };

        self.select = function () {
            var selectedIds = [];
            var selectedItems=[];
            var rows = self.widget().select();
            rows.each(function (i, row) {
                var dataItem = self.widget().dataItem(row);
                if (dataItem) {
                    selectedIds.push(dataItem.transportOrderId);
                    selectedItems.push(dataItem);
                }
            });
            if(selectedItems.length>0){
                $(".k-window-content.k-content").data("kendoWindow").sender=selectedItems;
                $(".k-window-content.k-content").data("kendoWindow").close();
            }
        };
        self.columns = [{
            title: "序号",
            template: function (dataItem) {
                return self.dataSource.indexOf(dataItem)+1;
            },
            width: 40
        },{
            field: "clientName",
            title: "客户",
            width: 100
        }, {
            field: "clientOrderNumber",
            title: "客户单号",
            width: 120
        },{
            field: "orderDate",
            title: "订单日期",
            template: function (dataItem) {
                return kendo.toString(kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
            },
            width: 80
        }, {
            field: "sentDate",
            title: "发运时间",
            template: function (dataItem) {
                if (dataItem.sentDate)  return kendo.toString(kendo.parseDate(dataItem.sentDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
            width: 80
        }, {
            field: "expectedDate",
            title: "预计到达时间",
            template: function (dataItem) {
                if (dataItem.expectedDate)  return kendo.toString(kendo.parseDate(dataItem.expectedDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
            width: 80
        }, {
            field: "startCity",
            title: "始发地",
            width: 80
        }, {
            field: "destCity",
            title: "目的地",
            width: 80
        }, {
            field: "receiveMan",
            title: "收货人",
            width: 80
        },{
            field: "mergeStatus",
            title: "合单状态",
            width: 80,
            values:ko.toJS(commonData.transportMergeStatus)
        }];

        self.selectedOrder = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedOrder(dataItem);
        };
        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length;i++){
                select.push("tr:eq("+i+")");
            }
            var grid = $(".k-grid").data("kendoGrid");
            grid.select(select.toString());
        };

        self.modify = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            rows.each(function (i, row) {
                var dataItem = self.widget().dataItem(row);
                if (dataItem) {
                    selectedIds.push(dataItem.transportOrderId);
                }
            });
            var url = "#/dispatchAssign/createAssign/" + selectedIds;
            router.navigate(url);
        };

        self.clientOrderNumber = ko.observable();
        self.lnetOrderNumber = ko.observable();
        self.orderBeginDate = ko.observable();
        self.orderEndDate = ko.observable();
        self.sentDateBegin = ko.observable();
        self.sentDateEnd = ko.observable();
        self.clientId = ko.observable();
        //self.orderDispatchType = ko.observable(1);
        self.transportType= ko.observable();
        self.receiveMan=ko.observable();
        self.receiveCompany=ko.observable();
        self.mergeStatus=ko.observable();
        self.mergeStatusList=commonData.transportMergeStatus;
        self.orderDispatchTypeList = commonData.orderDispatchType;
        self.transportTypes = commonData.transportType;
        self.transportMergeStatus=commonData.transportMergeStatus;
        self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
        self.search = function () {
            var clientOrderNumber = $.trim(self.clientOrderNumber());
            var lnetOrderNumber = $.trim(self.lnetOrderNumber());
            var orderBeginDate = $.trim(self.orderBeginDate());
            var orderEndDate = $.trim(self.orderEndDate());
            var sentDateBegin = $.trim(self.sentDateBegin());
            var sentDateEnd = $.trim(self.sentDateEnd());
            var clientId = $.trim(self.clientId());
            //var orderDispatchType = $.trim(self.orderDispatchType());
            var transportType = $.trim(self.transportType());
            var receiveMan = $.trim(self.receiveMan());
            var receiveCompany= $.trim(self.receiveCompany());
            var mergeStatus= $.trim(self.mergeStatus());
            var filters = new Array();
            if (clientOrderNumber) {
                filters.push({
                    field: "clientOrderNumber",
                    operator: "contains",
                    value: clientOrderNumber
                });
            }
            filters.push({field: "orderDispatchType",operator: "eq",value: 1});
            if (transportType) {
                filters.push({
                    field: "transportType",
                    operator: "eq",
                    value: transportType
                });
            }
            if (lnetOrderNumber) {
                filters.push({
                    field: "lnetOrderNumber",
                    operator: "contains",
                    value: lnetOrderNumber
                });
            }
            if (receiveCompany) {
                filters.push({
                    field: "receiveCompany",
                    operator: "contains",
                    value: receiveCompany
                });
            }
            if(receiveMan){
                filters.push({
                    field: "receiveMan",
                    operator: "contains",
                    value: receiveMan
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
                var endDate=new Date(orderEndDate);
                filters.push({
                    field: "orderDate",
                    operator: "lt",
                    value: new Date(endDate.setDate(endDate.getDate()+1))
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
                var sentDateEnd=new Date(sentDateEnd);
                filters.push({
                    field: "sentDate",
                    operator: "lt",
                    value: new Date(sentDateEnd.setDate(sentDateEnd.getDate()+1))
                });
            }
            /*if (orderDispatchType) {
                filters.push({
                    field: "orderDispatchType",
                    operator: "eq",
                    value: orderDispatchType
                });
            }*/
            if (mergeStatus) {
                filters.push({
                    field: "mergeStatus",
                    operator: "eq",
                    value: mergeStatus
                });
            }
            self.dataSource.filter({
                logic: "and",
                filters: filters
            });
        }
    };

    $(function () {
        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>