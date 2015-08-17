<div id="receiver-grid-wrap">
    <div class="grid-toolbar">
        <div class="grid-buttons">
            <button type="button" data-bind="kendoButton:add"><i class="fa fa-plus"></i> 增加</button>
            <button type="button" data-bind="kendoButton:remove, enable:canRemove"><i class="fa fa-remove"></i> 删除</button>
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
        self.dataSource = kendo.utils.createFilterDataSource("clientReceiverId", "/crmClientReceiver/getDataSource" ,
                {field: "clientId",
                 operator: "eq",
                 value: "${crmClient.clientId}"
        });
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
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
            field: "name",
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


        self.canRemove = ko.computed(function () {
            return self.selected();
        });

        self.add = function () {
            var url = "crmClientReceiver/create/${crmClient.clientId}";
            modal("新增", url, {
                width: 800, height: 400, close: function (e) {
                    console.info(e.sender.sender);
                    if (e.sender.sender) {
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/crmClientReceiver/createByAddressIds/${crmClient.clientId}",
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
        self.remove = function () {
            if (!confirm("确定删除该类型吗？")) return;
            var selectedId = self.selected().clientReceiverId;
            var selectedItem = self.selected();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/crmClientReceiver/deleteByAddressId",
                data:ko.toJSON(selectedId)
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
        ko.applyBindings(viewModel, document.getElementById("receiver-grid-wrap"));
    });
</script>