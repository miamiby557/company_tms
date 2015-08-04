<#import "/utils/readOnlyDropdown.ftl" as utils >
<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdOrderLogListView"><i class="fa fa-users"></i> 信息跟踪</a></li>
    <li><a href="#/otdCarrierOrderLog/detail/${otdCarrierOrder.carrierOrderId}"><i class="fa fa-edit"></i>托运单详情</a></li>
</ol>

<div>
    <form id="modify-form" method="post" class="form-horizontal">
        <div id="tabstrip" data-role="tabstrip">
            <ul>
                <li class="k-state-active">
                    基本信息
                </li>
                <li>
                    操作日志
                </li>
            </ul>
            <div>
                <div class="col-6" data-bind="with:model">
                    <@form.koStatusPanel data="$root.carrierOrderStatuss" field="status" label="托运单状态："/>
                    <div class="form-group">
                        <label for="carrierOrderNumber">托运单号</label>
                        <input type="text" class="k-input k-textbox" name="carrierOrderNumber" readonly="true" id="carrierOrderNumber" data-bind="value:carrierOrderNumber" required="required" maxlength="50">
                    </div>
                    <div class="form-group">
                        <label for="carrierId">承运商名称</label>
                    <@utils.koComboBox data="$root.carrierIds" value="carrierId" textField="name" valueField="carrierId" />
                    </div>
                    <div class="form-group">
                        <label for="transferOrganizationId">中转站名称</label>
                    <@utils.koComboBox data="$root.organizations" value="transferOrganizationId" textField="name" valueField="organizationId" />
                    </div>
                    <div class="form-group">
                        <label for="consignee">收货人</label>
                        <input type="text" class="k-input k-textbox" name="consignee" id="consignee" data-bind="value:consignee"
                               required="required" readonly="true" maxlength="25">
                    </div>
                    <div class="form-group">
                        <label for="consigneePhone">收货电话</label>
                        <input type="text" class="k-input k-textbox" name="consigneePhone" id="consigneePhone"
                               data-bind="value:consigneePhone" maxlength="25"
                               readonly="true" >
                    </div>
                    <div class="form-group">
                        <label for="consigneeAddress">收货地址</label>
                        <textarea id="consigneeAddress" name="consigneeAddress" data-bind="value:consigneeAddress" readonly="true" class="k-textbox" rows="5"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="driver">司机</label>
                        <input type="text" class="k-input k-textbox" name="driver" readonly="true" id="driver" data-bind="value:driver" required="required" maxlength="25">
                    </div>
                    <div class="form-group">
                        <label for="driverPhone">司机电话</label>
                        <input type="text" class="k-input k-textbox" name="driverPhone" readonly="true" id="driverPhone" data-bind="value:driverPhone" required="required" maxlength="25">
                    </div>
                    <div class="form-group">
                        <label for="carType">车型</label>
                        <input type="text" class="k-input k-textbox" name="carType" readonly="true" id="carType" data-bind="value:carType" required="required" maxlength="25">
                    </div>
                    <div class="form-group">
                        <label for="carNumber">车牌号码</label>
                        <input type="text" class="k-input k-textbox" name="carNumber" readonly="true" id="carNumber" data-bind="value:carNumber" required="required" maxlength="25">
                    </div>
                    <div class="form-group">
                        <label for="goodsName">货物名称</label>
                        <input type="text" class="k-input k-textbox" name="goodsName" readonly="true" id="goodsName" data-bind="value:goodsName" maxlength="25">
                    </div>
                    <div class="form-group">
                        <label for="startCityId">始发城市</label>
                    <@utils.koComboBox data="$root.citys" value="startCityId" textField="name" valueField="regionId" />
                    </div>
                    <div class="form-group">
                        <label for="destCityId">目的城市</label>
                    <@utils.koComboBox data="$root.citys" value="destCityId" textField="name" valueField="regionId" />
                    </div>
                    <div class="form-group">
                        <label for="sendDate">发运时间</label>
                        <input type="date" name="sendDate" required="required" readonly="true" id="sendDate" data-bind="kendoDateTimePicker:sendDate" >
                    </div>
                    <div class="form-group">
                        <label for="expectedDate">预计到达时间</label>
                        <input type="date" name="expectedDate" required="required" readonly="true" id="expectedDate" data-bind="kendoDateTimePicker:expectedDate" >
                    </div>
                    <div class="form-group">
                        <label for="receiptPageNumber">回单份数</label>
                        <input type="number" class="k-input k-textbox" required="required" readonly="true"  name="receiptPageNumber" id="receiptPageNumber" data-bind="value:receiptPageNumber">
                    </div>
                </div>

                <div class="col-6" data-bind="with:model">
                    <div class="form-group">
                        <label for="totalVolume">总体积</label>
                        <input type="number" class="k-input k-textbox" name="totalVolume" readonly="true" id="totalVolume" data-bind="value:totalVolume" required="required" maxlength="25">
                    </div>
                    <div class="form-group">
                        <label for="totalWeight">总重量</label>
                        <input type="number" class="k-input k-textbox" name="totalWeight" readonly="true" id="totalWeight" data-bind="value:totalWeight">
                    </div>
                    <div class="form-group">
                        <label for="totalItemQuantity">总商品数量</label>
                        <input type="number" class="k-input k-textbox" name="totalItemQuantity" readonly="true" id="totalItemQuantity" data-bind="value:totalItemQuantity">
                    </div>
                    <div class="form-group">
                        <label for="totalPackageQuantity">总件数</label>
                        <input type="number" class="k-input k-textbox" name="totalPackageQuantity" readonly="true" id="totalPackageQuantity" data-bind="value:totalPackageQuantity">
                    </div>
                    <div class="form-group">
                        <label for="paymentType">支付方式</label>
                    <@utils.koDropDown data="$root.paymentTypes" value="paymentType" />
                    </div>
                    <div class="form-group">
                        <label for="transportType">运输方式</label>
                    <@utils.koDropDown data="$root.transportTypes" value="transportType" />
                    </div>
                    <div class="form-group">
                        <label for="calculateType">计费方式</label>
                    <@utils.koDropDown data="$root.calculateTypes" value="calculateType" />
                    </div>
                    <div class="form-group">
                        <label for="handoverType">交接方式</label>
                    <@utils.koDropDown data="$root.handoverTypes" value="handoverType" />
                    </div>
                    <div class="form-group">
                        <input type="checkbox" name="isSign" id="isSign" data-bind="checked:isSign">
                        <label for="isSign">是否签回单</label>
                    </div>
                    <div class="form-group">
                        <label for="wrapType">包装类型</label>
                    <@utils.koDropDown data="$root.wrapTypes" value="wrapType" />
                    </div>
                    <div class="form-group">
                        <label for="remark">备注</label>
                        <textarea id="remark" name="remark" data-bind="value:remark" readonly="true" class="k-textbox" rows="5"></textarea>
                    </div>

                </div>

                <div class="clear">
                    <h3>托运单明细</h3>
                    <div id="details-grid-wrap">
                        <div data-bind="kendoGrid: {widget: $root.detailsGrid, data:  $root.detailsDataSource,sortable: true, editable:false, columns: $root.detailColumns }"></div>
                    </div>
                </div>
            </div>
            <div>
            <div>
                <button type="button" data-bind="kendoButton:addLog">
                    <i class="fa fa-plus"></i> 添加日志
                </button>
                <button type="button" data-bind="kendoButton:modify,enable:canModify">
                    <i class="fa fa-edit"></i> 修改日志
                </button>
            </div>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.logsGrid, data: $root.logsDataSource, sortable: true, editable:false, columns: $root.logColumns, toolbar:$root.logToolbar ,selectable: 'row',change:onChange}"></div>
            </div>
        </div>
        </div>
        <div class="form-buttons">
            <button type="button" data-bind="kendoButton:arrive, enable:canArrive">
                <i class="fa fa-save"></i> 到达
            </button>
            <button type="button" data-action="goBack" class="k-button k-button-icontext">
                <i class="fa fa-close"></i> 返回
            </button>
        </div>
    </form>
