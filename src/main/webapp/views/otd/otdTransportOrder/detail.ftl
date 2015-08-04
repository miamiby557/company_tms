<#import "/utils/readOnlyDropdown.ftl" as utils >
<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdTransportOrderView"><i class="fa fa-users"></i> 运输订单</a></li>
    <li><a href="#/otdTransportOrder/detail/${otdTransportOrder.transportOrderId}"><i class="fa fa-plus"></i> 订单详情</a>
    </li>
</ol>

<div>
    <form id="detail-form" method="post" data-bind="submit:save" class="form-horizontal">
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
                <div data-bind="with:model" class="col-6">
                <@form.koStatusPanel data="$root.orderStatus" field="status" label="订单状态："/>
                    <div class="form-group">
                        <label for="clientId">客户</label>
                    <@utils.koComboBox data="$root.clientIds" value="clientId" textField="name" valueField="clientId" />
                    </div>
                    <div class="form-group">
                        <label for="pickupOrderId">提货单号</label>
                    <@utils.koComboBox data="$root.pickupOrders" value="pickupOrderId" textField="pickupOrderNumber" valueField="pickupOrderId"/>
                    </div>
                    <div class="form-group">
                        <label for="clientOrderNumber">客户单号</label>
                        <input type="text" name="clientOrderNumber" data-bind="value:clientOrderNumber" id="clientOrderNumber" class="k-input k-textbox" disabled="disabled">
                    </div>
                    <div class="form-group">
                        <label for="viceClientOrderNumber" >副单号</label>
                        <input type="text" name="viceClientOrderNumber" id="viceClientOrderNumber"data-bind="value:viceClientOrderNumber"  class="k-input k-textbox" maxlength="25">
                    </div>
                    <div class="form-group">
                        <label for="marketClientNumber" >商超单号</label>
                        <input type="text" name="marketClientNumber" id="marketClientNumber"data-bind="value:marketClientNumber"  class="k-input k-textbox" maxlength="50">
                    </div>
                    <div class="form-group">
                        <label for="lnetOrderNumber">新易泰单号</label>
                        <input type="text" name="lnetOrderNumber" id="lnetOrderNumber" data-bind="value:lnetOrderNumber" class="k-input k-textbox" disabled="disabled">
                    </div>
                    <div class="form-group">
                        <label for="orderDate">订单日期</label>
                        <input type="date" id="orderDate" data-bind="kendoDatePicker:orderDate" name="orderDate" readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="orderType">订单类型</label>
                    <@utils.koDropDown data="$root.orderTypeList" value="orderType"/>
                    </div>
                   <#-- <div class="form-group">
                        <label for="consignerMan">发货人</label>
                        <input type="text" name="consignerMan" id="consignerMan" data-bind="value:consignerMan" class="k-input k-textbox"readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="consignerPhone">发货人电话</label>
                        <input type="text" name="consignerPhone" id="consignerPhone" data-bind="value:consignerPhone" class="k-input k-textbox"readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="consignerAddress">发货地址</label>
                        <textarea class="k-textbox" name="consignerAddress"  id="consignerAddress"rows="5"data-bind="value:consignerAddress"readonly="true"></textarea>
                    </div>-->
                    <div class="form-group">
                        <label for="transportType">运输方式</label>
                    <@utils.koDropDown data="$root.transportTypeList" value="transportType"/>
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
                        <label for="receiveCompany">收货公司</label>
                        <input type="text" name="receiveCompany" id="receiveCompany" data-bind="value:receiveCompany" class="k-input k-textbox"readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="receiveMan">收货人</label>
                        <input type="text" name="receiveMan" id="receiveMan" data-bind="value:receiveMan" class="k-input k-textbox"readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="receivePhone">收货人电话</label>
                        <input type="text" name="receivePhone" id="receivePhone" data-bind="value:receivePhone" class="k-input k-textbox"readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="receiveAddress">收货地址</label>
                        <textarea class="k-textbox" name="receiveAddress"  id="receiveAddress"rows="5"data-bind="value:receiveAddress"readonly="true"></textarea>
                    </div>
                </div>

                <div data-bind="with:model" class="col-6">
                    <div class="form-group">
                        <label for="orderDispatchType">配送类型</label>
                    <@utils.koDropDown data="$root.orderDispatchTypeList" value="orderDispatchType"/>
                    </div>
                    <div class="form-group">
                        <label for="handoverType">交接方式</label>
                    <@utils.koDropDown data="$root.handoverTypeList" value="handoverType"/>
                    </div>
                    <div class="form-group">
                        <label for="confirmedItemQuantity">实际数量</label>
                        <input type="number" name="confirmedItemQuantity" data-bind="value:confirmedItemQuantity" id="confirmedItemQuantity" class="k-input k-textbox" min="1" readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="confirmedPackageQuantity">应收总件数</label>
                        <input type="number" name="confirmedPackageQuantity" data-bind="value:confirmedPackageQuantity" id="confirmedPackageQuantity" class="k-input k-textbox" min="1" readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="confirmedVolume">应收体积（立方）</label>
                        <input type="text" name="confirmedVolume" id="confirmedVolume" data-bind="value:confirmedVolume" class="k-input k-textbox" min="0" readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="confirmedWeight">应收重量（公斤）</label>
                        <input type="text" name="confirmedWeight" id="confirmedWeight" data-bind="value:confirmedWeight" class="k-input k-textbox" min="0" readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="specialRequest">特殊要求</label>
                        <textarea class="k-textbox" name="specialRequest" id="specialRequest" rows="5" data-bind="value:specialRequest"readonly="true"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="billingCycle">结算周期</label>
                    <@utils.koDropDown data="$root.settleCycleList" value="billingCycle"/>
                    </div>
                    <div class="form-group">
                        <label for="paymentType">支付方式</label>
                    <@utils.koDropDown data="$root.paymentTypeList" value="paymentType"/>
                    </div>
                    <div class="form-group">
                        <label for="calculateType">计费方式</label>
                    <@utils.koDropDown data="$root.calculateTypeList" value="calculateType"/>
                    </div>
                    <div class="form-group">
                        <label for="wrapType">包装类型</label>
                    <@utils.koDropDown data="$root.wrapTypeList" value="wrapType"/>
                    </div>
                    <div class="form-group">
                        <label for="urgencyLevel">紧急程度</label>
                    <@utils.koDropDown data="$root.urgencyLevel" value="urgencyLevel"/>
                    </div>
                    <div class="form-group">
                        <label for="expectedDate" >预计到货日期</label>
                        <input type="date"  id="expectedDate" data-bind="kendoDateTimePicker:expectedDate" name="expectedDate"readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="remark">备注</label>
                        <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark"readonly="true"></textarea>
                    </div>
                </div>
                <div class="clear">
                    <h3>订单明细</h3>
                    <div id="details-grid-wrap">
                        <div data-bind="kendoGrid: {widget: $root.detailsGrid, data: $root.detailsDataSource, sortable: true, editable:false, columns: $root.detailColumns, toolbar:$root.detailToolbar }"></div>
                    </div>
                </div>
            </div>
            <div>
                <div id="details-grid-wrap">
                    <div data-bind="kendoGrid: {widget: $root.logsGrid, data: $root.logsDataSource, sortable: true, editable:false, columns: $root.logColumns, toolbar:$root.logToolbar }"></div>
                </div>
            </div>
        </div>
        <div style="margin-top: 20px">
            <button type="button" data-bind="kendoButton:modify, enable:canConfirm">
                <i class="fa fa-edit"></i> 修改
            </button>
            <button type="button" data-bind="kendoButton:confirm, enable:canConfirm">
                <i class="fa fa-edit"></i> 审单
            </button>
            <button type="button" data-bind="kendoButton:cancel, enable:canConfirm">
                <i class="fa fa-edit"></i> 取消
            </button>
            <button type="button" data-action="goBack" class="k-button k-button-icontext">
                <i class="fa fa-close"></i> 返回
            </button>
        </div>
    </form>
