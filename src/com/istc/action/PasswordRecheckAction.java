package com.istc.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by Morn Wu on 2017/3/3.
 * 密码二次校验
 */
@AllowedMethods({"recheck"})
public class PasswordRecheckAction extends ActionSupport implements SessionAware{

    private String password;
    private String redirecttarget;

    @Action(
            value="recheck",
            results={
                    @Result(name="success", location = "%{redirecttarget}.jsp"),
                    @Result(name="error",type = "redirect",location = "Logout")
            }
    )
    public String recheck(){
        System.out.println(redirecttarget);
        if (password.equals("456789"))
            return SUCCESS;
        else
            return ERROR;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRedirecttarget() {
        return redirecttarget;
    }

    public void setRedirecttarget(String redirecttarget) {
        this.redirecttarget = redirecttarget;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        redirecttarget=(String) map.get("redirecttarget");
        map.remove("redirecttarget");
    }
}