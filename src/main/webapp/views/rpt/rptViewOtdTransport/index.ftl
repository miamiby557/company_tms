<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/rpt"><i class="fa fa-users"></i> 运作报表</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="clientId">项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
            <label for="keyword">&nbsp;&nbsp;&nbsp;客户订单号</label><input class="k-textbox" data-bind="value:keyword">
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
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, toolbar:['excel','pdf'],editable:false, pageable: pageable, selectable: 'multiple,row',selectable: 'row', change:onChange}"></div>
</div>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            var today = new Date();
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("lnetOrderNumber","/rptViewOtdTransport/getDataSource");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.keyword("");
                self.clientId("");
                self.orderBeginDate("");
                self.orderEndDate("");
            };
            self.selected = ko.observable();
            self.onChange = function () {
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                self.selected(dataItem);
            };
            self.columns = [{
                title:"序号",
                template: function (dataItem) {
                    return self.dataSource.indexOf(dataItem)+1;
                },
                width: 40
            },{
                title:"发货方信息",
                headerAttributes: {
                    "class": "table-header-cell",
                    style: "text-align: center; font-size: 15px"
                },
                columns:[{field: "clientName",
                    title: "客户名称",
                    width: 120},{
                    field: "lnetOrderNumber",
                    title: "LNET运单编号",
                    width: 120
                }, {
                    field: "clientOrderNumber",
                    title: "客户订单号",
                    width: 120
                }, {
                    field: "orderDate",
                    title: "订单日期",
                    width: 120
                },{
                    field: "receiveCompany",
                    title: "收货单位",
                    width: 120
                },{
                    field: "receiveMan",
                    title: "收货人",
                    width: 120
                },{
                    field: "startCity",
                    title: "起运地",
                    width: 120
                },{
                    field: "destCity",
                    title: "到达地",
                    width: 120
                },{
                    field: "receiveAddress",
                    title: "收货地址",
                    width: 240
                },{
                    field: "transportTypeName",
                    title: "运输方式",
                    width: 120
                },{
                    field: "clientBillingCycle",
                    title: "结算方式",
                    width: 60
                },{
                    field: "confirmedItemQuantity",
                    title: "订单数量",
                    width: 60
                },{
                    field: "confirmedPackageQuantity",
                    title: "打包件数",
                    width: 60
                }]
            },{
                title:"应收信息",
                headerAttributes: {
                    "class": "table-header-cell",
                    style: "text-align: center; font-size: 15px"
                },
                columns:[{
                    field: "confirmedWeight",
                    title: "应收公斤",
                    width: 60
                },{
                    field: "confirmedVolume",
                    title: "应收立方",
                    width: 60
                },{
                    field: "receivableUnitPrice",
                    title: "应收单价",
                    width: 60
                }, {
                    field: "calculateTypeName",
                    title: "应收计费方式",
                    width: 60
                },{
                    field: "receivableMinimumFee",
                    title: "最低收费",
                    width: 60
                },{
                    field: "receivableFreight",
                    title: "直接运费",
                    width: 60
                },{
                    field: "receivablePickupfee",
                    title: "提货费",
                    width: 60
                },{
                    field: "receivableCartfee",
                    title: "包装费用",
                    width: 60
                },{
                    field: "receivableDelivery",
                    title: "送货费",
                    width: 60
                },{
                    field: "receivableUpstairs",
                    title: "上楼费",
                    width: 120
                },{
                    field: "receivablePremium",
                    title: "保险费",
                    width: 120
                },{
                    field: "receivableOther",
                    title: "其他费用",
                    width: 60
                },{
                    field: "totalAmount",
                    title: "应收合计",
                    width: 60
                },{
                    field: "transportOrderReportRemark",
                    title: "应收费用说明",
                    width: 120
                }
                ]},{
                field: "carrierName",
                title: "承运公司",
                width: 120
            },{
                field: "sendNumber",
                title: "托运单号",
                width: 120
            },{
                field: "carrierSentDate",
                title: "发货日期",
                width: 120
            },{
                title:"应付信息",
                headerAttributes: {
                    "class": "table-header-cell",
                    style: "text-align: center; font-size: 15px"
                },
                columns:[{
                    field: "sendWeight",
                    title: "应付公斤",
                    width: 60
                },{
                    field: "sendVolume",
                    title: "应付立方",
                    width: 60
                },{
                    field: "carrierCalculateTypeName",
                    title: "计费方式",
                    width: 60
                },{
                    field: "payableUnitPrice",
                    title: "计费单价",
                    width: 60
                },{
                    field: "carrierBillingCycle",
                    title: "付款方式",
                    width: 60
                },{
                    field: "payableFreight",
                    title: "直接运费",
                    width: 60
                },{
                    field: "payableDelivery",
                    title: "送货费",
                    width: 60
                },{
                    field: "payablePickupfee",
                    title: "提货费",
                    width: 60
                },{
                    field: "payableUpstairs",
                    title: "上楼费",
                    width: 60
                },{
                    field: "payableOther",
                    title: "其他费用",
                    width: 60
                },{
                    field: "sendAmount",
                    title: "合计",
                    width: 60
                },{
                    field: "carrierOrderReportRemark",
                    title: "应付费用说明",
                    width: 120
                }/*,{
                    field: "payableFreight",
                    title: "直接运费",
                    width: 120
                },{
                    field: "payableDelivery",
                    title: "送货费",
                    width: 120
                },{
                    field: "payablePickupfee",
                    title: "提货费",
                    width: 120
                },{
                    field: "payableUpstairs",
                    title: "上楼费",
                    width: 120
                },{
                    field: "payableOther",
                    title: "其他费用",
                    width: 120
                },{
                    field: "sendAmount",
                    title: "合计",
                    width: 120
                },{
                    field: "carrierOrderReportRemark",
                    title: "应付费用说明",
                    width: 120
                }*/
                ]},{
                field: "crossProfit",
                title: "毛利",
                width: 60,
                template: function (dataItem) {
                    if (dataItem.crossProfit < 0 ) {
                        return "<span style='color: #ff0000;font-weight: bold;'>" + dataItem.crossProfit + "</span>";
                    }
                    if(dataItem.crossProfit>0){
                        return dataItem.crossProfit;
                    }
                    return '';
                }
            },{
                field:"carRental",
                title: "租车费",
                value:0,
                width: 60
            },{
                field:"handlingCharge",
                title: "装卸费",
                value:0,
                width: 60
            },{
                field:"netMargin",
                title: "毛利净额",
                value:0,
                width: 60
            },{
                field:"goodsValue",
                title: "货物价值",
                value:0,
                width: 60
            },{
                field: "remark",
                title: "备注",
                width: 120
            }];

            self.keyword = ko.observable();
            self.clientId = ko.observable();
            self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");

            self.orderBeginDate = ko.observable(new Date(
                    today.getFullYear(),
                    today.getMonth(),
                    1
            ));
            self.orderEndDate = ko.observable(today);
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
                    operator: "lte",
                    value: new Date(dateEnd.setDate(dateEnd.getDate()+1))
                }]
            });

            self.search = function () {
                var word = $.trim(self.keyword());
                var clientId = $.trim(self.clientId());
                var beginDate = $.trim(self.orderBeginDate());
                var endDate = $.trim(self.orderEndDate());
                var filters = new Array();
                if (word) {
                    filters.push({field: "clientOrderNumber", operator: "contains", value: word});
                }
                if (clientId) {
                    filters.push({field: "clientId", operator: "eq", value: clientId});
                }
                if (beginDate) {
                    var begin=new Date(beginDate);
                    filters.push({field: "beginDate", operator: "gte", value: begin});
                }
                if (endDate) {
                    var end = new Date(endDate);
                    filters.push({field: "endDate", operator: "lte",
                        value: new Date(end.setDate(end.getDate()+1))});
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