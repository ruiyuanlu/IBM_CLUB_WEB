<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.istc.bean.Person" %>
<%
    Person p=new Person();
    String id=null;
    try {
        p=(Person)session.getAttribute("personInfo");
        id=p.getID();
    }
    catch (Exception e){
        id=null;
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>istc</title>
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
  <h1>欢迎访问西交IBM俱乐部官网！</h1>
  <% if(id==null){ %>
  <a href="register">面试申请</a><br/>
    <a href="login">登录</a><br/>
  <%}
  else{%>
      欢迎您，<%=id%><br/>
  <a href="welcome">进入个人中心</a><br/>
      <%}%>
  </body>
</html>
