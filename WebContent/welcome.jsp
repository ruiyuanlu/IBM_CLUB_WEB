<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
//以下代码测试能否获取在action中自定义的session，如果该段文字无乱码地正常显示则没有问题
String info="";
if(session.getAttribute("infofromAction2jsp")!=null){
	info=(String)session.getAttribute("infofromAction2jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%=info %>
    Welcome!欢迎！ <br>
    <a href="fileupload">上传文件测试</a><br/>
    <a href="intervieweeget" target="_blank">面试功能测试</a>
    <a href="Logout">登出</a>
  </body>
</html>
