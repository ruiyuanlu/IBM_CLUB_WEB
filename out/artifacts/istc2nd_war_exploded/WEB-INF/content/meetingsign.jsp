<%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/2/27
  Time: 下午 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean result=(boolean)session.getAttribute("sign");
%>
<html>
<head>
    <title>签到</title>
</head>
<body>
    <%
        if (result){
    %>
    签到成功，希望继续保持良好的时间观念！
    <%
        }
        else{
    %>
    签到失败！下次记得来早点哦！
    <%
        }
    %>
</body>
</html>
