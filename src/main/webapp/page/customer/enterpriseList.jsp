<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="enterpriseList.js"></script>

</head>
<body style="margin:1px;">
<table id="dg">
</table>
<div id="tb">
    <div>
        <tabel>

            <tr>
                <td>&nbsp;登录名：&nbsp;<input type="text" id="qryLoginName" size="20"/><td>
                <td>&nbsp;昵&nbsp;&nbsp;称：&nbsp;<input type="text" id="qryDisplayName" size="20"/><td>
                <td>&nbsp;状态：&nbsp;
                    <select id="qryStatus" name="qryStatus" >
                        <option value="-1">全部</option>
                        <option value="0">有效</option>
                        <option value="1">无效</option>

                    </select>
                </td>
                <td><a id="search" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a> </td>
            </tr>
        </tabel>
    </div>
    <div>
        <a  id="openAddDialog"  class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a  id="openModifyDialog" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a  id="openRestePwDialog" class="easyui-linkbutton" iconCls="icon-edit" plain="true">密码重置</a>
        <a  id="export" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出Excel</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 420px;height:420px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id">
        <input type="hidden" id="type" name="type" value="1">
        <table cellspacing="8px">
            <tr>
                <td>登录名：</td>

                <td><input type="text" id="loginName" name="loginName"
                           class="easyui-validatebox" data-options="required: true,validType:['CheckCustomer[\'#type\']']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>显示名：</td>
                <td><input type="text" id="displayName"  name="displayName"
                           class="easyui-validatebox" required="true"/>&nbsp;
                </td>
            </tr>
            <tr id="passwordDisplay">
                <td>密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" id="password"  name="password"
                           class="easyui-validatebox"  required="true" validType="CheckCustomerPw" />&nbsp;

                </td>
            </tr>
            <tr id="checkPasswordDisplay">
                <td>确认密码：</td>
                <td><input type="password" id="checkPassword" name="checkPassword"
                           class="easyui-validatebox" required="true" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" />&nbsp;

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
            <tr >
                <td>税&nbsp;&nbsp;&nbsp;号：</td>
                <td><input type="text" id="taxId"  name="taxId"
                           class="easyui-validatebox" data-options="required:true" />&nbsp;

                </td>
            </tr>
            <tr >
                <td>联&nbsp;系&nbsp;人：</td>
                <td><input type="text" id="contact"  name="contact"
                           class="easyui-validatebox" data-options="required:true" />&nbsp;

                </td>
            </tr>
            <tr >
                <td>联系人电话：</td>
                <td><input type="text" id="contactMobile"  name="contactMobile"
                           class="easyui-validatebox" required="true" validType="mobile"/>&nbsp;

                </td>
            </tr>

            <tr>

                <td>备&nbsp;&nbsp;&nbsp;注：</td>
                <td><textarea id="remark" name="remark"></textarea>&nbsp;</td>
            </tr>
        </table>
    </form>
</div>


<div id="resetPwDialog" class="easyui-dialog"
     style="width: 350px;height:210px;padding: 10px 20px" closed="true"
     buttons="#pwDlg-buttons">
    <form id="pwfm" method="post">
        <input type="hidden" id="pwId" name="pwId">
        <table cellspacing="8px">


            <tr >
                <td>密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" id="resetPassword"  name="resetPassword"
                           class="easyui-validatebox" data-options="required:true" />&nbsp;

                </td>
            </tr>
            <tr>
                <td>确认密码：</td>
                <td><input type="password" id="resetCheckPassword"
                           class="easyui-validatebox" required="true" validType="equalTo['#resetPassword']" invalidMessage="两次输入密码不匹配" />&nbsp;

                </td>
            </tr>
        </table>
    </form>
</div>


<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="pwDlg-buttons">
    <a id="pwSaveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="pwCloseDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>