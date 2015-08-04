<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/baseExacct"><i class="fa fa-user"></i> 业务费用科目</a></li>
    <li><a href="#/baseExacct/create"><i class="fa fa-plus"></i> 增加业务费用科目</a></li>
</ol>

<div>
    <form id="baseExacct-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="code">代码</label>
                <input type="text" class="k-input k-textbox" name="code" id="code" data-bind="value:code" required="required" minlength="3" maxlength="50">
            </div>
            <div class="form-group">
                <label for="name">名称</label>
                <input type="text" class="k-input k-textbox" name="name" id="name" data-bind="value:name" required="required"  maxlength="25">
            </div>
            <div class="form-group">
                <label for="superiorId">上级科目</label>
                <input id="superiorId" data-bind="kendoDropDownTreeView:{valueField: 'exacctId',textField: 'name',treeView:$root.treeView,
                value:superiorId,loadUrl:'/baseExacct/'}"  />
            </div>
            <div class="form-group">
                <label for="indexNumber">序号</label>
                <input type="number" class="k-input k-textbox" name="indexNumber" id="indexNumber" data-bind="value:indexNumber" pattern="\d+">
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
    function baseExacctCreateModel() {
        var self = this;

        self.model = {};
        self.model.name = ko.observable();
        self.model.code = ko.observable();
        self.model.superiorId = ko.observable();
        self.model.remark = ko.observable();
        self.model.indexNumber=ko.observable();
        var businessExaccts = kendo.utils.createHierarchicalDataSource("exacctId","baseExacct/getChildrenData");
        self.treeView = {
            dataSource: businessExaccts,
            dataTextField:"name"
        }
        self.save = function () {
            // 检查表单是否通过验证
            self.model.superiorId($("#superiorId").val());
            if (!$("#baseExacct-form").validate().valid()) return;
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/baseExacct/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加成功", "success");
                    router.navigate("/baseExacct");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        $("#baseExacct-form").validate();
        var viewModel = new baseExacctCreateModel();
        ko.applyBindings(viewModel, document.getElementById("baseExacct-form"));
    });
</script>