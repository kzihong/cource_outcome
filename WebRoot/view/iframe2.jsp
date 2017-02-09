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
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="resources/scripts/ajax.js"></script>
    <script src="resources/jquery/jquery-1.9.0.min.js"></script>
    <link rel="stylesheet" href="resources/css/iframe.css"/>
    <script type="text/javascript">
    window.onload=function(){
        quesList(1,8);
        document.getElementById("ntitle").onblur=function(){
    		if(this.value.replace(" ","")=="")
            	this.value="请输入标题";
        }
        document.getElementById("ntitle").onfocus=function(){
        	if(this.value=="请输入标题")
            	this.value="";
        }
        $("#btn").click(function(){
            document.getElementById("bg").style.display ="block";
            document.getElementById("show").style.display ="block";
        });
        $("#sub").click(function(){
            var nTitle=document.getElementById("ntitle");
            var nContent=document.getElementById("ncontent");
            $.ajax({
                url:'online_uploadWork.action?dd='+Math.random(),
                type:'post',
                data:{title:nTitle.value,content:nContent.value},
                success:function(data){
                	if(data.errorCode='0000'){
	                    alert("作业已经提交");
	                    document.getElementById("bg").style.display ="none";
	                    document.getElementById("show").style.display ="none";
	                    nTitle.value="请输入标题";
	                    nContent.value="";
	                    quesList(1,8);
                	}else{
                		alert(data.errorMessage);
                	}
                }
            })
        });
        $("#can").click(function(){
            document.getElementById("bg").style.display ="none";
            document.getElementById("show").style.display ="none";
        })
     };
    </script>
    <script type="text/javascript">
        function quesList(pageOffset,pageSize){
             $.ajax({
                    url:'online_workList.action?cc='+Math.random(),
                    type:'get',
                    data:{pageOffset:pageOffset,
                               pageSize: pageSize
                    },
                    success: function(data){
                        var oUl=document.createElement("ul");
                        oUl.className="list";
                        oUl.id="list";
                        var pager_list = data.pager_list;
                        var datas = pager_list.datas;
                        $("#list").remove();
                        document.getElementsByTagName("body")[0].appendChild(oUl);
                        for(var i=0;i<datas.length;i++){
                            var oLi=document.createElement("li");
                            oLi.className="que-contain";
                            oLi.id=datas[i].id;
                            oLi.innerHTML=datas[i].title+'<span class="span1">'+datas[i].user_name+'</span><span class="span2">'+datas[i].sendDate+'</span>';
                            oLi.onmouseover=function() {
                                this.style.backgroundColor="rgb(228,79,55)";
                            }
                            oLi.onmouseout=function(){
                                this.style.backgroundColor="";
                            }
                            oLi.onclick=function(){
                                window.location.href='online_workInfo.action?id='+this.id;
                            }
                            oUl.appendChild(oLi);
                        }
                        //var li=document.getElementsByTagName("li");
                       page({
                            id: "paging",
                            Max: pager_list.totalPages,
                            now: pager_list.currentPage,
                            fn: function(head,tail,now){
                                 var toolBar = document.getElementById("paging");
                                 toolBar.innerHTML="";
                                 if(now!= 1){
                                     toolBar.innerHTML="<a href='javascript:void(0);' page="+1+">首页</a>";
                                    toolBar.innerHTML+="<a href='javascript:void(0);'>上一页</a>";
                                 }
                                 for(i = head;i<=tail;i++){
                                     if(i==now){
                                         toolBar.innerHTML+="<a id='current'>"+i+"</a>";
                                     }else{
                                        toolBar.innerHTML+=" <a href='javascript:void(0);' page="+i+">"+i+"</a>";
                                     }
                                 }
                                 if(now!=pager_list.totalPages){
                                    toolBar.innerHTML+="<a href='javascript:void(0);'>下一页</a>";
                                    toolBar.innerHTML+="<a href='javascript:void(0);' page="+pager_list.totalPages+">尾页</a>";
                                 }
                            }
                        });
                    }
                });
        }
    </script>
</head>
<body>
    <div id="paging"></div>
      <c:if test="${sessionScope.user.role.name=='老师'||sessionScope.user.role.name=='管理员'}">
    	<input type="button" value="发布作业" id="btn">
    </c:if>
    <div id="num" style="display:none"></div>
    <!--遮罩层显示问题具体情况-->
    <div id="bg"></div>
    <div id="show">
        <div id="icon"></div>
        发布作业
        <input  id="ntitle" type="text" value="请输入标题"  /><br />
        <textarea id="ncontent"></textarea><br />
        <input type="button" value="提交" id="sub" />
        <input type="button" value="关闭" id="can" />
    </div>
     <ul class="list">
        <li>作业标题<span class="span1">发布者</span><span class="span2">时间</span></li>
    </ul>
</body>
</html>