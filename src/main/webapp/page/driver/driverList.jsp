<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="driverList.js"></script>

</head>
<body style="margin:1px;">
<table id="dg">
</table>
<div id="tb">
    <div>
        <tabel>

            <tr>
                <td>&nbsp;姓名：&nbsp;<input type="text" id="qryName" size="20"/><td>
                <td>&nbsp;手机：&nbsp;<input type="text" id="qryMobile" size="20"/><td>
                <td>&nbsp;状态：&nbsp;
                    <select id="qryStatus" name="qryStatus" >
                        <option value="-1">全部</option>
                        <option value="0">有效</option>
                        <option value="1">无效</option>
                    </select>
                <td>
                <td><a id="search" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a> </td>

            </tr>
        </tabel>
    </div>
    <div>
        <a  id="openAddDialog"  class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a  id="openModifyDialog" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
       <%-- <a  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>--%>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 420px;height:280px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id">
        <table cellspacing="8px">
            <tr>
                <td>姓&nbsp;&nbsp;&nbsp;名：</td>
                <td><input type="text" id="name"  name="name"
                           class="easyui-validatebox" required="true"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>手&nbsp;&nbsp;&nbsp;机：</td>

                <td><input type="text" id="mobile" name="mobile"
                           class="easyui-validatebox" data-options="required: true,validType:['CheckDriver']"/>&nbsp;
                </td>
            </tr>

            <tr id="statusDisplay">
                    <td>状&nbsp;&nbsp;&nbsp;态：</td>
                    <td>
                        <select id="status" name="status">
                          <option value="0"  selected="selected" >有效</option>
                          <option value="1">无效</option>
                      </select>
                    </td>
                </tr>

            <tr>

                <td>备&nbsp;&nbsp;&nbsp;注：</td>
                <td><textarea id="remark" name="remark"></textarea>&nbsp;</td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>