<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->

    <script type="text/javascript" src="orderHList.js"></script>

</head>
<body style="margin:1px;">
<input type="hidden" id="orderId" name="orderId" value="<%=(String)request.getParameter("orderId")%>" />
<table id="dg">
</table>


<div id="dlg" class="easyui-dialog"
     style="width: 630px;height:650px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table>
            <tr>
                <td>订单no：</td><td colspan="3"><input type="text" id="orderNo" name="orderNo" disabled="disabled"/></td>

            </tr>
            <tr>
                <td>操作人：</td><td><input type="text" id="optName" name="ptId"  disabled="disabled" /></td>
                <td>操作时间：</td><td><input type="text" id="optTime" name="optTime" disabled="disabled"/></td>

            </tr>
            <tr>
                <td>订单类型：</td><td><input type="text" id="type" name="type"  disabled="disabled" /></td>
                <td>订单状态：</td><td><input type="text" id="orderStatus" name="orderStatus" disabled="disabled"/></td>

            </tr>
            <tr >
                <td>用车时间：</td><td> <input  type="text" id="startTime" name="startTime"  disabled="disabled"/></td>
                <td></td><td></td>
              </tr>
            <tr id="tenancyDiv">
                <td>租期：</td>
                <td ><input type="text" id="tenancy" name="tenancy" disabled="disabled" ></td>

            </tr>

            <tr>
                <td>出发地点</td><td colspan="3"><input type="text" id="startAddress" name=startAddress" size="50" disabled="disabled"/></td>

            </tr>
            <tr>
                <td>下车地点</td><td colspan="3"><input type="text" id="endAddress" name="endAddress" size="50" disabled="disabled" /></td>
            </tr>
            <tbody id="orderExDiv">


            <tr>
                <td>单程/往返</td><td><input type="text" id="single" name="single" disabled="disabled"/></td>
                <td>接送机/车</td><td><input type="text" id="fetchSend" name="fetchSend" disabled="disabled" /></td>
            </tr>

            <tr>
                <td>航班/车次</td><td><input type="text" id="flightTrain" disabled="disabled"/></td>
            </tr>
            </tbody>
            <tr>
                <td>总计</td><td><input type="text" id="total"  disabled="disabled" /></td>
                <td>费用</td><td><input type="text" id="fee"  disabled="disabled" /></td>
            </tr>
            <tr>
                <td>乘客类型</td>
                <td><input type="text" id="customerType" name="customerType" disabled="disabled" />

                </td>
                <td>乘客选择车型</td><td><input type="text" id="customerCarType" disabled="disabled" /></td>
            </tr>
            <tr>
                <td>乘客姓名</td><td><input type="text" id="customerName" name="customerName" disabled="disabled" /></td>
                <td>乘客电话</td><td><input type="text" id="customerMobile" name="customerMobile"  disabled="disabled" /></td>
            </tr>
            <tr>
                <td>车牌</td><td><input type="text" id="plateNumber" name="plateNumber" disabled="disabled"/></td>

                <td>车型</td><td><input type="text" id="carType" name="carTyp" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>司机姓名</td><td><input type="text" id="driverName" name="driverName" disabled="disabled"/></td>
                <td>司机电话</td><td><input type="text" id="driverMobile" name="driverMobile" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>供应商</td><td><input type="text" id="supplierName" name="supplierName" disabled="disabled"/></td>

                <td></td><td></td>
            </tr>

            <tr>
                <td>备注</td>
                <td colspan="3">
                    <textarea id="remark" name="remark" rows="3" cols="40" disabled="disabled"></textarea>
                </td>

            </tr>

        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>