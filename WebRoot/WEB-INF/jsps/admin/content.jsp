<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="cn" xmlns=http://www.w3.org/1999/xhtml xmlns:bd=http://www.baidu.com/2010/xbdml>
<head>
<base href="<%=basePath%>">
 <meta http-equiv="X-UA-Compatible" content="IE=10"/>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>内容页</title>
<link rel="stylesheet" type="text/css" href="resources/css/admin.css"/>
<link rel="stylesheet" type="text/css" href="resources/video-js/video-js.css"/>
<script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-1.9.0.min.js" ></script>
<script type="text/javascript" src="resources/video-js/video.js"></script>
<script>
	var editor = null;
	var player = null;
	videojs.options.flash.swf="resources/video-js/video-js.swf";
	window.onload=function()
	{
		 $.ajaxSetup({ cache: false }); 
		$(".indexPage").append("<a>1</a>");
		getFileList(1,11);
		CKEDITOR.replace('html_textarea',{
			uiColor:'#76A6F0',
			customConfig:'/course/resources/scripts/ck_myConfig.js',
			image_previewText:"",
			filebrowserImageUploadUrl:"file_upload_html.action",
		},addBrowserButton(this));
	};
</script>
<script type="text/javascript" src="resources/scripts/ckeditor_extend.js"></script>
<script type="text/javascript" src="resources/scripts/pagediv.js"></script>
<script type="text/javascript" src="resources/scripts/editor_load.js"></script>
<script>
//基本的dom元素操作事件
function loadFile(e){ //点击表格行加载文件
	$(e).css("background-color","#CCFFFF");
	$(e).siblings().css("background-color","");
	var url = null; 
	url = e.getElementsByTagName("input")[0].value;
	$("#del").attr("disabled",false);
	$("#video-frame").empty();//清空视频浏览区域的视频
	$("#title_alter").val($(e).children(".title").html());//填写标题栏
	if($(e).children(".visiable").attr("status")=='1'){//填写显示状态 	
		$("#alter-mesg").children("input[value='1']").prop("checked","true");
	}else{
		$("#alter-mesg").children("input[value='0']").prop("checked","true");
	}
	if(url.indexOf('.html')>0){ //如果是html文件
		setEditorContents(url);
		$("#alter").unbind();
		$("#alter").attr("disabled",false);
		$("#alter").bind("click",alterHtml);
		$("#ckeditor-frame").show().siblings().hide();
	}else if(url.indexOf('.jpg')>0||url.indexOf('.png')>0||url.indexOf('.jpeg')>0
			||url.indexOf('.tif')>0||url.indexOf('.gif')>0){ //如果是图片文件
		setImageContent(url);
		$("#alter").unbind();
		$("#alter").attr("disabled","true");
		$("#image-frame").show().siblings().hide();
	}else if(url.indexOf('.pdf')>0||url.indexOf('.txt')>0){//如果是pdf文件
		setPdfContent(url);
		$("#alter").unbind();
		$("#alter").attr("disabled","true");
		$("#pdf-frame").show().siblings().hide();
	}else if(url.indexOf('.mp4')>0||url.indexOf('.webm')>0
			||url.indexOf('.wmv')>0||url.indexOf('.avi')>0){//如果是视频文件
		setVideoContent(url);
		$("#video-frame").show().siblings().hide();
	}else{//其他文件
		$("#alter").unbind();
		$("#alter").attr("disabled","true");
		$("#other-frame").show().siblings().hide();
	}
	$("#file_id").val(e.getElementsByTagName("input")[1].value);
}
function uploadFile(){//上传文件
	if($("#title").val().replace(" ","")==""){
		alert("请输入文件标题");
		return false;
	}
	$("#upload_file").attr("disabled","true");
	$("#form_content").submit();
}
function uploadCallBack(result){//上传文件回调函数
	if(result=='新建文件成功'){
		alert(result);
		turnPage(parseInt($("#currentPage").html()));
	}else{
		alert("新建文件失败");
	}
	$("#upload_file").attr("disabled",false);
	$("#upload").val("");
	$("#title").val("");
	$("#description").val("");
}
function alter_mesg(){ //修改文件信息
	if($("#title_alter").val()==""){
		alert("标题不能为空");
		return false;
	}
	$.ajax({
		url:'file_alter_mesg.action?aa='+Math.random(),
		data:{title:$("#title_alter").val(),id:$("#file_id").val()
			,visiable:$("#alter-mesg").children("input[name='visiable']:checked").val()},
		type:'POST',
		success:function(data){
			if(data.errorCode="0000"){
				alert("修改信息成功");
				turnPage(parseInt($("#currentPage").html()));
			}else{
				alert("修改信息失败");
			}
		}
	});
}
function del(){ //删除文件
	var result = confirm("你确认要删除该文件吗?");
	if(result==true){
		$.ajax({
			url:'file_delete.action?aa='+Math.random(),
			data:{id:$("#file_id").val()},
			success:function(data){
				if(data.errorCode=="0000"){
					alert("删除文件成功");
					turnPage(parseInt($("#currentPage").html()));
				}else{
					alert("删除文件失败");
				}
			}
		});
	}
}
</script>

