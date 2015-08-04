<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdTransportOrderView/receipt"><i class="fa fa-users"></i> 回单管理</a></li>
</ol>


<div id="grid-wrap">
    <div class="grid-toolbar">

        <div>
            <label for="clientOrderNumber">客户单号</label><input class="k-textbox" data-bind="value:clientOrderNumber">
            <label for="orderBeginDate">订单日期</label><input data-bind="kendoDatePicker:{value:orderBeginDate, max:orderEndDate}">
            <label for="orderEndDate">至</label><input data-bind="kendoDatePicker:{value:orderEndDate, min: orderBeginDate}">
            <label for="clientId">客户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
        </div>
        <div>
            <label for="lnetOrderNumber">新易泰单号</label><input class="k-textbox" data-bind="value:lnetOrderNumber">
            <label for="sentDateBegin">发运日期</label><input data-bind="kendoDatePicker:{value:sentDateBegin, max:sentDateEnd} ">
            <label for="sentDateEnd">至</label><input data-bind="kendoDatePicker:{value:sentDateEnd, min:sentDateBegin}">
            <label for="receiptStatus">回单状态</label><@utils.koDropDown data="$root.receiptStatusList" value="receiptStatus"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>

        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <button type="button" data-bind="kendoButton:receipt, enable:canReceipt">
                <i class="fa fa-edit"></i> 已回
            </button>
            <button type="button" data-bind="kendoButton:repair, enable:canRepair">
                <i class="fa fa-edit"></i> 待补签
            </button>
        </div>
        <div>
            <label for="sum">回单总数:</label><span data-bind="text:sum"/><#--<input class="k-textbox" data-bind="value:sum">-->
            <label for="count3">已回数量:</label><span data-bind="text:count3"/><#--<input class="k-textbox" data-bind="value:count3">-->
            <label for="count1">未回数量:</label><span data-bind="text:count1"/><#--<input class="k-textbox" data-bind="value:count1">-->
            <label for="count2">待补数量:</label><span data-bind="text:count2"/><#--<input class="k-textbox" data-bind="value:count2">-->
            <label for="RepairCount">补签数量:</label><span data-bind="text:RepairCount"/><#--<input class="k-textbox" data-bind="value:RepairCount">-->
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true,toolbar:['excel','pdf'], groupable: true, editable:false, pageable: pageable, selectable: 'multiple, row', change:onChange,dataBound:getCount }"></div>
</div>


