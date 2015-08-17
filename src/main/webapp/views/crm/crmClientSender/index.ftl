<div id="address-grid-wrap">
    <div class="grid-toolbar">
        <div>
            <input class="k-textbox" data-bind="value:keyword">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:add"><i class="fa fa-plus"></i> 增加</button>
            <button type="button" data-bind="kendoButton:remove, enable:canRemove"><i class="fa fa-remove"></i> 删除
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,
     editable:false, pageable: pageable, selectable: 'row',change:onChange}"></div>
</div>
<script>
    var GridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createFilterDataSource("clientReceiverId", "/crmClientSender/getDataSource",{field: "clientId",operator: "eq",value: "${crmClient.clientId}"});
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.keyword = ko.observable();
        self.addressType=commonData.baseAddressType;
        self.search = function () {
            var word = $.trim(self.keyword());
            var filters = [];
            if (word) {
                if (word)
                    self.dataSource.filter({
                        logic: "and",
                        filters: [{
                            field: "address",
                            operator: "contains",
                            value: word
                        }]
                    });
                else
                    self.dataSource.filter([]);
            }
        };
        self.columns = [  {
            field: "name",
            title: "所属地区"
        },{
            field: "address",
            title: "详细街道地址"
        }, {
            field: "contactMan",
            title: "联系人"
        }, {
            field: "contactPhone",
            title: "联系电话"
        }, {
            field: "addressType",
            title: "地址类型",
            values: commonData.baseAddressType()
        }];
        self.selected = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selected(dataItem);
        };
        self.clearFilter = function () {
            self.keyword("");
            self.dataSource.filter([]);
        };
        self.canRemove = ko.computed(function () {
            return self.selected();
        });

        self.canModify = ko.computed(function () {
            return self.selected();
        });
        self.add = function () {
            var url = "crmClientSender/create/${crmClient.clientId}";
            modal("新增", url, {
                width: 800, height: 400, close: function (e) {
                    console.info(e.sender.sender);
                    if (e.sender.sender) {
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/crmClientSender/createByAddressIds/${crmClient.clientId}",
                            data: ko.toJSON(e.sender.sender)
                        }).done(function (result) {
                            if (result.success === true) {
                                self.dataSource.query();
                                notify("增加成功", "success");
                            }
                            else {
                                notify(result.message, "error");
                            }
                        });
                    }
                }
            });
        };
        self.modify = function () {
            var selectedId = self.selected().clientSenderId;
            var url = "crmClientSender/modify/" + selectedId;
            modal("新增", url, {
                width: 400, height: 400, close: function (e) {
                    self.dataSource.query();
                }
            });
        };
        self.remove = function () {
            if (!confirm("确定要删除吗？")) return;
            var selectedId = self.selected().clientSenderId;
            var selectedItem = self.selected();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/crmClientSender/deletByClientIdAndAddressId",
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
        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("address-grid-wrap"));
    });

</script>