</head>

<body>
<div id="detail" class="clear">
	<div id="table">
		<div id="table-content">
			<table id="file_list">
				<caption>文件列表</caption>
				<thead>
				<tr>
					<th>标题名</th>
					<th>类型</th>
					<th>上传时间</th>
					<th>备注</th>
					<th>是否显示</th>
				</tr>
				</thead>
				<tbody>
				</tbody>
			</table><br />
		</div>
		<div id="pageDiv" >
			<a id="top" href="javascript:void(0);">首页</a>
			<div class="indexPage" style="display:inline-block;font-size:12px"></div>
			<a id="bottom" href="javascript:void(0);">尾页</a>
		</div>
		<div id="deal" style="margin:40px;display:inline-block;float:right;margin-right:100px;">
			<input id="del" style="margin-right:27px;" onClick="del()" disabled="disabled"  type="button" value="删除文件"/>
			<input id="alter"  disabled="disabled" type="button" value="保存文件"/>
		</div>
	</div>
	<div id="edit-frame">
		<!--html文件编辑窗口-->
		<div id="ckeditor-frame" style="display: none;">
			<textarea id="html_textarea" name="html_textarea" ></textarea>
		</div>
		<!-- 图片文件浏览窗口 -->
		<div id="image-frame" style="text-align: center;display:none;vertical-align:middle;">
		</div>
		<!-- pdf和txt文件浏览窗口 -->
		<iframe id="pdf-frame" name="pdf-frame" style="display:none;width:100%;height:100%;"></iframe>
		<!-- 视频文件浏览窗口 -->
		<div id="video-frame" style="display:none">
		</div>
		<!--其他文件编辑窗口-->
		<div id="other-frame" style="text-align:center;line-height:450px;width:100%;height:100%;display:none;">
			该类型文件无法浏览
		</div>
		<input type="hidden" id="file_id" value=""/>
	</div>
	<div id="alter-mesg" style="float:left;position:absolute;top:400px;left:20px;">
		标题:<input type="text" id="title_alter"/>
		&nbsp;&nbsp;<input type="button" onClick="alter_mesg()" value="修改文件信息"/></br></br>
		是否显示:&nbsp;&nbsp;<input type='radio' value="1" name="visiable" checked="checked"/>显示
		&nbsp;&nbsp;<input type='radio' value="0" name="visiable"/>不显示
	</div> 
	<div class="hr-line"></div>
	<div id="form" style="display:inline-block;">
		<iframe style="display:none" name="upload_frame" id="upload_frame"></iframe>
		<form id="form_content" action="file_upload.action" enctype="multipart/form-data" method="post" target="upload_frame">
			新建文件&nbsp;&nbsp;&nbsp;<input type="file" width="15" id="upload" name="upload" />
			文件标题:<input type="text" id="title" name="title"/>
			备注:<input type="text" id="description" name="description"/>
			是否显示:&nbsp;&nbsp;<input type='radio' value="1" name="visiable" checked="checked"/>显示
			&nbsp;&nbsp;<input type='radio' value="0" name="visiable"/>不显示
			<input type="button" onClick="uploadFile()" id="upload_file" value="新建" />
			<input type="hidden" id="catalog_id" name="catalog_id" value="${requestScope.catalog_id}"/>
		</form>
	</div>
</div>
</body>
</html>
