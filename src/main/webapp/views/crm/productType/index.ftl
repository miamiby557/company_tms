<div id="product-grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="name">名称</label><input class="k-textbox" data-bind="value:name">
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search">
                <i class="fa fa-search"></i> 搜索
            </button>
            <button type="button" data-bind="kendoButton:clearFilter">
                <i class="fa fa-eraser"></i> 清空
            </button>
        </div>
        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:add">
                <i class="fa fa-plus"></i> 增加
            </button>
            <button type="button" data-bind="kendoButton:remove, enable:canRemove">
                <i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" data-bind="kendoButton:modify, enable:canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: {data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, editable:false, pageable: pageable, selectable: 'row', change:onChange }"></div>
</div>


<script>
    $(function () {
        var crmClientProductTypeGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("clientProductTypeId", "/crmClientProductType/dataSource?clientId=${crmClient.clientId}");
            self.pageable = {pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.name = ko.observable();

            self.columns = [{
                field: "value",
                title: "值"
            }, {
                field: "name",
                title: "名称",
            }, {
                field: "remark",
                title: "备注"
            }];

            self.add=function(){
                var url="crmClientProductType/createProductType/${crmClient.clientId}";
                modal("新增", url, {
                    width: 400, height: 400, close: function () {
                        self.dataSource.query();
                    }
                });
            };

            self.modify = function(){
                var selectedId=self.selected().clientProductTypeId;
                var url="crmClientProductType/modifyProductType/"+selectedId;
                modal("新增", url, {
                    width: 400, height: 400, close: function () {
                        self.dataSource.query();
                    }
                });
            };

            self.selected = ko.observable();
            self.onChange = function () {
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                self.selected(dataItem);
            };
            self.canRemove = ko.computed(function () {
                return self.selected();
            });

            self.canModify = ko.computed(function () {
                return self.selected();
            });

            self.remove = function () {
                if (!confirm("确定要删除吗？")) return;
                var selectedId=self.selected().clientProductTypeId;
                var selectedItem=self.selected();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/crmClientProductType/deleteById",
                    data: ko.toJSON(selectedId)
                }).done(function (result) {
                    if (result.success === true) {
                        self.dataSource.remove(selectedItem);
                        notify("删除成功", "success");
                    }
                    else {
                        notify(result.message, "error");
                    }
                });
            };

            self.clearFilter = function () {
                self.name("");
                self.dataSource.filter([]);
            };

            self.search = function () {
                var name = $.trim(self.name());
                if (name)
                    self.dataSource.filter({
                        logic: "and",
                        filters: [{
                            field: "name",
                            operator: "contains",
                            value: name
                        }]
                    });
                else
                    self.dataSource.filter([]);
            }
        };

        var viewModel = new crmClientProductTypeGridModel();
        ko.applyBindings(viewModel, document.getElementById("product-grid-wrap"));
    });
</script>