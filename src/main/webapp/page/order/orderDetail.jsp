<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<input type="hidden" id="id" name="id">


        <table>
    <tbody id="orderNoDiv">
        <tr>
            <td>订单No</td><td><input type="text" id="orderNo" name="orderNo" disabled="disabled"></td>
            <td>创建时间：</td><td><input type="text" id="createTime" name="createTime"disabled="disabled"></td>
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
    <td>下一个状态：</td><td><select id="status" name="status" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"   editable="false">
                               <option value="1" selected="selected">等待处理</option>
                                <option value="2">预约完成</option>
                                 <option value="3">订单完成</option>
                                 <option value="4">订单取消</option>
                            </select>
                         </td>
  </tr>
    <tr >
        <td>用车时间：</td><td> <input class="Wdate" type="text" id="startTime" name="startTime"  onfocus="WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm'})" /></td>
        <%--<input type="text" id="startTime" name="startTime"  /><img onclick="WdatePicker({el:'startTime'})" src="<%=request.getContextPath()%>/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" dateFmt="yyyy-M-d HH:mm:ss"></td>
      --%>
        <td>当前订单状态</td><td><input type="text" id="orderStatus" name="orderStatus" disabled="disabled"/></td>
        <input type="hidden" id="orderStatusValue" name="orderStatusValue"/>
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
        <td>出发地点</td><td colspan="3">
        <input type="text" id="startAddress" name=startAddress" size="50" />
        <div id="result1" class="autobox" style="z-index:999"  name="result1"></div>
       </td>

    </tr>
    <tr>
        <td>下车地点</td><td colspan="3"><input type="text" id="endAddress" name="endAddress" size="50" />
        <div id="result2" class="autobox" style="z-index:999"  name="result1"></div>
        </td>
    </tr>
    <tbody id="orderExDiv">


    <tr>
        <td>单程/往返</td><td><select id="single" name="single" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"    editable="false" />

                              </select>
                          </td>
        <td>接送机/车</td><td><input id="fetchSend" name="fetchSend" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150"   editable="false" />


                            </td>
    </tr>

    <tr>
        <td>航班/车次</td><td><input type="text" id="flightTrain"></td>

    </tr>
    </tbody>
    <tr>
        <td>总计</td><td><input type="text" id="total"   class="easyui-validatebox" data-options="validType:['m']"/></td>
        <td>费用</td><td><input type="text" id="fee"     class="easyui-validatebox" data-options="validType:['m']"/></td>
    </tr>
     <tr>
         <td>佣金</td><td><input type="text" id="commission" disabled="disabled"/></td>
         <td></td><td></td>
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
        <td>乘客姓名</td><td><input type="text" id="customerName" name="customerName" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150 "/></td>
        <td>乘客电话</td><td><input type="text" id="customerMobile" name="customerMobile"  disabled="disabled" /></td>
    </tr>
    <tr>
        <td>车牌</td><td><input type="text" id="plateNumber" name="plateNumber" class="easyui-combobox" data-options="valueField:'id', textField:'text', panelHeight:'auto',width:150 "/></td>

        <td>车型</td><td><input type="text" id="carType" name="carTyp" disabled="disabled"/></td>
    </tr>
    <tr>
        <td>司机姓名</td><td><input type="text" id="driverName" name="driverName" class="easyui-combobox" data-options="valueField:'id', textField:'name', panelHeight:'auto',width:150 "/></td>
        <td>司机电话</td><td><input type="text" id="driverMobile" name="driverMobile" disabled="disabled"/></td>
    </tr>
    <tr>
        <td>供应商</td><td><input type="text" id="supplierName" name="supplierName" class="easyui-combobox" data-options="valueField:'id', textField:'name', panelHeight:'auto',width:150 "/></td>

        <td></td><td></td>
    </tr>

    <tr>
        <td>备注</td>
        <td colspan="3">
            <textarea id="remark" name="remark" rows="3" cols="40">
            </textarea>
       </td>
    </tr>
</table>
<div id="iCenter" style="display:none"></div>
<div id="result"></div>
