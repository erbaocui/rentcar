/**
 * Created by home on 2017/7/7.
 */

$(document).ready(function(){
    $("#uploadify").uploadify({
        'swf' : getContextPath()+'/js/plugins/uploadfiy/uploadify.swf',

        'uploader' :getContextPath()+'/file/upload.do',//flash文件的相对路径
        //'uploader' :getContextPath()+'/loadimg/upload.do',//flash文件的相对路径
       // 'script':getContextPath()+'/file/upload.do',  				//后台处理程序的路径
        'fileDataName':"Filedata", 						//设置上传文件名称,默认为Filedata
        //'cancelImg': 'images/cancel.png', 			//每一个文件上的关闭按钮图标
        'queueID': 'fileQueue', 					//文件队列的ID，该ID与存放文件队列的div的ID一致
        'queueSizeLimit': 1, 							//当允许多文件生成时，设置选择文件的个数，默认值：999
        'fileDesc': '*.jpg;*.gif;*.png;*.ppt;*.pdf;*.jpeg', 	//用来设置选择文件对话框中的提示文本
        'fileExt': '*.jpg;*.gif;*.png;*.ppt;*.pdf;*.jpeg', 		//设置可以选择的文件的类型
        'auto': true, 								//设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
        'multi': false, 								//设置为true时可以上传多个文件
        'simUploadLimit': 1, 						//允许同时上传的个数 默认值：1
        'sizeLimit': 2048000,						//上传文件的大小限制
        'buttonText': '上传图片',						//浏览按钮的文本，默认值：BROWSE
        'displayData': 'percentage',     			//上传队列显示的数据类型，percentage是百分比，speed是上传速度
        //回调函数
        'onComplete': function (evt, queueID, fileObj, response, data) {
            $("#img11").attr("src", "../" + response);
            alert(response);
            return false;
        },
        'onError': function (event, queueID, fileObj, errorObj) {
            if (errorObj.type === "File Size") {
                alert("文件最大为3M");
                $("#uploadify").uploadifyClearQueue();
            }
            alert("bbbb");
        },
        'onQueueFull': function (event, queueSizeLimit) {
            alert("最多上传" + queueSizeLimit + "张图片");
            return false;
        },
        'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）

            $("#showImg").attr("src",getContextPath()+data);
        },
        'onSelectError' : function(file, errorCode, errorMsg) {

            var msgText = "上传失败\n";
            switch (errorCode) {
                case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                    var towedAccreditDivLen = $("#fileQueue").children().length;
                    msgText += "每次最多上传 " + $('#uploadify').uploadify('settings', 'uploadLimit') + "个文件";
                    break;
                case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                    msgText += "文件大小超过限制( " + $('#uploadify').uploadify('settings', 'fileSizeLimit') + " )";
                    break;
                case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                    msgText += "文件大小为0";
                    break;
                case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                    msgText += "文件格式不正确，仅限 " + $('#uploadify').uploadify('settings', 'fileTypeExts');
                    break;
                default:
                    msgText += "错误代码：" + errorCode + "\n" + errorMsg;
            }
            alert(msgText);
          }
        });
   /* $("#uploadify").uploadify({
        'script':getContextPath()+'/file/upload.do',
       // 'uploader' :getContextPath()+'/file/upload.do',
        'swf' : getContextPath()+'/js/plugins/uploadfiy/uploadify.swf',
        'cancelImg' :getContextPath()+'/js/plugins/uploadfiy/uploadify-cancel.png',
        'folder' : 'uploads',//您想将文件保存到的路径
        'fileDataName': 'file',
        'queueID' : 'fileQueue',//与下面的id对应
        'queueSizeLimit' : 1,
        'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
        'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
        'auto' : true,
        'multi' : false,
        'simUploadLimit' : 2,
        'buttonText' : '选择文件',
        'onDialogOpen' : function() {//当选择文件对话框打开时触发
          //  alert( 'Open!');
        },
        'onSelect' : function(file) {//当每个文件添加至队列后触发
            alert( 'id: ' + file.id
            + ' - 索引: ' + file.index
            + ' - 文件名: ' + file.name
            + ' - 文件大小: ' + file.size
            + ' - 类型: ' + file.type
            + ' - 创建日期: ' + file.creationdate
            + ' - 修改日期: ' + file.modificationdate
            + ' - 文件状态: ' + file.filestatus);
        },
        'onSelectError' : function(file,errorCode,errorMsg) {//当文件选定发生错误时触发
            alert( 'id: ' + file.id
            + ' - 索引: ' + file.index
            + ' - 文件名: ' + file.name
            + ' - 文件大小: ' + file.size
            + ' - 类型: ' + file.type
            + ' - 创建日期: ' + file.creationdate
            + ' - 修改日期: ' + file.modificationdate
            + ' - 文件状态: ' + file.filestatus
            + ' - 错误代码: ' + errorCode
            + ' - 错误信息: ' + errorMsg);
        },
        'onDialogClose' : function(swfuploadifyQueue) {//当文件选择对话框关闭时触发
            if( swfuploadifyQueue.filesErrored > 0 ){
                alert( '添加至队列时有'
                +swfuploadifyQueue.filesErrored
                +'个文件发生错误n'
                +'错误信息:'
                +swfuploadifyQueue.errorMsg
                +'n选定的文件数:'
                +swfuploadifyQueue.filesSelected
                +'n成功添加至队列的文件数:'
                +swfuploadifyQueue.filesQueued
                +'n队列中的总文件数量:'
                +swfuploadifyQueue.queueLength);
            }
        },
        'onQueueComplete' : function(stats) {//当队列中的所有文件全部完成上传时触发
            alert( '成功上传的文件数: ' + stats.successful_uploads
            + ' - 上传出错的文件数: ' + stats.upload_errors
            + ' - 取消上传的文件数: ' + stats.upload_cancelled
            + ' - 出错的文件数' + stats.queue_errors);
        },
        'onUploadComplete' : function(file,swfuploadifyQueue) {//队列中的每个文件上传完成时触发一次
            alert( 'id: ' + file.id
            + ' - 索引: ' + file.index
            + ' - 文件名: ' + file.name
            + ' - 文件大小: ' + file.size
            + ' - 类型: ' + file.type
            + ' - 创建日期: ' + file.creationdate
            + ' - 修改日期: ' + file.modificationdate
            + ' - 文件状态: ' + file.filestatus);
        },
        'onUploadError' : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {//上传文件出错是触发（每个出错文件触发一次）
            alert( 'id: ' + file.id
            + ' - 索引: ' + file.index
            + ' - 文件名: ' + file.name
            + ' - 文件大小: ' + file.size
            + ' - 类型: ' + file.type
            + ' - 创建日期: ' + file.creationdate
            + ' - 修改日期: ' + file.modificationdate
            + ' - 文件状态: ' + file.filestatus
            + ' - 错误代码: ' + errorCode
            + ' - 错误描述: ' + errorMsg
            + ' - 简要错误描述: ' + errorString);
        },
        'onUploadProgress' : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {//上传进度发生变更时触发
            alert( 'id: ' + file.id
            + ' - 索引: ' + file.index
            + ' - 文件名: ' + file.name
            + ' - 文件大小: ' + file.size
            + ' - 类型: ' + file.type
            + ' - 创建日期: ' + file.creationdate
            + ' - 修改日期: ' + file.modificationdate
            + ' - 文件状态: ' + file.filestatus
            + ' - 当前文件已上传: ' + fileBytesLoaded
            + ' - 当前文件大小: ' + fileTotalBytes
            + ' - 队列已上传: ' + queueBytesLoaded
            + ' - 队列大小: ' + swfuploadifyQueueUploadSize);
        },
        'onUploadStart': function(file) {//上传开始时触发（每个文件触发一次）
            alert( 'id: ' + file.id
            + ' - 索引: ' + file.index
            + ' - 文件名: ' + file.name
            + ' - 文件大小: ' + file.size
            + ' - 类型: ' + file.type
            + ' - 创建日期: ' + file.creationdate
            + ' - 修改日期: ' + file.modificationdate
            + ' - 文件状态: ' + file.filestatus );
        },
        'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
            alert( 'id: ' + file.id
            + ' - 索引: ' + file.index
            + ' - 文件名: ' + file.name
            + ' - 文件大小: ' + file.size
            + ' - 类型: ' + file.type
            + ' - 创建日期: ' + file.creationdate
            + ' - 修改日期: ' + file.modificationdate
            + ' - 文件状态: ' + file.filestatus
            + ' - 服务器端消息: ' + data
            + ' - 是否上传成功: ' + response);
        }
    });*/
});



