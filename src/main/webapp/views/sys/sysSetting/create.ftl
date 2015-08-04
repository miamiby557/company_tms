<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysSetting"><i class="fa fa-user"></i> 系统参数</a></li>
    <li><a href="#/sysSetting/create"><i class="fa fa-plus"></i> 增加系统参数</a></li>
</ol>

<div>
    <form id="setting-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="name">名称</label>
                <input type="text" class="k-input k-textbox" name="name" id="name" data-bind="value:name" required="required" minlength="3" maxlength="50">
            </div>
            <div class="form-group">
                <label for="value">值</label>
                <input type="text" class="k-input k-textbox" name="value" id="value" data-bind="value:value" required="required" maxlength="50">
            </div>
            <div class="form-group">
                <input type="checkbox" name="isActive" id="isActive" data-bind="checked:isActive">
                <label for="isActive">是否启用</label>
            </div>

            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark"></textarea>
            </div>

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
    function sysSettingCreateModel() {
        var self = this;

        self.model = {};
        self.model.name = ko.observable();
        self.model.value = ko.observable();
        self.model.isActive = ko.observable(true);
        self.model.remark = ko.observable();

        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#setting-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/sysSetting/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加系统参数成功", "success");
                    router.navigate("/sysSetting");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        $("#setting-form").validate();
        var viewModel = new sysSettingCreateModel();
        ko.applyBindings(viewModel, document.getElementById("setting-form"));
    });


</script>