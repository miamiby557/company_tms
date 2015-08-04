<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/crmClientView"><i class="fa fa-users"></i> 客户档案</a></li>
</ol>

<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <label for="code">编码</label><input class="k-textbox" data-bind="value:code">
            <label for="name">名称</label><input class="k-textbox" data-bind="value:name">
            <label for="grade">等级</label><@utils.koDropDown data="$root.grades" value="grade"/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索 </button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空 </button>
        </div>
        <div class="grid-buttons">
            <a data-role="button" href="#/crmClient/create"> <i class="fa fa-plus"></i> 增加 </a>
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
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true,toolbar:['excel','pdf'], groupable: true, editable:false, pageable: pageable, selectable: 'row', change:onChange}"></div>
</div>


<script>


    var RoleGridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("clientId", "/crmClientView/getDataSource");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.clearFilter = function () {
            self.code("");
            self.name("");
            self.grade("");
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
            title: "客户编号",
            width:120
        }, {
            field: "name",
            title: "客户名称",
            template: "<a href='\\#/crmClient/details/#= clientId #' >#= name# </a>",
            width:120
        },{
            field: "fullName",
            title: "客户全名",
            width:200
        }, {
            field: "clientGradeName",
            title: "客户级别",
            width:120
        },{
            field: "businessModelname",
            title: "商业模式",width: 100
        },{
            field: "serviceStartDate",
            title: "开始服务时间",width:100,
            template: function (dataItem) {
                return kendo.toString(kendo.parseDate(dataItem.serviceStartDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd")
            }
        }, {
            field: "serviceEndDate",
            title: "结束服务时间",width:100,
            template: function (dataItem) {
                return kendo.toString(kendo.parseDate(dataItem.serviceEndDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd")
            }
        }/*,{
            field: "contractDocumentId",
            title: "合同文档",
            template:"<a href='/sysFile/download/?id=#=contractDocumentId#' target='_blank'>#=contractDocumentName?contractDocumentName :''#</a>",
            width:120
        }*//*{
                field: "contactMan",
                title: "联系人",
                width:120
            },{
                field: "contactPhone",
                title: "联系电话",
                width:120
            },{
                field: "contactAddress",
                title: "联系地址",
                width:120
            }, {
                field: "provinceName",
                title: "省",
                width:100
            },  {
                field: "cityName",
                title: "市",
                width:100
            },{
                field: "city",
                title: "区/县",
                width:100
            }, {
                title: "备注",
                field: "remark",width: 100
            }*/];
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


        self.importQuote = function () {
            var url = "crmClientQuote/importQuote";
            router.navigate(url);
            /*modal("导入客户报价", url, {width:600, height: 400,close:function(){
                self.dataSource.query();
            }});*/
        };
        self.modify = function () {
            var url = "#/crmClient/modify/" + self.selected().clientId;
            router.navigate(url);
        };
        self.quote = function () {
            var url = "#/crmClientQuote/quote/" +  self.selected().clientId;
            router.navigate(url);
        };
        self.remove = function () {
            if (!confirm("确定删除该客户吗？")) return;
            var selectedId=self.selected().clientId;
            var selectedItem=self.selected();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/crmClient/deleteById",
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

        self.code = ko.observable();
        self.name = ko.observable();
        self.grade=ko.observable("");
        self.grades=commonData.clientGrade;
        self.search = function () {
            var code = $.trim(self.code());
            var name = $.trim(self.name());
            var grade = $.trim(self.grade());
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
            if (filters.length > 0)
                self.dataSource.filter({
                    logic: "and",
                    filters: filters
                });
            else
                self.dataSource.filter([]);
        }
    };
    $(function () {

        var viewModel = new RoleGridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });

</script>