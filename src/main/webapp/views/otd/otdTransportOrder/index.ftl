<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdTransportOrderView"><i class="fa fa-users"></i> 运输订单</a></li>
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
            <label for="orderStatus">订单状态</label><@utils.koDropDown data="$root.orderStatuList" value="orderStatus"/>
        </div>
        <div>
            <label for="transportType">运输方式</label><@utils.koDropDown data="$root.transportTypes" value="transportType"/>
            <label for="receiveCompany">收货公司</label><input class="k-textbox" data-bind="value:receiveCompany">
            <label for="receiveMan">收货人</label><input class="k-textbox" data-bind="value:receiveMan">
            <label for="mergeStatus">合单状态</label><@utils.koDropDown data="$root.mergeStatusList" value="mergeStatus"/>
        </div>
        <div>
            <label for="viceClientOrderNumber">副单号</label><input class="k-textbox" data-bind="value:viceClientOrderNumber">
            <label for="destCity">目的地</label><input class="k-textbox" data-bind="value:destCity">
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>

        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <a data-role="button" href="#/otdTransportOrder/create"> <i class="fa fa-plus"></i> 接单 </a>
            <button type="button" data-bind="kendoButton:merge">
                <i class="fa fa-edit"></i> 新增合单
            </button>
            <button type="button" data-bind="kendoButton:repealMerge,enable:canRepealMerge">
                <i class="fa fa-edit"></i> 撤销合单
            </button>
            <button type="button" data-bind="kendoButton:modifyNumber, enable:canModifyNumber">
                <i class="fa fa-edit"></i> 确认客户单号
            </button>
            <button type="button" data-bind="kendoButton:modify, enable:canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
            <button type="button" data-bind="kendoButton:confirm, enable:canConfirm">
                <i class="fa fa-edit"></i> 审单
            </button>
            <button type="button" data-bind="kendoButton:repealConfirm, enable:canRepealConfirm">
                <i class="fa fa-edit"></i> 撤销审单
            </button>
            <button type="button" data-bind="kendoButton:sign, enable:canSign">
                <i class="fa fa-edit"></i> 签收
            </button>
            <button type="button" data-bind="kendoButton:cancel, enable:canCancel">
                <i class="fa fa-edit"></i> 取消
            </button>
            <button type="button" data-bind="kendoButton:repealCancel, enable:canRepealCancel">
                <i class="fa fa-edit"></i> 取消还原
            </button>
            <button type="button" data-bind="kendoButton:importOrder">
                <i class="fa fa-upload"></i> 导入
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, height:height,widget: widget, columns: columns,sortable: true, groupable: true,
     toolbar:['excel','pdf'],excel:excel,pdf:pdf,editable:false, pageable: pageable, selectable: 'multiple,row', change:onChange }"></div>
</div>


