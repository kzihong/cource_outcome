<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>站内列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="resources/css/dialog.css">
	<script type="text/javascript" src="resources/jquery/jquery-1.11.1.js" ></script>
	 <style type="text/css">

  	.link{
  		background-color:white;
  		height:50px;
  		line-height:20px;
  		text-align:center;
  	}
  	.link span{
  		display:inline-block;
  		margin-top:16px;
  	}
  	#link_list{
  		width:100%;
  		font-size:16px;
  		white-space:nowrap;
  		border:1px solid;
  		text-align:center;
  		text-overflow:ellipsis;
  	}
  	#link_list th{
  		background-color:#76A6F0;
  	}
  	#link_list tr{
  		cursor:pointer;
  	}
  	.decoration{
  		background-color:#CCFFFF;
  	}
  </style>
  <script type="text/javascript">
  	$(function(){
  		load();
  	});
  </script>
  <script type="text/javascript">
  var view_page ="";//展示页地址
  function load(){
	  $.ajax({
		 url:'file_url_list.action?aa='+Math.random(),
		 data:{'catalog_id':$("#catalog_id").val()},
		 type:'POST',
		 success:function(data){
			 if(data.errorCode=="0000"){
				 $("#link_list tbody").empty();//清空列表
				 $.each(data.list_link,function(i,item){
					 $("<tr onClick='check(this)'>"+
						"<td>"+item.title+"</td>"+
						"<td class='type'>"+item.type+"</td>"+
						"<td class='url'>"+item.url+"</td>"+
						 "</tr>").appendTo($("#link_list tbody"));
				 });
			 }
		 }
	  });
  }
  function check(e){
	  $(e).addClass("decoration");
	  $(e).siblings().removeClass("decoration");
  }
  function certain(){ //确认事件
	  var $result = $(".decoration").first();
  	  if(!(typeof($result.html())=="undefined")){
  		  var url = $result.children(".url").html();
  		  window.opener.document.getElementById($("#urlId").val()).value=view_page+"?url="+url;
  		  window.close();
  	  }
  }
  </script>
  </head>
 
  <body>
   <div class="content">
   <input type="hidden" id="urlId" value="${param.urlId}"/>
   <input type="hidden" id="catalog_id" value="${param.catalog_id}"/>
    <div class="title" >
    	<span style="float:left;margin-top:10px;font-size:14px;">站内链接</span>
    	<a class="btn" id="certain" onClick="certain()">确认</a>
    </div>
    	<table id="link_list">
				<caption>可用链接列表</caption>
				<thead>
				<tr>
					<th>标题名</th>
					<th>类型</th>
					<th>url链接</th>
				</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
    </div>
  </body>
</html>
