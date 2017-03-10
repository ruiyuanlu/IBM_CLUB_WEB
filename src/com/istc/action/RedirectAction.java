package com.istc.action;

import com.istc.validation.CookieUtils;
import com.istc.validation.TokenCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Morn Wu on 2017/2/23.
 */
@AllowedMethods({"mainpage",
        "login",
        "register",
        "error",
        "exit",
        "welcome",
        "memmodify",
        "personinfomanagement",
        "fileupload",
        "QRcodesign",
        "deptmanagement"})
public class RedirectAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware {

    Map<String, Object> session;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public void setSession(Map<String, Object> arg0) {
        // TODO Auto-generated method stub
        this.session=arg0;
    }
    @Action(value="mainpage", results={@Result(name="mainpage",location="mainpage.jsp")})
    public String mainpage() {
        try{
            directLogin();
            session.remove("token");
        }
        finally {
            return "mainpage";
        }
    }

    @Action(
            value="login",
            results={
                    @Result(name="login",location="login.jsp"),
                    @Result(name = "mainpage",location = "mainpage.jsp")
            })
    public String login() {
        if (directLogin()){
            System.out.println("重复登录，直接进入主页");
            return "mainpage";
        }
        session.put("token", TokenCheck.generateNewToken());
        return "login";
    }

    @Action(
            value="register",
            results={
                    @Result(name="register",location="register.jsp"),
                    @Result(name = "mainpage",location = "mainpage.jsp")
            })
    public String register() {
        if (directLogin()){
            System.out.println("无需注册，直接进入主页");
            return "mainpage";
        }
        session.put("token", TokenCheck.generateNewToken());
        return "register";
    }

    @Action(value="error", results={@Result(name="error",location="error.jsp")})
    public String error() {return "error";}

    @Action(value="exit", results={@Result(name="exit",location="timeout.jsp")})
    public String exit() {return "exit";}

    @Action(value="welcome", results={@Result(name="welcome",location="welcome.jsp")})
    public String welcome() {
        try{
            session.remove("token");
        }
        catch (NullPointerException e){

        }
        finally {
            return "welcome";
        }
    }

    @Action(value="memmodify", results={@Result(name="memmodify",location="memmodify.jsp")})
    public String memberMaintenance() {
        return "memmodify";
    }

    @Action(value="fileupload", results={@Result(name="fileupload",location="fileupload.jsp")})
    public String fileupload() {
        return "fileupload";
    }

    @Action(value="QRcodesign", results={@Result(name="QRcodesign",location="QRcodesign.jsp")})
    public String QRcodesign() {
        return "QRcodesign";
    }

    @Action(value="attendance", results={@Result(name="attendance",location="attendance.jsp")})
    public String attendance() {
        return "attendance";
    }

    @Action(value="personinfomanagement", results={@Result(name="personInfoMaintenance",location="personinfomanagement.jsp")})
    public String personInfoMaintenance() {
        return "personInfoMaintenance";
    }

    @Action(value="deptmanagement", results={@Result(name="deptManagement",location="deptManagement.jsp")})
    public String deptManagement() {
        return "deptManagement";
    }

    @Action(value="homeworkmanagement", results={@Result(name="homeworkManagement",location="homeworkManagement.jsp")})
    public String homeworkManagement() {
        return "homeworkManagement";
    }

    @Action(value="scoring", results={@Result(name="scoring",location="scoring.jsp")})
    public String scoring() {
        return "scoring";
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }

    /**
     * 防止用户在登录的情况下仍然访问登录和注册页面
     */
    private boolean directLogin(){
        try {
            if(session.get("personInfo")!=null){
                return true;
            }
            else {
                if (CookieUtils.checkCookie(request)){
                    response=CookieUtils.updateCookie(request,response);
                    System.out.println("cookie更新");
                    session.put("personInfo",CookieUtils.getPersonInCookie(request));
                    return true;
                }
            }
        }
        catch (Exception e){
            return  false;
        }
        return false;
    }
}
