<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>课程信息</title>
    <link rel="stylesheet" href="resources/css/common.css"/>
    <script src="resources/jquery/jquery-1.9.0.min.js"></script>
    <script>
    	function test(e){
    		$(e).css('height','');
    		$(e).css('height',document.getElementById('detail')
    				.contentWindow.document.getElementsByTagName('body')[0].scrollHeight+1);
    		//document.getElementById('detail').document.getElementsByName('body')[0].scrollHeight;
    	}
 </script>
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
    <img src="resources/image/course-information.jpg" />
</div>
<!--end header-->
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
    <!--end side-->
    <div id="content" class="same">
        <div class="title clearfix">
            <%--<img src="resources/image/sign.png" style="float: left"/>--%>
            <h3>各章课后案例目录</h3>
            <p>
                当前位置><a href="#">首页</a>><a href="#">案例资源</a>><a href="#">课后案例</a>>各章课后案例目录
            </p>
        </div>
        <iframe id="detail" name="detail" src="" frameborder="0"
          onload="test(this)" ></iframe>
    </div>
    <!--end content-->
</div>
<!--end main-->
<div id="footer">
    <iframe src="view/footer.html" frameborder="0" scrolling="no"  ></iframe>
</div>
<!--end footer-->
<script src="resources/scripts/move_perfect.js"></script>
<!--<script src="resources/scripts/list_handler.js"></script>-->
 <script src="resources/scripts/list_handler.js"></script>
</body>
</html>