<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/sysList"><i class="fa fa-list-ol"></i> 系统列表</a></li>
    <li><a href="#/sysList/create"><i class="fa fa-plus"></i> 增加</a></li>
</ol>

<div>
    <form id="sysList-form" method="post" data-bind="submit:save" class="form-horizontal row">
        <div data-bind="with:model">
            <div class="form-group">
                <label for="code">编码</label>
                <input type="text" class="k-input k-textbox" name="code" id="code" data-bind="value:code" required="required" minlength="3" maxlength="50" pattern="^[0-9a-zA-Z]*$">
            </div>
            <div class="form-group">
                <label for="name">名称</label>
                <input type="text" class="k-input k-textbox" name="name" id="name" data-bind="value:name" required="required" minlength="1" maxlength="25">
            </div>
            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark" class="k-textbox" rows="5" data-bind="value:remark"></textarea>
            </div>
            <div class="form-group">
                <label >条目</label>

                <div id="items-grid-wrap">
                    <div data-bind="kendoGrid: {widget: $root.itemsGrid, data: $root.itemsDataSource, sortable: true, editable:{ confirmation:false}, columns: $root.itemColumns, toolbar:$root.itemToolbar }"></div>
                </div>
            </div>

            <div class="form-buttons">
                <button type="submit" class="k-button k-button-icontext k-primary ">
                    <i class="fa fa-save"></i> 保存
                </button>

                <button type="button" data-action="goBack" class="k-button k-button-icontext">
                    <i class="fa fa-close"></i> 取消
                </button>
            </div>
        </div>

    </form>
</div>
<script>
    function SysListCreateModel() {
        var self = this;
        self.model = {};
        self.model.code = ko.observable();
        self.model.name = ko.observable();
        self.model.remark = ko.observable();

        self.itemsGrid = ko.observable();
        self.itemsDataSource = new kendo.data.DataSource({
            data: [],
            schema: {
                model: {
                    fields: {
                        name: {type: "string"},
                        value: {type: "number"},
                        remark: {type: "string"}
                    }
                }
            }
        });
        self.itemToolbar = [{name: "create"}];
        self.itemColumns = [{
            field: "name",
            title: "名称",
            width: 180
        }, {
            field: "value",
            title: "值",
            width: 180
        }, {
            field: "remark",
            title: "备注"
        }, {command: ["destroy"], width: 90}];

        self.save = function () {
            // 检查表单是否通过验证
            if (!$("#sysList-form").validate().valid()) return;

            self.model.items = self.itemsDataSource.data();

            // 发送AJAX请求保存数据
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/sysList/create",
                data: ko.toJSON(self.model)
            }).done(function (result) {
                if (result.success === true) {
                    notify("增加系统列表成功", "success");
                    router.navigate("/sysList");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
    }

    /*function SysListItemCreateModel() {
        var self = this;
        self.serialNumber = ko.observable();
        self.value = ko.observable();
        self.name = ko.observable();
        self.isActive = ko.observable(true);
        self.remark = ko.observable();
    }*/

    $(function () {
        $("#sysList-form").validate();
        var viewModel = new SysListCreateModel();
        ko.applyBindings(viewModel, document.getElementById("sysList-form"));
    });


</script>