<%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/3/10
  Time: 上午 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业评分</title>
    <script type="text/JavaScript" src="js/scoring.js"></script>
</head>
<body>
<form id="scoring">

</form>
<button id="scoringsubmit" onclick="scoreSubmit()">确认评分</button>
</body>
<script>
    refetch();
    function refetch() {
        var form = document.getElementById("scoring");
        form.innerHTML="";
        $.post("fetchHomeworklist",function(json) {
            if(isEmpty(json.jsonresult.curhomework.homeWorks)){
                document.getElementById("scoringsubmit").disabled=true;
                alert("还没有部员提交作业！");
            }
            for(var i=0;i<json.jsonresult.curhomework.homeWorks.length;i++){
                var hint = document.createElement("SPAN");
                form.appendChild(hint);
                hint.appendChild(document.createTextNode(json.jsonresult.curhomework.homeWorks[i].homeWorkSubmitter.ID+" "+json.jsonresult.curhomework.homeWorks[i].homeWorkSubmitter.name+"  "));
                var scoreinput = document.createElement("INPUT");
                var scorehint = document.createElement("SPAN");
                scorehint.appendChild(document.createTextNode("评分:"));
                form.appendChild(scorehint);
                scoreinput.setAttribute("type","number");
                scoreinput.setAttribute("name","scores['"+json.jsonresult.curhomework.homeWorks[i].homeWorkSubmitter.ID+"']");
                scoreinput.setAttribute("max","100");
                scoreinput.setAttribute("min","0");
                if (json.jsonresult.curhomework.homeWorks[i].score == -1){
                    scoreinput.setAttribute("value","0");
                }
                else{
                    scoreinput.setAttribute("value",json.jsonresult.curhomework.homeWorks[i].score);
                }
                form.appendChild(scoreinput);
                var br=document.createElement("BR");
                form.appendChild(br);
            }
        });
    }
</script>
</html>
