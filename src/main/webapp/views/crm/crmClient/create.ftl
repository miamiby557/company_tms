<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/crmClientView"><i class="fa fa-users"></i> 客户档案</a></li>
    <li><a href="#/crmClient/create"><i class="fa fa-plus"></i> 增加客户</a></li>
</ol>

<div>
    <form id="create-form" method="post" class="form-horizontal"data-bind="submit:save">
        <div data-bind="with:model"  class="col-6">
            <div class="form-group">
                <label for="code">客户编码</label>
                <input type="text" class="k-input k-textbox" name="code" id="code" data-bind="value:code" required="required" maxlength="50" existCode="/crmClient/existCode?clientId=">
            </div>
            <div class="form-group">
                <label for="name">客户名称</label>
                <input type="text" class="k-input k-textbox" name="name" id="name" data-bind="value:name" required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="fullName">客户全名</label>
                <input type="text" class="k-input k-textbox" name="fullName" id="fullName" data-bind="value:fullName" required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="grade">客户级别</label>
            <@utils.koDropDown data="$root.grades" value="grade" required=true/>
            </div>
            <div class="form-group">
                <label for="cityId">城市</label>
            <@utils.koComboBox data="$root.citys" value="cityId" textField="name" valueField="regionId" />
            </div>
            <div class="form-group">
                <label for="contactMan">联系人</label>
                <input type="text" class="k-input k-textbox"  name="contactMan" id="contactMan" data-bind="value:contactMan" required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="contactPhone">联系电话</label>
                <input type="text" class="k-input k-textbox" name="contactPhone" id="contactPhone" data-bind="value:contactPhone"maxlength="25"
                       >
            </div>
            <div class="form-group">
                <label for="contactAddress">联系地址</label>
                <textarea id="contactAddress" name="contactAddress"  data-bind="value:contactAddress"  class="k-textbox" rows="5"maxlength="250"></textarea>
            </div>
        </div>
        <div data-bind="with:model"  class="col-6">
            <div class="form-group">
                <label for="serviceStartDate">服务开始时间</label>
                <input type="date" name="serviceStartDate" id="serviceStartDate" data-bind="kendoDatePicker:serviceStartDate" required="required">
            </div>
            <div class="form-group">
                <label for="serviceEndDate">服务结束时间</label>
                <input type="date" name="serviceEndDate" id="serviceEndDate" compareDate="#serviceStartDate" data-bind="kendoDatePicker:serviceEndDate" required="required" >
            </div>
            <div class="form-group">
                <label for="settleCycle">结算周期</label>
            <@utils.koDropDown data="$root.settleCycles" value="settleCycle"  required=true/>
            </div>
            <div class="form-group">
                <label for="paymentType">支付方式</label>
            <@utils.koDropDown data="$root.paymentTypes" value="paymentType"  required=true/>
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
                <textarea id="remark" name="remark"  data-bind="value:remark"  class="k-textbox" rows="5"maxlength="250"></textarea>
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
        self.model={};
        self.model.clientId = ko.observable();
        self.model.name = ko.observable();
        self.model.code = ko.observable();
        self.model.fullName = ko.observable();
        self.model.grade = ko.observable("");
        self.model.contactMan = ko.observable();
        self.model.contactPhone = ko.observable();
        self.model.contactAddress = ko.observable();
        self.model.province = ko.observable();
        self.model.city = ko.observable();
        self.model.cityId = ko.observable();
        self.model.isIncludeOrderDate = ko.observable(true);
        self.model.remark = ko.observable();
        self.model.serviceStartDate = ko.observable();
        self.model.serviceEndDate = ko.observable();
        self.model.paymentType = ko.observable("");
        self.model.settleCycle = ko.observable("");
        self.model.contractDocumentId = ko.observable("");
        self.model.businessModel = ko.observable("");
        self.grades = commonData.clientGrade;
        self.businessModels = commonData.businessModel;
        self.settleCycles = commonData.settleCycle;
        self.paymentTypes = commonData.paymentType;
        self.citys = kendo.utils.createListDataSource("regionId","/baseRegion/getCityList");
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#create-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/crmClient/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加客户成功", "success");
                    router.navigate("/crmClient/createFile/"+result.content);
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