<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/rpt"><i class="fa fa-users"></i> 物料跟踪报表</a></li>
</ol>

<div id="grid-wrap-productTracking">
    <div class="grid-toolbar">
        <div>
            <label for="clientId">项目</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
            <label for="keyword">&nbsp;&nbsp;&nbsp;客户订单号</label><input class="k-textbox" data-bind="value:keyword">
        </div>
        <div>
            <label for="carrierId">承运商</label><input class="k-textbox" data-bind="value:carrierId">
            <label for="carrierNo">&nbsp;&nbsp;&nbsp;托运单号</label><input class="k-textbox" data-bind="value:carrierNo">
        </div>
        <div>
            <label for="orderBeginDate">订单起始日期</label><input data-bind="kendoDatePicker:orderBeginDate">
            <label for="orderEndDate">订单结束日期</label><input data-bind="kendoDatePicker:orderEndDate">
        </div>
        <div>
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, toolbar:['excel','pdf'],editable:false, pageable: pageable, selectable: 'multiple,row',selectable: 'row',change:onChange}"></div>
</div>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            var today=new Date();
            self.carrierId=ko.observable();
            self.carrierNo=ko.observable();
            self.orderBeginDate=ko.observable();
            self.orderEndDate=ko.observable();
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("logId","/rptViewProductTracking/getDataSource");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.keyword("");
                self.clientId("");
                self.carrierId("");
                self.carrierNo("");
                self.orderBeginDate("");
                self.orderEndDate("");
            };
            self.onChange = function () {
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                self.selected(dataItem);
            };
            self.selected = ko.observable();
            self.columns = [{
                field: "clientName",
                title: "项目名称",
                width: 120
            },{
                field: "orderDate",
                title: "订单日期",
                width: 120
            },{
                field: "sentDate",
                title: "发货日期",
                width: 120
            }, {
                field: "expectedDate",
                title: "预计到货日期",
                width: 120
            }, {
                field: "destCity",
                title: "目的地",
                width: 120
            },{
                field: "carrierName",
                title: "承运商",
                width: 120
            },{
                field: "carrierOrderNumber",
                title: "承运商单号",
                width: 120
            },{
                field: "operationContent",
                title: "跟踪信息",
                width: 180
            },{
                field: "operationDate",
                title: "操作时间",
                width: 120
            },{
                field: "isAbnormal",
                title: "是否异常",
                width: 120,
                template: function (dataItem) {
                    if(dataItem.isAbnormal &&dataItem.isAbnormal==1){
                        return "异常";
                    }else{
                        return "正常";
                    }
                }
            },{
                field: "remark",
                title: "备注",
                width: 120
            },{
                field: "signDate",
                title: "签收日期",
                width: 120
            },{
                field: "signMan",
                title: "签收人",
                width: 120
            },{
                field: "clientOrderNumber",
                title: "订单号",
                width: 120
            },{
                field: "viceClientOrderNumber",
                title: "跟进单号",
                width: 120
            },{
                field: "transportTypeName",
                title: "运输方式",
                width: 120
            }, {
                field: "handoverTypeName",
                title: "送货方式",
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
                field: "receivePhone",
                title: "联系电话",
                width: 120
            },{
                field: "goodsType",
                title: "物料大类",
                width: 180
            },{
                field: "goodsCode",
                title: "物料小类",
                width: 180
            },{
                field: "goodsName",
                title: "物料明细",
                width: 180
            },{
                field: "totalItemQuantity",
                title: "物料件数",
                width: 60
            },{
                field: "totalPackageQuantity",
                title: "打包件数",
                width: 60
            },{
                field: "totalVolume",
                title: "货物体积",
                width: 60
            },{
                field: "totalWeight",
                title: "货物重量",
                width: 60
            },{
                field: "marketClientNumber",
                title: "商超单号",
                width: 120
            },{
                field: "receiveAddress",
                title: "收货地址",
                width: 180
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
                var carrierId= $.trim(self.carrierId());
                var carrierNo= $.trim(self.carrierNo());
                var beginDate = $.trim(self.orderBeginDate());
                var endDate = $.trim(self.orderEndDate());
                var filters = new Array();
                if (word) {
                    filters.push({field: "clientOrderNumber", operator: "contains", value: word});
                }
                if (clientId) {
                    filters.push({field: "clientId", operator: "eq", value: clientId});
                }
                if(carrierId){
                    filters.push({field:"carrierName",operator:"contains",value:carrierId})
                }
                if(carrierNo){
                    filters.push({field:"carrierOrderNumber",operator:"contains",value:carrierNo})
                }
                if (beginDate) {
                    var begin=new Date(beginDate);
                    filters.push({field: "beginDate", operator: "gte", value: begin});
                }
                if (endDate) {
                    var end = new Date(endDate);
                    filters.push({field: "endDate", operator: "lte",value:end});
                    //value: new Date(end.setDate(end.getDate()+1))});
                }

                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            };
        };
        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap-productTracking"));
    });
</script>