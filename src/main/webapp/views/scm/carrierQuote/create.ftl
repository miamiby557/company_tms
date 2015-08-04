<#import "/utils/dropdown.ftl" as utils >
<div>
    <form id="create-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="startCityId">始发城市</label>
                <input id="startCityId" name="startCityId"  data-bind="kendoComboBox: { data
                : $root.citys, value: scmCarrierLine.startCityId, dataTextField: 'name',filter: 'contains', dataValueField
                : 'regionId',change:$root.startChange }"required="required" />
            </div>
            <div class="form-group">
                <label for="destCityId">目的城市</label>
                <input id="destCityId" name="destCityId"  data-bind="kendoComboBox: { data
                : $root.citys, value: scmCarrierLine.destCityId, dataTextField: 'name',filter: 'contains', dataValueField
                : 'regionId',change:$root.destChange }" required="required" />
            </div>
            <div class="form-group">
                <label for="transportType">运输方式</label>
                <input id="transportType" name="transportType" data-bind="kendoDropDownList
                : { data: $root.transportTypes, value: scmCarrierLine.transportType, dataTextField: 'text', dataValueField
                : 'value', optionLabel:'请选择...', change:$root.transportChange }" required="required"  />
            </div>
            <div class="form-group">
                <label for="calculateType">计费方式</label>
                <@utils.koDropDown data="$root.calculateTypes" value="calculateType"  required=true/>
            </div>
            <div class="form-group">
                <label for="exacctId">费用科目</label>
                <@utils.koComboBox data="$root.exacctIds" value="exacctId" textField="name" valueField="exacctId"  required=true/>
            </div>
            <div class="form-group">
                <label for="minimumFee">最低收费</label>
                <input type="number" class="k-input k-textbox"  name="minimumFee" id="minimumFee" data-bind="value:minimumFee" required="required" >
            </div>
            <div class="form-group">
                <label for="minimumCondiction">最小区间</label>
                <input type="number" class="k-input k-textbox"  name="minimumCondiction" id="minimumCondiction" data-bind="value:minimumCondiction" min="0"required="required">
            </div>
            <div class="form-group">
                <label for="maxmumCondiction">最大区间</label>
                <input type="number" class="k-input k-textbox"  name="maxmumCondiction" id="maxmumCondiction" data-bind="value:maxmumCondiction"  compareTo="#minimumCondiction" >
            </div>
            <div class="form-group">
                <label for="unitPrice">单价</label>
                <input type="number" class="k-input k-textbox"  name="unitPrice" id="unitPrice" data-bind="value:unitPrice" >
            </div>
            <div class="form-group">
                <label for="timeline">时效</label>
                <input type="number" class="k-input k-textbox"  name="timeline" id="timeline" data-bind="value:scmCarrierLine.timeline" required="required" >
            </div>
            <div class="form-group">
                <input type="checkbox" name="isActive" id="isActive" data-bind="checked:isActive">
                <label for="isActive">是否启用</label>
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark"  data-bind="value:remark"  class="k-textbox" rows="5"maxlength="250"></textarea>
            </div>

            <div class="form-buttons">
                <button type="submit" class="k-button k-button-icontext k-primary ">
                    <i class="fa fa-save"></i> 保存
                </button>

                <button type="button" data-action='closeModal' class="k-button k-button-icontext">
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
        self.model.scmCarrierLine={};
        self.model.scmCarrierLine.carrierId = ko.observable("${carrierId}");
        self.model.carrierLineId = ko.observable();
        self.model.scmCarrierLine.startCityId = ko.observable();
        self.model.scmCarrierLine.destCityId = ko.observable();
        self.model.scmCarrierLine.transportType = ko.observable();
        self.model.calculateType = ko.observable();
        self.model.exacctId = ko.observable();
        self.model.minimumFee = ko.observable();
        self.model.minimumCondiction = ko.observable();
        self.model.maxmumCondiction = ko.observable();
        self.model.unitPrice = ko.observable();
        self.model.scmCarrierLine.timeline = ko.observable();
        self.model.isActive = ko.observable(true);
        self.model.remark = ko.observable();
        self.transportTypes =commonData.transportType;
        self.calculateTypes = commonData.calculateType;
        self.exacctIds = kendo.utils.createListDataSource("exacctId","/baseExacct/getDataSourceByType?type=payable");
        self.citys = kendo.utils.createListDataSource("regionId","/baseRegion/getCityList");
        self.startChange =function(e){
            $("#timeline").attr("readonly",false);
            self.model.scmCarrierLine.timeline("");
            self.model.scmCarrierLine.startCityId(this.value());
            var startCity =self.model.scmCarrierLine.startCityId();
            var destCity = self.model.scmCarrierLine.destCityId();
            var transportType = self.model.scmCarrierLine.transportType();
            if(startCity&&destCity&&transportType){
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/scmCarrierLine/getLineByCity",
                    data: ko.toJSON(self.model.scmCarrierLine)
                }).done(function (result) {
                    if (result) {
                        $("#timeline").attr("readonly",true);
                        self.model.scmCarrierLine.timeline(result.timeline);
                    }
                });
            }
        }
        self.destChange =function(e){
            $("#timeline").attr("readonly",false);
            self.model.scmCarrierLine.timeline(null);
            self.model.scmCarrierLine.destCityId(this.value());
            var startCity =self.model.scmCarrierLine.startCityId();
            var destCity = self.model.scmCarrierLine.destCityId();
            var transportType = self.model.scmCarrierLine.transportType();
            if(startCity&&destCity&&transportType){
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/scmCarrierLine/getLineByCity",
                    data: ko.toJSON(self.model.scmCarrierLine)
                }).done(function (result) {
                    if (result) {
                        $("#timeline").attr("readonly",true);
                        self.model.scmCarrierLine.timeline(result.timeline);
                    }
                });
            }
        }
        self.transportChange =function(e){
            $("#timeline").attr("readonly",false);
            self.model.scmCarrierLine.timeline("");
            var startCity = $("#startCityId").val();
            var destCity = $("#destCityId").val();
            var transportType = this.value();
            self.model.scmCarrierLine.transportType(transportType);
            if(startCity&&destCity&&transportType){
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/scmCarrierLine/getLineByCity",
                    data: ko.toJSON(self.model.scmCarrierLine)
                }).done(function (result) {
                    if (result) {
                        $("#timeline").attr("readonly",true);
                        self.model.scmCarrierLine.timeline(result.timeline);
                    }
                });
            }
        }
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#create-form").validate().valid()) return;

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/scmCarrierQuote/createScmCarrierQuote",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加承运商报价成功！", "success");
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
        var createModel = new CreateModel();
        ko.applyBindings(createModel, document.getElementById("create-form"));
    });



</script>