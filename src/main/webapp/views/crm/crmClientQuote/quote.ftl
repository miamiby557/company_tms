<#import "/utils/dropdown.ftl" as utils >
<div id="grid-quote-wrap">
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
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, editable:false, pageable: pageable, selectable: 'row', change:onChange}"></div>
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
            field: "startCity",
            title: "始发城市",
            width:120
        }, {
            field: "destCity",
            title: "目的城市",
            width:120
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
            title: "最小区间",
            width: 120
        }, {
            field: "maxmumCondiction",
            title: "最大区间",
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
        ko.applyBindings(quoteViewModel, document.getElementById("grid-quote-wrap"));
    });
</script>