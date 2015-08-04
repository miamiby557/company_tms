<#import "/utils/dropdown.ftl" as utils >
<div id="grid-carrier-quote">
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
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true,
        groupable: true, editable:false, pageable: pageable, selectable: 'row', change:onChange}"></div>
</div>

<script>
    function CarrierQuoteViewModel() {
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createListDataSource("carrierQuoteId","/scmCarrierQuoteView/getDataSource");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        var filters=new Array();
        filters.push({
            field: "carrierId",
            operator: "eq",
            value: "${carrierId}"
        });
        self.dataSource.filter({
            logic: "and",
            filters: filters
        });

        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.clearFilter = function () {
            self.transportType("");
            self.calculateType("");
            self.startCity("");
            self.destCity("");
            filters=new Array();
            filters.push({
                field: "carrierId",
                operator: "eq",
                value: "${carrierId}"
            });
            self.dataSource.filter({
                logic: "and",
                filters: filters
            });
        };

        self.columns = [{
            field: "startCity",
            title: "线路",
            template: function (dataItem) {
                return dataItem.startCity + " <i class='fa fa-long-arrow-right'></i> " + dataItem.destCity;
            },
            width: 120
        }, {
            field: "transportTypeName",
            title: "运输方式"
        }, {
            field: "calculateTypeName",
            title: "计价方式"
        }, {
            field: "exacctName",
            title: "费用科目"
        }, {
            field: "minimumFee",
            title: "最低收费"
        }, {
            field: "minimumCondiction",
            title: "区间",
            template: function (dataItem) {
                var min = dataItem.minimumCondiction ? dataItem.minimumCondiction : "<i class='fa fa-ban'></i>";
                var max = dataItem.maxmumCondiction ? dataItem.maxmumCondiction : "<i class='fa fa-ban'></i>";
                return min + " <i class='fa fa-arrows-h'></i> " + max;
            },
            width: 120
        }, {
            field: "unitPrice",
            title: "单价"
        }, {
            field: "timeline",
            title: "时效"
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
            var startCity = $.trim(self.startCity());
            if (startCity) {
                filters.push({
                    field: "startCity",
                    operator: "contains",
                    value: startCity
                });
            }
            var destCity = $.trim(self.destCity());
            if (destCity) {
                filters.push({
                    field: "destCity",
                    operator: "contains",
                    value: destCity
                });
            }
            var calculateType = $.trim(self.calculateType());
            var transportType = $.trim(self.transportType());
            if (calculateType){
                filters.push({
                    field: "calculateType",
                    operator: "eq",
                    value: calculateType
                });
            }
            if (transportType){
                filters.push({
                    field: "transportType",
                    operator: "eq",
                    value: transportType
                });
            }
            if (filters.length > 0){
                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            }else{
                filters.push({
                    field: "carrierId",
                    operator: "eq",
                    value: "${carrierId}"
                });
            }

        }
    }

    $(function () {
        var quoteViewModel = new CarrierQuoteViewModel();
        ko.applyBindings(quoteViewModel, document.getElementById("grid-carrier-quote"));
    });
</script>