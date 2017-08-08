/**
 * Created by home on 2017/7/7.
 */

function addInit(){
    commonDataInit();
    addDataInit();
    addEnableInit();
}
function addEditInit(row){
    commonDataInit();
    addEditDataInit(row);
    addEnableInit();
}
function dispatchInit(row){

    commonDataInit();
    dispatchDataInit(row);
    dispathEnableInit();

}
function dispatchEditInit(row){

    commonDataInit();
    dispatchEditDataInit(row);
    dispathEnableInit();
    $('#status').combobox('disable');
}
function finishInit(row){

    commonDataInit();
    finishDataInit(row);
    finishEnableInit();
}


function finishEditInit(row){

    commonDataInit();
    finishDataInit(row);
    finishEnableInit();
    $('#status').combobox('disable');
}
function showInit(row){

    commonDataInit();
    showDataInit(row);
    showEnableInit();
}

function commonDataInit(){
    var data;
    data= [];
    data.push({ "text": "日租包车", "id": 1 });
    data.push({ "text": "机场接送", "id": 2 });
    data.push({ "text": "车站接送", "id": 3 });
    data.push({ "text": "月租包车", "id": 4 });
    data.push({ "text": "企业用车", "id": 5 });
    $("#type").combobox("loadData", data);
    $('#type').combobox('select',data[0].id);

    data= [];
    data.push({ "text": "单次", "id": 1 });
    data.push({ "text": "半天", "id": 2 });
    data.push({ "text": "全天", "id": 3 });
    $("#tenancy").combobox("loadData", data);
    $('#tenancy').combobox('select',data[0].id);

    $("#type").combobox({
        onChange: function (n, o) {
            $("#tenancy").combobox('clear');
            $("#tenancyDiv").hide();
            type=Number($(this).val());
            switch(type)
            {
                case 1:
                    data= [];
                    data.push({ "text": "单次", "id": 1 });
                    data.push({ "text": "半天", "id": 2 });
                    data.push({ "text": "全天", "id": 3 });
                    $("#tenancy").combobox("loadData", data);
                    $('#tenancy').combobox('select',data[0].id);
                    $("#tenancyDiv").show();
                    $("#orderExDiv").hide();
                    break;
                case 2:
                    //单程
                    data = [];
                    data.push({"id":0,"text":"单程"});
                    data.push({"id":1,"text":"往返"});
                    $("#single").combobox("loadData", data);
                    $('#single').combobox('select',data[0].id);
                    //接送
                    data = [];
                    data.push({"id":0,"text":"接"});
                    data.push({"id":1,"text":"送"});
                    $("#fetchSend").combobox("loadData", data);
                    $('#fetchSend').combobox('select',data[0].id);
                    $("#tenancyDiv").hide();
                    $("#orderExDiv").show();
                    break;
                case 3:
                    //单程
                    data = [];
                    data.push({"id":0,"text":"单程"});
                    data.push({"id":1,"text":"往返"});
                    $("#single").combobox("loadData", data);
                    $('#single').combobox('select',data[0].id);
                    //接送
                    data = [];
                    data.push({"id":0,"text":"接"});
                    data.push({"id":1,"text":"送"});
                    $("#fetchSend").combobox("loadData", data);
                    $('#fetchSend').combobox('select',data[0].id);
                    $("#tenancyDiv").hide();
                    $("#orderExDiv").show();
                    break;
                case 4:
                    data = [];
                    data.push({"id":4,"text": "短租(1-3月)"});
                    data.push({"id":5,"text": "长租（4-12月）"});
                    $("#tenancy").combobox("loadData", data);
                    $('#tenancy').combobox('select',data[0].id);
                    $("#tenancyDiv").show();
                    $("#orderExDiv").hide();
                    break;
                case 5:
                    data = [];
                    data.push({"id":6,"text":"3年"});
                    data.push({"id":7,"text":"4年"});
                    data.push({"id":8,"text":"5年"});
                    data.push({"id":9,"text":"6年"});
                    data.push({"id":10,"text":"7年"});
                    data.push({"id":11,"text":"8年"});
                    data.push({"id":12,"text":"9年"});
                    data.push({"id":13,"text": "10年"});
                    $("#tenancy").combobox("loadData", data);
                    $('#tenancy').combobox('select',data[0].id);
                    $("#tenancyDiv").show();
                    $("#orderExDiv").hide();
                    break;
                default:
                    data = [];
                    $("#tenancy").combobox("loadData", data);
                    break;
            }

        }
    });
    //状态
    data = [];
    data.push({"id":1,"text":"等待处理"});
    data.push({"id":2,"text":"预约完成"});
    data.push({"id":3,"text":"订单完成"});
    data.push({"id":4,"text":"订单取消"});
    $("#status").combobox("loadData", data);
    $('#status').combobox('select',data[0].id);
    //单程
    data = [];
    data.push({"id":0,"text":"单程"});
    data.push({"id":1,"text":"往返"});
    $("#single").combobox("loadData", data);
    $('#single').combobox('select',data[0].id);
    //接送
    data = [];
    data.push({"id":0,"text":"接"});
    data.push({"id":1,"text":"送"});
    $("#fetchSend").combobox("loadData", data);
    $('#fetchSend').combobox('select',data[0].id);
    //客户类型
    data = [];
    data.push({"id":0,"text":"个人"});
    data.push({"id":1,"text":"企业"});
    $("#customerType").combobox("loadData", data);
    $('#customerType').combobox('select',data[0].id);
    //乘客类型
    $("#customerType").combobox({
        // "data":data ,
        //"value":data[0].id,
        onChange: function (n, o) {
            $("#customerMobile").val("");
            $('#customerName').combobox({
                method:'post',
                url:getContextPath()+"/customer/validList.do?type="+ $("#customerType").combobox('getValue'),
                valueField:'id',
                textField:'displayName',
                onSelect: function (record) {
                    if ($("#customerMobile").val("") != null && $("#customerMobile").val("") != "") {

                        if ($("#customerType").combobox('getValue') == 0) {
                            $("#customerMobile").val(record.loginName);

                        } else {
                            $("#customerMobile").val(record.contactMobile);
                        }
                    }
                },
                onLoadSuccess:function () {
                 }
            });
        }
    });


}
function addDataInit(){




    //乘客选择
    $("#customerMobile").val("");
    $('#customerName').combobox('clear');
    $('#customerName').combobox({
        method:'get',
        url:getContextPath()+"/customer/validList.do?type="+ $("#customerType").combobox('getValue'),
        valueField:'id',
        textField:'displayName',
        value:"",
        onSelect: function (record) {
                if ($("#customerType").combobox('getValue') == 0) {
                    $("#customerMobile").val(record.loginName);

                } else {
                    $("#customerMobile").val(record.contactMobile);
                }

        }
    });


}

