<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="combobox.js"></script>
</head>

<body>
<form id="fm" method="post" enctype="multipart/form-data">
    <input class="easyui-combobox shopVipInfoInput" name="idname"  panelHeight="auto" id="idname" />
    <input type="button" name="btn" id="btn" value="确定" />

</form>
</body>

</html>