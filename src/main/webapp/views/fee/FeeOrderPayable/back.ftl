    <form id="back-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="remark">原因</label>
                <textarea id="remark" name="remark" class="k-textbox" rows="5"data-bind="value:remark"maxlength="250"></textarea>
            </div>
            <div class="form-buttons">
                <button type="submit" class="k-button k-button-icontext k-primary ">
                    <i class="fa fa-save"></i> 确定
                </button>

                <button type="button" data-action='closeModal' class="k-button k-button-icontext ">
                    <i class="fa fa-close"></i> 取消
                </button>
            </div>
        </div>
    </form>

<script>
    function signCreateModel() {
        var self = this;
        self.model={};
        self.model.orderPayableId = ko.observable("${orderPayableId}");
        self.model.remark = ko.observable();
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#back-form").validate().valid()) return;
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderPayableLog/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("操作成功！", "success");
                    $(".k-window-content.k-content").data("kendoWindow").close();
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
    }
    $(function () {
        $("#back-form").validate();
        var backModel = new signCreateModel();
        ko.applyBindings(backModel, document.getElementById("back-form"));
    });
</script>