<script>
    $(function () {
        var GridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.sum = ko.observable();
            self.count1 = ko.observable();
            self.count2 = ko.observable();
            self.count3 = ko.observable();
            self.RepairCount = ko.observable();
            self.dataSource = kendo.utils.createDataSource("transportOrderId", "/otdTransportOrderView/getNotMergeOrder");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.clientOrderNumber("");
                self.lnetOrderNumber("");
                self.orderBeginDate("");
                self.orderEndDate("");
                self.sentDateBegin("");
                self.sentDateEnd("");
                self.receiptStatus("");
                self.clientId("");
                self.dataSource.filter([]);
                self.getCount();
            };
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
                field: "clientName",
                title: "客户",
                width: 100
            }, {
                field: "clientOrderNumber",
                title: "客户单号",
                width: 120,
                template: "<a href='\\#/otdTransportOrder/detail/#= transportOrderId #' >#= clientOrderNumber# </a>",
            }, {
                field: "receiptPageNumber",
                title: "回单份数",
                width: 80
            }, {
                field: "carrierOrderNumber",
                title: "托运单号",
                width: 100
            },  {
                field: "carrierName",
                title: "承运商",
                width: 100
            }, {
                field: "transferOrganizationName",
                title: "中转公司",
                width: 100
            },{
                field: "orderDate",
                title: "订单日期",
                template: function (dataItem) {
                    return kendo.toString(kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
                },
                width: 120
            }, {
                field: "sentDate",
                title: "发运时间",
                template: function (dataItem) {
                    if (dataItem.sentDate)  return kendo.toString(kendo.parseDate(dataItem.sentDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                    return "";
                },
                width: 120
            }, {
                field: "startCity",
                title: "始发地",
                width: 80
            }, {
                field: "destCity",
                title: "目的地",
                width: 80
            }, {
                field: "statusName",
                title: "订单状态",
                width: 80
            }, {
                field: "receiptStatusName",
                title: "回单状态",
                width: 80
            }, {
                field: "receiptDate",
                title: "回单/待补签日期",
                template: function (dataItem) {
                    if(dataItem.receiptDate)
                        return kendo.toString(kendo.parseDate(dataItem.receiptDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
                    else
                        return "";
                },
                width: 100
            },{
                field: "receiptRemark",
                title: "备注",
                width: 140
            }];
            self.getCount = function(){
                var sum = 0;
                var count1=0;//未回
                var count2=0;//已回待补
                var count3=0;//已回
                var RepairCount = 0;
                if(self.dataSource.data().length>0){
                    for(var i =0;i<self.dataSource.data().length;i++){
                        var order =self.dataSource.data()[i];
                        if(order.status!=-1){
                            sum++;
                            switch (order.receiptStatus){
                                case 1:{
                                    count1++;
                                    break;
                                } case 2:{
                                count2++;
                                break;
                            } case 3:{
                                count3++;
                                break;
                            }
                            }
                            if (order.isRepair==1){
                                RepairCount++;
                            }
                        }
                    }
                }
                self.sum(sum);
                self.count1(count1);
                self.count2(count2);
                self.count3(count3);
                self.RepairCount(RepairCount);
                var grid = $(".k-grid").data("kendoGrid");
                $(".k-grid table[aria-multiselectable='true'] td").dblclick(function(){
                    grid.editCell(this);
                    $(this).find("input").attr("readonly",true);
                });
            }
            self.selected = ko.observable();
            self.onChange = function () {
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                self.selected(dataItem);
            };

            self.canReceipt = ko.computed(function () {
                return self.selected() && self.selected().receiptStatus &&self.selected().receiptStatus != 3;
            });

            self.canRepair = ko.computed(function () {
                return self.selected() && self.selected().receiptStatus != 2;
            });

            self.receipt = function () {
                var url = "otdTransportOrderReceipt/receipt/"+self.selected().transportOrderId;
                if(self.widget().select().length>1){
                    var selectedIds = [];
                    var rows = self.widget().select();
                    rows.each(function (i, row) {
                        var dataItem = self.widget().dataItem(row);
                        if (dataItem) {
                            if(dataItem.receiptStatus==3){
                                notify("请选择未回或待补签的订单","error");
                                return;
                            }
                            selectedIds.push(dataItem.transportOrderId);
                        }
                    });
                    url = "otdTransportOrderReceipt/receiptBatch/"+selectedIds;
                }

                modal("回单", url, {width:600, height: 400, close:function(){
                    self.selected("");
                    self.dataSource.query();
                    self.getCount();
                }});
            };

            self.repair = function () {
                if (self.widget().select().length != 1) {
                    notify("请选择待补签的一行", "error");
                    return;
                }
                var url = "otdTransportOrderReceipt/repair/"+self.selected().transportOrderId;
                modal("回单待补", url, {width:800, height: 600, close:function(){
                    self.selected("");
                    self.dataSource.query();
                    self.getCount();
                }});
            }

            self.clientOrderNumber = ko.observable();
            self.lnetOrderNumber = ko.observable();
            self.orderBeginDate = ko.observable();
            self.orderEndDate = ko.observable();
            self.sentDateBegin = ko.observable();
            self.sentDateEnd = ko.observable();
            self.clientId = ko.observable();
            self.receiptStatus = ko.observable("");
            self.receiptStatusList = commonData.receiptStatus;
            self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
            self.search = function () {
                var clientOrderNumber = $.trim(self.clientOrderNumber());
                var lnetOrderNumber = $.trim(self.lnetOrderNumber());
                var orderBeginDate = $.trim(self.orderBeginDate());
                var orderEndDate = $.trim(self.orderEndDate());
                var sentDateBegin = $.trim(self.sentDateBegin());
                var sentDateEnd = $.trim(self.sentDateEnd());
                var clientId = $.trim(self.clientId());
                var receiptStatus = $.trim(self.receiptStatus());
                var filters = new Array();
                if (clientOrderNumber) {
                    filters.push({
                        field: "clientOrderNumber",
                        operator: "contains",
                        value: clientOrderNumber
                    });
                }
                if (lnetOrderNumber) {
                    filters.push({
                        field: "lnetOrderNumber",
                        operator: "contains",
                        value: lnetOrderNumber
                    });
                }
                if (clientId) {
                    filters.push({
                        field: "clientId",
                        operator: "eq",
                        value: clientId
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
                if (sentDateBegin) {
                    filters.push({
                        field: "sentDate",
                        operator: "gte",
                        value: new Date(sentDateBegin)
                    });
                }
                if (sentDateEnd) {
                    var sentDateEnd=new Date(sentDateEnd);
                    filters.push({
                        field: "sentDate",
                        operator: "lt",
                        value: new Date(sentDateEnd.setDate(sentDateEnd.getDate()+1))
                    });
                }
                if (receiptStatus) {
                    filters.push({
                        field: "receiptStatus",
                        operator: "eq",
                        value: receiptStatus
                    });
                }

                if (filters.length > 0) {
                    self.dataSource.filter({
                        logic: "and",
                        filters: filters
                    });
                    self.getCount();
                }else
                {
                    self.dataSource.filter([]);
                    self.getCount();
                }
            }
        };

        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>