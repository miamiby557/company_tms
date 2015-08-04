<#import "/utils/form.ftl" as form >
<div>
    <form id="modify-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <@form.koText field="pickupOrderNumber" label="提货单号"  readonly = "true"/>
            <@form.koComboBox field="clientId" data="$root.clientIds" textField="name" valueField="clientId" label="客户名称" readonly="readonly"/>
            <@form.koDateTimePicker field="reservationTime" label="预约时间" required="required" />
            <@form.kendoDropDownTreeView field="cityId" textField="name" valueField="regionId" treeView="$root.treeView" loadUrl="/baseRegion/" label="提货城市"/>
            <@form.koTextarea field="address" label="提货地址" required="required"/>
            <@form.koNumberBox field="confirmedTotalVolume" label="确认体积(立方)" required="required"  />
            <@form.koNumberBox field="confirmedTotalWeight" label="确认重量(千克)"   />
            <@form.koIntegerBox field="confirmedTotalItemQty" label="确认数量" required="required"/>
            <@form.koIntegerBox field="confirmedTotalPackageQty" label="确认件数" required="required"/>
            <div class="form-buttons">
                <button type="submit" class="k-button k-button-icontext k-primary ">
                    <i class="fa fa-save"></i> 确认
                </button>

                <button type="button" data-action="closeModal" class="k-button k-button-icontext">
                    <i class="fa fa-close"></i> 取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    function ModifyModel() {
        var self = this;
        self.model = {};
        self.model = ko.mapping.fromJS(${otdPickupOrderJson});
        var businessRegions = kendo.utils.createHierarchicalDataSource("regionId","baseRegion/getChildrenData");
        self.treeView = {
            dataSource: businessRegions,
            dataTextField:"name"
        }
        self.citys = kendo.utils.createListDataSource("regionId","/baseRegion/getCityList");
        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            self.model.cityId($("#cityId").val());
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdPickupOrder/confirm",
                data: kendo.stringify(ko.toJS(self.model))
            }).done(function (result) {
                if (result.success === true) {
                    notify("确认成功", "success");
                    $(".k-window-content.k-content").data("kendoWindow").close();
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