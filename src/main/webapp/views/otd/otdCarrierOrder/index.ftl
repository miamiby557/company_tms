<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdCarrierOrder"><i class="fa fa-users"></i> 托运单</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">

        <div>
            <label for="carrierOrderNumber">托运单号</label><input class="k-textbox" data-bind="value:carrierOrderNumber">
            <label for="transportType">运输方式</label><@utils.koDropDown data="$root.transportTypeList" value="transportType"/>
            <label for="carrierId">承运商名称</label><@utils.koComboBox data="$root.carrierIds" value="carrierId" textField="name" valueField="carrierId"/>
            <label for="status">处理状态</label><@utils.koDropDown data="$root.statuss" value="status"/>
        </div>
        <div>
            <label for="consignee">收货人</label><input class="k-textbox" data-bind="value:consignee">
            <label for="sendStartDate">发运时间</label><input data-bind="kendoDateTimePicker:sendStartDate">
            <label for="sendEndDate">至</label><input data-bind="kendoDateTimePicker:sendEndDate">
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
        <div class="grid-buttons">
            <a data-role="button" href="#/otdCarrierOrder/create"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:modify, enable:canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
            <button type="button" data-bind="kendoButton:send, enable:canSend">
                <i class="fa fa-list"></i> 发运
            </button>
            <button type="button" data-bind="kendoButton:resetSend, enable:canReset">
                <i class="fa fa-list"></i> 发运撤销
            </button>
            <button type="button" data-bind="kendoButton:ok, enable:canOk">
                <i class="fa fa-list"></i> 完成
            </button>
            <button type="button" data-bind="kendoButton:cancel, enable:canCancel">
                <i class="fa fa-close"></i> 取消
            </button>
            <button type="button" data-bind="kendoButton:repealCancel, enable:canRepealCancel">
                <i class="fa fa-close"></i> 取消还原
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,toolbar:['excel','pdf'], editable:false, pageable: pageable, selectable: 'row',change:onChange}"></div>
</div>


