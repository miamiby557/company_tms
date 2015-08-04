<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/rpt"><i class="fa fa-users"></i> 应收对账单</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="clientId">项目</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
        </div>
        <div>
            <label for="orderBeginDate">订单开始日期</label><input data-bind="kendoDatePicker: orderBeginDate"/>
            <label for="orderEndDate">订单结束日期</label><input data-bind="kendoDatePicker: orderEndDate"/>
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
            self.dataSource = kendo.utils.createDataSource("clientOrderNumber","/rptViewCheckIncome/getDataSource");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.clientId("");
                self.orderBeginDate("");
                self.orderEndDate("");
            };

            self.columns = [{
                field: "clientOrderNumber",
                title: "客户单号",
                width: 120
            }, {
                field: "orderDate",
                title: "订单日期",
                width: 120
            }, {
                field: "sentDate",
                title: "发货日期",
                width: 120
            },{
                field: "name",
                title: "客户名称",
                width: 120
            },{
                field: "contactAddress",
                title: "客户地址",
                width: 120
            },{
                field: "transportTypeName",
                title: "发运方式",
                width: 120
            }, {
                field: "calculateTypeName",
                title: "计费方式",
                width: 120
            },{
                field: "startCity",
                title: "起运地",
                width: 120
            },{
                field: "destCity",
                title: "目的城市",
                width: 120
            },{
                field: "carrierOrderNumber",
                title: "运单号",
                width: 120
            },{
                field: "receiveCompany",
                title: "收货单位",
                width: 120
            },{
                field: "receiveMan",
                title: "收货人",
                width: 120
            },  {
                field: "goodsCode",
                title: "物料信息",
                width: 120
            }, {
                field: "expectedDate",
                title: "预计到货日期",
                width: 120
            }, {
                field: "signDate",
                title: "实际到货日期",
                width: 120
            }, {
                field: "remark",
                title: "异常备注",
                width: 120
            },  {
                field: "totalItemQuantity",
                title: "总件数",
                width: 120
            }, {
                field: "totalPackageQuantity",
                title: "打包数",
                width: 120
            },{
                field: "totalVolume",
                title: "总体积",
                width: 120
            },{
                field: "totalWeight",
                title: "总重量",
                width: 120
            },{
                field: "receivableFreightAmount",
                title: "运费",
                width: 120
            },{
                field: "receivablePickupfeeAmount",
                title: "提货费",
                width: 120
            },{
                field: "receivableDeliveryAmount",
                title: "送货费",
                width: 120
            },{
                field: "receivableOtherAmount",
                title: "其他费用",
                width: 120
            },{
                field: "receivablePackingAmount",
                title: "包装费",
                width: 120
            },{
                field: "receivablePremiumAmount",
                title: "保险费",
                width: 120
            },{
                field: "totalAmount",
                title: "总费用",
                width: 120
            }];
            self.keyword = ko.observable();
            self.clientId = ko.observable();
            self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
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
                var clientId = $.trim(self.clientId());
                var beginDate = $.trim(self.orderBeginDate());
                var endDate = $.trim(self.orderEndDate());
                var filters = new Array();
                if (clientId) {
                    filters.push({field: "clientId", operator: "eq", value: clientId});
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