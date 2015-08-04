<#import "/utils/readOnlyDropdown.ftl" as utils >
<#import "/utils/form.ftl" as form >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/feeOrderPayable"><i class="fa fa-users"></i> 应收费用</a></li>
    <li><a href="#/feeOrderPayable/modify/${feeOrderPayableView.orderPayableId}"><i class="fa fa-plus"></i> 修改应付费用</a>
    </li>
</ol>

<div>
    <form id="modify-form" class="form-horizontal">
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
            <div>
                <button type="button" data-bind="kendoButton:showHistory">
                    <i class="fa fa-list"></i> 查看确认记录
                </button>
            </div>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.detailsGrid, data: $root.detailsDataSource, pageable:false,sortable: true, editable:{ confirmation:false}, columns: $root.detailColumns, save:$root.onChange }"></div>
            </div>
            <h4 style="text-align: center">总金额：<span data-bind="text:$root.model.totalAmount"></span></h4>
        </div>
        <div class="clear">
            <h3>分摊明细</h3>

            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.proportionDetailsGrid, data: $root.proportionDetailsDataSource,pageable:false, sortable: true, editable:{ confirmation:false}, columns: $root.proportionDetailsColumns ,save:$root.proportionChange}"></div>
            </div>
            <h4 style="text-align: right">未分摊金额：<span data-bind="text:leftAmount"></span></h4>
        </div>

        <div class="clear">
            <h3>操作日志</h3>
            <div id="details-grid-wrap">
                <div data-bind="kendoGrid: {widget: $root.logsGrid, data: $root.logsDataSource, sortable: true, editable:false,pageable:false,  columns: $root.logColumns, toolbar:$root.logToolbar }"></div>
            </div>
        </div>

        <div style="margin-top: 20px" >
            <button type="button" data-bind="kendoButton:$root.save1,enable:canOk">
                <i class="fa fa-save"></i>保存
            </button>
            <button type="button" class="k-button k-button-icontext " data-bind="kendoButton:$root.save,enable:canOk">
                <i class="fa fa-save"></i> 确认
            </button>
            <button type="button" data-bind="kendoButton:$root.back1">
                <i class="fa fa-close"></i>返回至托运单
            </button>
            <button type="button" data-bind="kendoButton:$root.back2">
                <i class="fa fa-close"></i>返回至应付
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
        self.transportTypes = commonData.transportType;
        self.wrapTypes = commonData.wrapType;
        self.leftAmount =ko.observable(0);
        self.status = commonData.feeAuditStatus;
        self.carrierIds = kendo.utils.createListDataSource("carrierId", "/scmCarrier/getDataSource");
        self.citys = kendo.utils.createListDataSource("regionId", "/baseRegion/getCityList");
        self.carrierOrders = kendo.utils.createListDataSource("carrierOrderId", "/otdCarrierOrder/getDataSource");
        self.canOk = ko.observable("${feeOrderPayableView.status}"==1);
        self.changeCanOk=function(){
            return self.canOk(false);
        }
        //费用
        self.detailsGrid = ko.observable();
        self.detailsDataSource = new kendo.data.DataSource({
            data: ${feeOrderPayableDetailsJson},
            schema: {
                  model: {
                        fields: {
                            exacctName: {type: "string", editable: false},
                            amount: {type: "number", editable: true,validation: {required: {message: "金额为必填项"},min:0}}/*,
                            minimumCondiction: {type: "number", editable: false},
                            unitPrice: {type: "number", editable: false}*/
                        }
                  }
            }
        });

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

        self.detailColumns = [{
            field: "exacctName",
            title: "费用科目"
        },/*{
            field: "minimumCondiction",
            title: "区间",
            template: function (dataItem) {
                var min = dataItem.minimumCondiction ? dataItem.minimumCondiction : "<i class='fa fa-ban'></i>";
                var max = dataItem.maxmumCondiction ? dataItem.maxmumCondiction : "<i class='fa fa-ban'></i>";
                return min + " <i class='fa fa-arrows-h'></i> " + max;
            },
            width: 120
        },{
            field: "unitPrice",
            title: "单价"
        }, */{
            field: "amount",
            title: "金额"
        },{
            field: "remark",
            title: "备注"
        }];
        //订单分摊明细
        self.proportionDetailsGrid = ko.observable();
        self.proportionDetailsDataSource = new kendo.data.DataSource({
            data: ${feePayableProportionDetailsJson},
            schema: {
                model: {
                    fields: {
                        clientOrderNumber: {type: "string", editable: false},
                        lnetOrderNumber: {type: "string", editable: false},
                        confirmedVolume : {type: "string", editable: false},
                        confirmedWeight: {type: "string", editable: false},
                        confirmedItemQuantity: {type: "string", editable: false},
                        confirmedPackageQuantity: {type: "string", editable: false},
                        amount: {type: "number", editable: true,validation: {min:0}}
                    }
                }
            }
        });
        self.proportionDetailsColumns = [{
            field: "clientName",
            title: "客户"
        },{
            field: "clientOrderNumber",
            title: "客户单号"
        }, {
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
        self.showHistory = function(){
            var url ="feeOrderPayHistoryView/index/${feeOrderPayableView.orderPayableId}";
            modal("应付费用确认记录", url, {width:1000, height:500});
        }

        self.onChange = function (e) {
            if (e.values.amount!=undefined&&e.values.amount != e.model.amount) {
                var totalAmount = 0;
                e.model.amount = e.values.amount? e.values.amount:0;
                var data = self.detailsDataSource.data();
                for (var i = 0; i < data.length; i++) {
                    totalAmount += data[i].amount;
                }
                self.model.totalAmount(formatFloat(totalAmount,2));
                var proportionData = self.proportionDetailsDataSource.data();
                var gridTotal=0;
                for (var i = 0; i < proportionData.length; i++) {
                    var amount =formatFloat(totalAmount* proportionData[i].scale,2);
                    self.proportionDetailsDataSource.data()[i].amount=amount;
                    gridTotal= parseFloat(gridTotal)+proportionData[i].amount;
                }
                var leftAmount = formatFloat(self.model.totalAmount() - gridTotal,2);
                self.leftAmount(leftAmount);

                self.proportionDetailsGrid().refresh();
                e.container.parent().next().children().eq(1).trigger("click");
            }
        };

        self.proportionChange = function (e) {
            if (e.values.amount!=undefined &&e.values.amount != e.model.amount) {
                e.model.amount = e.values.amount||0;
                var proportionData = self.proportionDetailsDataSource.data();
                var gridTotal = 0;
                for (var i = 0; i < proportionData.length; i++) {
                    gridTotal= parseFloat(gridTotal)+proportionData[i].amount;
                }
                var leftAmount = formatFloat(self.model.totalAmount() - gridTotal,2);
                self.leftAmount(leftAmount);
                e.container.parent().next().children().eq(6).trigger("click");
            }
        }

        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            if (!confirm("确定确认吗？")) return;
            var proportionData = self.proportionDetailsDataSource.data();
            var proportionTotalAmount = 0;
            for (var i = 0; i < proportionData.length; i++) {
                proportionTotalAmount += proportionData[i].amount;
            }
            if (formatFloat(self.model.totalAmount(),0) != formatFloat(proportionTotalAmount,0)) {//不必精确到分，，，，，难以准确
                notify("总金额未完全分摊，请重新核对", "error");
                return;
            }
            self.model.details = self.detailsDataSource.data();
            self.model.proportionDetails = self.proportionDetailsDataSource.data();
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderPayable/confirm",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("确认成功！", "success");
                    self.changeCanOk();
                }
                else {
                    notify(result.message, "error");
                }
            });
        }
        self.save1 = function () {
            // 检查表单是否通过验证
            if (!$("#modify-form").validate().valid()) return;
            var proportionData = self.proportionDetailsDataSource.data();
            var proportionTotalAmount = 0;
            for (var i = 0; i < proportionData.length; i++) {
                proportionTotalAmount += proportionData[i].amount;
            }
            if (formatFloat(self.model.totalAmount(),0) !=formatFloat(proportionTotalAmount,0)) {
                notify("总金额未完全分摊，请重新核对", "error");
                return;
            }
            self.model.details = self.detailsDataSource.data();
            self.model.proportionDetails = self.proportionDetailsDataSource.data();
            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feeOrderPayable/updatePayable",
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
            router.navigate("#/otdCarrierOrder");
        }
        self.back2= function () {
            router.navigate("#/feeOrderPayable");
        }

    }
    $(function () {
        $("#modify-form").validate();
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("modify-form"));
    });
</script>