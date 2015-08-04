<#import "/utils/dropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/crmClientView"><i class="fa fa-users"></i> 客户档案</a></li>
    <li><a href="#/crmClient/modify/${crmClient.clientId}"><i class="fa fa-edit"></i> 修改客户档案</a></li>
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
        <li>
            订单类型
        </li>
        <li>
            产品类型
        </li>
        <li>
            提货地址
        </li>
        <li>
            重货类型
        </li>
    </ul>
    <div>
    <#include "/views/crm/crmClient/edit.ftl">
    </div>
    <div>
    <#include "/views/crm/crmClient/modifyFile.ftl">
    </div>
    <div>
    <#include "/views/crm/crmClientLine/index.ftl">
    </div>
    <div id="quote">
    <#include "/views/crm/crmClientQuote/index.ftl">
    </div>
    <div>
    <#include  "/views/crm/crmClientOrderType/index.ftl">
    </div>
    <div>
    <#include "/views/crm/productType/index.ftl">
    </div>
    <div>
        <#include  "/views/crm/crmClientPickupAddress/index.ftl">
    </div>
    <div>
    <#include  "/views/crm/heavyGoodsType/index.ftl">
    </div>
</div>
