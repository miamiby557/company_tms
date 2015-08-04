<div id="client-line">
    <div class="grid-toolbar">
        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:modify, enable:canModify">
                <i class="fa fa-edit"></i> 修改时效
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: $root.lineDataSource, widget:  $root.lineWidget, columns:  $root.lineColumns,toolbar:['excel','pdf'],sortable: true, groupable: true,
    editable:false, pageable:  $root.pageable, selectable: 'row', change:onChange}"></div>
</div>

<script>
    function lineGridModel() {
        //线路
        var self = this;
        self.lineWidget = ko.observable();
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.lineDataSource = new kendo.data.DataSource({
            schema: {
                model: {
                    id: "clientLineId"
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
                read: kendo.utils.createTransport("/crmClientLine/getDataSource"),
            },
            pageSize: 20,
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true,
            filter:{
                field: "clientId",
                operator: "eq",
                value: "${clientId}"
            }

        });
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
            var url = "crmClientLine/modify/"+self.selected().clientLineId;
            modal("修改线路时效", url, {width:600, height: 400, close:function(){
                self.selected("");
                self.lineDataSource.query();
            }});
        };

    }

    $(function () {
        var lineViewModel = new lineGridModel();
        ko.applyBindings(lineViewModel, document.getElementById("client-line"));
    });
</script>