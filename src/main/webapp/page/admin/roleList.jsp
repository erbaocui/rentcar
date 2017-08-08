<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="roleList.js"></script>

</head>
<body style="margin:1px;">

<table id="dg">
</table>
<div id="tb">
    <div>
        <tabel>

            <tr>
                <td>&nbsp;角色标识：&nbsp;<input type="text" id="qryKv" size="20"/><td>
                <td>&nbsp;角色名称：&nbsp;<input type="text" id="qryName" size="20"/><td>
                <td><a id="searchRole" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a> </td>
            </tr>
        </tabel>
    </div>
    <div>
        <a  id="openAddDialog"  class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a  id="openModifyDialog" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a  id="deleteRole" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 500px;height:600px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">

        <input type="hidden" id="id" name="id" value="">
        <div style="margin:5px 0;">
        <tabel>
            <tr>
                <td>角色标识：</td>
                <td><input type="text" id="kv" name="kv" class="easyui-validatebox" data-options="required: true,validType:['CheckRole']"/><td>
            </tr>
            <tr>
                <td>角色名称：</td>
                <td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/><td>
            </tr>
        </tabel>
        </div>
        <div style="margin:5px 0;"></div>
        <div  class="easyui-panel" style="padding:5px;width: 430px">
            <ul id="tt" class="easyui-tree">
            </ul>
        </div>
        <div style="margin:10px 0;"></div>
        <tabel>
            <tr>
                <td>备&nbsp;&nbsp;&nbsp;注：</td>
                 <td> <textarea id="remark" name="remark"></textarea></td>
            </tr>
        </tabel>

    </form>
</div>


<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="showdlg" class="easyui-dialog"
     style="width: 400px;height:416px;padding: 0px 0px" closed="true">
        <div  class="easyui-panel" style="padding:0px;width:386px">
            <ul id="showtree" class="easyui-tree">
            </ul>
        </div>

</div>
</body>
</html>