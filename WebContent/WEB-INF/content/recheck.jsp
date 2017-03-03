<%@ page import="com.istc.bean.Person" %><%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/3/3
  Time: 上午 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>身份验证</title>
</head>
<body>
<h1>您正在进行敏感操作！</h1>
<h2>为了系统安全，请再输入一次密码：</h2>
学号：<%=((Person)session.getAttribute("personInfo")).getID()%>
<form action="recheck" method="post">
    密码：<input type="password" name="password"/><br>
    <input type="submit" name="submit"/>
</form>
注意：仅有一次机会！如果输入错误将会被强制登出！<br>
</body>
</html>
