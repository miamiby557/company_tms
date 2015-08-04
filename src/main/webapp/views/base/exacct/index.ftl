<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/baseExacct"><i class="fa fa-user"></i> 业务费用科目</a></li>
</ol>

<div id="baseExacct-grid-wrap">
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
            <a data-role="button" href="#/baseExacct/create"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:remove, enable:canRemove">
                <i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" data-bind="kendoButton:modify, enable:canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
    </div>
    <div data-bind="kendoTreeList: { data: dataSource, widget: widget, columns: columns, sortable: true,toolbar:['excel','pdf'], groupable: true, editable: 'popup', pageable: pageable, selectable: 'row', change:onChange}"></div>
</div>

<script>
    $(function () {
        var baseExacctGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createTreeListDataSource("exacctId", "/baseExacct/getDataSource","superiorId");
            self.pageable = {pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.keyword = ko.observable();

            self.columns = [{
                title: "代码",
                field: "code"
            }, {
                title: "名称",
                field: "name"
            }, {
                title: "序号",
                field: "indexNumber"
            },{
                title: "备注",
                field: "remark"
            }];

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

            self.modify = function(){
                var url = "#/baseExacct/modify/" + self.selected().exacctId;
                router.navigate(url);
            };

            self.remove = function () {
                if (!confirm("确定要删除吗？")) return;
                var selectedId=self.selected().exacctId;
                var selectedItem=self.selected();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/baseExacct/deleteById",
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
                            field: "name",
                            operator: "contains",
                            value: word
                        }, {
                            field: "code",
                            operator: "contains",
                            value: word
                        }, {
                            field: "remark",
                            operator: "contains",
                            value: word
                        }]
                    });
                else
                    self.dataSource.filter([]);
            };
        };

        var viewModel = new baseExacctGridModel();
        ko.applyBindings(viewModel, document.getElementById("baseExacct-grid-wrap"));
    });
</script>