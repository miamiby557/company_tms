<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/scmCarrier"><i class="fa fa-users"></i> 承运商档案</a></li>
    <li><a href="#/scmCarrier/createFile/${scmCarrier.carrierId}"><i class="fa fa-plus"></i> 承运商附件</a></li>
</ol>

<div>
    <form id="file-form" method="post" action="scmCarrier/upload/${scmCarrier.carrierId}" class="form-horizontal" enctype="multipart/form-data">
        <div class="form-group">
                <label for="files">证件资料</label>
                <input name="files"  id="files" type="file" data-role="upload" required="required">
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
        router.navigate("#/scmCarrier");
    }
</script>
