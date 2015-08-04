
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/${camelModelName}"><i class="fa fa-users"></i> ${camelModelName}</a></li>
    <li><a href="#/${camelModelName}/detail"><i class="fa fa-plus"></i> ${camelModelName}</a></li>
</ol>

<div>
    <form id="detail-form" method="post" data-bind="submit:save" class="form-horizontal">
        <div data-bind="with:model">
        <#list props as prop>
            <#if prop.type == "boolean" || prop.type == "java.lang.Boolean">
                <div class="form-group">
                    <input type="checkbox" name="${prop.name}" id="${prop.name}" readonly="true" data-bind="checked:${prop.name}">
                    <label for="${prop.name}">${prop.name}</label>
                </div>
            <#elseif prop.type == "java.sql.Timestamp" || prop.type=="java.sql.Date"|| prop.type=="java.util.Date">
                <div class="form-group">
                    <label for="${prop.name}">${prop.name}</label>
                    <input type="date" name="${prop.name}" id="${prop.name}" readonly="true" data-bind="kendoDatePicker:${prop.name}" >
                </div>
            <#elseif (prop.type == "java.lang.String" || prop.type == "java.util.UUID"|| prop.type =="java.lang.Long") && !prop.isJpaId>
                <div class="form-group">
                    <label for="${prop.name}">${prop.name}</label>
                    <input type="text" name="${prop.name}" id="${prop.name}" readonly="true" data-bind="value:${prop.name}" class="k-input k-textbox" >
                </div>
            <#elseif  prop.type =="java.lang.Double"|| prop.type == "java.math.BigDecimal" || prop.type == "java.math.BigInteger">
                <div class="form-group">
                    <label for="${prop.name}">${prop.name}</label>
                    <input type="number" name="${prop.name}" id="${prop.name}" readonly="true" data-bind="value:${prop.name}" class="k-input k-textbox" >
                </div>
            <#elseif  prop.type =="java.lang.Integer">
                <div class="form-group">
                    <label for="${prop.name}">${prop.name}</label>
                    <input type="text" name="${prop.name}" id="${prop.name}" readonly="true" data-bind="value:${prop.name}" class="k-input k-textbox" >
                </div>
            </#if>
        </#list>
            <div class="form-buttons">

                <button type="button" data-action="goBack" class="k-button k-button-icontext">
                    <i class="fa fa-close"></i> 取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    function DetailModel() {
        var self = this;
        self.model = ${camelModelName}Json;
    }
    $(function () {
        $("#detail-form").validate();
        var viewModel = new DetailModel();
        ko.applyBindings(viewModel, document.getElementById("detail-form"));
    });
</script>