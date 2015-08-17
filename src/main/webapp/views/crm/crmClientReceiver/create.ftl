<#import "/utils/form.ftl" as form >
<div id="createReceiver-grid-wrap">
    <#--<form id="address-create-form" method="post" data-bind="submit:save" class="form-horizontal">-->
        <#--<div class="col-12" data-bind="with:model">-->
        <#--&lt;#&ndash;<@form.koDropDown field="address" data="$root.addressList" label="地址类型" required="required"/>&ndash;&gt;-->
        <#--&lt;#&ndash;<@form.koComboBox textField="contactMan" valueField="addressId" data="$root.addressList"  field="addressId" label="24243432" required="required" />&ndash;&gt;-->
        <#--</div>-->
            <div class="grid-buttons">
                <#--<button type="button" style="float:left;" data-bind="kendoButton:selectAll"> <i class="fa fa-save"></i> 全选 </button>-->
                <button type="button" data-bind="kendoButton:select, enable:canSelect"><i class="fa fa-edit"></i> 选择 </button>
            </div>
        <div id="receiverSearch" data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true,
        groupable: true, editable:false, pageable: pageable, selectable:'multiple, row', change:onChange }">
        </div>
</div>
<script>
    function CreateModel() {
//        var self = this;
//        self.model = {};
        <#--self.model.addressId= ko.observable();-->
        <#--self.model.clientId= ko.observable("${clientId}");-->
        <#--self.addressList = kendo.utils.createListDataSource("addressId", "/baseAddress/getDataSource");-->
        var self = this;
//        self.addressType=ko.observable();
//        self.addressType=commonData.baseAddressType;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("addressId", "/baseAddress/getDataSource");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.columns = [{
            field: "addressType",
            title: "地址类型",
//            values: commonData.baseAddressType()
        }, {
            field: "contactMan",
            title: "联系人"
        }, {
            field: "contactPhone",
            title: "联系电话"
        }, {
            field: "name",
            title: "地区"
        }, {
            field: "address",
            title: "地址"
        }];
        self.selectedOrder = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedOrder(dataItem);
        };

        self.canSelect = ko.computed(function () {
            return self.selectedOrder();
        });
        self.select = function () {
            var selectedIds = [];
            var selectedItems=[];
            var rows = self.widget().select();
            rows.each(function (i, row) {
                var dataItem = self.widget().dataItem(row);
                if (dataItem) {
                    selectedIds.push(dataItem.addressId);
                    selectedItems.push(dataItem);
                }
            });
            $(".k-window-content.k-content").data("kendoWindow").sender=selectedIds;
            $(".k-window-content.k-content").data("kendoWindow").close();
        };

        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length+1;i++){
                select.push("tr:eq("+i+")");
            }
            console.info(select.toString());
            var grid = $("#receiverSearch").data("kendoGrid");
            grid.select(select.toString());
            select.length=0;
        }
    }
    $(function () {
        var viewModel = new CreateModel();
        ko.applyBindings(viewModel, document.getElementById("createReceiver-grid-wrap"));
    });
</script>