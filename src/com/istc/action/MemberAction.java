package com.istc.action;

import java.util.Map;

import com.istc.validation.*;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于管理成员信息的增删改查，其中手动增加成员仅可以由部长级以上成员完成
 */
@ParentPackage("needajax")
@AllowedMethods({"addPerson"})
public class MemberAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;
    private String id;
    private String name;
    private String oldpassword;
    private String password;
    private String repassword;
    private String dept;

    @Action(
            value="addPerson",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String addPerson(){
        System.out.println(id+" "+password+" "+name+" "+dept);
        return INPUT;
    }

    public void validateAddPerson(){
        if (id==null || id.equals("")) {
            addFieldError("id", "请输入学号！");
        }
        else {
            if (!new IDCheck(id).getResult()) {
                addFieldError("id", "学号输入有误，请检查并重新输入。");
            }
/*            else if (condition) {
			addFieldError("id", "您的学号已经被注册过！请登录或尝试找回密码。");
		}*/
            else {

            }
        }
        if (password==null || password.equals("")) {
            addFieldError("password", "请设置密码！");
        }
        else {
            if (!new PasswordCheck(password).getResult()) {
                addFieldError("password", "密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位。");
            }
            else if (!password.equals(repassword)) {
                addFieldError("repassword", "两次输入的密码不一致！");
            }
            else {
            }
        }
        if (name==null || name.equals("")) {
            addFieldError("name", "请输入姓名！");
        }
        else {
            if (!new NameCheck(name).getResult()) {
                addFieldError("name", "请输入正确的姓名信息！");
            }
            else {
            }
        }
    }

    @Action(
            value="changePassword",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
            }
    )
    public String changePassword(){
        System.out.println("修改前的密码："+oldpassword);
        System.out.println("修改后的密码："+password);
        this.session.clear();
        response=CookieUtils.clearCookie(request,response);
        System.out.println("session和cookie均被清除");
        return INPUT;
    }

    public void validateChangePassword(){
        //此处有从数据库获取旧密码的步骤，这里先用假数据测试，用户ID的来源是session
        String oldpasswordfromdatabase="456789";
        if (oldpassword==null || oldpassword.equals("")){
            addFieldError("oldpassword","请输入旧密码！");
        }
        else {
            if (!oldpassword.equals(oldpasswordfromdatabase)){
                addFieldError("oldpassword","您输入的旧密码不正确！");
            }
        }
        if (password==null || password.equals("")){
            addFieldError("password","请键入新密码！");
        }
        else {
            if (oldpasswordfromdatabase.equals(password)){
                addFieldError("password","请键入与旧密码不同的新密码！");
            }
            if (!new PasswordCheck(password).getResult()){
                addFieldError("password","密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位。");
            }
            if (!password.equals(repassword)){
                addFieldError("repassword","两次输入的密码不一致！");
            }
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }
}
