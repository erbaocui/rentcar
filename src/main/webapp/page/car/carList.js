/**
 * Created by home on 2017/7/7.
 */
var url;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"车辆管理",
        toolbar:"#tb",
        url:getContextPath()+"/car/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:0,checkbox:true},
            {field:'plateNumber',title:'车牌',width:"15%"},
            {field:'type' ,title:'车型',width:"15%"},
            {field: 'status', title: '状态', width: "15%",
                formatter: commonFormatter.status
            } ,
            {field:'remark',title:'备注',width: "27%"},
            {field:'createTime',title:'创建时间',width:"27.4%",align:'center', formatter:commonFormatter.time},
            {field:'id',title:'',width:0,hidden:true},
            {field:'typeId',title:'',width:0,hidden:true}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "plateNumber": $("#qryPlateNumber").val(),
            "status":$("#qryStatus").val()

        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this).datagrid('appendRow', { plateNumber: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this) .datagrid('mergeCells', { index:0, field: 'plateNumber', colspan:5 });
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else {
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
        $("#dg").datagrid('load', {
            "plateNumber": $("#qryPlateNumber").val(),
            "status":$("#qryStatus").val()
        });
    });

    //新增窗口
    $('#openAddDialog').click(function(){
        $("#fm").form('clear');
        $("#statusDisplay").hide();
        $('#plateNumber').attr("disabled","true");
        $('#plateNumber').validatebox('reduce');
        $('#typeId').combobox({
            method:'get',
            url:getContextPath()+"/car/typeList.do",
            valueField:'id',
            textField:'type'
        });
        $("#dlg").dialog({title: "添加车辆信息",modal:true});
        $("#dlg").dialog("open");
        url = getContextPath ()+"/car/add.do";
    });
    //编辑窗口
    $('#openModifyDialog')
        $('#openModifyDialog').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
            return;
        }
        $('#typeId').combobox({
            method:'get',
            url:getContextPath()+"/car/typeList.do",
            valueField:'id',
            textField:'type'
        });
        var row = selectedRows[0];
        $('#fm').form('load', row);
        $("#status").val(row.status);

        $("#statusDisplay").show();
        $('#plateNumber').attr("disabled","false");
        $('#plateNumber').validatebox('remove');

        url = getContextPath ()+"/car/modify.do";
        $("#dlg").dialog({title: "编辑车辆信息",modal:true});
        $("#dlg").dialog("open");

    });
    //删除按钮
    $('#delete').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要删除的数据！");
            return;
        }
        var row = selectedRows[0];

        $.messager.confirm("系统提示", "您确认要删除车辆<font color=red>"
        + row.plateNumber+ "</font>吗？", function (r) {
            if (r) {
                $.post( getContextPath ()+"/car/delete.do", {
                    id: row.id
                }, function (data) {
                    if (data.result=='success') {
                        $.messager.alert("系统提示", "数据已成功删除！");
                        $("#dg").datagrid("reload");
                    }else{
                        $.messager.alert("系统提示", "数据删除失败！");
                    }
                }, "json");
            }
        });
    });
    //保存按钮
    $('#saveDialog').click(function(){
       if($("#typeId").val()==null||$("#typeId").val()==""){
           $.messager.alert("系统提示", "请选择车型");
           return;
       }
        $("#fm").form("submit", {
            url: url,
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
                var json = $.parseJSON(data);
                if (json.result == "success") {
                    $.messager.alert("系统提示", "保存成功");
                    $("#fm").form('clear');
                    $("#dlg").dialog("close");
                    $("#dg").datagrid("reload");
                }


            }
        });
    });
    //关闭按钮
    $('#closeDialog').click(function(){
        $("#dlg").dialog("close");
    });

});


