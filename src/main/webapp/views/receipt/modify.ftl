<#import "/utils/dropdown.ftl" as utils >

<div>
    <form id="modify-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="receiptDate" >回单日期</label>
                <input type="date"  id="receiptDate" data-bind="kendoDatePicker:receiptDate" name="receiptDate" required="required">
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark"maxlength="250"></textarea>
            </div>
        </div>
        <div style="margin-top: 20px">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 保存
            </button>
            <button type="button" data-bind="kendoButton:cancel">
                <i class="fa fa-close"></i> 取消
            </button>
        </div>
    </form>
</div>
<script>
    function CreateModel() {
        var self = this;
        self.model = ko.mapping.fromJS(${receiptJson});
        self.model.receiptDate=ko.observable(new Date);
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrderReceipt/update",
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
        self.cancel=function(){
            $(".k-window-content.k-content").data("kendoWindow").close();
        }
    }
    $(function () {
        $("#modify-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });
</script>