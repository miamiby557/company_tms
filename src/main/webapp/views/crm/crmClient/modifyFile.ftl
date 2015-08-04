<div>
    <form id="file-form" method="post" data-bind="submit:submitForm" class="form-horizontal" enctype="multipart/form-data">
        <div class="form-group">
                <label for="contractDocumentId">合同文档</label>
                <input name="file"  id="file" type="file" data-role="upload" data-multiple="false" required="required" >
        </div>
        <div id="file-grid" style="width: 500px;" data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,editable:false}"></div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 保存
            </button>
        </div>
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
        self.dataSource = kendo.utils.createDataSource("fileId", "/sysFile/getDataSource");
        var fileId ="${crmClient.contractDocumentId}"||"00000000-0000-0000-0000-000000000000";//第一次新增的无附件 ，则查询不到
        var filters= [];
        filters.push({
            field: "fileId",
            operator: "eq",
            value: fileId
        });
        self.dataSource.filter({
            logic: "and",
            filters: filters
        });
        self.columns = [{
            field: "fileName",
            title: "文件名称",
            template:"<a href='/sysFile/download/?id=#=fileId#' >#=fileName?fileName :''#</a>",
            width:200,
        }, {command: [{text:"删除",click:removeFile}], width: 60}];

        self.submitForm=function(){
            var fd = new FormData(document.getElementById("file-form"));
            $.ajax({
                url: "crmClient/upload/${crmClient.clientId}",
                type: "POST",
                data: fd,
                enctype: 'multipart/form-data',
                processData: false,  // tell jQuery not to process the data
                contentType: false   // tell jQuery not to set contentType
            }).done(function(result) {
                if(result.success){
                    self.dataSource.filter({
                        logic: "and",
                        filters: [{
                            field: "fileId",
                            operator: "eq",
                            value: result.content
                        }]
                    });
                    var file = $("#files");
                    file.after(file.clone().val(""));
                    file.remove();
                    notify("上传成功", "success");
                }else{
                    notify("上传失败", "error");
                }

            });
            return false;
        }
    }
    $(function () {
        $("#file-form").validate();
        var viewModel = new fileModel();
        ko.applyBindings(viewModel, document.getElementById("file-form"));
    });
</script>
