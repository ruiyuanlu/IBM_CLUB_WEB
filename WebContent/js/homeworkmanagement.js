/**
 * Created by Morn Wu on 2017/3/9.
 */
document.write('<script type="text/JavaScript" src="js/jquery-3.1.1.js"></script>');

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return  unescape(r[2]);
    return null;
}

function addHomeworkValidation() {
    $("#errorMessages").html("");
    $('.errorLabel').html('').removeClass('errorLabel');
    $.post("addHomework",$("#addHomework").serialize(), function(json) {
        jsonSerialize(json,"作业添加成功！");
    });
    return;
}

function deleteHomework() {
    var confirmbox=confirm("如果不是必须，我们并不建议此操作。仍然删除？");
    if (confirmbox == true){
        $.post("deleteHomework", $("#homeworkmanage").serialize(), function(json){
            if (json.jsonresult.deleteresult == true){
                alert("删除成功！");
                refetch();
            }
            else {
                alert("删除失败！");
            }
        });
    }
    return;
}
function jsonSerialize(json,success){
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
    alert(success);
    refetch();
}
function isEmpty(obj){//判断对象是否为空(处理Object obj = {}这种情况认为isEmpty=true)
    for(var p in obj){
        return false;
    }
    return true;
}

