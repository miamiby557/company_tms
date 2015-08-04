<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/feeOrderReceivableView"><i class="fa fa-users"></i> 应收确认</a></li>
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
            <label for="orderEndDate">至</label><input data-bind="kendoDatePicker:{value:orderEndDate, min: orderBeginDate}">
            <label for="feeSaveMark">是否已保存</label><@utils.koDropDown data="$root.feeSaveMarkList" value="feeSaveMark"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索 </button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空 </button>
        </div>
        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <button type="button" data-bind="kendoButton:confirmBatch">
                <i class="fa fa-edit"></i> 批量确认
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,toolbar:['excel','pdf'], editable:false, pageable: pageable,selectable:'multiple, row'}"></div>
</div>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("orderReceivableId", "/feeOrderReceivableView/getDataSource");
            self.dataSource.filter({
                logic: "and",
                filters: [{field: "status",operator: "eq",value: "1"}]
            });
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.clientOrderNumber("");
                self.clientId("");
                self.status("");
                self.orderBeginDate("");
                self.orderEndDate("");
                self.receiveMan("");
                self.feeSaveMark("");
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
                template: "<a href='\\#/feeOrderReceivable/modify/#= orderReceivableId #' >#= clientOrderNumber# </a>",
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
                width:60
            },{
                field: "backStatusName",
                title: "审核拒绝",
                template:function (dataItem) {
                    if(dataItem.backStatusName)
                    return "<span style='color: #ff0000;font-weight: bold;'>"+dataItem.backStatusName+"</span>";
                    return "";
                },
                width: 80
            }];

            self.selectAll= function(){
                var select = new Array();
                for(var i = 1;i<=self.dataSource.data().length;i++){
                    select.push("tr:eq("+i+")");
                }
                var grid = $(".k-grid").data("kendoGrid");
                grid.select(select.toString());
            }

            self.confirmBatch = function () {
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
                    var feeSaveMark=dataItem.feeSaveMark;
                    if(!(status==1&&feeSaveMark)){
                        notify("只能选择待确认及已保存的应收费用！","error");
                        return;
                    }else {
                        selectedIds.push(dataItem.orderReceivableId);
                    }
                }

                if (!confirm("确定批量确认吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderReceivable/confirmBatch",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("批量确认成功！", "success");
                        self.dataSource.query();
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            };

            self.clientOrderNumber = ko.observable();
            self.clientId = ko.observable();
            self.status = ko.observable(1);
            self.orderBeginDate = ko.observable();
            self.orderEndDate = ko.observable();
            self.receiveMan=ko.observable();
            self.feeSaveMark=ko.observable();
            self.statusList = commonData.feeAuditStatus;
            self.feeSaveMarkList=commonData.YesOrNo;
            self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
            self.search = function () {
                var clientOrderNumber = $.trim(self.clientOrderNumber());
                var clientId = $.trim(self.clientId());
                var status = $.trim(self.status());
                var orderEndDate = $.trim(self.orderEndDate());
                var orderBeginDate = $.trim(self.orderBeginDate());
                var receiveMan=$.trim(self.receiveMan());
                var feeSaveMark= $.trim(self.feeSaveMark());
                var filters = new Array();
                if (clientOrderNumber) {
                    filters.push({
                        field: "clientOrderNumber",
                        operator: "contains",
                        value: clientOrderNumber
                    });
                }
                if (feeSaveMark) {
                    if(!(status && status==1)){
                        notify("请选择审核状态为待确认！", "error");
                        return;
                    }
                    filters.push({
                        field: "feeSaveMark",
                        operator: "eq",
                        value: feeSaveMark
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