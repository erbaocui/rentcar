<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/plugins/uploadfiy/uploadify.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/uploadfiy/jquery.uploadify.js"></script>
    <script type="text/javascript" src="visaList.js"></script>

</head>
<body style="margin:1px;">

</table>
<table id="dg">
</table>
<div id="tb">
    <div>
        <tabel>

            <tr>
                <td>&nbsp;签证图片名称：&nbsp;<input type="text" id="qryPicName" name="qryPicName" size="20"/><td>
                <input type="hidden" id="qryPicType" name="qryPicType" value="4" />
                <td>&nbsp;状态：&nbsp;
                    <select id="qryStatus" name="qryStatus" >
                        <option value="-1">全部</option>
                        <option value="0">有效</option>
                        <option value="1">无效</option>

                    </select>
                <td>
                <td><a id="searchUser" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a> </td>

            </tr>
        </tabel>
    </div>
    <div>
        <a  id="openAddDialog"  class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a  id="openModifyDialog" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <%--<a  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>--%>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 420px;height:550px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post" enctype="multipart/form-data">
        <input type="hidden" id="id" name="id">
        <table cellspacing="8px">



            <tr>
                <td>签证图片名称：</td>
                <td><input type="text" id="name" name="name"
                           class="easyui-validatebox" data-options="required: true"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>签证图片顺序：</td>
                <td><input type="text" id="seq" name="seq"
                           class="easyui-validatebox" data-options="required: true,validType:['number']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>签证图片url：</td>
                <td><input type="text" id="url" name="url" readonly  unselectable="on" />&nbsp;
                </td>
            </tr>
            <tr>
                <input type="hidden" id="type" name="type" value="4" />

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
          <table>
            <tr>


                <td>   <img id="showImg" src="" style="width:200px;height:100px;padding: 10px 20px"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="fileQueue"></div>
                    <input type="file" name="uploadify" id="uploadify" />
                </td>
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