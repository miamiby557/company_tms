<#import "/utils/form.ftl" as form >
<div>
    <form id="address-update-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div class="col-12" data-bind="with:model">
        <@form.koDropDown field="addressType" data="$root.addressType" label="地址类型" required="required"/>
        <@form.koTextarea field="contactMan" label="联系人" />
        <@form.koTextarea field="contactPhone" label="联系电话" />
        <@form.kendoDropDownTreeView valueField='regionId' textField="name" field="region" treeView="$root.treeViewRegion"
        label="地区" required="required" loadUrl='/baseRegion/' />
        <@form.koTextarea field="address" label="地址"  maxlength="200"/>
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary "> <i class="fa fa-save"></i> 保存
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
        self.model = ko.mapping.fromJS(${baseAddressJson});
        self.addressType=commonData.baseAddressType;
        var Regions = kendo.utils.createHierarchicalDataSource("regionId","baseRegion/getChildrenData");
        self.treeViewRegion = {
            dataSource: Regions,
            dataTextField:"name"
        }
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#address-update-form").validate().valid()) return;
            self.model.region($("#region").val());
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/baseAddress/update",
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
        $("#address-update-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("address-update-form"));
    });
</script>