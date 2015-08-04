<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/scmCarrierView"><i class="fa fa-users"></i> 承运商档案</a></li>
    <li><a href="#/scmCarrier/modify/${scmCarrier.carrierId}"><i class="fa fa-plus"></i> 修改承运商档案</a></li>
</ol>

<div id="tabstrip" data-role="tabstrip">
    <ul>
        <li class="k-state-active">
            基本信息
        </li>
        <li>
            附件信息
        </li>
        <li>
            线路
        </li>
        <li>
            报价
        </li>
    </ul>
    <div>
    <#include "/views/scm/carrier/edit.ftl">
    </div>
    <div>
    <#include "/views/scm/carrier/modifyFile.ftl">
    </div>
    <div>
    <#include "/views/scm/carrierLine/index.ftl">
    </div>
    <div >
    <#include "/views/scm/carrierQuote/index.ftl">
    </div>
</div>
