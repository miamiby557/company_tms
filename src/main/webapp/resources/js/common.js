kendo.culture("zh-CN");
var router = new kendo.Router();
var isBack =false;
router.bind('back', function (e) {
    //isBack = true;
});
var index=0;
var viewHis = {
    data: [],
    findView: function (name) {
        console.info(this.data);
        for (var i in this.data) {
            /*console.info(this.data[i].attr("data-url"));
            var dataItem = this.data[i];
            var s = dataItem.attr("data-url");*/
            /*if (this.data[i].attr("data-url") == name) {
                return this.data[i];
            }*/
            return this.data[1];
        }
        return null;
    },
    addView: function (view) {
        if (this.data.length > 2) {
            this.data.remove(0);
            $("#pageView"+0).remove();
            index = 0;
        }
        var h = $("#page-view").clone().attr("id","pageView"+index++).appendTo($("#page-body")).hide();
        this.data.push(h);
    },
    getView: function (url) {
        var view = this.findView(url);
        if (view) return view;
        else {
            // load html to a div
            view = $('<div data-view="' + url + '"></div>').appendTo(document.body).hide();
            view.html();
            // div parseBindings
            parseBindings(view);
            // save to data
            this.addView(view);
            return view;
        }
    }
};

//div <input test>
router.route("*path", function (path) {
    // 忽略空的路由
    if (path == "" || path == "/" || path == "#" || path == "/#") return;

    setBreadcrumb(path);

    var pageBody = $("#page-body"), view = $("#page-view");
    kendo.ui.progress(pageBody, true);
    //viewHis.addView(view);
    view.slideUp();
    if (isBack) {
        var historyView = viewHis.findView(path);
        isBack =false;
        historyView.children("div #grid-wrap").children("div .k-grid.k-widget.k-reorderable").empty();
        $("span .k-widget.k-datepicker.k-header.k-input").attr("style","");
        //console.info(historyView.html());
        view.html(historyView.html());
        parseBindings(view);
        kendo.ui.progress(pageBody, false);
        view.slideDown();
        // display breadcrumb
        var breadcrumb = $("#page-view > .breadcrumb:first");
        if (breadcrumb.length > 0) {
            setBreadcrumb("");
            breadcrumb.appendTo("#breadcrumbs-wrap");
        }
    } else {
        $.get(path).done(function (result) {
            view.html(result);
            parseBindings(view);
            kendo.ui.progress(pageBody, false);
            view.slideDown();
            // display breadcrumb
            var breadcrumb = $("#page-view > .breadcrumb:first");
            if (breadcrumb.length > 0) {
                setBreadcrumb("");
                breadcrumb.appendTo("#breadcrumbs-wrap");
            }
        }).fail(function (xhr) {
            view.html("<h3>加载失败！</h3>");
            view.append("<p>(" + xhr.status + ") " + xhr.statusText + "</p>");
            kendo.ui.progress(pageBody, false);
            view.slideDown();
        });
    }
});


function parseBindings(selector) {
    var target = $(selector);
    kendo.init(target); // kendo ui init
    target.find("form").validate(); // validate form
}

function bindOnce() {
    // bind link to main frame
    $(document.body).on("click", "a[target='_main'][href]", function (event) {
        event.preventDefault();
        var url = $(this).attr("href");
        router.navigate(url);
    });

    // bind modal open
    $(document.body).on("click", "a[target='_modal'][href]", function (event) {
        event.preventDefault();
        var element = $(this);
        var title = element.text() || element.attr("title"), url = element.attr("href");
        modal(title, url);
    });

    // bind action closeModal
    $(document.body).on("click", "a[data-action='closeModal'], button[data-action='closeModal']", function (event) {
        event.preventDefault();
        var modalObject = $(this).closest('.k-window-content.k-content').data("kendoWindow");
        if (modalObject) modalObject.close();
    });

    // bind action goBack
    $(document.body).on("click", "a[data-action='goBack'], button[data-action='goBack'], input[type='button'][data-action='goBack']", function (event) {
        event.preventDefault();
        history.back();
    });

    // bind redirect button
    $(document.body).on("click", "button[data-url], input[type='button'][data-url]", function (event) {
        event.preventDefault();
        var url = $(this).data("url");
        router.navigate(url);
    });

}


function notify(msg, type) {
    var notificationElement = $("#popupNotification");
    if (notificationElement.length < 1) {
        notificationElement = $('<span id="popupNotification"></span>').appendTo(document.body);
        notificationElement.kendoNotification({
            position: {
                top: 60,
                left:700
            }
        });
    }
    var notificationWidget = notificationElement.data("kendoNotification");
    notificationWidget.show(msg, type);
    return notificationElement;
}

