<#import "/utils/dropdown.ftl" as utils >

<div>
    <form id="create-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model1">
            <div class="form-group">
                <label for="operationDate">最近操作时间</label>
                <input type="date" name="operationDate" id="operationDate" data-bind="kendoDateTimePicker:operationDate" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="operationContent">最近操作内容</label>
                <textarea id="operationContent" name="operationContent"  data-bind="value:operationContent"  class="k-textbox" rows="5" maxlength="250" readonly="readonly"></textarea>
            </div>
        </div>
        <div data-bind="with:model">
            <div class="form-group">
                <label for="operationDate">操作时间</label>
                <input type="date" name="operationDate" id="operationDate" required="required" data-bind="kendoDateTimePicker:operationDate" >
            </div>
            <div class="form-group">
                <label for="operationContent">操作内容</label>
                <textarea id="operationContent" name="operationContent" required="required"  data-bind="value:operationContent"  class="k-textbox" rows="5" maxlength="250"></textarea>
            </div>
            <div class="form-group">
                <input type="checkbox" name="isAbnormal" id="isAbnormal" data-bind="checked:isAbnormal">
                <label>是否异常</label>
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark"  data-bind="value:remark"  class="k-textbox" rows="5" maxlength="250"></textarea>
            </div>
            <div class="form-buttons">
                <button type="submit" class="k-button k-button-icontext k-primary ">
                    <i class="fa fa-save"></i> 保存
                </button>
                <button type="button" data-action="closeModal" class="k-button k-button-icontext">
                    <i class="fa fa-close"></i> 取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    function CreateModel() {
        var self = this;
        self.model={};
        self.model1=ko.mapping.fromJS(${otdOrderLogListView});
        self.model.transportOrderId = ko.observable("${transportOrderId}");
        self.model.operationContent = ko.observable();
        self.model.operationDate = ko.observable(new Date());
        self.model.currentStatus = ko.observable();
        self.model.isPublic = ko.observable(true);
        self.model.remark = ko.observable();
        self.model.isAbnormal= ko.observable();
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#create-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrderLog/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加成功", "success");
                    $(".k-window-content.k-content").data("kendoWindow").close();
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