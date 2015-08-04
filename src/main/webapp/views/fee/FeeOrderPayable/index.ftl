<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/feeOrderPayable"><i class="fa fa-users"></i> 运输订单</a></li>
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
            <label for="sendDateEnd">至</label><input data-bind="kendoDatePicker:{value:sendDateEnd, min:sendDateBegin}">
            <label for="feeSaveMark">是否已保存</label><@utils.koDropDown data="$root.feeSaveMarkList" value="feeSaveMark"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
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
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, toolbar:['excel','pdf'],editable:false, pageable: pageable,selectable:'multiple, row'}"></div>
</div>


<script>
    $(function () {
        var GridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("orderPayableId", "/feeOrderPayableView/getDataSource");
            self.dataSource.filter({
                logic: "and",
                filters: [{field: "status",operator: "eq",value: "1"}]
            });
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.statusList = commonData.feeAuditStatus;
            self.feeSaveMarkList=commonData.YesOrNo;
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
                template: "<a href='\\#/feeOrderPayable/modify/#= orderPayableId #' >#= carrierOrderNumber# </a>",
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
                    notify("请选择批量操作的应付费用！","error");
                    return;
                }
                for(var i=0;i<rows.length;i++){
                    var row = rows[i];
                    var dataItem = self.widget().dataItem(row);
                    var status=dataItem.status;
                    var feeSaveMark=dataItem.feeSaveMark;
                    if(!(status==1&&feeSaveMark)){
                        notify("只能选择待确认及已保存的应付费用！","error");
                        return;
                    }else {
                        selectedIds.push(dataItem.orderPayableId);
                    }
                }

                if (!confirm("确定批量确认吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/feeOrderPayable/confirmBatch",
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
            self.clearFilter = function () {
                self.carrierOrderNumber("");
                self.carrierName("");
                self.status("");
                self.consignee("");
                self.sendDateBegin("");
                self.sendDateEnd("");
                self.feeSaveMark("");
                self.dataSource.filter([]);
            };
            self.carrierOrderNumber = ko.observable();
            self.carrierName = ko.observable();
            self.status = ko.observable(1);
            self.consignee = ko.observable();
            self.sendDateBegin = ko.observable();
            self.sendDateEnd = ko.observable();
            self.feeSaveMark=ko.observable();

            self.search = function () {
                var carrierOrderNumber = $.trim(self.carrierOrderNumber());
                var carrierName = $.trim(self.carrierName());
                var status = $.trim(self.status());
                var consignee = $.trim(self.consignee());
                var sendDateBegin = $.trim(self.sendDateBegin());
                var sendDateEnd = $.trim(self.sendDateEnd());
                var feeSaveMark= $.trim(self.feeSaveMark());
                var filters = new Array();
                if (carrierOrderNumber) {
                    filters.push({
                        field: "carrierOrderNumber",
                        operator: "contains",
                        value: carrierOrderNumber
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
                if (carrierName) {
                    filters.push({
                        field: "carrierName",
                        operator: "contains",
                        value: carrierName
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

        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>