<script>
    var GridModel = function () {
        var self = this;
        self.height = ko.observable(getGridHeight());
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("transportOrderId", "/otdTransportOrderView/getDataSource");
        self.dataSource.filter({
            logic: "and",
            filters: [{field: "status",operator: "eq",value: "1"}]
        });
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.clearFilter = function () {
            self.clientOrderNumber("");
            self.lnetOrderNumber("");
            self.orderBeginDate("");
            self.orderEndDate("");
            self.sentDateBegin("");
            self.sentDateEnd("");
            self.orderStatus("");
            self.mergeStatus("");
            self.clientId("");
            self.destCity("");
            self.transportType("");
            self.receiveMan("");
            self.receiveCompany("");
            self.viceClientOrderNumber("");
            self.dataSource.filter([]);
        };
        self.excel ={
            fileName: "运输订单.xlsx",
            allPages:true,
            filterable: true
        }
        self.pdf ={
            fileName: "运输订单.pdf",
            allPages:true,
            filterable: true
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
            template: "<a href='\\#/otdTransportOrder/detail/#= transportOrderId #' >#= clientOrderNumber# </a>",
            width: 110
        }, {
            field: "lnetOrderNumber",
            title: "新易泰单号",
            width: 130
        }, {
            field: "viceClientOrderNumber",
            title: "副单号",
            width: 90
        }, {
            field: "carrierOrderNumber",
            title: "托运单号",
            width: 110
        }, {
            field: "transportTypeName",
            title: "运输方式",
            width: 110
        }, {
            field: "orderDate",
            title: "订单日期",
            template: function (dataItem) {
                return kendo.toString(kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
            },
            width: 110
        }, {
            field: "sentDate",
            title: "发运时间",
            template: function (dataItem) {
                if (dataItem.sentDate)  return kendo.toString(kendo.parseDate(dataItem.sentDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
            width: 110
        }, {
            field: "expectedDate",
            title: "预计到达时间",
            template: function (dataItem) {
                if (dataItem.expectedDate)  return kendo.toString(kendo.parseDate(dataItem.expectedDate, "yyyy-MM-dd HH:mm:ss"), "MM-dd HH:mm");
                return "";
            },
            width: 130
        }, {
            field: "startCity",
            title: "始发地",
            width: 90
        }, {
            field: "destCity",
            title: "目的地",
            width: 90
        }, {
            field: "receiveMan",
            title: "收货人",
            width: 90
        }, {
            field: "statusName",
            title: "订单状态",
            template:function (dataItem) {
                if(dataItem.status!=null) {
                    if (0 < dataItem.status && dataItem.status <= 3) {
                        var orderDate = kendo.parseDate(dataItem.orderDate, "yyyy-MM-dd");
                        var currentDate = kendo.parseDate(kendo.toString(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
                        if (orderDate < currentDate) {
                            return "<span style='color: #ff0000;font-weight: bold;'>发运延误</span>";
                        } else {
                            return dataItem.statusName;
                        }
                    } else if (dataItem.status > 3 && dataItem.status < 6) {
                        var expectedDate = kendo.parseDate(dataItem.expectedDate, "yyyy-MM-dd HH:mm:ss");
                        if (expectedDate && expectedDate < new Date()) {
                            return "<span style='color: #ff0000;font-weight: bold;'>在途延误</span>";
                        } else {
                            return dataItem.statusName;
                        }
                    } else {
                        return dataItem.statusName;
                    }
                }else{
                    return "";
                }
            },
            width: 110
        },{
            field: "mergeStatus",
            title: "合单状态",
            width: 110,
            values:ko.toJS(commonData.transportMergeStatus)
        }];

        self.selectedOrder = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedOrder(dataItem);
        };
        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length;i++){
                select.push("tr:eq("+i+")");
            }
            var grid = $(".k-grid").data("kendoGrid");
            grid.select(select.toString());
        }
        self.canModifyNumber = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().isTemp;
        });

        self.canConfirm = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().status == 1;
        });
        self.canRepealMerge=ko.computed(function(){
           return self.selectedOrder() && self.selectedOrder().mergeStatus==3;
        });
        self.canRepealConfirm=ko.computed(function(){
            return self.selectedOrder() && self.selectedOrder().status == 2 && self.selectedOrder().mergeStatus==1;
        });
        self.canModify = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().status == 1;
        });
        self.importOrder = function () {
            var url = "otdTransportOrder/importOrder";
            router.navigate(url);
        };
        self.canSign = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().status == 5;
        });

        self.canCancel = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().status ==1 && self.selectedOrder().mergeStatus==1;
        });

        self.canRepealCancel = ko.computed(function () {
            return self.selectedOrder() && self.selectedOrder().status ==-1;
        });

        self.modify = function () {
            var url = "#/otdTransportOrder/modify/" + self.selectedOrder().transportOrderId;
            router.navigate(url);
        };

        self.repealConfirm=function(){
            var rows = self.widget().select();
            var dataItem = self.widget().dataItem(rows[0]);
            var message="确定撤销客户订单号为["+dataItem.clientOrderNumber+"]的审单吗？";
            if (!confirm(message)) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/repealConfirm",
                data: ko.toJSON(dataItem.transportOrderId)
            }).done(function (result) {
                if (result.success === true) {
                    notify("撤销审单成功", "success");
                    self.dataSource.query();
                }
                else {
                    notify(result.message, "error");
                }
            });
        };

        self.repealMerge=function(){
            var rows = self.widget().select();
            var dataItem = self.widget().dataItem(rows[0]);
            var message="确定撤销客户订单号为["+dataItem.clientOrderNumber+"]的合单吗？";
            if (!confirm(message)) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/repealMerge",
                data: ko.toJSON(dataItem.transportOrderId)
            }).done(function (result) {
                if (result.success === true) {
                    notify("撤销合单成功", "success");
                    self.dataSource.query();
                }
                else {
                    notify(result.message, "error");
                }
            });
        };

        self.merge=function(){
            /*var rows = self.widget().select();
            if(rows.length<2){
                notify("请至少选择2单运输订单！","error");
                return;
            }
            var clientId=self.widget().dataItem(rows[0]).clientId;
            var selectIds=new Array();
            var j=rows.length;
            for(var i=0;i<rows.length;i++){
                var row=rows[i];
                var dataItem = self.widget().dataItem(row);
                if(clientId != dataItem.clientId ){
                    notify("请选择同一个客户的运输订单！","error");
                    return;
                }
                if(dataItem.mergeStatus!=1){
                    notify("请选择未合单的运输订单！","error");
                    return;
                }
                if(dataItem.status==-1){
                    notify("请选择未取消的运输订单！","error");
                    return;
                }
                selectIds.push(dataItem.transportOrderId);
            }
            if(j>10){
                notify("选择数据过多，请先选择任意两条数据合单，合单后进入合单详情中再添加其他被合单！","error");
                return;
            }*/
            var url = "otdTransportOrder/merge";
            router.navigate(url);
        };

        self.modifyNumber = function () {
            var url = "otdTransportOrder/modifyNumber/"+self.selectedOrder().transportOrderId;
            modal("修改客户单号", url, { width: 400, height: 400, close:function(){
                self.selectedOrder("");
                self.dataSource.query();
            }});
        }
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

        self.confirm = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            if(self.widget().select().length>1){
                for(var i=0;i<rows.length;i++){
                    var row=rows[i];
                    var dataItem = self.widget().dataItem(row);
                    if (dataItem) {
                        if(dataItem.status!=1){
                            notify("请选择接单中的运输订单","error");
                            return;
                        }
                        selectedIds.push(dataItem.transportOrderId);
                    }
                }
                if (!confirm("确定批量审单吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdTransportOrder/batchConfirm",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("批量审单成功", "success");
                        self.dataSource.query();
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            }else if(self.widget().select().length==1) {
                var url = "#/otdTransportOrder/confirm/" + self.selectedOrder().transportOrderId;
                router.navigate(url);
            }
        }

        self.cancel = function () {
            if (!confirm("确定取消该订单吗？")) return;

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/cancel",
                data: ko.toJSON(self.selectedOrder().transportOrderId)
            }).done(function (result) {
                if (result.success === true) {
                    notify("取消成功", "success");
                    self.dataSource.query();
                }
                else {
                    notify(result.message, "error");
                }
            });
        };

        self.repealCancel=function(){
            if (!confirm("确定还原该订单吗？")) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/repealCancel",
                data: ko.toJSON(self.selectedOrder().transportOrderId)
            }).done(function (result) {
                if (result.success === true) {
                    notify("操作成功!", "success");
                    self.dataSource.query();
                }
                else {
                    notify(result.message, "error");
                }
            });
        };

        self.clientOrderNumber = ko.observable();
        self.lnetOrderNumber = ko.observable();
        self.orderBeginDate = ko.observable();
        self.orderEndDate = ko.observable();
        self.sentDateBegin = ko.observable();
        self.sentDateEnd = ko.observable();
        self.clientId = ko.observable();
        self.orderStatus = ko.observable(1);
        self.transportType= ko.observable();
        self.receiveMan=ko.observable();
        self.destCity = ko.observable();
        self.receiveCompany=ko.observable();
        self.mergeStatus=ko.observable();
        self.viceClientOrderNumber=ko.observable();
        self.mergeStatusList=commonData.transportMergeStatus;
        self.orderStatuList = commonData.orderStatus;
        self.transportTypes = commonData.transportType;
        self.transportMergeStatus=commonData.transportMergeStatus;
        self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
        self.search = function () {
            var clientOrderNumber = $.trim(self.clientOrderNumber());
            var lnetOrderNumber = $.trim(self.lnetOrderNumber());
            var orderBeginDate = $.trim(self.orderBeginDate());
            var orderEndDate = $.trim(self.orderEndDate());
            var sentDateBegin = $.trim(self.sentDateBegin());
            var sentDateEnd = $.trim(self.sentDateEnd());
            var clientId = $.trim(self.clientId());
            var destCity = $.trim(self.destCity());
            var orderStatus = $.trim(self.orderStatus());
            var transportType = $.trim(self.transportType());
            var receiveMan = $.trim(self.receiveMan());
            var receiveCompany= $.trim(self.receiveCompany());
            var mergeStatus= $.trim(self.mergeStatus());
            var viceClientOrderNumber= $.trim(self.viceClientOrderNumber());
            var filters = new Array();
            if (clientOrderNumber) {
                filters.push({
                    field: "clientOrderNumber",
                    operator: "contains",
                    value: clientOrderNumber
                });
            }
            if (transportType) {
                filters.push({
                    field: "transportType",
                    operator: "eq",
                    value: transportType
                });
            }
            if (lnetOrderNumber) {
                filters.push({
                    field: "lnetOrderNumber",
                    operator: "contains",
                    value: lnetOrderNumber
                });
            }
            if (destCity) {
                filters.push({
                    field: "destCity",
                    operator: "contains",
                    value: destCity
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
            if (orderStatus) {
                filters.push({
                    field: "status",
                    operator: "eq",
                    value: orderStatus
                });
            }
            if (mergeStatus) {
                filters.push({
                    field: "mergeStatus",
                    operator: "eq",
                    value: mergeStatus
                });
            }
            if (viceClientOrderNumber) {
                filters.push({
                    field: "viceClientOrderNumber",
                    operator: "contains",
                    value: viceClientOrderNumber
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

    $(function () {
        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $(event.target).trigger("blur");
                viewModel.search();
            }
        });
    });
    $(window).on('resize', function () {
        if(!$(".k-grid.k-widget").parent().is("form")){
            $(".k-grid.k-widget").height(getGridHeight());
        }
    });
</script>