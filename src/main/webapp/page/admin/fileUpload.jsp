<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="fileUpload.js"></script>
</head>

<body>
<form id="fm" method="post" enctype="multipart/form-data">
<div id="fileQueue"></div>
<input type="file" name="uploadify" id="uploadify" />
<p>
    <!-- 上传第一个未上传的文件 -->
    <a href="javascript:$('#uploadify').uploadify('upload')">上传</a>
    <!-- 取消第一个未取消的文件 -->
    <a href="javascript:$('#uploadify').uploadify('cancel')">取消上传</a>

    <a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传所有文件</a>&nbsp;
    <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消所有上传</a>
</p>
    <img id="showImg" src="" alt="">
</form>
</body>

</html>