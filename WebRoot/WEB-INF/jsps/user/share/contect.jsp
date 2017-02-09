<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>在线交流</title>
    <!--<link rel="stylesheet" type="text/css" href="../../css/index.css">-->
    <link rel="stylesheet" href="resources/css/common.css"/>
</head>
<body>
    <div id="header" class="common">
        <img src="resources/image/head-logo.jpg" />
        <div id="nav">
            <ul class="clearfix">
                <li><a href="catalog_query_user.action?parent_id=01&target=homePage">首页</a></li>
	            <li><a href="catalog_query_user.action?parent_id=02&target=information">课程信息</a></li>
	            <li><a href="catalog_query_user.action?parent_id=03&target=source">教学资源</a></li>
	            <li><a href="catalog_query_user.action?parent_id=04&target=project_video">课程视频</a></li>
	            <li><a href="catalog_query_user.action?parent_id=05&target=example">案例资源</a></li>
	            <li><a href="catalog_query_user.action?parent_id=06&target=creative">创新实践</a></li>
	            <li><a href="catalog_query_user.action?parent_id=07&target=sjjx">实践教学示范</a></li>
	            <li><a href="catalog_query_user.action?parent_id=08&target=kzzy">扩展资源</a></li>
	             <li><a href="online_toOnline.action">在线交流</a></li>
	            <li><a href="catalog_query_user.action?parent_id=01&target=homePage_declare">申报网站</a>
            </ul>
        </div>
        <img src="resources/image/communicate-online.jpg" />
    </div>
    <!--end header-->
    <div id="main" class="common clearfix">
        <div id="side" class="same">
            <ul id="menu">
                <li>
                    <a name="btn" href="view/iframe1.jsp" target="detail">在线问题解答</a>
                    <ul class="list">
                        
                    </ul>
                </li>
                <li>
                    <a name="btn" href="view/iframe2.jsp" target="detail">在线作业发布</a>
                    <ul class="list">
                    </ul>
                </li>
            </ul>
        </div>
        <!--end side-->
        <div id="content" class="same">
            <div class="title clearfix">
                <img src="resources/image/sign.png" style="float: left"/>
                <h3>在线问题解答</h3>
                <p>
                    当前位置><a href="#">首页</a>><a href="#">在线交流</a>><a href="#">在线问题解答</a>
                </p>
            </div>
            <iframe name="detail" src="view/iframe1.jsp" frameborder="0" id="if"></iframe>
        </div>
        <!--end content-->
    </div>
    <!--end main-->
    <div id="footer">
    <iframe src="view/footer.html" frameborder="0" scrolling="no" allowtransparency></iframe>
	</div>
    <!--end footer-->
    <script src="resources/jquery/jquery-1.9.0.min.js"></script>
    <script src="resources/scripts/move_perfect.js"></script>
    <script src="resources/scripts/list_handler.js"></script>
</body>
</html>