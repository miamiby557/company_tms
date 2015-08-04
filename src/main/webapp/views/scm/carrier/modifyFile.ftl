<div>
    <form id="file-form" method="post" data-bind="submit:submitForm" class="form-horizontal" enctype="multipart/form-data">
        <div class="form-group">
                <label for="contractDocumentId">证件资料</label>
                <input name="files"  id="files" type="file" data-role="upload" required="required">
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
        if(confirm("确定要删除吗？")){
            var row = $(e.currentTarget).closest("tr");
            var grid = $("#file-grid").data("kendoGrid");
            var dataItem = grid.dataItem(row);
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/scmCarrier/deleteFile?fileId="+dataItem.fileId+"&carrierId=${scmCarrier.carrierId}"
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
        var fileIds=["00000000-0000-0000-0000-000000000000"];
        var fileId ="${scmCarrier.certificateAffixId}";//第一次新增的无附件 ，则查询不到
        if(fileId){
            fileIds =fileId.substring(0,fileId.lastIndexOf(",")).split(",");
        }
        self.dataSource.filter({
            logic: "and",
            filters: [{
                field: "fileId",
                operator: "in",
                value: fileIds
            }]
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
                url: "scmCarrier/upload/${scmCarrier.carrierId}",
                type: "POST",
                data: fd,
                enctype: 'multipart/form-data',
                processData: false,  // tell jQuery not to process the data
                contentType: false   // tell jQuery not to set contentType
            }).done(function(result) {
                if(result.success){
                    for(var i =0;i<result.content.length;i++){
                        fileIds.push(result.content[i]);
                    }
                    self.dataSource.filter({
                        logic: "and",
                        filters: [{
                            field: "fileId",
                            operator: "in",
                            value: fileIds
                        }]
                    });
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
