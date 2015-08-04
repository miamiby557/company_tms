<div id="grid-wrap-line">
    <div class="grid-toolbar">
        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:modify, enable:canModify">
                <i class="fa fa-edit"></i> 修改时效
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: $root.lineDataSource, widget: $root.lineWidget, columns: $root.lineColumns,sortable: true, groupable: true,
    editable:false, pageable: $root.pageable, selectable: 'row', change:onChange}"></div>
</div>

<script>
    function CarrierLineViewModel() {
        var self = this;
        self.lineWidget = ko.observable();
        self.lineDataSource = new kendo.data.DataSource({
            schema: {
                model: {
                    id: "carrierLineId"
                },
                data: function (response) {
                    if (response.data) return response.data;
                    return response;
                },
                total: 'total'
            },
            transport: {
                parameterMap: function (options) {
                    return kendo.stringify(options);
                },
                read: kendo.utils.createTransport("/scmCarrierLine/getDataSource"),
            },
            pageSize: 20,
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true,
            filter:{
                field: "carrierId",
                operator: "eq",
                value: "${carrierId}"
            }

        });

        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.lineColumns = [{
            title: "序号",
            template: function (dataItem) {
                return self.lineDataSource.indexOf(dataItem)+1;
            },
            width: 40
        },{
            field: "startCity",
            title: "始发城市"
        }, {
            field: "destCity",
            title: "目的城市"
        }, {
            field: "transportType",
            title: "运输方式",
            values: commonData.transportType()
        }, {
            field: "timeline",
            title: "时效"
        }];

        self.selected = ko.observable();
        self.onChange = function () {
            var row = self.lineWidget().select()[0];
            var dataItem = self.lineWidget().dataItem(row);
            self.selected(dataItem);
        };

        self.canModify = ko.computed(function () {
            return self.selected();
        });

        self.modify = function () {
            var url = "scmCarrierLine/modify/"+self.selected().carrierLineId;
            modal("修改线路时效", url, {width:600, height: 400, close:function(){
                self.selected("");
                self.lineDataSource.query();
            }});
        };
    }

    $(function () {
        var lineViewModel = new CarrierLineViewModel();
        ko.applyBindings(lineViewModel, document.getElementById("grid-wrap-line"));
    });
</script>