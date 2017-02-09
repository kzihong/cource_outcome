<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>图片管理</title>
	<script type="text/javascript" src="resources/jquery/jquery-1.11.1.js" ></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="resources/css/dialog.css">
	<script type="text/javascript">
		$(function(){
			load();
		});
	</script>
	<script type="text/javascript" src="resources/scripts/photo.js"></script>
  </head>
  <body>
   <input type="hidden" value="${param.urlId}" id="urlId"/>
   <input type="hidden"  id="path"/>
   <div class="content">
   <div class="title" >
   		<span style="float:left;margin-top:10px;font-size:14px;">图片管理</span>
  		<a class="btn" id="certain" onClick="certain()">确认</a>
  		<a class="btn" id="del" onClick="del()" style="display:none;">删除</a>
  		<a class="btn" id="exit" onClick="exit()" style="display:none;">退出</a>
   		<a class="btn" id="edit" onClick="edit()">编辑管理</a>
   	</div>
   	<div id="photo_list">
   		
   	</div>
   </div>
  </body>
</html>
