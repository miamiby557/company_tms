<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新易泰运输管理系统</title>
    <link rel="stylesheet" href="/resources/styles/normalize.css"/>
    <link rel="stylesheet" href="/resources/styles/font-awesome.min.css"/>
    <link rel="stylesheet" href="/resources/styles/kendo/kendo.common.min.css"/>
    <link rel="stylesheet" href="/resources/styles/kendo/kendo.silver.min.css"/>
    <link rel="stylesheet" href="/resources/styles/kendo/kendo.dataviz.min.css"/>
    <link rel="stylesheet" href="/resources/styles/kendo/kendo.dataviz.silver.min.css"/>
    <link rel="stylesheet" href="/resources/styles/main.css"/>
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/jquery.validate.min.js"></script>
    <script src="/resources/js/jquery.validate.messages_zh.min.js"></script>
    <script src="/resources/js/kendo/kendo.all.min.js"></script>
    <script src="/resources/js/kendo/cultures/kendo.culture.zh-CN.min.js"></script>
    <script src="/resources/js/kendo/messages/kendo.messages.zh-CN.min.js"></script>
    <script src="/resources/js/knockout.js"></script>
    <script src="/resources/js/knockout.mapping.js"></script>
    <script src="/resources/js/knockout-kendo.min.js"></script>
    <script src="/resources/js/jszip.min.js"></script>
    <script src="/resources/js/common.js"></script>
    <script src="/resources/js/dropdownTreeView.js"></script>
</head>
<body>

<div id="page-wrap" data-role="splitter"
     data-panes="[{ collapsible: false, resizable: false, size: '60px' }, { collapsible: false }]"
     data-orientation="vertical" style="width: 100%;height:100%;">
    <div id="page-top">
        <div class="logo"><a href="/" target="_top"><img src="/resources/images/lnet_app_logo_inverse.png"></a></div>
        <div class="userinfo">
            <a href="#/sysUser/setting"><i class="fa fa-user"></i> ${currentUser.principal.username}</a>
            <a href="/logout"><i class="fa fa-arrow-right"></i> 退出</a>
        </div>
    </div>
    <div id="page-middle">
        <div data-role="splitter"
             data-panes="[{ collapsible: true, size:'150px', min:'120px', max: '400px' },{ collapsible: false }]"
             style="height: 100%; width: 100%;">
            <div id="page-left">
                <div class="sidebar">
                   <#include "/utils/navbar.ftl">
                </div>
            </div>
            <div id="page-body" >
                <div style="min-width: 1100px;">
                    <div id="breadcrumbs-wrap" ></div>
                    <div id="page-view" ></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>