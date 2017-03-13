/**
 * Created by Morn Wu on 2017/3/2.
 */
/**
 * Created by Morn Wu on 2017/3/2.
 */
document.write('<script type="text/JavaScript" src="/js/CryptoJS-3.1.2/rollups/sha512.js"></script>');
document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');
// document.write('<script type="text/jsp" src="/WEB-INF/content/jsp/memberInfoManagement.jsp"></script>');

function fetchAllMember(){
    postForm2Action("fetchMemberInfo","","获取面试列表成功！")
}

function changePasswordValidation(){
    postForm2Action("changePassword", "#changePassword", "密码yinggia修改成功！请重新登录！","loginRedirect");
}

function personUpgradeValidation(){
    postForm2Action("personUpgrade","#personUpgrade","成员设置成功！")
}

function changeInfoValidation(){
    postForm2Action("modifyInfo", "#modifyInfo", "修改个人信息成功!");
    refetch();
}

function addMemberValidation(){

    postForm2Action("addMember", "#addMember", "添加部员成功!");

}