<#macro koDropDown data value required=false  readonly=false>
<input id="${value}" name="${value}" data-bind="kendoDropDownList: { data: ${data}, value: ${value}, dataTextField: 'text', dataValueField: 'value', optionLabel:'请选择...' }"
      <#if required>required="required"</#if>  <#if readonly>readonly="readonly"</#if>/>
</#macro>
<#macro koTemplateComboBox data value textField valueField headerTemplate template >
<input id="${value}" name="${value}" data-bind="kendoComboBox: { data: ${data}, value: ${value}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}', template: ${template}, headerTemplate:${headerTemplate }}" />
</#macro>
<#macro koComboBox data value textField valueField required=false readonly=false>
<input id="${value}" name="${value}"  data-bind="kendoComboBox: { data: ${data}, value: ${value}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}' }"<#if required>required="required"</#if> <#if readonly>readonly="readonly"</#if>/>
</#macro>
<#macro koCascadeComboBox data value textField valueField cascadeFrom>
<input id="${value}" name="${value}" data-bind="kendoComboBox: { data: ${data}, value: ${value},cascadeFrom:'${cascadeFrom}', dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}' }" />
</#macro>
<#macro koMultiSelect data value textField valueField>
<select id="${value}" style="width:200px" name="${value}" data-bind="kendoMultiSelect: { data: ${data}, value: ${value}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}' }" />
</#macro>