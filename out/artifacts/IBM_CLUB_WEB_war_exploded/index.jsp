<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lurui
  Date: 2017/2/10 0010
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
import="java.util.*, java.lang.*" %>

<html>
  <head>
    <style type="text/css">
        .errorLabel{color:red;}
    </style>

    <title>登陆页面</title>

      <sj:head jquerytheme="cupertino" ajaxcache="true" compressed="false"/>
      <script type="text/javascript" src="js/validation-format.js"></script>
  </head>
  <body>
  <%--form中的action属性用于指定发送到struts中的请求, method指定发送请求的方法
      这里需要注意，如果为了后端简单可维护，这里的每个name属性都有其作用
      form表单的name属性对应person类
      用户名name属性"id"对应了loginAction类的"id"属性
      密码name属性"password"对应了LoginAction类的"password"属性
      在 LoginAction 中的login方法是"login"这个action的实现，由于 LoginAction 类
      实现了 SessionAware 接口, 因此会自动根据表单的 name 属性将 person 表单封装成一个
      由 "person" 这个form 的那么属性对应的一个键值对 "person"→{ "id":id, "password":password },
      并存储到 LoginAction 的 Map<String, Object> session 这个成员变量
      因此封装了获取资源的过程
      --%>
  <%--<form action="login" method="post" name="person">--%>
    <%--用户名: <input type="text" value="请输入用户名" name="id"/>${}--%>
    <%--密 码 : <input type="password" name="password"/>--%>
    <%--<br/>--%>
    <%--<input type="submit" value="登陆"/>--%>
  <%--</form>--%>

  <form id="login" action="login" method="post">
      学号:<input type="text" name="id"/><li color="red" border="0"><s:fielderror fieldName="id" /></li>
      密码:<input type="password" name="password"/><li color="red" border="0"><s:fielderror fieldName="password" /></li>
      <s:token/>
      <input type="submit" value="登陆"/>
  </form>
  </body>
</html>
