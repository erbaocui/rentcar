/**
 * Created by home on 2017/7/7.
 */
var url;
var action;
var canExport=false;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"订单管理",
        toolbar:"#tb",
        url:getContextPath()+"/order/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:0,checkbox:true},
            {field:'orderNo',title:'订单号',width:"6%"},
            {field:'type',title:'订单类型',width:"5%", formatter: commonFormatter.orderType},
            {field:'customerName',title:'客户姓名',width:"5%"},
            {field:'customerMobile',title:'客户手机',width:"6%"},
            {field:'startTime',title:'用车时间',width:"8%",align:'center', formatter:commonFormatter.dateTime},
            {field:'startAddress',title:'出发地点',width:"5%",formatter: commonFormatter.brief},
            {field:'endAddress',title:'送达地点',width:"5%",formatter: commonFormatter.brief},
            {field:'carType' ,title:'车型',width:"5%"},
            {field:'plateNumber' ,title:'车牌',width:"5%"},
            {field:'driverName' ,title:'司机',width:"3%"},
            {field:'driverMobile' ,title:'司机电话',width:"6%"},
            {field:'supplierName' ,title:'供应商',width:"3%"},
            {field:'total' ,title:'订单金额',width:"4%"},
            {field:'fee' ,title:'费用',width:"4%"},
            {field:'commission' ,title:'佣金',width:"4%"},
            {field: 'status', title: '状态', width: "4%", formatter: commonFormatter.orderStatus} ,
            {field:'remark',title:'备注',width: "5%",formatter: commonFormatter.brief},
            {field:'lastOptUser',title:'操作者',width: "4%",formatter:function(value,row,index){
                if( value!=null){
                    return value.displayName;
                }else{
                    return "";
                }
            }},
            {field:'lastOptTime',title:'操作者时间',width:"9%",align:'center', formatter:commonFormatter.time},
            {field:'_opation',title:'订单详情',width:"4%",formatter:function(val,row,index){

                return '<a href="#" onclick="showOrder(\''+index+'\')">订单详情</a>';
            }},
            {field:'id',title:'',width:0,hidden:true},
            {field:'single',title:'',width:0,hidden:true},
            {field:'fetchSend',title:'',width:0,hidden:true},
            {field:'customerType',title:'',width:0,hidden:true},
            {field:'createTime',title:'',width:0,hidden:true}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "beginTime": $("#qryBeginTime").val(),
            "endTime":$("#qryEndTime").val(),
            "customerName":$("#qryCustomeName").val(),
            "supplierName":$("#qrySupplierName").val(),
            "orderStatus":$("#qryOrderStatus").val()
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this).datagrid('appendRow', { orderNo: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this) .datagrid('mergeCells', { index:0, field: 'orderNo', colspan:21});
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else {
                if($("#qryOrderStatus").val()=="5"){
                    canExport=true;
                }
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
            }
        }
    });

    $('#dg').datagrid('getPager').pagination({
        pageSize: 10,
        pageNumber: 1,
        pageList: [10, 20, 30, 40, 50],
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    //搜索按钮
    $('#search').click(function(){
        var st=$("#qryBeginTime").val();
        var et=$("#qryEndTime").val();
        var stdt=new Date(st.replace("-","/"));
        var etdt=new Date(et.replace("-","/"));
        if(stdt>etdt){
            $.messager.alert("系统提示","开始时间大于结束时间");
            return;
        }
        $("#dg").datagrid('load', {
            "beginTime": $("#qryBeginTime").val(),
            "endTime":$("#qryEndTime").val(),
            "customerName":$("#qryCustomeName").val(),
            "supplierName":$("#qrySupplierName").val(),
            "orderStatus":$("#qryOrderStatus").val()

        });
    });

    //新增窗口
    $('#openAddDialog').click(function(){
        $("#fm").form('clear');
        $("#result1").empty();
        $("#result2").empty();
        action="add";
        addInit();
        $("#dlg").dialog({title: "添加订单信息",modal:true,height:"550",width:"630"});
        $("#dlg-buttons").show();
        $("#dlg").dialog("open");
    });
    //状态流转窗口
    $('#openModifyDialog').click(function(){
        $("#fm").form('clear');
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条数据！");
            return;
        }

        var row = selectedRows[0];
        if (row.status== 4) {
            $.messager.alert("系统提示", "订单已经取消！");
            return;
        }
        if (row.status== 5) {
            $.messager.alert("系统提示", "订单已完结！");
            return;
        }
        if (row.status== 3) {
           terminal(row);
            return;
        }
       if(row.status==1){
           action="dispatch";
           dispatchInit(row);
       }
        if(row.status==2){
            action="finish";
            finishInit(row);
        }

        $("#dlg").dialog({title:"订单流转信息",modal:true,height:"600",width:"630"});
        $("#dlg-buttons").show();
        $("#dlg").dialog("open");

    });

    //编辑信息窗口
    $('#edit').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条数据！");
            return;
        }

        var row = selectedRows[0];
        if (row.status== 4) {
            $.messager.alert("系统提示", "订单已经取消！");
            return;
        }
        if (row.status== 5) {
            $.messager.alert("系统提示", "订单已完结！");
            return;
        }
        if(row.status==1){
            action="addEdit";
            addEditInit(row);
        }
        if(row.status==2){
            action="dispatchEdit";
            dispatchEditInit(row);
        }
        if(row.status==3){
            action="finishEdit";
            finishEditInit(row);
        }


        $("#dlg").dialog({title:"编辑订单信息",modal:true,height:"600",width:"630"});
        $("#dlg-buttons").show();
        $("#dlg").dialog("open");

    });
    //历史记录
    $('#history').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条订单数据数据！");
            return;
        }

        var row = selectedRows[0];
       //

        window.parent.url=getContextPath()+"/driver/driver.do";
        window.parent.openTab("订单历史",getContextPath()+"/page/order/orderHList.jsp?orderId="+row.id,'icon-shuben');
    });

    //保存按钮
    $('#saveDialog').click(function(){

       if(action=='add'){
           addSave();
       }
        if(action=='addEdit'){
            addEditSave();
        }
        if(action=='dispatch'||action=='dispatchEdit'){
            dispatchSave(action);
        }
        if(action=='finish'||action=='finishEdit'){
            finishSave();
        }

    });
    //关闭按钮
    $('#closeDialog').click(function(){
        $("#dlg").dialog("close");
    });
  //导出按钮
    $('#export').click(function() {
        if(canExport) {
            window.location.href = getContextPath() + "/order/export.do";
        }else{
            $.messager.alert("系统提示","只有终结状态的订单可以导出");
            return;
        }
    });

    $("#fee").change(function(){
       compute()
    });
    $("#total").change(function(){
        compute()
    });

});