</div>
<script>
    function ModifyModel() {
        var self = this;
        self.model = {};
        self.model = ko.mapping.fromJS(${otdCarrierOrderJson});

        self.paymentTypes = commonData.paymentType;
        self.transportTypes = commonData.transportType;
        self.calculateTypes =commonData.calculateType;
        self.handoverTypes = commonData.handoverType;
        self.wrapTypes = commonData.wrapType;
        self.carrierOrderStatuss = commonData.carrierOrderStatus;
        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.carrierIds = kendo.utils.createListDataSource("carrierId", "/scmCarrier/getDataSource");
        self.otdTransportOrders = kendo.utils.createListDataSource("transportOrderId", "/otdTransportOrder/getOkTransportOrder");
        self.organizations = kendo.utils.createListDataSource("organizationId", "/sysOrganization/getDataSource");
        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({data:${transportOrderListJson}});
        self.arriveFalse=function(){
            self.canArrive(false);
        };

        self.detailColumns = [{
            field: "clientName",
            title: "客户",width:120
        }, {
            field: "clientOrderNumber",
            title: "客户单号",width:120
        },{
            field: "confirmedVolume",
            title: "体积",width:80
        }, {
            field: "confirmedWeight",
            title: "重量",width:80
        }, {
            field: "confirmedItemQuantity",
            title: "商品数量",width:80
        },{
            field: "confirmedPackageQuantity",
            title: "件数",width:80
        }];

        self.logsGrid = ko.observable();
        self.selectedLog = ko.observable();
        self.onChange = function () {
            var row = self.logsGrid().select()[0];
            var dataItem = self.logsGrid().dataItem(row);
            self.selectedLog(dataItem);
        };
        self.canModify = ko.computed(function () {
            return self.selectedLog() && self.selectedLog().isSystem;
        });
        self.logsDataSource =  kendo.utils.createDataSource("carrierOrderLogId", "/otdCarrierOrderLogView/dataSource?carrierOrderId=${otdCarrierOrder.carrierOrderId}");
        self.logColumns = [{
            field: "statusName",
            title: "订单状态",
            width: 120,
        },  {
            field: "createUserName",
            title: "操作人",
            width: 120
        },  {
            field: "operationDate",
            format:"{0:yyyy-MM-dd HH:mm:ss}",
            title: "操作时间",
            width: 120
        },  {
            field: "operationContent",
            title: "操作内容",
            width: 120
        },{
            title: "是否异常",
            field: "isAbnormal",
            template: '<input type="checkbox" #= isAbnormal ? "checked=checked" : "" # disabled="disabled" />',
            width: 120
        },{
            field: "remark",
            title: "备注",
            width: 120
        }];

        self.addLog = function(){
            var url ="otdCarrierOrderLog/create/${otdCarrierOrder.carrierOrderId}";
            modal("添加托运单日志", url, {width:500, height:600, close:function(){
                self.logsDataSource.query();
            }});
        }
        self.modify = function () {
            var url = "otdCarrierOrderLog/modify/"+self.selectedLog().carrierOrderLogId;
            modal("修改托运单日志", url, {
                width: 400, height: 500, close: function () {
                    self.logsDataSource.query();
                }
            });
        };
        self.canArrive =ko.observable("${otdCarrierOrder.status}"==2);
        //到达
        self.arrive = function () {
            var selectedIds = [];
            var carrierOrderId="${otdCarrierOrder.carrierOrderId}";
            selectedIds.push(carrierOrderId);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdCarrierOrder/batchArrive",
                data: ko.toJSON(selectedIds)
            }).done(function (result) {
                if (result.success === true) {
                    notify("到达成功", "success");
                    self.arriveFalse();
                    self.logsDataSource.query();
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        $("#modify-form").validate();
        var viewModel = new ModifyModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });


</script>