function modal(title, url, options) {
    var settings = $.extend({
        width: 600,
        minHeight: 400,
        modal: true,
        resizeable: true,
        title: title,
        content: url
    }, options);

    var modalElement = $('<div></div>').appendTo(document.body);
    var modalObject = modalElement.kendoWindow(settings).data("kendoWindow");
    modalObject.center();
    modalObject.bind("close", function () {
        modalObject.destroy();
    });

    modalObject.bind("refresh", function () {
        parseBindings(modalElement);
    });

    return modalElement;

}

function setBreadcrumb(value) {
    $("#breadcrumbs-wrap").html(value);
}
//设置grid columns readonly
function readonly(container, options) {
    var input = $('<input name="' + options.field + '" class="k-input k-textbox valid" readonly="true" />');
    input.appendTo(container);
}
var commonData = {
    organizationType: ko.observableArray(),
    carrierGrade: ko.observableArray(),
    orderType: ko.observableArray(),
    paymentType: ko.observableArray(),
    carrierType: ko.observableArray(),
    handoverType: ko.observableArray(),
    calculateType: ko.observableArray(),
    settleCycle: ko.observableArray(),
    orderStatus: ko.observableArray(),
    regionType: ko.observableArray(),
    businessModel: ko.observableArray(),
    industryType: ko.observableArray(),
    clientGrade: ko.observableArray(),
    transportType: ko.observableArray(),
    carrierOrderStatus: ko.observableArray(),
    urgencyLevel: ko.observableArray(),
    pickUpOrderStatus: ko.observableArray(),
    regions: ko.observableArray(),
    wrapType: ko.observableArray(),
    feeSourceType: ko.observableArray(),
    feeAuditStatus: ko.observableArray(),
    provinces: ko.observableArray(),
    receiptStatus: ko.observableArray(),
    transportMergeStatus: ko.observableArray(),
    transportOrderSignStatus: ko.observableArray(),
    backStatus: ko.observableArray(),
    orderDispatchType: ko.observableArray(),
    vehicleType: ko.observableArray(),
    dispatchAssignvehicleStatus:ko.observableArray(),
    YesOrNo: ko.observableArray()
};

$(function () {
    router.start(); // start SPA route
    parseBindings(document.body); // parse bindings
    bindOnce(); // bind once events

    // load common data
    $.ajax("/commonData/sysList").done(function (result) {
        $.each(result, function (prop, list) {
            $.each(list, function (i, item) {
                if (commonData[prop]) commonData[prop].push(item);
                else console.info(prop + "未定义");
            });

        });
    })
    // load provinces
    /*$.ajax("/commonData/provinces").done(function (result) {
        $.each(result, function (i, item) {
            commonData.provinces.push(item);
        });
    })*/
});

// common datasource


// kendo ui helper
function kendoHelper() {
    var self = this;
    self.createTransport = function (url, type, dataType, contentType) {
        if (!url) return undefined;
        return {
            contentType: contentType || 'application/json',
            dataType: dataType || 'json',
            type: type || 'POST',
            url: url
        };
    };
    self.createTreeListDataSource = function(idField, readUrl,parentField){
        var datasource = {
            schema: {
                model: {
                    id: idField,
                    fields: {
                        parentId: { field: parentField, nullable: true }
                    }
                },
                data: function (response) {
                    if (response.data) return response.data;
                    return response;
                }
            },
            transport: {
                parameterMap: function (options) {
                    return kendo.stringify(options);
                },
                read: self.createTransport(readUrl)
            },
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true
        };

        return new kendo.data.TreeListDataSource(datasource);
    }
    self.createDataSource = function (idField, readUrl, createUrl, updateUrl, deleteUrl) {
        var datasource = {
            schema: {
                model: {
                    id: idField
                },
                data: function (response) {
                    if (response.data) return response.data;
                    return response;
                },
                total: 'total'
            },
            transport: {
                parameterMap: function (options) {
                    return kendo.stringify(options);
                },
                read: self.createTransport(readUrl),
                create: self.createTransport(createUrl),
                update: self.createTransport(updateUrl),
                delete: self.createTransport(deleteUrl)
            },
            pageSize: 20,
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true
        };

        return new kendo.data.DataSource(datasource);
    };
    self.createFilterDataSource = function (idField, readUrl, filter) {
        var datasource = {
            schema: {
                model: {
                    id: idField
                },
                data: function (response) {
                    if (response.data) return response.data;
                    return response;
                },
                total: 'total'
            },
            transport: {
                parameterMap: function (options) {
                    return kendo.stringify(options);
                },
                read: self.createTransport(readUrl)
            },
            pageSize: 20,
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true,
            filter: filter
        };

        return new kendo.data.DataSource(datasource);
    };
    self.createHierarchicalDataSource = function (idField, readUrl, hasChildren) {
        var dataSource = {
            schema: {
                model: {
                    id: idField,
                    hasChildren: hasChildren
                },
                data: function (response) {
                    if (response.data) return response.data;
                    return response;
                }
            },
            transport: {
                parameterMap: function (options) {
                    return kendo.stringify(options);
                },
                read: self.createTransport(readUrl)
            }
        };
        return new kendo.data.HierarchicalDataSource(dataSource);
    };
    self.createListDataSource = function (idField, readUrl,filter) {
        var dataSource = {
            schema: {
                model: {
                    id: idField
                },
                data: function (response) {
                    if (response.data) return response.data;
                    return response;
                },
                total: 'total'
            },
            transport: {
                parameterMap: function (options) {
                    return kendo.stringify(options);

                },
                read: self.createTransport(readUrl)
            },
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true,
            filter: filter
        };

        return new kendo.data.DataSource(dataSource);
    };

    self.template = function (tmpl) {
        var selector = $(tmpl);
        return selector.length > 0 ? kendo.template(selector.html()) : kendo.template(tmpl);
    };

}

