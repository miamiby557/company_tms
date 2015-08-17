<#import "/utils/form.ftl" as form >
<div>
    <form id="crmClientSender" method="post" data-bind="submit:save" class="form-horizontal">
        <div class="col-12" data-bind="with:model">
            <@form.koText field="clientSenderId" label="客户送货地址ID" required="required"/>
            <@form.koText field="clientId" label="客户ID" required="required"/>
            <@form.koText field="addressId" label="地址ID" required="required"/>
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary "><i class="fa fa-save"></i> 保存</button>
            <button type="button" data-action="closeModal" class="k-button k-button-icontext"><i class="fa fa-close"></i> 取消
            </button>
        </div>
    </form>
</div>
<script>
    function CreateModel() {
        var self = this;
        self.model = {};
        self.model = ko.mapping.fromJS(${crmClientSenderJson});
        self.save = function(){
            // 检查表单是否通过验证
            if (!$("#crmClientSender").validate().valid()) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/crmClientSender/myCreate",
                data: ko.toJSON(self.model)
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
        $("#crmClientSender").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("crmClientSender"));
    });
</script>