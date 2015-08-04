<#import "/utils/readOnlyDropdown.ftl" as utils >
<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/feeOrderPayableView/financingAudit"><i class="fa fa-users"></i> 应付财务审核</a></li>
    <li><a href="#/feeOrderPayable/financingAudit/${feeOrderPayableView.orderPayableId}"><i class="fa fa-plus"></i> 应付财务审核详情</a>
    </li>
</ol>

<div>
    <form id="modify-form" method="post"  class="form-horizontal">

        <div data-bind="with:model" class="col-6">
        <@form.koStatusPanel data="$root.status" field="status" label="审核状态："/>
            <div class="form-group">
                <label for="carrierId">承运商名称</label>
            <@utils.koComboBox data="$root.carrierIds" value="carrierId" textField="name" valueField="carrierId" />
            </div>
            <div class="form-group">
                <label for="sourceOrderId">来源订单</label>
            <@utils.koComboBox data="$root.carrierOrders" value="sourceOrderId" textField="carrierOrderNumber" valueField="carrierOrderId" />
            </div>
            <div class="form-group">
                <label for="sourceOrderType">来源订单类型</label>
            <@utils.koDropDown data="$root.feeSourceType" value="sourceOrderType"/>
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
                <input type="number" class="k-input k-textbox" required="required" name="totalAmount" on
                       id="totalAmount" readonly="true"
                       data-bind="value:totalAmount">
            </div>
        </div>
        <div data-bind="with:model" class="col-6">
            <div class="form-group">
                <label for="transportType">运输方式</label>
            <@utils.koDropDown data="$root.transportTypes" value="transportType" />
            </div>
            <div class="form-group">
                <label for="wrapType">包装类型</label>
            <@utils.koDropDown data="$root.wrapTypes" value="wrapType" />
            </div>
            <div class="form-group">
                <label for="startCityId">始发城市</label>
            <@utils.koComboBox data="$root.citys" value="startCityId" textField="name" valueField="regionId" />
            </div>
            <div class="form-group">
                <label for="destCityId">目的城市</label>
            <@utils.koComboBox data="$root.citys" value="destCityId" textField="name" valueField="regionId" />
            </div>
            <div class="form-group">
                <label for="totalVolume">总体积</label>
                <input type="number" class="k-input k-textbox" name="totalVolume" readonly="true" id="totalVolume" data-bind="value:totalVolume" required="required" maxlength="25">
            </div>
            <div class="form-group">
                <label for="totalWeight">总重量</label>
                <input type="number" class="k-input k-textbox" name="totalWeight" readonly="true" id="totalWeight" data-bind="value:totalWeight">
            </div>
            <div class="form-group">
                <label for="totalItemQuantity">总数量</label>
                <input type="number" class="k-input k-textbox" name="totalItemQuantity" readonly="true" id="totalItemQuantity" data-bind="value:totalItemQuantity">
            </div>
            <div class="form-group">
                <label for="totalPackageQuantity">总件数</label>
                <input type="number" class="k-input k-textbox" name="totalPackageQuantity" readonly="true" id="totalPackageQuantity" data-bind="value:totalPackageQuantity">
            </div>
        </div>

        <div class="clear">
            <h3>费用明细</h3>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.detailsGrid, data: $root.detailsDataSource, sortable: true,pageable:false, editable:false, columns: $root.detailColumns, save:$root.onChange }"></div>
            </div>
        </div>
        <div class="clear">
            <h3>分摊明细</h3>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.proportionDetailsGrid, data: $root.proportionDetailsDataSource,pageable:false, sortable: true, editable:false, columns: $root.proportionDetailsColumns }"></div>
            </div>
        </div>

        <div class="clear">
            <h3>操作日志</h3>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.logsGrid, data: $root.logsDataSource, sortable: true, editable:false,pageable:false,  columns: $root.logColumns, toolbar:$root.logToolbar }"></div>
            </div>
        </div>

        <div style="margin-top: 20px">
            <button type="button" data-bind="kendoButton:pass,enable:canOk">
                <i class="fa fa-edit"></i> 通过
            </button>
            <button type="button" data-bind="kendoButton:back,enable:canOk">
                <i class="fa fa-edit"></i> 拒绝
            </button>
            <button type="button" data-bind="kendoButton:finish,enable:canFinish">
                <i class="fa fa-edit"></i> 完结
            </button>
            <button type="button" data-bind="kendoButton:cancelFinish,enable:canFinish">
                <i class="fa fa-edit"></i> 撤销
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
        self.model = ko.mapping.fromJS(${feeOrderPayableJson});
        self.calculateTypeList = commonData.calculateType;
        self.settleCycleList = commonData.settleCycle;
        self.paymentTypeList = commonData.paymentType;
        self.feeSourceType = commonData.feeSourceType;
        self.status = commonData.feeAuditStatus;
        self.carrierIds = kendo.utils.createListDataSource("carrierId", "/scmCarrier/getDataSource");
        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.carrierOrders = kendo.utils.createListDataSource("carrierOrderId", "/otdCarrierOrder/getDataSource");
        self.canOk = ko.computed(function () {
            return self.model.status() && self.model.status() == 3;
        });
        self.canFinish = ko.computed(function () {
            return self.model.status() && self.model.status() == 4;
        });
        //费用
        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({data: ${feeOrderPayableDetailsJson}});

        self.detailColumns = [{
            field: "exacctName",
            title: "费用科目",
            editor:readonly
        }, {
            field: "amount",
            title: "金额"
        },{
            field: "remark",
            title: "备注"
        }];

        self.logsGrid = ko.observable();
        self.logsDataSource = new kendo.data.DataSource({data:${FeeOrderPayableLogsJson}});
        self.logColumns = [{
            field: "statusName",
            title: "审核状态",
            width: 100
        },  {
            field: "operationUserName",
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
        //订单分摊明细
        self.proportionDetailsGrid = ko.observable();
        self.proportionDetailsDataSource = new kendo.data.DataSource({data: ${feePayableProportionDetailsJson}});

        self.proportionDetailsColumns = [{
            field: "clientName",
            title: "客户"
        },{
            field: "clientOrderNumber",
            title: "客户单号"
        },{
            field: "confirmedVolume",
            title: "体积(立方)"
        }, {
            field: "confirmedWeight",
            title: "重量(千克)"
        }, {
            field: "confirmedItemQuantity",
            title: "商品数量"
        }, {
            field: "confirmedPackageQuantity",
            title: "件数"
        },  {
            field: "amount",
            title: "金额"
        },  {
            field: "remark",
            title: "备注"
        }];

        self.pass=function(){
            var orderPayableId="${feeOrderPayableView.orderPayableId}";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderPayable/financingPass",
                data: ko.toJSON(orderPayableId)
            }).done(function (result) {
                if (result.success === true) {
                    notify("操作成功！", "success");
                    router.navigate("/feeOrderPayableView/financingAudit");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }

        self.finish=function(){
            var orderPayableId="${feeOrderPayableView.orderPayableId}";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderPayable/financingFinish",
                data: ko.toJSON(orderPayableId)
            }).done(function (result) {
                if (result.success === true) {
                    notify("操作成功！", "success");
                    router.navigate("/feeOrderPayableView/financingAudit");
                }
                else {
                    notify(result.message, "error");
                }
            });
        }

        self.back=function(){
            var url = "feeOrderPayableLog/financingCreate/${feeOrderPayableView.orderPayableId}";
            modal("拒绝", url, {width:400, height: 100, close:function(){
                router.navigate("/feeOrderPayableView/financingAudit");
            }});

            /*var orderPayableId="${feeOrderPayableView.orderPayableId}";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderPayable/back",
                data: ko.toJSON(orderPayableId)
            }).done(function (result) {
                if (result.success === true) {
                    notify("操作成功！", "success");
                    router.navigate("/feeOrderPayableView/financingAudit");
                }
                else {
                    notify(result.message, "error");
                }
            });*/
        }

        self.cancelFinish=function(){
            var url = "feeOrderPayableLog/cancelFinish/${feeOrderPayableView.orderPayableId}";
            modal("撤销", url, {width:400, height: 100, close:function(){
                router.navigate("/feeOrderPayableView/financingAudit");
            }});
        }
    }
    $(function () {
        $("#modify-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });
</script>