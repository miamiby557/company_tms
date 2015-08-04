<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdTransportOrderView"><i class="fa fa-users"></i> 运输订单</a></li>
    <li><a href="#/otdTransportOrder/confirm/${otdTransportOrder.transportOrderId}"><i class="fa fa-plus"></i> 运输订单确认</a></li>
</ol>

<div>
    <form id="confirm-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="totalItemQuantity">总数量</label>
                <input type="number" name="totalItemQuantity" data-bind="value:totalItemQuantity" id="totalItemQuantity" class="k-input k-textbox" min="1" disabled="disabled">
            </div>
            <div class="form-group">
                <label for="totalPackageQuantity">总箱数</label>
                <input type="number" name="totalPackageQuantity" data-bind="value:totalPackageQuantity" id="totalPackageQuantity" class="k-input k-textbox" min="1" disabled="disabled">
            </div>
            <div class="form-group">
                <label for="totalVolume">理论体积（立方）</label>
                <input type="text" name="totalVolume" id="totalVolume" data-bind="value:totalVolume" class="k-input k-textbox" min="0" disabled="disabled">
            </div>
            <div class="form-group">
                <label for="totalWeight">理论重量（公斤）</label>
                <input type="text" name="totalWeight" id="totalWeight" data-bind="value:totalWeight" class="k-input k-textbox" min="0" disabled="disabled">
            </div>
            <div class="form-group">
                <label for="confirmedItemQuantity" >实际数量</label>
                <input type="number" name="confirmedItemQuantity"data-bind="value:confirmedItemQuantity"  id="confirmedItemQuantity" class="k-input k-textbox" min="1"required="required"pattern="^[1-9]\d*$">
            </div>
            <div class="form-group">
                <label for="confirmedPackageQuantity" >应收总件数</label>
                <input type="number" name="confirmedPackageQuantity" data-bind="value:confirmedPackageQuantity"  id="confirmedPackageQuantity" class="k-input k-textbox" min="1"required="required"pattern="^[1-9]\d*$">
            </div>
            <div class="form-group">
                <label for="confirmedVolume" >应收体积（立方）</label>
                <input type="text" name="confirmedVolume" id="confirmedVolume" data-bind="value:confirmedVolume"  pattern="\d+(\.\d{0,6})?"  class="k-input k-textbox" min="0.01" required="required">
            </div>
            <div class="form-group">
                <label for="confirmedWeight" >应收重量（公斤）</label>
                <input type="text" name="confirmedWeight" id="confirmedWeight" data-bind="value:confirmedWeight"  class="k-input k-textbox"  min="0" >
            </div>
            <#--<div class="form-group">
                <label for="receiptPageNumber" >回单页数</label>
                <input type="number" name="receiptPageNumber" data-bind="value:receiptPageNumber"  id="receiptPageNumber" class="k-input k-textbox" min="0"required="required"pattern="^[1-9]\d*$">
            </div>
            <div class="form-group">
                <label for="wrapType">包装类型</label>
            <@utils.koDropDown data="$root.wrapTypeList" value="wrapType" required=true/>
            </div>-->
            <div class="form-buttons">
                <button type="button" data-bind="kendoButton:$root.save1,enable:$root.canSave1">
                    <i class="fa fa-edit"></i>确认至应收
                </button>
                <button type="submit" class="k-button k-button-icontext k-primary ">
                    <i class="fa fa-save"></i> 确认至订单
                </button>
                <button type="button" data-action="goBack" class="k-button k-button-icontext">
                    <i class="fa fa-close"></i> 取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    function CreateModel() {
        var self = this;
        self.model=ko.mapping.fromJS(${otdTransportOrderJson});
      /*  self.model.receiptPageNumber=ko.observable(1);
        self.model.calculateType=ko.observable(2);
        self.wrapTypeList=commonData.wrapType;*/
        self.canSave1 = ko.observable("${otdTransportOrder.mergeStatus}" == 1);
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#confirm-form").validate().valid()) return;

            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!self.model.confirmedWeight()||self.model.confirmedWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==2) {
                if (!self.model.confirmedPackageQuantity()||self.model.confirmedPackageQuantity()<=0) {
                    notify("按件数计费，件数须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==3) {//体积
                if (!self.model.confirmedVolume()||self.model.confirmedVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return;
                }
            }

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/confirm",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("确认成功！", "success");
                    router.navigate("/otdTransportOrderView");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }

        self.save1 = function () {
            // 检查表单是否通过验证
            if (!$("#confirm-form").valid()) return;

            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!self.model.confirmedWeight()||self.model.confirmedWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==2) {
                if (!self.model.confirmedPackageQuantity()||self.model.confirmedPackageQuantity()<=0) {
                    notify("按件数计费，件数须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==3) {//体积
                if (!self.model.confirmedVolume()||self.model.confirmedVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return;
                }
            }

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/confirm",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("确认成功！", "success");
                    var orderReceivableId=result.content;
                    var url="#/feeOrderReceivable/modify/"+orderReceivableId;
                    router.navigate(url);
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
    }
    $(function () {
        $("#confirm-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("confirm-form"));
    });
</script>