function addEditDataInit(row){

    var data;
    $('#id').val(row.id);
    $("#orderNo").val(row.orderNo);
    $("#createTime").val(commonFormatter.time(row.createTime,"",""));
    $('#orderStatus').val(commonFormatter.orderStatus(row.status,"",""));
    $('#orderStatusValue').val(row.status);
    $('#type').combobox('setValue',row.type);
    data = [];
    data.push({"id":2,"text":"预约完成"});
    data.push({"id":4,"text":"订单取消"});
    $("#status").combobox("loadData", data);
    $("#status").combobox("setValue",data[0].id);
    $("#startTime").val(commonFormatter.dateTime(row.startTime,"",""));
    if(row.tenancy!=null&&row.tenancy!=""){
        $('#tenancy').combobox("setText",row.tenancy);
    }
    $("#startAddress").val(row.startAddress);
    $("#endAddress").val(row.endAddress);
    if(row.single!=null&&row.single!=""){
        $('#single').combobox("select",row.single);
    }
    if(row.fetchSend!=null&&row.fetchSend!=""){
        $('#fetchSend').combobox("select",row.fetchSend);
    }
    if(row.flightTrain!=null&&row.flightTrain!=""){
        $('#flightTrain').val(row.flightTrain);
    }
    data = [];
    data.push({"id":0,"text":"个人"});
    data.push({"id":1,"text":"企业"});
    $("#customerType").combobox("loadData", data);
    $('#customerType').combobox("select",row.customerType);

    //乘客类型
    $("#customerType").combobox({
        // "data":data ,
        //"value":data[0].id,
        onChange: function (n, o) {
            $("#customerMobile").val("");
            $('#customerName').combobox({
                method:'post',
                url:getContextPath()+"/customer/validList.do?type="+ $("#customerType").combobox('getValue'),
                valueField:'id',
                textField:'displayName',
                onSelect: function (record) {
                    if ($("#customerMobile").val("") != null && $("#customerMobile").val("") != "") {

                        if ($("#customerType").combobox('getValue') == 0) {
                            $("#customerMobile").val(record.loginName);

                        } else {
                            $("#customerMobile").val(record.contactMobile);
                        }
                    }
                },
                 onLoadSuccess:function () {
                 var val = $(this).combobox("getData");
                 $(this).combobox("select", val[0].id);
                 }
            });
        }
    });
    //乘客选择
    $('#customerName').combobox({
        method:'post',
        url:getContextPath()+"/customer/validList.do?type="+row.customerType,
        valueField:'id',
        textField:'displayName',
        onSelect: function (record) {
            if(row.customerMobile==null||row.customerMobile==""){
                if ($("#customerType").combobox('getValue') == 0) {
                    $("#customerMobile").val(record.loginName);

                } else {
                    $("#customerMobile").val(record.contactMobile);
                }
            }
        },
        onLoadSuccess:function () {
            $('#customerName').combobox("select",row.customerId);
            $('#customerMobile').val(row.customerMobile);
        }
    });
    $('#remark').val(row.remark);


}

