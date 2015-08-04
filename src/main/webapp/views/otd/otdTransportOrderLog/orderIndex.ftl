<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdOrderLogListView"><i class="fa fa-users"></i> 信息跟踪</a></li>
</ol>


<div id="grid-wrap">
    <div class="grid-toolbar">

        <div>
            <label for="clientId">客户</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
            <label for="clientOrderNumber">客户单号</label><input class="k-textbox" data-bind="value:clientOrderNumber">
            <label for="orderBeginDate">订单日期</label><input data-bind="kendoDatePicker:orderBeginDate">
            <label for="orderEndDate">至</label><input data-bind="kendoDatePicker:orderEndDate">
        </div>
        <div>
            <label for="carrierName">承运商</label><input class="k-textbox" data-bind="value:carrierName">
            <label for="carrierOrderNumber">托运单号</label><input class="k-textbox" data-bind="value:carrierOrderNumber">
            <label for="sentDateBegin">发运日期</label><input data-bind="kendoDatePicker:{value:sentDateBegin, max:sentDateEnd} ">
            <label for="sentDateEnd">至</label><input data-bind="kendoDatePicker:{value:sentDateEnd, min:sentDateBegin}">
        </div>
        <div>
            <label for="receiveCompany">收货公司</label><input class="k-textbox" data-bind="value:receiveCompany">
            <label for="receiveMan">收货人</label><input class="k-textbox" data-bind="value:receiveMan">
            <label for="receivePhone">收货人电话</label><input class="k-textbox" data-bind="value:receivePhone">
            <label for="destCity">目的地</label><input class="k-textbox" data-bind="value:destCity">
        </div>
        <div>
            <label for="expectedDateBegin">预计到达时间</label><input data-bind="kendoDateTimePicker:{value:expectedDateBegin,max:expectedDateEnd}">
            <label for="expectedDateEnd">至</label><input data-bind="kendoDateTimePicker:{value:expectedDateEnd,min:expectedDateBegin}">
            <label for="orderStatus">订单状态</label><@utils.koDropDown data="$root.orderStatuList" value="orderStatus"/>
            <label for="isAbnormal">签收状态</label><@utils.koDropDown data="$root.transportOrderSignStatusList" value="isAbnormal"/>
        </div>
        <div>
            <label for="transportType">运输方式</label><@utils.koDropDown data="$root.transportTypes" value="transportType"/>
            <label for="marketClientNumber">电商单号</label><input class="k-textbox" data-bind="value:marketClientNumber">
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>

        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <button type="button" data-bind="kendoButton:addMarketNumber, enable:canTransportLog">
                <i class="fa fa-plus"></i> 维护电商单号
            </button>
            <button type="button" data-bind="kendoButton:transportLog, enable:canTransportLog">
                <i class="fa fa-plus"></i> 运输订单日志
            </button>
            <button type="button" data-bind="kendoButton:carrierLog, enable:canCarrierLog">
                <i class="fa fa-plus"></i> 托运单日志
            </button>
            <button type="button" data-bind="kendoButton:sign, enable:canSign">
                <i class="fa fa-edit"></i> 签收
            </button>
            <button type="button" data-bind="kendoButton:reset, enable:canReset">
                <i class="fa fa-edit"></i> 撤销签收
            </button>
            <button type="button" data-bind="kendoButton:arrive, enable:canArrive">
                <i class="fa fa-edit"></i> 到达
            </button>
        </div>
    </div>
    <div id="otdOrderLogListView" data-bind="kendoGrid: { data: dataSource,height:height, widget: widget, columns: columns,columnMenu:true,detailInit: detailInit,sortable: true,toolbar:['excel','pdf'], groupable: false, editable:false, pageable: pageable, selectable: 'multiple,row',change:$root.onChange}"></div>
</div>


