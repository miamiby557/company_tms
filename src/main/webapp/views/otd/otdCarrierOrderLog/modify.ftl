<#import "/utils/dropdown.ftl" as utils >
<div>
    <form id="modify-log-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="operationDate">操作时间</label>
                <input type="date" name="operationDate" id="operationDate" required="required" data-bind="kendoDateTimePicker:operationDate" >
            </div>
            <div class="form-group">
                <label for="operationContent">操作内容</label>
                <textarea id="operationContent" name="operationContent" required="required"  data-bind="value:operationContent"  class="k-textbox" rows="5"></textarea>
            </div>
            <div class="form-group">
                <input type="checkbox" name="isPublic" id="isPublic" data-bind="checked:isPublic">
                <label for="isPublic">是否公开</label>
            </div>
            <div class="form-group">
                <input type="checkbox" name="isAbnormal" id="isAbnormal" data-bind="checked:isAbnormal">
                <label for="isAbnormal">是否异常</label>
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark"  data-bind="value:remark"  class="k-textbox" rows="5"></textarea>
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
    function ModifyLogModel() {
        var self = this;
        self.model = {};
        self.model = ko.mapping.fromJS(${carrierOrderLogJson});
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-log-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdCarrierOrderLog/update",
                data: kendo.stringify(ko.toJS(self.model))
            }).done(function (result) {
                if (result.success === true) {
                    notify("修改成功", "success");
                    $(".k-window-content.k-content").data("kendoWindow").close();
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        $("#modify-log-form").validate();
        var modifyModel = new ModifyLogModel();
        ko.applyBindings(modifyModel, document.getElementById("modify-log-form"));
    });


</script>