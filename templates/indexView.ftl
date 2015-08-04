<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/${camelModelName}"><i class="fa fa-users"></i> ${camelModelName}</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <a data-role="button" href="#/${camelModelName}/create"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:remove">
                <i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" data-bind="kendoButton:modify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
        <div>
            <input class="k-textbox" data-bind="value:keyword">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, editable:false, pageable: pageable, selectable: 'multiple,row'}"></div>
</div>


<script>
    $(function () {
        var gridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("${jpaId}", "/${camelModelName}/getDataSource");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.keyword("");
                self.dataSource.filter([]);
            };

            self.columns =  [
            <#list props as prop> {
                field: "${prop.name}",
                title: "${prop.name}",
                width: 120
            }, </#list>];

            self.modify = function () {
                if (self.widget().select().length != 1) {
                    notify("请选择要修改的一行", "error");
                    return;
                }
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                var url = "#/${camelModelName}/modify/" + dataItem.${jpaId};
                router.navigate(url);
            };

            self.remove = function () {
                if (self.widget().select().length < 1) {
                    notify("请选择要删除的行", "error");
                    return;
                }
                if (!confirm("确定要删除吗？")) return;

                var selectedItems = [];
                var selectedIds = [];
                var rows = self.widget().select();
                rows.each(function (i, row) {
                    var dataItem = self.widget().dataItem(row);
                    if (dataItem) {
                        selectedItems.push(dataItem);
                        selectedIds.push(dataItem.${jpaId});
                    }
                });

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/${camelModelName}/batchDeleteById",
                    data: ko.toJSON(selectedIds)
                }).done(function (result) {
                    if (result.success === true) {
                        for (var i in selectedItems) self.dataSource.remove(selectedItems[i]);
                        notify("删除成功！", "success");
                    }
                    else {
                        notify(result.message, "error");
                    }
                });

            };

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
                        }]
                    });
                else
                    self.dataSource.filter([]);
            }
        };

        var viewModel = new gridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>