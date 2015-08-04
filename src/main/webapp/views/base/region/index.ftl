<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/baseRegionView"><i class="fa fa-users"></i> 地址区域</a></li>
</ol>

<div id="region-grid-wrap">
    <div class="grid-toolbar">
        <div>
            <input class="k-textbox" data-bind="value:keyword">
            <button type="button" data-bind="kendoButton:search">
                <i class="fa fa-search"></i> 搜索
            </button>
            <button type="button" data-bind="kendoButton:clearFilter">
                <i class="fa fa-eraser"></i> 清空
            </button>
        </div>

    </div>
    <div data-bind="kendoTreeList: { data: dataSource, widget: widget, columns: columns,toolbar:['excel','pdf'], sortable: true, groupable: true, editable: 'popup', pageable: pageable, selectable: 'row'}"></div>
</div>

<script>
    $(function () {
        var regionGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createTreeListDataSource("regionId", "/baseRegionView/getDataSource", "superiorRegionId");
            self.pageable = {pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.keyword("");
                self.dataSource.filter([]);
            };
            self.columns = [{
                field: "code",
                title: "区域编号"
            }, {
                field: "name",
                title: "区域名称"
            }, {
                field: "regionTypeName",
                title: "区域类型"
            }, {
                field: "superiorRegionName",
                title: "上级区域名称"
            }];

            self.keyword = ko.observable();
            self.search = function () {
                var word = $.trim(self.keyword());
                if (word)
                    self.dataSource.filter({
                        logic: "or",
                        filters: [{
                            field: "code",
                            operator: "contains",
                            value: word
                        }, {
                            field: "name",
                            operator: "contains",
                            value: word
                        }, {
                            field: "namePinyin",
                            operator: "contains",
                            value: word
                        }]
                    });
                else
                    self.dataSource.filter([]);
            }
        };

        var viewModel = new regionGridModel();
        ko.applyBindings(viewModel, document.getElementById("region-grid-wrap"));
    });
</script>