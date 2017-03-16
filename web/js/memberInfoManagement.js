/**
 * Created by Morn Wu on 2017/3/2.
 */
/**
 * Created by Morn Wu on 2017/3/2.
 */
document.write('<script type="text/JavaScript" src="/js/CryptoJS-3.1.2/rollups/sha512.js"></script>');
document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');

function changePasswordValidation(){
    postForm2Action("changePassword", "#changePassword", "密码修改成功！请重新登录！","loginRedirect");
}
function personUpgradeValidation(){
    postForm2Action("personUpgrade","#personUpgrade","成员设置成功！")
}
function memberUpgradeValidation(){
    postForm2Action("memberUpgrade","#memberUpgrade","部长设置成功！")
}

function changeInfoValidation(){
    postForm2Action("modifyInfo", "#modifyInfo", "修改个人信息成功!");
    refetch();
}
function getRestIntervieweesValidation(){
    postForm2Action("getRestInterviewees","","请查看network响应!！")
}

function deleteMemberSubmitValidation(){

    postForm2Action("deleteMemberSubmit", "#deleteMemberSubmit", "删除部员完成!");

}
function chooseDeptValidation(){
    postForm2Action("chooseDept","#chooseDept","部门选择完成！")
}



function fetchAllPersonValidation(){

    postForm2Action("fetchAllPerson", "#fetchAllPerson", "请查看network响应!");

}
function addMemberValidation(){

    postForm2Action("addMember", "#addMember", "添加部员成功!");

}