function actionSucess(){
    $("#fm").form('clear');
    $("#dlg").dialog("close");
    $("#dg").datagrid("reload");
}

function showOrder(row){
    var r= $('#dg').datagrid('getData').rows[row];
   showInit(r);
    $("#dlg").dialog({title:"订单流转信息",modal:true,height:"600",width:"630"});
    $("#dlg-buttons").hide();
    $("#dlg").dialog("open");

}
function terminal(row){
    $.messager.confirm("系统提示", "您确认要终结<font color=red>"
    + row.orderNo+ "</font>吗？", function (r) {
        if (r) {
            /* $.post( getContextPath ()+"/order//terminal.do", {
             id: row.id,
             status:5
             }, function (data) {
             if (data.result=='success') {
             $.messager.alert("系统提示", "订单已成功终结！");
             $("#dg").datagrid("reload");
             }else{
             $.messager.alert("系统提示", "操作失败！");
             }
             }, "json");*/
            $.ajax({
                url: getContextPath() + "/order/terminal.do",
                async: false,
                data: {
                    id:row.id,
                    status: 5
                },
                type: 'post',
                cache: false,
                dataType: 'json',
                success: function (data) {
                    if (data.result == "success") {
                        $.messager.alert("系统提示", "操作成功！");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "操作失败！");
                    }
                },
                error: function () {
                    $.messager.alert("系统提示", "系统异常！");
                }
            });
        }
    });
}


