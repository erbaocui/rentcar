
var mapObj;
$(document).ready(function() {
    mapInit();

});



//初始化地图对象，加载地图
function mapInit(){
    mapObj = new AMap.Map("iCenter",{
        center:new AMap.LngLat(116.397428,39.90923), //地图中心点
        level:11  //地图显示的比例尺级别
    });

    //加载输入提示插件
    mapObj.plugin(["AMap.Autocomplete"], function() {
        //判断是否IE浏览器
        if (navigator.userAgent.indexOf("MSIE") > 0) {
            document.getElementById("startAddress").onpropertychange = autoSearchS;
        }
        else {
            document.getElementById("startAddress").oninput = autoSearchS;
        }
    });

    mapObj.plugin(["AMap.Autocomplete"], function() {
        //判断是否IE浏览器
        if (navigator.userAgent.indexOf("MSIE") > 0) {
            document.getElementById("endAddress").onpropertychange = autoSearchE;
        }
        else {
            document.getElementById("endAddress").oninput = autoSearchE;
        }
    });

}


//输入提示
function autoSearchS() {
    var startAddresss = document.getElementById("startAddress").value;
    var auto;
    var autoOptions = {
        pageIndex:1,
        pageSize:10,
        city: decodeURI("%E5%A4%A9%E6%B4%A5") //城市，默认全国
    };
    auto = new AMap.Autocomplete(autoOptions);
    //查询成功时返回查询结果
    AMap.event.addListener(auto, "complete", autocomplete_CallBackS);
    auto.search(startAddresss);
}
function autoSearchE() {
    var endAddresss = document.getElementById("endAddress").value;
    var auto;
    var autoOptions = {
        pageIndex:1,
        pageSize:10,
        city: decodeURI("%E5%A4%A9%E6%B4%A5") //城市，默认全国
    };
    auto = new AMap.Autocomplete(autoOptions);
    //查询成功时返回查询结果
    AMap.event.addListener(auto, "complete", autocomplete_CallBackE);
    auto.search(endAddresss);
}


//输出输入提示结果的回调函数
function autocomplete_CallBackS(data) {
    var resultStr = "";
    var tipArr = [];
    tipArr = data.tips;
    if (tipArr.length>0) {
        for (var i = 0; i < tipArr.length; i++) {
            resultStr += "<div id='sdivid" + (i + 1) + "' onmouseover='openMarkerTipById(" + (i + 1)
            + ",this)' onclick='selectResultS(" + i + ")' onmouseout='onmouseout_MarkerStyle(" + (i + 1)
            + ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\">" + tipArr[i].name + "<span style='color:#C1C1C1;'>"+ tipArr[i].district + "</span></div>";
        }
    }
    else  {
        resultStr = " π__π 亲,人家找不到结果!<br />要不试试：<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";
    }

    document.getElementById("result1").innerHTML = resultStr;
    document.getElementById("result1").style.display = "block";
    document.getElementById("result1").style.position="absolute";
    document.getElementById("result1").style.backgroundColor="#FFF3ED";
}

function autocomplete_CallBackE(data) {
    var resultStr = "";
    var tipArr = [];
    tipArr = data.tips;
    if (tipArr.length>0) {
        for (var i = 0; i < tipArr.length; i++) {
            resultStr += "<div id='edivid" + (i + 1) + "' onmouseover='openMarkerTipById(" + (i + 1)
            + ",this)' onclick='selectResultE(" + i + ")' onmouseout='onmouseout_MarkerStyle(" + (i + 1)
            + ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\">" + tipArr[i].name + "<span style='color:#C1C1C1;'>"+ tipArr[i].district + "</span></div>";
        }
    }
    else  {
        resultStr = " π__π 亲,人家找不到结果!<br />要不试试：<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";
    }

    document.getElementById("result2").innerHTML = resultStr;
    document.getElementById("result2").style.display = "block";
    document.getElementById("result2").style.position="absolute";
    document.getElementById("result2").style.backgroundColor="#FFF3ED";
}

//输入提示框鼠标滑过时的样式
function openMarkerTipById(pointid, thiss) {  //根据id打开搜索结果点tip
    thiss.style.background = '#CAE1FF';
}

//输入提示框鼠标移出时的样式
function onmouseout_MarkerStyle(pointid, thiss) {  //鼠标移开后点样式恢复
    thiss.style.background = "";
}

//从输入提示框中选择关键字并查询
function selectResultS(index) {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("startAddress").onpropertychange = null;
        document.getElementById("startAddress").onfocus = focus_callback;
    }
    //截取输入提示的关键字部分
    var text = document.getElementById("sdivid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g,"");;
    document.getElementById("startAddress").value = text;
    document.getElementById("result1").style.display = "none";
    //根据选择的输入提示关键字查询
    mapObj.plugin(["AMap.PlaceSearch"], function() {
        var msearch = new AMap.PlaceSearch();  //构造地点查询类
        AMap.event.addListener(msearch, "complete", Search_CallBack); //查询成功时的回调函数
        msearch.search(text);  //关键字查询查询
    });
}

function selectResultE(index) {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("endAddress").onpropertychange = null;
        document.getElementById("endAddress").onfocus = focus_callback;
    }
    //截取输入提示的关键字部分
    var text = document.getElementById("edivid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g,"");;
    document.getElementById("endAddress").value = text;
    document.getElementById("result2").style.display = "none";
    //根据选择的输入提示关键字查询
    mapObj.plugin(["AMap.PlaceSearch"], function() {
        var msearch = new AMap.PlaceSearch();  //构造地点查询类
        AMap.event.addListener(msearch, "complete", Search_CallBack); //查询成功时的回调函数
        msearch.search(text);  //关键字查询查询
    });
}

//定位选择输入提示关键字
function focus_callbackS() {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("startAddress").onpropertychange = autoSearchS;
    }
}

function focus_callbackE() {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("endAddress").onpropertychange = autoSearchE;
    }
}
