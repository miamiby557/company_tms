<#import "/utils/form.ftl" as form >
<div>
    <form id="product-create-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div class="col-12" data-bind="with:model">
            <@form.koIntegerBox field="value" label="值" required="required"/>
            <@form.koText field="name" label="名称" required="required" maxlength="50"/>
            <@form.koTextarea field="remark" label="备注"  maxlength="500"/>
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 保存
            </button>
            <button type="button" data-action="closeModal" class="k-button k-button-icontext">
                <i class="fa fa-close"></i> 取消
            </button>
        </div>
    </form>

</div>
<script>
    function CreateModel() {
        var self = this;
        self.model = {};
        self.model.clientId=ko.observable("${clientId}");
        self.model.value= ko.observable();
        self.model.name= ko.observable();
        self.model.remark= ko.observable();
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#product-create-form").validate().valid()) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/crmClientProductType/createProductType",
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
        };
    }
    $(function () {
        $("#product-create-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("product-create-form"));
    });
</script>