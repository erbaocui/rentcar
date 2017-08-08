<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="zero.css" />
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="orderList.js"></script>
    <script type="text/javascript" src="orderDetail.js"></script>
    <script language="javascript" src="http://webapi.amap.com/maps?v=1.2&key=c8d499635271ab4f9d449d35911e2cf1"></script>
    <script language="javascript">
        <%@include file="map.js"%>
    </script>


</head>
<body style="margin:1px;">
<table id="dg">
</table>
<div id="tb">
    <div>
        <tabel>
            <tr>
                <td>&nbsp;开始时间：&nbsp;<input type="text" id="qryBeginTime" size="20" class="Wdate" onfocus="WdatePicker({readOnly:true,isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm'})" /><td>
                <td>&nbsp;结束时间：&nbsp;<input type="text" id="qryEndTime" size="20" class="Wdate" onfocus="WdatePicker({readOnly:true,isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm'})" /><td>
                <td>&nbsp;客户姓名：&nbsp;<input type="text" id="qryCustomeName" size="20"/><td>
                <td>&nbsp;供应姓名：&nbsp;<input type="text" id="qrySupplierName" size="20"/><td>
                <td>&nbsp;订单状态：&nbsp;
                 <select id="qryOrderStatus" name="qryOrderStatus" >
                     <option value="0">全部状态</option>
                     <option value="1">等待处理</option>
                     <option value="2">预约成功</option>
                     <option value="3">订单完成</option>
                     <option value="4">订单取消</option>
                     <option value="5">订单完结</option>
                 </select>
                <td>
                <td><a id="search" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a> </td>
            </tr>
        </tabel>
    </div>
    <div>
        <a  id="openAddDialog"  class="easyui-linkbutton" iconCls="icon-add" plain="true">订单添加</a>
        <a  id="openModifyDialog" class="easyui-linkbutton" iconCls="icon-edit" plain="true">订单流转</a>
        <a  id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改信息</a>
        <a  id="history" class="easyui-linkbutton" iconCls="icon-remove" plain="true">订单历史</a>
        <td><a id="export" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出Excel</a> </td>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 630px;height:650px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <%@include file="/page/order/orderDetail.jsp"%><!--静态包含-->
    </form>
</div>
<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>