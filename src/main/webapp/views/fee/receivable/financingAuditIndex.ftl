<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/feeOrderReceivableView/financingAudit"><i class="fa fa-users"></i> 应收财务审核</a></li>
</ol>
<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="clientId">客户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
            <label for="clientOrderNumber">客户单号</label><input class="k-textbox" data-bind="value:clientOrderNumber">
            <label for="receiveMan">收货人</label><input class="k-textbox" data-bind="value:receiveMan">
            <label for="status">审核状态</label><@utils.koDropDown data="$root.statusList" value="status"/>
        </div>
        <div>
            <label for="orderBeginDate">订单日期</label><input data-bind="kendoDatePicker:{value:orderBeginDate, max:orderEndDate}">
            <label for="orderEndDate" class="nospace">至</label><input data-bind="kendoDatePicker:{value:orderEndDate, min: orderBeginDate}">
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索 </button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空 </button>
        </div>
        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <button type="button" data-bind="kendoButton:pass">
                <i class="fa fa-edit"></i> 批量通过
            </button>
            <#--<button type="button" data-bind="kendoButton:back">
                <i class="fa fa-edit"></i> 拒绝
            </button>
            <button type="button" data-bind="kendoButton:cancel">
                <i class="fa fa-edit"></i> 撤销
            </button>-->
            <button type="button" data-bind="kendoButton:finish">
                <i class="fa fa-edit"></i> 批量完结
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true,toolbar:['excel','pdf'], groupable: true, editable:false, pageable: pageable, selectable:'multiple, row'}"></div>
</div>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("orderReceivableId", "/feeOrderReceivableView/getFinancingAuditDataSource");
            var filters=new Array();
            filters.push({
                field: "status",
                operator: "eq",
                value: "3"
            });
            self.dataSource.filter({
                logic: "and",
                filters: filters
            });
            self.selectAll= function(){
                var select = new Array();
                for(var i = 1;i<=self.dataSource.data().length;i++){
                    select.push("tr:eq("+i+")");
                }
                var grid = $(".k-grid").data("kendoGrid");
                grid.select(select.toString());
            }
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.clientOrderNumber("");
                self.clientId("");
                self.status("");
                self.orderBeginDate("");
                self.orderEndDate("");
                self.receiveMan("");
                self.dataSource.filter([]);
            };

            self.columns = [{
                title: "序号",
                template: function (dataItem) {
                    return self.dataSource.indexOf(dataItem)+1;
                },
                width: 40
            },{
                field: "clientName",
                title: "客户",
                width:80
            },{
                field: "clientOrderNumber",
                title: "客户单号",
                template: "<a href='\\#/feeOrderReceivable/financingAudit/#= orderReceivableId #' >#= clientOrderNumber# </a>",
                width:120
            }, {
                field: "orderDate",
                title: "订单日期",
                width:80,
                template: function (dataItem) {
                    return kendo.toString(kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd")
                }

            },{
                field: "startCity",
                title: "始发城市",
                width:80
            }, {
                field: "destCity",
                title: "目的城市",
                width: 80
            }, {
                field: "transportType",
                title: "运输方式",
                width: 80,
                values:ko.toJS(commonData.transportType)
            }, {
                field: "receiveMan",
                title: "收货人",
                width: 80
            },{
                field: "totalAmount",
                title: "应收",
                width:60
            },{
                field: "payAmount",
                title: "应付",
                width:60
            },{
                title: "毛利",
                width:60,
                template:function (dataItem) {
                    if(dataItem.payAmount && dataItem.totalAmount!=0){
                        var scale = formatFloat(dataItem.totalAmount-dataItem.payAmount,2);
                        if(scale<0){
                            return "<span style='color: #ff0000;font-weight: bold;'>"+scale+"</span>";
                        }else{
                            return "<span style='color: #0000FF'>"+scale+"</span>";
                        }
                    }
                    return '';
                }
            },{
                title: "毛利率",
                width:60,
                template:function (dataItem) {
                    if(dataItem.payAmount && dataItem.totalAmount!=0){
                        var scale = formatFloat((dataItem.totalAmount-dataItem.payAmount)*100/dataItem.payAmount,2);
                        if(scale<0){
                            return "<span style='color: #ff0000;font-weight: bold;'>"+scale+"%</span>";
                        }else{
                            return "<span style='color: #0000FF'>"+scale+"%</span>";
                        }
                    }
                    return '';
                }
            },{
                field: "statusName",
                title: "审核状态",
                width:80
            }];
            self.pass=function(){
                var selectedIds = [];
                var rows = self.widget().select();
                if(rows.length==0){
                    notify("请选择批量操作的应收费用！","error");
                    return;
                }
                for(var i=0;i<rows.length;i++){
                    var row = rows[i];
                    var dataItem = self.widget().dataItem(row);
                    var status=dataItem.status;
                    if(status!=3){
                        notify("只能选择项目已审核的应收费用！","error");
                        return;
                    }else {
                        selectedIds.push(dataItem.orderReceivableId);
                    }
                }

                if (!confirm("确定批量审核通过吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderReceivable/financingPass",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("批量审核成功！", "success");
                        self.dataSource.query();
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            }

            self.back=function(){
                var selectedIds = [];
                var rows = self.widget().select();
                if(rows.length==0){
                    notify("请选择批量操作的应收费用！","error");
                    return;
                }
                for(var i=0;i<rows.length;i++){
                    var row = rows[i];
                    var dataItem = self.widget().dataItem(row);
                    var status=dataItem.status;
                    if(status!=3){
                        notify("只能选择项目已审核的应收费用！","error");
                        return;
                    }else {
                        selectedIds.push(dataItem.orderReceivableId);
                    }
                }

                if (!confirm("确定批量审核拒绝吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderReceivable/back",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("批量拒绝成功！", "success");
                        self.dataSource.query();
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            }
            self.finish=function(){
                var selectedIds = [];
                var rows = self.widget().select();
                if(rows.length==0){
                    notify("请选择批量操作的应收费用！","error");
                    return;
                }
                for(var i=0;i<rows.length;i++){
                    var row = rows[i];
                    var dataItem = self.widget().dataItem(row);
                    var status=dataItem.status;
                    if(status!=4){
                        notify("只能选择财务已审核的应收费用！","error");
                        return;
                    }else {
                        selectedIds.push(dataItem.orderReceivableId);
                    }
                }

                if (!confirm("确定批量审核完结吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderReceivable/financingFinish",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("批量操作成功！", "success");
                        self.dataSource.query();
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            }
            self.cancel=function(){
                var selectedIds = [];
                var rows = self.widget().select();
                if(rows.length==0){
                    notify("请选择批量操作的应收费用！","error");
                    return;
                }
                for(var i=0;i<rows.length;i++){
                    var row = rows[i];
                    var dataItem = self.widget().dataItem(row);
                    var status=dataItem.status;
                    if(status!=4){
                        notify("只能选择财务已审核的应收费用！","error");
                        return;
                    }else {
                        selectedIds.push(dataItem.orderReceivableId);
                    }
                }

                if (!confirm("确定批量撤销吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderReceivable/back",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("批量撤销成功！", "success");
                        self.dataSource.query();
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            }
            self.clientOrderNumber = ko.observable();
            self.clientId = ko.observable();
            self.status = ko.observable(3);
            self.orderBeginDate = ko.observable();
            self.orderEndDate = ko.observable();
            self.receiveMan=ko.observable();
            self.statusList = commonData.feeAuditStatus;
            self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
            self.search = function () {
                var clientOrderNumber = $.trim(self.clientOrderNumber());
                var clientId = $.trim(self.clientId());
                var status = $.trim(self.status());
                var orderEndDate = $.trim(self.orderEndDate());
                var orderBeginDate = $.trim(self.orderBeginDate());
                var receiveMan=$.trim(self.receiveMan());
                var filters = new Array();
                if (clientOrderNumber) {
                    filters.push({
                        field: "clientOrderNumber",
                        operator: "contains",
                        value: clientOrderNumber
                    });
                }
                if (clientId) {
                    filters.push({
                        field: "clientId",
                        operator: "eq",
                        value: clientId
                    });
                }
                if (status) {
                    filters.push({
                        field: "status",
                        operator: "eq",
                        value: status
                    });
                }
                if (receiveMan) {
                    filters.push({
                        field: "receiveMan",
                        operator: "contains",
                        value: receiveMan
                    });
                }
                if (orderBeginDate) {
                    filters.push({
                        field: "orderDate",
                        operator: "gte",
                        value: new Date(orderBeginDate)
                    });
                }
                if (orderEndDate) {
                    var endDate=new Date(orderEndDate);
                    filters.push({
                        field: "orderDate",
                        operator: "lt",
                        value: new Date(endDate.setDate(endDate.getDate()+1))
                    });
                }

                if (filters.length > 0)
                    self.dataSource.filter({
                        logic: "and",
                        filters: filters
                    });
                else
                    self.dataSource.filter([]);
            }
        };

        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>