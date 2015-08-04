<#import "/utils/dropdown.ftl" as utils >

<div>
    <form id="create-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="marketClientNumber" >商超单号</label>
                <input type="text" name="marketClientNumber" id="marketClientNumber"data-bind="value:marketClientNumber"  class="k-input k-textbox" maxlength="50">
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
        self.model.transportOrderIds = ko.observableArray(${transportOrderIds});
        self.model.marketClientNumber = ko.observable();
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#create-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/addMarketNumber",
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