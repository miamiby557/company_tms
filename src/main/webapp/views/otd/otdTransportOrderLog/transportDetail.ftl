<#import "/utils/readOnlyDropdown.ftl" as utils >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/otdOrderLogListView"><i class="fa fa-users"></i> 信息跟踪</a></li>
    <li><a href="#/otdTransportOrderLog/detail/${otdTransportOrder.transportOrderId}"><i class="fa fa-plus"></i>
        运输订单信息跟踪</a>
    </li>
</ol>

<div id="transport-detail">
    <div id="tabstrip" data-role="tabstrip">
        <ul>
            <li class="k-state-active">
                基本信息
            </li>
            <li>
                签收信息
            </li>
            <li>
                操作日志
            </li>
        </ul>
        <#include "/views/otd/otdTransportOrderLog/formDetail.ftl">
        <div>
        <#include "/views/otd/sign/create.ftl">
        </div>
        <div>
            <#include "/views/otd/otdTransportOrderLog/logInfo.ftl">
        </div>
    </div>

    <div style="margin-top: 20px">
        <button type="button" data-action="goBack" class="k-button k-button-icontext">
            <i class="fa fa-close"></i> 返回
        </button>
    </div>
</div>