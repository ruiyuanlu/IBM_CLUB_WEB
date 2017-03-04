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
    密码：<input type="password" name="password" onkeypress="checkCapsLock(event)"/><br>
    <input type="submit" name="submit"/>
</form>
<div id ="capStatus" style="visibility: hidden"><font color="red">已打开大写锁定</font></div>
注意：仅有一次机会！如果输入错误将会被强制登出！<br>
</body>
<script>
    function checkCapsLock (e){
        valueCapsLock  =  e.keyCode ? e.keyCode:e.which; // Caps Lock　是否打开
        valueShift  =  e.shiftKey ? e.shiftKey:((valueCapsLock  ==   16 ) ? true : false ); // shift键是否按住

        if (((valueCapsLock  >=   65   &&  valueCapsLock  <=   90 )  &&   ! valueShift) // Caps Lock 打开，并且 shift键没有按住
            || ((valueCapsLock  >=   97   &&  valueCapsLock  <=   122 )  &&  valueShift)) // Caps Lock 打开，并且按住 shift键
            document.getElementById('capStatus').style.visibility  =  'visible';
        else
            document.getElementById('capStatus').style.visibility  =  'hidden';
    }
</script>
</html>
