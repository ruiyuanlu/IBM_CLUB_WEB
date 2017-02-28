<%@ page import="java.util.List" %>
<%@ page import="com.istc.bean.Person" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/2/28
  Time: 上午 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    List<Person> signlist=new ArrayList<Person>();
    if(session.getAttribute("signlist")!=null)
        signlist=(ArrayList<Person>)session.getAttribute("signlist");
%>
<html>
<head>
    <style type="text/css">
        .errorLabel{color: red;}
    </style>
    <title>手动签到</title>
    <script type="text/JavaScript" src="js/manualSignSubmit.js"></script>
</head>
<body>
<%
    if(signlist.size()!=0){
%>
<form id="manualSign">
    <%for (int i=0;i<signlist.size();i++){%>
    <%=signlist.get(i).getName()%>(<%=signlist.get(i).getID()%>)
    <input type="radio" name="<%=signlist.get(i).getID()%>" value="attend" >已到
    <input type="radio" name="<%=signlist.get(i).getID()%>" value="absent" checked>未到
    <input type="radio" name="<%=signlist.get(i).getID()%>" value="leave" >请假
    <input type="radio" name="<%=signlist.get(i).getID()%>" value="late" >请假
    <br/>
    <%
    }
    %>
</form>
<button onclick="manualSignSubmit()">提交</button>
<%
    }
%>
<font color="red"><s:actionerror /></font>
<span id="error_manualSign"></span>
<span id="errorMessages"></span>
</body>
</html>
