/**
 * Created by home on 2017/7/7.
 */
var url;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"供应商管理",
        toolbar:"#tb",
        url:getContextPath()+"/supplier/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:0,checkbox:true},

            {field:'name',title:'供应商名称',width:"10%"},
            {field:'mobile' ,title:'联系手机',width:"10%"},
            {field:'contactName',title:'联系人',width:"10%"},
            {field: 'status', title: '状态', width: "10%",
                formatter: commonFormatter.status
            } ,
            {field:'remark',title:'备注',width: "30%"},
            {field:'createTime',title:'创建时间',width:"29.5%",align:'center', formatter:commonFormatter.time},
            {field:'id',title:'',width:0,hidden:true}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "name": $("#qryName").val(),
            "mobile":$("#qryMobile").val(),
            "status":$("#qryStatus").val()
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this).datagrid('appendRow', { name: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this) .datagrid('mergeCells', { index:0, field: 'name', colspan:6 });
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
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
            "name": $("#qryName").val(),
            "mobile":$("#qryMobile").val(),
            "status":$("#qryStatus").val()
        });
    });

    //新增窗口
    $('#openAddDialog').click(function(){
        $("#fm").form('clear');
        $("#statusDisplay").hide();
        $('#mobile').attr("disabled","true");
        $('#mobile').validatebox('reduce');
        url = getContextPath ()+"/supplier/add.do";
        $("#dlg").dialog({title: "添加供应商信息",modal:true});
        $("#dlg").dialog("open");
    });
    //编辑窗口
    $('#openModifyDialog').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
            return;
        }
        var row = selectedRows[0];
        $('#fm').form('load', row);
        $("#status").val(row.status);

        $("#statusDisplay").show();
        $('#mobile').attr("disabled","false");
        $('#mobile').validatebox('remove');
        url = getContextPath ()+"/supplier/modify.do";
        $("#dlg").dialog({title: "编辑供应商信息",modal:true});
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

        $.messager.confirm("系统提示", "您确认要删除供应商<font color=red>"
        + row.name+ "</font>吗？", function (r) {
            if (r) {
                $.post( getContextPath ()+"/supplier/delete.do", {
                    id: row.id
                }, function (data) {
                    if (data.result=='success') {
                        $.messager.alert("系统提示", "数据已成功删除！");
                        $("#dg").datagrid("reload");
                    }else {
                        $.messager.alert("系统提示", "数据删除失败！");
                    }
                }, "json");
            }
        });
    });
    //保存按钮
    $('#saveDialog').click(function(){

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


