<#macro loginStyle>
<style type="text/css">
    html, body {
        height: auto;
    }

    #login-wrap {
        width: 450px;
        margin: 100px auto;
        margin-bottom: 0;
        padding: 40px;
        border: 1px solid rgba(20, 53, 80, 0.05);
        background-color: #fefefe;
        border-color: #fefefe;
        box-shadow: 0 1px 2px 1px rgba(0, 0, 0, .10), 0 2px 5px rgba(0, 0, 0, .10);
        color: #444;
        font-weight: normal;
    }

    .login-logo {
        background: #993388;
        margin: -20px -20px 20px -20px;
        padding: 10px 20px;
        text-align: center;
    }

    #login-form ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }

    #login-form input.k-textbox {
        width: 200px;
    }

    #login-form input.error {
        border-color: orangered;
    }

    #login-form li {
        margin: 10px 0;
    }

    #login-form li.rememberMe {
        padding-left: 66px;
    }

    #login-form label {
        display: inline-block;
        padding-right: 3px;
        padding-top: 3px;
        min-width: 60px;
    }

    #login-form label.error {
        margin-left: 6px;
        color: orangered;
    }

    .actions {
        padding-left: 66px;
        padding-top: 10px;
    }

    .message {
        margin-left: 66px;
        color: orangered;
    }
</style>
</#macro>

<#macro loginForm>
<div id="login-wrap">
    <div class="login-logo">
        <img src="/resources/images/lnet_app_logo_inverse.png" alt="LNET">
    </div>
    <form id="login-form" method="post" action="/login">
        <ul>
            <li>
                <label for="username">用户名</label>
                <input type="text" class="k-textbox" name="username" id="username" required="required" minlength="3" maxlength="50" value="${username}"/>
            </li>
            <li>
                <label for="password">密码</label>
                <input type="password" class="k-textbox" name="password" id="password" required="required" minlength="6" maxlength="50"/>
            </li>
            <li class="rememberMe">
                <input type="checkbox" name="rememberMe" id="rememberMe"> <label for="rememberMe">记住登录</label>
            </li>
            <li class="actions">
                <button type="submit" class="k-button k-primary"> 登 录</button>
            </li>
        </ul>
    </form>
    <#if message??>
        <div class="message">
        ${message}
        </div>
    </#if>
</div>
<script>
    $("#login-form").validate();
</script>
</#macro>

<#if isAjaxRequest>
    <@loginStyle />
    <@loginForm />
<#else>
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
    <link rel="stylesheet" href="/resources/styles/main.css"/>
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/jquery.validate.min.js"></script>
    <script src="/resources/js/jquery.validate.messages_zh.min.js"></script>
    <script src="/resources/js/kendo/kendo.all.min.js"></script>
    <script src="/resources/js/kendo/cultures/kendo.culture.zh-CN.min.js"></script>
    <script src="/resources/js/kendo/messages/kendo.messages.zh-CN.min.js"></script>
    <script src="/resources/js/knockout.js"></script>
    <script src="/resources/js/knockout-kendo.min.js"></script>
    <@loginStyle />
</head>
<body>
    <@loginForm />
</body>
</html>
</#if>
