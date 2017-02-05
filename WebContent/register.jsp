<%@page import="club.istc.bean.Person"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
Person curPerson=new Person();
if(session.getAttribute("personInfo")!=null)
	curPerson=(Person)session.getAttribute("personInfo");
try{
	curPerson.getAge();
}
catch(NullPointerException e){
	curPerson.setAge(0);
}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
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
  请在该页面提交您的信息。<br/>
  请保证您的信息，尤其是手机号的填写正确。信息一旦提交后不可手动修改！<br/>
  到时我们会通过手机号为您通知具体的面试信息。<br/>
  面试通过后您可以用该页面填写的信息登录。<br/>
    <form action="Register" method="post">
	  	  学号:<input type="text" name="id" <%if(curPerson.getID()!=null)%>value=<%=curPerson.getID()%>></input><font color="red"><s:fielderror fieldName="id"/></font><br/>
	   	密码:<input type="password" name="password"/><font color="red"><s:fielderror fieldName="password"/></font><br/>
	   	确认密码：<input type="password" name="repassword" /><font color="red"><s:fielderror fieldName="repassword"/></font><br/>
	  	  姓名:<input type="text" name="name" <%if(curPerson.getName()!=null)%>value=<%=curPerson.getName()%> ></input><font color="red"><s:fielderror fieldName="name"/></font><br/>
	   	性别：<br/>
	    <input type="radio" name="gender" value="1" checked> 男<br>
  		<input type="radio" name="gender" value="0"> 女<br>
  		年龄：<input type="number" name="age" min="14" max="100"  <%if(curPerson.getAge()!=0)%>value=<%=curPerson.getAge()%>></input><font color="red"><s:fielderror fieldName="age"/></font><br/>
  		手机号：<input type="text" name="phoneNumber" <%if(curPerson.getPhoneNumber()!=null)%>value=<%=curPerson.getPhoneNumber()%>></input><font color="red"><s:fielderror fieldName="phoneNumber"/></font><br/>
  		QQ：<input type="text" name="QQ" <%if(curPerson.getQQ()!=null)%>value=<%=curPerson.getQQ()%>></input><font color="red"><s:fielderror fieldName="QQ" /></font><br/>
  		<s:token/>
	    <input type="submit" value="提交"/>
    </form>
	<font color="red"><s:fielderror fieldName="registerfault"/></font>
  </body>
</html>
