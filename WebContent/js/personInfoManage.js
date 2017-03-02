/**
 * Created by Morn Wu on 2017/3/2.
 */
/**
 * Created by Morn Wu on 2017/3/2.
 */
document.write('<script type="text/JavaScript" src="js/jquery-3.1.1.js"></script>');


function changePasswordValidation(){
    $("#errorMessages").html("");
    $('.errorLabel').html('').removeClass('errorLabel');
    $.post("changePassword",$("#changePassword").serialize(), function(json) {
        jsonSerialize(json,"密码修改成功！请重新登录！","login");
    });
}

function jsonSerialize(json,success,url){
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
    alert(success);//既没有actionError有没有fieldError则登陆成功
    window.location.assign(url);
}

function isEmpty(obj){//判断对象是否为空(处理Object obj = {}这种情况认为isEmpty=true)
    for(var p in obj){
        return false;
    }
    return true;
}