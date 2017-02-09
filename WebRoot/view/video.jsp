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
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <script src="resources/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
window.onload=function(){
    var Sys = {};
    var url = window.location.href;
    url = url.substring(url.indexOf("=")+1);
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 0;
    document.write("<object id='video' width='680' height='440' border='0'>"+
            "<param name='ShowDisplay' value='0'>"+
    "<param name='ShowControls' value='1'>"+
    "<param name='AutoStart' value='0'>"+
    "<param name='AutoRewind' value='0'>"+
    "<param name='PlayCount' value='0'>"+
    "<param name='Appearance' value=''>"+
    "<param name='loop' value='0'>"+
    "<param name='FileName' value='"+url.substring(url.indexOf("/")+1)+"'>"+
 "<embed width='100%' height='440' border='0'  showdisplay='0' showcontrols='1' "+
 "autostart='0' autorewind='0' playcount='0' moviewindowheight='400' moviewindowwidth='400' loop='0'"+
 "filename='file_read.action?url="+url+"' src='file_read.action?url="+url+"' />"+  
"</object>"+
"  </br>"+
    "<p style='text-align:center'>如若不能播放可以下载插件或者转向IE浏览器播放,插件下载地址：<a id='plugin' href='javascript:void(0);'>插件下载</a></p>");
    //以下进行测试
    if (Sys.firefox) $("#plugin").attr("href",'resources/video-plugin/wmpplugin.xpi');
    if (Sys.chrome)  $("#plugin").attr("href",'resources/video-plugin/wmpfirefoxplugin.exe');
};
</script>
  </head>
  <body>
  <!--<object id="video" width="680" height="440" border="0">
                      <param name="ShowDisplay" value="0">
                      <param name="ShowControls" value="1">
                      <param name="AutoStart" value="0">
                      <param name="AutoRewind" value="0">
                      <param name="PlayCount" value="0">
                      <param name="Appearance value=" value="">
                      <param name="loop" value="0">
                      <param name="FileName" value="V51010-234706.wmv">
                  <embed width="680" height="440" border="0"  showdisplay="0" showcontrols="1" 
                  autostart="0" autorewind="0" playcount="0" moviewindowheight="400"
                   moviewindowwidth="400" loop="0" 
                   filename="file_read.action?url=2/96fffcf978e54772a658d47cdb5d0de3.mp4/" src="resources/example.avi" >  
   </object>
   -->
  </body>
  
</html>
