<div id="form-client-details">
<form id="modify-form" method="post"  class="form-horizontal">
    <div data-bind="with:model" class="col-6">
        <div class="form-group">
            <label for="code">客户编码</label>
            <input type="text" readonly="true" class="k-input k-textbox" name="code" id="code"
                   data-bind="value:code" required="required" maxlength="50">
        </div>
        <div class="form-group">
            <label for="name">客户名称</label>
            <input type="text" readonly="true" class="k-input k-textbox" name="name" id="name"
                   data-bind="value:name" required="required" maxlength="25">
        </div>
        <div class="form-group">
            <label for="fullName">客户全名</label>
            <input type="text" readonly="true" class="k-input k-textbox" name="fullName" id="fullName"
                   data-bind="value:fullName" required="required" maxlength="25">
        </div>
        <div class="form-group">
            <label for="grade">客户级别</label>
        <@utils.koDropDown data="$root.grades" value="grade" />
        </div>
        <div class="form-group">
            <label for="cityId">城市</label>
        <@utils.koComboBox data="$root.citys" value="cityId" textField="name" valueField="regionId" />
        </div>
        <div class="form-group">
            <label for="contactMan">联系人</label>
            <input type="text" readonly="true" class="k-input k-textbox" name="contactMan" id="contactMan"
                   data-bind="value:contactMan" required="required" maxlength="25">
        </div>
        <div class="form-group">
            <label for="contactPhone">联系电话</label>
            <input type="text" readonly="true" class="k-input k-textbox" name="contactPhone" id="contactPhone"
                   data-bind="value:contactPhone">
        </div>
        <div class="form-group">
            <label for="contactAddress">联系地址</label>
            <input type="text" readonly="true" class="k-input k-textbox" name="contactAddress"
                   id="contactAddress" data-bind="value:contactAddress">
        </div>
    </div>
    <div data-bind="with:model" class="col-6">
        <div class="form-group">
            <label for="serviceStartDate">服务开始时间</label>
            <input type="date" readonly="true" name="serviceStartDate" id="serviceStartDate"
                   data-bind="kendoDatePicker:serviceStartDate">
        </div>
        <div class="form-group">
            <label for="serviceEndDate">服务结束时间</label>
            <input type="date" name="serviceEndDate" id="serviceEndDate"
                   data-bind="kendoDatePicker:serviceEndDate">
        </div>
        <div class="form-group">
            <label for="settleCycle">结算周期</label>
        <@utils.koDropDown data="$root.settleCycles" value="settleCycle" />
        </div>
        <div class="form-group">
            <label for="paymentType">支付方式</label>
        <@utils.koDropDown data="$root.paymentTypes" value="paymentType" />
        </div>
        <div class="form-group">
            <label for="businessModel">商业模式</label>
        <@utils.koDropDown data="$root.businessModels" value="businessModel" />
        </div>
        <div class="form-group">
            <input type="checkbox" name="isIncludeOrderDate" id="isIncludeOrderDate" data-bind="checked:isIncludeOrderDate">
            <label for="isIncludeOrderDate">时效是否包含当天</label>
        </div>
        <div class="form-group">
            <label for="remark">备注</label>
                    <textarea id="remark" readonly="true" name="remark" data-bind="value:remark" class="k-textbox"
                              rows="5"></textarea>
        </div>
    </div>
    <div class="form-buttons">
        <button type="button" data-action="goBack" class="k-button k-button-icontext">
            <i class="fa fa-close"></i> 取消
        </button>
    </div>
</form>
</div>

<script>
    function ModifyModel() {
        var self = this;
        self.model = {};
        self.model = ko.mapping.fromJS(${crmClientJson});
        self.grades = commonData.clientGrade;
        self.businessModels = commonData.businessModel;
        self.settleCycles = commonData.settleCycle;
        self.paymentTypes = commonData.paymentType;
        self.citys = kendo.utils.createListDataSource("regionId","/baseRegion/getCityList");
    }

    $(function () {
        $("#modify-form").validate();
        var viewModel = new ModifyModel();
        ko.applyBindings(viewModel, document.getElementById("form-client-details"));
    });


</script>

