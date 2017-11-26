/**
 * Created by home on 2017/7/7.
 */
var url;
var str="";
function findParet(node){
    var ids=str.split(",");
    var flag=false;
    for(var i=0;i<ids.length;i++){
        if(ids[i]==node.id){
            flag=true;
        }
    }
    if(!flag){
        str=str+node.id+",";
    }
    var father= $('#tt').tree("getParent",node.target);
    if(father!=null){
        findParet(father);
    }
}

function findCheckedIds(){
    var nodes = $('#tt').tree('getChecked');

    for (var i = 0; i < nodes.length; i++) {
        findParet( nodes[i]);
    }
    if(str==null||str==""){

        return null;
    }else{
        str = str.substring(0, str.length - 1);
        return str;
    }
}
$(document).ready(function(){
    //表格init
    $('#dg').datagrid({
        title:"角色管理",
        toolbar:"#tb",
        url:getContextPath()+"/role/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:100,checkbox:true},

            {field:'kv',title:'角色标识',width:100},
            {field:'name',title:'角色名称',width:100},
            {field:'remark',title:'备注',width:100,align:'center'},
            {field:'_operator',title:'权限详情',width:100,align:'center',formatter:function(val,row,index){
                return '<a href="#" onclick="showDetail(\''+row.id+'\')">权限详情</a>';
            }},
            {field:'id',title:'',width:100,hidden:true},
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "name": $("#qryName").val(),
            "kv": $("#qryKv").val()
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this).datagrid('appendRow', { kv: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']")[0].remove();
                }
                $(this) .datagrid('mergeCells', { index:0, field: 'kv', colspan:4 });
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else {
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
            }
        }
    });
    //分页页脚
    $('#dg').datagrid('getPager').pagination({
        pageSize: 10,
        pageNumber: 1,
        pageList: [10, 20, 30, 40, 50],
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

    //搜索按钮
    $('#searchRole').click(function(){
        $("#dg").datagrid('load', {
            "name": $("#qryName").val(),
            "kv": $("#qryKv").val()
        });
    });
    //新增窗口
    $('#openAddDialog').click(function(){
        ("#fm").form('clear');
        $('#tt').tree({
            url:getContextPath()+"/role/addRightTree.do",
            checkbox:true,
            //url:"data.html",
            onCheck:function(node,checked) {
                str="";
            }
        });
        $("#dlg").dialog("open").dialog("setTitle", "添加角色权限信息");
        url =  getContextPath() + "/role/add.do";
        $("#kv").validatebox("enableValidation");
        $("#kv").removeAttr("disabled");
        $("#dlg").dialog({title: "添加角色权限信息",modal:true});
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
        $("#id").val(row.id);
        $('#tt').tree({
            url:getContextPath()+"/role/modifyRightTree.do?roleId="+row.id,
            checkbox:true,
            onCheck:function(node,checked) {
                str="";
            }
        });

        url = getContextPath ()+"/role/modify.do";

        $("#kv").validatebox("disableValidation");
        $("#kv").attr("disabled","disabled");
        $("#dlg").dialog({title: "编辑角色权限信息",modal:true});
        $("#dlg").dialog("open");
    });
    //删除按钮
    $('#deleteRole').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要删除的数据！");
            return;
        }
        var row = selectedRows[0];

        $.messager.confirm("系统提示", "您确认要删除角色<font color=red>"
        + row.name+ "</font>吗？", function (r) {
            if (r) {
                $.post( getContextPath ()+"/role/delete.do", {
                    roleId: row.id
                }, function (data) {
                    if (data.result=='success') {
                        $.messager.alert("系统提示", "数据已成功删除！");
                        $("#dg").datagrid("reload");
                    }else if(data.result=='have')  {
                        $.messager.alert("系统提示", "角色正在使用中，无法删除！");
                    }else{
                        $.messager.alert("系统提示", "数据删除失败！");
                    }
                }, "json");
            }
        });
    });


    //保存按钮
    $('#saveDialog').click(function() {
        if (findCheckedIds()==null) {
            $.messager.alert("系统提示", "请选择权限！");
            return;
        }
        $.ajax({
            url:url,
            async: true,
            data: {
                rightIds: str,
                id: $('#id').val(),
                kv: $('#kv').val(),
                name: $('#name').val(),
                remark: $('#remark').val()
            },
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function (data) {
                if (data.result == "success") {
                    $("#dlg").dialog("close");
                    $("#dg").datagrid("reload");
                    $.messager.alert("系统提示", "操作成功！");
                } else {
                    $.messager.alert("系统提示", "操作失败！");
                }
            },
            error: function () {
                $.messager.alert("系统提示", "系统异常！");
            }
        });
    });
    //关闭按钮
    $('#closeDialog').click(function(){
           $("#dlg").dialog("close");
            $("#fm").form('clear');

    });

});

function resetValue() {
    $("#fm").form('clear');
}

function showDetail(roleId){
    $("#roleId").val(roleId);
    $('#showtree').tree({
        url:getContextPath()+"/role/showRightTree.do?roleId="+roleId,
        checkbox:false
    });
    $("#showdlg").dialog("open").dialog("setTitle", "角色权限信息");

}


