/**
 * Created by home on 2017/7/4.
 */


$(document).ready(function(){
    //权限选择框初始化

    $('#showtree').tree({
        url:getContextPath()+"/admin/menu.do",
        checkbox:false,
        onClick: function(node){
           if(node.attributes.isLeaf=="0"){
            openTab(node.text,getContextPath()+node.attributes.url,'icon-shuben');
           }
        }
    });

});

function openTab(text,url,iconCls) {
    if ($("#tabs").tabs("exists", text)) {
        $("#tabs").tabs("close", text);
        addTab(url, text, iconCls);
        $("#tabs").tabs("select", text);
    } else {
        addTab(url, text, iconCls);
    }
}
function addTab(url, text, iconCls) {
    var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"
        + url + "'></iframe>";
    $("#tabs").tabs("add", {
        title: text,
        iconCls: iconCls,
        closable: true,
        content: content
    });
}

function logout() {
    $.messager
        .confirm(
        decodeURI("%E7%B3%BB%E7%BB%9F%E6%8F%90%E7%A4%BA"),
        decodeURI("%E6%82%A8%E7%A1%AE%E5%AE%9A%E8%A6%81%E9%80%80%E5%87%BA%E7%B3%BB%E7%BB%9F%E5%90%97"),
        function (r) {
            if (r) {
                window.location.href = "${pageContext.request.contextPath}/admin/logout.do";
            }
        });
}


