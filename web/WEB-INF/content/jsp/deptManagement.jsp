<%--
  Created by IntelliJ IDEA.
  User: vicky
  Date: 2017/3/15
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门管理测试</title>
    <script type="text/JavaScript" src="/js/deptManagement.js"></script>
</head>
<body>

<br>
<h1>部门添加</h1>
<span id="addDeptId"></span>
<form id="addDepartment" onsubmit="return false">
    部门ID：<input type="number" name="dept"/><font color="red"><span id="error_dept"></span></font><br/>
    部门名称：<input type="text" name="deptName"/><font color="red"><span id="error_deptName"></span></font><br/>
    介绍：<input type="text" name="introduction"/><font color="red"><span id="error_introduction"></span></font><br/>
</form>
<button onclick="addDepartmentValidation()">部门添加</button>

<font color="red"><span id="error_deletetedDepartment"></span></font>
<br>
<h1>部门添加</h1>
<span id="deletedDepartmentID"></span>
<form id="deleteDepartment" onsubmit="return false">
    部门ID：<input type="number" name="deleteDept"/><font color="red"><span id="error_deleteDept"></span></font><br/>
</form>
<button onclick="deleteDepartmentValidation()">部门删除</button>
<font color="red"><span id="error_deleteDepartment"></span></font>

</body>

</html>