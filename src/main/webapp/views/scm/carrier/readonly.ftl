<div id="form-carrier-details">

    <form id="modify-form" method="post" class="form-horizontal">
        <div class="col-6" data-bind="with:model">
            <div class="form-group">
                <label for="code">编码</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="code" id="code" data-bind="value:code" required="required" minlength="3" maxlength="50">
            </div>
            <div class="form-group">
                <label for="name">名称</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="name" id="name" data-bind="value:name" required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="type">类型</label>
            <@utils.koDropDown data="$root.carrierType" value="type"/>
            </div>
            <div class="form-group">
                <label for="grade">等级</label>
            <@utils.koDropDown data="$root.carrierGrade" value="grade"/>
            </div>
            <div class="form-group">
                <label for="serviceStartDate">服务开始时间</label>
                <input type="date" name="serviceStartDate" readonly="true" id="serviceStartDate" data-bind="kendoDatePicker:serviceStartDate" >
            </div>
            <div class="form-group">
                <label for="serviceEndDate">服务结束时间</label>
                <input type="date" name="serviceEndDate" readonly="true" id="serviceEndDate" data-bind="kendoDatePicker:serviceEndDate" >
            </div>
            <div class="form-group">
                <label for="contactMan">联系人</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="contactMan" id="contactMan" data-bind="value:contactMan" required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="contactPhone">联系电话</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="contactPhone"   id="contactPhone" data-bind="value:contactPhone" maxlength="25">
            </div>
            <div class="form-group">
                <label for="contactAddress">联系地址</label>
                <textarea id="contactAddress" name="contactAddress" class="k-textbox" rows="5"data-bind="value:contactAddress"maxlength="25"></textarea>
            </div>
            <div class="form-group">
                <label for="cityId">城市</label>
            <@utils.koComboBox data="$root.citys" value="cityId" textField="name" valueField="regionId" />
            </div>
        </div>
        <div class="col-6" data-bind="with:model">
            <div class="form-group">
                <label for="invoiceTitle">发票抬头</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="invoiceTitle" id="invoiceTitle" data-bind="value:invoiceTitle" maxlength="25">
            </div>
            <div class="form-group">
                <label for="taxCode">税号</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="taxCode"   id="taxCode" data-bind="value:taxCode" maxlength="25">
            </div>
            <div class="form-group">
                <label for="settleCycle">结算周期</label>
            <@utils.koDropDown data="$root.settleCycle" value="settleCycle"/>
            </div>
            <div class="form-group">
                <label for="paymentType">支付方式</label>
            <@utils.koDropDown data="$root.paymentType" value="paymentType"/>
            </div>
            <div class="form-group">
                <label for="receiveBank">收款银行</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="receiveBank" id="receiveBank" data-bind="value:receiveBank" maxlength="25">
            </div>
            <div class="form-group">
                <label for="receiver">收款人</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="receiver" id="receiver" data-bind="value:receiver"maxlength="25" >
            </div>
            <div class="form-group">
                <label for="receiveAccount">收款账号</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="receiveAccount" id="receiveAccount" data-bind="value:receiveAccount" maxlength="25">
            </div>
            <div class="form-group">
                <input type="checkbox" name="isIncludeOrderDate" id="isIncludeOrderDate" data-bind="checked:isIncludeOrderDate">
                <label for="isIncludeOrderDate">时效是否包含当天</label>
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" readonly="true" class="k-textbox" rows="5"data-bind="value:remark"maxlength="250"></textarea>
            </div>
        </div>
        <div class="form-buttons">
            <button type="button" data-action="goBack" class="k-button k-button-icontext">
                <i class="fa fa-close"></i> 返回
            </button>
        </div>
    </form>
</div>


<script>
    function CarrierDetailsViewModel() {
        var self = this;
        self.model = ko.mapping.fromJS(${scmCarrierJson});

        self.carrierType = commonData.carrierType;
        self.carrierGrade = commonData.carrierGrade;
        self.settleCycle = commonData.settleCycle;
        self.paymentType = commonData.paymentType;

        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");

    }
    $(function () {
        $("#detail-form").validate();
        var detailModel = new CarrierDetailsViewModel();
        ko.applyBindings(detailModel, document.getElementById("form-carrier-details"));
    });
</script>