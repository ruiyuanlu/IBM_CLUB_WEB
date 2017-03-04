<%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/3/2
  Time: 上午 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员信息管理测试</title>
    <style type="text/css">
        .errorLabel{color: red;}
    </style>
    <script type="text/JavaScript" src="js/membermaintenance.js"></script>
</head>
<body>
人员添加：
<form id="addPerson">
    学号:<input type="text" name="id"></input><font color="red"><span id="error_id"></span></font><br/>
    密码:<input type="password" name="password" onkeydown="checkCapsLock()"/><font color="red"><span id="error_password"></span></font><br/>
    确认密码：<input type="password" name="repassword" onkeydown="checkCapsLock()"/><font color="red"><span id="error_repassword"></span></font><br/>
    姓名:<input type="text" name="name" ></input><font color="red"><span id="error_name"></span></font><br/>
    <input type="hidden" name="dept">
</form>
<button onclick="addPersonValidation()">注册</button>
<div id ="capStatus" style="visibility: hidden"><font color="red">已打开大写锁定</font></div>
<font color="red"><ul id="errorMessages"></ul></font>
</body>
<script>
    $("[name='dept']").val(GetQueryString("dept"));
</script>
</html>
