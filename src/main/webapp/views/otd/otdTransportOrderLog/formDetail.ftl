<#import "/utils/readOnlyDropdown.ftl" as utils >
<#import "/utils/form.ftl" as form >
<div id="detail">
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
            <input type="text" name="clientOrderNumber" readonly="true" data-bind="value:clientOrderNumber" id="clientOrderNumber" class="k-input k-textbox" disabled="disabled">
        </div>
            <div class="form-group">
                <label for="viceClientOrderNumber" >副单号</label>
                <input type="text" name="viceClientOrderNumber" id="viceClientOrderNumber"data-bind="value:viceClientOrderNumber"  class="k-input k-textbox" maxlength="50" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="marketClientNumber" >商超单号</label>
                <input type="text" name="marketClientNumber" id="marketClientNumber"data-bind="value:marketClientNumber"  class="k-input k-textbox" maxlength="50" readonly="readonly">
            </div>
        <div class="form-group">
            <label for="lnetOrderNumber">新易泰单号</label>
            <input type="text" name="lnetOrderNumber" readonly="true" id="lnetOrderNumber" data-bind="value:lnetOrderNumber" class="k-input k-textbox" disabled="disabled">
        </div>
        <div class="form-group">
            <label for="orderDate">订单日期</label>
            <input type="date" id="orderDate" data-bind="kendoDatePicker:orderDate" readonly="true" name="orderDate"/>
        </div>
        <div class="form-group">
            <label for="orderType">订单类型</label>
        <@utils.koDropDown data="$root.orderTypeList" value="orderType"/>
        </div>
        <#--<div class="form-group">
            <label for="consignerMan">发货人</label>
            <input type="text" name="consignerMan" id="consignerMan" readonly="true" data-bind="value:consignerMan" class="k-input k-textbox">
        </div>
        <div class="form-group">
            <label for="consignerPhone">发货人电话</label>
            <input type="text" name="consignerPhone" id="consignerPhone" readonly="true" data-bind="value:consignerPhone" class="k-input k-textbox">
        </div>
        <div class="form-group">
            <label for="consignerAddress">发货地址</label>
            <textarea class="k-textbox" name="consignerAddress"  id="consignerAddress"rows="5"data-bind="value:consignerAddress"></textarea>
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
            <input type="text" name="receiveCompany" id="receiveCompany" readonly="true" data-bind="value:receiveCompany" class="k-input k-textbox">
        </div>
        <div class="form-group">
            <label for="receiveMan">收货人</label>
            <input type="text" name="receiveMan" id="receiveMan" readonly="true" data-bind="value:receiveMan" class="k-input k-textbox">
        </div>
        <div class="form-group">
            <label for="receivePhone">收货人电话</label>
            <input type="text" name="receivePhone" id="receivePhone" readonly="true" data-bind="value:receivePhone" class="k-input k-textbox">
        </div>
        <div class="form-group">
            <label for="receiveAddress">收货地址</label>
            <textarea class="k-textbox" name="receiveAddress" readonly="true"  id="receiveAddress"rows="5"data-bind="value:receiveAddress"></textarea>
        </div>
    </div>

    <div data-bind="with:model" class="col-6">
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
            <textarea class="k-textbox" name="specialRequest" id="specialRequest" rows="5" readonly="true" data-bind="value:specialRequest"></textarea>
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
            <input type="date"  id="expectedDate" data-bind="kendoDateTimePicker:expectedDate" name="expectedDate">
        </div>
        <div class="form-group">
            <label for="remark">备注</label>
            <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark"></textarea>
        </div>
    </div>

    <div class="clear">
        <h3>订单明细</h3>
        <div id="details-grid-wrap">
            <div data-bind="kendoGrid: {widget: $root.detailsGrid, data: $root.detailsDataSource, sortable: true, editable:false, columns: $root.detailColumns, toolbar:$root.detailToolbar }"></div>
        </div>
    </div>
</div>
<script>
    function detailFormModel() {
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
        self.orderStatus=commonData.orderStatus;
        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.pickupOrders = kendo.utils.createDataSource("pickupOrderId", "/otdPickupOrder/getDataSource");
        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");


        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({data:${otdTransportOrderDetailsJson}});
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
        self.canSign = ko.observable("${otdTransportOrder.status}" == 5);
        self.changeSign=function(){
            self.canSign(false);
        }
    }

    $(function () {
        var model = new detailFormModel();
        ko.applyBindings(model, document.getElementById("detail"));
    });
</script>