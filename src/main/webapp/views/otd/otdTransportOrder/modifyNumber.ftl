
<div>
    <form id="modify-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <input id="clientId" type="hidden" name="clientId" data-bind="value:clientId">
                <label for="clientOrderNumber">客户单号</label>
                <input type="text" name="clientOrderNumber" data-bind="value:clientOrderNumber" id="clientOrderNumber" class="k-input k-textbox" maxlength="50" readonly="readonly" required="required">
            </div>
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
        self.model = ko.mapping.fromJS(${otdTransportOrderJson});
        self.detailsDataSource = new kendo.data.DataSource({data:${otdTransportOrderDetailsJson}});
        var clientOrderNumber =self.model.clientOrderNumber();
        if(self.model.isTemp()){
            $("#clientOrderNumber").removeAttr("readonly");
        }

        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            self.model.details = self.detailsDataSource.data();
            if(clientOrderNumber!=self.model.clientOrderNumber()){
                self.model.isTemp(false);
            }
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/update",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("修改成功！", "success");
                    $(".k-window-content.k-content").data("kendoWindow").close();
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
    }
    $(function () {
        jQuery.validator.methods.numberCheck =function(value,element,params){
            var clientId = $(params).val();
            var transportOrderId="${otdTransportOrder.transportOrderId}";
            var result = true;
            var url =  "/otdTransportOrder/clientOrderNumberModifyIsExist?clientId=" + clientId + "&clientOrderNumber=" + value+ "&transportOrderId=" + transportOrderId;
            $.ajax({
                type:"GET",
                contentType: "application/json",
                async:false,
                url:url
            }).done(function(response){
                if(response){
                    result = response.success;
                }
            });
            return result;
        };
        $("#modify-form").validate({
            rules:{
                "clientOrderNumber":{
                    numberCheck:"#modify-form #clientId"
                }
            },messages:{
                "clientOrderNumber":{
                    numberCheck: "客户单号已存在"
                }
            }
        });
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });

</script>