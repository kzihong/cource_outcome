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
	 });
	</script>
	<c:forEach items="${catalog_list}" var="catalog" >
 	<script>
  		catalog_array.push("${catalog.id}"+","+"${catalog.name}");
	</script>
 	</c:forEach>
 	<link rel="stylesheet" href="resources/css/index.css" media="all" type="text/css">
</head>
<body>
<div id="cover">
</div>
<div id="register">
    <div id="align">
        <input type="radio" name='r_user-type' id="stu" checked="checked"/>
        <label for="stu">学生</label>
        <input type="radio" name='r_user-type' id="tea"/>
        <label for="tea">老师</label>
        <br />
        <span>用户名</span><input type="text" class="text"/>
        <br />
        <span>密码</span><input type="text" class="text">
        <br/>
        <div style="clear:both;"></div>
        <input type="button" class="btn" value="注册">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" class="btn" value="关闭">
    </div>
</div>
<div id="content">
    <img src="resources/image/head-logo.jpg" class="headimg">
    <ul id="nav">
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
            <div>
                <input type="radio" name='user-type' id="stu" checked="checked"/>
                <label for="stu">学生</label>
                <input type="radio" name='user-type' id="tea"/>
                <label for="tea">老师</label>
            </div>
            <form action="" method="">
                <!--表单的信息还未填写-->
                <div>
                    用户名&nbsp;<input type='text' size=12/>
                </div>
                <div>
                    密码 &nbsp;<input type="password" size=12>
                </div>
                <div style="text-align:center">
                    <input type='button' value="登录">
                    &nbsp;
                    <input type='button' value="注册" id="registerbBtn">
                </div>
            </form>
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
</script>
</html>