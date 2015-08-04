<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/dispatchVehicle"><i class="fa fa-users"></i> 车辆管理</a></li>
    <li><a href="#/dispatchVehicle/modify/${dispatchVehicle.vehicleId}"><i class="fa fa-plus"></i> 修改车辆</a></li>
</ol>

<div>
    <form id="modify-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
        <@form.koText field="vehicleNumber" label="车牌号码" required="required" maxlength="25"></@form.koText>
        <@form.koDropDown field="vehicleType" data="$root.vehicleTypeList" label="车辆类型" required="required"/>
        <@form.koText field="driver" label="司机" ></@form.koText>
        <@form.koText field="driverPhone" label="司机电话"></@form.koText>
        <@form.koText field="vehicleSize" label="车厢尺寸" required="required" maxlength="25"></@form.koText>
        <@form.koText field="maxWeight" label="吨位" required="required" maxlength="25"></@form.koText>
        <@form.koDatePicker field="compactStartDate" label="合同开始时间"/>
        <@form.koDatePicker field="compactEndDate" label="合同结束时间"/>
        <@form.koTextarea field="vehicleContent" label="车辆信息" maxlength="250"/>
        <@form.koTextarea field="remark" label="备注" maxlength="250"/>

            <div class="form-buttons">
                <button type="submit" class="k-button k-button-icontext k-primary ">
                    <i class="fa fa-save"></i> 保存
                </button>

                <button type="button" data-action="goBack" class="k-button k-button-icontext">
                    <i class="fa fa-close"></i> 取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    function ModifyModel() {
        var self = this;
        self.model =ko.mapping.fromJS(${dispatchVehicleJson});
        self.vehicleTypeList = commonData.vehicleType;

        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/dispatchVehicle/update",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("修改成功", "success");
                    router.navigate("/dispatchVehicle");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
    }
    $(function () {
        $("#modify-form").validate();
        var viewModel = new ModifyModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });



</script>