<div id="grid-wrap">
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: false, groupable: false,editable:false, pageable: pageable}"></div>
</div>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("id", "/feeOrderPayHistoryView/getDataSourceById?orderPayableId=${orderPayableId}");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.columns = [{
                field: "version",
                title: "版本号",
                template: function (dataItem) {
                    if (parseInt(dataItem.version)==0)
                        return "计算值";
                    return "第"+dataItem.version+"次确认";
                },
                width:80
            },{
                field: "payableFreight",
                title: "应付_运费",
                width:80
            }, {
                field: "payablePickupfee",
                title: "应付_提货费",
                width:80
            },{
                field: "payableDelivery",
                title: "应付_送货费",
                width:80
            },{
                field: "payableUpstairs",
                title: "应付_上楼费",
                width:80
            },{
                field: "payableCartfee",
                title: "应付_租车费",
                width:80
            },{
                field: "payableOther",
                title: "应付_其他费用",
                width:80
            },{
                title: "应付_总计",
                template: function (dataItem) {
                    return dataItem.payableFreight+dataItem.payablePickupfee+dataItem.payableDelivery+dataItem.payableCartfee+dataItem.payableOther+dataItem.payableUpstairs;
                },
                width:80
            }, {
                field: "operationUserName",
                title: "操作人",
                width:120
            },{
                field: "operationDate",
                title: "操作日期",
                template: function (dataItem) {
                    if(dataItem.operationDate)
                        return kendo.toString(kendo.parseDate(dataItem.operationDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd")
                    return '';
                },
                width:80
            }];
        };

        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>