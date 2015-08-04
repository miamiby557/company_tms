<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdCarrierOrder"><i class="fa fa-users"></i> 托运单</a></li>
    <li><a href="#/otdCarrierOrder/create"><i class="fa fa-plus"></i> 增加托运单</a></li>
</ol>

<div>
    <form id="create-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div class="col-6" data-bind="with:model">

            <div class="form-group">
                <label for="carrierId">承运商名称</label>
                <input id="carrierId" name="carrierId" data-bind="kendoComboBox: { data: $root.carrierIds, value: carrierId
            ,dataTextField: 'name',filter: 'contains',dataValueField:'carrierId', change:$root.carrierChange }" required="required"/>
            </div>
            <div class="form-group">
                <label for="carrierOrderNumber">托运单号</label>
                <input type="text" class="k-input k-textbox" name="carrierOrderNumber" id="carrierOrderNumber"
                       data-bind="value:carrierOrderNumber" readonly="true" required="required" maxlength="50">
            </div>
            <div class="form-group">
                <label for="transferOrganizationId">中转站名称</label>
                <input id="transferOrganizationId" name="transferOrganizationId" data-bind="kendoComboBox: { data: $root.organizations, value: transferOrganizationId
            ,dataTextField: 'name',filter: 'contains',dataValueField:'organizationId' }" />
            </div>
            <div class="form-group">
                <label for="consignee">收货人</label>
                <input type="text" class="k-input k-textbox" name="consignee" id="consignee" data-bind="value:consignee"
                       required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="consigneePhone">收货电话</label>
                <input type="text" class="k-input k-textbox" name="consigneePhone" id="consigneePhone"
                       data-bind="value:consigneePhone" maxlength="50">
            </div>
            <div class="form-group">
                <label for="consigneeAddress">收货地址</label>
                <textarea id="consigneeAddress" name="consigneeAddress" data-bind="value:consigneeAddress" required="required" class="k-textbox" rows="5"></textarea>
            </div>
            <div class="form-group">
                <label for="goodsName">货物名称</label>
                <input type="text" class="k-input k-textbox" name="goodsName" id="goodsName" data-bind="value:goodsName"
                       maxlength="25">
            </div>
            <div class="form-group">
                <label for="receiptPageNumber">回单份数</label>
                <input type="number" class="k-input k-textbox" readonly="true" name="receiptPageNumber" id="receiptPageNumber" data-bind="value:receiptPageNumber">
            </div>
            <div class="form-group">
                <label for="startCityId">始发城市</label>
            <@utils.koComboBox data="$root.citys" value="startCityId" textField="name" valueField="regionId" required=true/>
            </div>
            <div class="form-group">
                <label for="destCityId">目的城市</label>
            <@utils.koComboBox data="$root.citys" value="destCityId" textField="name" valueField="regionId" required=true/>
            </div>
            <div class="form-group">
                <label for="sendDate">发运时间</label>
                <input type="date" name="sendDate" id="sendDate" data-bind="kendoDateTimePicker:sendDate">
            </div>
            <div class="form-group">
                <label for="driver">司机</label>
                <input type="text" class="k-input k-textbox" name="driver" id="driver" data-bind="value:driver"
                       maxlength="25">
            </div>
            <div class="form-group">
                <label for="driverPhone">司机电话</label>
                <input type="text" class="k-input k-textbox" name="driverPhone" id="driverPhone"
                       data-bind="value:driverPhone" maxlength="25"
                        >
            </div>
            <div class="form-group">
                <label for="carType">车型</label>
                <input type="text" class="k-input k-textbox" name="carType" id="carType" data-bind="value:carType"
                       maxlength="25">
            </div>
            <div class="form-group">
                <label for="carNumber">车牌号码</label>
                <input type="text" class="k-input k-textbox" name="carNumber" id="carNumber" data-bind="value:carNumber"
                       maxlength="25">
            </div>
        </div>

        <div class="col-6" data-bind="with:model">

            <div class="form-group">
                <label for="totalVolume">总体积</label>
                <input type="number" class="k-input k-textbox" name="totalVolume" id="totalVolume" readonly="true"
                       data-bind="value:totalVolume" required="required" maxlength="25"placeholder="自动计算">
            </div>
            <div class="form-group">
                <label for="totalWeight">总重量</label>
                <input type="number" class="k-input k-textbox" name="totalWeight" id="totalWeight" readonly="true"
                        data-bind="value:totalWeight"placeholder="自动计算">
            </div>
            <div class="form-group">
                <label for="totalItemQuantity">总商品数量</label>
                <input type="number" class="k-input k-textbox" name="totalItemQuantity" readonly="true"
                       id="totalItemQuantity" required="required" data-bind="value:totalItemQuantity"placeholder="自动计算">
            </div>
            <div class="form-group">
                <label for="totalPackageQuantity">总件数</label>
                <input type="number" class="k-input k-textbox" name="totalPackageQuantity" readonly="true"
                       id="totalPackageQuantity" required="required" data-bind="value:totalPackageQuantity"placeholder="自动计算">
            </div>
            <div class="form-group">
                <label for="billingCycle">结算周期</label>
            <@utils.koDropDown data="$root.settleCycleList" value="billingCycle"required=true/>
            </div>
            <div class="form-group">
                <label for="paymentType">支付方式</label>
            <@utils.koDropDown data="$root.paymentTypes" value="paymentType" required=true />
            </div>
            <div class="form-group">
                <label for="transportType">运输方式</label>
            <@utils.koDropDown data="$root.transportTypes" value="transportType" required=true/>
            </div>
            <div class="form-group">
                <label for="calculateType">计费方式</label>
            <@utils.koDropDown data="$root.calculateTypes" value="calculateType" required=true/>
            </div>
            <div class="form-group">
                <label for="handoverType">交接方式</label>
            <@utils.koDropDown data="$root.handoverTypes" value="handoverType" required=true/>
            </div>
            <div class="form-group">
                <input type="checkbox" name="isUpstairs" id="isUpstairs" data-bind="checked:isUpstairs">
                <label for="isUpstairs">是否产生上楼费</label>
            </div>
            <div class="form-group">
                <input type="checkbox" name="isSign" id="isSign" data-bind="checked:isSign">
                <label for="isSign">是否签回单</label>
            </div>
            <div class="form-group">
                <label for="wrapType">包装类型</label>
            <@utils.koDropDown data="$root.wrapTypes" value="wrapType" required=true/>
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" data-bind="value:remark" class="k-textbox" rows="5"></textarea>
            </div>

        </div>

        <div class="clear">
            <h3>托运单明细</h3>
            <div >
                <button type="button" data-bind="kendoButton:add">
                    <i class="fa fa-plus"></i> 选择运输订单
                </button>
            </div>

            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.detailsGrid, data:  $root.detailsDataSource,sortable: true, editable:{ confirmation:false},
                columns: $root.detailColumns,save:$root.gridChange,remove:$root.gridRemove }"></div>
            </div>
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 保存
            </button>
            <button type="button" data-bind="kendoButton:send1">
                <i class="fa fa-edit"></i>发运至应付
            </button>
            <button type="button" data-bind="kendoButton:send ">
                <i class="fa fa-edit"></i> 发运至托运单
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
        self.model.carrierId = ko.observable();
        self.model.carrierOrderNumber = ko.observable();
        self.model.consigneeAddress = ko.observable();
        self.model.consigneePhone = ko.observable();
        self.model.consignee = ko.observable();
        self.model.driver = ko.observable();
        self.model.remark = ko.observable();
        self.model.carType = ko.observable();
        self.model.carNumber = ko.observable();
        self.model.driverPhone = ko.observable();
        self.model.transportType = ko.observable(1);
        self.model.calculateType = ko.observable(3);
        self.model.status = ko.observable("");
        self.model.paymentType = ko.observable(3);
        self.model.handoverType = ko.observable(1);
        self.model.wrapType = ko.observable(2);
        self.model.totalPackageQuantity = ko.observable();
        self.model.totalVolume = ko.observable();
        self.model.goodsName = ko.observable();
        self.model.totalWeight = ko.observable();
        self.model.totalItemQuantity = ko.observable();
        self.model.contactAddress = ko.observable();
        self.model.cityId = ko.observable();
        self.model.transportOrderIds = ko.observable();
        self.model.details = ko.observable();
        self.model.detailViews = ko.observable();
        self.model.startCityId = ko.observable();
        self.model.destCityId = ko.observable();
        self.model.sendDate = ko.observable();
        self.model.isSign = ko.observable(true);
        self.model.isUpstairs = ko.observable(false);
        self.model.receiptPageNumber = ko.observable();
        self.model.transferOrganizationId = ko.observable();
        self.model.billingCycle= ko.observable(3);
        self.settleCycleList=commonData.settleCycle;
        self.paymentTypes = commonData.paymentType;
        self.transportTypes = commonData.transportType;
        self.calculateTypes = commonData.calculateType;
        self.carrierOrderStatuss = commonData.carrierOrderStatus;
        self.handoverTypes = commonData.handoverType;
        self.wrapTypes = commonData.wrapType;
        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.carrierIds = kendo.utils.createListDataSource("carrierId", "/scmCarrier/getDataSource");
        self.organizations = kendo.utils.createListDataSource("organizationId", "/sysOrganization/getDataSource");
        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({
            data:[],
            schema: {
                model: {
                    fields: {
                        clientName: {type: "string", editable: false},
                        clientOrderNumber: {type: "string", editable: false},
                        receiveCompany: {type: "string", editable: false},
                        receiveMan: {type: "string", editable: false},
                        destCity: {type: "string", editable: false},
                        confirmedItemQuantity: {type: "number",validation:{pattern:"^\\d+$",min:1}},
                        confirmedPackageQuantity: {type: "number",validation:{pattern:"^\\d+$",min:1}},
                        confirmedVolume: {type: "string",validation: {pattern:"^\\d+(\\.\\d{0,6})?$",required: {message: "体积为必填项"}}},
                        confirmedWeight: {type: "number",validation: {required: {message: "重量为必填项"},min:0}},
                        receiptPageNumber:{type: "number",validation:{required: {message: "回单页数为必填项"},pattern:"^\\d+$",min:1}}
                    }
                }
            }
        });
        self.add =function(){
            var url = "otdTransportOrder/search";
            modal("选择运输订单", url, {width:1000, height: 600, close:function(e){
                var currentData = self.detailsDataSource.data();
                if (e.sender.sender) {
                    if(currentData.length>0){
                        for(var i=0;i<e.sender.sender.length;i++){
                            var flag = true;
                            for(var j = 0;j<currentData.length;j++){
                                if(currentData[j].transportOrderId==e.sender.sender[i].transportOrderId){
                                    flag = false;
                                }
                            }
                            if(flag){
                                e.sender.sender[i].wrapType=2;
                                if(!e.sender.sender[i].receiptPageNumber) {
                                    e.sender.sender[i].receiptPageNumber = 1;
                                }
                                self.detailsDataSource.data().push(ko.toJS(e.sender.sender[i]));
                            }
                        }
                    }else{
                        for(var i=0;i<e.sender.sender.length;i++) {
                            e.sender.sender[i].wrapType=2;
                            if(!e.sender.sender[i].receiptPageNumber) {
                                e.sender.sender[i].receiptPageNumber = 1;
                            }
                            self.detailsDataSource.data().push(ko.toJS(e.sender.sender[i]));
                        }
                    }
                }
                self.calculate();
            }});
        }
        self.carrierChange = function(e){
            var id = $("#carrierId").val();
            if(id){
                $("#carrierOrderNumber").attr("readonly",false);
                var data = self.carrierIds.data();
                for(var i =0; i<data.length;i++){
                    var item = data[i];
                    if(item.carrierId==id){
                        self.model.paymentType(item.paymentType);
                        self.model.billingCycle(item.settleCycle);
                    }
                }
            }
            else{
                $("#carrierOrderNumber").attr("readonly",true);
            }
        }
        self.gridRemove = function(e){
            var receiptPageNumber = self.model.receiptPageNumber();
            var totalPackageQuantity=self.model.totalPackageQuantity();
            var totalVolume = self.model.totalVolume();
            var totalWeight = self.model.totalWeight();
            var totalItemQuantity = self.model.totalItemQuantity();
            self.model.receiptPageNumber(receiptPageNumber- e.model.receiptPageNumber);
            self.model.totalPackageQuantity(totalPackageQuantity- e.model.confirmedPackageQuantity);
            self.model.totalVolume((totalVolume- e.model.confirmedVolume).toFixed(6));
            self.model.totalWeight((totalWeight- e.model.confirmedWeight).toFixed(2));
            self.model.totalItemQuantity(totalItemQuantity- e.model.confirmedItemQuantity);
        }
        self.calculate = function (){
            var totalVolume = 0;
            var totalPackageQuantity = 0;
            var totalWeight = 0;
            var totalItemQuantity = 0;
            var  receiptPageNumber = 0;
            var consignee ='';
            var consigneeAddress ='';
            var consigneePhone ='';
            var startCityId='';
            var destCityId='';
            var transportType = '';
            var handoverType = '';
            var calculateType = '';
            var paymentType ="";
            var wrapType="";
            var maxOrderDate= kendo.parseDate(null,"yyyy-MM-dd");//计算默认发运时间
            for (var i = 0; i < self.detailsDataSource.data().length; i++) {
                var dataItem= self.detailsDataSource.data()[i];
                consignee = dataItem.receiveMan;
                consigneeAddress = dataItem.receiveAddress;
                consigneePhone = dataItem.receivePhone;
                startCityId = dataItem.startCityId;
                destCityId = dataItem.destCityId;
                transportType = dataItem.transportType;
                handoverType = dataItem.handoverType;
                calculateType = dataItem.calculateType;
                paymentType = dataItem.paymentType;
                wrapType = dataItem.wrapType;
                totalVolume = parseFloat(totalVolume) + parseFloat(dataItem.confirmedVolume);
                totalWeight = totalWeight + dataItem.confirmedWeight;
                totalItemQuantity =parseInt(totalItemQuantity) + parseInt(dataItem.confirmedItemQuantity);
                totalPackageQuantity =parseInt(totalPackageQuantity) + parseInt(dataItem.confirmedPackageQuantity);
                receiptPageNumber =parseInt(receiptPageNumber) + parseInt(dataItem.receiptPageNumber);
                var currentOrderDate=kendo.parseDate(self.detailsDataSource.data()[i].orderDate,"yyyy-MM-dd");
                if(currentOrderDate>maxOrderDate){
                    maxOrderDate=currentOrderDate;
                }
            }
            self.model.destCityId(destCityId);
            self.model.startCityId(startCityId);
            self.model.wrapType(wrapType);
            self.model.receiptPageNumber(receiptPageNumber);
            self.model.consignee(consignee);
            self.model.transportType(transportType);
            self.model.paymentType(paymentType);
            self.model.calculateType(calculateType);
            self.model.handoverType(handoverType);
            self.model.consigneeAddress(consigneeAddress);
            self.model.consigneePhone(consigneePhone);
            self.model.totalPackageQuantity(totalPackageQuantity);
            self.model.totalVolume(totalVolume.toFixed(6));
            self.model.totalWeight(totalWeight.toFixed(2));
            self.model.totalItemQuantity(totalItemQuantity);
            var defaultDate=kendo.toString(maxOrderDate,"yyyy-MM-dd")+" 23:00:00";
            self.model.sendDate(kendo.parseDate(defaultDate,"yyyy-MM-dd HH:mm:ss"));
        }
        self.gridChange = function (e) {
            if (e.values.confirmedWeight!==undefined&& e.values.confirmedWeight != e.model.confirmedWeight) {
                e.model.confirmedWeight = e.values.confirmedWeight ?e.values.confirmedWeight:0;
            }
            if (e.values.confirmedVolume!==undefined && e.values.confirmedVolume != e.model.confirmedVolume) {
                e.model.confirmedVolume = e.values.confirmedVolume ?e.values.confirmedVolume:0;
            }
            if (e.values.confirmedItemQuantity!==undefined && e.values.confirmedItemQuantity != e.model.confirmedItemQuantity) {
                e.model.confirmedItemQuantity = e.values.confirmedItemQuantity?e.values.confirmedItemQuantity:0;
            }
            if (e.values.confirmedPackageQuantity!==undefined && e.values.confirmedPackageQuantity != e.model.confirmedPackageQuantity) {
                e.model.confirmedPackageQuantity = e.values.confirmedPackageQuantity?e.values.confirmedPackageQuantity:0;
            }
            if (e.values.receiptPageNumber&& e.values.receiptPageNumber != e.model.receiptPageNumber) {
                e.model.receiptPageNumber = e.values.receiptPageNumber;
            }
            if (e.values.wrapType&& e.values.wrapType != e.model.wrapType) {
                e.model.wrapType = e.values.wrapType;
            }
            var totalVolume = 0;
            var totalPackageQuantity = 0;
            var totalWeight = 0;
            var totalItemQuantity = 0;
            var receiptPageNumber = 0;
            var wrapType = 1;
            for (var i = 0; i < self.detailsDataSource.data().length; i++) {
                var dataItem = self.detailsDataSource.data()[i];
                totalVolume = parseFloat(totalVolume) + parseFloat(dataItem.confirmedVolume);
                totalWeight = totalWeight + dataItem.confirmedWeight;
                totalItemQuantity = parseInt(totalItemQuantity) + parseInt(dataItem.confirmedItemQuantity);
                totalPackageQuantity = parseInt(totalPackageQuantity) + parseInt(dataItem.confirmedPackageQuantity);
                receiptPageNumber = parseInt(receiptPageNumber) + parseInt(dataItem.receiptPageNumber);
                wrapType = dataItem.wrapType;
            }
            self.model.wrapType(wrapType);
            self.model.receiptPageNumber(receiptPageNumber);
            self.model.totalPackageQuantity(totalPackageQuantity);
            self.model.totalVolume(totalVolume.toFixed(6));
            self.model.totalWeight(totalWeight.toFixed(2));
            self.model.totalItemQuantity(totalItemQuantity);
            /*self.calculate();*/
        }
        self.detailColumns = [ {
            field: "clientName",
            title: "客户", width: 80
        },{
            field: "clientOrderNumber",
            title: "客户单号", width: 80
        }, {
            field: "receiveCompany",
            title: "收货公司", width: 80
        }, {
            field: "receiveMan",
            title: "收货人", width: 80
        }, {
            field: "destCity",
            title: "目的地", width: 80
        }, {
            field: "confirmedVolume",
            title: "体积(立方)", width: 80
        }, {
            field: "confirmedWeight",
            title: "重量(千克)", width: 80
        }, {
            field: "confirmedItemQuantity",
            title: "商品数量", width: 80
        }, {
            field: "confirmedPackageQuantity",
            title: "件数", width: 80
        }, {
            field: "receiptPageNumber",
            title: "回单份数", width: 80
        }, {
            field: "wrapType",
            title: "包装类型",
            values:commonData.wrapType(),
            width: 80
        }, {command: ["destroy"], width: 60}];
        self.save = function () {
            // 检查表单是否通过验证

            $("#sendDate").removeAttr("required");
            self.model.sendDate("");
            if (!$("#create-form").valid()) return;

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

            if(self.detailsDataSource.data().length<1){
                notify("请选择运输订单！","error");
                return;
            }
            self.model.details = ko.toJS(self.detailsDataSource.data());
            self.model.detailViews = ko.toJS(self.detailsDataSource.data());
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdCarrierOrder/createCarrierOrder",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加成功", "success");
                    router.navigate("/otdCarrierOrder");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.send = function () {
            // 检查表单是否通过验证
            $("#sendDate").attr("required","required");
            if (!$("#create-form").valid()) return;
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

            if(self.detailsDataSource.data().length<1){
                notify("请选择运输订单！","error");
                return;
            }
            self.model.details = ko.toJS(self.detailsDataSource.data());
            self.model.detailViews = ko.toJS(self.detailsDataSource.data());
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdCarrierOrder/send",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("发运成功", "success");
                    router.navigate("/otdCarrierOrder");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.send1 = function () {
            // 检查表单是否通过验证

            $("#sendDate").attr("required","required");
            if (!$("#create-form").valid()) return;
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

            if(self.detailsDataSource.data().length<1){
                notify("请选择运输订单！","error");
                return;
            }
            self.model.details = ko.toJS(self.detailsDataSource.data());
            self.model.detailViews = ko.toJS(self.detailsDataSource.data());
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdCarrierOrder/send",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("发运成功", "success");
                    var orderPayableId=result.content;
                    var url="#/feeOrderPayable/modify/"+orderPayableId;
                    router.navigate(url);
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
    }
    $(function () {
        jQuery.validator.methods.numberCheck =function(value,element,params){
            var carrierId = $(params).val();
            var result = true;
            var url  = "/otdCarrierOrder/carrierOrderNumberIsExist?carrierOrderId=&carrierId="+carrierId+"&carrierOrderNumber="+value;
            $.ajax({
                type:"GET",
                contentType: "application/json",
                async:false,
                url:url
            }).done(function(response){
                    result = response;
            });
            return result;
        };
        $("#create-form").validate({
            rules:{
                "carrierOrderNumber":{
                    numberCheck:"#carrierId"
                }
            },messages:{
                "carrierOrderNumber":{
                    numberCheck: "此托运单号已存在"
                }
            }
        });
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("create-form"));
    });
</script>

