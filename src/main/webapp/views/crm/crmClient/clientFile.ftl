<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/crmClientView"><i class="fa fa-users"></i> 客户档案</a></li>
    <li><a href="#/crmClient/createFile/${crmClient.clientId}"><i class="fa fa-plus"></i> 客户附件</a></li>
</ol>

<div>
    <form id="file-form" method="post" action="crmClient/upload/${crmClient.clientId}" class="form-horizontal" enctype="multipart/form-data">
        <div class="form-group">
                <label for="contractDocumentId">合同文档</label>
                <input name="files"  id="files" type="file" data-role="upload" data-multiple="false" required="required" >
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext k-primary ">
                <i class="fa fa-save"></i> 保存
            </button>

            <button type="button" onclick="back();" class="k-button k-button-icontext">
                <i class="fa fa-close"></i> 取消
            </button>
        </div>
    </form>
</div>
<script>
    $(function(){
        $("#file-form").validate();
    })
    function back(){
        router.navigate("#/crmClient");
    }
</script>