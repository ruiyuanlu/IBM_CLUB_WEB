    // js 文件夹必须放在web根目录下，并用/js访问其中资源
    // 导入包特定的加密算法，要有特定的包
    document.write('<script type="text/JavaScript" src="/js/CryptoJS-3.1.2/rollups/sha512.js"></script>');
    document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
    document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');

    // function registerValidation(){
    //     $("#errorMessages").html("");
    //     $('.errorLabel').html('').removeClass('errorLabel');
    //     $.post("register",$("#register").serialize(), function(json) {
    //         checkJsonAndAssign(json,"注册成功！单击确定按钮返回首页。","mainpage");
    //     });
    // }
    function registerValidation(){
        //测试
        postForm2Action("register", "#register", "注册成功! 单击确定返回首页!", "mainpage", 'password','repassword');
    }

    function loginValidation(){
        var password=CryptoJS.enc.Utf8.parse($("[name='password']").val());
        if(password != ""){
            var SHA512encrypt = CryptoJS.SHA512(password);
            $("[name = 'password']").val(SHA512encrypt);
        }
        $("#errorMessages").html("");
        $('.errorLabel').html('').removeClass('errorLabel');//删除错误信息
        $.post("login",$("#login").serialize(), function(json) { // post url为login，去login 为 id 的get请求并序列化，调用json解析
            jsonSerialize(json,"登录成功！","success");//action
        });
    }

    // function checkJsonAndAssign(json, result, url){
    //     $("[name = 'password']").val("");//清空password和repassword的值
    //     $("[name = 'repassword']").val("");
    //     $("[name = 'token']").val(json.newToken);//将后端的新的token写入前端, json中的newToken属性定义在loginAction类中
    //
    //     if(json.actionErrors && json.actionErrors.length > 0){//判断有没有actionErrors
    //         $.each(json.actionErrors,function(index,data){
    //             $("#errorMessages").append(data);
    //         });
    //         $("[name='password']").val("");
    //         return;
    //     }
    //     if(json.fieldErrors && !isEmpty(json.fieldErrors)){//判断有没有fieldError
    //         $.each(json.fieldErrors,function(key, value){//index就是field的name,value就是该filed对应的错误列表，这里取第一个
    //             $("#error_"+ key).html(value[0]);
    //             $("#error_"+ key).addClass("errorLabel");//添加错误信息到 DOM 节点中
    //         });
    //         return;
    //     }
    //     alert(result);//既没有actionError有没有fieldError则登陆成功
    //     window.location.assign(url);//跳转请求
    // }

    function jsonSerialize(json, result, url) {
        clearFieldsByName('password','repassword');
        if(hasErrors(json))return;
        alert(result);
        window.location.assign(url);
    }
    //
    // function isEmpty(obj){//判断对象是否为空(处理Object obj = {}这种情况认为isEmpty=true)
    //     for(var p in obj){
    //         return false;
    //     }
    //     return true;
    // }