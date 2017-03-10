// /**
//  * Created by Morn Wu on 2017/3/2.
//  */
// /**
//  * Created by Morn Wu on 2017/3/2.
//  */
// document.write('<script type="text/JavaScript" src="/js/CryptoJS-3.1.2/rollups/sha512.js"></script>');
// document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
// document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');
// document.write('<script type="text/jsp" src="/WEB-INF/content/jsp/memberInfoManagement.jsp"></script>');
//
//
// function changePasswordValidation(){
//     // var oldpassword = CryptoJS.enc.Utf8.parse($("[name='oldpassword']").val());
//     // var password = CryptoJS.enc.Utf8.parse($("[name='password']").val());z
//     // var password1 = CryptoJS.enc.Utf8.parse($("[name='repassword']").val());
//     if(password != "" && password1 != ""){
//         // var SHA512encrypt_old = CryptoJS.SHA512(oldpassword); //如果在前端直接执行 toUpperCase 不知道为什么就跪了
//         // var SHA512encrypt_re = CryptoJS.SHA512(password1); //如果在前端直接执行 toUpperCase 不知道为什么就跪了
//         // var SHA512encrypt = CryptoJS.SHA512(password); //如果在前端直接执行 toUpperCase 不知道为什么就跪了
//         $("[name = 'oldpassword']").val(SHA512encrypt_old);
//         $("[name = 'repassword']").val(SHA512encrypt_re);
//         $("[name = 'password']").val(SHA512encrypt);
//
//     }
//     postForm2Action("changePassword", "#changePassword", "密码修改成功！请重新登录！","login");
// }
//
// function changeInfoValidation(){
//     postForm2Action("modifyInfo", "#modifyInfo", "修改个人信息成功!");
//     refetch();
// }
//


/**
 * Created by Morn Wu on 2017/3/2.
 */
/**
 * Created by Morn Wu on 2017/3/2.
 */
document.write('<script type="text/JavaScript" src="/js/CryptoJS-3.1.2/rollups/sha512.js"></script>');
document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');
document.write('<script type="text/jsp" src="/WEB-INF/content/jsp/memberInfoManagement.jsp"></script>');


function changePasswordValidation(){
    postForm2Action("changePassword", "#changePassword", "密码修改成功！请重新登录！","loginRedirect");
}

function changeInfoValidation(){
    postForm2Action("modifyInfo", "#modifyInfo", "修改个人信息成功!");
    refetch();
}

function addMemberValidation(){

    postForm2Action("addMember", "#addMember", "添加部员成功!");

}