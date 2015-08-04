<#macro koText field label=field id=field name=field attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input type="text" class="k-input k-textbox" name="${name}" id="${id}" data-bind="value:${field}"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>


<#macro koNumberBox field label=field id=field name=field attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input type="number" class="k-input k-textbox" name="${name}" id="${id}" data-bind="value:${field}"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>
<#macro koIntegerBox field label=field id=field name=field attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input type="digits" class="k-input k-textbox" name="${name}" id="${id}" data-bind="value:${field}"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>



<#macro koTextarea field label=field id=field name=field attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <textarea id="${id}" name="${name}" data-bind="value:${field}" class="k-textbox"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>

<#macro koCheckBox field label=field id=field name=field attributes...>
<div class="form-group">
    <input type="checkbox" id="${id}" name="${name}" data-bind="checked:${field}"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
    <label for="${id}">${label}</label>
</div>
</#macro>

<#macro koMultiSelect field data textField valueField label=field id=field name=field dataEvent=false attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input id="${id}" name="${name}"
           data-bind="kendoMultiSelect: { data: ${data}, value: ${field}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}'
    <#if dataEvent>,${dataEvent}</#if>
            }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>

<#macro koDropDown field data label=field id=field name=field textField="text" valueField="value" dataEvent=false attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input id="${id}" name="${name}"
           data-bind="kendoDropDownList: { data: ${data}, value: ${field}, dataTextField: '${textField}', dataValueField: '${valueField}', optionLabel:'请选择...'
    <#if dataEvent>,${dataEvent}</#if>
            }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>

<#macro koDatePicker field label=field id=field name=field dataEvent=false attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input type="number" name="${name}" id="${id}" data-bind="kendoDatePicker:{value:${field}
    <#if dataEvent>,${dataEvent}</#if>
            }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>

<#macro koDateTimePicker field label=field id=field name=field dataEvent=false attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input type="number" name="${name}" id="${id}" data-bind="kendoDateTimePicker:{value:${field}
    <#if dataEvent>,${dataEvent}</#if>
            }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>

<#macro kendoDropDownTreeView field textField valueField treeView loadUrl label=field id=field name=field dataEvent=false attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input id="${id}" name="${name}"
           data-bind="kendoDropDownTreeView: {value: ${field},treeView:${treeView},loadUrl:${loadUrl}, textField: '${textField}', valueField: '${valueField}'
            <#if dataEvent>,${dataEvent}</#if>
           }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>

<#macro koComboBox field data textField valueField label=field id=field name=field dataEvent=false attributes...>
<div class="form-group">
    <label for="${id}">${label}</label>
    <input id="${id}" name="${name}"
           data-bind="kendoComboBox: { data: ${data}, value: ${field}, dataTextField: '${textField}',filter: 'contains', dataValueField: '${valueField}'
    <#if dataEvent>,${dataEvent}</#if>
            }"
    <#list attributes?keys as attr>
    ${attr}="${attributes[attr]?html}"
    </#list>
    />
</div>
</#macro>

<#macro koStatusPanel field data label=field  textField="text" valueField="value" >
<div class="form-group">
    <label>${label}</label>
    <ul data-bind="foreach:${data}" class="statusPanel">
        <li data-bind="text:${textField}, css:{active:${valueField} == $parent.${field}(),cancel:${valueField}<0}"></li>
    </ul>
</div>
</#macro>

