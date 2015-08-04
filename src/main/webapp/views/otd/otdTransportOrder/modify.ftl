<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdTransportOrderView"><i class="fa fa-users"></i> 运输订单</a></li>
    <li><a href="#/otdTransportOrder/modify/${otdTransportOrder.transportOrderId}"><i class="fa fa-plus"></i> 修改运输订单</a>
    </li>
</ol>

<div>
    <form id="modify-form" method="post" data-bind="submit:save" class="form-horizontal">
            <div data-bind="with:model" class="col-6">
                <div class="form-group">
                    <label for="clientId">客户</label>
                    <input id="clientId" name="clientId" data-bind="kendoComboBox: { data: $root.clientIds, value: clientId
        , dataTextField: 'name',filter: 'contains',dataValueField:'clientId', change:$root.clientChange }" required="required"/>
                </div>
            <div class="form-group">
                <label for="pickupOrderId">提货单号</label>
            <@utils.koComboBox data="$root.pickupOrders" value="pickupOrderId" textField="pickupOrderNumber" valueField="pickupOrderId"/>
            </div>
            <div class="form-group">
                <label for="clientOrderNumber">客户单号</label>
                <input type="text" name="clientOrderNumber" data-bind="value:clientOrderNumber" id="clientOrderNumber" class="k-input k-textbox" maxlength="50" readonly="readonly" required="required">
            </div>
                <div class="form-group">
                    <label for="viceClientOrderNumber" >副单号</label>
                    <input type="text" name="viceClientOrderNumber" id="viceClientOrderNumber"data-bind="value:viceClientOrderNumber"  class="k-input k-textbox" maxlength="50">
                </div>
                <div class="form-group">
                    <label for="marketClientNumber" >商超单号</label>
                    <input type="text" name="marketClientNumber" id="marketClientNumber"data-bind="value:marketClientNumber"  class="k-input k-textbox" maxlength="50">
                </div>
                <div class="form-group">
                <label for="lnetOrderNumber">新易泰单号</label>
                <input type="text" name="lnetOrderNumber" id="lnetOrderNumber" data-bind="value:lnetOrderNumber" class="k-input k-textbox" readonly="false">
            </div>
            <div class="form-group">
                <label for="orderDate">订单日期</label>
                <input type="date" id="orderDate" data-bind="kendoDatePicker:{value:orderDate,max:today,min:minDate}" name="orderDate"required="required"/>
            </div>
            <div class="form-group">
                <label for="orderType">订单类型</label>
            <@utils.koDropDown data="$root.orderTypeList" value="orderType" required=true/>
            </div>
           <#-- <div class="form-group">
                <label for="consignerMan">发货人</label>
                <input type="text" name="consignerMan" id="consignerMan" data-bind="value:consignerMan" class="k-input k-textbox" maxlength="25"required="required">
            </div>
            <div class="form-group">
                <label for="consignerPhone">发货人电话</label>
                <input type="text" name="consignerPhone" id="consignerPhone" data-bind="value:consignerPhone" class="k-input k-textbox" maxlength="25"required="required"
                       >
            </div>
            <div class="form-group">
                <label for="consignerAddress">发货地址</label>
                <textarea class="k-textbox" name="consignerAddress"  id="consignerAddress"rows="5"data-bind="value:consignerAddress" maxlength="250"required="required"></textarea>
            </div>-->
                <div class="form-group">
                    <label for="transportType">运输方式</label>
                <@utils.koDropDown data="$root.transportTypeList" value="transportType"required=true/>
                </div>
                <div class="form-group">
                <label for="startCityId">始发城市</label>
            <@utils.koComboBox data="$root.citys" value="startCityId" textField="name" valueField="regionId" required=true/>
            </div>
            <div class="form-group">
                <label for="destCityId">目的城市</label>
            <#--<@utils.koComboBox data="$root.citys" value="destCityId" textField="name" valueField="regionId" />-->
                <input id="destCityId" name="destCityId" data-bind="kendoComboBox: { data: $root.citys, value: destCityId
        , dataTextField: 'name',filter: 'contains',dataValueField:'regionId', change:$root.getExpectedDate }"required="required" />
            </div>
            <div class="form-group">
                <label for="receiveCompany">收货公司</label>
                <input type="text" name="receiveCompany" id="receiveCompany" data-bind="value:receiveCompany" class="k-input k-textbox" maxlength="250"required="required">
            </div>
            <div class="form-group">
                <label for="receiveMan">收货人</label>
                <input type="text" name="receiveMan" id="receiveMan" data-bind="value:receiveMan" class="k-input k-textbox" maxlength="50"required="required">
            </div>
            <div class="form-group">
                <label for="receivePhone">收货人电话</label>
                <input type="text" name="receivePhone" id="receivePhone" data-bind="value:receivePhone" class="k-input k-textbox" maxlength="50"required="required"
                       >
            </div>
            <div class="form-group">
                <label for="receiveAddress">收货地址</label>
                <textarea class="k-textbox" name="receiveAddress"  id="receiveAddress"rows="5"data-bind="value:receiveAddress" maxlength="250"required="required"></textarea>
            </div>
        </div>
        <div data-bind="with:model" class="col-6">
            <div class="form-group">
                <label for="orderDispatchType">配送类型</label>
            <@utils.koDropDown data="$root.orderDispatchTypeList" value="orderDispatchType" required=true/>
            </div>
            <div class="form-group">
                <label for="handoverType">交接方式</label>
            <@utils.koDropDown data="$root.handoverTypeList" value="handoverType"required=true/>
            </div>
            <div class="form-group">
                <label for="totalItemQuantity">总数量</label>
                <input type="number" name="totalItemQuantity" data-bind="value:totalItemQuantity" id="totalItemQuantity" class="k-input k-textbox" min="1" required="required" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="totalPackageQuantity">总件数</label>
                <input type="number" name="totalPackageQuantity" data-bind="value:totalPackageQuantity" id="totalPackageQuantity" class="k-input k-textbox" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="totalVolume">理论体积（立方）</label>
                <input type="number" name="totalVolume" id="totalVolume" data-bind="value:totalVolume" class="k-input k-textbox" min="0" required="required" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="totalWeight">理论重量（公斤）</label>
                <input type="number" name="totalWeight" id="totalWeight" data-bind="value:totalWeight" class="k-input k-textbox" min="0" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="specialRequest">特殊要求</label>
                <textarea class="k-textbox" name="specialRequest" id="specialRequest" rows="5" data-bind="value:specialRequest"maxlength="250"></textarea>
            </div>
            <div class="form-group">
                <label for="billingCycle">结算周期</label>
            <@utils.koDropDown data="$root.settleCycleList" value="billingCycle"required=true/>
            </div>
            <div class="form-group">
                <label for="paymentType">支付方式</label>
            <@utils.koDropDown data="$root.paymentTypeList" value="paymentType"required=true/>
            </div>
            <div class="form-group">
                <label for="calculateType">计费方式</label>
            <@utils.koDropDown data="$root.calculateTypeList" value="calculateType"required=true/>
            </div>
            <div class="form-group">
                <label for="urgencyLevel">紧急程度</label>
                <@utils.koDropDown data="$root.urgencyLevel" value="urgencyLevel"/>
            </div>
            <div class="form-group">
                <label for="expectedDate" >预计到货日期</label>
                <input type="date"  id="expectedDate" data-bind="kendoDateTimePicker:{value:expectedDate,min:orderDate}" name="expectedDate"required="required">
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark"maxlength="250"></textarea>
            </div>
        </div>

        <div class="clear">
            <h3>订单明细</h3>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.detailsGrid, data: $root.detailsDataSource, sortable: true, editable:{ confirmation:false}, columns: $root.detailColumns, toolbar:$root.detailToolbar,save:$root.detailsChange,remove:$root.detailsRemove }"></div>
            </div>
        </div>

        <div style="margin-top: 20px">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 保存
            </button>
            <button type="button" data-bind="kendoButton:$root.updateAndConfirm">
                <i class="fa fa-edit"></i>审单至订单
            </button>
            <button type="button" data-bind="kendoButton:$root.updateAndConfirm1">
                <i class="fa fa-edit"></i>审单至应收
            </button>
            <button type="button" data-action="goBack" class="k-button k-button-icontext">
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
        self.model.urgencyLevel = ko.observable(${otdTransportOrder.urgencyLevel});
        self.model.today=ko.observable(new Date());
        var clientOrderNumber =self.model.clientOrderNumber();
        if(self.model.isTemp()){
            $("#clientOrderNumber").removeAttr("readonly");
        }
        self.minDate=function(){
            var today=new Date();
            var month=today.getMonth();
            var year=today.getFullYear();
            if(today.getDate()>7){
                month=parseInt(month)+1;
            }
            if(month==0){
                year=year-1;
                month=12;
            }
            if(month<10){
                month="-0"+month;
            }else{
                month="-"+month;
            }
            return new Date(year+month+"-01");
        }
        self.model.minDate=ko.observable(self.minDate());

        self.orderTypeList = commonData.orderType;
        self.orderDispatchTypeList = commonData.orderDispatchType;
        self.handoverTypeList = commonData.handoverType;
        self.calculateTypeList = commonData.calculateType;
        self.transportTypeList = commonData.transportType;
        self.settleCycleList = commonData.settleCycle;
        self.paymentTypeList = commonData.paymentType;
        self.urgencyLevel=commonData.urgencyLevel;

        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.pickupOrders = kendo.utils.createDataSource("pickupOrderId", "/otdPickupOrder/getDataSourceByStatus");
        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");
        self.getExpectedDate=function(e){
            var orderDate=kendo.toString(kendo.parseDate(self.model.orderDate(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
            var clientId=self.model.clientId();
            var transportType=self.model.transportType();
            var startCityId=self.model.startCityId();
            var destCityId=$("#destCityId").val();
            if(orderDate &&clientId &&transportType &&startCityId &&destCityId) {
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "/otdTransportOrder/getExpectedDate?clientId=" + clientId + "&transportType=" + transportType + "&startCityId=" + startCityId + "&destCityId=" + destCityId + "&orderDate=" + orderDate
                }).done(function (result) {
                    self.model.expectedDate(result);
                });
            }
        }

        self.clientChange = function(e){
            var id = $("#clientId").val();
            if(id){
                $("#clientOrderNumber").attr("readonly",false);
                self.model.pickupOrderId("");
                self.pickupOrders.filter({
                    logic: "and",
                    filters: [{
                        field: "clientId",
                        operator: "eq",
                        value: id
                    }]
                });

                var data = self.clientIds.data();
                for(var i =0; i<data.length;i++){
                    var item = data[i];
                    if(item.clientId==id){
                        self.model.paymentType(item.paymentType);
                        self.model.billingCycle(item.settleCycle);
                    }
                }
            }else{
                $("#clientOrderNumber").attr("readonly",true);
            }
        }
        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({
            data:${otdTransportOrderDetailsJson},
            schema: {
                model: {
                    fields: {
                        goodsCode: {type: "string"},
                        goodsName: {type: "string"},
                        goodsType: {type: "string"},
                        totalItemQuantity: {type: "number",validation:{pattern:"^\\d+$",min:1}},
                        totalPackageQuantity: {type: "number",validation:{pattern:"^\\d+$",min:1}},
                        totalVolume: {type: "string",validation: {pattern:"^\\d+(\\.\\d{0,6})?$",required: {message: "体积为必填项"}}},
                        totalWeight: {type: "number",validation: {min:0}},
                        remark: {type: "string"}
                    }
                }
        }
        });
        self.detailToolbar = [{name: "create"}];
        self.detailColumns = [{
            field: "goodsCode",
            title: "货物编码"
        }, {
            field: "goodsName",
            title: "货物名称"
        }, {
            field: "goodsType",
            title: "货物型号"
        }, {
            field: "totalItemQuantity",
            title: "数量"
        }, {
            field: "totalPackageQuantity",
            title: "件数"
        }, {
            field: "totalVolume",
            title: "体积"
        }, {
            field: "totalWeight",
            title: "重量"
        }, {
            field: "remark",
            title: "备注"
        }, {command: ["destroy"], width: 90}];

        self.detailsChange = function (e) {
            var totalVolume = 0;
            var totalPackageQuantity = 0;
            var totalWeight = 0;
            var totalItemQuantity = 0;
            if(e.values.totalWeight&&e.values.totalWeight!=e.model.totalWeight){
                e.model.totalWeight = e.values.totalWeight;
            }
            if(e.values.totalVolume&&e.values.totalVolume!=e.model.totalVolume){
                e.model.totalVolume = e.values.totalVolume;
            }
            if(e.values.totalItemQuantity&&e.values.totalItemQuantity!=e.model.totalItemQuantity){
                e.model.totalItemQuantity = e.values.totalItemQuantity;
            }
            if(e.values.totalPackageQuantity&&e.values.totalPackageQuantity!=e.model.totalPackageQuantity){
                e.model.totalPackageQuantity = e.values.totalPackageQuantity;
            }
            for (var i = 0; i < self.detailsDataSource.data().length; i++) {
                var dataItem= self.detailsDataSource.data()[i];
                totalVolume = parseFloat(totalVolume)+parseFloat(dataItem.totalVolume==null?0:dataItem.totalVolume);
                totalWeight = parseFloat(totalWeight)+parseFloat(dataItem.totalWeight==null?0:dataItem.totalWeight);
                totalItemQuantity = parseInt(totalItemQuantity)+parseInt(dataItem.totalItemQuantity==null?0:dataItem.totalItemQuantity);
                totalPackageQuantity =parseInt(totalPackageQuantity)+ parseInt(dataItem.totalPackageQuantity==null?0:dataItem.totalPackageQuantity);
            }
            self.model.totalPackageQuantity(totalPackageQuantity);
            self.model.totalVolume(totalVolume);
            self.model.totalWeight(totalWeight);
            self.model.totalItemQuantity(totalItemQuantity);
        }

        self.detailsRemove=function(e){
            var totalVolume = self.model.totalVolume();
            var totalPackageQuantity = self.model.totalPackageQuantity();
            var totalWeight = self.model.totalWeight();
            var totalItemQuantity = self.model.totalItemQuantity();
            totalVolume=totalVolume-e.model.totalVolume;
            totalPackageQuantity=totalPackageQuantity-e.model.totalPackageQuantity;
            totalWeight=totalWeight-e.model.totalWeight;
            totalItemQuantity=totalItemQuantity-e.model.totalItemQuantity;
            self.model.totalPackageQuantity(totalPackageQuantity);
            self.model.totalVolume(totalVolume);
            self.model.totalWeight(totalWeight);
            self.model.totalItemQuantity(totalItemQuantity);
        }

        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            if(clientOrderNumber!=self.model.clientOrderNumber()){
                self.model.isTemp(false);
            }
            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!this.model.totalWeight()||this.model.totalWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==2) {//体积
                if (!this.model.totalPackageQuantity()||this.model.totalPackageQuantity()<=0) {
                    notify("按件数计费，件数须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==3) {//体积
                if (!this.model.totalVolume()||this.model.totalVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return;
                }
            }
            self.model.details = self.detailsDataSource.data();
            if(self.detailsDataSource.data().length==0){
                notify("请至少添加一条订单明细！", "error");
                return;
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
                    router.navigate("/otdTransportOrderView");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.updateAndConfirm1 = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!this.model.totalWeight()||this.model.totalWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==2) {//体积
                if (!this.model.totalPackageQuantity()||this.model.totalPackageQuantity()<=0) {
                    notify("按件数计费，件数须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==3) {//体积
                if (!this.model.totalVolume()||this.model.totalVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return;
                }
            }
            self.model.details = self.detailsDataSource.data();
            if(self.detailsDataSource.data().length==0){
                notify("请至少添加一条订单明细！", "error");
                return;
            }
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/updateAndConfirm",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("修改并审单成功！", "success");
                    var orderReceivableId=result.content;
                    var url="#/feeOrderReceivable/modify/"+orderReceivableId;
                    router.navigate(url);
                }
                else {
                    notify(result.message, "error");
                }
            });
        }

        self.updateAndConfirm = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!this.model.totalWeight()||this.model.totalWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==2) {//体积
                if (!this.model.totalPackageQuantity()||this.model.totalPackageQuantity()<=0) {
                    notify("按件数计费，件数须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==3) {//体积
                if (!this.model.totalVolume()||this.model.totalVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return;
                }
            }
            self.model.details = self.detailsDataSource.data();
            if(self.detailsDataSource.data().length==0){
                notify("请至少添加一条订单明细！", "error");
                return;
            }
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/updateAndConfirm",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("修改并审单成功！", "success");
                    router.navigate("/otdTransportOrderView");
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
                    numberCheck:"#clientId"
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