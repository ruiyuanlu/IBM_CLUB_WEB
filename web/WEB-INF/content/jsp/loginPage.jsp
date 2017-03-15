<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lurui
  Date: 2017/3/1 0001
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
    <script type="text/javascript" src="/js/validation-format.js"></script>
</head>
<body>

<form id="login"  onsubmit="return false;">
    学号:<input type="text" name="id"/><font color="red"><span id="error_id"></span></font><br/>
    密码:<input type="password" name="password"/><font color="red"><span id="error_password"></span></font><br/>
    <input type="hidden" name="token" value="<%=session.getAttribute("token")%>">
    <input type="checkbox" name="remember" value="true">下次自动登录
</form>
<button onclick="loginValidation()">登录</button>
<font color="red"><span id="errorMessages"></span></font>

</body>
</html>
