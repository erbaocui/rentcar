/**
 * Created by home on 2017/7/7.
 */

$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"订单操作历史记录",
        url:getContextPath()+"/order/hlist.do",
        method:"post",
        columns:[[
            {field:'id',title:'订单号',width:0,hidden:true},
            {field:'single',title:'',width:0,hidden:true},
            {field:'fetchSend',title:'',width:0,hidden:true},
            {field:'customerType',title:'',width:0,hidden:true},
            {field:'orderNo',title:'订单号',width:"12%"},
            {field:'type',title:'订单类型',width:"5%", formatter: commonFormatter.orderType},
            {field:'customerName',title:'客户姓名',width:"5%"},
            {field:'customerMobile',title:'客户手机',width:"6%"},
            {field:'startTime',title:'用车时间',width:"8%",align:'center', formatter:commonFormatter.dateTime},
            {field:'startAddress',title:'出发地点',width:"5%",formatter: commonFormatter.brief},
            {field:'endAddress',title:'送达地点',width:"5%",formatter: commonFormatter.brief},
            {field:'carType' ,title:'车型',width:"5%",hidden:true},
            {field:'plateNumber' ,title:'车牌',width:"5%"},
            {field:'driverName' ,title:'司机',width:"5%"},
            {field:'driverMobile' ,title:'司机电话',width:"6%",hidden:true},
            {field:'supplierName' ,title:'供应商',width:"5%",hidden:true},
            {field:'fee' ,title:'费用',width:"5%"},
            {field:'total' ,title:'订单金额',width:"5%"},
            {field: 'status', title: '状态', width: "5%", formatter: commonFormatter.orderStatus} ,
            {field:'remark',title:'备注',width: "8%",formatter: commonFormatter.brief},
            {field:'optTime',title:'操作时间',width:"8%",align:'center', formatter:commonFormatter.time},
            {field:'optUser' ,title:'操作人',width:"5%",formatter:function(value,row,index){
                if( value!=null){
                    return value.displayName;
                }else{
                    return "";
                }
            }},
            {field:'_opation',title:'记录详情',width:"5%",formatter:function(val,row,index){
                return '<a href="#" onclick="show(\''+index+'\')">操作记录</a>';
            }}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: true,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            orderId:$('#orderId').val()
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

    //关闭按钮
    $('#closeDialog').click(function(){
        $("#dlg").dialog("close");
    });




});

function show(index){
    var row= $('#dg').datagrid('getData').rows[index];
       $("#fm").form('clear');
        var type=row.type;
        $("#orderNoDiv").show();
        if(type==1||type==4||type==5){
            $("#tenancyDiv").show();
            $("#orderExDiv").hide();
        }else{
            $("#tenancyDiv").hide();
            $("#orderExDiv").show();
        }
       $('#orderNo').val(row.orderNo);
       $('#optName').val(row.optUser.displayName);
       $('#optTime').val(commonFormatter.time(row.optTime,"",""));
        $('#type').val(commonFormatter.orderType(row.type,"",""));
        $('#orderStatus').val(commonFormatter.orderStatus(row.status,"",""));
        $("#startTime").val(commonFormatter.dateTime(row.startTime,"",""));
        $('#tenancy').val(row.tenancy);
        $("#startAddress").val(row.startAddress);
        $("#endAddress").val(row.endAddress);
        $('#single').val(commonFormatter.single(row.single,"",""));
        $('#fetchSend').val(commonFormatter.fetchSend(row.fetchSend,"",""));
        $("#flightTrain").val(row.flightTrain);
        $("#total").val(row.total);
        $("#fee").val(row.fee);
        $('#customerType').val(commonFormatter.custuomerType(row.customerType,"",""));
        $('#customerName').val(row.customerName);
        $('#customerMobile').val(row.customerMobile);
        $('#plateNumber').val(row.plateNumber);
        $('#carType').val(row.carType);
        $('#driverName').val(row.driverName);
        $('#driverMobile').val(row.driverMobile);
        $('#supplierName').val(row.supplierName);
        $("#remark").val(row.remark);;
        $("#dlg").dialog({title: "订单操作信息",modal:true,height:"600",width:"630"});
        $("#dlg").dialog("open");

}




