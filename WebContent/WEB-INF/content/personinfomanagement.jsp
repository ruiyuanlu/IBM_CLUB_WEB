<%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/3/2
  Time: 下午 7:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户个人信息管理测试</title>
    <script type="text/JavaScript" src="js/personInfoManage.js"></script>
</head>
<body>
<h1>密码修改</h1>
<form id="changePassword">
    旧密码:<input type="password" name="oldpassword" onkeydown="checkCapsLock()" /><font color="red"><span id="error_oldpassword"></span></font><br/>
    密码:<input type="password" name="password" onkeydown="checkCapsLock()"/><font color="red"><span id="error_password"></span></font><br/>
    确认密码：<input type="password" name="repassword" onkeydown="checkCapsLock()" /><font color="red"><span id="error_repassword"></span></font><br/>
</form>
<button onclick="changePasswordValidation()">修改密码</button>
<font color="red"><span id="error_changePassword"></span></font>
<div id ="capStatus" style="visibility: hidden"><font color="red">已打开大写锁定</font></div>
<br>
<h1>个人信息修改</h1>
<span id="curpersonid"></span>
<form id="modifyInfo" onsubmit="return false">
    姓名:<input type="text" name="name" ></input><font color="red"><span id="error_name"></span></font><br/>
    性别：<br/>
    <input type="radio" name="gender" value="true"> 男<br>
    <input type="radio" name="gender" value="false"> 女<br>
    生日：<input type="date" name="birthday" id="birthday"></input><font color="red"><span id="error_birthday"></span></font><br/>
    手机号：<input type="text" name="phoneNumber" ></input><font color="red"><span id="error_phoneNumber"></span></font><br/>
    QQ：<input type="text" name="QQ"></input><font color="red"><span id="error_QQ"></span></font><br/>
</form>
<button onclick="changeInfoValidation()">修改个人信息</button>
<font color="red"><span id="error_fetchPersonInfo"></span></font>
</body>
<script>
    var curdate = new Date();
    var maxyear = curdate.getFullYear()-15;
    var maxbirthday = ""+maxyear+"-12-31";
    var datepicker = document.getElementById("birthday");
    datepicker.setAttribute("max",maxbirthday);
    datepicker.setAttribute("min","1970-01-01");
    $.post("fetchPersonInfo",function(json) {
        var id=document.getElementById("curpersonid");
        id.appendChild(document.createTextNode("学号："+json.jsonresult.curPerson.ID));
        var name=document.getElementsByName("name")[0];
        name.setAttribute("value",json.jsonresult.curPerson.name);
        var male=document.getElementsByName("gender")[0];
        var female=document.getElementsByName("gender")[1];
        if (json.jsonresult.curPerson.gender == true){
            male.checked=true;
        }
        else {
            female.checked=true;
        }
        var phonenumber = document.getElementsByName("phoneNumber")[0];
        phonenumber.setAttribute("value",json.jsonresult.curPerson.phoneNumber);
        var QQ=document.getElementsByName("QQ")[0];
        QQ.setAttribute("value",json.jsonresult.curPerson.QQ);
    });
</script>
</html>
