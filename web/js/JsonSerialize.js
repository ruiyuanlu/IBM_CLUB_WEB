/**
 * Created by lurui on 2017/3/4 0004.
 */
document.write("<script type='text/javascript' src='/js/jquery-3.1.1.js'></script>")
/**
 * 以 json 方式提交表单，并调用匿名回调函数
 * @param action
 * @param formId
 * @param result
 */
function postForm2Action(action, formId, result, url, clearedFieldsNames){
    $('#errorMessages').val("");
    $('.errorLabel').html('').removeClass('errorLabel');
    $.post(action, $(formId).serialize(), function(json){//action == LoginAction
        clearFieldsByName(clearedFieldsNames);//清空指定的域
        checkJsonAndAssign(json, result, url);//判定json的返回值是否包含错误信息，如一切正常，则跳转
    });
    //$.ajax{
    // url: LoginAction
    //}
}

/**
 * 清空表单中指定的域
 * @param fieldsName
 */
function clearFieldsByName(fieldsName) {
    if(isEmpty(fieldsName)) return;
    for (var i = 0; i < fieldsName.length; i++) {
        field = fieldsName[i];
        $("[name = field]").val('');
    }
}

/**
 * 从json读取数据，返回true 或false 表示正常或错误
 * @param json
 * @return {boolean} true 一切正常 false 出现错误
 */
function hasErrors(json){
    $("[name = 'token']").val(json.newToken);//将后端的新的token写入前端, json中的newToken属性定义在loginAction类中
    if(json.actionErrors && !isEmpty(json.actionErrors)){//判断有没有actionErrors
        $.each(json.actionErrors,function(key,value){
            $("#errorMessages").append(value);
        });
        $("[name='password']").val("");
        return true;
    }
    if (json.fieldErrors && !isEmpty(json.fieldErrors)) {//判断有没有fieldError
        $.each(json.fieldErrors, function (key, value) {//index就是field的name,value就是该filed对应的错误列表，这里取第一个
            $("#error_" + key).html(value[0]);
            $("#error_" + key).addClass("errorLabel");//添加错误信息到 DOM 节点中
        });
        return true;
    }
    return false;
}

/**
 * 判断一个元素是否为空
 * @param obj
 * @return {boolean}
 */
function isEmpty(obj) {
    for (var p in obj) return false;
    return true;
}
/**
 * 检查返回的 json 中有无错误信息
 * 如果有 则不跳转，否则 跳转到参数 url 指定的页面
 * @param json
 * @param result
 * @param url
 */
function checkJsonAndAssign(json, result, url) {
    if(hasErrors(json))return;
    // alert(result);
    if(url && !isEmpty(url)) window.location.assign(url);

}