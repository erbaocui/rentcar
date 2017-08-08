
<meta charset="UTF-8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/themes/icon.css" />
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/demo/demo.css" />
<%--<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/plugins/uploadfiy/uploadify.css" />--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/plugins/jquery.validatebox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/plugins/jquery.combobox.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validator-rule.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validator-method.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorUser.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorCustomer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorSupplier.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorDriver.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorCar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorRole.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/datagrid.js"></script>
<%--
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/uploadfiy/jquery.uploadify.js"></script>
--%>

</head>


<script type="text/javascript">
    function getContextPath() {
        // 获取当前网址，如：http://localhost:8080/ssm/index.jsp
        var currentPath = window.document.location.href;
        // 获取主机地址之后的目录，如： /ssm/index.jsp
        var pathName = window.document.location.pathname;
        var pos = currentPath.indexOf(pathName);
        // 获取主机地址，如： http://localhost:8080
        var localhostPath = currentPath.substring(0, pos);
        // 获取带"/"的项目名，如：/ssm
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
       /* alert("currentPath:" + currentPath + "\n"
        + "pathName:" + pathName + "\n"
        + "localhostPath:" + localhostPath + "\n"
        + "projectName:" + projectName + "\n"
        + "contextPath:"+ localhostPath + projectName);*/
        return(localhostPath + projectName);
    }
    var commonFormatter=
    {
        dateTime: function (value, rec, index) {
            if (value == null) {
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
           // return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute ;
        },
        time: function (value, rec, index) {
            if (value == null) {
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;

        },
        status: function (value, rec, index) {
            if (value == 0) return decodeURI('%E6%9C%89%E6%95%88');//有效
            if (value == 1) return decodeURI('%E6%97%A0%E6%95%88');//无效
            return decodeURI('');

        },
        picType: function (value, rec, index) {
            if (value == null) return "";
            if (value == 1) return decodeURI('%E5%9B%BD%E5%86%85');//国内
            if (value == 2) return decodeURI('%E5%A2%83%E5%A4%96');//境外
            if (value == 3) return decodeURI('%E5%95%86%E5%8A%A1');//商务
            if (value == 4) return decodeURI('%E7%AD%BE%E8%AF%81');//签证
            if (value == 5) return decodeURI('%E6%88%BF%E8%BD%A6');//房车
            return decodeURI('%E6%9C%AA%E7%9F%A5');//未知

        },
        orderStatus: function (value, rec, index) {

            if (value == null) return "";
            if(rec==null||rec==""){
             if (value == 1) return  decodeURI('%E7%AD%89%E5%BE%85%E5%A4%84%E7%90%86');
            }else{
                if (value == 1) return '<font color="red">'+ decodeURI('%E7%AD%89%E5%BE%85%E5%A4%84%E7%90%86')+'</font>';
            }
            if (value == 2) return  decodeURI('%E9%A2%84%E7%BA%A6%E5%AE%8C%E6%88%90');
            if (value == 3) return  decodeURI('%E8%AE%A2%E5%8D%95%E5%AE%8C%E6%88%90');
            if (value == 4) return  decodeURI('%E8%AE%A2%E5%8D%95%E5%8F%96%E6%B6%88');
            if (value == 5) return  decodeURI('%E8%AE%A2%E5%8D%95%E7%BB%88%E7%BB%93');
            return  decodeURI('%E6%9C%AA%E7%9F%A5');//未知
        },
        orderType: function (value, rec, index) {

            if (value == null) return "";
            if (value == 1) return decodeURI('%E6%97%A5%E7%A7%9F%E5%8C%85%E8%BD%A6');
            if (value == 2) return decodeURI('%E6%9C%BA%E5%9C%BA%E6%8E%A5%E9%80%81');
            if (value == 3) return decodeURI('%E8%BD%A6%E7%AB%99%E6%8E%A5%E9%80%81');
            if (value == 4) return decodeURI('%E6%9C%88%E7%A7%9F%E5%8C%85%E8%BD%A6');
            if (value == 5) return decodeURI('%E4%BC%81%E4%B8%9A%E7%94%A8%E8%BD%A6');
            return   decodeURI('%E6%9C%AA%E7%9F%A5');//未知
        },
        brief: function (value, rec, index) {

            if (value == null) return "";
            if(value.length>5){
                var str=value.substr(3)+"."
                var content = '<span title="' + value + '" class="tip">' + str+ '</span>';
                return content;
            }
            return value;//未知
        },
        custuomerType: function (value, rec, index) {
            if (value == 0) return decodeURI('%E4%B8%AA%E4%BA%BA');//个人
            if (value == 1) return decodeURI('%E4%BC%81%E4%B8%9A');//企业
            else return decodeURI("");//

        },
        single: function (value, rec, index) {
            if (value == 0) return decodeURI('%E5%8D%95%E7%A8%8B');//单程
            if (value == 1) return decodeURI('%E5%BE%80%E8%BF%94');//单程
            else return decodeURI('');//

        },
        fetchSend: function (value, rec, index) {
            if (value == 0) return decodeURI('%E6%8E%A5');//接
            if (value == 0) return decodeURI('%E9%80%81');//接
            else return decodeURI('');//送

        }
    }

</script>
