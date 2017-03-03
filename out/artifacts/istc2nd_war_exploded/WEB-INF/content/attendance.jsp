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
<button id="signsubmit" onclick="manualSignSubmit()">提交</button>
<font color="red"><s:actionerror /></font>
<span id="error_manualSign"></span>
<span id="errorMessages"></span>
</body>
<script>
    $.post("fetchRestPerson", function(json){
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
        if(json.jsonresult.deptmember.length==0){
            document.getElementById("signsubmit").disabled=true;
            alert("当前不可签到！");
            window.close();
        }
        var form=document.getElementById("manualSign");
        for(var i=0;i<json.jsonresult.deptmember.length;i++){
            var hint = document.createElement("SPAN");
            form.appendChild(hint);
            hint.appendChild(document.createTextNode(json.jsonresult.deptmember[i].ID+" "+json.jsonresult.deptmember[i].name+" "));
            var attendinput = document.createElement("INPUT");
            var attendhint = document.createElement("SPAN");
            form.appendChild(attendhint);
            attendhint.appendChild(document.createTextNode("已到"));
            attendinput.setAttribute("type","radio");
            attendinput.setAttribute("name",json.jsonresult.deptmember[i].ID);
            attendinput.setAttribute("value","attend");
            form.appendChild(attendinput);
            var absentinput = document.createElement("INPUT");
            var absenthint = document.createElement("SPAN");
            form.appendChild(absenthint);
            absenthint.appendChild(document.createTextNode("未到"));
            absentinput.setAttribute("type","radio");
            absentinput.setAttribute("name",json.jsonresult.deptmember[i].ID);
            absentinput.setAttribute("value","absent");
            absentinput.checked = true;
            form.appendChild(absentinput);
            var leaveinput = document.createElement("INPUT");
            var leavehint = document.createElement("SPAN");
            form.appendChild(leavehint);
            leavehint.appendChild(document.createTextNode("请假"));
            leaveinput.setAttribute("type","radio");
            leaveinput.setAttribute("name",json.jsonresult.deptmember[i].ID);
            leaveinput.setAttribute("value","leave");
            form.appendChild(leaveinput);
            var lateinput = document.createElement("INPUT");
            var latehint = document.createElement("SPAN");
            form.appendChild(latehint);
            latehint.appendChild(document.createTextNode("迟到"));
            lateinput.setAttribute("type","radio");
            lateinput.setAttribute("name",json.jsonresult.deptmember[i].ID);
            lateinput.setAttribute("value","late");
            form.appendChild(lateinput);
            var br=document.createElement("BR");
            form.appendChild(br);
        }
    });
</script>
</html>
