<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
     <base href="<%=basePath%>">
     <link rel="stylesheet" href="resources/css/iframe.css" type="text/css"/>
</head>
<body id="home">
    <p class="p1">${requestScope.work.title}</p>
     <hr style="clear:both;" />
     <div id="content">${requestScope.work.content}<p>${requestScope.work.user.loginName}
     <br/><span>${requestScope.work.sendDate}</span></p></div>
    <input type="button" value="返回" class="btn" onclick="location.href='view/iframe2.jsp';"  />
</body>
</html>