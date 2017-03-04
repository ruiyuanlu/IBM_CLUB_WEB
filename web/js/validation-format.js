    // js 文件夹必须放在web根目录下，并用/js访问其中资源
    // 导入包特定的加密算法，要有特定的包
    document.write('<script type="text/JavaScript" src="/js/CryptoJS-3.1.2/rollups/sha512.js"></script>');
    document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
    document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');

    function registerValidation(){
        postForm2Action("register", "#register", "注册成功! 单击确定返回首页!", "mainpage", ["password", "repassword"]);//用[]代表数组，否则会被拆开成一个个字母
    }

    function loginValidation(){
        alert("执行了")
        var password = CryptoJS.enc.Utf8.parse($("[name='password']").val());
        if(password != ""){
            var SHA512encrypt = CryptoJS.SHA512(password); //如果在前端直接执行 toUpperCase 不知道为什么就跪了
            $("[name = 'password']").val(SHA512encrypt);
        }
        postForm2Action("login", "#login", "登录成功!", "success");
    }