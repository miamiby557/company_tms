<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdTransportOrderView"><i class="fa fa-users"></i> 运输订单</a></li>
    <li><a href="#/otdTransportOrder/importOrder"><i class="fa fa-upload"></i> 导入运输订单</a></li>
    <li><i class="fa fa-users"></i> 导入结果</li>
</ol>
<div id="grid-wrap">
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns}"></div>
</div>
<script>
    var GridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("rowIndex", "/otdTransportOrder/getImportResult");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.columns = [{
            field: "rowIndex",
            title: "EXCEL行号",
            template: function (dataItem) {
                var rowIndex= parseInt(dataItem.rowIndex)+2;
                return "第"+rowIndex+"行";
            },
            width: 80
        }, {
            field: "importResult",
            title: "导入结果",
            width: 80
        }, {
            field: "failReason",
            title: "导入失败原因",
            width: 400
        }];
    };
    $(function () {
        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>