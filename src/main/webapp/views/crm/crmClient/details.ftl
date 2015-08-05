<#import "/utils/readOnlyDropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/crmClientView"><i class="fa fa-users"></i> 客户档案</a></li>
    <li><a href="#/crmClient/details/${crmClient.clientId}"><i class="fa fa-edit"></i> 客户档案详情</a></li>
</ol>

<div id="tabstrip" data-role="tabstrip">
    <ul>
        <li class="k-state-active" id="li1">
            基本信息
        </li>
        <li id="li2">
            附件信息
        </li>
        <li id="li3">
            线路
        </li>
        <li id="li4">
            报价
        </li>
        <li id="li5">
            订单类型
        </li>
        <li id="li6">
            产品类型
        </li>
        <li>
            提货地址
        </li>
        <li>
            收货地址
        </li>
        <li>
            重货类型
        </li>
    </ul>
    <div>
    <#include "/views/crm/crmClient/readonly.ftl">
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