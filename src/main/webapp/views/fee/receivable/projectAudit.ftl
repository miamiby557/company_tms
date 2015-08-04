<#import "/utils/readOnlyDropdown.ftl" as utils >
<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/feeOrderReceivableView/projectAudit"><i class="fa fa-users"></i> 应收客服经理审核</a></li>
    <li><a href="#/feeOrderReceivable/projectAudit/${orderReceivable.orderReceivableId}"><i class="fa fa-plus"></i> 应收客服经理审核详情</a>
    </li>
</ol>

<div>
    <form id="modify-form" method="post"  class="form-horizontal">

        <div data-bind="with:model" class="col-6">
        <@form.koStatusPanel data="$root.status" field="status" label="审核状态："/>
            <div class="form-group">
                <label for="clientId">客户</label>
            <@utils.koComboBox data="$root.clientIds" value="clientId" textField="name" valueField="clientId" />
            </div>
            <div class="form-group">
                <label for="clientOrderNumber">客户订单号</label>
                <textarea type="text" class="k-input k-textbox" name="clientOrderNumber" id="clientOrderNumber" data-bind="value:clientOrderNumber" readonly="readonly" rows="5"></textarea>
            </div>
            <div class="form-group">
                <label for="sourceOrderType">来源订单类型</label>
                <@utils.koDropDown data="$root.sourceOrderTypeList" value="sourceOrderType"/>
            </div>
            <div class="form-group">
                <label for="billingCycle">结算周期</label>
            <@utils.koDropDown data="$root.settleCycleList" value="billingCycle"/>
            </div>
            <div class="form-group">
                <label for="paymentType">支付方式</label>
            <@utils.koDropDown data="$root.paymentTypeList" value="paymentType"/>
            </div>
            <div class="form-group">
                <label for="calculateType">计费方式</label>
                <@utils.koDropDown data="$root.calculateTypeList" value="calculateType"/>
            </div>
            <div class="form-group">
                <label for="totalAmount">总金额</label>
                <input type="number" class="k-input k-textbox" required="required" name="totalAmount" id="totalAmount" data-bind="value:totalAmount" disabled="disabled">
            </div>
        </div>

        <div data-bind="with:model1" class="col-6">
            <div class="form-group">
                <label for="transportType">运输方式</label>
            <@utils.koDropDown data="$root.transportTypeList" value="transportType"/>
            </div>
            <div class="form-group">
                <label for="wrapType">包装类型</label>
            <@utils.koDropDown data="$root.wrapTypeList" value="wrapType"/>
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
                <label for="confirmedItemQuantity" >实际数量</label>
                <input type="number" name="confirmedItemQuantity"data-bind="value:confirmedItemQuantity"  id="confirmedItemQuantity" class="k-input k-textbox" min="1"  readonly="readonly">
            </div>
            <div class="form-group">
                <label for="confirmedPackageQuantity" >应收总件数</label>
                <input type="number" name="confirmedPackageQuantity" data-bind="value:confirmedPackageQuantity"  id="confirmedPackageQuantity" class="k-input k-textbox" min="1" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="confirmedVolume" >应收体积（立方）</label>
                <input type="number" name="confirmedVolume" id="confirmedVolume" data-bind="value:confirmedVolume"  class="k-input k-textbox" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="confirmedWeight" >应收重量（公斤）</label>
                <input type="number" name="confirmedWeight" id="confirmedWeight" data-bind="value:confirmedWeight"  class="k-input k-textbox"  readonly="readonly">
            </div>
        </div>

        <div class="clear">
            <h3>费用明细</h3>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.detailsGrid, data: $root.detailsDataSource, sortable: true, editable:false,pageable:false, columns: $root.detailColumns, toolbar:$root.detailToolbar}"></div>
            </div>
        </div>

        <div class="clear">
            <h3>操作日志</h3>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.logsGrid, data: $root.logsDataSource, sortable: true, pageable:false,editable:false, columns: $root.logColumns, toolbar:$root.logToolbar }"></div>
            </div>
        </div>

        <div style="margin-top: 20px">
            <button type="button" data-bind="kendoButton:pass,enable:canPass">
                <i class="fa fa-save"></i> 通过
            </button>
            <button type="button" data-bind="kendoButton:back,enable:canBack">
                <i class="fa fa-save"></i> 拒绝
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
        self.model = ko.mapping.fromJS(${orderReceivableJson});
        self.model1=ko.mapping.fromJS(${otdTransportOrder});
        self.sourceOrderTypeList = commonData.feeSourceType;
        self.calculateTypeList = commonData.calculateType;
        self.settleCycleList = commonData.settleCycle;
        self.paymentTypeList = commonData.paymentType;
        self.transportTypeList=commonData.transportType;
        self.wrapTypeList=commonData.wrapType;

        self.clientIds =  kendo.utils.createListDataSource("clientId","/crmClient/getDataSource");

        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({data:${FeeReceivableDetailsJson}});
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

        self.canPass = ko.computed(function () {
            return self.model.status() && self.model.status() == 2;
        });

        self.canBack = ko.computed(function () {
            return self.model.status() && self.model.status() == 2;
        });

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

        self.pass=function(){
            var orderReceivableIds=[];
            var orderReceivableId="${orderReceivable.orderReceivableId}";
            orderReceivableIds.push(orderReceivableId);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderReceivable/projectPass",
                data: ko.toJSON(orderReceivableIds)
            }).done(function (result) {
                if (result.success === true) {
                    notify("审核成功！", "success");
                    router.navigate("/feeOrderReceivableView/projectAudit");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }

        self.back=function(){
            var url = "feeOrderReceivableLog/projectCreate/${orderReceivable.orderReceivableId}";
            modal("审核拒绝", url, {width:400, height: 100, close:function(){
                router.navigate("/feeOrderReceivableView/projectAudit");
            }});

            /*var orderReceivableIds=[];
            var orderReceivableId="${orderReceivable.orderReceivableId}";
            orderReceivableIds.push(orderReceivableId);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderReceivable/back",
                data: ko.toJSON(orderReceivableIds)
            }).done(function (result) {
                if (result.success === true) {
                    notify("操作成功！", "success");
                    router.navigate("/feeOrderReceivableView/projectAudit");
                }
                else {
                    notify(result.message, "error");
                }
            });*/
        }
    }
    $(function () {
        $("#modify-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });
</script>