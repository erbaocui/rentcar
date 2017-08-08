<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>租车系统</title>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript">
    <%@include file="main.js"%><!--静态包含-->
    </script>
</head>
<body class="easyui-layout">

<div data-options="region:'north',border:false" style="height:60px;padding:10px">
    <table width="100%">
        <tr>
            <td width="50%"></td>
            <td valign="bottom"
                style="font-size: 20px;color:#8B8B8B;font-family: '楷体';"
                align="right" width="50%"><font size="3">&nbsp;&nbsp;<strong>当前用户：</strong>${user.displayName}</font>
                |<a href="javascript:logout()">退出</a>
            </td>
        </tr>
    </table>
</div>
<div data-options="region:'west',split:true,title:'导航菜单'" style="width:220px;padding:0px;" title="导航菜单"
     split="true">


        <div  class="easyui-panel" style="padding:0px;height:100%">
            <ul id="showtree" class="easyui-tree" style="padding:0px;width:200px;height:98%">
            </ul>
        </div>

</div>
<div data-options="region:'center',title:''">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">

        </div>
    </div>
</div>
</body>
</html>