function dispatchDataInit(row){
    var data;
    $('#id').val(row.id);
    $("#orderNo").val(row.orderNo);
    $("#createTime").val(commonFormatter.time(row.createTime,"",""));
    $('#orderStatus').val(commonFormatter.orderStatus(row.status,"",""));
    $('#orderStatusValue').val(row.status);
    $('#type').combobox('setValue',row.type);
    data = [];
    data.push({"id":2,"text":"预约完成"});
    data.push({"id":4,"text":"订单取消"});
    $("#status").combobox("loadData", data);
    $("#status").combobox("setValue",data[0].id);
    $("#startTime").val(commonFormatter.dateTime(row.startTime,"",""));
    if(row.tenancy!=null&&row.tenancy!=""){
        $('#tenancy').combobox("setText",row.tenancy);
    }
    $("#startAddress").val(row.startAddress);
    $("#endAddress").val(row.endAddress);
    if(row.single!=null&&row.single!=""){
        $('#single').combobox("select",row.single);
    }
    if(row.fetchSend!=null&&row.fetchSend!=""){
        $('#fetchSend').combobox("select",row.fetchSend);
    }
    if(row.flightTrain!=null&&row.flightTrain!=""){
        $('#flightTrain').val(row.flightTrain);
    }
    $('#customerType').combobox("select",row.customerType);
    //乘客选择
    $('#customerName').combobox({
        method:'get',
        url:getContextPath()+"/customer/validList.do?type="+row.customerType,
        valueField:'id',
        textField:'displayName',
        onSelect: function (record) {
        },
        onLoadSuccess:function () {
            $('#customerName').combobox("setValue",row.customerId);
            $('#customerMobile').val(row.customerMobile);
        }

    });
    //车辆选择
    $('#plateNumber').combobox({
        method:'get',
        url:getContextPath()+"/car/validList.do",
        valueField:'id',
        textField:'plateNumber',
        onSelect: function (record) {

            $("#carType").val(record.type);
        }
    });
    //司机选择
    $('#driverName').combobox({
        method:'get',
        url:getContextPath()+"/driver/validList.do",
        valueField:'id',
        textField:'name',
        onSelect: function (record) {
            $("#driverMobile").val(record.mobile);
        }
    });
    //选择供应商
    $('#supplierName').combobox({
        method:'get',
        url:getContextPath()+"/supplier/validList.do",
        valueField:'id',
        textField:'name',
        onSelect: function (record) {
        }
    });
    $('#remark').val(row.remark);
}

