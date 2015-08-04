<#import "/utils/query.ftl" as query >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/dispatchVehicle"><i class="fa fa-users"></i> 车辆管理</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <@query.koText field="vehicleNumber" label="车牌号码"></@query.koText>
            <@query.koDropDown data="$root.vehicleTypeList" field="vehicleType" label="车牌类型"/>
            <@query.koText field="maxWeight" label="吨位"></@query.koText>
            <@query.koDropDown data="$root.statusList" field="status" label="状态"/>
        </div>
        <div>
            <@query.koText field="vehicleSize" label="车厢尺寸"></@query.koText>
            <@query.koText field="driver" label="司机"/>
            <@query.koDatePicker field="compactStartDate" label="合同开始时间"/>
            <@query.koDatePicker field="compactEndDate" label="合同结束时间"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <a data-role="button" href="#/dispatchVehicle/create"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:$root.modify,enable:$root.canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
            <button type="button" data-bind="kendoButton:$root.delete,enable:$root.canDelete">
                <i class="fa fa-close"></i> 删除
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,
     editable:false,toolbar:['excel','pdf'], pageable: pageable, selectable: 'row',change:onChange}"></div>
</div>


<script>

    var GridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.statusList = commonData.dispatchAssignvehicleStatus;
        self.vehicleTypeList = commonData.vehicleType;
        self.dataSource = kendo.utils.createDataSource("vehicleId", "/dispatchVehicle/getDataSource");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.selectAll = function () {
            var select = new Array();
            for (var i = 1; i <= self.dataSource.data().length; i++) {
                select.push("tr:eq(" + i + ")");
            }
            var grid = $(".k-grid").data("kendoGrid");
            grid.select(select.toString());
        }
        self.vehicleNumber = ko.observable();
        self.vehicleType = ko.observable("");
        self.status = ko.observable();
        self.maxWeight = ko.observable();
        self.vehicleSize = ko.observable();
        self.driver = ko.observable();
        self.compactStartDate = ko.observable();
        self.compactEndDate = ko.observable();
        self.clearFilter = function () {
            self.vehicleNumber("");
            self.vehicleType("");
            self.status("");
            self.maxWeight("");
            self.vehicleSize("");
            self.driver("");
            self.compactStartDate("");
            self.compactEndDate("");
            self.dataSource.filter([]);
        };
        self.columns = [{
            title: "序号",
            template: function (dataItem) {
                return self.dataSource.indexOf(dataItem) + 1;
            },
            width: 20
        },  {
            field: "vehicleNumber",
            title: "车牌号码",
            template: "<a href='\\#/dispatchVehicle/detail/#= vehicleId #' >#= vehicleNumber# </a>",
            width: 60
        }, {
            field: "vehicleType",
            title: "车辆类型",
            width: 60,
            values: commonData.vehicleType()
        },{
            field: "driver",
            title: "司机",
            width: 70
        }, {
            field: "driverPhone",
            title: "司机电话",
            width: 110
        }, {
            field: "maxWeight",
            title: "吨位",
            width: 60
        }, {
            field: "vehicleSize",
            title: "车厢尺寸",
            width: 110
        }, {
            field: "compactStartDate",
            title: "合同开始时间",
            template: function (dataItem) {
                if (dataItem.compactStartDate)  return kendo.toString(kendo.parseDate(dataItem.compactStartDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
            width: 80
        },{
            field: "compactEndDate",
            title: "合同结束时间",
            template: function (dataItem) {
                if (dataItem.compactEndDate)  return kendo.toString(kendo.parseDate(dataItem.compactEndDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
            width: 80
        },{
            field: "vehicleContent",
            title: "车辆信息",
            width: 80
        },   {
            field: "status",
            title: "状态",
            width: 60,
            values:commonData.vehicleType()
        },{
            field: "remark",
            title: "备注",
            width: 80
        }];
        self.selectedItem = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedItem(dataItem);
        };
        self.canModify = ko.computed(function () {
            return self.selectedItem();
        });
        self.canDelete = ko.computed(function () {
            return self.selectedItem();
        });
        self.modify = function () {
            var url = "#/dispatchVehicle/modify/" + self.selectedItem().vehicleId;
            router.navigate(url);
        };
        self.delete = function () {
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
                    selectedIds.push(dataItem.vehicleId);
                }
            });

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/dispatchVehicle/batchDeleteById",
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
            var vehicleNumber = $.trim(self.vehicleNumber());
            var vehicleType = $.trim(self.vehicleType());
            var status = $.trim(self.status());
            var maxWeight = $.trim(self.maxWeight());
            var vehicleSize = $.trim(self.vehicleSize());
            var driver = $.trim(self.driver());
            var compactStartDate = $.trim(self.compactStartDate());
            var compactEndDate = $.trim(self.compactEndDate());
            var filters = new Array();
            if (vehicleSize) {
                filters.push({
                    field: "vehicleSize",
                    operator: "contains",
                    value: vehicleSize
                });
            }
            if (driver) {
                filters.push({
                    field: "driver",
                    operator: "contains",
                    value: driver
                });
            }
            if (compactStartDate) {
                filters.push({
                    field: "compactStartDate",
                    operator: "gte",
                    value: new Date(compactStartDate)
                });
            }
            if (compactEndDate) {
                filters.push({
                    field: "compactEndDate",
                    operator: "lte",
                    value: new Date(compactEndDate)
                });
            }
            if (vehicleNumber) {
                filters.push({
                    field: "vehicleNumber",
                    operator: "contains",
                    value: vehicleNumber
                });
            }
            if (status) {
                filters.push({
                    field: "status",
                    operator: "eq",
                    value: status
                });
            }
            if (vehicleType) {
                filters.push({
                    field: "vehicleType",
                    operator: "eq",
                    value: vehicleType
                });
            }
            if (maxWeight) {
                filters.push({
                    field: "maxWeight",
                    operator: "contains",
                    value: maxWeight
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