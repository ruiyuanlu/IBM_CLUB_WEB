/**
 * 用于将验证表单时获得的信息进行格式化并显示
 */

function getValidation(){
    $("#errorMessages").html("");  
    $('.errorLabel').html('').removeClass('errorLabel');  
    //alert("complete!");
    var json = $.parseJSON('{"actionErrors":[],"actionMessages":[],"fieldErrors":{"id":["请输入您的学号！"],"password":["请设置您的密码！"],"name":["请输入您的姓名！"],"phoneNumber":["请告诉我们您的手机号以方便联系您。"],"QQ":["请告诉我们您的QQ号以方便日后您与社团其他成员的互动。"]}}');  
    if(json.actionErrors && json.actionErrors.length>0){//判断有没有actionErrors  
        $.each(json.actionErrors,function(index,data){  
            $("#errorMessages").append("<li>"+data+"</li>");  
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
    alert("登陆成功");//既没有actionError有没有fieldError则登陆成功 
}

function isEmpty(obj){//判断对象是否为空(处理Object obj = {}这种情况认为isEmpty=true)  
    for(var p in obj){  
        return false;  
    }  
    return true;  
}  