<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.statuss = commonData.carrierOrderStatus;
            self.transportTypeList = commonData.transportType;
            self.carrierIds =  kendo.utils.createDataSource("carrierId", "/scmCarrier/getDataSource");
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("carrierOrderId", "/otdCarrierOrderView/getDataSource");
            self.dataSource.filter({
                logic: "and",
                filters: [{field: "status",operator: "eq",value: "1"}]
            });
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.carrierId = ko.observable();
            self.status = ko.observable(1);
            self.transportType = ko.observable("");
            self.sendStartDate = ko.observable("");
            self.sendEndDate = ko.observable("");
            self.consignee = ko.observable();
            self.carrierOrderNumber = ko.observable();
            self.clearFilter = function () {
                self.carrierId("");
                self.status("");
                self.transportType("");
                self.sendStartDate("");
                self.sendEndDate("");
                self.consignee("");
                self.carrierOrderNumber("");
                self.dataSource.filter([]);
            };
            self.selectedItem = ko.observable();
            self.onChange = function () {
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                self.selectedItem(dataItem);
            };

            self.canModify = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().status == 1;
            });
            self.canReset = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().status == 2&&self.selectedItem().payableStatus==1;
            });
            self.canSend= ko.computed(function () {
                return self.selectedItem() && self.selectedItem().status == 1;
            });
            self.canOk = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().status == 3;
            });
            self.canCancel = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().status ==1;
            });
            self.canRepealCancel = ko.computed(function () {
                return self.selectedItem() && self.selectedItem().status ==-1;
            });
            self.modify = function () {
                var url = "#/otdCarrierOrder/modify/" + self.selectedItem().carrierOrderId;
                router.navigate(url);
            };
            self.send = function () {
                var url = "#/otdCarrierOrder/send/" + self.selectedItem().carrierOrderId;
                router.navigate(url);
            }
            self.columns = [{
                title: "序号",
                template: function (dataItem) {
                    return self.dataSource.indexOf(dataItem)+1;
                },
                width: 40
            },{
                field: "carrierOrderNumber",
                title: "托运单号",
                template: "<a href='\\#/otdCarrierOrder/detail/#= carrierOrderId #' >#= carrierOrderNumber# </a>"
            }, {
                field: "name",
                title: "承运商名称"
            },{
                field: "transportTypeName",
                title: "运输方式"
            },  {
                field: "totalVolume",
                title: "总体积（立方）"
            }, {
                field: "totalPackageQuantity",
                title: "件数"
            },{
                field: "sendDate",
                title: "发运时间"
            },{
                field: "expectedDate",
                title: "预计到达时间"
            },{
                field: "consignee",
                title: "收货人"
            },{
                field: "statusName",
                title: "处理状态",
                template:function (dataItem) {
                    var expectedDate = kendo.parseDate(dataItem.expectedDate, "yyyy-MM-dd HH:mm:ss");
                    if(expectedDate&&expectedDate<new Date()&&dataItem.status<3&&dataItem.status>0){
                        return "<span style='color: #ff0000;font-weight: bold;'>在途延误</span>";
                    }else{
                        return dataItem.statusName;
                    }
                }
            }];
            //到达
            /*self.arrive = function () {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdCarrierOrder/arrive",
                    data: ko.toJSON( self.selectedItem().carrierOrderId)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("到达成功", "success");
                        self.dataSource.filter([]);
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            };*/
            //完成
            self.ok = function () {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdCarrierOrder/ok",
                    data: ko.toJSON( self.selectedItem().carrierOrderId)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("取消成功", "success");
                        self.dataSource.filter([]);
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            };
            self.resetSend = function () {
                if (!confirm("确定撤销该订单发运吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdCarrierOrder/resetSend",
                    data: ko.toJSON( self.selectedItem().carrierOrderId)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("撤销成功", "success");
                        self.dataSource.query();
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            };
            self.cancel = function () {
                if (!confirm("确定取消该订单吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdCarrierOrder/cancel",
                    data: ko.toJSON(self.selectedItem().carrierOrderId)
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
            self.repealCancel = function () {
                if (!confirm("确定还原该订单吗？")) return;
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/otdCarrierOrder/repealCancel",
                    data: ko.toJSON(self.selectedItem().carrierOrderId)
                }).done(function (result) {
                    if (result.success === true) {
                        notify("操作成功！", "success");
                        self.dataSource.query();
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            };
            self.search = function () {
                var carrierId = $.trim(self.carrierId());
                var carrierOrderNumber = $.trim(self.carrierOrderNumber());
                var status = $.trim(self.status());
                var consignee = $.trim(self.consignee());
                var startDate = $.trim(self.sendStartDate());
                var endDate = $.trim(self.sendEndDate());
                var transportType = $.trim(self.transportType());
                var filters = new Array();
                if(carrierOrderNumber){
                    filters.push({
                        field: "carrierOrderNumber",
                        operator: "contains",
                        value: carrierOrderNumber
                    });
                }if(consignee){
                    filters.push({
                        field: "consignee",
                        operator: "contains",
                        value: consignee
                    });
                }
                if(status){
                    filters.push({
                        field: "status",
                        operator: "eq",
                        value: status
                    });
                }
                if(carrierId){
                    filters.push({
                        field: "carrierId",
                        operator: "eq",
                        value: carrierId
                    });
                }
                if (startDate) {
                    filters.push({
                        field: "sendDate",
                        operator: "gte",
                        value: new Date(startDate)
                    });
                }
                if (endDate) {
                    filters.push({
                        field: "sendDate",
                        operator: "lte",
                        value: new Date(endDate)
                    });
                }
                if (transportType) {
                    filters.push({
                        field: "transportType",
                        operator: "eq",
                        value: transportType
                    });
                }
                if (filters)
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