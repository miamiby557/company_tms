<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysFunction"><i class="fa fa-users"></i> 功能管理</a></li>
    <li><a href="#/sysFunction/modify/${sysFunction.functionId}"><i class="fa fa-edit"></i> 修改功能</a></li>
</ol>

<div>
    <form id="function-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="code">编码</label>
                <input type="text" class="k-input k-textbox" readonly="true" name="code" id="code" data-bind="value:code" required="required" minlength="6" maxlength="50">
            </div>
            <div class="form-group">
                <label for="name">名称</label>
                <input type="text" class="k-input k-textbox" name="name" id="name" data-bind="value:name" required="required" minlength="3" maxlength="50">
            </div>
            <div class="form-group">
                <input type="checkbox" name="isActive" id="isActive" data-bind="checked:isActive">
                <label for="isActive">是否启用</label>
            </div>

            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" data-bind="value:remark" class="k-textbox" rows="5"></textarea>
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
    function SysFunctionCreateModel() {
        var self = this;

        self.model = ${sysFunctionJson};
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#function-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/sysFunction/update",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("修改功能成功", "success");
                    router.navigate("/sysFunction");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        var viewModel = new SysFunctionCreateModel();
        ko.applyBindings(viewModel, document.getElementById("function-form"));
    });


</script>