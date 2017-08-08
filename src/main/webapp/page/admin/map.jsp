<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>地图服务</title>
  <link rel="stylesheet" type="text/css" href="zero.css" />

  <%@include file="/page/common/header.jsp"%><!--静态包含-->

  <script language="javascript" src="http://webapi.amap.com/maps?v=1.2&key=c8d499635271ab4f9d449d35911e2cf1"></script>
  <script language="javascript">


    <%@include file="map.js"%>

  </script>
</head>
<body >
<input type="button" id="test" value="测试" />
<div id="dlg" class="easyui-dialog"
     style="width: 630px;height:650px;padding: 10px 20px" closed="true"
     >
  <form id="fm" method="post">
    <table style="width: 100%;">
      <tr><td>
        <input type="text" id="startAddress" name=startAddress" value=""  style="width: 30%;" />
        <div id="result1" class="autobox" style="z-index:999"  name="result1"></div>
      </td></tr>
      <tr><td>
        <input type="text" id="endAddress" name="endAddress" value="" style="width: 30%;"/>
        <div id="result2" class="autobox" style="z-index:999"  name="result2"></div>
      </td></tr>
    </table>
  </form>
</div>


</div>
<div id="iCenter"></div>
<div id="result"></div>
<!-- tongji begin-
<!-- tongji end -->

</table>
</body>
<script language="javascript">

  <%@include file="map.js"%>

</script>
</html>
