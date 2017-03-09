<%--
  Created by IntelliJ idEA.
  User: Morn Wu
  Date: 2017/3/9
  Time: 下午 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业发布管理</title>
    <script type="text/JavaScript" src="js/homeworkmanagement.js"></script>
</head>
<body>
作业添加
<form id="addHomework">
    作业要求：<br><textarea rows="4" cols="50" name="homeworkRequirement" form="addHomework" maxlength="200"></textarea><font color="red"><span id="error_homeworkRequirement"></span></font><br/>
    开始时间：<input type="datetime-local" name="starttime"/><font color="red"><span id="error_starttime"></span></font><br/>
    结束时间：<input type="datetime-local" name="endtime"/><font color="red"><span id="error_endtime"></span></font><br/>
    <input type="hidden" name="dept">
</form>
<button onclick="addHomeworkValidation()">添加新的作业</button>
<font color="red"><ul id="error_addHomework"></ul></font>
<br>
<font color="red"><span id="error_fetchAllHomework"></span></font>
<form id="homeworkmanage" action="" onsubmit="return false">

</form>
<button id="deletesubmit" onclick="deleteHomework()">确定删除</button>
</body>
<script>
    refetch();
    function refetch() {
        var elem=document.getElementById("homeworkmanage");
        elem.innerHTML="";
        var dept1=document.createElement("INPUT");
        dept1.setAttribute("type","hidden");
        dept1.setAttribute("name","dept");
        elem.appendChild(dept1);
        var depts=document.getElementsByName("dept");
        depts[0].setAttribute("value",GetQueryString("dept"));
        depts[1].setAttribute("value",GetQueryString("dept"));
        $.post("fetchAllHomework", function(json){
            if(isEmpty(json.jsonresult.homeworklist)){
                document.getElementById("deletesubmit").disabled=true;
                alert("当前还没有布置过作业！");
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
            var form=document.getElementById("homeworkmanage");
            for(var i=0;i<json.jsonresult.homeworklist.length;i++){
                var hint = document.createElement("SPAN");
                form.appendChild(hint);
                hint.appendChild(document.createTextNode(json.jsonresult.homeworklist[i].id+" "+json.jsonresult.homeworklist[i].homeWorkRequirement+" "+json.jsonresult.homeworklist[i].startTime+" "+json.jsonresult.homeworklist[i].endTime));
                var deleteinput = document.createElement("INPUT");
                var deletehint = document.createElement("SPAN");
                form.appendChild(deleteinput);
                deleteinput.setAttribute("type","checkbox");
                deleteinput.setAttribute("name","deleted");
                deleteinput.setAttribute("value",json.jsonresult.homeworklist[i].id);
                form.appendChild(deletehint);
                deletehint.appendChild(document.createTextNode("删除 "));
                var reset = document.createElement("BUTTON");
                reset.appendChild(document.createTextNode("修改作业信息"));
                reset.setAttribute("onclick","modifyHomework("+GetQueryString("dept")+","+json.jsonresult.homeworklist[i].id+")");
                form.appendChild(reset);
                var br=document.createElement("BR");
                form.appendChild(br);
            }
        });
    }
</script>
</html>
