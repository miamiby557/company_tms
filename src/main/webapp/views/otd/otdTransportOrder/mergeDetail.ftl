<#import "/utils/dropdown.ftl" as utils >
<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdTransportOrderView"><i class="fa fa-users"></i> 运输订单</a></li>
    <li><a href="#/otdTransportOrder/detail/${otdTransportOrder.transportOrderId}"><i class="fa fa-plus"></i> 订单详情</a>
    </li>
</ol>

<div>
    <form id="detail-form" method="post" class="form-horizontal">
        <div>
            <div data-bind="with:model" class="col-6">
                <div class="form-group">
                    <label for="clientId">客户</label>
                <@utils.koComboBox data="$root.clientIds" value="clientId" textField="name" valueField="clientId" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label for="clientOrderNumber">客户单号</label>
                    <textarea type="text" name="clientOrderNumber" data-bind="value:clientOrderNumber" id="clientOrderNumber" class="k-input k-textbox" required="required" rows="5" maxlength="2000"></textarea>
                </div>
                <div class="form-group">
                    <label for="lnetOrderNumber">新易泰单号</label>
                    <input type="text" name="lnetOrderNumber" id="lnetOrderNumber" data-bind="value:lnetOrderNumber" class="k-input k-textbox" readonly="true">
                </div>
                <div class="form-group">
                    <label for="orderDate">订单日期</label>
                    <input type="date" id="orderDate" data-bind="kendoDatePicker:orderDate" name="orderDate" required="required">
                </div>
                <div class="form-group">
                    <label for="receiveCompany">收货公司</label>
                    <input type="text" name="receiveCompany" id="receiveCompany" data-bind="value:receiveCompany" class="k-input k-textbox" required="required">
                </div>
                <div class="form-group">
                    <label for="receiveMan">收货人</label>
                    <input type="text" name="receiveMan" id="receiveMan" data-bind="value:receiveMan" class="k-input k-textbox" required="required">
                </div>
                <div class="form-group">
                    <label for="receivePhone">收货人电话</label>
                    <input type="text" name="receivePhone" id="receivePhone" data-bind="value:receivePhone" class="k-input k-textbox">
                </div>
                <div class="form-group">
                    <label for="receiveAddress">收货地址</label>
                    <textarea class="k-textbox" name="receiveAddress"  id="receiveAddress"rows="5"data-bind="value:receiveAddress"required="required"></textarea>
                </div>
            </div>

            <div data-bind="with:model" class="col-6">
                <div class="form-group">
                    <label for="billingCycle">结算周期</label>
                <@utils.koDropDown data="$root.settleCycleList" value="billingCycle"required=true/>
                </div>
                <div class="form-group">
                    <label for="transportType">运输方式</label>
                <@utils.koDropDown data="$root.transportTypeList" value="transportType" required="required"/>
                </div>
                <div class="form-group">
                    <label for="startCityId">始发城市</label>
                <@utils.koComboBox data="$root.citys" value="startCityId" textField="name" valueField="regionId" required="required"/>
                </div>
                <div class="form-group">
                    <label for="destCityId">目的城市</label>
                <@utils.koComboBox data="$root.citys" value="destCityId" textField="name" valueField="regionId"required="required"/>
                </div>
                <div class="form-group">
                    <label for="calculateType">计费方式</label>
                <@utils.koDropDown data="$root.calculateTypeList" value="calculateType"required="required"/>
                </div>
                <div class="form-group">
                    <label for="confirmedItemQuantity">实际数量</label>
                    <input type="number" name="confirmedItemQuantity" data-bind="value:confirmedItemQuantity" id="confirmedItemQuantity" class="k-input k-textbox" min="1"required="required">
                </div>
                <div class="form-group">
                    <label for="confirmedPackageQuantity">应收总件数</label>
                    <input type="number" name="confirmedPackageQuantity" data-bind="value:confirmedPackageQuantity" id="confirmedPackageQuantity" class="k-input k-textbox" min="1" required="required">
                </div>
                <div class="form-group">
                    <label for="confirmedVolume">应收体积（立方）</label>
                    <input type="text" name="confirmedVolume" id="confirmedVolume" data-bind="value:confirmedVolume" class="k-input k-textbox" min="0" required="required">
                </div>
                <div class="form-group">
                    <label for="confirmedWeight">应收重量（公斤）</label>
                    <input type="text" name="confirmedWeight" id="confirmedWeight" data-bind="value:confirmedWeight" class="k-input k-textbox" min="0" required="required">
                </div>

            </div>
            <div class="clear">
                <h3>订单明细</h3>
                <div id="details-grid-wrap">
                    <div data-bind="kendoGrid: {widget: $root.detailsGrid, data: $root.detailsDataSource, sortable: true, editable:false, columns: $root.detailColumns, toolbar:$root.detailToolbar }"></div>
                </div>
            </div>
            <div class="clear">
                <h3>新增被合单</h3>
                <div class="search-buttons">
                    <button type="button" data-bind="kendoButton:$root.add,enable:$root.canAdd">
                        <i class="fa fa-plus"></i> 增加
                    </button>
                </div>
                <div id="add-grid-wrap">
                    <div data-bind="kendoGrid: {widget: $root.addGrid, data: $root.addDataSource, sortable: true, editable:{destroy: true,update: false}, columns: $root.addColumns, toolbar:$root.addToolbar,remove:$root.remove}"></div>
                </div>
            </div>
        </div>
        <div style="margin-top: 20px">
            <button type="button" data-bind="kendoButton:$root.confirmAdd,enable:$root.canAdd">
                <i class="fa fa-edit"></i> 确认新增至订单
            </button>
            <button type="button" data-bind="kendoButton:$root.confirmAdd1,enable:$root.canAdd">
                <i class="fa fa-edit"></i> 确认新增至应收
            </button>
            <button type="button" data-bind="kendoButton:$root.repealMerge,enable:$root.canAdd">
                <i class="fa fa-edit"></i> 撤销合单
            </button>
            <button type="button" data-action="goBack" class="k-button k-button-icontext">
                <i class="fa fa-close"></i> 返回
            </button>
        </div>
    </form>
