// js 文件夹必须放在web根目录下，并用/js访问其中资源
// 导入包特定的加密算法，要有特定的包
document.write('<script type="text/JavaScript" src="/js/CryptoJS-3.1.2/rollups/sha512.js"></script>');
document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');

function getRegisterValidation(){
    //alert("complete!");
    //var key=CryptoJS.enc.Utf8.parse($("[name='token']").val());
   // var password=CryptoJS.enc.Utf8.parse($("[name='password']").val());
    //var repassword=CryptoJS.enc.Utf8.parse($("[name='repassword']").val());
    //var pwEncrypt=CryptoJS.AES.encrypt(password, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
    //var rpwEncrypt=CryptoJS.AES.encrypt(repassword, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
    //$("[name='password']").val(pwEncrypt);
    //$("[name='repassword']").val(rpwEncrypt);
    $("#errorMessages").html("");
    $('.errorLabel').html('').removeClass('errorLabel');  
    $.post("Register",$("#register").serialize(), function(json) {  
    	jsonSerialize(json,"注册成功！单击确定按钮返回首页。","mainpage");
    });
}

function getLoginValidation(){
    //var key=CryptoJS.enc.Utf8.parse($("[name='token']").val());
    //var key=CryptoJS.enc.Utf8.parse("456");
    //var AESencrypt=CryptoJS.AES.encrypt(password, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
    //alert(password+"\n"+key);
    var password=CryptoJS.enc.Utf8.parse($("[name='password']").val());
    if(password!=""){
        var SHA1encrypt=CryptoJS.SHA512(password);
        $("[name='password']").val(SHA1encrypt);
    }
    $("#errorMessages").html("");
    $('.errorLabel').html('').removeClass('errorLabel');  
    $.post("login",$("#login").serialize(), function(json) { // post url为login，去login 为 id 的get请求并序列化，调用json解析
        // jsonSerialize(json,"登录成功！","mainpage");//action?
        jsonSerialize(json,"登录成功！","success");//action?
    });
}

function jsonSerialize(json,success,url){
    $("[name='password']").val("");
    $("[name='repassword']").val("");
    $.each(json.actionMessages,function(index,data){
        $("[name='token']").val(data);
    });
    if(json.actionErrors && json.actionErrors.length>0){//判断有没有actionErrors  
        $.each(json.actionErrors,function(index,data){  
            $("#errorMessages").append(data);
        });
        $("[name='password']").val("");
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