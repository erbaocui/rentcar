<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>地图服务</title>
  <link rel="stylesheet" type="text/css" href="zero.css" />
  <script language="javascript" src="http://webapi.amap.com/maps?v=1.2&key=3409b9c6074f02202bcafee9780fe784"></script>
</head>
<body onLoad="mapInit()">
<div class="autoclass">
  <input type="text" id="keyword" name="keyword" value="东方明珠" style="width: 95%;"/>
  <div id="result1" class="autobox" name="result1"></div>
</div>
<div id="iCenter"></div>
<div id="iControlbox">
  <p>坐标：<span id="lnglats">&nbsp;</span></p>
  <ul>
    <li>
      <button onclick="javascript:placeSearch1();">关键词搜索</button>
      <button onclick="javascript:clearMap();">清空地图</button>
    </li>
    <li>
      <button onclick="javascript:placeSearch2();">周边查询</button>
      <button onclick="javascript:clearMap();">清空地图</button>
    </li>
    <li>
      <button onclick="javascript:placeSearch3();">范围内查询</button>
      <button onclick="javascript:clearMap();">清空地图</button>
    </li>
    <li>
      <button onclick="javascript:roadCrossSearchByRoadName();">道路查询</button>
      <button onclick="javascript:clearMap();">清空地图</button>
    </li>
    <li>
      <button onclick="javascript:addCloudLayer();">云图</button>
      <button onclick="javascript:cloudSearch();">检索自有酒店数据</button>
      <button onclick="javascript:clearCloud();">清空云图</button>
    </li>
  </ul>
</div>
<div id="result"></div>
<!-- tongji begin-->
<script type="text/javascript">
  var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
  document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Faeff88f19045b513af7681b011cea3bd' type='text/javascript'%3E%3C/script%3E"));
