<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdPickupOrder"><i class="fa fa-users"></i> 提货订单</a></li>
    <li><a href="#/otdPickupOrder/modify/${otdPickupOrder.pickupOrderId}"><i class="fa fa-edit"></i> 修改提货订单</a></li>
</ol>

<div>
    <form id="modify-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="pickupOrderNumber">提货单号</label>
                <input type="text" class="k-input k-textbox" name="pickupOrderNumber" id="pickupOrderNumber" readonly = "true" data-bind="value:pickupOrderNumber" required="required" maxlength="50">
            </div>
            <div class="form-group">
                <label for="clientId">客户名称</label>
            <@utils.koComboBox data="$root.clientIds" value="clientId" textField="name" valueField="clientId"  required=true/>
            </div>
            <div class="form-group">
                <label for="reservationTime" >预约时间</label>
                <input type="date"  id="reservationTime" data-bind="kendoDateTimePicker:reservationTime" name="reservationTime" required="required">
            </div>
            <div class="form-group">
                <label for="cityId">提货城市</label>
                <input id="cityId" required="required" data-bind="kendoDropDownTreeView:{valueField: 'regionId',textField: 'name',treeView:$root.treeView,value:cityId,loadUrl:'/baseRegion/' }" />
            <#--<@utils.koComboBox data="$root.citys" value="cityId" textField="name" valueField="regionId" required=true/>-->
            </div>
            <div class="form-group">
                <label for="address">提货地址</label>
                <textarea type="text" class="k-textbox" name="address" id="address" data-bind="value:address" required="required" maxlength="25"/>
            </div>
            <div class="form-group">
                <label for="totalVolume">总体积(立方)</label>
                <input type="number" class="k-input k-textbox" name="totalVolume" id="totalVolume" data-bind="value:totalVolume" required="required" >
            </div>
            <div class="form-group">
                <label for="totalWeight">总重量(千克)</label>
                <input type="number" class="k-input k-textbox" name="totalWeight" id="totalWeight" data-bind="value:totalWeight">
            </div>
            <div class="form-group">
                <label for="totalItemQty">总数量</label>
                <input type="number" class="k-input k-textbox" name="totalItemQty" id="totalItemQty" data-bind="value:totalItemQty" required="required"pattern="^[1-9]\d*$">
            </div>
            <div class="form-group">
                <label for="totalPackageQty">总件数</label>
                <input type="number" class="k-input k-textbox" name="totalPackageQty" id="totalPackageQty" data-bind="value:totalPackageQty" required="required"pattern="^[1-9]\d*$">
            </div>

            <div class="form-buttons">
                <button type="submit" class="k-button k-button-icontext k-primary ">
                    <i class="fa fa-save"></i> 保存
                </button>

                <button type="button" data-action="goBack" class="k-button k-button-icontext">
                    <i class="fa fa-close"></i> 取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    function ModifyModel() {
        var self = this;
        self.model = {};
        self.model = ko.mapping.fromJS(${otdPickupOrderJson});
        var businessRegions = kendo.utils.createHierarchicalDataSource("regionId","baseRegion/getChildrenData");
        self.treeView = {
            dataSource: businessRegions,
            dataTextField:"name"
        }
        self.citys = kendo.utils.createListDataSource("regionId","/baseRegion/getCityList");
        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            self.model.cityId($("#cityId").val());
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdPickupOrder/update",
                data: kendo.stringify(ko.toJS(self.model))
            }).done(function (result) {
                if (result.success === true) {
                    notify("修改成功", "success");
                    router.navigate("/otdPickupOrder");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    $(function () {
        $("#modify-form").validate();
        var viewModel = new ModifyModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });


</script>