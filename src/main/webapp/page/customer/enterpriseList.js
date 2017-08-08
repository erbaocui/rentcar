/**
 * Created by home on 2017/7/7.
 */
var url;
var canExport=false;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"企业客户管理",
        toolbar:"#tb",
        url:getContextPath()+"/customer/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:0,checkbox:true},
            {field:'loginName',title:'登录名',width:'10%'},
            {field:'displayName',title:'显示名',width:'10%'},
            {field: 'status', title: '状态', width: '10%',
                formatter: commonFormatter.status
            } ,
            {field:'taxId',title:'企业税号',width:'10%',align:'center'},
            {field:'contact',title:'联系人',width:'10%',align:'center'},
            {field:'contactMobile',title:'联系人电话',width:'10%',align:'center'},
            {field:'remark',title:'备注',width:'20%',align:'center'},
            {field:'createTime',title:'创建时间',width:'19.5%',align:'center', formatter:commonFormatter.time},
            {field:'id',title:'',width:0,hidden:true}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "loginName": $("#qryLoginName").val(),
            "displayName":$("#qryDisplayName").val(),
            "status":$("#qryStatus").val(),
            "type":"1"
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this).datagrid('appendRow', { loginName: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this) .datagrid('mergeCells', { index: 0, field: 'loginName', colspan:8 });
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
        $("#dg").datagrid('load', {
            "loginName": $("#qryLoginName").val(),
            "displayName":$("#qryDisplayName").val(),
            "status":$("#qryStatus").val(),
            "type":"1"
        });
    });
    //导出
    $('#export').click(function() {
        if(canExport) {
            window.location.href = getContextPath() + "/customer/export.do?type=1";
        }else{
            $.messager.alert("系统提示","请先统计数据");
            return;
        }
    });

    //新增窗口
    $('#openAddDialog').click(function(){
        $("#fm").form('clear');
        $("#checkPasswordDisplay").show();
        $("#type").val("1");
        $("#passwordDisplay").show();
        $("#statusDisplay").hide();
        $('#loginName').attr("disabled","true");
        $('#loginName').validatebox('reduce');
        $('#password').validatebox('reduce');
        $('#checkPassword').validatebox('reduce');
        //$("#dlg").dialog("open").dialog("setTitle", "添加企业客户信息");
        url = getContextPath ()+"/customer/add.do";
        $("#dlg").dialog({title: "添加企业客户信息",modal:true});
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

        //$("#dlg").dialog("open").dialog("setTitle", "编辑企业客户信息");
        $('#fm').form('load', row);
        $("#status").val(row.status);
        $("#type").val("1");
        $("#checkPasswordDisplay").hide();
        $("#passwordDisplay").hide();
        $("#statusDisplay").show();
        $('#loginName').attr("disabled","false");
        $('#loginName').validatebox('remove');
        $('#password').validatebox('remove');
        $('#checkPassword').validatebox('remove');
        url = getContextPath ()+"/customer/modify.do";
        $("#dlg").dialog({title: "编辑企业客户信息",modal:true});
        $("#dlg").dialog("open");
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
                }else{
                    $.messager.alert("系统提示", "保存失败");
                }
            }
        });
    });
    //关闭按钮
    $('#closeDialog').click(function(){
        $("#dlg").dialog("close");
        $("#fm").form('clear');
    });

    //重置密码窗口
    $('#openRestePwDialog').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
            return;
        }
        $("#type").val("1");
        $("#resetPwDialog").dialog({title: "重置密码",modal:true});
        $("#resetPwDialog").dialog("open");
    });
    //重置密码保存按钮
    $('#pwSaveDialog').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        var row = selectedRows[0];
        $.messager.confirm("系统提示", "您确认要重置企业客户<font color=red>"
        + row.loginName + "</font>的登录密码？", function (r) {
            if (r) {
                $.post(getContextPath ()+"/customer/modify.do", {
                    id:row.id,
                    password:$("#resetPassword").val(),
                    type:$("#type").val()

                }, function (data) {
                    if (data.result=="success") {
                        $("#resetPwDialog").dialog("close");
                        $.messager.alert("系统提示", "密码重置成功！");
                    } else {
                        $.messager.alert("系统提示", "密码重置失败！");
                    }
                }, "json");
            }
        });
    });
    //重置关闭按钮
    $('#pwCloseDialog').click(function(){
        $("#resetPwDialog").dialog("close");
    });

});




