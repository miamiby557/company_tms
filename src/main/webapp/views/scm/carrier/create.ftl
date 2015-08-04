<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/scmCarrierView"><i class="fa fa-users"></i> 承运商档案</a></li>
    <li><a href="#/scmCarrier/create"><i class="fa fa-plus"></i> 新增承运商</a></li>
</ol>

<div>

    <form id="create-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div class="col-6" data-bind="with:model">
            <div class="form-group">
                <label for="code">编码</label>
                <input type="text" class="k-input k-textbox" name="code" id="code" data-bind="value:code" required="required" minlength="3" maxlength="50"
                       existCode="/scmCarrier/existCode?carrierId=${scmCarrier.carrierId}">
            </div>
            <div class="form-group">

                <label for="name">名称</label>
                <input type="text" class="k-input k-textbox" name="name" id="name" data-bind="value:name" required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="type">类型</label>
            <@utils.koDropDown data="$root.carrierType" value="type"required=true/>
            </div>
            <div class="form-group">
                <label for="grade">等级</label>
            <@utils.koDropDown data="$root.carrierGrade" value="grade"required=true/>
            </div>
            <div class="form-group">
                <label for="serviceStartDate">服务开始时间</label>
                <input type="date" name="serviceStartDate" id="serviceStartDate" data-bind="kendoDatePicker:serviceStartDate" required="required">
            </div>
            <div class="form-group">
                <label for="serviceEndDate">服务结束时间</label>
                <input type="date" name="serviceEndDate" id="serviceEndDate"  compareDate="#serviceStartDate" required="required" data-bind="kendoDatePicker:serviceEndDate" >
            </div>
            <div class="form-group">
                <label for="contactMan">联系人</label>
                <input type="text" class="k-input k-textbox" name="contactMan" id="contactMan" data-bind="value:contactMan" required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="contactPhone">联系电话</label>
                <input type="text" class="k-input k-textbox" name="contactPhone"   id="contactPhone" data-bind="value:contactPhone" maxlength="25"
                       >
            </div>
            <div class="form-group">
                <label for="contactAddress">联系地址</label>
                <textarea id="contactAddress" name="contactAddress" class="k-textbox" rows="5"data-bind="value:contactAddress"maxlength="250"></textarea>
            </div>
            <div class="form-group">
                <label for="cityId">城市</label>
            <@utils.koComboBox data="$root.citys" value="cityId" textField="name" valueField="regionId" />
            </div>
        </div>
        <div class="col-6" data-bind="with:model">
            <div class="form-group">
                <label for="invoiceTitle">发票抬头</label>
                <input type="text" class="k-input k-textbox" name="invoiceTitle" id="invoiceTitle" data-bind="value:invoiceTitle" maxlength="25">
            </div>
            <div class="form-group">
                <label for="taxCode">税号</label>
                <input type="text" class="k-input k-textbox" name="taxCode"   id="taxCode" data-bind="value:taxCode" maxlength="25">
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
                <input type="text" class="k-input k-textbox" name="receiveBank" id="receiveBank" data-bind="value:receiveBank" maxlength="25">
            </div>
            <div class="form-group">
                <label for="receiver">收款人</label>
                <input type="text" class="k-input k-textbox" name="receiver" id="receiver" data-bind="value:receiver"maxlength="25" >
            </div>
            <div class="form-group">
                <label for="receiveAccount">收款账号</label>
                <input type="text" class="k-input k-textbox" name="receiveAccount" id="receiveAccount" data-bind="value:receiveAccount" maxlength="25">
            </div>
            <div class="form-group">
                <input type="checkbox" name="isIncludeOrderDate" id="isIncludeOrderDate" data-bind="checked:isIncludeOrderDate">
                <label for="isIncludeOrderDate">时效是否包含当天</label>
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark" maxlength="250"></textarea>
            </div>
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 保存
            </button>

            <button type="button" data-action="goBack" class="k-button k-button-icontext">
                <i class="fa fa-close"></i> 取消
            </button>
        </div>
    </form>

</div>
<script>
    function CreateModel() {
        var self = this;
        self.model = {};
        self.model.branchId = ko.observable();
        self.model.name = ko.observable();
        self.model.code = ko.observable();
        self.model.type = ko.observable();
        self.model.grade = ko.observable();
        self.model.serviceStartDate = ko.observable();
        self.model.serviceEndDate = ko.observable();
        self.model.contactMan = ko.observable();
        self.model.contactPhone = ko.observable();
        self.model.contactAddress = ko.observable();
        self.model.city = ko.observable();
        self.model.cityId = ko.observable();
        self.model.invoiceTitle = ko.observable();
        self.model.taxCode = ko.observable();
        self.model.settleCycle = ko.observable();
        self.model.paymentType = ko.observable();
        self.model.receiveBank = ko.observable();
        self.model.receiver = ko.observable();
        self.model.receiveAccount = ko.observable();
        self.model.remark = ko.observable();
        self.model.certificateAffixId = ko.observable();
        self.model.isIncludeOrderDate = ko.observable(true);
        self.carrierType = commonData.carrierType;
        self.carrierGrade = commonData.carrierGrade;
        self.settleCycle = commonData.settleCycle;
        self.paymentType = commonData.paymentType;

        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#create-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/scmCarrier/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加成功！", "success");
                    router.navigate("/scmCarrier/createFile/"+result.content);
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
    }
    $(function () {
        $("#create-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("create-form"));
    });
</script>