<%@page import="club.istc.bean.Person"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%
//以下代码测试能否获取在action中自定义的session，如果该段文字无乱码地正常显示则没有问题
String info="";
if(session.getAttribute("infofromAction2jsp")!=null){
	info=(String)session.getAttribute("infofromAction2jsp");
}
//以下的setAttribute是测试能否正常从页面设置session并发回后端
session.setAttribute("infofromjsp2Action", "这是一段测试从jsp到Servlet能否正常发送session的文字，若该文字在控制台中无乱码地正常显示则没有问题。");
%>

<html>
<head>  
<style type="text/css">  
    .errorLabel{color: red;}  
</style>
<!--底下这行代码是个神奇的玩意儿，明明用不上却不能删……-->
<sj:head jquerytheme="cupertino" ajaxcache="true" compressed="false"/>
<script type="text/javascript" src="js/validation-format.js"></script>  
</head> 
  
  <body>
  <%=info %>
	<form id="login">
	  	  学号:<input type="text" name="id"></input><font color="red"><span id="error_id"></span></font><br/>
	   	密码:<input type="password" name="password"/><font color="red"><span id="error_password"></span></font><br/>
	   	<s:token/>
    </form>  
    <button onclick="getLoginValidation()">登录</button>
	<font color="red"><ul id="errorMessages"></ul></font>
  </body>
</html>