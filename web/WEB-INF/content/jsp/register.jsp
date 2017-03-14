<%@page import="com.istc.Entities.Entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>面试申请</title>
    <style type="text/css">
        .errorLabel{color: red;}
    </style>
    <script type="text/javascript" src="/js/validation-format.js"></script>
</head>

<body>
请在该页面提交您的信息。<br/>
请保证您的信息，尤其是手机号的填写正确。信息一旦提交后不可手动修改！<br/>
到时我们会通过手机号为您通知具体的面试信息。<br/>
面试通过后您可以用该页面填写的信息登录。<br/>
<form id="register">
    学号:<input type="text" name="id"/><font color="red"><span id="error_id"></span></font><br/>
    密码:<input type="password" name="password"/><font color="red"><span id="error_password"></span></font><br/>
    确认密码：<input type="password" name="repassword" /><font color="red"><span id="error_repassword"></span></font><br/>
    姓名:<input type="text" name="name"/><font color="red"><span id="error_name"></span></font><br/>
    性别：<br/>
    <input type="radio" name="gender" value="true" checked="true"/> 男<br/>
    <input type="radio" name="gender" value="false"/> 女<br/>
    生日：<input type="date" name="birthday" id="birthday"/><font color="red"><span id="error_birthday"></span></font><br/>
    手机号：<input type="text" name="phoneNumber"/><font color="red"><span id="error_phoneNumber"></span></font><br/>
    QQ：<input type="text" name="QQ"/><font color="red"><span id="error_QQ"></span></font><br/>
    <input type="hidden" name="token" value="<%=session.getAttribute("token")%>">
</form>
<button onclick="registerValidation()">注册</button>
<font color="red"><ul id="errorMessages"></ul></font>
</body>
<script>
    var curdate = new Date();
    var maxyear = curdate.getFullYear() - 14;
    var maxbirthday = ""+maxyear+"-12-31";
    var datepicker = document.getElementById("birthday");// date 类型的 DOM 节点返回的日期就是 yyyy-MM-dd 的时间字符串
    datepicker.setAttribute("max",maxbirthday);
    datepicker.setAttribute("min","1970-01-01");
</script>
</html>
