<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdPickupOrder"><i class="fa fa-users"></i> 提货订单</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="pickupOrderNumber">提货单号</label><input class="k-textbox" data-bind="value:pickupOrderNumber">
            <label for="clientId">客户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
            <label for="processStatus">处理状态</label><@utils.koDropDown data="$root.processStatuss" value="processStatus"/>
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
            <a data-role="button" href="#/otdPickupOrder/create"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:modify,enable:canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
            <button type="button" data-bind="kendoButton:dispatch, enable:canDispatch">
                <i class="fa fa-edit"></i> 审单
            </button>
            <#--<button type="button" data-bind="kendoButton:pick, enable:canPick">
                <i class="fa fa-edit"></i> 提货
            </button>
            <button type="button" data-bind="kendoButton:ok, enable:canOk">
                <i class="fa fa-edit"></i> 完成
            </button>-->
            <button type="button" data-bind="kendoButton:cancel, enable:canCancel">
                <i class="fa fa-edit"></i> 取消
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,
     <#--toolbar: ['excel', 'pdf'],excel:{fileName: '提货订单.xlsx',proxyURL:'/otdPickerOrderView/export',filterable: true},-->
     editable:false,toolbar:['excel','pdf'], pageable: pageable, selectable: 'row',change:onChange}"></div>
</div>


<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
            self.processStatuss = commonData.pickUpOrderStatus;
            self.dataSource = kendo.utils.createDataSource("pickupOrderId", "/otdPickerOrderView/getDataSource");
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
            self.processStatus = ko.observable("");
            self.pickupOrderNumber = ko.observable();
            self.address = ko.observable();
            self.clearFilter = function () {
                self.clientId("");
                self.processStatus("");
                self.pickupOrderNumber("");
                self.address("");
                self.dataSource.filter([]);
            };
            self.excel = {
                fileName: "提货订单.xlsx",
                proxyURL: "/otdPickerOrderView/export",
                filterable: true
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
                width: 60
            }, {
                field: "pickupOrderNumber",
                title: "提货单号",
                template: "<a href='\\#/otdPickupOrder/details/#= pickupOrderId #' >#= pickupOrderNumber# </a>",
                width: 60
            }, {
                field: "reservationTime",
                title: "预约时间",
                width: 80
            }, {
                field: "totalVolume",
                title: "总体积(立方)",
                width: 60
            }, {
                field: "totalWeight",
                title: "总重量(千克)",
                width: 60
            }, {
                field: "totalItemQty",
                title: "总数量",
                width: 60
            }, {
                field: "totalPackageQty",
                title: "总件数",
                width: 60
            }, {
                field: "processStatusName",
                title: "状态",
                width: 60
            }, {
                field: "city",
                title: "提货城市",
                width: 60
            }, {
                field: "address",
                title: "提货地址",
                width: 200
            }];
            self.selectedItem = ko.observable();
            self.onChange = function () {
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                self.selectedItem(dataItem);
            };
            self.canModify = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().processStatus == 1;
            });
            self.canDispatch = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().processStatus == 1;
            });
            self.canPick = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().processStatus == 2;
            });
            self.canOk = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().processStatus == 3;
            });
            self.canCancel = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().processStatus == 1;
            });
            self.dispatch = function () {
                var url = "#/otdPickupOrder/confirm/" + self.selectedItem().pickupOrderId;
                router.navigate(url);
                /*$.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdPickupOrder/dispatch",
                    data: ko.toJSON(self.selectedItem().pickupOrderId)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("审单成功", "success");
                        self.dataSource.filter([]);
                    }
                    else {
                        notify(result.message, "error");
                    }
                });*/
            };
            self.ok = function () {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdPickupOrder/ok",
                    data: ko.toJSON(self.selectedItem().pickupOrderId)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("操作成功", "success");
                        self.dataSource.filter([]);
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            };
            self.cancel = function () {
                if (!confirm("确定要取消吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdPickupOrder/cancel",
                    data: ko.toJSON(self.selectedItem().pickupOrderId)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("操作成功", "success");
                        self.dataSource.filter([]);
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            }
            self.pick = function () {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdPickupOrder/pick",
                    data: ko.toJSON(self.selectedItem().pickupOrderId)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("操作成功", "success");
                        self.dataSource.filter([]);
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            }
            self.modify = function () {
                var url = "#/otdPickupOrder/modify/" + self.selectedItem().pickupOrderId;
                router.navigate(url);
            };
            self.remove = function () {
                if (self.widget().select().length < 1) {
                    notify("请选择要删除的行", "error");
                    return;
                }
                if (!confirm("确定要删除吗？")) return;

                var selectedItems = [];
                var selectedIds = [];
                var rows = self.widget().select();
                rows.each(function (i, row) {
                    var dataItem = self.widget().dataItem(row);
                    if (dataItem) {
                        selectedItems.push(dataItem);
                        selectedIds.push(dataItem.pickupOrderId);
                    }
                });

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdPickupOrder/batchDeleteById",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        for (var i in selectedItems) self.dataSource.remove(selectedItems[i]);
                        notify("删除成功", "success");
                    }
                    else {
                        notify(result.message, "error");
                    }
                });

            };

            self.search = function () {
                var clientId = $.trim(self.clientId());
                var processStatus = $.trim(self.processStatus());
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
                if (processStatus) {
                    filters.push({
                        field: "processStatus",
                        operator: "eq",
                        value: processStatus
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
                    self.dataSource.filter({
                        logic: "and",
                        filters: filters
                    });
                } else
                    self.dataSource.filter([]);
            }
        };

        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>