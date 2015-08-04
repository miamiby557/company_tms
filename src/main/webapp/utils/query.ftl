<#macro koText field label=field id=field name=field attributes...>
<label for="${id}">${label}</label>
<input type="text" class="k-input k-textbox" name="${name}" id="${id}" data-bind="value:${field}"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
        />
</#macro>
<#macro koNumberBox field label=field id=field name=field attributes...>
<label for="${id}">${label}</label>
<input type="number" class="k-input k-textbox" name="${name}" id="${id}" data-bind="value:${field}"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
        />
</#macro>
<#macro koDatePicker field label id=field name=field attributes...>
    <#if label><label for="${id}">${label}</label></#if>
<input type="number" name="${name}" id="${id}" data-bind="kendoDatePicker:${field}"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
        />
</#macro>

<#macro koDateTimePicker field label id=field name=field attributes...>
    <#if label><label for="${id}">${label}</label></#if>
<input type="number" name="${name}" id="${id}" data-bind="kendoDateTimePicker:${field}"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
        />
</#macro>

<#macro koDropDown field data label=field id=field name=field textField="text" valueField="value" attributes...>
<label for="${id}">${label}</label>
<input id="${id}" name="${name}"
       data-bind="kendoDropDownList: { data: ${data}, value: ${field}, dataTextField: '${textField}', dataValueField: '${valueField}', optionLabel:'请选择...' }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
        />
</#macro>
<#macro kendoDropDownTreeView field data treeView loadUrl label=field id=field name=field  textField="text" valueField="value"  attributes...>
<label for="${id}">${label}</label>
<input id="${id}" name="${name}"
       data-bind="kendoDropDownTreeView: { data: ${data}, value: ${field},treeView:${treeView},loadUrl:${loadUrl} textField: '${textField}', valueField: '${valueField}' }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
        />
</#macro>

<#macro koComboBox field data textField valueField label=field id=field name=field attributes...>
<label for="${id}">${label}</label>
<input id="${id}" name="${name}"
       data-bind="kendoComboBox: { data: ${data}, value: ${field}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}' }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
        />
</#macro>
