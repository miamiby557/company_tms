<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysOrganization"><i class="fa fa-sitemap"></i> 组织架构</a></li>
</ol>

<div id="sysOrganization-grid-wrap">
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
            <a data-role="button" href="#/sysOrganization/create"> <i class="fa fa-plus"></i> 增加 </a>
            <button type="button" data-bind="kendoButton:remove">
                <i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" data-bind="kendoButton:modify">
                <i class="fa fa-edit"></i> 修改
            </button>
        </div>
    </div>
    <div data-bind="kendoTreeList: { data: dataSource, widget: widget, columns: columns, sortable: true, selectable: 'row'}"></div>
</div>

<script>

    var sysOrganizationGridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createTreeListDataSource("organizationId", "/sysOrganization/getDataSource","superiorOrganizationId");
        self.keyword = ko.observable();

        self.columns = [{
            title: "代码",
            field: "code",
            width: 120
        }, {
            title: "名称",
            field: "name",
            width: 120
        }, {
            title: "组织类型",
            field: "type",
            values: commonData.organizationType()
        }, {
            title: "所在地",
            field: "city"
        }, {
            title: "是否启用",
            field: "isActive",
            template: '<input type="checkbox" #= isActive ? "checked=checked" : "" # disabled="disabled" />',
            width: 80
        }, {
            title: "备注",
            field: "remark"
        }];

        self.modify = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            if (dataItem) {
                var url = "#/sysOrganization/modify/" + dataItem.organizationId;
                router.navigate(url);
            }
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
                    selectedIds.push(dataItem.organizationId);
                }
            });

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/sysOrganization/batchDeleteById",
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
        };
    };
    $(function () {

        var viewModel = new sysOrganizationGridModel();
        ko.applyBindings(viewModel, document.getElementById("sysOrganization-grid-wrap"));
    });
</script>