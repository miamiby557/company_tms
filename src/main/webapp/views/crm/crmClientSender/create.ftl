<#import "/utils/dropdown.ftl" as utils >
<div id="grids-wrap">
    <div class="grids-toolbar">
        <div class="grids-buttons">

            <button type="button" data-bind="kendoButton:select, enable:canSelect">
                <i class="fa fa-edit"></i> 选择
            </button>
        </div>
    </div>
    <div id="transportSearch" data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, editable:false, pageable: pageable, selectable:'multiple, row', change:onChange }"></div>
</div>


<script>

    var GridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("addressId", "/baseAddress/getDataSource");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.addressType=commonData.baseAddressType;
        self.clearFilter = function () {
            self.dataSource.filter([]);
        };

        self.columns = [{
            field: "addressType",
            title: "地址类型",
            values: commonData.baseAddressType()
        }, {
            field: "contactMan",
            title: "联系人"
        }, {
            field: "contactPhone",
            title: "联系电话"
        }, {
            field: "name",
            title: "地区"
        }, {
            field: "address",
            title: "地址"
        }];

        self.selectedOrder = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedOrder(dataItem);
        };

        self.canSelect = ko.computed(function () {
            return self.selectedOrder();
        });

        self.select = function () {
            var selectedIds = [];
            var selectedItems=[];
            var rows = self.widget().select();
            rows.each(function (i, row) {
                var dataItem = self.widget().dataItem(row);
                if (dataItem) {
                    selectedIds.push(dataItem.addressId);
                    selectedItems.push(dataItem);
                }
            });
            $(".k-window-content.k-content").data("kendoWindow").sender=selectedIds;
            $(".k-window-content.k-content").data("kendoWindow").close();
        };

        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length+1;i++){
                select.push("tr:eq("+i+")");
            }
            console.info(select.toString());
            var grid = $("#transportSearch").data("kendoGrid");
            grid.select(select.toString());
            select.length=0;
        }
        self.mergeStatus=ko.observable();
        self.mergeStatusList=commonData.transportMergeStatus;
        self.transportTypes = commonData.transportType;
        self.clients = kendo.utils.createDataSource("clientId", "/crmClient/getDataSource");
        self.citys = kendo.utils.createListDataSource("regionId","/baseRegion/getCityList");
        self.search = function () {
            var clientNumbers = $.trim(self.clientNumbers());
            var clientOrderNumber = $.trim(self.clientOrderNumber());
            var receiveMan = $.trim(self.receiveMan());
            var receiveCompany = $.trim(self.receiveCompany());
            var orderBeginDate = $.trim(self.orderBeginDate());
            var orderEndDate = $.trim(self.orderEndDate());
            var clientId = $.trim(self.clientId());
            var destCityId = $.trim(self.destCityId());
            var transportType = $.trim(self.transportType());
            var mergeStatus= $.trim(self.mergeStatus());
            var filters = new Array();
            if (clientNumbers) {
                clientNumbers = clientNumbers.replaceAllChar(",","，");
                var numbers =clientNumbers.split("，");
                filters.push({
                    field: "clientOrderNumber",
                    operator: "in",
                    value: numbers
                });
            }
            if (clientOrderNumber) {
                filters.push({
                    field: "clientOrderNumber",
                    operator: "contains",
                    value: clientOrderNumber
                });
            }
            if (transportType) {
                filters.push({
                    field: "transportType",
                    operator: "eq",
                    value: transportType
                });
            }
            if (mergeStatus) {
                filters.push({
                    field: "mergeStatus",
                    operator: "eq",
                    value: mergeStatus
                });
            }
            if (receiveMan) {
                filters.push({
                    field: "receiveMan",
                    operator: "contains",
                    value: receiveMan
                });
            }
            if (receiveCompany) {
                filters.push({
                    field: "receiveCompany",
                    operator: "contains",
                    value: receiveCompany
                });
            }
            if (orderBeginDate) {
                filters.push({
                    field: "orderDate",
                    operator: "gte",
                    value: new Date(orderBeginDate)
                });
            }
            if (orderEndDate) {
                var endDate=new Date(orderEndDate);
                filters.push({
                    field: "orderDate",
                    operator: "lt",
                    value: new Date(endDate.setDate(endDate.getDate()+1))
                });
            }
            if (clientId) {
                filters.push({
                    field: "clientId",
                    operator: "eq",
                    value: clientId
                });
            }
            if (destCityId) {
                filters.push({
                    field: "destCityId",
                    operator: "eq",
                    value: destCityId
                });
            }
            if (filters.length > 0)
                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            else
                self.dataSource.filter([]);
        }
    };
    $(function () {

        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grids-wrap"));
    });
</script>
