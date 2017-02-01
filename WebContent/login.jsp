<%@page import="club.istc.bean.Person"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<form id="login">
	  	  学号:<input type="text" name="id"></input><font color="red"><span id="error_id"></span></font><br/>
	   	密码:<input type="password" name="password"/><font color="red"><span id="error_password"></span></font><br/>
    </form>  
    <button onclick="getLoginValidation()">登录</button>
	<font color="red"><ul id="errorMessages"></ul></font>
  </body>
</html>