kendo.utils = new kendoHelper();
Array.prototype.remove=function(dx)
{
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
            this[n++]=this[i]
        }
    }
    this.length-=1
};
// set date format
ko.bindingHandlers.kendoDatePicker.options = {
    format: "yyyy-MM-dd"
};

ko.bindingHandlers.kendoDateTimePicker.options = {
    format: "yyyy-MM-dd HH:mm:ss"
};
ko.bindingHandlers.kendoGrid.options = {
    excel: {
        allPages: true,
        filterable: true
    },
    pdf: {
        allPages: false,
        filterable: true
    },
    resizable: true,//设置可自己调列宽
    allowCopy: true,
    reorderable: true, //设置可自己调列顺序
    dataBound:function(){
        var grid = $(".k-grid").data("kendoGrid");
        $(".k-grid table[aria-multiselectable='true'] td").dblclick(function(){
            grid.editCell(this);
            $(this).find("input").attr("readonly",true);
        });
    }
};
Date.prototype.toJSON = function () {
    return kendo.toString(this, "yyyy-MM-dd HH:mm:ss");
};
String.prototype.replaceAllChar =function(s1,s2){
    return this.replace(new RegExp(s1,"gm"),s2);
}
//Date.parse = function (dateString) {
//    return kendo.parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
//};
//格式数字  src 数字  pos 精度
function formatFloat(src, pos) {
    return Math.round(src * Math.pow(10, pos)) / Math.pow(10, pos);
}

jQuery.validator.setDefaults({
    ignore: "",
    errorPlacement: function (error, element) {
        var el = $(element);
        if (el.data("role") == "combobox") {
            el.closest(".k-widget.k-combobox.k-header").after(error);
        }
        else if (el.data("role") == "datetimepicker") {
            el.closest(".k-widget.k-datetimepicker.k-header").after(error);
        }
        else if (el.data("role") == "datepicker") {
            el.closest(".k-widget.k-datepicker.k-header").after(error);
        }
        else if (el.data("role") == "dropdownlist") {
            el.closest(".k-widget.k-dropdown.k-header").after(error);
        } else if (el.data("role") == "upload") {
            el.closest(".k-button.k-upload-button").after(error);
        } else if (el.data("role") == "treeview") {
            el.closest(".k-button.k-upload-button").after(error);
        }
        else {
            el.after(error);
        }
    }
});

jQuery.validator.addMethod("pattern", function (value, element, params) {
    var match;
    if (this.optional(element)) {
        return true;
    }
    match = new RegExp(params).exec(value);
    return (match && (match.index === 0) && (match[0].length === value.length));
}, "数据格式不正确");

jQuery.validator.addMethod("compareDate", function (value, element, params) {
    var startDate = jQuery(params).val();
    return startDate < value;
});
jQuery.validator.addMethod("compareTo", function (value, element, params) {
    var start = parseFloat(jQuery(params).val());
    value = parseFloat(value);
    if (value) {
        return start < value;
    }
    return true;
}, "区间最大值不能小于最小值");
jQuery.validator.addMethod("notEquals", function (value, element, params) {
    var startDate = jQuery(params).val();
    return startDate != value;
}, "目的地不能和始发地一样");
jQuery.validator.addMethod("existCode", function (value, element, url) {
    var result = true;
    var url = url + "&code=" + value;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        async: false,
        url: url
    }).done(function (response) {
        result = response;
    });
    return result;
}, "编码已存在");

$(document).keydown(function (event) {
    if (event.keyCode == 27) {
        var modalObject = $(this).closest('.k-window-content.k-content').data("kendoWindow");
        if (modalObject) modalObject.close();
    }
});
$(document).keydown(function(event){
    if(event.keyCode==13){
        $(event.target).trigger("blur");
        $("div .search-buttons button:first").trigger("click");
    }
});

function getGridHeight(){
    var height=$(window).height()-$("#grid-wrap .grid-toolbar").height()-140;
    return Math.max(height, 300);
}
/*
$(window).on('resize', function () {
    console.info($(".k-grid.k-widget").closest("form"));
    if(!$(".k-grid.k-widget").closest("form").is("form")){
    $(".k-grid.k-widget").height(getGridHeight());
    }
});*/
