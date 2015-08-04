<#import "/utils/readOnlyDropdown.ftl" as utils >
<div>
    <form id="scmCarrierLine-modify-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="startCity">始发城市</label>
                <input type="text" name="startCity" id="startCity" data-bind="value:startCity" class="k-input k-textbox" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="destCity">目的城市</label>
                <input type="text" name="destCity" id="destCity" data-bind="value:destCity" class="k-input k-textbox" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="transportType">运输类型</label>
            <@utils.koDropDown data="$root.transportType" value="transportType"/>
            </div>
            <div class="form-group">
                <label for="timeline" >时效</label>
                <input type="number" name="timeline" data-bind="value:timeline"  id="timeline" class="k-input k-textbox" min="0"required="required">
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
    function scmCarrierLineModel() {
        var self = this;
        self.model = ko.mapping.fromJS(${UUIDJson});
        self.transportType = commonData.transportType;
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#scmCarrierLine-modify-form").validate().valid()) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/scmCarrierLine/update",
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
        $("#scmCarrierLine-modify-form").validate();
        var viewModel = new scmCarrierLineModel();
        ko.applyBindings(viewModel, document.getElementById("scmCarrierLine-modify-form"));
    });
</script>