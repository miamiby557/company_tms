<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdCarrierOrder"><i class="fa fa-users"></i> 托运单</a></li>
    <li><a href="#/otdCarrierOrderLog/index/${carrierOrderId}"><i class="fa fa-list"></i> 托运单日志</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <a data-role="button" href="#/otdCarrierOrder"> <i class="fa fa-close"></i> 返回 </a>
            <a data-role="button" href="#/otdCarrierOrderLog/create/${carrierOrderId}"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:remove">
                <i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" data-bind="kendoButton:modify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
        <div>
            <label for="currentStatus">当前订单状态</label>
            <@utils.koDropDown data="currentStatuss" value="currentStatus" />
            <label for="operationDate">操作时间</label>
            <input type="date" name="startDate" id="startDate" data-bind="kendoDateTimePicker:startDate" >  &nbsp;&nbsp;—&nbsp;
            <input type="date" name="endDate" id="endDate" data-bind="kendoDateTimePicker:endDate" >
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,toolbar:['excel','pdf'], editable:false, pageable: pageable, selectable: 'multiple,row'}"></div>
</div>


<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("carrierOrderLogId", "/otdCarrierOrderLog/dataSource?carrierOrderId=${carrierOrderId}");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};

            self.currentStatus = ko.observable("");
            self.startDate = ko.observable();
            self.endDate = ko.observable();
            self.currentStatuss = commonData.orderStatus;
            self.clearFilter = function () {
                self.currentStatus("");
                self.startDate("");
                self.endDate("");
                self.dataSource.filter([]);
            };

            self.columns = [{
                title: "序号",
                template: function (dataItem) {
                    return self.dataSource.indexOf(dataItem)+1;
                },
                width: 40
            },{
                field: "currentStatus",
                title: "当前状态",
                width: 120,
                values:ko.toJS(commonData.orderStatus)
            },  {
                field: "operationDate",
                format:"{0:yyyy-MM-dd HH:mm:ss}",
                title: "操作时间",
                width: 120
            },  {
                field: "operationContent",
                title: "操作内容",
                width: 120
            },{
                title: "是否公开",
                field: "isPublic",
                template: '<input type="checkbox" #= isPublic ? "checked=checked" : "" # disabled="disabled" />',
                width: 120
            },{
                field: "remark",
                title: "备注",
                width: 120
            }];
            self.modify = function () {
                if (self.widget().select().length != 1) {
                    notify("请选择要修改的一行", "error");
                    return;
                }
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                var url = "#/otdCarrierOrderLog/modify/" + dataItem.carrierOrderLogId;
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
                        selectedIds.push(dataItem.carrierOrderLogId);
                    }
                });

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdCarrierOrderLog/batchDeleteById",
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
                var currentStatus = $.trim(self.currentStatus());
                var startDate = $.trim(self.startDate());
                var endDate = $.trim(self.endDate());
                var filter = new Array();
                if (currentStatus){
                    filter.push({
                        logic: "and",
                        field: "currentStatus",
                        operator: "eq",
                        value: currentStatus
                    });
                }if (startDate){
                    filter.push({
                        logic: "and",
                        field: "operationDate",
                        operator: "gte",
                        value: new Date(startDate)
                    });
                }if (endDate){
                    filter.push({
                        logic: "and",
                        field: "operationDate",
                        operator: "lte",
                        value: new Date(endDate)
                    });
                }
                self.dataSource.filter({
                    logic: "and",
                    filters: filter
                });
            }
        };

        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>