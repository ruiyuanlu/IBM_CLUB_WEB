<%@page import="com.istc.bean.Person"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>  
<style type="text/css">  
    .errorLabel{color: red;}  
</style>
<!--底下这行代码是个神奇的玩意儿，明明用不上却不能删……-->
<script type="text/javascript" src="js/validation-format.js"></script>  
</head> 
  
  <body>
  请在该页面提交您的信息。<br/>
  请保证您的信息，尤其是手机号的填写正确。信息一旦提交后不可手动修改！<br/>
  到时我们会通过手机号为您通知具体的面试信息。<br/>
  面试通过后您可以用该页面填写的信息登录。<br/>
	<form id="register">
	  	  学号:<input type="text" name="id"></input><font color="red"><span id="error_id"></span></font><br/>
	   	密码:<input type="password" name="password"/><font color="red"><span id="error_password"></span></font><br/>
	   	确认密码：<input type="password" name="repassword" /><font color="red"><span id="error_repassword"></span></font><br/>
	  	  姓名:<input type="text" name="name" ></input><font color="red"><span id="error_name"></span></font><br/>
	   	性别：<br/>
	    <input type="radio" name="gender" value="1" checked> 男<br>
  		<input type="radio" name="gender" value="0"> 女<br>
  		年龄：<input type="number" name="age" min="14" max="100" value="18"></input><font color="red"><span id="error_age"></span></font><br/>
  		手机号：<input type="text" name="phoneNumber" ></input><font color="red"><span id="error_phoneNumber"></span></font><br/>
  		QQ：<input type="text" name="QQ"></input><font color="red"><span id="error_QQ"></span></font><br/>
		<input type="hidden" name="token" value="<%=session.getAttribute("token")%>">
    </form>  
    <button onclick="getRegisterValidation()">注册</button>
	<font color="red"><ul id="errorMessages"></ul></font>
  </body>
</html>
