<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/dispatchAssign"><i class="fa fa-users"></i> 派车单</a></li>
    <li><a href="#/dispatchAssign/modify/${dispatchAssign.dispatchAssignId}"><i class="fa fa-plus"></i> 发运派车单</a></li>
</ol>

<div>

    <form id="modify-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div class="col-6" data-bind="with:model">
        <@form.koText field="dispatchAssignNumber" label="调派单号" required="required" readonly="true" placeholder="自动生成"/>
            <@form.koText field="vehicleCode" label="车牌号码" required="required" />
            <@form.koDropDown field="vehicleType" data="$root.vehicleTypes" label="车辆类型" />
            <@form.koText field="dirver" label="司机"  />
            <@form.koText field="dirverPhone" label="司机电话"  />
            <@form.koNumberBox field="totalFee" label="费用" required="required"  />
            <@form.koDateTimePicker field="expectFinishTime" label="预计完成时间" required="required"  />
        </div>
        <div class="col-6" data-bind="with:model">
            <@form.koDropDown field="dispatchType" data="$root.orderDispatchTypes" label="调度类型" readonly="true"  required="required" />
            <@form.koIntegerBox field="totalItemQuantity" label="总数量" readonly="true" placeholder="自动生成"/>
            <@form.koIntegerBox field="totalPackageQuantity" label="总件数"  readonly="true" placeholder="自动生成"/>
            <@form.koNumberBox field="totalVolume" label="总体积" readonly="true" placeholder="自动生成"/>
            <@form.koNumberBox field="totalWeight" label="总重量"  readonly="true" placeholder="自动生成"/>
            <@form.koTextarea field="startAddress" label="起始地址" required="required" />
            <@form.koTextarea field="destAddress" label="目的地址" required="required"  />
            <@form.koTextarea field="remark" label="备注"  />
        </div>

        <div>
            <h3>派车单明细</h3>
            <div >
                <button type="button" data-bind="kendoButton:add">
                    <i class="fa fa-plus"></i> 选择提货单
                </button>
            </div>

            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.detailsGrid, data:  $root.detailsDataSource,sortable: false, editable:{destroy: true,update: false},
                columns: $root.detailColumns,dataBound:$root.calculate ,remove:$root.gridRemove }"></div>
            </div>
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 保存
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
        self.model = ko.mapping.fromJS(${dispatchAssignJson});
        self.vehicleTypes = commonData.vehicleType;
        self.orderDispatchTypes = commonData.orderDispatchType;
        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({data:[]});
        if('${pickupOrders}'){
            self.detailsDataSource.data(${pickupOrders});
        }
        self.detailColumns = [{
            title: "序号",
            template: function (dataItem) {
                return self.detailsDataSource.indexOf(dataItem)+1;
            },
            width: 30
        },{
            field: "clientName",
            title: "客户",
            width: 80
        }, {
            field: "pickupOrderNumber",
            title: "提货单号",
            width: 80
        }, {
            field: "reservationTime",
            title: "预约时间",
            width: 80
        }, {
            field: "confirmedTotalItemQty",
            title: "总数量",
            width: 60
        }, {
            field: "confirmedTotalPackageQty",
            title: "总件数",
            width: 60
        }, {
            field: "confirmedTotalVolume",
            title: "总体积(立方)",
            width: 60
        }, {
            field: "confirmedTotalWeight",
            title: "总重量(千克)",
            width: 60
        }, {
            field: "city",
            title: "提货城市",
            width: 60
        },{
            field: "address",
            title: "提货地址",
            width: 200
        }, {command: ["destroy"], width: 60}];
        self.gridRemove = function(e){
            var totalPackageQuantity=self.model.totalPackageQuantity();
            var totalVolume =  self.model.totalVolume();
            var totalWeight = self.model.totalWeight();
            var totalItemQuantity = self.model.totalItemQuantity();
            self.model.totalPackageQuantity(totalPackageQuantity- e.model.confirmedPackageQuantity);
            self.model.totalVolume(totalVolume- e.model.confirmedVolume);
            self.model.totalWeight(totalWeight- e.model.confirmedWeight);
            self.model.totalItemQuantity(totalItemQuantity- e.model.confirmedItemQuantity);
        }
        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            // 发送AJAX请求保存数据
            var details =[];
            for (var i = 0; i < self.detailsDataSource.data().length; i++) {
                var dataItem= self.detailsDataSource.data()[i];
                var detail = {orderId:dataItem.pickupOrderId}
                details.push(detail);
            }
            self.model.details =details;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/dispatchAssign/pickupSend",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("确认成功！", "success");
                    router.navigate("/dispatchAssign");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
        self.add =function(){
            var url = "otdPickupOrder/search";
            modal("选择提货单", url, {width:1000, height: 600, close:function(e){
                var currentData = self.detailsDataSource.data();
                if (e.sender.sender) {
                    if(currentData.length>0){
                        for(var i=0;i<e.sender.sender.length;i++){
                            var flag = true;
                            for(var j = 0;j<currentData.length;j++){
                                if(currentData[j].pickupOrderId==e.sender.sender[i].pickupOrderId){
                                    flag = false;
                                }
                            }
                            if(flag){
                                self.detailsDataSource.data().push(ko.toJS(e.sender.sender[i]));
                            }
                        }
                    }else{
                        for(var i=0;i<e.sender.sender.length;i++) {
                            self.detailsDataSource.data().push(ko.toJS(e.sender.sender[i]));
                        }
                    }
                }
                self.calculate();
            }});
        };
        self.calculate = function (){
            var totalVolume = 0;
            var totalPackageQuantity = 0;
            var totalWeight = 0;
            var totalItemQuantity = 0;
            for (var i = 0; i < self.detailsDataSource.data().length; i++) {
                var dataItem= self.detailsDataSource.data()[i];
                totalVolume = parseFloat(totalVolume) +parseFloat(dataItem.confirmedVolume);
                totalWeight =parseFloat(totalWeight) + parseFloat(dataItem.confirmedWeight);
                totalItemQuantity =parseInt(totalItemQuantity) + parseInt(dataItem.confirmedItemQuantity);
                totalPackageQuantity =parseInt(totalPackageQuantity) + parseInt(dataItem.confirmedPackageQuantity);
            }
            self.model.totalPackageQuantity(totalPackageQuantity);
            self.model.totalVolume(totalVolume);
            self.model.totalWeight(totalWeight);
            self.model.totalItemQuantity(totalItemQuantity);
        };
    }
    $(function () {
        $("#modify-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });
</script>