function dispatchEditDataInit(row){
    var data;
    $('#id').val(row.id);
    $("#orderNo").val(row.orderNo);
    $("#createTime").val(commonFormatter.time(row.createTime,"",""));
    $('#orderStatus').val(commonFormatter.orderStatus(row.status,"",""));
    $('#orderStatusValue').val(row.status);
    $('#type').combobox('setValue',row.type);
    data = [];
    data.push({"id":2,"text":"预约完成"});
    data.push({"id":4,"text":"订单取消"});
    $("#status").combobox("loadData", data);
    $("#status").combobox("setValue",data[0].id);
    $("#startTime").val(commonFormatter.dateTime(row.startTime,"",""));
    if(row.tenancy!=null&&row.tenancy!=""){
        $('#tenancy').combobox("setText",row.tenancy);
    }
    $("#startAddress").val(row.startAddress);
    $("#endAddress").val(row.endAddress);
    if(row.single!=null&&row.single!=""){
        $('#single').combobox("select",row.single);
    }
    if(row.fetchSend!=null&&row.fetchSend!=""){
        $('#fetchSend').combobox("select",row.fetchSend);
    }
    if(row.flightTrain!=null&&row.flightTrain!=""){
        $('#flightTrain').val(row.flightTrain);
    }
    $('#customerType').combobox("select",row.customerType);
    //乘客选择
    $('#customerName').combobox({
        method:'get',
        url:getContextPath()+"/customer/validList.do?type="+row.customerType,
        valueField:'id',
        textField:'displayName',
        onSelect: function (record) {
        },
        onLoadSuccess:function () {
            $('#customerName').combobox("setValue",row.customerId);
            $('#customerMobile').val(row.customerMobile);
        }

    });
    //车辆选择
    $('#plateNumber').combobox({
        method:'get',
        url:getContextPath()+"/car/validList.do",
        valueField:'id',
        textField:'plateNumber',
        onSelect: function (record) {

            $("#carType").val(record.type);
        },
        onLoadSuccess:function (record) {
            $('#plateNumber').combobox("setValue",row.carId);
            $("#carType").val(row.carType);
        }
    });
    //司机选择
    $('#driverName').combobox({
        method:'get',
        url:getContextPath()+"/driver/validList.do",
        valueField:'id',
        textField:'name',
        onSelect: function (record) {
            $("#driverMobile").val(record.mobile);
        },
        onLoadSuccess:function (record) {
            $('#driverName').combobox("setValue",row.driverId);
            $("#driverMobile").val(row.driverMobile);
        }
    });
    //选择供应商
    $('#supplierName').combobox({
        method:'get',
        url:getContextPath()+"/supplier/validList.do",
        valueField:'id',
        textField:'name',
        onSelect: function (record) {
        },
        onLoadSuccess:function (record) {
            $('#supplierName').combobox("setValue",row.supplierId);
        }
    });
    $('#remark').val(row.remark);
}

