<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/crmClientView"><i class="fa fa-users"></i> 客户档案</a></li>
    <li><a href="#/crmClientQuote/importQuote"><i class="fa fa-upload"></i> 导入客户报价</a></li>
</ol>
<div>
    <form id="upload-form" method="post" action="crmClientQuote/importData" class="form-horizontal" enctype="multipart/form-data">
        <div class="col-6">
            <div class="form-group">
                <label for="template">客户报价模板：</label>
                <a data-role="button" href="/sysFile/download/?id=C5676FCA4A9F40DC98CC0B08FBD0439D"> <i class="fa fa-download"></i> 下载模板 </a>
            </div>
            <div class="form-group">
                <label for="template">文件</label>
                <input type="file" name="file" id="file" required="required" data-role="upload" data-multiple="false" >
            </div>
        </div>
        <div class="form-buttons">
            <button type="submit" class="k-button k-button-icontext ">
                <i class="fa fa-file-excel-o"></i> 导入数据
            </button>

            <button type="button" data-action="goBack" class="k-button k-button-icontext">
                <i class="fa fa-close"></i> 取消
            </button>
        </div>
    </form>
</div>
<script>
    $(function(){
        $("#upload-form").validate();
    })
</script>