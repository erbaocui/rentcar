/**
 * Created by home on 2017/7/7.
 */

$(document).ready(function(){
    $('#idname').combobox({
        method:'get',
        url:getContextPath()+"/car/typeList.do",
        valueField:'id',
        textField:'type'
    });
    $('#btn').click(function(){
        alert( $('#idname').val());
    });
});