function finishDataInit(row){
    var data;
    $('#id').val(row.id);
    $("#orderNo").val(row.orderNo);
    $("#createTime").val(commonFormatter.time(row.createTime,"",""));
    $('#orderStatus').val(commonFormatter.orderStatus(row.status,"",""));
    $('#orderStatusValue').val(row.status);
    $('#type').combobox('setValue',row.type);
    data = [];
    data.push({"id":3,"text":"订单完成"});
    data.push({"id":4,"text":"订单取消"});
    $("#status").combobox("loadData", data);
    $("#status").combobox("setValue",data[0].id);
    $("#startTime").val(commonFormatter.dateTime(row.startTime,"",""));
    if(row.tenancy!=null&&row.tenancy!=""){
        $('#tenancy').combobox("setText",row.tenancy);
    }
    $("#startAddress").val(row.startAddress);
    $("#endAddress").val(row.endAddress);
    if(row.single!=null&&row.single!=""){
        $('#single').combobox("select",row.single);
    }

    if(row.fetchSend!=null&&row.fetchSend!=""){
        $('#fetchSend').combobox("select",Number(row.fetchSend));
    }
    if(row.flightTrain!=null&&row.flightTrain!=""){
        $('#flightTrain').val(row.flightTrain);
    }
    $('#customerType').combobox("select",row.customerType);
    //乘客选择
    $('#customerName').combobox({
        method:'get',
        url:getContextPath()+"/customer/validList.do?type="+row.customerType,
        valueField:'id',
        textField:'displayName',
        onSelect: function (record) {
        },
        onLoadSuccess:function (record) {
            $('#customerName').combobox("select",row.customerId);
            $('#customerMobile').val(row.customerMobile);
        }

    });
    //车辆选择
    $('#plateNumber').combobox({
        method:'get',
        url:getContextPath()+"/car/validList.do",
        valueField:'id',
        textField:'plateNumber',
        onSelect: function (record) {
        },
        onLoadSuccess:function (record) {
            $('#plateNumber').combobox("setValue",row.carId);
            $("#carType").val(row.carType);
        }
    });
    //司机选择
    $('#driverName').combobox({
        method:'get',
        url:getContextPath()+"/driver/validList.do",
        valueField:'id',
        textField:'name',
        onSelect: function (record) {

        },
        onLoadSuccess:function (record) {
            $('#driverName').combobox("setValue",row.driverId);
            $("#driverMobile").val(row.driverMobile);
        }
    });
    //选择供应商
    $('#supplierName').combobox({
        method:'get',
        url:getContextPath()+"/supplier/validList.do",
        valueField:'id',
        textField:'name',
        onSelect: function (record) {
        },
        onLoadSuccess:function (record) {
            $('#supplierName').combobox("setValue",row.supplierId);
        }
    });
    $('#fee').val(row.fee);
    $('#total').val(row.total);
    $('#commission').val(row.commission);
    $('#remark').val(row.remark);
}

function showDataInit(row){
    var data;
    $('#id').val(row.id);
    $("#orderNo").val(row.orderNo);
    $("#createTime").val(commonFormatter.time(row.createTime,"",""));
    $('#orderStatus').val(commonFormatter.orderStatus(row.status,"",""));
    $('#orderStatusValue').val(row.status);
    $('#type').combobox('setValue',row.type);

    $("#status").combobox("clear");
    $("#startTime").val(commonFormatter.dateTime(row.startTime,"",""));
    if(row.tenancy!=null&&row.tenancy!=""){
        $('#tenancy').combobox("setText",row.tenancy);
    }
    $("#startAddress").val(row.startAddress);
    $("#endAddress").val(row.endAddress);
    if(row.single!=null&&row.single!=""){
        $('#single').combobox("setValue",row.single);
    }
    if(row.fetchSend!=null&&row.fetchSend!=""){
        $('#fetchSend').combobox("setValue",row.fetchSend);
    }
    if(row.flightTrain!=null&&row.flightTrain!=""){
        $('#flightTrain').val(row.flightTrain);
    }
    $('#customerType').combobox("setValue",row.customerType);
    //乘客选择
    $('#customerName').combobox({
        method:'get',
        url:getContextPath()+"/customer/validList.do?type="+row.customerType,
        valueField:'id',
        textField:'displayName',
        onSelect: function (record) {
        },
        onLoadSuccess:function (record) {
            $('#customerName').combobox("select",row.customerId);
            $('#customerMobile').val(row.customerMobile);
        }

    });
    //车辆选择
    $('#plateNumber').combobox({
        method:'get',
        url:getContextPath()+"/car/validList.do",
        valueField:'id',
        textField:'plateNumber',
        onSelect: function (record) {
        },
        onLoadSuccess:function (record) {
            $('#plateNumber').combobox("setValue",row.carId);
            $("#carType").val(row.carType);
        }
    });
    //司机选择
    $('#driverName').combobox({
        method:'post',
        url:getContextPath()+"/driver/validList.do",
        valueField:'id',
        textField:'name',
        onSelect: function (record) {

        },
        onLoadSuccess:function (record) {

            $('#driverName').combobox("setValue",row.driverId);
            $("#driverMobile").val(row.driverMobile);
        }
    });
    //选择供应商
    $('#supplierName').combobox({
        method:'post',
        url:getContextPath()+"/supplier/validList.do",
        valueField:'id',
        textField:'name',
        onSelect: function (record) {
        },
        onLoadSuccess:function (record) {
            $('#supplierName').combobox("setValue",row.supplierId);
        }
    });
    $("#total").val(row.total);
    $("#fee").val(row.fee);
    $("#commission").val(row.commission);
    $('#remark').val(row.remark);
}




