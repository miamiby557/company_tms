<#import "/utils/dropdown.ftl" as utils >
<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="clientNumbers">多个客户单号</label>
            <textarea style="width: 500px" class="k-textbox" name="clientNumbers"  id="clientNumbers" rows="3" cols="10"
                      data-bind="value:clientNumbers" placeholder="多个单号以逗号，隔开"></textarea>
        </div>
        <div>
            <label for="clientOrderNumber">客户单号</label><input class="k-textbox" data-bind="value:clientOrderNumber">
            <label for="clientId">客户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
            <label for="destCityId">目的城市</label><@utils.koComboBox data="$root.citys" value="destCityId" textField="name" valueField="regionId"/>
        </div>
        <div>
            <label for="transportType">运输方式</label><@utils.koDropDown data="$root.transportTypes" value="transportType" />
            <label for="orderBeginDate">订单日期</label><input data-bind="kendoDatePicker:{value:orderBeginDate, max:orderEndDate}">
            <label for="orderEndDate">至</label><input data-bind="kendoDatePicker:{value:orderEndDate, min: orderBeginDate}">
        </div>
        <div>
            <label for="receiveCompany">收货公司</label><input class="k-textbox" data-bind="value:receiveCompany">
            <label for="receiveMan">收货人</label><input class="k-textbox" data-bind="value:receiveMan">
            <label for="mergeStatus">合单状态</label><@utils.koDropDown data="$root.mergeStatusList" value="mergeStatus"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>

        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <button type="button" data-bind="kendoButton:select, enable:canSelect">
                <i class="fa fa-edit"></i> 选择
            </button>
        </div>
    </div>
    <div id="transportSearch" data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, editable:false, pageable: pageable, selectable:'multiple, row', change:onChange }"></div>
</div>


<script>

    var GridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("transportOrderId", "/chooseTransportOrderView/getDataSource");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.clearFilter = function () {
            self.clientNumbers("");
            self.clientOrderNumber("");
            self.orderBeginDate("");
            self.orderEndDate("");
            self.clientId("");
            self.receiveMan("");
            self.destCityId("");
            self.transportType("");
            self.mergeStatus("");
            self.receiveCompany("");
            self.dataSource.filter([]);
        };

        self.columns = [{
            field: "clientName",
            title: "客户",
            width: 120
        }, {
            field: "clientOrderNumber",
            title: "客户单号",
            width: 120
        }, {
            field: "transportTypeName",
            title: "运输方式",
            width: 120
        }, {
            field: "receiveMan",
            title: "收货人",
            width: 120
        }, {
            field: "orderDate",
            title: "订单日期",
            template: function (dataItem) {
                return kendo.toString(kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
            },
            width: 120
        }, {
            field: "startCity",
            title: "始发地",
            width: 90
        }, {
            field: "destCity",
            title: "目的地",
            width: 90
        }, {
            field: "mergeStatus",
            title: "合单状态",
            values:ko.toJS(commonData.transportMergeStatus),
            width: 90
        }];

        self.selectedOrder = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedOrder(dataItem);
        };

        self.canSelect = ko.computed(function () {
            return self.selectedOrder();
        });

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
            $(".k-window-content.k-content").data("kendoWindow").sender=selectedItems;
            $(".k-window-content.k-content").data("kendoWindow").close();
        };

        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length+1;i++){
                select.push("tr:eq("+i+")");
            }
            console.info(select.toString());
            var grid = $("#transportSearch").data("kendoGrid");
            grid.select(select.toString());
            select.length=0;
        }
        self.clientNumbers = ko.observable();
        self.clientOrderNumber = ko.observable();
        self.orderBeginDate = ko.observable();
        self.orderEndDate = ko.observable();
        self.clientId = ko.observable();
        self.receiveMan=ko.observable();
        self.destCityId=ko.observable();
        self.transportType = ko.observable();
        self.receiveCompany=ko.observable();
        self.mergeStatus=ko.observable();
        self.mergeStatusList=commonData.transportMergeStatus;
        self.transportTypes = commonData.transportType;
        self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
        self.citys = kendo.utils.createListDataSource("regionId","/baseRegion/getCityList");
        self.search = function () {
            var clientNumbers = $.trim(self.clientNumbers());
            var clientOrderNumber = $.trim(self.clientOrderNumber());
            var receiveMan = $.trim(self.receiveMan());
            var receiveCompany = $.trim(self.receiveCompany());
            var orderBeginDate = $.trim(self.orderBeginDate());
            var orderEndDate = $.trim(self.orderEndDate());
            var clientId = $.trim(self.clientId());
            var destCityId = $.trim(self.destCityId());
            var transportType = $.trim(self.transportType());
            var mergeStatus= $.trim(self.mergeStatus());
            var filters = new Array();
            if (clientNumbers) {
                clientNumbers = clientNumbers.replaceAllChar(",","，");
                var numbers =clientNumbers.split("，");
                filters.push({
                    field: "clientOrderNumber",
                    operator: "in",
                    value: numbers
                });
            }
            if (clientOrderNumber) {
                filters.push({
                    field: "clientOrderNumber",
                    operator: "contains",
                    value: clientOrderNumber
                });
            }
            if (transportType) {
                filters.push({
                    field: "transportType",
                    operator: "eq",
                    value: transportType
                });
            }
            if (mergeStatus) {
                filters.push({
                    field: "mergeStatus",
                    operator: "eq",
                    value: mergeStatus
                });
            }
            if (receiveMan) {
                filters.push({
                    field: "receiveMan",
                    operator: "contains",
                    value: receiveMan
                });
            }
            if (receiveCompany) {
                filters.push({
                    field: "receiveCompany",
                    operator: "contains",
                    value: receiveCompany
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
            if (clientId) {
                filters.push({
                    field: "clientId",
                    operator: "eq",
                    value: clientId
                });
            }
            if (destCityId) {
                filters.push({
                    field: "destCityId",
                    operator: "eq",
                    value: destCityId
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
    });
</script>