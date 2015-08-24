<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/report"><i class="fa fa-users"></i> 动态报表测试</a></li>
</ol>
<div id="grid-wrap-test">
    <div class="grid-toolbar">
<div>
    <label for="clientOrderNo">客户单号</label><input id="clientOrderNo" class="k-textbox" data-bind="value:clientOrderNo">
    <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
</div>
        </div>
</div>
<body>
<h1>Spring MVC Hello World Example</h1>
<h2>by gloomyfish</h2>
<h2>you can get PDF report from here:</h2>
<p><a href="/report/pdfReport" target="_blank">Get PDF Report</a></p>
<p><a onclick="test();" target="_blank">PDF Report</a></p>
</body>

<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.clientOrderNo=ko.observable();
            //self.dataSource = kendo.utils.createDataSource("clientOrderNumber","/report/getpdfReport");
            self.dataSource=ko.observable();
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.search = function () {
                var clientOrderNo = $.trim(self.clientOrderNo());
                var filters = new Array();
                if (clientOrderNo) {
                    filters.push({field: "clientOrderNumber", operator: "contains", value: clientOrderNo});
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
        ko.applyBindings(viewModel, document.getElementById("grid-wrap-test"));
    });
    function test(){
        //var url = "/report/pdfReport/"+$("#clientOrderNo").val();
        var url = "/report/pdfReport";
        alert(url);
        window.open(url);
    }
</script>