function addEnableInit(){
    var type=$('#type').combobox("getValue");
    $("#orderNoDiv").show();
    if(type==1||type==4||type==5){
        $("#tenancyDiv").show();
        $("#orderExDiv").hide();
    }else{
        $("#tenancyDiv").hide();
        $("#orderExDiv").show();
    }
    $('#type').combobox('enable');
    $('#status').combobox('disable');
    $("#startTime").removeAttr("disabled");
    $('#tenancy').combobox('enable');
    $("#startAddress").removeAttr("disabled");
    $("#endAddress").removeAttr("disabled");
    $('#single').combobox('enable');
    $('#fetchSend').combobox('enable');
    $("#flightTrain").removeAttr("disabled");
    $("#total").attr("disabled","disabled");
    $("#fee").attr("disabled","disabled");
    $('#customerType').combobox('enable');
    $('#customerName').combobox('enable');
    $('#plateNumber').combobox('disable');
    $('#driverName').combobox('disable');
    $('#supplierName').combobox('disable');
    $("#remark").removeAttr("disabled");
}

function dispathEnableInit(){
    var type=$('#type').combobox("getValue");
    $("#orderNoDiv").show();
    if(type==1||type==4||type==5){
        $("#tenancyDiv").show();
        $("#orderExDiv").hide();
    }else{
        $("#tenancyDiv").hide();
        $("#orderExDiv").show();
    }
    $('#type').combobox('disable');
    $('#status').combobox('enable');
    $("#startTime").attr("disabled","disabled");
    $('#tenancy').combobox('disable');
    $("#startAddress").attr("disabled","disabled");
    $("#endAddress").attr("disabled","disabled");
    $('#single').combobox('disable');
    $('#fetchSend').combobox('disable');
    $("#flightTrain").attr("disabled","disabled");
    $("#total").attr("disabled","disabled");
    $("#fee").attr("disabled","disabled");
    $('#customerType').combobox('disable');
    $('#customerName').combobox('disable');
    $('#plateNumber').combobox('enable');
    $('#driverName').combobox('enable');
    $('#supplierName').combobox('enable');
    $("#remark").removeAttr("disabled");
}
function finishEnableInit(){
    var type=$('#type').combobox("getValue");
    $("#orderNoDiv").show();
    if(type==1||type==4||type==5){
        $("#tenancyDiv").show();
        $("#orderExDiv").hide();
    }else{
        $("#tenancyDiv").hide();
        $("#orderExDiv").show();
    }
    $('#type').combobox('disable');
    $('#status').combobox('enable');
    $("#startTime").attr("disabled","disabled");
    $('#tenancy').combobox('disable');
    $("#startAddress").attr("disabled","disabled");
    $("#endAddress").attr("disabled","disabled");
    $('#single').combobox('disable');
    $('#fetchSend').combobox('disable');
    $("#flightTrain").attr("disabled","disabled");
    $("#total").removeAttr("disabled");
    $("#fee").removeAttr("disabled");
    $('#customerType').combobox('disable');
    $('#customerName').combobox('disable');
    $('#plateNumber').combobox('disable');
    $('#driverName').combobox('disable');
    $('#supplierName').combobox('disable');
    $("#remark").removeAttr("disabled");
}

