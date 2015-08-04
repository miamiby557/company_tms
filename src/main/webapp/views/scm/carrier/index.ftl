<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/scmCarrierView"><i class="fa fa-users"></i> 承运商档案</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="code">编码</label><input class="k-textbox" data-bind="value:code">
            <label for="name">名称</label><input class="k-textbox" data-bind="value:name">
            <label for="type">类型</label><@utils.koDropDown data="$root.types" value="type"/>
            <label for="grade">等级</label><@utils.koDropDown data="$root.grades" value="grade"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
        <div class="grid-buttons">
            <a data-role="button" href="#/scmCarrier/create"> <i class="fa fa-plus"></i> 增加 </a>
            <#--<button type="button" data-bind="kendoButton:remove, enable:canRemove">
                <i class="fa fa-remove"></i> 删除
            </button>-->
            <button type="button" data-bind="kendoButton:modify, enable:canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
            <button type="button" data-bind="kendoButton:importQuote">
                <i class="fa fa-upload"></i> 导入报价
            </button>
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,toolbar:['excel','pdf'], editable:false, pageable: pageable, selectable: 'row', change:onChange}"></div>
</div>


<script>
    $(function () {
        var RoleGridModel = function () {
            var self = this;
            self.widget = ko.observable();
            self.dataSource = kendo.utils.createDataSource("carrierId", "/scmCarrierView/getDataSource");
            self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
            self.clearFilter = function () {
                self.code("");
                self.name("");
                self.grade("");
                self.type("");
                self.dataSource.filter([]);
            };

            self.columns = [{
                title: "序号",
                template: function (dataItem) {
                    return self.dataSource.indexOf(dataItem)+1;
                },
                width: 40
            },{
                field: "code",
                title: "编码"
            },{
                field: "name",
                title: "名称",
                template: "<a href='\\#/scmCarrier/detail/#= carrierId #' >#= name# </a>",
            },{
                field: "carrierTypeName",
                title: "类型"
            }, {
                field: "carrierGradeName",
                title: "等级"
            },  {
                field: "serviceStartDate",
                title: "服务开始时间",
                template: function (dataItem) {
                    if(dataItem.serviceStartDate)
                        return kendo.toString(kendo.parseDate(dataItem.serviceStartDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
                    else
                        return "";
                }
            },   {
                field: "serviceEndDate",
                title: "服务结束时间",
                template: function (dataItem) {
                    if(dataItem.serviceEndDate)
                        return kendo.toString(kendo.parseDate(dataItem.serviceEndDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
                    else
                        return "";
                }
            },   {
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

            self.canQuote = ko.computed(function () {
                return self.selected();
            });

            self.modify = function () {
                var url = "#/scmCarrier/modify/" + self.selected().carrierId;
                router.navigate(url);
            };

            self.importQuote = function () {
                var url = "scmCarrierQuote/importQuote";
                router.navigate(url);
            };

            self.remove = function () {
                if (!confirm("确定删除该承运商吗？")) return;
                var selectedId=self.selected().carrierId;
                var selectedItem=self.selected();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/scmCarrier/deleteById",
                    data: ko.toJSON(selectedId)
                }).done(function (result) {
                    if (result.success === true) {
                        self.dataSource.remove(selectedItem);
                        notify("删除成功！", "success");
                    }
                    else {
                        notify(result.message, "error");
                    }
                });

            };
            self.code = ko.observable();
            self.name = ko.observable();
            self.grade=ko.observable("");
            self.type=ko.observable("");
            self.grades=commonData.carrierGrade;
            self.types=commonData.carrierType;
            self.search = function () {
                var code = $.trim(self.code());
                var name = $.trim(self.name());
                var grade = $.trim(self.grade());
                var type = $.trim(self.type());
                var filters = new Array();
                if (code) {
                    filters.push({
                        field: "code",
                        operator: "contains",
                        value: code
                    });
                }
                if (name) {
                    filters.push({
                        field: "name",
                        operator: "contains",
                        value: name
                    });
                }
                if (grade) {
                    filters.push({
                        field: "grade",
                        operator: "eq",
                        value: grade
                    });
                }
                if (type) {
                    filters.push({
                        field: "type",
                        operator: "eq",
                        value: type
                    });
                }
                if (filters.length > 0)
                    self.dataSource.filter({
                        logic: "and",
                        filters: filters
                    });
                else
                    self.dataSource.filter([]);
            }
        };

        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>