<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysUser"><i class="fa fa-user"></i> 用户管理</a></li>
</ol>

<div id="user-grid-wrap">
    <div class="grid-toolbar">
        <div>
            <a data-role="button" href="#/sysUser/create"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:remove"> <i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" data-bind="kendoButton:modify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
        <div>
            <form data-bind="submit:search">
                <input class="k-textbox" data-bind="value:keyword">
                <button type="submit" data-role="button">
                    <i class="fa fa-search"></i> 搜索
                </button>
                <button type="button" data-bind="kendoButton:clearFilter">
                    <i class="fa fa-eraser"></i> 清空
                </button>
            </form>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns, sortable: true, groupable: true, editable: 'popup', pageable: pageable, selectable: 'multiple, row'}"></div>
</div>

<script>
    $(function () {
        var userGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("userId", "/sysUser/getDataSource");
            self.pageable = {pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.keyword = ko.observable();

            self.columns = [{
                title: "用户名",
                field: "username"
            }, {
                title: "姓名",
                field: "fullName"
            }, {
                title: "邮箱",
                field: "email"
            }, {
                title: "工号",
                field: "employeeNumber"
            },{
                title: "分支机构",
                field: "branchName"
            }, {
                title: "站点",
                field: "siteName"
            }, {
                title: "是否启用",
                field: "isActive",
                template: '<input type="checkbox" #= isActive ? "checked=checked" : "" # disabled="disabled" />',
                width: 80
            }, {
                title: "允许登录",
                field: "isAllowLogin",
                template: '<input type="checkbox" #= isAllowLogin ? "checked=checked" : "" # disabled="disabled" />',
                width: 80
            }];

            self.modify = function () {
                if (self.widget().select().length != 1) {
                    notify("请选择要修改的一行", "error");
                    return;
                }
                var row = self.widget().select()[0];
                var dataItem = self.widget().dataItem(row);
                var url = "#/sysUser/modify/" + dataItem.userId;
                router.navigate(url);
            };

            self.remove = function () {
                if (self.widget().select().length != 1) {
                    notify("请选择要删除的一行", "error");
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
                        selectedIds.push(dataItem.userId);
                    }
                });

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/sysUser/batchDeleteById",
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
                            field: "username",
                            operator: "contains",
                            value: word
                        }, {
                            field: "email",
                            operator: "contains",
                            value: word
                        }, {
                            field: "fullName",
                            operator: "contains",
                            value: word
                        }]
                    });
                else
                    self.dataSource.filter([]);
            };
        };

        var viewModel = new userGridModel();
        ko.applyBindings(viewModel, document.getElementById("user-grid-wrap"));
    });
</script>