function showEnableInit(){
    var type=$('#type').combobox("getValue");
    $("#orderNoDiv").show();
    if(type==1||type==4||type==5){
        $("#tenancyDiv").show();
        $("#orderExDiv").hide();
    }else{
        $("#tenancyDiv").hide();
        $("#orderExDiv").show();
    }
    $('#type').combobox('disable');
    $('#status').combobox('disable');
    $("#startTime").attr("disabled","disabled");
    $('#tenancy').combobox('disable');
    $("#startAddress").attr("disabled","disabled");
    $("#endAddress").attr("disabled","disabled");
    $('#single').combobox('disable');
    $('#fetchSend').combobox('disable');
    $("#flightTrain").attr("disabled","disabled");
    $("#total").attr("disabled","disabled");
    $("#fee").attr("disabled","disabled");
    $('#customerType').combobox('disable');
    $('#customerName').combobox('disable');
    $('#plateNumber').combobox('disable');
    $('#driverName').combobox('disable');
    $('#supplierName').combobox('disable');
    $("#remark").attr("disabled","disabled");
}

function check(){
    var nextStatus= $("#status").combobox('getValue');
    var currentStatus= $("#orderStatusValue").val();
    var startTime=$("#startTime").val();
    var startAddress=$("#startAddress").val();
    var endAddress=$("#endAddress").val();
    var customer=$("#customerName").combobox('getValue');
    var plateNumber=$("#plateNumber").combobox('getValue');
    var driverName=$("#driverName").combobox('getValue');
    var supplierName=$("#supplierName").combobox('getValue');
    var fee=$("#fee").val();
    var total=$("#total").val();
    if(nextStatus=="4"){
        return true;
    }

    if( action=="addEdit"||action=="add"){
        //用车时间
        if(startTime==null||startTime==""){
            $.messager.alert("系统提示", "请选择用车时间！");
            return false;
        }
        //上车地点
        if(startAddress==null||startAddress==""){
            $.messager.alert("系统提示", "请选择上车地点！");
            return false;
        }
        //下车地点
        if(endAddress==null||endAddress==""){
            $.messager.alert("系统提示", "请选择下车地点！");
            return false;
        }
        //乘客
        if(customer==null||customer==""){
            $.messager.alert("系统提示", "请选择乘客！");
            return false;
        }
        return true;
    }
    if(action=="dispatchEdit"||action=="dispatch"){
        //车牌信息
        if(plateNumber==null||plateNumber==""){
            $.messager.alert("系统提示", "请选择用车牌！");
            return false;
        }
        //司机
        if(driverName==null||driverName==""){
            $.messager.alert("系统提示", "请选择司机！");
            return false;
        }
        //供应商
        if(supplierName==null||supplierName==""){
            $.messager.alert("系统提示", "请选择供应商！");
            return false;
        }

        return true;
    }
    if(action=="finishEdit"||action=="finish"){
        //费用
        if(fee==null||fee==""){
            $.messager.alert("系统提示", "请输入费用！");
            return false;
        }
        //订单总计
        if(total==null||total==""){
            $.messager.alert("系统提示", "请输入订单总计！");
            return false;
        }
        $("#fee").validatebox("isValid");
        if( !$("#total").validatebox("isValid") ||!$("#fee").validatebox("isValid")){
            $.messager.alert("系统提示", "请输入数字小,数点后保存两位！");
            return false;
        }
        if(Number(total)<Number(fee)){
            $.messager.alert("系统提示", "费用大于订单合计！");
            return false;
        }

        return true;
    }
    return true;
}


