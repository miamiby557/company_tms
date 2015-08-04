<#import "/utils/query.ftl" as query >
<ol class="breadcrumb">
    <li><a href="#/home"><i class="fa fa-home"></i> 主页</a></li>
    <li><a href="#/dispatchAssign"><i class="fa fa-users"></i> 派车单</a></li>
</ol>
<div id="grid-wrap">
    <div class="grid-toolbar">
        <div>
            <@query.koText field="dispatchAssignNumber" label="派车单号"/>
            <@query.koDropDown field="dispatchType" data="dispatchTypeList" label="调度类型"/>
        </div>
        <div>
            <@query.koText field="destAddress" label="目的地址"/>
            <@query.koDropDown field="vehicleType" data="vehicleTypeList" label="车辆类型"/>
            <@query.koDateTimePicker field="expectFinishTimeStart" label="预计完成时间"/>至
            <@query.koDateTimePicker field="expectFinishTimeEnd" label=""/>
        </div>
        <div class="search-buttons">
            <button type="button" data-bind="kendoButton:search"><i class="fa fa-search"></i> 搜索</button>
            <button type="button" data-bind="kendoButton:clearFilter"><i class="fa fa-eraser"></i> 清空</button>
        </div>
        <div class="grid-buttons">
            <button type="button" style="float:left;" data-bind="kendoButton:selectAll">
                <i class="fa fa-save"></i> 全选
            </button>
            <#--<a data-role="button" href="#/dispatchAssign/create"> <i class="fa fa-plus"></i> 新增 </a>-->
            <button type="button" data-bind="kendoButton:$root.modify,enable:$root.canModify">
                <i class="fa fa-edit"></i> 修改
            </button>
            <button type="button" data-bind="kendoButton:$root.send,enable:$root.canSend">
                <i class="fa fa-edit"></i> 发车
            </button>
            <button type="button" data-bind="kendoButton:$root.back,enable:$root.canBack">
                <i class="fa fa-edit"></i> 回场
            </button>
            <#--<button type="button" data-bind="kendoButton:$root.send,enable:false">
                <i class="fa fa-edit"></i> 签收
            </button>-->
        </div>
    </div>
    <div data-bind="kendoGrid: { data: dataSource, widget: widget, columns: columns,sortable: true, groupable: true,
     editable:false, pageable: pageable, selectable: 'row',change:$root.onChange}"></div>
