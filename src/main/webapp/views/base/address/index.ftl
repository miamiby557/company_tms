<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/baseAddress"><i class="fa fa-users"></i>地址</a></li>
</ol>
<div id="address-grid-wrap">
    <div class="grid-toolbar">
        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:add"><i class="fa fa-plus"></i> 增加</button>
            <button type="button" data-bind="kendoButton:remove, enable:canRemove"><i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" data-bind="kendoButton:modify, enable:canModify"><i class="fa fa-edit"></i> 修改
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, editable:false,
    pageable: pageable, selectable: 'row', change:onChange}"></div>
</div>
<script>
    var gridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.addressType=commonData.baseAddressType;
        self.dataSource = kendo.utils.createDataSource("addressId", "/baseAddress/getDataSource");
        self.pageable = {pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.columns = [{
            field: "addressType",
            title: "地址类型",
            values: commonData.baseAddressType()
        }, {
            field: "contactMan",
            title: "联系人"
        }, {
            field: "contactPhone",
            title: "联系电话"
        }, {
            field: "region",
            title: "地区"
        }, {
            field: "address",
            title: "地址"
        }];
        self.selected = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selected(dataItem);
        };

        self.canModify = ko.computed(function () {
            return self.selected();
        });

        self.canRemove = ko.computed(function () {
            return self.selected();
        });

        self.add = function () {
            var url = "baseAddress/create";
            modal("新增", url, {
                width: 400, height: 400, close: function () {
                    self.dataSource.query();
                }
            });
        };

        self.modify = function () {
            var url = "/baseAddress/modify/" + self.selected().addressId;
            modal("新增", url, {
                width: 400, height: 400, close: function () {
                    self.dataSource.query();
                }
            });
        };

        self.remove = function () {
            if (!confirm("确定删除该类型吗？")) return;
            var selectedId = self.selected().addressId;
            var selectedItem = self.selected();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/baseAddress/deleteById",
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

    };

    $(function () {
        var viewModel = new gridModel();
        ko.applyBindings(viewModel, document.getElementById("address-grid-wrap"));
    });
</script>