function addSave() {
    if (!check()) {
        return;
    }
    $.ajax({
        url: getContextPath() + "/order/add.do",
        async: true,
        data: {
            id:$('#id').val(),
            type: $('#type').combobox("getValue"),
            startTime:$('#startTime').val(),
            tenancy:$('#tenancy').combobox("getText"),
            startAddress:$('#startAddress').val(),
            endAddress:$('#endAddress').val(),
            fetchSend:$('#fetchSend').combobox("getValue"),
            flightTrain:$('#flightTrain').val(),
            single:$('#single').combobox("getValue"),
            status:$('#status').combobox("getValue"),
            customerType: $('#customerType').combobox("getValue"),
            customerId: $('#customerName').combobox("getValue"),
            customerName:$('#customerName').combobox("getText"),
            customerMobile:$('#customerMobile').val(),
            remark: $("#remark").val()
        },
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.result == "success") {
                $.messager.alert("系统提示", "操作成功！");
                actionSucess();
            } else {
                $.messager.alert("系统提示", "操作失败！");
            }
        },
        error: function () {
            $.messager.alert("系统提示", "系统异常！");
        }
    });
}

function addEditSave() {
    if (!check()) {
        return;
    }
    $.ajax({
        url: getContextPath() + "/order/addEdit.do",
        async: true,
        data: {
            id:$('#id').val(),
            type: $('#type').combobox("getValue"),
            startTime:$('#startTime').val(),
            tenancy:$('#tenancy').combobox("getText"),
            startAddress:$('#startAddress').val(),
            endAddress:$('#endAddress').val(),
            fetchSend:$('#fetchSend').combobox("getValue"),
            flightTrain:$('#flightTrain').val(),
            single:$('#single').combobox("getValue"),
            status:$('#status').combobox("getValue"),
            customerType: $('#customerType').combobox("getValue"),
            customerId: $('#customerName').combobox("getValue"),
            customerName:$('#customerName').combobox("getText"),
            customerMobile:$('#customerMobile').val(),
            remark: $("#remark").val()
        },
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.result == "success") {
                $.messager.alert("系统提示", "操作成功！");
                actionSucess();
            } else {
                $.messager.alert("系统提示", "操作失败！");
            }
        },
        error: function () {
            $.messager.alert("系统提示", "系统异常！");
        }
    });
}

function dispatchSave() {
    if (!check()) {
        return;
    }
    $.ajax({
        url: getContextPath() + "/order/dispatch.do",
        async: true,
        data: {
            id:$('#id').val(),
            status:  $('#status').combobox('getValue'),
            action:  action,
            driverId: $('#driverName').combobox('getValue'),
            driverName:$('#driverName').combobox('getText'),
            driverMobile:$('#driverMobile').val(),
            carId:$('#plateNumber').combobox('getValue'),
            plateNumber:$('#plateNumber').combobox('getText'),
            carType:$('#carType').val(),
            supplierId:$('#supplierName').combobox('getValue'),
            supplierName:$('#supplierName').combobox('getText'),
            remark: $("#remark").val()
        },
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.result == "success") {
                $.messager.alert("系统提示", "操作成功！");
                actionSucess();
            } else {
                $.messager.alert("系统提示", "操作失败！");
            }
        },
        error: function () {
            $.messager.alert("系统提示", "系统异常！");
        }
    });
}




function finishSave() {
    if (!check()) {
        return;
    }
    $.ajax({
        url: getContextPath() + "/order/finish.do",
        async: true,
        data: {
            id:$('#id').val(),
            status: $('#status').combobox('getValue'),
            action:action,
            total:$("#total").val(),
            fee:$("#fee").val(),
            commission:$("#commission").val(),
            remark: $("#remark").val()
        },
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.result == "success") {
                $.messager.alert("系统提示", "操作成功！");
                actionSucess();
            } else {
                $.messager.alert("系统提示", "操作失败！");
            }
        },
        error: function () {
            $.messager.alert("系统提示", "系统异常！");
        }
    });
}

function compute(){
    if( $("#total").val()!=null&&$("#total").val()!=""&& $("#fee").val()!=null&&$("#fee").val()!="") {
        if ($("#total").validatebox("isValid") && $("#fee").validatebox("isValid")) {
            var total=Number($("#total").val());
            var fee=Number($("#fee").val());
            $("#commission").val(accSub(total,fee));
        }
    }
}

function accAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2))
    return (arg1*m+arg2*m)/m
}
function accSub(arg1,arg2){
    return accAdd(arg1,-arg2);
}

