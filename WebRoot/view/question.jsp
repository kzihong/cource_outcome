<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="resources/css/iframe.css" type="text/css" />
    <script type="text/javascript" src="resources/jquery/jquery-1.9.0.min.js" ></script>
    <script type="text/javascript">
    	$(function(){
    		$("#send").click(function(){
    			if($("#text").val()==""){
    				alert("发送内容不能为空");
    				return false;
    			}
    			$.ajax({
    				url:'online_answerQues.action?aa='+Math.random(),
    				data:{id:$("#id").val(),content:$("#text").val()},
    				success:function(data){
    					if(data.errorCode=="0000"){
    						alert("回答问题成功");
    						var date = new Date();
    						var formatDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()
    						+":"+date.getMinutes()+":"+date.getMinutes()+":"+date.getSeconds();
    						$("#contain").children("ul")
    						.append("<li>"+$("#text").val()+"<p><span>${sessionScope.user.loginName}</span><br/>"+formatDate+"</p></li>");
    					}else{
    						alert(data.errorMessage);
    					}
    				}
    			})
    		});
    	});
    </script>
</head>
<body id="home">
	<c:set var="question" value="${requestScope.question}"></c:set>
	<input type="hidden" value="${question.id}" id="id"/>
    <div id="title">
    ${question.title}
    </div>
    <div id="time">${question.sendDate}</div>
    <hr style="clear:both;"/>
    <div id="content">${question.content }</div>
    <hr />
    <div id="contain">
        <ul style="list-style: none;">
        	<c:forEach items="${requestScope.answer_list}" var="answer">
            	<li>${answer.content}<p><span>${answer.user.loginName}</span><br/>${answer.sendDate}</p></li>
            </c:forEach>
        </ul>
    </div>
     <c:if test="${sessionScope.user.role.name=='老师'||sessionScope.user.role.name=='管理员'}">
	    <div style="text-align:center"><textarea id="text"></textarea><br/></div>
	    <input type="button" value="提交" class="btn" id="send" />
	    <input type="button" value="返回" onClick="window.location.href='view/iframe1.jsp';"/>
	 </c:if>
</body>
</html>