<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/report/reportIndex"><i class="fa fa-users"></i> 动态报表</a></li>
</ol>
<div>
    <form id="file-form" method="post" data-bind="submit:submitForm" class="form-horizontal" enctype="multipart/form-data">
        <div class="form-group">
            <label for="contractDocumentId">报表模板</label>
            <input name="file"  id="file" type="file" data-role="upload" data-multiple="false" required="required" >
            <label for="reportName">报表名称</label><input class="k-textbox" data-bind="value:reportName">
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 确定
            </button>
        </div>
        <div id="file-grid" style="width: 500px;" data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,editable:false}"></div>
    </form>
</div>
<script>

    function removeFile(e){
        if(confirm("确认删除吗?")){
            var row = $(e.currentTarget).closest("tr");
            var grid = $("#file-grid").data("kendoGrid");
            var dataItem = grid.dataItem(row);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/sysFile/delete/"+dataItem.fileId
            }).done(function (result) {
                if(result.success==true){
                    grid.removeRow(row);
                }
                else{
                    notify(result.message, "error");
                }
            });
        }
    }
    function fileModel() {
        var self = this;
        self.model = {};
        self.widget = ko.observable();
        self.reportName=ko.observable();
        self.dataSource = kendo.utils.createDataSource("fileId", "/sysFile/getDataSource");
        var filters= [];
        filters.push({
            field: "uploadType",
            operator: "eq",
            value: 1
        });
        self.dataSource.filter({
            logic: "and",
            filters: filters
        });
        self.columns = [{
            field: "fileCode",
            title: "报表名称",
            template:"<a href='/report/pdfReport/?id=#=fileId#' target='_blank' >#=fileCode?fileCode :''#</a>",
            width:100,
        },{
            field:"fileName",
            title:"模板名称",
            width:100
        },{command: [{text:"删除",click:removeFile}], width: 60}];

        self.submitForm=function(){
            if(self.reportName()) {
                var fd = new FormData(document.getElementById("file-form"));
                $.ajax({
                    url: "report/upload/" + self.reportName(),
                    type: "POST",
                    data: fd,
                    enctype: 'multipart/form-data',
                    processData: false,  // tell jQuery not to process the data
                    contentType: false   // tell jQuery not to set contentType
                }).done(function (result) {
                    if (result.success) {
                        self.dataSource.filter({
                            logic: "and",
                            filters: [{
                                field: "uploadType",
                                operator: "eq",
                                value: 1
                            }]
                        });
                        notify("上传成功", "success");
                    } else {
                        notify(result.message,"error");
                    }

                });
            }
            else{notify("输入报表名称","error")}
            return false;
        }
    }
    $(function () {
        $("#file-form").validate();
        var viewModel = new fileModel();
        ko.applyBindings(viewModel, document.getElementById("file-form"));
    });
</script>