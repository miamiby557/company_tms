<#import "/utils/dropdown.ftl" as utils >
<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/feeOrderReceivableView"><i class="fa fa-users"></i> 应收审核</a></li>
    <li><a href="#/feeOrderReceivable/modify/${orderReceivable.orderReceivableId}"><i class="fa fa-plus"></i> 修改应收费用</a>
    </li>
</ol>

<div>
    <form id="receivable-confirm-form" class="form-horizontal">

        <div data-bind="with:model" class="col-6">
        <@form.koStatusPanel data="$root.status" field="status" label="审核状态："/>
            <div class="form-group">
                <label for="clientId">客户</label>
            <@utils.koComboBox data="$root.clientIds" value="clientId" textField="name" valueField="clientId" readonly="readonly"/>
            </div>
            <div class="form-group">
                <label for="clientOrderNumber">客户订单号</label>
                <textarea type="text" class="k-input k-textbox" name="clientOrderNumber" id="clientOrderNumber" data-bind="value:clientOrderNumber" readonly="readonly" rows="5"></textarea>
            </div>
            <div class="form-group">
                <label for="sourceOrderType">来源订单类型</label>
                <@utils.koDropDown data="$root.sourceOrderTypeList" value="sourceOrderType" readonly="readonly"/>
            </div>
            <div class="form-group">
                <label for="billingCycle">结算周期</label>
            <@utils.koDropDown data="$root.settleCycleList" value="billingCycle" readonly="readonly"/>
            </div>
            <div class="form-group">
                <label for="paymentType">支付方式</label>
            <@utils.koDropDown data="$root.paymentTypeList" value="paymentType" readonly="readonly"/>
            </div>
            <div class="form-group">
                <label for="totalAmount">总金额</label>
                <input type="number" class="k-input k-textbox" required="required" name="totalAmount" id="totalAmount" data-bind="value:totalAmount" disabled="disabled">
            </div>
        </div>

        <div data-bind="with:model1" class="col-6">
            <div class="form-group">
                <label for="wrapType">包装类型</label>
            <@utils.koDropDown data="$root.wrapTypeList" value="wrapType" readonly="readonly"/>
            </div>
            <div class="form-group">
                <label for="startCity">始发城市</label>
                <input type="text" class="k-input k-textbox"  name="startCity" id="startCity" data-bind="value:startCity" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="destCity">目的城市</label>
                <input type="text" class="k-input k-textbox"  name="destCity" id="destCity" data-bind="value:destCity" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="transportType">运输方式</label>
            <@utils.koDropDown data="$root.transportTypeList" value="transportType"/>
            </div>
            <div class="form-group">
                <label for="calculateType">计费方式</label>
            <@utils.koDropDown data="$root.calculateTypeList" value="calculateType"/>
            </div>
            <div class="form-group">
                <label for="confirmedItemQuantity" >实际数量</label>
                <input type="number" name="confirmedItemQuantity"data-bind="value:confirmedItemQuantity"  id="confirmedItemQuantity" class="k-input k-textbox" min="1" required="required"  pattern="\d+">
            </div>
            <div class="form-group">
                <label for="confirmedPackageQuantity" >应收总件数</label>
                <input type="number" name="confirmedPackageQuantity" data-bind="value:confirmedPackageQuantity"  id="confirmedPackageQuantity" class="k-input k-textbox" min="1" required="required"  pattern="\d+">
            </div>
            <div class="form-group">
                <label for="confirmedVolume" >应收体积（立方）</label>
                <input type="number" name="confirmedVolume" id="confirmedVolume" data-bind="value:confirmedVolume" pattern="\d+(\.\d{0,6})?" class="k-input k-textbox"  min="0" required="required">
            </div>
            <div class="form-group">
                <label for="confirmedWeight" >应收重量（公斤）</label>
                <input type="number" name="confirmedWeight" id="confirmedWeight" data-bind="value:confirmedWeight"  class="k-input k-textbox"  min="0" required="required">
            </div>
            <div>
                <button type="button" data-bind="kendoButton:$root.againCalculator,enable:$root.canConfirm">
                    <i class="fa fa-save"></i>重新计算应收费用
                </button>
            </div>
        </div>

        <div class="clear">
            <h3>费用明细</h3>
            <div>
                <button type="button" data-bind="kendoButton:showHistory">
                    <i class="fa fa-list"></i> 查看确认记录
                </button>
            </div>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.detailsGrid, data: $root.detailsDataSource,pageable:false, sortable: true, editable:{ confirmation:false}, columns: $root.detailColumns, toolbar:$root.detailToolbar, save:$root.onChange }"></div>
            </div>
            <h4 style="text-align: center">总金额：<span data-bind="text:$root.model.totalAmount"></span></h4>
        </div>


        <div class="clear">
            <h3>操作日志</h3>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.logsGrid, data: $root.logsDataSource, sortable: true,pageable:false, editable:false, columns: $root.logColumns, toolbar:$root.logToolbar }"></div>
            </div>
        </div>


        <div style="margin-top: 20px">
            <button type="button" data-bind="kendoButton:$root.save1,enable:canConfirm">
                <i class="fa fa-save"></i>保存
            </button>
            <button type="button" class="k-button k-button-icontext k-primary "data-bind="kendoButton:$root.save,enable:canConfirm">
                <i class="fa fa-save"></i> 确认
            </button>
            <button type="button" data-bind="kendoButton:$root.back1">
                <i class="fa fa-close"></i>返回至订单
            </button>
            <button type="button" data-bind="kendoButton:$root.back2">
                <i class="fa fa-close"></i>返回至应收
            </button>
        </div>
    </form>
