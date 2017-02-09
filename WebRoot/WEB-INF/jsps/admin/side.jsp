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
<title>目录侧栏</title>
<style type="text/css">
	*{
		font-family:"Microsoft YaHei",Arial,Helvetica,sans-serif;
		font-size:14px;
	}
	body{
		margin:0;
		padding:0;
		/*background:url(bg_1.jpg) repeat-x;*/	
	}
	ul,li{
		margin:0;
		padding:0;
		list-style:none;	
	}
	li{
		/*border-bottom:1px dashed;*/
		padding:4px 16px;	
	}
	#frame{
		display:none;
		position:absolute;
		text-align:center;
		top:380px;
		left:20px;
		background:#fff;
		border:1px solid #ddd;
		width:200px;
		height:100px;
	}
	#catalog-list{
		/*border:1px solid;*/
		width:100%;
		height:420px;	
	}
	#toolBar{
		position:absolute;
		top:480px;
		left:40px
	}
	input{
		background:#eee;
		border:1px solid #ddd;
	}
</style>
<link rel="stylesheet" type="text/css" href="resources/zTree/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="resources/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="resources/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="resources/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="resources/zTree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
	var zTree;
	var currentNode = null;
	$(function(){
	  zTree = $.fn.zTree.init($("#tree"),setting,zTreeNodes);
	  var defalutNode = zTree.getNodeByParam("首页",null,null);
	  zTreeBeforeExpand(null,defalutNode);
	  var firstChild = defalutNode.children[0];//选中第一个子节点
	  zTree.selectNode(firstChild);
	  zTreeClick(null,null,firstChild);
	});
</script>
<script type="text/javascript">
 var setting = null;
 	setting={
				async:true,
				data:{
					isSimpleData:true,
				},
				view:{
					expandSpeed:"fast",
					showLine:true,
					keepParent:true,
				},
				callback:{
					onClick:zTreeClick,
					remove:null,
					beforeExpand:zTreeBeforeExpand,
					onExpand:null,
					nodeCreated:null
				}
		};
		var zTreeNodes=[
		    {isParent:true,id:'01',name:"首页"},
		    {isParent:true,id:'02',name:"成果内容"},
		    {isParent:true,id:'03',name:"成果总结"},
		    {isParent:true,id:'04',name:"创新点"},
		    {isParent:true,id:'05',name:"成果应用"},
		    {isParent:true,id:'06',name:"成果水平"},
		    {isParent:true,id:'07',name:"申报书"},
		    {isParent:true,id:'08',name:"实践基地"},
		    {isParent:true,id:'09',name:"竞赛活动"},
		    {isParent:true,id:'10',name:"成果附件"}];
		function zTreeBeforeExpand(treeId,treeNode){
			zTree.removeChildNodes(treeNode);
			treeNode.isParent=true;
			$.ajax({
				url:'catalog_query.action?aa='+Math.random(),
				type:'GET',
				async: false,
				data:{parent_id:treeNode.id},
				success:function(data){
					if(data.errorCode=="0000"){
						zTree.addNodes(treeNode,data.catalog_list);
					}else{
					}
				}
			});
			return false;
		}
		function zTreeExpand(event,treeId,treeNode){
		}
		function zTreeClick(event,treeId,treeNode){
			currentNode=treeNode;
			if(!treeNode.isParent){
				window.parent.document.getElementById("content").src="file_list.action?catalog_id="+treeNode.id;
			}
		}
	</script>
	<script type="text/javascript">
	//对结点操作
	function checkAdd(){
		if(null == currentNode||!currentNode.isParent){
			alert("请先选择父目录");
		}else{
			$("#frame").fadeToggle(80);
			$("#newNode").val("目录名称");
		}
	};
	function del(){
		if(null==currentNode||currentNode.isParent){
			alert("请先选择要删除的子目录");
		}else{
			var result = confirm("你确认要删除该目录吗?");
			if(result==true){
				$.ajax({
					url:'catalog_delete.action?aa='+Math.random(),
					type:'POST',
					data:{id:currentNode.id},
					success:function(data){
						if(data.errorCode=="0000"){
							alert("删除目录成功");
							zTree.removeNode(currentNode);
							currentNode.getParentNode().isParent=true;
						}else{
							alert("删除目录失败");
						}
					}
				});
			}
		}
	}
	function add(){
		var name = $("#newNode").val();
		if(name=="目录名称"||name==''){
			alert("请输入正确的目录名称");
			return false;
		}
		if(null == currentNode||!currentNode.isParent){
			alert("请先选择父目录");
			return false;
		}
		$.ajax({
			url:'catalog_create.action?aa='+Math.random(),
			type:'POST',
			data:{'name':name,'parent_id':currentNode.id},
			success:function(data){
				if(data.errorCode=="0000"){
					alert("添加目录成功");
					zTreeBeforeExpand(null,currentNode);
					$("#newNode").val("目录名称");
					$("#frame").hide();
				}else{
					alert(data.errorMessage);
				}
			}
		});
	}
	function cancel(){
		$("#frame").fadeToggle(80);
		$("#newNode").val("目录名称");
	}
	function txtfocus(){
		if($("#newNode").val()=="目录名称"){
			$("#newNode").val("");
		}
	}
	function txtblur(){
		if($("#newNode").val()==""){
			$("#newNode").val("目录名称");
		}
	}
</script>
</head>
<body>
<div id="catalog-list">
	<ul id="tree" class="ztree">
	</ul>
	<div id="frame">
		<div class="title" style="background-color:#2b99ce;text-align:center;height:15px">
		</div>
		<input type="text" id="newNode" onfocus="txtfocus()" onblur="txtblur()" value="目录名称" style="color:#aaa;background-color:#fff;margin:10px;height:30px"/>
		<input type="button" onClick="add()" value="确定" style="cursor: pointer;"/>&nbsp;&nbsp;&nbsp;
		<input type="button" onClick="cancel()" value="取消" style="cursor: pointer;"/>
	</div>
	<div id="error">
	</div>
	<div id="toolBar">
		<input type="button" onClick="checkAdd()" value="增加子目录" style="cursor: pointer;">
		<input type="button" onClick="del()" value="删除子目录" style="cursor: pointer;">
	</div> 
</div>
</body>
</html>
