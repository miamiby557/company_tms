<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysUser"><i class="fa fa-user"></i> 用户管理</a></li>
    <li><a href="#/sysUser/create"><i class="fa fa-plus"></i> 增加</a></li>
</ol>

<div>
    <form id="user-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="k-input k-textbox" name="username" id="username" data-bind="value:username"
                       required="required" minlength="3" maxlength="50">
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" autocomplete="off" class="k-input k-textbox" name="password" id="password"
                       data-bind="value:password" required="required" minlength="6" maxlength="50">
            </div>
            <div class="form-group">
                <label for="password">确认密码</label>
                <input type="password" autocomplete="off" class="k-input k-textbox" name="confirmPassword"
                       id="confirmPassword" equalTo="#password">
            </div>
            <div class="form-group">
                <label for="fullName">姓名</label>
                <input type="text" class="k-input k-textbox" name="fullName" id="fullName" data-bind="value:fullName">
            </div>
            <div class="form-group">
                <label for="firstName">邮箱</label>
                <input type="email" class="k-input k-textbox" name="email" id="email" data-bind="value:email">
            </div>
            <div class="form-group">
                <label for="branchId">所属组织</label>
                <input id="branchId" data-bind="kendoDropDownTreeView:{valueField: 'organizationId',textField: 'name',treeView:$root.treeView,
                    value:branchId,loadUrl:'/sysOrganization/',change:$root.branchChange}" required="required"/>
            </div>
            <div class="form-group">
                <label for="siteId">所属站点</label>
                <@utils.koComboBox data="$root.sites" value="siteId" textField="name" valueField="organizationId" required=true/>
            </div>
            <div class="form-group">
                <label for="firstName">工号</label>
                <input type="text" class="k-input k-textbox" name="employeeNumber" id="employeeNumber"
                       data-bind="value:employeeNumber">
            </div>

            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark"></textarea>
            </div>
            <div class="form-group">
                <input type="checkbox" name="isAllowLogin" id="isAllowLogin" data-bind="checked:isAllowLogin">
                <label for="isAllowLogin">允许登录</label>
            </div>
            <div class="form-group">
                <input type="checkbox" name="isActive" id="isActive" data-bind="checked:isActive">
                <label for="isActive">是否启用</label>
            </div>

            <div class="form-group">
                <label for="remark">角色</label>
                <span data-bind="foreach:$root.allRoles">
                    <input type="checkbox" data-bind="value:roleId, checked:checked, attr: { id: 'role-' + code}">
                    <label data-bind="text:name, attr: { for: 'role-' + code}"></label>
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

    </form>
</div>
<script>
    function SysUserCreateModel() {
        var self = this;

        self.allRoles = ko.observableArray(${allRoles});

        self.model = {};
        self.model.username = ko.observable();
        self.model.password = ko.observable();
        self.model.branchId = ko.observable();
        self.model.branchId=ko.observable();
        self.model.fullName = ko.observable();
        self.model.employeeNumber = ko.observable();
        self.model.email = ko.observable();
        self.model.siteId = ko.observable();
        self.model.isAllowLogin = ko.observable(true);
        self.model.isActive = ko.observable(true);
        self.model.remark = ko.observable();
        var orgId = "00000000-0000-0000-0000-000000000000"||$("#branchId").val();
        self.sites = kendo.utils.createListDataSource("organizationId", "/sysOrganization/getDataSource",{field: "superiorOrganizationId",operator: "eq",value: orgId});
        self.branchChange =function(){
            var branchId = $("#branchId").val();
            self.sites.filter({
                logic: "and",
                filters: [{field: "superiorOrganizationId",operator: "eq",value: branchId}]
            });
        }
        self.model.sysRoles = ko.observableArray([]);
        var organizations = kendo.utils.createHierarchicalDataSource("organizationId", "sysOrganization/getChildrenData");
        self.treeView = {
            dataSource: organizations,
            dataTextField: "name"
        }
        for (var i in self.allRoles()) {
            self.allRoles()[i].checked = ko.computed({
                read: function (data) {
                    for (var j in self.model.sysRoles()) {
                        if (self.model.sysRoles()[j].roleId == this.roleId) return true;
                    }
                    return false;
                }, write: function (value) {
                    if (value == true) {
                        self.model.sysRoles.push(this)
                    } else {
                        for (var j in self.model.sysRoles()) {
                            if (self.model.sysRoles()[j].roleId == this.roleId) self.model.sysRoles.remove(self.model.sysRoles()[j]);
                        }
                    }
                },
                owner: self.allRoles()[i]
            });
        }


        self.save = function () {
            // 检查表单是否通过验证
            self.model.branchId($("#branchId").val());
            if (!$("#user-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/sysUser/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加用户成功", "success");
                    router.navigate("/sysUser");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        $("#user-form").validate();
        var viewModel = new SysUserCreateModel();
        ko.applyBindings(viewModel, document.getElementById("user-form"));
    });


</script>