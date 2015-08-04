<#import "/utils/dropdown.ftl" as utils >
<div id="grid-client-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="startCity">起始城市</label>
            <input type="text" class="k-input k-textbox"  name="startCity" id="startCity" data-bind="value:startCity" >
            <label for="destCity">目的城市</label>
            <input type="text" class="k-input k-textbox"  name="destCity" id="destCity" data-bind="value:destCity" >
            <label for="transportType">运输方式</label><@utils.koDropDown data="$root.transportTypeList" value="transportType"/>
            <label for="calculateType">计价方式</label><@utils.koDropDown data="$root.calculateTypeList" value="calculateType"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:$root.search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:$root.clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:$root.create">
            <i class="fa fa-plus"></i> 新增
            </button>
            <button type="button" data-bind="kendoButton:$root.copy,enable:$root.canModify">
                <i class="fa fa-copy"></i> 复制
            </button>
            <button type="button" data-bind="kendoButton:$root.remove,enable:$root.canRemove">
                <i class="fa fa-remove"></i> 批量删除
            </button>
            <button type="button" data-bind="kendoButton:$root.modify,enable:$root.canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: $root.dataSource, widget: $root.widget, columns: $root.columns,sortable: true, toolbar:['excel','pdf'],groupable: true, editable:false, pageable: $root.pageable, selectable: 'multiple,row', change:$root.onChange}"></div>
</div>


<script>
    var quoteGridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("clientQuoteId", "/crmClientQuoteView/dataSource?clientId=${clientId}");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};


        self.clearFilter = function () {
            self.transportType("");
            self.calculateType("");
            self.startCity("");
            self.destCity("");
            self.dataSource.filter([]);
        };

        self.columns = [{
            title: "序号",
            template: function (dataItem) {
                return self.dataSource.indexOf(dataItem)+1;
            },
            width: 40
        },{
            title: "线路",
            width:120,
            template: function (dataItem) {
                return dataItem.startCity + " <i class='fa fa-long-arrow-right'></i> " + dataItem.destCity;
            },
        },{
            field: "transportTypeName",
            title: "运输方式",
            width:120
        }, {
            field: "calculateTypeName",
            title: "计价方式",
            width:120
        },{
            field: "exacctName",
            title: "费用科目",
            width:120
        }, {
            field: "minimumFee",
            title: "最低收费",
            width:120
        },{
            field: "minimumCondiction",
            title: "区间",
            template: function (dataItem) {
                var min = dataItem.minimumCondiction ? dataItem.minimumCondiction : "0";
                var max = dataItem.maxmumCondiction ? dataItem.maxmumCondiction : "<i class='fa fa-ban'></i>";
                return min + " <i class='fa fa-arrows-h'></i> " + max;
            },
            width: 120
        },{
            field: "unitPrice",
            title: "单价",
            width:120
        },{
            field: "timeline",
            title: "时效",
            width:120
        }];
        self.selected = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selected(dataItem);
        };
        self.canModify = ko.computed(function () {
            return self.selected();
        });

        self.canRemove = ko.computed(function () {
            return self.selected();
        });

        self.create = function () {
            var url = "crmClientQuote/create/${clientId}";
            modal("编辑报价", url, {width:800, height: 600,close:function(){
                self.dataSource.query();
            }});
        };
        self.copy = function(){
            var url = "crmClientQuote/copy/" + self.selected().clientQuoteId;
            modal("编辑报价", url, {width:800, height: 600,close:function(){
                self.dataSource.query();
            }});
        }
        self.modify = function () {
            var url = "crmClientQuote/modify/" + self.selected().clientQuoteId;
            modal("编辑报价", url, {width:800, height: 600,close:function(){
                self.dataSource.query();
            }});
        };
        self.remove = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            for(var i=0;i<rows.length;i++){
                var row=rows[i];
                var dataItem = self.widget().dataItem(row);
                selectedIds.push(dataItem.clientQuoteId);
            }
            if (!confirm("确定删除已选择报价吗？")) return;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/crmClientQuote/batchDeleteById",
                data: ko.toJSON(selectedIds)
            }).done(function (result) {
                if (result.success === true) {
                    notify("删除成功", "success");
                    self.dataSource.query();
                }
                else {
                    notify(result.message, "error");
                }
            });

        };
        self.destCity=ko.observable();
        self.startCity=ko.observable();
        self.calculateType=ko.observable("");
        self.transportType=ko.observable("");
        self.transportTypeList=commonData.transportType;
        self.calculateTypeList=commonData.calculateType;
        self.search = function () {
            var destCity = $.trim(self.destCity());
            var startCity = $.trim(self.startCity());
            var calculateType = $.trim(self.calculateType());
            var transportType = $.trim(self.transportType());
            var filter = new Array();
            if (destCity){
                filter.push({
                    logic: "and",
                    field: "destCity",
                    operator: "contains",
                    value: destCity
                });
            }if (startCity){
                filter.push({
                    logic: "and",
                    field: "startCity",
                    operator: "contains",
                    value: startCity
                });
            }if (calculateType){
                filter.push({
                    logic: "and",
                    field: "calculateType",
                    operator: "eq",
                    value: calculateType
                });
            }if (transportType){
                filter.push({
                    logic: "and",
                    field: "transportType",
                    operator: "eq",
                    value: transportType
                });
            }
            self.dataSource.filter({
                logic: "and",
                filters: filter
            });
        }
    };

    $(function () {
        var quoteViewModel = new quoteGridModel();
        ko.applyBindings(quoteViewModel, document.getElementById("grid-client-wrap"));
    });
</script>