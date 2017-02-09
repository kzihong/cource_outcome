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
    <title>教学资源</title>
    <link rel="stylesheet" href="resources/css/public.css"/>
</head>
<body>
<div id="header" class="common">
    <img src="resources/image/head-logo.jpg" />
    <div id="nav">
        <ul class="clearfix">
            <li><a href="catalog_query_user.action?parent_id=01&target=homePage_declare">首页</a></li>
            <li><a href="catalog_query_user.action?parent_id=09&target=courser">课程负责人</a></li>
            <li><a href="catalog_query_user.action?parent_id=10&target=tutors">主讲教师</a></li>
            <li><a href="catalog_query_user.action?parent_id=11&target=group">教学队伍</a></li>
            <li><a href="catalog_query_user.action?parent_id=12&target=description">课程描述</a></li>
            <li><a href="catalog_query_user.action?parent_id=13&target=outcome">教学效果</a></li>
            <li><a href="catalog_query_user.action?parent_id=14&target=comment">自我评价</a></li>
            <li><a href="catalog_query_user.action?parent_id=15&target=setup">课程建设</a></li>
            <li><a href="catalog_query_user.action?parent_id=16&target=measure">政策措施</a></li>
            <li><a href="catalog_query_user.action?parent_id=03&target=resource">教学资源</a></li>
            <li><a href="catalog_query_user.action?parent_id=06&target=creative_declare">创新实践</a></li>
            <li><a href="">申报书</a></li>
            <li><a href="catalog_query_user.action?parent_id=01&target=homePage">资源共享网站</a></li>
        </ul>
    </div>
    <img src="resources/image/theme-img.jpg" />
</div>
<!--end header-->
<div id="main" class="common clearfix">
    <div id="side" class="same">
          <ul id="menu">
                <c:forEach items="${catalog_list}" var="catalog">
                	<li>
                    	
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
            <h3>案例大赛规则</h3>
            <p>
                当前位置><a href="#">首页</a>><a href="#">创新实践</a>><a href="#">案例大赛</a>>案例大赛规则
            </p>
        </div>
        <iframe name="detail" src="" frameborder="0"></iframe>
    </div>
    <!--end content-->
</div>
<!--end main-->
<div id="footer" class="common">
    <iframe src="view/footer.html" frameborder="0" scrolling="no"></iframe>
</div>
<!--end footer-->
 <script src="resources/jquery/jquery-1.9.0.min.js"></script>
<script src="resources/scripts/move_perfect.js"></script>
<script src="resources/scripts/list_handler.js"></script>
<!--<script src="../js/current.js"></script>-->
</body>
</html>