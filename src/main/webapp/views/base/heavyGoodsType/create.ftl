<#import "/utils/form.ftl" as form >
<div>
    <form id="heavyGoodsType-create-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div class="col-12" data-bind="with:model">
            <@form.koText field="name" label="名称" required="required" maxlength="50"/>
            <@form.koNumberBox field="ratioMin" label="重量体积比最小值" required="required" min="0"/>
            <@form.koNumberBox field="ratioMax" label="重量体积比最大值"/>
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
        <h4 style="text-align: right">*重量体积比:1吨货物对应的体积方数</h4>
    </form>

</div>
<script>
    function CreateModel() {
        var self = this;
        self.model = {};
        self.model.name= ko.observable();
        self.model.ratioMin= ko.observable();
        self.model.ratioMax= ko.observable();
        self.model.remark= ko.observable();
        self.model.clientId=ko.observable("${clientId}");
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#heavyGoodsType-create-form").validate().valid()) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/baseHeavygoodsType/create",
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
        $("#heavyGoodsType-create-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("heavyGoodsType-create-form"));
    });
</script>