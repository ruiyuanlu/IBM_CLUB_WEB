/**
 * Created by Morn Wu on 2017/3/2.
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

function addPersonValidation(){
    $("#errorMessages").html("");
    $('.errorLabel').html('').removeClass('errorLabel');
    $.post("addPerson",$("#addPerson").serialize(), function(json) {
        jsonSerialize(json,"部员添加成功！");
    });
    return;
}

function deletePerson(){
    $("#errorMessages").html("");
    $('.errorLabel').html('').removeClass('errorLabel');
    var confirmbox=confirm("如果删除错误将会给部员带来极大的麻烦！确认您的选择无误？");
    if (confirmbox == true){
        $.post("deletePersonSubmit",$("#membermamage").serialize(), function(json) {
            jsonSerialize(json,"删除部员成功！");
        });
    }
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

function resetPassword(id) {
    var confirmbox=confirm("确认重置？");
    if (confirmbox == true){
        $.post("resetPasswordSubmit", {needreset: id}, function(json){
            if (json.jsonresult.resetresult == true){
                alert("密码重置成功！");
            }
            else {
                alert("密码重置失败！");
            }
        });
    }
    return;
}

function checkCapsLock (e){
    valueCapsLock  =  e.keyCode ? e.keyCode:e.which; // Caps Lock　是否打开
    valueShift  =  e.shiftKey ? e.shiftKey:((valueCapsLock  ==   16 ) ? true : false ); // shift键是否按住

    if (((valueCapsLock  >=   65   &&  valueCapsLock  <=   90 )  &&   ! valueShift) // Caps Lock 打开，并且 shift键没有按住
        || ((valueCapsLock  >=   97   &&  valueCapsLock  <=   122 )  &&  valueShift)) // Caps Lock 打开，并且按住 shift键
        document.getElementById('capStatus').style.visibility  =  'visible';
    else
        document.getElementById('capStatus').style.visibility  =  'hidden';
}