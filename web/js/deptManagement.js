/**
 * Created by vicky on 2017/3/15.
 */
document.write('<script type="text/JavaScript" src="/js/CryptoJS-3.1.2/rollups/sha512.js"></script>');
document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');

function addDepartmentValidation(){
    postForm2Action("addDepartment", "#addDepartment", "部门添加成功！");
}


function deleteDepartmentValidation(){
    postForm2Action("deleteDepartment", "#deleteDepartment", "部门删除成功！");
}