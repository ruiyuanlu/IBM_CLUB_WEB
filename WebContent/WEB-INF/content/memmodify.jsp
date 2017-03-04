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
    密码:<input type="password" name="password" onkeypress="checkCapsLock(event)"/><font color="red"><span id="error_password"></span></font><br/>
    确认密码：<input type="password" name="repassword" onkeypress="checkCapsLock(event)"/><font color="red"><span id="error_repassword"></span></font><br/>
    姓名:<input type="text" name="name" ></input><font color="red"><span id="error_name"></span></font><br/>
    <input type="hidden" name="dept">
</form>
<button onclick="addPersonValidation()">添加成员</button>
<div id ="capStatus" style="visibility: hidden"><font color="red">已打开大写锁定</font></div>
<font color="red"><ul id="error_addPerson"></ul></font>
<br>
<font color="red"><span id="error_fetchPerson"></span></font>
<form id="membermamage" action="" onsubmit="return false">

</form>
<font color="red"><span id="error_deletePerson"></span></font><br>
<button id="deletesubmit" onclick="deletePerson()">确定删除</button>
</body>
<script>
    $.post("fetchAllPerson", function(json){
        if(isEmpty(json.jsonresult.deptmember)){
            document.getElementById("deletesubmit").disabled=true;
            alert("当前部门没有成员！");
        }
        if(json.actionErrors && json.actionErrors.length>0){//判断有没有actionErrors
            $.each(json.actionErrors,function(index,data){
                $("#errorMessages").append(data);
            });
            return;
        }
        if(json.fieldErrors && !isEmpty(json.fieldErrors)){//判断有没有fieldError
            $.each(json.fieldErrors,function(index,value){//index就是field的name,value就是该filed对应的错误列表，这里取第一个
                $("#error_"+index).html(value[0]);
                $("#error_"+index).addClass("errorLabel");
            });
            return;
        }
        var form=document.getElementById("membermamage");
        for(var i=0;i<json.jsonresult.deptmember.length;i++){
            var hint = document.createElement("SPAN");
            form.appendChild(hint);
            hint.appendChild(document.createTextNode(json.jsonresult.deptmember[i].ID+" "+json.jsonresult.deptmember[i].name+" "));
            var deleteinput = document.createElement("INPUT");
            var deletehint = document.createElement("SPAN");
            form.appendChild(deleteinput);
            deleteinput.setAttribute("type","checkbox");
            deleteinput.setAttribute("name","deleted");
            deleteinput.setAttribute("value",json.jsonresult.deptmember[i].ID);
            form.appendChild(deletehint);
            deletehint.appendChild(document.createTextNode("删除 "));
            var reset = document.createElement("BUTTON");
            reset.appendChild(document.createTextNode("重置密码"));
            reset.setAttribute("onclick","resetPassword("+json.jsonresult.deptmember[i].ID+")");
            form.appendChild(reset);
            var br=document.createElement("BR");
            form.appendChild(br);
        }
    });
</script>
</html>