</div>
<script>
    function CreateModel() {
        var self = this;
        self.model = ko.mapping.fromJS(${otdTransportOrderJson});
        self.orderTypeList = commonData.orderType;
        self.handoverTypeList = commonData.handoverType;
        self.calculateTypeList = commonData.calculateType;
        self.transportTypeList = commonData.transportType;
        self.settleCycleList = commonData.settleCycle;
        self.paymentTypeList = commonData.paymentType;
        self.urgencyLevel=commonData.urgencyLevel;
        self.wrapTypeList=commonData.wrapType;
        self.orderStatus =commonData.orderStatus ;
        self.orderDispatchTypeList = commonData.orderDispatchType;
        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.pickupOrders = kendo.utils.createDataSource("pickupOrderId", "/otdPickupOrder/getDataSource");
        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");

        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({data:${otdTransportOrderDetailsJson}});
        self.canConfirm = ko.computed(function () {
            return self.model.status() == 1;
        });
        self.modify = function () {
            var url = "#/otdTransportOrder/modify/" + self.model.transportOrderId();
            router.navigate(url);
        };
        self.confirm = function () {
            var url = "#/otdTransportOrder/confirm/" + self.model.transportOrderId();
            router.navigate(url);
        }
        self.cancel = function () {
            if (!confirm("确定取消该订单吗？")) return;

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/cancel",
                data: ko.toJSON(self.model.transportOrderId())
            }).done(function (result) {
                if (result.success === true) {
                    notify("取消成功", "success");
                    router.navigate("#/otdTransportOrder");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
        self.detailColumns = [{
            field: "goodsCode",
            title: "货物编码",
        }, {
            field: "goodsName",
            title: "货物名称",
        }, {
            field: "goodsType",
            title: "货物型号"
        }, {
            field: "totalItemQuantity",
            title: "数量",
        }, {
            field: "totalPackageQuantity",
            title: "件数",
        }, {
            field: "totalVolume",
            title: "体积",
        }, {
            field: "totalWeight",
            title: "重量"
        }, {
            field: "remark",
            title: "备注"
        }];

        self.logsGrid = ko.observable();
        self.logsDataSource = new kendo.data.DataSource({data:${otdTransportOrderLogsJson}});
        self.logColumns = [{
            field: "currentStatus",
            title: "订单状态",
            width: 120,
            values:ko.toJS(commonData.orderStatus)
        },  {
            field: "createUserName",
            title: "操作人",
            width: 120
        },  {
            field: "operationDate",
            title: "操作时间",
            width: 120
        },  {
            field: "operationContent",
            title: "操作内容",
            width: 120
        },{
            field: "isAbnormal",
            title: "是否异常",
            template: '<input type="checkbox" #= isAbnormal ? "checked=checked" : "" # disabled="disabled" />',
            width: 120
        },{
            field: "remark",
            title: "备注",
            width: 120
        }];

        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#detail-form").validate().valid()) return;
            self.model.details = self.detailsDataSource.data();

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/update",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("修改成功！", "success");
                    router.navigate("/otdTransportOrderView");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
    }
    $(function () {
        $("#detail-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("detail-form"));
    });
</script>