<script>

    var GridModel = function () {
        var self = this;
        self.height = ko.observable(getGridHeight());
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createFilterDataSource("transportOrderId", "/otdOrderLogListView/getDataSource",{field: "status",operator: "neq",value: "6"});
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.selectTest =function(){
            var grid = $("#grid").data("kendoGrid");
            $("#grid td").dblclick(function(){
                grid.editCell(this);
                $(this).find("input").attr("readonly",true);
            });
        }
        self.clearFilter = function () {
            self.carrierOrderNumber("");
            self.destCity("");
            self.clientOrderNumber("");
            self.carrierName("");
            self.orderBeginDate("");
            self.orderEndDate("");
            self.sentDateBegin("");
            self.sentDateEnd("");
            self.orderStatus("");
            self.clientId("");
            self.transportType("");
            self.receiveMan("");
            self.receiveCompany("");
            self.isAbnormal("");
            self.expectedDateBegin("");
            self.expectedDateEnd("");
            self.receivePhone("");
            self.marketClientNumber("");
            self.dataSource.filter({
                logic: "and",
                filters: [{field: "status",operator: "neq",value: "6"}]
            });
        };
        self.selectedOrder = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedOrder(dataItem);
        };
        self.detailInit = function(e){
            $("<div/>").appendTo(e.detailCell).kendoGrid({
                dataSource: kendo.utils.createFilterDataSource("transportOrderDetailId", "/otdTransportOrderDetail/getDataSource",{ field: "transportOrderId", operator: "eq", value: e.data.transportOrderId }),
                sortable: true,
                columns: [{
                    field: "goodsCode",
                    title: "货物编码"
                }, {
                    field: "goodsName",
                    title: "货物名称"
                }, {
                    field: "goodsType",
                    title: "货物型号"
                }, {
                    field: "totalItemQuantity",
                    title: "数量"
                }, {
                    field: "totalPackageQuantity",
                    title: "件数"
                },{
                    field: "totalVolume",
                    title: "体积"
                }, {
                    field: "totalWeight",
                    title: "重量"
                }]
            });
        };

        self.columns = [ {
            title: "序号",
            template: function (dataItem) {
                return self.dataSource.indexOf(dataItem)+1;
            },
            width: 40
        },{
            field: "startCity",
            title: "始发地",
            width: 85
        },{
            field: "clientName",
            title: "客户",
            width: 100
        }, {
            field: "clientOrderNumber",
            title: "客户单号",
            template: "<a href='\\#/otdTransportOrderLog/detail/#= transportOrderId #' >#= clientOrderNumber# </a>",
            width: 110
        }, {
            field: "viceClientOrderNumber",
            title: "副单号",
            width: 85
        }, {
            field: "marketClientNumber",
            title: "电商单号",
            width: 110
        },{
            field: "receivablePackageQuantity",
            title: "应收件数",
            width: 110
        },{
            field: "payablePackageQuantity",
            title: "应付件数",
            width: 110
        }, {
            field: "orderDate",
            title: "订单日期",
            width: 110,
            template: function (dataItem) {
                return kendo.toString(kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd")
            }
        },{
            field: "carrierName",
            title: "承运商",
            width: 85
        }, {
            field: "carrierOrderNumber",
            title: "托运单号",
            width: 110,
            template: "<a href='\\#/otdCarrierOrderLog/detail/#= carrierOrderId #' >#= carrierOrderNumber?carrierOrderNumber:'' # </a>"
        },{
            field: "transferOrganizationName",
            title: "中转公司",
            width: 110
        }, {
            field: "sentDate",
            title: "发运时间",
            width: 110,
            template: function (dataItem) {
                if (dataItem.sentDate)  return kendo.toString(kendo.parseDate(dataItem.sentDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
        }, {
            field: "expectedDate",
            title: "预到时间",
            template: function (dataItem) {
                if (dataItem.expectedDate)  return kendo.toString(kendo.parseDate(dataItem.expectedDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
            width: 110
        },{
            field: "receiveMan",
            title: "收货人",
            width: 85
        },{
            field: "destCity",
            title: "目的地",
            width: 85
        },{
            field: "receivePhone",
            title: "电话",
            width: 60
        },{
            field: "receiveAddress",
            title: "地址",
            width:150
        },  {
            field: "statusName",
            title: "状态",
            template:function (dataItem) {
                if(0<dataItem.status && dataItem.status<=3){
                    var orderDate=kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd");
                    var currentDate=kendo.parseDate(kendo.toString(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd");
                    if(orderDate < currentDate){
                        return "<span style='color: #ff0000;font-weight: bold;'>发运延误</span>";
                    }else{
                        return dataItem.statusName;
                    }
                }else if(dataItem.status>3&&dataItem.status<6){
                    var expectedDate = kendo.parseDate(dataItem.expectedDate, "yyyy-MM-dd HH:mm:ss");
                    if(expectedDate&&expectedDate<new Date()){
                        return "<span style='color: #ff0000;font-weight: bold;'>在途延误</span>";
                    }else{
                        return dataItem.statusName;
                    }
                }else{
                    return dataItem.statusName;
                }
            },
            width: 90
        }, {
            field: "operationDate",
            title: "最近操作时间",
            width: 130
        }, {
            field: "operationContent",
            title: "操作内容",template:function (dataItem) {
                var expectedDate = kendo.parseDate(dataItem.expectedDate, "yyyy-MM-dd HH:mm:ss");
                var currentDate=kendo.parseDate(kendo.toString(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd");
                if(expectedDate&&kendo.parseDate(dataItem.expectedDate, "yyyy-MM-dd")<=currentDate) {//预到货日期是今天
                    if(kendo.parseDate(dataItem.operationDate,"yyyy-MM-dd")<currentDate&&dataItem.status<=5&&dataItem.status>0) {
                        return "<span style='color: #ff0000;font-weight: bold;'>"+dataItem.operationContent+"</span>";
                    }else{
                        return dataItem.operationContent;
                    }
                } else {
                    return dataItem.operationContent;
                }
            },
            width: 150
        }];

        self.showLog = function () {
            if (self.widget().select().length != 1) {
                notify("请选择要操作的一行", "error");
                return;
            }
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            var url = "#/otdTransportOrderLog/index/" + dataItem.transportOrderId;
            router.navigate(url);
        };

        self.clientOrderNumber = ko.observable();
        self.carrierOrderNumber = ko.observable();
        self.destCity = ko.observable();
        self.carrierName = ko.observable();
        self.orderBeginDate = ko.observable();
        self.orderEndDate = ko.observable();
        self.sentDateBegin = ko.observable();
        self.sentDateEnd = ko.observable();
        self.clientId = ko.observable();
        self.orderStatus = ko.observable("");
        self.transportType= ko.observable();
        self.receiveMan=ko.observable();
        self.receiveCompany=ko.observable();
        self.isAbnormal=ko.observable();
        self.expectedDateEnd=ko.observable();
        self.expectedDateBegin=ko.observable();
        self.receivePhone=ko.observable();
        self.marketClientNumber=ko.observable();
        self.transportTypes = commonData.transportType;
        self.transportOrderSignStatusList = commonData.transportOrderSignStatus;
        self.orderStatuList = commonData.orderStatus;
        self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");

        self.sign = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            if(self.widget().select().length>0){
                for(var i=0;i<rows.length;i++){
                    var row=rows[i];
                    var dataItem = self.widget().dataItem(row);
                    if (dataItem) {
                        if(dataItem.status!=5){
                            notify("请选择已到达的运输订单","error");
                            return;
                        }
                        selectedIds.push(dataItem.transportOrderId);
                    }
                }
            }
            var url = "otdOrderSign/batchSign/"+selectedIds;
            modal("签收", url, {width:600, height: 400, close:function(){
                self.selectedOrder("");
                self.dataSource.query();
            }});
        }
        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length;i++){
                select.push("tr:eq("+i+")");
            }
            var grid = $(".k-grid").data("kendoGrid");
            grid.select(select.toString());
        }
        self.transportLog = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            var url = "";
            if(rows.length==1) {
                url= "otdTransportOrderLog/create/"+self.selectedOrder().transportOrderId;
            }
            else if(self.widget().select().length>1){
                for(var i=0;i<rows.length;i++){
                    var row=rows[i];
                    var dataItem = self.widget().dataItem(row);
                    if (dataItem) {
                        selectedIds.push(dataItem.transportOrderId);
                    }
                }
                url= "otdTransportOrderLog/createBatch/"+selectedIds;
            }
            modal("添加运输订单日志", url, {
                width: 500, height: 600, close: function () {
                    self.selectedOrder("");
                    self.dataSource.query();
                }
            });
        }

        self.addMarketNumber=function(){
            var selectedIds=[];
            var rows=self.widget().select();
            for(var i=0;i<rows.length;i++){
                var row=rows[i];
                var dataItem = self.widget().dataItem(row);
                if(dataItem.marketClientNumber){
                    notify("已选择的运输订单已存在电商单号！","error");
                    return ;
                }
                selectedIds.push(dataItem.transportOrderId);
            }
            var url="otdTransportOrder/addMarketNumber/"+selectedIds;
            modal("维护运输订单电商单号",url,{
                width: 400, height: 500, close: function () {
                    self.selectedOrder("");
                    self.dataSource.query();
                }
            });
        }
        self.carrierLog = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            var url ="otdCarrierOrderLog/create/"+self.selectedOrder().carrierOrderId;
            if(self.widget().select().length>1){
                for(var i=0;i<rows.length;i++){
                    var row=rows[i];
                    var dataItem = self.widget().dataItem(row);
                    if(!dataItem.carrierOrderId){
                        notify("请选择已发运的订单","error");
                        return ;
                    }
                    else{
                        selectedIds.push(dataItem.carrierOrderId);
                    }
                }
                url ="otdCarrierOrderLog/createBatch/"+selectedIds;
            }
            modal("添加托运单日志", url, {
                width: 500, height: 600, close: function () {
                    self.selectedOrder("");
                    self.dataSource.query();
                }
            });
        }
        self.canSign = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().status == 5;
        });
        self.canArrive = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().carrierStatus== 2;
        });
        self.canReset = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().status==6&&self.selectedOrder().receiptStatus!= 3;
        });
        self.canTransportLog = ko.computed(function () {
            return self.selectedOrder();
        });
        self.canCarrierLog = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().carrierOrderId;
        });
        self.reset = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            if(self.widget().select().length>0){
                for(var i=0;i<rows.length;i++){
                    var row=rows[i];
                    var dataItem = self.widget().dataItem(row);
                    if (dataItem) {
                        if(dataItem.receiptStatus==3){
                            notify("请选择未回单的运输订单","error");
                            return;
                        }else if(dataItem.status!=6){
                            notify("请选择已签收的运输订单","error");
                            return;
                        } else{
                            selectedIds.push(dataItem.transportOrderId);
                        }
                    }
                }
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdOrderSign/batchReset",
                data: ko.toJSON(selectedIds)
            }).done(function (result) {
                if (result.success === true) {
                    notify("撤销成功", "success");
                    self.dataSource.query();
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.arrive = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            if(self.widget().select().length>0){
                for(var i=0;i<rows.length;i++){
                    var row=rows[i];
                    var dataItem = self.widget().dataItem(row);
                    if (dataItem) {
                        if(dataItem.carrierStatus!=2){
                            notify("请选择已发运的运输订单","error");
                            return;
                        }else{
                            selectedIds.push(dataItem.carrierOrderId);
                        }
                    }
                }
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdCarrierOrder/batchArrive",
                data: ko.toJSON(selectedIds)
            }).done(function (result) {
                if (result.success === true) {
                    notify("到达操作成功", "success");
                    self.dataSource.query();
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.search = function () {
            var clientOrderNumber = $.trim(self.clientOrderNumber());
            var carrierName = $.trim(self.carrierName());
            var orderBeginDate = $.trim(self.orderBeginDate());
            var orderEndDate = $.trim(self.orderEndDate());
            var sentDateBegin = $.trim(self.sentDateBegin());
            var sentDateEnd = $.trim(self.sentDateEnd());
            var clientId = $.trim(self.clientId());
            var orderStatus = $.trim(self.orderStatus());
            var transportType = $.trim(self.transportType());
            var receiveMan = $.trim(self.receiveMan());
            var receiveCompany= $.trim(self.receiveCompany());
            var isAbnormal= $.trim(self.isAbnormal());
            var carrierOrderNumber = $.trim(self.carrierOrderNumber());
            var expectedDateBegin = $.trim(self.expectedDateBegin());
            var expectedDateEnd = $.trim(self.expectedDateEnd());
            var destCity = $.trim(self.destCity());
            var receivePhone= $.trim(self.receivePhone());
            var marketClientNumber= $.trim(self.marketClientNumber());
            var filters = new Array();
            if (clientOrderNumber) {
                filters.push({
                    field: "clientOrderNumber",
                    operator: "contains",
                    value: clientOrderNumber
                });
            }
            if (marketClientNumber) {
                filters.push({
                    field: "marketClientNumber",
                    operator: "contains",
                    value: marketClientNumber
                });
            }
            if (carrierOrderNumber) {
                filters.push({
                    field: "carrierOrderNumber",
                    operator: "contains",
                    value: carrierOrderNumber
                });
            }if (destCity) {
                filters.push({
                    field: "destCity",
                    operator: "contains",
                    value: destCity
                });
            }
            if (receivePhone) {
                filters.push({
                    field: "receivePhone",
                    operator: "contains",
                    value: receivePhone
                });
            }
            if (carrierName) {
                filters.push({
                    field: "carrierName",
                    operator: "contains",
                    value: carrierName
                });
            }
            if (transportType) {
                filters.push({
                    field: "transportType",
                    operator: "eq",
                    value: transportType
                });
            }
            if (receiveCompany) {
                filters.push({
                    field: "receiveCompany",
                    operator: "contains",
                    value: receiveCompany
                });
            }
            if(receiveMan){
                filters.push({
                    field: "receiveMan",
                    operator: "contains",
                    value: receiveMan
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
            if (expectedDateBegin) {
                filters.push({
                    field: "expectedDate",
                    operator: "gte",
                    value: new Date(expectedDateBegin)
                });
            }
            if (expectedDateEnd) {
                filters.push({
                    field: "expectedDate",
                    operator: "lte",
                    value: new Date(expectedDateEnd)
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
            if(isAbnormal){
                if(orderStatus && orderStatus!=6){
                    notify("请选择订单状态为已签收！", "error");
                    return ;
                }
                filters.push({
                    field: "status",
                    operator: "eq",
                    value: 6
                });
                filters.push({
                    field: "isAbnormal",
                    operator: "eq",
                    value: isAbnormal
                });
            }
            if (orderStatus) {
                filters.push({
                    field: "status",
                    operator: "eq",
                    value: orderStatus
                });
            }

            if (filters.length > 0)
                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            else{
                self.dataSource.filter({
                    logic: "and",
                    filters: [{field: "status",operator: "neq",value: "6"}]
                });
            }
        }
    };
    $(function () {

        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
    $(window).on('resize', function () {
         $("#otdOrderLogListView").height(getGridHeight());
    });
</script>