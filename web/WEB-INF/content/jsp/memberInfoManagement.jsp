<%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/3/2
  Time: 下午 7:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户个人信息管理测试</title>
    <script type="text/JavaScript" src="/js/personInfoManage.js"></script>
</head>
<body>
<h1>密码修改</h1>
<form id="changePassword">
    旧密码:<input type="password" name="oldpassword"/><font color="red"><span id="error_oldpassword"></span></font><br/>
    密码:<input type="password" name="password"/><font color="red"><span id="error_password"></span></font><br/>
    确认密码：<input type="password" name="repassword"/><font color="red"><span id="error_repassword"></span></font><br/>
</form>
<button onclick="changePasswordValidation()">修改密码</button>
</body>
</html>
