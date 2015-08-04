<#import "/utils/readOnlyDropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/scmCarrierView"><i class="fa fa-users"></i> 承运商档案</a></li>
    <li><a href="#/scmCarrier/detail/${scmCarrier.carrierId}"><i class="fa fa-plus"></i> 承运商档案详情</a></li>
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
    <#include "/views/scm/carrier/readonly.ftl">
    </div>
    <div>
    <#include "/views/scm/carrier/modifyFile.ftl">
    </div>
    <div>
    <#include "/views/scm/carrierLine/index.ftl">
    </div>
    <div id="quote">
    <#include "/views/scm/carrierQuote/index.ftl">
    </div>
</div>
