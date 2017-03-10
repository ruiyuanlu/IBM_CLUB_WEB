/**
 * Created by Morn Wu on 2017/3/2.
 */
document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="/js/JsonSerialize.js"></script>');


function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return  unescape(r[2]);
    return null;
}

function addPersonValidation(){
    postForm2Action("addPerson", "#addPerson", "部员添加成功!");
}

function deletePerson(){
    var confirmbox=confirm("如果删除错误将会给部员带来极大的麻烦！确认您的选择无误？");
    if (confirmbox == true) postForm2Action("deletePersonSubmit", "#membermamage", "删除部员成功!");
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