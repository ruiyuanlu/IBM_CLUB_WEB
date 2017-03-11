<%--
  Created by IntelliJ IDEA.
  User: lurui
  Date: 2017/3/11 0011
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="fileUpload" method="post" enctype="multipart/form-data">
    <%--文件名: <input type="text"/>--%>
    文件: <input type="file" name="upload"/>
    <input type="submit" value="上传文件">
</form>
</body>
</html>
