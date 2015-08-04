<#macro koDropDown data value >
<input id="${value}" readonly="true" name="${value}" required="required"  data-bind="kendoDropDownList: { data: ${data}, value: ${value}, dataTextField: 'text', dataValueField: 'value' }" />
</#macro>
<#macro koTemplateComboBox data value textField valueField headerTemplate template >
<input id="${value}" readonly="true" name="${value}" data-bind="kendoComboBox: { data: ${data}, value: ${value}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}', template: ${template}, headerTemplate:${headerTemplate }}" />
</#macro>
<#macro koComboBox data value textField valueField>
<input id="${value}" readonly="true" name="${value}" data-bind="kendoComboBox: { data: ${data}, value: ${value}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}' }" />
</#macro>
<#macro koCascadeComboBox data value textField valueField cascadeFrom>
<input id="${value}" readonly="true" name="${value}" data-bind="kendoComboBox: { data: ${data}, value: ${value},cascadeFrom:'${cascadeFrom}', dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}' }" />
</#macro>
<#macro koMultiSelect data value textField valueField>
<select id="${value}" readonly="true" style="width:200px" name="${value}" data-bind="kendoMultiSelect: { data: ${data}, value: ${value}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}' }" />
</#macro>