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
<html>
<head>
    <style type="text/css">
        .errorLabel{color: red;}
    </style>
    <title>手动签到</title>
    <script type="text/JavaScript" src="js/manualSignSubmit.js"></script>
</head>
<body>
<form id="manualSign">

</form>
<button onclick="manualSignSubmit()">提交</button>
<font color="red"><s:actionerror /></font>
<span id="error_manualSign"></span>
<span id="errorMessages"></span>
</body>
<script>
    $.post("fetchRestPerson", function(json){
        $.each(json.deptmember,function(index,value){//index就是field的name,value就是该filed对应的错误列表，这里取第一个

        });
    });
</script>
</html>