</div>
<script>
    function CreateModel() {
        var self = this;
        self.model = ko.mapping.fromJS(${orderReceivableJson});
        self.model1=ko.mapping.fromJS(${otdTransportOrder});
        self.sourceOrderTypeList = commonData.feeSourceType;
        self.calculateTypeList = commonData.calculateType;
        self.settleCycleList = commonData.settleCycle;
        self.paymentTypeList = commonData.paymentType;
        self.transportTypeList=commonData.transportType;
        self.wrapTypeList=commonData.wrapType;
        self.status = commonData.feeAuditStatus;
        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");

       /* self.canConfirm = ko.computed(function () {
            return self.model.status() && self.model.status() == 1;
        });*/
        self.canConfirm = ko.observable("${orderReceivable.status}" == 1);
        self.changeCanConfirm=function(){
            return self.canConfirm(false);
        }

        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({
            data: ${FeeReceivableDetailsJson},
            schema: {
                model: {
                    fields: {
                        name: {type: "string", editable: false},
                        amount: {type: "number", editable: true,validation: {required: {message: "金额为必填项"},min:0}},
                        remark: {type: "string", editable: true}
                    }
                }
            }
        });
        self.detailColumns = [{
            field: "name",
            title: "费用科目",
            width:200
        }, {
            field: "amount",
            title: "金额",
            width:200
        }, {
            field: "remark",
            title: "备注",
            width:200
        }];

        self.logsGrid = ko.observable();
        self.logsDataSource = new kendo.data.DataSource({data:${FeeOrderReceivableLogsJson}});
        self.logColumns = [{
            field: "status",
            title: "审核状态",
            width: 100,
            values:ko.toJS(commonData.feeAuditStatus)
        },  {
            field: "fullName",
            title: "操作人",
            width: 100
        },  {
            field: "operationDate",
            format:"{0:yyyy-MM-dd HH:mm:ss}",
            title: "操作时间",
            width: 100
        },{
            field: "remark",
            title: "操作内容",
            width: 400
        }];

        self.showHistory = function(){
            var url ="feeOrderReceiveHistoryView/index/${orderReceivable.orderReceivableId}";
            modal("应收费用确认记录", url, {width:1000, height:500});
        }

        self.againCalculator = function () {
            // 检查表单是否通过验证
            if (!$("#receivable-confirm-form").valid()) return;

            var calculateType = self.model.calculateType();
            if(calculateType==1) {//重量
                if (!self.model1.confirmedWeight()||self.model1.confirmedWeight()<=0) {
                    notify("按重量计费，重量须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==2) {//件数
                if (!self.model1.confirmedPackageQuantity()||self.model1.confirmedPackageQuantity()<=0) {
                    notify("按件数计费，件数须大于 0 ！","error");
                    return;
                }
            }else if(calculateType==3) {//体积
                if (!self.model1.confirmedVolume()||self.model1.confirmedVolume()<=0) {
                    notify("按体积计费，体积须大于 0 ！","error");
                    return;
                }
            }

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderReceivable/againCalculator",
                data: ko.toJSON(self.model1)
            }).done(function (result) {
                if (result.success === true) {
                    notify("重新计算成功！", "success");
                    self.detailsDataSource.data(result.content);
                    var totalAmount=0;
                    var data =self.detailsDataSource.data();
                    for(var i = 0; i<data.length;i++){
                        totalAmount+=data[i].amount;
                    }
                    self.model.totalAmount(totalAmount.toFixed(2));
                }
                else {
                    notify(result.message, "error");
                }
            });
        }


        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#receivable-confirm-form").validate().valid()) return;
            if (!confirm("确定确认吗？")) return;
            self.model.details = self.detailsDataSource.data();
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderReceivable/confirm",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("确认成功！", "success");
                    self.changeCanConfirm();
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.save1 = function () {
            // 检查表单是否通过验证
            if (!$("#receivable-confirm-form").validate().valid()) return;
            self.model.details = self.detailsDataSource.data();
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderReceivable/updateReceivable",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("保存成功！", "success");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.back1= function () {
            router.navigate("#/otdTransportOrderView");
        }
        self.back2= function () {
            router.navigate("#/feeOrderReceivableView");
        }

        self.onChange = function(e){
            if(e.values.amount!=undefined&&e.values.amount!=e.model.amount){
                var totalAmount=0;
                e.model.amount = e.values.amount;
                var data =self.detailsDataSource.data();
                for(var i = 0; i<data.length;i++){
                    totalAmount= parseFloat(totalAmount)+data[i].amount;
                }
                self.model.totalAmount(totalAmount.toFixed(2));
                e.container.parent().next().children().eq(1).trigger("click");
            }
        };
    }
    $(function () {
        $("#receivable-confirm-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("receivable-confirm-form"));
    });
</script>