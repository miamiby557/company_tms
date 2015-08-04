<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysList"><i class="fa fa-list-ol"></i> 系统列表</a></li>
</ol>

<div id="sysList-grid-wrap">
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
        <div class="grid-buttons">
            <a data-role="button" href="#/sysList/create"> <i class="fa fa-plus"></i> 增加 </a>
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
        var sysListGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("listId", "/sysList/getDataSource");
            self.pageable = {pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.keyword = ko.observable();

            self.columns = [{
                field: "name",
                title: "名称"
                /*template: kendo.template("<a href='\\#/sysList/details/#= listId #'>#= name #</a>")*/
            }, {
                field: "code",
                title: "编码",
            }, {
                field: "remark",
                title: "备注"
            }];

            self.modify = function(){
                var url = "#/sysList/modify/" + self.selected().listId;
                router.navigate(url);
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
                var selectedId=self.selected().listId;
                var selectedItem=self.selected();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/sysList/deleteById",
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
                self.keyword("");
                self.dataSource.filter([]);
            };

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

        var viewModel = new sysListGridModel();
        ko.applyBindings(viewModel, document.getElementById("sysList-grid-wrap"));
    });
</script>