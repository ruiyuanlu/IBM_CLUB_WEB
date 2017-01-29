<%@page import="club.istc.bean.Person"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>  
<style type="text/css">  
    .errorLabel{color: red;}  
</style>  
<sj:head jquerytheme="cupertino" ajaxcache="false" compressed="false"/>  
</head> 
  
  <body>
	<s:form action="Register.do" theme="xhtml" target="_blank">
	  	  学号:<input type="text" name="id"></input><font color="red"><span id="error_id"></span></font><br/>
	   	密码:<input type="password" name="password"/><font color="red"><span id="error_password"></span></font><br/>
	   	确认密码：<input type="password" name="repassword" /><font color="red"><span id="error_repassword"></span></font><br/>
	  	  姓名:<input type="text" name="name" ></input><font color="red"><span id="error_name"></span></font><br/>
	   	性别：<br/>
	    <input type="radio" name="gender" value="1" checked> 男<br>
  		<input type="radio" name="gender" value="0"> 女<br>
  		年龄：<input type="number" name="age" min="14" max="100" value="18"></input><font color="red"><span id="error_age"></span></font><br/>
  		手机号：<input type="text" name="phoneNumber" ></input><font color="red"><span id="error_phoneNumber"></span></font><br/>
  		QQ：<input type="text" name="QQ"></input><font color="red"><span id="error_QQ"></span></font><br/> 
        <sj:submit   
            onCompleteTopics="complete"   
            targets="result"   
            onBeforeTopics="clearError"  
            value="注册"/>  
    </s:form>  
    
	<font color="red"><s:fielderror fieldName="registerfault"/></font>
  </body>
 <script type="text/javascript">  
    $.subscribe('clearError', function(event,data) {  
        $("#errorMessages").html("");  
        $('.errorLabel').html('').removeClass('errorLabel');  
    });  
    $.subscribe('complete', function(event,data) {  
        $("#errorMessages").html("");//先将上次认证的错误消息清除掉  
        $('.errorLabel').html('').removeClass('errorLabel');  
          
        var json = $.parseJSON(event.originalEvent.request.responseText);  
        if(json.actionErrors && json.actionErrors.length>0){//判断有没有actionErrors  
            $.each(json.actionErrors,function(index,data){  
                $("#errorMessages").append("<li>"+data+"</li>");  
            });  
            return;  
        }  
        if(json.fieldErrors && !isEmpty(json.fieldErrors)){//判断有没有fieldError(LoginAction-validation.xml验证错误)  
            $.each(json.fieldErrors,function(index,value){//index就是field的name,value就是该filed对应的错误列表，这里取第一个  
                $("#error_"+index).html(value[0]);  
                $("#error_"+index).addClass("errorLabel");  
            });  
            return;  
        }  
        alert("登陆成功");//既没有actionError有没有fieldError则登陆成功  
    });  
    function isEmpty(obj){//判断对象是否为空(处理Object obj = {}这种情况认为isEmpty=true)  
        for(var p in obj){  
            return false;  
        }  
        return true;  
    }  
</script> 
</html>
