
<form id="sign-form" method="post" data-bind="submit:save" class="form-horizontal">
    <div data-bind="with:model">
        <div class="form-group">
            <label for="signDate">签收日期</label>
            <input type="date" name="signDate" id="signDate" data-bind="kendoDateTimePicker:signDate" required="required">
        </div>
        <div class="form-group">
            <label for="signMan">签收人</label>
            <input type="text" class="k-input k-textbox" name="signMan" id="signMan" data-bind="value:signMan"
                   maxlength="50">
        </div>
        <div class="form-group">
            <label for="signManCard">签收人身份证号</label>
            <input type="text" class="k-input k-textbox" name="signManCard" id="signManCard"
                   data-bind="value:signManCard" maxlength="18" pattern="(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)">
        </div>
        <div class="form-group">
            <label for="agentSignMan">代理签收人</label>
            <input type="text" class="k-input k-textbox" name="agentSignMan" id="agentSignMan"
                   data-bind="value:agentSignMan" maxlength="50">
        </div>
        <div class="form-group">
            <label for="agentSignManCard">代理签收人身份证号</label>
            <input type="text" class="k-input k-textbox" name="agentSignManCard" id="agentSignManCard"
                   data-bind="value:agentSignManCard" maxlength="18" pattern="(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)">
        </div>
        <div class="form-group">
            <input type="checkbox" name="isAbnormal" id="isAbnormal" data-bind="checked:isAbnormal">
            <label>是否异常</label>
        </div>
        <div class="form-group">
            <label for="remark">备注</label>
            <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark"
                      maxlength="250"></textarea>
        </div>

        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary " >
                <i class="fa fa-save"></i> 保存
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
        self.model = {};
        self.model.transportOrderIds = ko.observableArray(${transportOrderIds});
        self.model.signMan = ko.observable();
        self.model.signDate = ko.observable(new Date());
        self.model.signManCard = ko.observable();
        self.model.agentSignMan = ko.observable();
        self.model.agentSignManCard = ko.observable();
        self.model.isAbnormal= ko.observable();
        self.model.remark = ko.observable();
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#sign-form").validate().valid()) return;
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdOrderSign/batchSign",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("签收成功！", "success");
                    $(".k-window-content.k-content").data("kendoWindow").close();
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
    }
    $(function () {
        $("#sign-form").validate();
        var signViewModel = new signCreateModel();
        ko.applyBindings(signViewModel, document.getElementById("sign-form"));
    });
</script>