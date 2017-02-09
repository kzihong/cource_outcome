<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=10"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理</title>
<style>
	body{
		margin:0;
		padding:0;
		background:url(resources/image//bg_1.jpg) repeat-x;
	}
	.header{
		background:url(resources/image//bg.jpg) no-repeat;
		width:100%;
		background-size:100% 100%;
		height:108px;
		min-width:1260px;
		border-bottom:2px solid #999;
		box-shadow:0 2px 2px 2px #999;	
	}
	.vital{
		/*border: 1px solid #CCC;*/
		width: 93%;
		height: auto;
		margin:0 auto;
		min-width:1260px;
		min-height:540px;
		white-space:nowrap;	
	}
	.side,.content{
		display:inline-block;
		/*float:left;*/
		padding-bottom:10px;	
	}
	.side{
		border:1px solid #999;
		width:18%;
		height:auto;
		margin-left:6px;
		min-width:230px;	
	}
	.catalog,.detail{
		background:url(resources/image//title-bg.jpg) repeat-x;
		background-size:100% 100%;
		width:100%;
		height:46px;
		line-height:46px;
		text-align:center;
		font-weight:bolder;
		letter-spacing:10px;
		color:#fff;
	}
	.content{
		border:1px solid #999;
		width:78%;
		height:auto;
		margin-left:6px;
		min-width:794px;
	}
	iframe{
		width:100%;
		height:512px;	
	}
	.clear{
		*zoom:1;	
	}
	.clear:after{
		content:"";
		display:table;
		clear:both;	
	}
	.footer{
		width:100%;
		height:auto;	
	}
</style>

</head>

<body>
<div class="header"></div><br />
<div class="vital clear">
	<div class="side">
		<div class="catalog">
			<img src="resources/image/201505090950172577.png" style="width:12%;height:auto;position:relative;top:8px;" />&nbsp;目录菜单
		</div>
		<iframe frameborder="0" src="catalog_side.action" scrolling="no"></iframe>
	</div>
	<div class="content">
		<div class="detail">
			<img src="resources/image/201505090950100905.png" style="width:4%;height:auto;position:relative;top:8px;" />&nbsp;详细内容
		</div>
		<iframe frameborder="0" id="content" src="" scrolling="no"></iframe>
	</div>
</div>
<!--预留的底部-->
<div class="footer"><br /><br /><br /><br /><br /></div>
</body>
</html>
