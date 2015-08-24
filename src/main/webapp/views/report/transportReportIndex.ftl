<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/report/transportReportIndex"><i class="fa fa-users"></i> 动态运作报表</a></li>
</ol>
<div id="grid-wrap-transportReport">
    <div class="grid-toolbar">
<div>
    <label for="clientId">项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</label><@utils.koComboBox data="$root.clients" value="clientId" textField="name" valueField="clientId"/>
    <label for="keyword">&nbsp;&nbsp;&nbsp;客户订单号</label><input class="k-textbox" data-bind="value:clientOrderNo">
</div>
<div>
    <label for="orderBeginDate">订单开始日期</label><input data-bind="kendoDatePicker: orderBeginDate"/>
    <label for="orderEndDate">订单结束日期</label><input data-bind="kendoDatePicker: orderEndDate"/>
</div>
<div>
    <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 生成报表</button>
    <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
</div>
    </div>
</div>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            var today=new Date();
            self.widget = ko.observable();
            self.clientOrderNo=ko.observable();
            self.clientId=ko.observable();
            self.orderBeginDate=ko.observable();
            self.orderEndDate=ko.observable();
            self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.clientOrderNo("");
                self.clientId("");
                self.orderBeginDate("");
                self.orderEndDate("");
            };
            self.orderBeginDate = ko.observable(new Date(
                    today.getFullYear(),
                    today.getMonth(),
                    1
            ));
            self.orderEndDate = ko.observable(today);
            self.search = function () {
                var clientOrderNo = $.trim(self.clientOrderNo());
                var beginDate = $.trim(self.orderBeginDate());
                var endDate = $.trim(self.orderEndDate());
                var clientId = $.trim(self.clientId());
                var filters = new Array();
                if (clientOrderNo) {
                    filters.push({field: "clientOrderNumber", operator: "contains", value: clientOrderNo});
                }
                if (clientId) {
                    filters.push({field: "clientId", operator: "eq", value: clientId});
                }
                if (beginDate) {
                    var begin=new Date(beginDate);
                    filters.push({field: "beginDate", operator: "gte", value: begin});
                }
                if (endDate) {
                    var end = new Date(endDate);
                    filters.push({field: "endDate", operator: "lte",
                        value: new Date(end.setDate(end.getDate()+1))});
                }
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/report/getFilters",
                    data: ko.toJSON(filters),
                    success:function(){
                        var url = "/report/getPdfReport";
                        window.open(url);
                    }
                });
            };
        };
        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap-transportReport"));
    });