<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.istc.bean.Person" %>
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


  </head>
  
  <body>
    欢迎，<%=((Person)session.getAttribute("personInfo")).getID()%> <br>
    <a href="fileupload" target="_blank">上传文件测试</a><br/>
    <a href="intervieweeGet" target="_blank">面试功能测试</a><br/>
    <a href="attendance" target="_blank">手动签到</a><br>
    <a href="QRcodesign" target="_blank">开始扫码签到</a><br>
    <a href="memmodify?&dept=1" target="_blank">人员管理测试</a><br>
    <a href="personinfomanagement" target="_blank">用户信息管理测试</a><br>
    <a href="deptmanagement" target="_blank">部门管理</a><br>
    <a href="Logout">登出</a>
  </body>
</html>
