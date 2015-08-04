<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/test"><i class="fa fa-code"></i> 测试</a></li>
</ol>

<script>
    ko.components.register('cityselector', {
        viewModel: function (params) {
            var self = this;
            self.selectedProvince = ko.observable(params.province || null);
            self.selectedCity = ko.observable(params.city || null);

            self.cities = ko.observableArray();
            self.provinces = commonData.provinces;

            var subs = this.selectedProvince.subscribe(function (newValue) {
                self.cities.removeAll();
                if (newValue) {
                    $.ajax("/commonData/regions?parentId=" + newValue).done(function (result) {
                        $.each(result, function (i, item) {
                            self.cities.push(item);
                        });
                    })
                }
            });
        },
        template: "<select data-role=\"dropdownlist\" data-bind=\"options: provinces, value:selectedProvince, optionsValue: \'regionId\', optionsText:\'name\', optionsCaption: \'请选择...\'\"></select> " +
        "<select data-bind=\"visible: cities().length > 0, options: cities, value:selectedCity, optionsValue: \'regionId\', optionsText:\'name\', optionsCaption: \'请选择...\'\" ></select>"
    });

    ko.bindingHandlers.regionPicker = {
        init: function(element, valueAccessor, allBindings, viewModel, bindingContext) {
            $(element).kendoDropDownList();
        },
        update: function(element, valueAccessor, allBindings, viewModel, bindingContext) {
            // This will be called once when the binding is first applied to an element,
            // and again whenever any observables/computeds that are accessed change
            // Update the DOM element based on the supplied values here.
        }
    };
</script>

<div class="box">

    <select data-bind="regionPicker:{}"></select>

</div>


<div class="box">
    <cityselector params="{}">
    </cityselector>

</div>

<div class="box">
    <span style="" class="k-widget k-combobox k-header">
        <span class="k-dropdown-warp k-state-default" unselectable="on" tabindex="-1">
            <input type="text" autocomplete="off" class="k-input" style="width: 100%;">
            <span class="k-select">
                <span class="k-icon k-i-arrow-s" role="button">select</span>
            </span>
        </span>


        </span>

    <div style="width: 300px">
        <div data-role="tabstrip" id="tabstrip">
            <ul>
                <li class="k-state-active">
                    省
                </li>
                <li>
                    市
                </li>
                <li>
                    区
                </li>
            </ul>
            <div>
                <div class="areaSelector-province"></div>
            </div>
            <div>
                <div class="areaSelector-city"></div>
            </div>
            <div>
                <div class="areaSelector-district"></div>
            </div>
        </div>
    </div>

</div>


<div class="box">
    <form action="/testUpload" method="post" enctype="multipart/form-data">
        <input data-role="upload" name="files" id="files" type="file" multiple="multiple"/>
        <button type="submit">submit</button>
    </form>
</div>

<script>


    $(function () {
        ko.applyBindings({});
    });

</script>