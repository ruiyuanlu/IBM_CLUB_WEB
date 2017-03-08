/**
 * Created by Morn Wu on 2017/3/8.
 */

document.write('<script type="text/JavaScript" src="js/jquery-3.1.1.js"></script>');
function deleteDept(id) {
    var confirmbox=confirm("该操作会影响该部门及所属的所有成员！确认删除？");
    if (confirmbox == true){
        $.post("deleteDept", {deptdeleted: id}, function(json){
            if (json.jsonresult.deleteresult == true){
                alert("删除成功！");
                window.location.reload();
            }
            else {
                alert("删除失败！");
            }
        });
    }
    return;
}

function addDeptValidation(){
    $("#errorMessages").html("");
    $('.errorLabel').html('').removeClass('errorLabel');
    $.post("addDept",$("#addDept").serialize(), function(json) {
        jsonSerialize(json,"部门添加成功！");
    });
    return;
}

function jsonSerialize(json,success){
    $("[name='password']").val("");
    $("[name='repassword']").val("");
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
    window.location.reload();
}

function isEmpty(obj){//判断对象是否为空(处理Object obj = {}这种情况认为isEmpty=true)
    for(var p in obj){
        return false;
    }
    return true;
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}