</div>
<script>

    function CreateModel() {
        var self = this;
        self.model = {};
        self.model.clientOrderNumber=ko.observable();
        self.model.confirmedItemQuantity=ko.observable();
        self.model.confirmedPackageQuantity=ko.observable();
        self.model.confirmedVolume=ko.observable();
        self.model.confirmedWeight=ko.observable();
        self.model.receiptPageNumber=ko.observable();
        self.model = ko.mapping.fromJS(${otdTransportOrderJson});
        self.orderTypeList = commonData.orderType;
        self.handoverTypeList = commonData.handoverType;
        self.calculateTypeList = commonData.calculateType;
        self.transportTypeList = commonData.transportType;
        self.settleCycleList = commonData.settleCycle;
        self.paymentTypeList = commonData.paymentType;
        self.urgencyLevel=commonData.urgencyLevel;
        self.wrapTypeList=commonData.wrapType;
        self.orderStatus =commonData.orderStatus ;
        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.pickupOrders = kendo.utils.createDataSource("pickupOrderId", "/otdPickupOrder/getDataSource");
        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");
        self.canAdd=ko.observable("${flag}"==0);

        self.add=function(){
            var url = "otdTransportOrder/mergeSearch/${otdTransportOrder.clientId}";
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
                        self.calculateSum();
                    }
                }
            });
        }

        self.calculateSum =function(){
            var confirmedPackageQuantity = self.model.confirmedPackageQuantity();
            var confirmedVolume = self.model.confirmedVolume();
            var confirmedWeight = self.model.confirmedWeight();
            var confirmedItemQuantity = self.model.confirmedItemQuantity();
            var clientOrderNumber = self.model.clientOrderNumber();
            var receiptPageNumber = self.model.receiptPageNumber();
            for (var i = 0; i < self.addDataSource.data().length; i++) {
                var dataItem = self.addDataSource.data()[i];
                confirmedVolume = parseFloat(confirmedVolume)+ parseFloat(dataItem.confirmedVolume);
                confirmedWeight = parseFloat(confirmedWeight) + parseFloat(dataItem.confirmedWeight);
                confirmedItemQuantity = parseInt(confirmedItemQuantity) + parseInt(dataItem.confirmedItemQuantity);
                confirmedPackageQuantity = parseInt(confirmedPackageQuantity) + parseInt(dataItem.confirmedPackageQuantity);
                clientOrderNumber=clientOrderNumber+","+dataItem.clientOrderNumber;
                receiptPageNumber++;
            }
            self.model.receiptPageNumber(receiptPageNumber);
            self.model.clientOrderNumber(clientOrderNumber);

            self.model.confirmedPackageQuantity(confirmedPackageQuantity);
            self.model.confirmedVolume(confirmedVolume.toFixed(6));
            self.model.confirmedWeight(confirmedWeight.toFixed(2));
            self.model.confirmedItemQuantity(confirmedItemQuantity);
        }
        self.remove=function(e){
            self.model.receiptPageNumber(self.model.receiptPageNumber()-1);
            self.model.confirmedItemQuantity(self.model.confirmedItemQuantity()- e.model.confirmedItemQuantity);
            self.model.confirmedPackageQuantity(self.model.confirmedPackageQuantity()-e.model.confirmedPackageQuantity);
            self.model.confirmedVolume((self.model.confirmedVolume()-e.model.confirmedVolume).toFixed(6));
            self.model.confirmedWeight((self.model.confirmedWeight()-e.model.confirmedWeight).toFixed(2));

            var clientOrderNumber = self.model.clientOrderNumber()+",";
            clientOrderNumber = clientOrderNumber.replace(e.model.clientOrderNumber+",","");
            self.model.clientOrderNumber(clientOrderNumber.substr(0,clientOrderNumber.length-1));
        }
        self.confirmAdd=function(){
            if(self.addDataSource.data().length<1){
                notify("请选择被合订单！","error");
                return;
            }

            // 检查表单是否通过验证
            if (!$("#detail-form").valid()) return;
            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!this.model.confirmedWeight()||this.model.confirmedWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==2) {//数量
                if (!this.model.confirmedItemQuantity()||this.model.confirmedItemQuantity()<=0) {
                    notify("按件数计费，数量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==3) {//体积
                if (!this.model.confirmedVolume()||this.model.confirmedVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return;
                }
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
                url: "/otdTransportOrder/mergeAdd",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("新增被合单操作成功！", "success");
                    router.navigate("#/otdTransportOrderView");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.confirmAdd1=function(){
            if(self.addDataSource.data().length<1){
                notify("请选择被合订单！","error");
                return;
            }

            // 检查表单是否通过验证
            if (!$("#detail-form").valid()) return;
            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!this.model.confirmedWeight()||this.model.confirmedWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==2) {//数量
                if (!this.model.confirmedItemQuantity()||this.model.confirmedItemQuantity()<=0) {
                    notify("按件数计费，数量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==3) {//体积
                if (!this.model.confirmedVolume()||this.model.confirmedVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return;
                }
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
                url: "/otdTransportOrder/mergeAdd",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("新增被合单操作成功！", "success");
                    var orderReceivableId=result.content;
                    var url="#/feeOrderReceivable/modify/"+orderReceivableId;
                    router.navigate(url);
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.repealMerge=function(){
            var message="确定撤销该合单吗？";
            if (!confirm(message)) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/otdTransportOrder/repealMerge",
                data: ko.toJSON("${otdTransportOrder.transportOrderId}")
            }).done(function (result) {
                if (result.success === true) {
                    notify("撤销合单成功", "success");
                    router.navigate("#/otdTransportOrderView");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
        self.addGrid = ko.observable();
        self.addDataSource = new kendo.data.DataSource({data:[]});
        self.addColumns = [  {
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

        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({data:${otdTransportOrderDetailsJson}});
        self.detailColumns = [ {
            title: "序号",
            template: function (dataItem) {
                return self.detailsDataSource.indexOf(dataItem)+1;
            },
            width: 40
        },{
            field: "clientOrderNumber",
            title: "客户单号",
            template: "<a href='\\#/otdTransportOrder/detail/#= transportOrderId #' >#= clientOrderNumber# </a>"
        },{
            field: "goodsCode",
            title: "货物编码",
        }, {
            field: "goodsName",
            title: "货物名称",
        }, {
            field: "goodsType",
            title: "货物型号"
        }, {
            field: "totalItemQuantity",
            title: "数量",
        }, {
            field: "totalPackageQuantity",
            title: "件数",
        }, {
            field: "totalVolume",
            title: "体积",
        }, {
            field: "totalWeight",
            title: "重量"
        }, {
            field: "remark",
            title: "备注"
        }];
    }
    $(function () {
        $("#detail-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("detail-form"));
    });
</script>