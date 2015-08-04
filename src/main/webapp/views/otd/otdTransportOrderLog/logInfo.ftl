<div id="transportOrder-log">
    <div>
        <button type="button" data-bind="kendoButton:addLog">
            <i class="fa fa-plus"></i> 添加日志
        </button>
        <button type="button" data-bind="kendoButton:modify,enable:canModify">
            <i class="fa fa-edit"></i> 修改日志
        </button>
    </div>
    <div id="details-grid-wrap">
        <div data-bind="kendoGrid: {widget: logsGrid, data: logsDataSource, sortable: true, editable:false, columns: logColumns ,selectable: 'row',change:onChange}"></div>
    </div>
</div>
<script>
    function transportLogModel() {
        var self = this;
        self.addLog = function () {
            var url = "otdTransportOrderLog/create/${otdTransportOrder.transportOrderId}";
            modal("添加运输订单日志", url, {
                width: 500, height: 600, close: function () {
                    self.logsDataSource.query();
                }
            });
        };
        self.modify = function () {
            var url = "otdTransportOrderLog/modify/"+self.selectedLog().id;
            modal("修改运输订单日志", url, {
                width: 400, height: 500, close: function () {
                    self.logsDataSource.query();
                }
            });
        };
        self.selectedLog = ko.observable();
        self.logsGrid = ko.observable();
        self.onChange = function () {
            var row = self.logsGrid().select()[0];
            var dataItem = self.logsGrid().dataItem(row);
            self.selectedLog(dataItem);
        };
        self.canModify = ko.computed(function () {
            return self.selectedLog() && self.selectedLog().isSystem;
        });
        self.logsDataSource = kendo.utils.createDataSource("id", "/otdTransportOrderLog/dataSource?transportOrderId=${otdTransportOrder.transportOrderId}");
        self.logColumns = [{
            title: "序号",
            template: function (dataItem) {
                return self.logsDataSource.indexOf(dataItem)+1;
            },
            width: 40
        },{
            field: "currentStatus",
            title: "订单状态",
            width: 120,
            values: commonData.orderStatus()
        }, {
            field: "createUserName",
            title: "操作人",
            width: 120
        }, {
            field: "operationDate",
            title: "操作时间",
            width: 120
        }, {
            field: "operationContent",
            title: "操作内容",
            width: 120
        }, {
            title: "是否异常",
            field: "isAbnormal",
            template: '<input type="checkbox" #= isAbnormal ? "checked=checked" : "" # disabled="disabled" />',
            width: 120
        }, {
            field: "remark",
            title: "备注",
            width: 120
        }];
    }

    $(function () {
        var logModel = new transportLogModel();
        ko.applyBindings(logModel, document.getElementById("transportOrder-log"));
    });
</script>