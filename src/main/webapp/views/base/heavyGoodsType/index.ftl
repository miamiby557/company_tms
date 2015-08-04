<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/baseHeavygoodsType"><i class="fa fa-users"></i> 重货类型</a></li>
</ol>

<div id="heavyGoodsType-grid-wrap">
    <div class="grid-toolbar">
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
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true, editable:false, pageable: pageable, selectable: 'row', change:onChange}"></div>
    <h4 style="text-align: right">*重量体积比:1吨货物对应的体积方数</h4>
</div>

<script>
    var gridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("heavygoodsTypeId", "/baseHeavygoodsType/getDataSource");
        self.pageable = {pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.columns = [{
            field: "name",
            title: "名称"
        },{
            field: "ratioMin",
            title: "重量体积比区间",
            template: function (dataItem) {
                var min = dataItem.ratioMin ? dataItem.ratioMin : "0";
                var max = dataItem.ratioMax ? dataItem.ratioMax : "<i class='fa fa-ban'></i>";
                return min + " <i class='fa fa-arrows-h'></i> " + max;
            }
        }/*, {
            field: "ratioMin",
            title: "重量体积比最小值"
        }, {
            field: "ratioMax",
            title: "重量体积比最小值"
        }*/, {
            field: "remark",
            title: "备注"
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

        self.add=function(){
            var url="baseHeavygoodsType/create";
            modal("新增", url, {
                width: 400, height: 400, close: function () {
                    self.dataSource.query();
                }
            });
        };

        self.modify=function(){
            var url = "/baseHeavygoodsType/modify/" + self.selected().heavygoodsTypeId;
            modal("新增", url, {
                width: 400, height: 400, close: function () {
                    self.dataSource.query();
                }
            });
        };

        self.remove = function () {
            if (!confirm("确定删除该类型吗？")) return;
            var selectedId=self.selected().heavygoodsTypeId;
            var selectedItem=self.selected();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/baseHeavygoodsType/deleteById",
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
        ko.applyBindings(viewModel, document.getElementById("heavyGoodsType-grid-wrap"));
    });
</script>