<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/rpt"><i class="fa fa-users"></i> 应付对账单</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="carrierId">承运商</label><@utils.koComboBox data="$root.carrierIds" value="carrierId" textField="name" valueField="carrierId"/>
        </div>
        <div>
            <label for="orderBeginDate">托运单开始日期</label><input data-bind="kendoDatePicker: orderBeginDate"/>
            <label for="orderEndDate">托运单结束日期</label><input data-bind="kendoDatePicker: orderEndDate"/>
        </div>
        <div>
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, toolbar:['excel','pdf'],editable:false, pageable: pageable, selectable: 'multiple,row'}"></div>
</div>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            var today = new Date();
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("carrierOrderNumber","/rptViewCheckPayable/getDataSource");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.carrierId("");
                self.orderBeginDate("");
                self.orderEndDate("");
            };

            self.columns = [{
                field: "carrierOrderNumber",
                title: "托运单号",
                width: 120
            }, {
                field: "createDate",
                title: "开单日期",
                width: 120
            }, {
                field: "destCity",
                title: "目的城市",
                width: 120
            },{
                field: "goodsName",
                title: "物料信息",
                width: 120
            },{
                field: "totalItemQuantity",
                title: "总件数",
                width: 120
            },{
                field: "totalPackageQuantity",
                title: "打包件数",
                width: 120
            },  {
                field: "totalVolume",
                title: "总体积",
                width: 120
            },  {
                field: "totalWeight",
                title: "总重量",
                width: 120
            }, {
                field: "payableFreight",
                title: "运费",
                width: 120
            },{
                field: "payablePickupfee",
                title: "提货费",
                width: 120
            },{
                field: "payableDelivery",
                title: "送货费",
                width: 120
            },{
                field: "payableOther",
                title: "其他费用",
                width: 120
            },{
                field: "payableCartfee",
                title: "LNET租车费",
                width: 120
            },{
                field: "totalAmount",
                title: "总费用",
                width: 120
            }];
            self.keyword = ko.observable();
            self.carrierId = ko.observable();
            self.carrierIds = kendo.utils.createDataSource("carrierId", "/scmCarrier/getDataSource");
            self.orderBeginDate = ko.observable(new Date(
                    today.getFullYear(),
                    today.getMonth()-1,
                    1
            ));
            self.orderEndDate = ko.observable(new Date(
                    today.getFullYear(),
                    today.getMonth(),
                    1
            ));
            var dateBegin=new Date($.trim(self.orderBeginDate()));
            var dateEnd=new Date($.trim(self.orderEndDate()));
            self.dataSource.filter({
                logic: "and",
                filters: [{
                    field: "beginDate",
                    operator: "gte",
                    value: dateBegin
                }, {
                    field: "endDate",
                    operator: "lt",
                    value: dateEnd
                }]
            });

            self.search = function () {
                var carrierId = $.trim(self.carrierId());
                var beginDate = $.trim(self.orderBeginDate());
                var endDate = $.trim(self.orderEndDate());
                var filters = new Array();
                if (carrierId) {
                    filters.push({field: "carrierId", operator: "eq", value: carrierId});
                }
                if (beginDate) {
                    var begin=new Date(beginDate);
                    filters.push({field: "beginDate", operator: "gte", value: begin});
                }
                if (endDate) {
                    var end = new Date(endDate);
                    filters.push({field: "endDate", operator: "lte", value: end});
                }
                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            };
        };
        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>