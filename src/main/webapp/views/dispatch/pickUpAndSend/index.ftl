<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/dispatchAssign/pickUpAndSend"><i class="fa fa-users"></i> 提货发运</a></li>
</ol>
<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="pickupOrderNumber">提货单号</label><input class="k-textbox" data-bind="value:pickupOrderNumber">
            <label for="clientId">客户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
            <label for="address">提货地址</label><input class="k-textbox" data-bind="value:address">
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>

        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <button type="button" data-bind="kendoButton:confirm,enable:canCreate">
                <i class="fa fa-edit"></i> 预约确认
            </button>
            <button type="button" data-bind="kendoButton:create,enable:canCreate">
                <i class="fa fa-edit"></i> 派车
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
        self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
        self.dataSource = kendo.utils.createFilterDataSource("pickupOrderId", "/dispatchAssign/getConfirmPickUpOrder",{field: "processStatus",operator: "eq",value: "2"});
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length;i++){
                select.push("tr:eq("+i+")");
            }
            var grid = $(".k-grid").data("kendoGrid");
            grid.select(select.toString());
        }
        self.clientId = ko.observable();
        self.pickupOrderNumber = ko.observable();
        self.address = ko.observable();
        self.clearFilter = function () {
            self.clientId("");
            self.pickupOrderNumber("");
            self.address("");
            self.dataSource.filter([{field: "processStatus",operator: "eq",value: "2"}]);
        };
        self.columns = [{
            title: "序号",
            template: function (dataItem) {
                return self.dataSource.indexOf(dataItem)+1;
            },
            width: 20
        },{
            field: "clientName",
            title: "客户",
            width: 80
        }, {
            field: "pickupOrderNumber",
            title: "提货单号",
            template: "<a href='\\#/otdPickupOrder/details/#= pickupOrderId #' >#= pickupOrderNumber# </a>",
            width: 80
        }, {
            field: "reservationTime",
            title: "预约时间",
            width: 80
        }, {
            field: "confirmedTotalVolume",
            title: "总体积(立方)",
            width: 60
        }, {
            field: "confirmedTotalWeight",
            title: "总重量(千克)",
            width: 60
        }, {
            field: "confirmedTotalItemQty",
            title: "总数量",
            width: 60
        }, {
            field: "confirmedTotalPackageQty",
            title: "总件数",
            width: 60
        }, {
            field: "city",
            title: "提货城市",
            width: 60
        },{
            field: "address",
            title: "提货地址",
            width: 200
        }];

        self.selectedOrder = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedOrder(dataItem);
        };
        self.canCreate = ko.computed(function () {
            return self.selectedOrder();
        });
        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length;i++){
                select.push("tr:eq("+i+")");
            }
            var grid = $(".k-grid").data("kendoGrid");
            grid.select(select.toString());
        };
        self.confirm=function(){
            var rows = self.widget().select();
            var dataItem = self.widget().dataItem(rows[0]);
            var url="dispatchAssign/confirmPickUpOrder/"+dataItem.pickupOrderId;
            modal("预约确认", url, {
                width: 500, height: 600, close: function () {
                    self.dataSource.query();
                }
            });
        };

        self.create = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            rows.each(function (i, row) {
                var dataItem = self.widget().dataItem(row);
                if (dataItem) {
                    selectedIds.push(dataItem.pickupOrderId);
                }
            });
            window.selectpickupOrderIds = selectedIds;
            var url = "#/dispatchAssign/pickUpOrderAssign";
            router.navigate(url);
        };

        self.search = function () {
            var clientId = $.trim(self.clientId());
            var address = $.trim(self.address());
            var pickupOrderNumber = $.trim(self.pickupOrderNumber());
            var filters = new Array();
            if (clientId) {
                filters.push({
                    field: "clientId",
                    operator: "eq",
                    value: clientId
                });
            }
            if (pickupOrderNumber) {
                filters.push({
                    field: "pickupOrderNumber",
                    operator: "contains",
                    value: pickupOrderNumber
                });
            }
            if (address) {
                filters.push({
                    field: "address",
                    operator: "contains",
                    value: address
                });
            }
            if (filters) {
                filters.push({
                    field: "processStatus",
                    operator: "eq",
                    value: 2
                });
                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            } else
                self.dataSource.filter([{field: "processStatus",operator: "eq",value: "2"}]);
        }
    };

    $(function () {
        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>