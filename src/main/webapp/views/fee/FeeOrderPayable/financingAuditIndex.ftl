<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/feeOrderPayableView/financingAudit"><i class="fa fa-users"></i> 应付财务审核</a></li>
</ol>
<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="carrierOrderNumber">托运单号</label><input class="k-textbox" data-bind="value:carrierOrderNumber">
            <label for="carrierName">承运商名称</label><input class="k-textbox" data-bind="value:carrierName">
            <label for="consignee">收货人</label><input class="k-textbox" data-bind="value:consignee">
            <label for="status">审核状态</label><@utils.koDropDown data="$root.statusList" value="status"/>
        </div>
        <div>
            <label for="sendDateBegin">发运日期</label><input data-bind="kendoDatePicker:{value:sendDateBegin, max:sendDateEnd} ">
            <label for="sendDateEnd" class="nospace">至</label><input data-bind="kendoDatePicker:{value:sendDateEnd, min:sendDateBegin}">
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <button type="button" data-bind="kendoButton:pass,enable:canPass">
                <i class="fa fa-edit"></i> 批量通过
            </button>
            <#--<button type="button" data-bind="kendoButton:back,enable:canBack">
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
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true,toolbar:['excel','pdf'], groupable: true, editable:false, pageable: pageable, selectable: 'multiple,row', change:onChange}"></div>
</div>

<script>

    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("orderPayableId", "/feeOrderPayableView/getFinancingAuditDataSource");
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
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.statusList = commonData.feeAuditStatus;
            self.selectAll= function(){
                var select = new Array();
                for(var i = 1;i<=self.dataSource.data().length;i++){
                    select.push("tr:eq("+i+")");
                }
                var grid = $(".k-grid").data("kendoGrid");
                grid.select(select.toString());
            }
            self.columns = [{
                title: "序号",
                template: function (dataItem) {
                    return self.dataSource.indexOf(dataItem)+1;
                },
                width: 40
            },{
                field: "carrierName",
                title: "承运商",
                width:80
            }, {
                field: "carrierOrderNumber",
                title: "托运单",
                template: "<a href='\\#/feeOrderPayable/financingAudit/#= orderPayableId #' >#= carrierOrderNumber# </a>",
                width:120
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
                field: "consignee",
                title: "收货人",
                width: 80
            }, {
                field: "sendDate",
                title: "发运日期",
                template: function (dataItem) {
                    return kendo.toString(kendo.parseDate(dataItem.sendDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
                },
                width: 80
            }, {
                field: "calculateTypeName",
                title: "计费方式",
                width: 80
            },{
                field: "receivTotalAmount",
                title: "应收",
                width: 60
            },{
                field: "totalAmount",
                title: "应付",
                width: 60
            },{
                title: "毛利",
                width: 60,
                template:function (dataItem) {
                    if(dataItem.receivTotalAmount && dataItem.totalAmount!=0){
                        var scale = dataItem.receivTotalAmount-dataItem.totalAmount;
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
                width: 60,
                template:function (dataItem) {
                    if(dataItem.receivTotalAmount && dataItem.receivTotalAmount!=0 && dataItem.totalAmount && dataItem.totalAmount!=0){
                        var scale = formatFloat((dataItem.receivTotalAmount-dataItem.totalAmount)*100/dataItem.totalAmount,2);
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
                width: 60
            }];
            self.selected = ko.observable();
            self.onChange = function () {
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                self.selected(dataItem);
            };
            self.canPass = ko.computed(function () {
                return true;// self.selected()&&self.selected().status==3;
            });
            self.canBack = ko.computed(function () {
                return true;// self.selected()&&self.selected().status==3;
            });

            self.pass=function(){
                var selectedIds = [];
                var rows = self.widget().select();
                for(var i = 0;i<rows.length;i++){
                    var dataItem =self.widget().dataItem(rows[i]);
                    if (dataItem) {
                        if(dataItem.status!==3){
                            notify("只能选择项目已审核的应付费用！",'error');
                            return;
                        }
                        selectedIds.push(dataItem.orderPayableId);
                    }
                }
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderPayable/financingPassBatch",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("审核成功！", "success");
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
                for(var i = 0;i<rows.length;i++){
                    var dataItem =self.widget().dataItem(rows[i]);
                    if (dataItem) {
                    if(dataItem.status!==3){
                        notify("只能选择项目已审核的应付费用！",'error');
                        return;
                    }
                        selectedIds.push(dataItem.orderPayableId);
                    }
                }
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderPayable/backBatch",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("操作成功！", "success");
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
                    notify("请选择批量操作的应付费用！","error");
                    return;
                }
                for(var i=0;i<rows.length;i++){
                    var row = rows[i];
                    var dataItem = self.widget().dataItem(row);
                    var status=dataItem.status;
                    if(status!=4){
                        notify("只能选择财务已审核的应付费用！","error");
                        return;
                    }else {
                        selectedIds.push(dataItem.orderPayableId);
                    }
                }

                if (!confirm("确定批量审核完结吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderPayable/financingFinishBatch",
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
                    notify("请选择批量操作的应付费用！","error");
                    return;
                }
                for(var i=0;i<rows.length;i++){
                    var row = rows[i];
                    var dataItem = self.widget().dataItem(row);
                    var status=dataItem.status;
                    if(status!=4){
                        notify("只能选择财务已审核的应付费用！","error");
                        return;
                    }else {
                        selectedIds.push(dataItem.orderPayableId);
                    }
                }

                if (!confirm("确定批量撤销吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderPayable/backBatch",
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
            self.clearFilter = function () {
                self.carrierOrderNumber("");
                self.carrierName("");
                self.status("");
                self.consignee("");
                self.sendDateBegin("");
                self.sendDateEnd("");
                self.dataSource.filter([]);
            };
            self.carrierOrderNumber = ko.observable();
            self.carrierName = ko.observable();
            self.status = ko.observable(3);
            self.consignee = ko.observable();
            self.sendDateBegin = ko.observable();
            self.sendDateEnd = ko.observable();
            self.search = function () {
                var carrierOrderNumber = $.trim(self.carrierOrderNumber());
                var carrierName = $.trim(self.carrierName());
                var status = $.trim(self.status());
                var consignee = $.trim(self.consignee());
                var sendDateBegin = $.trim(self.sendDateBegin());
                var sendDateEnd = $.trim(self.sendDateEnd());
                var filters = new Array();
                if (carrierOrderNumber) {
                    filters.push({
                        field: "carrierOrderNumber",
                        operator: "contains",
                        value: carrierOrderNumber
                    });
                }
                if (carrierName) {
                    filters.push({
                        field: "carrierName",
                        operator: "contains",
                        value: carrierName
                    });
                }if (status) {
                    filters.push({
                        field: "status",
                        operator: "eq",
                        value: status
                    });
                }
                if (sendDateBegin) {
                    filters.push({
                        field: "sendDate",
                        operator: "gte",
                        value: new Date(sendDateBegin)
                    });
                }
                if (sendDateEnd) {
                    var sendDateEnd=new Date(sendDateEnd);
                    filters.push({
                        field: "sendDate",
                        operator: "lt",
                        value: new Date(sendDateEnd.setDate(sendDateEnd.getDate()+1))
                    });
                }
                if (consignee) {
                    filters.push({
                        field: "consignee",
                        operator: "contains",
                        value: consignee
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