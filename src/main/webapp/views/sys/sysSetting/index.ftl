<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysSetting"><i class="fa fa-user"></i> 系统参数</a></li>
</ol>

<div id="setting-grid-wrap">
    <div class="grid-toolbar">
        <div>
            <a data-role="button" href="#/sysSetting/create"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:remove">
                <i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" data-bind="kendoButton:modify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
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
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns, sortable: true, groupable: true, editable: 'popup', pageable: pageable, selectable: 'multiple, row'}"></div>
</div>

<script>
    $(function () {
        var userGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("name", "/sysSetting/getDataSource");
            self.pageable = {pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.keyword = ko.observable();

            self.columns = [{
                field: "name",
                title: "参数名称"
            }, {
                field: "value",
                title: "参数值"
            }, {
                field: "isActive",
                title: "是否启用",
                template: '<input type="checkbox" #= isActive ? "checked=checked" : "" # disabled="disabled" />',
                width: 80
            }, {
                field: "remark",
                title: "备注"
            }];

            self.modify = function(){
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                var url = "#/sysSetting/modify/" + dataItem.name;
                router.navigate(url);
            };

            self.remove = function () {
                if (!confirm("确定要删除吗？")) return;

                var selectedItems = [];
                var selectedIds = [];
                var rows = self.widget().select();
                rows.each(function (i, row) {
                    var dataItem = self.widget().dataItem(row);
                    if (dataItem) {
                        selectedItems.push(dataItem);
                        selectedIds.push(dataItem.name);
                    }
                });

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/sysSetting/batchDeleteById",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        for (var i in selectedItems) self.dataSource.remove(selectedItems[i]);
                        notify("删除成功", "success");
                    }
                    else {
                        notify(result.message, "error");
                    }
                });

            };

            self.clearFilter = function () {
                self.keyword("");
                self.dataSource.filter([]);
            };

            self.search = function () {
                var word = $.trim(self.keyword());
                if (word)
                    self.dataSource.filter({
                        logic: "or",
                        filters: [{
                            field: "name",
                            operator: "contains",
                            value: word
                        }, {
                            field: "value",
                            operator: "contains",
                            value: word
                        }]
                    });
                else
                    self.dataSource.filter([]);
            };
        };

        var viewModel = new userGridModel();
        ko.applyBindings(viewModel, document.getElementById("setting-grid-wrap"));
    });
</script>