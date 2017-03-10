/**
 * Created by Morn Wu on 2017/3/2.
 */
/**
 * Created by Morn Wu on 2017/3/2.
 */
document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');
document.write('<script type="text/jsp" src="/WEB-INF/content/jsp/memberInfoManagement.jsp"></script>');


function changePasswordValidation(){
    var password = CryptoJS.enc.Utf8.parse($("[name='password']").val());
    if(password != ""){
        var SHA512encrypt = CryptoJS.SHA512(password); //如果在前端直接执行 toUpperCase 不知道为什么就跪了
        $("[name = 'password']").val(SHA512encrypt);
    }
    postForm2Action("changePassword", "#changePassword", "密码修改成功！请重新登录！","login");
}

function changeInfoValidation(){
    postForm2Action("modifyInfo", "#modifyInfo", "修改个人信息成功!");
    refetch();
}