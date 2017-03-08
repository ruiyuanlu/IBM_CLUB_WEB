<%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/3/8
  Time: 下午 5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门管理</title>
    <script type="text/JavaScript" src="js/deptmanagement.js"></script>
</head>
<body>
<form id="addDept">
    部门名称：<input type="text" name="deptName"></input><font color="red"><span id="error_deptName"></span></font><br/>
    部门ID：<input type="number" name="dept" max="100" min="1" ></input><font color="red"><span id="error_dept"></span></font><br/>
    部门创建时间：<input type="date" name="establishTime" id="establishTime"></input><font color="red"><span id="error_establishTime"></span></font><br/>
    部门简介：<br><textarea rows="4" cols="50" name="introduction" form="addDept" maxlength="100"></textarea><font color="red"><span id="error_introduction"></span></font><br/>
</form>
<button onclick="addDeptValidation()">添加部门</button>
<br>
<br>
<font color="red"><span id="error_fetchDept"></span></font>
<form id="deptmanage" action="" onsubmit="return false">

</form>
<button id="deletesubmit" onclick="deletePerson()">确定删除</button>
<font color="red"><span id="error_deleteDept"></span></font><br>
</body>
<script>
    var curdate = new Date();
    var datepicker = document.getElementById("establishTime");
    var curtimeString = formatDate(curdate);
    datepicker.setAttribute("max",curtimeString);
    datepicker.setAttribute("min","1970-01-01");
    refetch();
    function refetch() {
        var elem = document.getElementById("deptmanage");
        elem.innerHTML = "";
        $.post("fetchAllDept", function(json) {
            if(isEmpty(json.jsonresult.deptlist)){
                document.getElementById("deletesubmit").disabled=true;
                alert("当前社团没有部门！");
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
            var form=document.getElementById("deptmanage");
            for(var i=0;i<json.jsonresult.deptlist.length;i++){
                var hint = document.createElement("SPAN");
                form.appendChild(hint);
                hint.appendChild(document.createTextNode(json.jsonresult.deptlist[i].deptID+" "+json.jsonresult.deptlist[i].deptName+" "+json.jsonresult.deptlist[i].introduction+" "+json.jsonresult.deptlist[i].establishTime));
                var reset = document.createElement("BUTTON");
                reset.appendChild(document.createTextNode("删除"));
                reset.setAttribute("onclick","deleteDept("+json.jsonresult.deptlist[i].deptID+")");
                form.appendChild(reset);
                var br=document.createElement("BR");
                form.appendChild(br);
            }
        });
    }
</script>
</html>
