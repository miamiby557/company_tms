<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysRole"><i class="fa fa-users"></i> 角色管理</a></li>
    <li><a href="#/sysRole/create"><i class="fa fa-plus"></i> 增加角色</a></li>
</ol>

<div>
    <form id="role-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div class="col-6" data-bind="with:model">
            <div class="form-group">
                <label for="code">编码</label>
                <input type="text" class="k-input k-textbox" name="code" id="code" data-bind="value:code" required="required" minlength="2" maxlength="50">
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

            <div class="form-group">
                <label for="remark">功能</label>
                <span data-bind="foreach:$root.allFunctions">
                    <input type="checkbox" data-bind="value:functionId, checked:checked, attr: { id: 'function-' + code}">
                    <label data-bind="text:name, attr: { for: 'function-' + code}"></label>
                </span>
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
        <#--<div class="col-6">
            <#list allFunctions as function>
                <input name="functionId" id="function-${function.code}" value="${function.functionId}" type="checkbox"/> <label for="function-${function.code}">${function.name}</label>
            </#list>
        </div>-->
    </form>
</div>
<script>
    function SysRoleCreateModel() {
        var self = this;
        self.allFunctions = ko.observableArray(${allFunctions});
        self.model = {};
        self.model.roleId = ko.observable();
        self.model.name = ko.observable();
        self.model.code = ko.observable();
        self.model.isActive = ko.observable(true);
        self.model.remark = ko.observable();
        self.model.sysFunctions = ko.observableArray([]);

        for (var i in self.allFunctions()) {
            self.allFunctions()[i].checked = ko.computed({
                read: function (data) {
                    for (var j in self.model.sysFunctions()) {
                        if (self.model.sysFunctions()[j].functionId == this.functionId) return true;
                    }
                    return false;
                }, write: function (value) {
                    if (value == true) {
                        self.model.sysFunctions.push(this)
                    } else {
                        for (var j in self.model.sysFunctions()) {
                            if (self.model.sysFunctions()[j].functionId == this.functionId) self.model.sysFunctions.remove(self.model.sysFunctions()[j]);
                        }
                    }
                },
                owner: self.allFunctions()[i]
            });
        }

        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#role-form").validate().valid()) return;
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/sysRole/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加用户成功", "success");
                    router.navigate("/sysRole");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        $("#role-form").validate();
        var viewModel = new SysRoleCreateModel();
        ko.applyBindings(viewModel, document.getElementById("role-form"));
    });


</script>