<#import "/utils/dropdown.ftl" as utils >
<div id="grid-carrier-quote">
    <div class="grid-toolbar">
        <div>
            <label for="startCity">起始城市</label>
            <input type="text" class="k-input k-textbox"  name="startCity" id="startCity" data-bind="value:startCity" >
            <label for="destCity">目的城市</label>
            <input type="text" class="k-input k-textbox"  name="destCity" id="destCity" data-bind="value:destCity" >
            <label for="transportType">运输方式</label><@utils.koDropDown data="transportTypeList" value="transportType"/>
            <label for="calculateType">计价方式</label><@utils.koDropDown data="calculateTypeList" value="calculateType"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:create">
                <i class="fa fa-plus"></i> 增加
            </button>
            <button type="button" data-bind="kendoButton:copy,enable:canModify">
                <i class="fa fa-copy"></i> 复制
            </button>
            <button type="button" data-bind="kendoButton:remove,enable:canRemove">
                <i class="fa fa-remove"></i> 批量删除
            </button>
            <button type="button" data-bind="kendoButton:modify,enable:canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true,toolbar:['excel','pdf'],
        groupable: true, editable:false, pageable: pageable, selectable: 'multiple,row', change:onChange}"></div>
</div>

<script>
    function CarrierQuoteViewModel() {
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("carrierQuoteId","/scmCarrierQuoteView/getDataSource");
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
            title: "序号",
            template: function (dataItem) {
                return self.dataSource.indexOf(dataItem)+1;
            },
            width: 40
        },{
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
                var min = dataItem.minimumCondiction ? dataItem.minimumCondiction : "0";
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
        self.canModify = ko.computed(function () {
            return self.selected();
        });

        self.canRemove = ko.computed(function () {
            return self.selected();
        });

        self.modify = function () {
            var url = "scmCarrierQuote/modify/" + self.selected().carrierQuoteId;
            modal("编辑报价", url, {width:800, height: 600, close:function(){
                self.dataSource.query();
            }});
        };
        self.copy = function () {
            var url = "scmCarrierQuote/copy/" + self.selected().carrierQuoteId;
            modal("编辑报价", url, {width:800, height: 600, close:function(){
                self.dataSource.query();
            }});
        };
        self.create = function () {
            var url = "scmCarrierQuote/create/${carrierId}";
            modal("编辑报价", url, {width:800, height: 600, close:function(){
                self.dataSource.query();
            }});
        };
        self.remove = function () {
            var selectedIds = [];
            var rows = self.widget().select();
            for(var i=0;i<rows.length;i++){
                var row=rows[i];
                var dataItem = self.widget().dataItem(row);
                selectedIds.push(dataItem.carrierQuoteId);
            }

            if (!confirm("确定删除该报价吗？")) return;

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/scmCarrierQuote/batchDeleteById",
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
        self.calculateType=ko.observable();
        self.transportType=ko.observable();
        self.transportTypeList=commonData.transportType;
        self.calculateTypeList=commonData.calculateType;
        self.search = function () {
            filters.length=0;
            filters.push({
                field: "carrierId",
                operator: "eq",
                value: "${carrierId}"
            });
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