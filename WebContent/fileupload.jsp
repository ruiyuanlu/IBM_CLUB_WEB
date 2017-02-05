<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文件上传测试</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  上传文件格式限定为pdf, doc, docx和wps，上传的文件大小不得超过5M。
　　<form action="HomeworkDocAction" method="post" enctype="multipart/form-data">
        文件选择: <input type="file" name="file"/><font color="red"><s:fielderror fieldName="fileerror"/></font><br/>  
        <input type="submit" value="上传">
        <s:token></s:token>
    </form>
  </body>
</html>
