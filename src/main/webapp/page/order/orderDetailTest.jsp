<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="orderDetail.js"></script>

</head>
<body style="margin:1px;">
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-blank'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cut'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no'">添加</a>
<a id="btn"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" >添加</a>
<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">添加</a>
<a id="btn"  class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-sum'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-mini-add'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-mini-edit'">添加</a>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-mini-refresh'">添加</a>
<table>
    <tr>
        <td colspan="4">订单基本信息</td>

    </tr>
    <tbody id="orderNoDiv">
        <tr>
            <td>订单no</td><td><input type="text" id="order_no" name="order_no" disabled="disabled"></td>
            <td>创建时间：</td><td><input type="text" id="create_time" name="create_time"disabled="disabled"></td>
        </tr>
    </tbody>
  <tr>
    <td>订单类型：</td><td><select id="type" name="type" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"   editable="false">
                              <option value="1"  selected="selected">日租包车</option>
                              <option value="2">机场接送</option>
                              <option value="3">车站接送</option>
                              <option value="4">月租包车</option>
                              <option value="5">企业用车</option>
                            </select></td>
    <td>订单状态：</td><td><select id="status" name="status" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"   editable="false">
                               <option value="1"  selected="selected">等待处理</option>
                                <option value="2">预约完成</option>
                                 <option value="3">订单完成</option>
                                 <option value="4">订单完成</option>
                            </select>
                         </td>
  </tr>
    <tr >
        <td>用车时间：</td><td><input type="text" id="startTime" name="startTime"  /><img onclick="WdatePicker({el:'startTime'})" src="<%=request.getContextPath()%>/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
        <td></td><td></td>
    </tr>
    <tr id="tenancyDiv">
        <td>租期：</td>
        <td >
            <select id="tenancy" name="tenancy" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"   editable="false">
                <option value="1" selected="selected">单次</option>
                <option value="2">半天</option>
                <option value="3">全天</option>
            </select>
        </td>

    </tr>
    <tr>
        <td>出发地点</td><td><input type="text" id="startAddress" name=startAddress" class="easyui-validatebox" data-options="required:true" /></td>
        <td>下车地点</td><td><input type="text" id="endAddress" name="endAddress" class="easyui-validatebox" data-options="required:true" /></td>

    </tr>
    <tbody id="orderExDiv">
    <tr>
        <td colspan="4">订单扩展信息</td>
    </tr>

    <tr>
        <td>单程/往返</td><td><select id="single" name="single" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"   editable="false">
                                <option value="0"  selected="selected">单程</option>
                                <option value="1">往返</option>
                              </select>
                          </td>
        <td>接送机/车</td><td><select id="fetchSend" name="fetchSend" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"   editable="false">
                                  <option value="0"  selected="selected">接</option>
                                <option value="1">送</option>
                              </select>
                            </td>
    </tr>

    <tr>
        <td>航班/车次</td><td><input type="text" id="flightTrain"></td>
    </tr>
    </tbody>
    <tr>
        <td colspan="4">订单费用</td>
    </tr>

    <tr>
        <td>总计</td><td><input type="text" id="total"/></td>
        <td>费用</td><td><input type="text" id="fee" /></td>
    </tr>
    <tr>
        <td colspan="4">乘客信息</td>
    </tr>
    <tr>
        <td>乘客类型</td>
        <td><select id="customerType" name="customerType" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"   editable="false">
                <option value="0"  selected="selected">个人</option>
                <option value="1"  >企业</option>
              </select>
        </td>
        <td>乘客选择车型</td><td><input type="text" id="customerCarType" disabled="disabled" /></td>
   </tr>
    <tr>
        <td>乘客姓名</td><td><input type="text" id="customerName" name="driverName" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150 "/></td>
        <td>乘客电话</td><td><input type="text" id="customerMobile" name="customerMobile"  disabled="disabled" /></td>
    </tr>
    <tr>
        <td colspan="4">车辆信息</td>
    </tr>
    <tr>
        <td>车牌</td><td><input type="text" id="plateNumber" name="plateNumber" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150 "/></td>

        <td>车型</td><td><input type="text" id="carType" name="carTyp" disabled="disabled"/></td>
    </tr>
    <tr>
        <td colspan="4">司机信息</td>
    </tr>
    <tr>
        <td>司机姓名</td><td><input type="text" id="driverName" name="driverName" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150 "/></td>
        <td>司机电话</td><td><input type="text" id="driverMobile" disabled="disabled"/></td>
    </tr>
    <tr>
        <td colspan="4">供应商信息</td>
    </tr>
    <tr>
        <td>供应商</td><td><input type="text" id="supplierName" name="supplierName" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150 "/></td>

        <td></td><td></td>
    </tr>
    <tr>
        <td colspan="4">备注</td>
    </tr>
    <tr>
        <td>备注</td><td><input type="text" id="remark" name="remark"></td>

    </tr>
    <input type="button" id="test" value="测试" />

</table>

<div id="p" class="easyui-panel" style="width:500px;height:200px;padding:10px;border-radius: 24px"
     iconCls="icon-save"
     collapsible="true" minimizable="true"
     maximizable=true closable="true">
    <p>panel</p>
    <p>panel</p>
    <p>panel</p>

</div>
</body>
</html>