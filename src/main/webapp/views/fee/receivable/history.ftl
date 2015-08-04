<div id="grid-wrap">
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: false, groupable: false,editable:false, pageable: pageable}"></div>
</div>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("id", "/feeOrderReceiveHistoryView/getDataSourceById?orderReceivableId=${orderReceivableId}");
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
                field: "receivableFreight",
                title: "应收_运费",
                width:80
            }, {
                field: "receivablePickupfee",
                title: "应收_提货费",
                width:80
            },{
                field: "receivableDelivery",
                title: "应收_送货费",
                width:80
            },{
                field: "receivableUpstairs",
                title: "应收_上楼费",
                width:80
            },{
                field: "receivablePacking",
                title: "应收_包装费",
                width:80
            }, {
                field: "receivablePremium",
                title: "应收_保险费",
                width:80
            },{
                field: "receivableOther",
                title: "应收_其他费用",
                width:80
            },{
                title: "应收_总计",
                template: function (dataItem) {
                    return dataItem.receivableFreight+dataItem.receivablePickupfee+dataItem.receivableDelivery+dataItem.receivablePacking+dataItem.receivablePremium+dataItem.receivableOther+dataItem.receivableUpstairs;
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