<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->

    <script type="text/javascript" src="statisticsList.js"></script>


</head>
<body style="margin:1px;">
<table id="dg">
    <tbody id="emptyBody">

    </tbody>
</table>
<div id="tb">
    <div>
        <tabel>
            <tr>
                <td>&nbsp;开始时间：&nbsp;<input type="text" id="beginTime" size="20" class="Wdate" onfocus="WdatePicker({readOnly:true,isShowClear:true,dateFmt:'yyyy-MM-dd'})" /><td>
                <td>&nbsp;结束时间：&nbsp;<input type="text" id="endTime" size="20" class="Wdate" onfocus="WdatePicker({readOnly:true,isShowClear:true,dateFmt:'yyyy-MM-dd'})" /><td>
                <td>&nbsp;统计类型：&nbsp;<select id="groupType" name="groupType">
                                             <option value="0" selected="selected">客户</option>
                                              <option value="1">供应商</option>
                                          </select>
                </td>
                <td><a id="search" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a> </td>
                <td><a id="export" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出Excel</a> </td>
            </tr>
        </tabel>
    </div>

</div>



</body>
</html>