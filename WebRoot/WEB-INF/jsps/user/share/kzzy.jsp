<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>扩展资源</title>
</head>
<link rel="stylesheet" href="resources/css/common.css"/>
<!--<link rel="stylesheet" href="resources/css/jpkc.css" media="all" type="text/css"> -->
<!--<link rel="stylesheet" href="resources/css/hou.css" media="all" type="text/css"> -->
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
        <img src="resources/image/expand-scource.jpg" />
       </div>
        <div id="main" class="common clearfix">
             <div id="side" class="same">
                <ul id="menu">
                    <c:forEach items="${catalog_list}" var="catalog">
                	<li>
                    	<%--<img src="resources/image/201505090950172577.png" alt=""/>--%>
                    	<a catalog_id="${catalog.id}" name="btn" href="javascript:;">${catalog.name}</a>
                    	<ul class="list">
                    	</ul>
               	 	</li>
              		 </c:forEach>
                </ul>
            </div>
             <div id="content" class="same">
            <div class="title clearfix">
                <%--<img src="resources/image/sign.png" style="float: left"/>--%>
                <h3>案例大赛规则</h3>
                <p>
                    当前位置><a href="#">首页</a>><a href="#">扩展资源</a>
                </p>
            </div>
            <iframe name="detail" src="" frameborder="0"></iframe>
        </div>
        <!--end content-->
        </div>
    </div>
    <div style="clear:both"></div>
    <div id="footer">
    <iframe src="view/footer.html" frameborder="0" scrolling="no"></iframe>
	</div>
	<!--end footer-->
	<script src="resources/jquery/jquery-1.9.0.min.js"></script>
	<script src="resources/scripts/move_perfect.js"></script>
	<!--<script src="resources/scripts/list_handler.js"></script>-->
	 <script src="resources/scripts/list_handler.js"></script>
</body>
</html>