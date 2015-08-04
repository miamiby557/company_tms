var DropDownTreeView = kendo.ui.Widget.extend({
    _uid: null,
    _treeView: null,
    _dropdown: null,
    _v: null,

    init: function (element, options) {
        var self = this;
        kendo.ui.Widget.fn.init.call(self, element, options);
        self._uid = new Date().getTime();
        $(element).val(options.value());
        //$(element).trigger("change");
        $(element).attr("type","hidden");//隐藏当前input  操作新生成的div  input
        $(element).before(kendo.format("<input id='extDropDown{0}' class='k-ext-dropdown'/>", self._uid));
        $(element).before(kendo.format("<div id='extTreeView{0}' class='k-ext-treeview' style='z-index:1;'/>", self._uid));
        // Create the dropdown.
        self._dropdown = $(kendo.format("#extDropDown{0}", self._uid)).kendoDropDownList({
            dataSource: [{text: "", value: "" }],
            dataTextField: "text"||options.textField,
            dataValueField:"value"|| options.valueField,
            value:""||$(element).val(),
            open: function (e) {
                e.preventDefault();
                if(!$(element).attr("readonly")){
                    if (!$treeViewRootElem.hasClass("k-custom-visible")) {
                        $treeViewRootElem.css({
                            "top": $dropdownRootElem.position().top + $dropdownRootElem.height(),
                            "left": $dropdownRootElem.position().left
                        });
                        $treeViewRootElem.slideToggle('fast', function () {
                            self._dropdown.close();
                            $treeViewRootElem.addClass("k-custom-visible");
                        });
                    }
                }
            }
        }).data("kendoDropDownList");

        if (options.dropDownWidth) {
            self._dropdown._inputWrapper.width(options.dropDownWidth);
        }
        var $dropdownRootElem = $(self._dropdown.element).closest("span.k-dropdown");
        self._treeView = $(kendo.format("#extTreeView{0}", self._uid)).kendoTreeView(options.treeView).data("kendoTreeView");
        self._treeView.bind("select", function (e) {
            $dropdownRootElem.find("span.k-input").text($(e.node).children("div").text());
            $treeViewRootElem.slideToggle('fast', function () {
                $treeViewRootElem.removeClass("k-custom-visible");
                self.trigger("select", e);
                if(options.change){
                    $dropdownRootElem.trigger("change",options.change(e));
                }
            });
            var treeValue = this.dataItem(e.node);
            self._v = treeValue[options.valueField];
            $(element).val(self._v);
        });
        var $treeViewRootElem = $(self._treeView.element).closest("div.k-treeview");
        $treeViewRootElem.css({
            "border"   : "1px solid #dbdbdb",
            "display"  : "none",
            "position" : "absolute",
            "background-color": self._dropdown.list.css("background-color")
        });

        $(document).click(function (e) {
            if ($(e.target).closest("div.k-treeview").length === 0) {
                if ($treeViewRootElem.hasClass("k-custom-visible")) {
                    $treeViewRootElem.slideToggle('fast', function () {
                        $treeViewRootElem.removeClass("k-custom-visible");
                    });
                }
            }
        });
        var value = $(element).val();
        if(value && options.loadUrl){
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: options.loadUrl+value,
                async:false
            }).done(function (result) {
                if (result) {
                    $dropdownRootElem.find("span.k-input").text(result.name);
                }else{
                    $dropdownRootElem.find("span.k-input").text("");
                }
            });
        }
    },
    value: function(v){
        var self = this;
        if(v !== undefined){
            var n = self._treeView.dataSource.get(v);
            var selectNode = self._treeView.findByUid(n.uid);
            self._treeView.trigger('select',{node: selectNode});
            var $treeViewRootElem = $(self._treeView.element).closest("input.k-treeview");
            $treeViewRootElem.hide();
        }
        else{
            return self._v;
        }
    },

    dropDownList: function () {
        return this._dropdown;
    },

    treeView: function () {
        return this._treeView;
    },
    refresh: function () {
        return this._treeView.dataSource.read();
    },
    options: {
        name: "DropDownTreeView"
    }
});
kendo.ui.plugin(DropDownTreeView);

//  自定义knockout 绑定
ko.bindingHandlers.kendoDropDownTreeView = {
    init: function (element, valueAccessor) {
        $(element).kendoDropDownTreeView(valueAccessor()).data("kendoDropDownTreeView");
    }
};