</script>
<!-- tongji end -->
</body>
<script language="javascript">
  var mapObj;
  var marker = new Array();
  var windowsArr = new Array();
  var cloudDataLayer;
  var MSearch;
  var keyword;
  //初始化地图对象，加载地图
  function mapInit(){
    mapObj = new AMap.Map("iCenter",{
      center:new AMap.LngLat(116.397428,39.90923), //地图中心点
      level:11  //地图显示的比例尺级别
    });
    AMap.event.addListener(mapObj,'click',getLnglat); //点击事件

    //加载输入提示插件
    mapObj.plugin(["AMap.Autocomplete"], function() {
      //判断是否IE浏览器
      if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("keyword").onpropertychange = autoSearch;
      }
      else {
        document.getElementById("keyword").oninput = autoSearch;
      }
    });

  }
  //鼠标点击，获取经纬度坐标
  function getLnglat(e){
    var x = e.lnglat.getLng();
    var y = e.lnglat.getLat();
    document.getElementById("lnglats").innerHTML = x + "," + y;
  }
  //清空地图
  function clearMap(){
    mapObj.clearMap();
    document.getElementById("result").innerHTML = '&nbsp;';
  }
  function clearCloud(){
    cloudDataLayer.setMap(null);
    mapObj.clearMap();
    document.getElementById("result").innerHTML = '&nbsp;';
  }
  //关键词查询
  function placeSearch1() {
    clearMap();
    keyword = document.getElementById("keyword").value;
    mapObj.plugin(["AMap.PlaceSearch"], function() {
      MSearch = new AMap.PlaceSearch({ //构造地点查询类
        pageSize:10,
        pageIndex:1,
        city:"021" //城市
      });
      AMap.event.addListener(MSearch, "complete", Search_CallBack);//返回地点查询结果
      MSearch.search(keyword); //关键字查询
    });
  }

  //周边查询函数
  var cpoint = new AMap.LngLat(116.405467,39.907761); //搜索查询的中心点设置
  function placeSearch2() {
    clearMap();
    //加载服务插件，实例化地点查询类    
    mapObj.plugin(["AMap.PlaceSearch"], function() {
      MSearch = new AMap.PlaceSearch({
        city: "北京"
      });
      //查询成功时的回调函数
      AMap.event.addListener(MSearch, "complete", Search_CallBack);
      //周边搜索
      MSearch.searchNearBy("酒店", cpoint, 500);
    });
    var circle = new AMap.Circle({
      center:cpoint,// 圆心位置
      radius:500, //半径
      strokeColor: "#F33", //线颜色
      strokeOpacity: 1, //线透明度
      strokeWeight: 3, //线粗细度
      fillColor: "#ee2200", //填充颜色
      fillOpacity: 0.35//填充透明度
    });
    circle.setMap(mapObj);
  }
  //范围内搜索-矩形
  function placeSearch3(){
    clearMap();
    var arr = new Array();
    //绘制矩形边框     
    arr.push(new AMap.LngLat("116.423321","39.884055"));
    arr.push(new AMap.LngLat("116.473103","39.884055"));
    arr.push(new AMap.LngLat("116.473103","39.919348"));
    arr.push(new AMap.LngLat("116.423321","39.919348"));
    var polygon = new AMap.Polygon({
      map:mapObj,
      path:arr,
      strokeColor:"#0000ff",
      strokeOpacity:0.2,
      strokeWeight:3,
      fillColor: "#f5deb3",
      fillOpacity: 0.8
    });
    mapObj.plugin(["AMap.PlaceSearch"], function() { //加载PlaceSearch服务插件        
      MSearch = new AMap.PlaceSearch({
        pageSize: 8
      }); //构造地点查询类
      AMap.event.addListener(MSearch, "complete", Search_CallBack); //查询成功时的回调函数
      MSearch.searchInBounds("酒店", new AMap.Bounds(arr[0], arr[2])); //范围查询
    });
  }

  //添加marker&infowindow
  function addmarker(i, d) {
    var lngX;
    var latY;
    var iName;
    var iAddress;
    if(d.location){
      lngX = d.location.getLng();
      latY = d.location.getLat();
    }else{
      lngX = d._location.getLng();
      latY = d._location.getLat();
    }
    if(d.name){
      iName = d.name;
    }else{
      iName = d._name;
    }
    if(d.name){
      iAddress = d.address;
    }else{
      iAddress = d._address;
    }
    var markerOption = {
      map:mapObj,
      icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
      position:new AMap.LngLat(lngX, latY)
    };
    var mar = new AMap.Marker(markerOption);
    marker.push(new AMap.LngLat(lngX, latY));

    var infoWindow = new AMap.InfoWindow({
      content:"<h3><font color=\"#00a6ac\">" + (i + 1) + ". " + iName + "</font></h3>" + TipContents(d.type, iAddress, d.tel),
      size:new AMap.Size(300, 0),
      autoMove:true,
      offset:new AMap.Pixel(0,-30)
    });
    windowsArr.push(infoWindow);
    var aa = function (e) {infoWindow.open(mapObj, mar.getPosition());};
    AMap.event.addListener(mar, "click", aa);
  }
  //回调函数
  function Search_CallBack(data) {
    var resultStr = "";
    var poiArr = data.poiList.pois;
    var resultCount = poiArr.length;
    for (var i = 0; i < resultCount; i++) {
      resultStr += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i + 1) + ",this)' style=\"font-size: 12px;cursor:pointer;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><img src=\"http://webapi.amap.com/images/" + (i + 1) + ".png\"></td>" + "<td><h3><font color=\"#00a6ac\">名称: " + poiArr[i].name + "</font></h3>";
      resultStr += TipContents(poiArr[i].type, poiArr[i].address, poiArr[i].tel) + "</td></tr></table></div>";
      addmarker(i, poiArr[i]);
    }
    mapObj.setFitView();
    document.getElementById("result").innerHTML = resultStr;
  }
  function TipContents(type, address, tel) {  //窗体内容
    if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {
      type = "暂无";
    }
    if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
      address = "暂无";
    }
    if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {
      tel = "暂无";
    }
    var str = "&nbsp;&nbsp;地址：" + address + "<br />&nbsp;&nbsp;电话：" + tel + " <br />&nbsp;&nbsp;类型：" + type;
    return str;
  }
  function openMarkerTipById1(pointid, thiss) {  //根据id 打开搜索结果点tip
    thiss.style.background = '#CAE1FF';
    windowsArr[pointid].open(mapObj, marker[pointid]);
  }
  function onmouseout_MarkerStyle(pointid, thiss) { //鼠标移开后点样式恢复
    thiss.style.background = "";
  }

  //输入提示
  function autoSearch() {
    var keywords = document.getElementById("keyword").value;
    var auto;
    var autoOptions = {
      pageIndex:1,
      pageSize:10,
      city: "" //城市，默认全国
    };
    auto = new AMap.Autocomplete(autoOptions);
    //查询成功时返回查询结果  
    AMap.event.addListener(auto, "complete", autocomplete_CallBack);
    auto.search(keywords);
  }
  //输出输入提示结果的回调函数
  function autocomplete_CallBack(data) {
    var resultStr = "";
    var tipArr = [];
    tipArr = data.tips;
    if (tipArr.length>0) {
      for (var i = 0; i < tipArr.length; i++) {
        resultStr += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById(" + (i + 1)
        + ",this)' onclick='selectResult(" + i + ")' onmouseout='onmouseout_MarkerStyle(" + (i + 1)
        + ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\">" + tipArr[i].name + "<span style='color:#C1C1C1;'>"+ tipArr[i].district + "</span></div>";
      }
    }
    else  {
      resultStr = " π__π 亲,人家找不到结果!<br />要不试试：<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";
    }

    document.getElementById("result1").innerHTML = resultStr;
    document.getElementById("result1").style.display = "block";
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
  function selectResult(index) {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
      document.getElementById("keyword").onpropertychange = null;
      document.getElementById("keyword").onfocus = focus_callback;
    }
    //截取输入提示的关键字部分  
    var text = document.getElementById("divid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g,"");;
    document.getElementById("keyword").value = text;
    document.getElementById("result1").style.display = "none";
    //根据选择的输入提示关键字查询  
    mapObj.plugin(["AMap.PlaceSearch"], function() {
      var msearch = new AMap.PlaceSearch();  //构造地点查询类
      AMap.event.addListener(msearch, "complete", Search_CallBack); //查询成功时的回调函数
      msearch.search(text);  //关键字查询查询
    });
  }

  //定位选择输入提示关键字
  function focus_callback() {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
      document.getElementById("keyword").onpropertychange = autoSearch;
    }
  }
  //道路交叉口
  function roadCrossSearchByRoadName(){
    var roadname = '101';
    var city2 = '北京';
    var RoadSearchOption = {
      number:10,//每页数量,默认10
      batch:1,//请求页数，默认1
      ext:""//扩展字段
    };
    var road = new AMap.RoadSearch(RoadSearchOption);
    road.roadCrossSearchByRoadName(roadname,city2,roadCrossSearch_CallBack);
    //清空本次搜索的marker和windowsArr  
    marker=[];
    windowsArr=[];
  }
  function roadCrossSearch_CallBack(data){
    mapObj.clearMap();
    var resultStr="";
    if(data.status =="E0") {
      var marker = new Array();
      for (var i = 0; i < data.list.length; i++) {
        resultStr += "<div id='divid"+(i+1)+"' onmouseover='openMarkerTipById1("+(i)+",this)' onmouseout='onmouseout_MarkerStyle("+(i+1)+",this)' style=\"font-size: 13px;cursor:pointer;padding:2px 0 10px 5px;\"><b>"+(i+1)+"、"+data.list[i].roadname2+"和"+data.list[i].roadname1+"交叉口</b>"+"<br \/></div>";
        resultStr += "<div>道路交叉口坐标："+data.list[i].x+","+data.list[i].y+"<br></div>";
        addmarker2(i,data.list[i]);
      }
      mapObj.setFitView();
    }
    else if(data.status =="E1")
    {
      resultStr = "未查找到任何结果!<br />建议：<br />1.请确保所有字词拼写正确。<br />2.尝试不同的关键字。<br />3.尝试更宽泛的关键字。";
    }
    else
    {
      resultStr= "错误信息："+data.state+"请对照API Server v2.0.0 简明提示码对照表查找错误类型";
    }
    document.getElementById("result").innerHTML = resultStr;
  }
  function addmarker2(i,d){
    var markerOption = {
      map:mapObj,
      icon:"http://webapi.amap.com/images/point.png",
      position:new AMap.LngLat(d.x,d.y),
      offset:{x:-8,y:-9}
    };
    var mar =new AMap.Marker(markerOption);
    marker.push(new AMap.LngLat(d.x,d.y));
    var infoWindow = new AMap.InfoWindow({
      content:"&nbsp;&nbsp;"+(i+1) + ". "+ d.roadname2 +"和"+d.roadname1+"交叉口"+"<br />",
      size:new AMap.Size(300,0),
      autoMove:true
    });
    windowsArr.push(infoWindow);

    var aa=function(e){infoWindow.open(mapObj,mar.getPosition());};
    AMap.event.addListener(mar,"click",aa);
  }
  var yunPoint = new AMap.LngLat(120.169144,30.293164);
  //叠加云数据图层
  function addCloudLayer() {
    mapObj.setZoomAndCenter(14,yunPoint);
    //加载云图层插件  
    mapObj.plugin('AMap.CloudDataLayer', function () {
      var layerOptions = {
        query:{keywords: ''},
        clickable:true
      };
      cloudDataLayer = new AMap.CloudDataLayer('5358f853e4b01214f369d851', layerOptions); //实例化云图层类
      cloudDataLayer.setMap(mapObj); //叠加云图层到地图
      AMap.event.addListener(cloudDataLayer, 'click', function (result) {
        var clouddata = result.data;
        var infoWindowYun = new AMap.InfoWindow({
          content:"<h3><font face=\"微软雅黑\"color=\"#3366FF\">"+ clouddata._name +"</font></h3><hr />地址："+ clouddata._address + "<br />" + "创建时间：" + clouddata._createtime+ "<br />" + "更新时间：" + clouddata._updatetime,
          size:new AMap.Size(300, 0),
          autoMove:true,
          offset:new AMap.Pixel(0,-5)
        });
        infoWindowYun.open(mapObj, clouddata._location);
      });
    });
  }
  function cloudSearch(){
    var arr = new Array();
    var search;
    var searchOptions = {
      keywords:'酒店',
      orderBy:'_id:ASC'
    };
    //加载CloudDataSearch服务插件  
    mapObj.plugin(["AMap.CloudDataSearch"], function() {
      search = new AMap.CloudDataSearch('5358f853e4b01214f369d851', searchOptions); //构造云数据检索类
      AMap.event.addListener(search, "complete", cloudSearch_CallBack); //查询成功时的回调函数
      AMap.event.addListener(search, "error", errorInfo); //查询失败时的回调函数
      search.searchNearBy(yunPoint, 10000); //周边检索
    });
  }
  function cloudSearch_CallBack(data) {
    var resultStr="";
    var resultArr = data.datas;
    var resultNum = resultArr.length;
    for (var i = 0; i < resultNum; i++) {
      resultStr += "<div id='divid" + (i+1) + "' onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i+1) + ",this)' style=\"font-size: 12px;cursor:pointer;padding:2px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><h3><font face=\"微软雅黑\"color=\"#3366FF\">" + (i+1) + "." + resultArr[i]._name + "</font></h3>";
      resultStr += '地址：' + resultArr[i]._address + '<br/>类型：' + resultArr[i].type + '<br/>ID：' + resultArr[i]._id + "</td></tr></table></div>";
      addmarker(i, resultArr[i]);
    }
    mapObj.setFitView();
    document.getElementById("result").innerHTML = resultStr;
  }
  //回调函数
  function errorInfo(data) {
    resultStr = data.info;
    document.getElementById("result").innerHTML = resultStr;
  }
</script>
</html>
