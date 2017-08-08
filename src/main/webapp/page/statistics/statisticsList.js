/**
 * Created by home on 2017/7/7.
 */
var canExport=false;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        fitColumns: true,
        title:"数据统计",
        toolbar:"#tb",
        url:getContextPath()+"/statistics/list.do",
        method:"post",
        columns: [[
            {field: 'statistics', title: '客户名', width: "25%"},
            {field: 'sumFee', title: '费用合计', width: "25%"},
            {field: 'sumTotal', title: '订单金额合计', width: "25%"},
            {field: 'sumCommission', title: '佣金合计', width: "25%"}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "beginTime": $("#beginTime").val(),
            "endTime":$("#endTime").val(),
            "groupType":$("#groupType").val()
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $(this).datagrid('appendRow', { statistics: '<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'statistics', colspan: 3 });
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else {
                canExport=true;
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
        var st=$("#beginTime").val();
        var et=$("#endTime").val();
        var stdt=new Date(st.replace("-","/"));
        var etdt=new Date(et.replace("-","/"));
        if(st==null||st==""){
            $.messager.alert("系统提示","请选择统计开始时间");
            return;
        }
        if(et==null||et==""){
            $.messager.alert("系统提示","请选择统计结束时间");
            return;
        }
        if(stdt>etdt){
            $.messager.alert("系统提示","开始时间大于结束时间");
            return;
        }
        if( $("#groupType").val()==0) {
            $('#dg').datagrid({
                columns: [[
                    {field: 'statistics', title: '客户', width: "25%"},
                    {field: 'sumFee', title: '费用合计', width: "25%"},
                    {field: 'sumTotal', title: '订单金额合计', width: "25%"},
                    {field: 'sumCommission', title: '佣金合计', width: "25%"}
                ]]
            });
        }else{
            $('#dg').datagrid({
                columns: [[
                    {field: 'statistics', title: '供应商', width: "25%"},
                    {field: 'sumFee', title: '费用合计', width: "25%"},
                    {field: 'sumTotal', title: '订单金额合计', width: "25%"},
                    {field: 'sumCommission', title: '佣金合计', width: "25%"}
                ]]
            });
        }
        $("#dg").datagrid('load', {
            "beginTime": $("#beginTime").val(),
            "endTime":$("#endTime").val(),
            "groupType":$("#groupType").val()

        });
    });

    //导出
    $('#export').click(function() {
        if(canExport) {
            window.location.href = getContextPath() + "/statistics/export.do";
        }else{
            $.messager.alert("系统提示","请先统计数据");
            return;
        }
    });



});