</div>
<script>
    var GridModel = function () {
        var self = this;
        self.widget = ko.observable();
        self.dataSource = kendo.utils.createDataSource("dispatchAssignId", "/dispatchAssign/getDataSource");
        self.pageable = {refresh: true, pageSize: 20, pageSizes: [10, 20, 50, 100, 200]};
        self.dispatchTypeList = commonData.orderDispatchType;
        self.vehicleTypeList = commonData.vehicleType;
        self.dispatchAssignNumber=ko.observable();
        self.dispatchType=ko.observable();
        self.vehicleType=ko.observable();
        self.destAddress=ko.observable();
        self.expectFinishTimeStart=ko.observable();
        self.expectFinishTimeEnd=ko.observable();
        self.clearFilter = function(){
            self.dispatchAssignNumber("");
            self.dispatchType("");
            self.vehicleType("");
            self.destAddress("");
            self.expectFinishTimeStart("");
            self.expectFinishTimeEnd("");
            self.dataSource.filter([]);
        };
        self.search =function(){
            var dispatchAssignNumber = $.trim(self.dispatchAssignNumber());
            var dispatchType = $.trim(self.dispatchType());
            var vehicleType = $.trim(self.vehicleType());
            var destAddress = $.trim(self.destAddress());
            var expectFinishTimeStart = $.trim(self.expectFinishTimeStart());
            var expectFinishTimeEnd = $.trim(self.expectFinishTimeEnd());
            var filters = new Array();
            if(dispatchAssignNumber){
                filters.push({field: "dispatchAssignNumber",operator: "contains",value: dispatchAssignNumber});
            }if(dispatchType){
                filters.push({field: "dispatchType",operator: "eq",value: dispatchType});
            }if(vehicleType){
                filters.push({field: "vehicleType",operator: "eq",value: vehicleType});
            }if(destAddress){
                filters.push({field: "destAddress",operator: "contains",value: destAddress});
            }if(expectFinishTimeStart){
                filters.push({field: "expectFinishTime",operator: "gte",value: new Date(expectFinishTimeStart)});
            }if(expectFinishTimeEnd){
                var endDate=new Date(expectFinishTimeEnd);
                filters.push({field: "expectFinishTime",operator: "lt",value: new Date(endDate.setDate(endDate.getDate()+1))});
            }
            self.dataSource.filter({
                logic: "and",
                filters: filters
            });
        }
        self.columns = [{
            title: "序号",
            template: function (dataItem) {
                return self.dataSource.indexOf(dataItem)+1;
            },
            width: 30
        },{
            field: "dispatchAssignNumber",
            title: "派车单号",
            template: "<a href='\\#/dispatchAssign/detail/#= dispatchAssignId #' >#= dispatchAssignNumber# </a>",
            width: 100
        }, {
            field: "vehicleType",
            title: "车辆类型",
            values:commonData.vehicleType(),
            width: 60
        }, {
            field: "vehicleCode",
            title: "车牌号码",
            width: 60
        }, {
            field: "totalFee",
            title: "费用",
            width: 60
        }, {
            field: "dispatchType",
            title: "调度类型",
            values:commonData.orderDispatchType(),
            width: 60
        },   {
            field: "totalWeight",
            title: "重量(千克)",
            width: 60
        }, {
            field: "totalVolume",
            title: "体积(立方)",
            width: 60
        }, {
            field: "expectFinishTime",
            title: "预计完成时间",
            template: function (dataItem) {
                if(dataItem.expectFinishTime)
                    return kendo.toString(kendo.parseDate(dataItem.expectFinishTime, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
                else
                    return "";
            },
            width: 120
        },{
            field: "startAddress",
            title: "起始地址",
            width: 180
        }, {
            field: "destAddress",
            title: "目的地址",
            width: 180
        },{
            field: "status",
            title: "状态",
            values:ko.toJS(commonData.dispatchAssignvehicleStatus),
            width: 60
        }];
        self.selectedOrder = ko.observable();
        self.onChange = function () {
            var row = self.widget().select()[0];
            var dataItem = self.widget().dataItem(row);
            self.selectedOrder(dataItem);
        };
        self.canModify = ko.computed(function () {
            return self.selectedOrder()&&self.selectedOrder().status==1;
        });
        self.modify = function () {
            var url = "#/dispatchAssign/modify/" + self.selectedOrder().dispatchAssignId;
            router.navigate(url);
        };
        self.canSend = ko.computed(function () {
            return self.selectedOrder()&&self.selectedOrder().status==1;
        });
        self.send = function () {
            var url = "#/dispatchAssign/send/" + self.selectedOrder().dispatchAssignId;
            router.navigate(url);
        };
        self.canBack = ko.computed(function () {
            return self.selectedOrder()&&self.selectedOrder().status==2;
        });
        self.back = function () {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/dispatchAssign/backAssign",
                data: ko.toJSON([self.selectedOrder().dispatchAssignId])
            }).done(function (result) {
                if (result.success === true) {
                    self.dataSource.query();
                    notify("操作成功", "success");
                }
                else {
                    notify(result.message, "error");
                }
            });
        };
        self.selectAll= function(){
            var select = new Array();
            for(var i = 1;i<=self.dataSource.data().length;i++){
                select.push("tr:eq("+i+")");
            }
            var grid = $(".k-grid").data("kendoGrid");
            grid.select(select.toString());
        }
    };

    $(function () {
        var viewModel = new GridModel();
        ko.applyBindings(viewModel, document.getElementById("grid-wrap"));
    });
</script>