/**
 * Created by Morn Wu on 2017/2/28.
 */
document.write('<script type="text/JavaScript" src="js/jquery-3.1.1.js"></script>');

function manualSignSubmit(){
    var confirmbox=confirm("是否确认提交？提交后不可修改！");
    if (confirmbox == true){
        $.post("manualSignSubmit",$("#manualSign").serialize(), function(json) {
            jsonSerialize(json);
        });
    }
}
function jsonSerialize(json){
    if(json.fieldErrors && !isEmpty(json.fieldErrors)){//判断有没有fieldError
        $.each(json.fieldErrors,function(index,value){//index就是field的name,value就是该filed对应的错误列表，这里取第一个
            $("#error_"+index).html(value[0]);
            $("#error_"+index).addClass("errorLabel");
        });
        return;
    }
    if(json.actionErrors && json.actionErrors.length>0){//判断有没有actionErrors
        $.each(json.actionErrors,function(index,data){
            alert(data);
            $("[name='manualsign']").val("");
            window.close();
            return;
        });
    }
}

function isEmpty(obj){//判断对象是否为空(处理Object obj = {}这种情况认为isEmpty=true)
    for(var p in obj){
        return false;
    }
    return true;
}