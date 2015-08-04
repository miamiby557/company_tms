<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdTransportOrderView"><i class="fa fa-users"></i> 运输订单</a></li>
    <li><i class="fa fa-plus"></i> 合单</li>
</ol>
<div>
    <form id="modify-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model" class="col-6">
            <div class="form-group">
                <label for="clientId">客户</label>
                <input id="clientId" name="clientId" data-bind="kendoComboBox: { data: $root.clientIds, value: clientId
        , dataTextField: 'name',filter: 'contains',dataValueField:'clientId'}"/>
            </div>
            <div class="form-group">
                <label for="clientOrderNumber">客户单号</label>
                <textarea id="clientOrderNumber" name="clientOrderNumber" class="k-textbox" rows="5" data-bind="value:clientOrderNumber" maxlength="2000" ></textarea>
            </div>
            <div class="form-group">
                <label for="orderDate">订单日期</label>
                <input type="date" id="orderDate" data-bind="kendoDatePicker:{value:orderDate}" name="orderDate"required="required">
            </div>
            <div class="form-group">
                <label for="startCityId">始发城市</label>
            <@utils.koComboBox data="$root.citys" value="startCityId" textField="name" valueField="regionId" required="required"/>
            </div>
            <div class="form-group">
                <label for="destCityId">目的城市</label>
            <@utils.koComboBox data="$root.citys" value="destCityId" textField="name" valueField="regionId" required="required"/>
            </div>
            <div class="form-group">
                <label for="receiveCompany">收货公司</label>
                <input type="text" name="receiveCompany" id="receiveCompany" data-bind="value:receiveCompany" class="k-input k-textbox" maxlength="25" required="required">
            </div>
            <div class="form-group">
                <label for="receiveMan">收货人</label>
                <input type="text" name="receiveMan" id="receiveMan" data-bind="value:receiveMan" class="k-input k-textbox" maxlength="50" required="required">
            </div>
            <div class="form-group">
                <label for="receivePhone">收货人电话</label>
                <input type="text" name="receivePhone" id="receivePhone" data-bind="value:receivePhone" class="k-input k-textbox" maxlength="50">
            </div>
            <div class="form-group">
                <label for="receiveAddress">收货地址</label>
                <textarea class="k-textbox" name="receiveAddress"  id="receiveAddress"rows="5"data-bind="value:receiveAddress" maxlength="250" required="required"></textarea>
            </div>
        </div>
        <div data-bind="with:model" class="col-6">
            <div class="form-group">
                <label for="billingCycle">结算周期</label>
            <@utils.koDropDown data="$root.settleCycleList" value="billingCycle"required=true/>
            </div>
            <div class="form-group">
                <label for="transportType">运输方式</label>
            <@utils.koDropDown data="$root.transportTypeList" value="transportType"required="required"/>
            </div>
            <div class="form-group">
                <label for="calculateType">计费方式</label>
            <@utils.koDropDown data="$root.calculateTypeList" value="calculateType"required="required"/>
            </div>
            <div class="form-group">
                <label for="totalItemQuantity">总数量</label>
                <input type="number" name="totalItemQuantity" data-bind="value:totalItemQuantity" id="totalItemQuantity" class="k-input k-textbox" min="1" readonly="true">
            </div>
            <div class="form-group">
                <label for="totalPackageQuantity">总件数</label>
                <input type="number" name="totalPackageQuantity" data-bind="value:totalPackageQuantity" id="totalPackageQuantity" class="k-input k-textbox" readonly="true">
            </div>
            <div class="form-group">
                <label for="totalVolume">理论体积（立方）</label>
                <input type="number" name="totalVolume" id="totalVolume" data-bind="value:totalVolume" class="k-input k-textbox" min="0" readonly="true" readonly="true">
            </div>
            <div class="form-group">
                <label for="totalWeight">理论重量（公斤）</label>
                <input type="number" name="totalWeight" id="totalWeight" data-bind="value:totalWeight" class="k-input k-textbox" min="0" readonly="true">
            </div>
            <div class="form-group">
                <label for="confirmedItemQuantity" >合单后总数量</label>
                <input type="number" name="confirmedItemQuantity"data-bind="value:confirmedItemQuantity"  id="confirmedItemQuantity" class="k-input k-textbox" min="1"required="required"pattern="^[1-9]\d*$">
            </div>
            <div class="form-group">
                <label for="confirmedPackageQuantity" >合单后总件数</label>
                <input type="number" name="confirmedPackageQuantity" data-bind="value:confirmedPackageQuantity"  id="confirmedPackageQuantity" class="k-input k-textbox" min="1" required="required"pattern="^[1-9]\d*$">
            </div>
            <div class="form-group">
                <label for="confirmedVolume" >合单后体积（立方）</label>
                <input type="number" name="confirmedVolume" id="confirmedVolume" data-bind="value:confirmedVolume"  pattern="\d+(\.\d{0,6})?"  class="k-input k-textbox" min="0" required="required">
            </div>
            <div class="form-group">
                <label for="confirmedWeight" >合单后重量（公斤）</label>
                <input type="number" name="confirmedWeight" id="confirmedWeight" data-bind="value:confirmedWeight"  class="k-input k-textbox"  min="0" required="required">
            </div>
        </div>
        <div class="clear">
            <h3>新增被合单</h3>
            <div>
                <button type="button" data-bind="kendoButton:$root.add">
                    <i class="fa fa-plus"></i> 增加
                </button>
            </div>
            <div id="add-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.addGrid, data: $root.addDataSource, sortable: true, editable:{destroy: true,update: false}, columns: $root.addColumns,remove:$root.remove}"></div>
            </div>
        </div>

        <div style="margin-top: 20px">
            <button type="button" data-bind="kendoButton:$root.save1">
                <i class="fa fa-edit"></i>确定合单至应收
            </button>
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 确定合单至订单
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
        self.model.transportOrderId = ko.observable();
        self.model.pickupOrderId = ko.observable();
        self.model.clientOrderNumber = ko.observable();
        self.model.lnetOrderNumber = ko.observable();
        self.model.orderDate = ko.observable();
        self.model.orderType= ko.observable(1);
        self.model.startCityId= ko.observable();
        self.model.destCityId= ko.observable();
        self.model.receiveCompany= ko.observable();
        self.model.receiveMan= ko.observable();
        self.model.receivePhone= ko.observable();
        self.model.receiveAddress= ko.observable();
        self.model.handoverType= ko.observable(1);
        self.model.totalPackageQuantity= ko.observable();
        self.model.totalItemQuantity = ko.observable();
        self.model.totalVolume= ko.observable();
        self.model.totalWeight= ko.observable();
        self.model.confirmedPackageQuantity= ko.observable();
        self.model.confirmedItemQuantity = ko.observable();
        self.model.confirmedVolume= ko.observable();
        self.model.confirmedWeight= ko.observable();
        self.model.specialRequest= ko.observable();
        self.model.billingCycle= ko.observable();
        self.model.paymentType= ko.observable();
        self.model.calculateType= ko.observable();
        self.model.transportType= ko.observable();
        self.model.remark= ko.observable();
        self.model.clientId=ko.observable();
        self.model.expectedDate=ko.observable();
        self.model.urgencyLevel=ko.observable(0);
        self.model.viceClientOrderNumber=ko.observable();
        self.model.marketClientNumber=ko.observable();
        self.model.receiptPageNumber = ko.observable();
        self.model.billingCycle= ko.observable();
        self.calculateTypeList = commonData.calculateType;
        self.transportTypeList = commonData.transportType;
        self.settleCycleList=commonData.settleCycle;

        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");
        self.addGrid = ko.observable();
        self.addDataSource = new kendo.data.DataSource({data:[]});
        self.addColumns = [ {
            title: "序号",
            template: function (dataItem) {
                return self.addDataSource.indexOf(dataItem)+1;
            },
            width: 40
        },{
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
            field: "confirmedItemQuantity",
            title: "数量", width: 80
        }, {
            field: "confirmedPackageQuantity",
            title: "件数", width: 80
        }, {
            field: "confirmedVolume",
            title: "体积(立方)", width: 80
        }, {
            field: "confirmedWeight",
            title: "重量(千克)", width: 80
        }, {command: ["destroy"], width: 60}];
        self.add=function(){
            if(!self.model.clientId()){
                notify("请先选择客户","error");
                return;
            }
            var url = "otdTransportOrder/mergeSearch/"+self.model.clientId();
            modal("选择被合单", url, {
                width: 1000, height: 600, close: function (e) {
                    var currentData = self.addDataSource.data();
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
                                    self.addDataSource.data().push(ko.toJS(e.sender.sender[i]));
                                }
                            }
                        }else{
                            for(var i=0;i<e.sender.sender.length;i++) {
                                self.addDataSource.data().push(ko.toJS(e.sender.sender[i]));
                            }
                        }
                        var model =self.addDataSource.data()[0];
                        self.model.orderDate(model.orderDate);
                        self.model.startCityId(model.startCityId);
                        self.model.destCityId(model.destCityId);
                        self.model.receiveCompany(model.receiveCompany);
                        self.model.receiveMan(model.receiveMan);
                        self.model.receivePhone(model.receivePhone);
                        self.model.receiveAddress(model.receiveAddress);
                        self.model.handoverType(model.handoverType);
                        self.model.calculateType(model.calculateType);
                        self.model.transportType(model.transportType);
                        self.model.billingCycle(model.billingCycle);
                        self.calculateSum();
                    }
                }
            });
        }

        self.calculateSum =function(){
            var totalVolume = 0;
            var totalPackageQuantity = 0;
            var totalWeight = 0;
            var totalItemQuantity = 0;
            var clientOrderNumber='';
            var receiptPageNumber =0;
            for (var i = 0; i < self.addDataSource.data().length; i++) {
                var dataItem = self.addDataSource.data()[i];
                totalVolume = parseFloat(totalVolume)+ parseFloat(dataItem.confirmedVolume);
                totalWeight = parseFloat(totalWeight) + parseFloat(dataItem.confirmedWeight);
                totalItemQuantity = parseInt(totalItemQuantity) + parseInt(dataItem.confirmedItemQuantity);
                totalPackageQuantity = parseInt(totalPackageQuantity) + parseInt(dataItem.confirmedPackageQuantity);
                clientOrderNumber+=dataItem.clientOrderNumber+",";
                receiptPageNumber++;
            }
            clientOrderNumber = clientOrderNumber.substr(0,clientOrderNumber.length-1);
            self.model.receiptPageNumber(receiptPageNumber);
            self.model.clientOrderNumber(clientOrderNumber);
            self.model.totalPackageQuantity(totalPackageQuantity);
            self.model.totalVolume(totalVolume.toFixed(6));
            self.model.totalWeight(totalWeight.toFixed(2));
            self.model.totalItemQuantity(totalItemQuantity);

            self.model.confirmedPackageQuantity(totalPackageQuantity);
            self.model.confirmedVolume(totalVolume);
            self.model.confirmedWeight(totalWeight);
            self.model.confirmedItemQuantity(totalItemQuantity);
        }
        self.remove=function(e){
            self.model.totalPackageQuantity(self.model.totalPackageQuantity()-e.model.totalPackageQuantity);
            self.model.totalVolume((self.model.totalVolume()-e.model.totalVolume).toFixed(6));
            self.model.totalWeight((self.model.totalWeight()-e.model.totalWeight).toFixed(2));
            self.model.totalItemQuantity(self.model.totalItemQuantity()-e.model.totalItemQuantity);
            self.model.receiptPageNumber(self.model.totalItemQuantity()-1);
            self.model.confirmedItemQuantity(self.model.confirmedItemQuantity()- e.model.confirmedItemQuantity);
            self.model.confirmedPackageQuantity(self.model.confirmedPackageQuantity()-e.model.confirmedPackageQuantity);
            self.model.confirmedVolume((self.model.confirmedVolume()-e.model.confirmedVolume).toFixed(6));
            self.model.confirmedWeight((self.model.confirmedWeight()-e.model.confirmedWeight).toFixed(2));

            var clientOrderNumber = self.model.clientOrderNumber()+",";
            clientOrderNumber = clientOrderNumber.replace(e.model.clientOrderNumber+",","");
            self.model.clientOrderNumber(clientOrderNumber.substr(0,clientOrderNumber.length-1));
        }
        self.validate =function(){
            if(self.addDataSource.data().length<2){
                notify("请选择被合订单！","error");
                return false;
            }
            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!this.model.confirmedWeight()||this.model.confirmedWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return false;
                }
            }else if(calculateType==2) {//数量
                if (!this.model.confirmedItemQuantity()||this.model.confirmedItemQuantity()<=0) {
                    notify("按件数计费，数量须大于 0 ！","error");
                    return false;
                }
            }else if(calculateType==3) {//体积
                if (!this.model.confirmedVolume()||this.model.confirmedVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return false;
                }
            }
            return true;
        }
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").valid()) return;
            if(!self.validate()) return;
            if(self.addDataSource.data().length==0){
                notify("请选择需合单的运输订单！", "error");
                return ;
            }
            // 发送AJAX请求保存数据
            var transportOrderIds=[];
            for(var i=0;i<self.addDataSource.data().length;i++){
                transportOrderIds.push(self.addDataSource.data()[i].transportOrderId);
            }
            self.model.transportOrderIds = transportOrderIds;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/merge",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("合单操作成功！", "success");
                    router.navigate("/otdTransportOrderView");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }

        self.save1 = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").valid()) return;
            if(!self.validate()) return;
            if(self.addDataSource.data().length==0){
                notify("请选择需合单的运输订单！", "error");
                return ;
            }
            var transportOrderIds=[];
            for(var i=0;i<self.addDataSource.data().length;i++){
                transportOrderIds.push(self.addDataSource.data()[i].transportOrderId);
            }
            self.model.transportOrderIds = transportOrderIds;
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/merge",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("合单操作成功！", "success");
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
        $("#modify-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });

</script>