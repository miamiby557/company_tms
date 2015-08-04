<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysOrganization"><i class="fa fa-sitemap"></i> 组织架构</a></li>
    <li><a href="#/sysOrganization/create"><i class="fa fa-plus"></i> 增加</a></li>
</ol>

<div>
    <form id="organization-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">

            <div class="form-group">
                <label for="superiorOrganizationId">上级组织</label>
                <input id="superiorOrganizationId" data-bind="kendoDropDownTreeView:{valueField: 'organizationId',textField: 'name',treeView:$root.treeViewOrganization,
                value:superiorOrganizationId,loadUrl:'/sysOrganization/',change:$root.superChange}"   />
            </div>
            <div class="form-group">
                <label for="type">组织类型</label>
                <input id="type" readonly="readonly" data-bind="kendoDropDownList: { data:$root.organizationTypes , value: type, dataTextField:'text', dataValueField:'value', optionLabel:'请选择...'}" />
            </div>
            <div class="form-group">
                <label for="code">编码</label>
                <input type="text" class="k-input k-textbox" name="code" id="code" data-bind="value:code" required="required" minlength="2" maxlength="50">
            </div>
            <div class="form-group">
                <label for="name">名称</label>
                <input type="text" class="k-input k-textbox" name="name" id="name" data-bind="value:name" required="required" minlength="2" maxlength="50">
            </div>
            <div class="form-group">
                <label for="cityId">所在地</label>
                <input id="cityId" required="required" data-bind="kendoDropDownTreeView:{valueField: 'regionId',textField: 'name',treeView:$root.treeViewRegion,value:cityId,loadUrl:'/baseRegion/' }" />
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
    function SysOrganizationCreateModel() {
        var self = this;

        self.model = {};
        self.model.organizationId = ko.observable();
        self.model.name = ko.observable();
        self.model.type = ko.observable();
        self.model.code = ko.observable();
        self.model.isActive = ko.observable(true);
        self.model.remark = ko.observable();
        self.model.superiorOrganizationId = ko.observable();
        var organizations = kendo.utils.createHierarchicalDataSource("organizationId","sysOrganization/getChildrenData");
        self.treeViewOrganization = {
            dataSource: organizations,
            dataTextField:"name"
        }
        self.organizationTypes = commonData.organizationType;
        self.model.cityId = ko.observable();
        var businessRegions = kendo.utils.createHierarchicalDataSource("regionId","baseRegion/getChildrenData");
        self.treeViewRegion = {
            dataSource: businessRegions,
            dataTextField:"name"
        }
        self.superChange = function(){
            var superiorOrganizationId = $("#superiorOrganizationId").val();
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/sysOrganization/"+superiorOrganizationId
            }).done(function (result) {
                if (result) {
                    self.model.type(result.type+1);
                }
            });
        }
        self.save = function () {
            // 检查表单是否通过验证
            self.model.superiorOrganizationId($("#superiorOrganizationId").val())
            self.model.cityId($("#cityId").val());
            if (!$("#organization-form").validate().valid()) return;
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/sysOrganization/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加成功", "success");
                    router.navigate("/sysOrganization");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        var viewModel = new SysOrganizationCreateModel();
        ko.applyBindings(viewModel, document.getElementById("organization-form"));
    });


</script>