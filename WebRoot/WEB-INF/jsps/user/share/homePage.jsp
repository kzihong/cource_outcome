<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
 	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>首页</title>
	<script type="text/javascript" src="resources/jquery/jquery-1.9.0.min.js" ></script>
	<script type="text/javascript" src="resources/scripts/homepage.js"></script>
	<script type="text/javascript">
		var catalog_array = [];
	$(function(){
		$(".block").each(function(){
			var $this = $(this);
			//添加个（正在加载请稍后的图片)
			$.ajax({
				url:'file_load.action?aa='+Math.random(),
				data:{catalog_id:$this.attr("catalog_id")},
				success:function(data){
					if(data.errorCode="0000"){
						$.each(data.file_list,function(i,item){
							if(item.visiable==1){
								if(item.type=="html"){
									$this.children(".describe").load("file_read.action?url="+item.url,function(){
										$this.children(".describe").append("<br/>"+"<a href='javascript:;' class='more'>更多信息>></a>");
									});
									return; //默认只加载第一张html
								}else if(item.type=="jpg"||item.type=="png"||item.type=="gif"){
									$this.children("#team-div").children("ul").append("<li><img src='file_read.action?url="+item.url+"'/></li>");
								}
							}
						});
						//删除这个加载图片
					}
				}
			});
		});
		pic_row();
		var oRegister=document.getElementById("register");
    	var oCover=document.getElementById("cover");
   	 	var registerBtn=document.getElementById("registerbBtn");
    	registerBtn.onclick=function(){
        oRegister.style.display='block';
        oCover.style.display='block';
   		 };
   	 oRegister.getElementsByClassName('btn')[1].onclick=function(){
        oRegister.style.display='none';
        oCover.style.display='none';
    };
	var oUlTeam=document.getElementById('team');
    oUlTeam.onmouseover=function(){
        clearInterval(timer);
    };

    oUlTeam.onmouseout=function(){
        timer=setInterval(function(){
            oUlTeam.style.left=parseInt(getStyle(oUlTeam,"left"))-1+"px";
            if(parseInt(oUlTeam.style.left)==-290){
                oUlTeam.appendChild(oUlTeam.children[0]);
                oUlTeam.style.left=0;
            }
        },25);
    }
    var register_form = document.getElementById("register_form");
    var login_form = document.getElementById("login_form");
    register_form.onsubmit=function(){ //注册
			if(this.register_name.value.replace(" ","")==""){
				alert("用户名不能为空");
				return false;
			}
			if(this.register_pwd.value.replace(" ","")==""){
				alert("密码不能为空");
				return false;
			}
		};
    login_form.onsubmit =function(){
    	if(this.loginName.value.replace(" ","")==""){
			alert("用户名不能为空");
			return false;
		}
		if(this.password.value.replace(" ","")==""){
			alert("密码不能为空");
			return false;
		}
    };
	 });
	</script>
	<c:forEach items="${catalog_list}" var="catalog" >
 	<script>
  		catalog_array.push("${catalog.id}"+","+"${catalog.name}");
	</script>
 	</c:forEach>
</head>
<link rel="stylesheet" href="resources/css/homepage.css" media="all" type="text/css">
<body>
    <div id="cover">
    </div>
    <div id="register">
        <div id="align">
        	<form id="register_form" action="user_register.action" method="POST">
	            <input type="radio" name='role' value="STUDENT" id="stu" checked="checked"/>
	            <label for="stu">学生</label>
	            <input type="radio" name='role' value="TEACHER" id="tea" />
	            <label for="tea">老师</label>
	            <br />
	            <span>用户名</span><input type="text" name="loginName" id="register_name" class="text"/>
	            <br />
	            <span>密码</span><input type="password" name="password" id="register_pwd" class="text">
	            <br/>
	            <div style="clear:both;"></div>
	            <input type="submit" class="btn" value="注册">
	            &nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="button" class="btn" value="关闭">
	          </form>
        </div>
    </div>
	<div id="content">
		<img src="resources/image/head-logo.jpg" class="headimg">
		<ul id="nav">
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
		<img src="resources/image/theme-img.jpg" class="headimg">
		<div id="main">
			<div class="block" style="width:608px;" title="课程负责人">
				<p class="headline">课程负责人</p>
				<p class="describe" style="margin-bottom:0;margin-left:3%;line-height: 1.2;">
					<br /><br />
				</p>
				<div style="clear:both"></div>
			</div>
			<div class="block" id='login' style="width:200px;height:242px;margin-left:20px">
				<p class="headline">登录注册</p>
				 	<c:choose>
				 	<c:when test="${sessionScope.user!=null}">
				 		欢迎您，${sessionScope.user.loginName}!<a href="user_loginOut.action">退出登录</a>
				 		<c:if test="${sessionScope.user.role.name=='管理员'}"></br></br><a href="user_admin.action">进入管理员界面</a></c:if>
				 	</c:when>
				 	<c:otherwise>
				 		<form id="login_form" action="user_login.action" method="POST">
						<div>
							<input type="radio" name='role' value="STUDENT" id="stu" checked="checked"/>
							<label for="stu">学生</label>
							<input type="radio" name='role' value="TEACHER" id="tea"/>
							<label for="tea">老师</label>
							<input type="radio" name='role' value="ADMIN" id="tea"/>
							<label for="tea">管理</label>
						</div>
						<!--表单的信息还未填写-->
							<div>
								<span>用户名</span><input type='text' size=12 name="loginName"/>
							</div>
							<div>
								<span>密码</span><input type="password" size=12 name="password">
							</div>
							<div style="text-align:center">
								<input type='submit' value="登录">
								&nbsp;
								<input id="registerbBtn" type='button' value="注册" >
							</div>
						</form>
					</c:otherwise>
					</c:choose>
				<div style="clear:both"></div>
			</div>
			<div class="block" title="课程简介">
				<p class="headline">课程简介</p>
				<p class="describe" style="margin-bottom:0;margin-right:3%;line-height: 1.5;"><br /><br />
					
				</p>
				<div style="clear:both"></div>
			</div>
			<div class="block" title="课程团队" style="height:240px;">
				<p class="headline">课程团队</p>
				<div id="team-div">
					<ul id="team" class="team">
					</ul>
				</div>
				<div style="clear:both"></div>
			</div>
			<div style="clear:both"></div>
		</div>
	</div>
	<div style="clear:both"></div>
	<p class="foot">CopyRight 2009 School of Management,GDUT. 广东工业大学 管理学院<br />
<a href="javascript:;" style="color:red">联系我们</a> | 广东工业大学 电话：86-020-87083017 E-mail: glxy@gdut.edu.cn<br />
地址：中国 广州 天河区 迎龙路 161号 邮编：510520</p>

 <script>
   	   var contents = $(".block");
   	   for(var i = 0 ;i<catalog_array.length;i++){
   		   var content = null;
   		   var message = catalog_array[i].split(",");
   		   content = $("div[title='"+message[1]+"']");
   		   if(null!=content){
   			   content.attr("catalog_id",message[0]);
   		   }
   	   }
   </script>
</body>
</html>