/**
 * Created by home on 2017/7/7.
 */
var url;
$(document).ready(function(){

    //表格格式化
    $('#dg').datagrid({
        title:"签证图片管理",
        toolbar:"#tb",
        url:getContextPath()+"/picture/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:100,checkbox:true},
            {field:'name',title:'名称',width:'10%'},
            {field:'type',title:'图片类型',width:'10%',align:'center',
                formatter: commonFormatter.picType
            },
            {field: 'status', title: '状态', width:'10%',align:'center',
                formatter: commonFormatter.status
            } ,
            {field:'seq' ,title:'排序',width:'10%'},
            {field:'url' ,title:'url',width:'25%'},
            {field:'remark',title:'备注',width:'25%'},
            {field:'_operator',title:'查看大图',width:"9.5%",align:'center',formatter:function(val,row,index){
                return '<a href="#" onclick="showDetail(\''+row.url+'\')">查询详情</a>';
            }},
            {field:'id',title:'',width:100,hidden:true}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "name": $("#qryPicName").val(),
            "type":$("#qryPicType").val(),
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
                $(this) .datagrid('mergeCells', { index:0, field: 'name', colspan:7 });
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
    $('#searchUser').click(function(){
        $("#dg").datagrid('load', {
            "name": $("#qryPicName").val(),
            "type":$("#qryPicType").val(),
            "status":$("#qryStatus").val()
        });
    });
    //新增窗口
    $('#openAddDialog').click(function(){
        $("#fm").form('clear');
        initUpload();
        $("#statusDisplay").hide();
        $("#type").val("4");
        $("#showImg").attr("src","");
        url = getContextPath ()+"/picture/add.do";
        $("#dlg").dialog({title: "添加签证图片信息",modal:true});
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
        initUpload();
        $('#fm').form('load', row);
        $("#status").val(row.status);
        $("#showImg").attr("src",getContextPath()+row.url);
        $("#statusDisplay").show();
        url = getContextPath ()+"/picture/modify.do";
        $("#dlg").dialog({title: "编辑签证图片信息",modal:true});
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

        $.messager.confirm("系统提示", "您确认要删除图片<font color=red>"
        + row.name+ "</font>吗？", function (r) {
            if (r) {
                $.post( getContextPath ()+"/picture/delete.do", {
                    id: row.id
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
    $('#saveDialog').click(function(){
        if( $("#url").val()==null||$("#url").val()==""){
            $.messager.alert("系统提示", "请先上传图片");
            return;
        }
        $("#fm").form("submit", {
            url: url,
            dataType: "json",
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
                var json = $.parseJSON(data);
                if (json.result == "success") {
                $.messager.alert("系统提示", "保存成功");
                $("#dg").datagrid("reload");
                $("#fm").form('clear');
                $("#dlg").dialog("close");
                }
            }
        });
    });
    //关闭按钮
    $('#closeDialog').click(function(){
        $("#dlg").dialog("close");
        $("#fm").form('clear');
    });
});


function showDetail(url){
    window.open(getContextPath ()+"/page/picture/detail.jsp?url="+url);

}
function initUpload(){
    $("#uploadify").uploadify({
        'swf' : getContextPath()+'/js/plugins/uploadfiy/uploadify.swf',

        'uploader' :getContextPath()+'/file/upload.do',//flash文件的相对路径
        'fileDataName':"Filedata", 						//设置上传文件名称,默认为Filedata
        //'cancelImg': 'images/cancel.png', 			//每一个文件上的关闭按钮图标
        'queueID': 'fileQueue', 					//文件队列的ID，该ID与存放文件队列的div的ID一致
        'queueSizeLimit': 1, 							//当允许多文件生成时，设置选择文件的个数，默认值：999
        'fileDesc': '*.jpg;*.gif;*.png;*.ppt;*.pdf;*.jpeg', 	//用来设置选择文件对话框中的提示文本
        'fileExt': '*.jpg;*.gif;*.png;*.ppt;*.pdf;*.jpeg', 		//设置可以选择的文件的类型
        'auto': true, 								//设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
        'multi': false, 								//设置为true时可以上传多个文件
        'simUploadLimit': 1, 						//允许同时上传的个数 默认值：1
        'sizeLimit': 2048000,						//上传文件的大小限制
        'buttonText': '上传图片',						//浏览按钮的文本，默认值：BROWSE
        'displayData': 'percentage',     			//上传队列显示的数据类型，percentage是百分比，speed是上传速度
        //回调函数
        'onComplete': function (evt, queueID, fileObj, response, data) {

        },
        'onError': function (event, queueID, fileObj, errorObj) {
            if (errorObj.type === "File Size") {
                alert("文件最大为3M");
                $("#uploadify").uploadifyClearQueue();
            }
        },
        'onQueueFull': function (event, queueSizeLimit) {
            alert("最多上传" + queueSizeLimit + "张图片");
            return false;
        },
        'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）

            $("#showImg").attr("src",getContextPath()+data);
            $("#url").val(data);
        }
    });
}


