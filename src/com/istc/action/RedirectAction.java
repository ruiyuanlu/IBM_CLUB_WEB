package com.istc.action;

/**
 * Created by lurui on 2017/2/24 0024.
 */
import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityService.MemberService;
import com.istc.Utilities.CookieUtils;
import com.istc.Utilities.TokenUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用于实现页面的重定向
 */
@Controller("redirectAction")
@Scope("prototype")
//有的部署时没有 @AllowedMethods 时无法找到注册的方法
@AllowedMethods({"deptManagement", "personInfoManagement", "memberModify", "homeworkManagement", "signRedirect","mainpage", "success", "welcome", "loginRedirect", "registerRedirect", "register", "fileupload", "memberInfoManagement"})
public class RedirectAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    @Resource(name = "memberService")
    private MemberService memberService;

    private static final long serialVersionUID = 186336L;

    private static final String loginKey = "member";
    private static final String tokenKey = "token";

    private Map<String, Object> session;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public RedirectAction(){
        System.out.println("进入RedirectAction");
    }

    @Action(value = "memberInfoManagementRedirect", results = {@Result(name = "personInfoManagement", location = "jsp/memberInfoManagement.jsp")})
    public String memberInfoManagementRedirect(){
        return "personInfoManagement";
    }

    @Action(value="deptManagementRedirect", results={@Result(name="deptManagement",location="jsp/deptManagement.jsp")})
    public String deptManagementRedirect() {
        return "deptManagement";
    }

    @Action(value="homeworkManagementRedirect", results={@Result(name="homeworkManagement",location="jsp/homeworkManagement.jsp")})
    public String homeworkManagementRedirect() {
        return "homeworkManagement";
    }

    @Action(value="memberModifyRedirect", results={@Result(name="memberModify",location="jsp/memberModify.jsp")})
    public String memberModifyRedirect() {
        return "memberModify";
    }

    @Action(value = "signRedirectRedirect", results = {@Result(name = "sign",location = "jsp/QRcodesign.jsp")})
    public String signRedirectRedirect(){
        return "sign";
    }

    @Action(value = SUCCESS, results = {@Result(name = "success", location = "jsp/success.jsp")})
    public String success(){
        return SUCCESS;
    }

    @Action(value="welcome", results={@Result(name="welcome", location="jsp/welcome.jsp")})
    public String welcome() {
        if(session.get(tokenKey) != null)
            session.remove(tokenKey);
        return "welcome";
    }

    @Action(value="mainpage", results={@Result(name="mainpage", location="jsp/mainPage.jsp")})
    public String mainpage() {
        System.out.println("进入mainpage转发");
        if(isLogin())
            session.remove(tokenKey);// 用完后删除token
        return "mainpage";
    }

    @Action(value="loginRedirect", results={
                    @Result(name="loginRedirect", location="jsp/loginPage.jsp"),
                    @Result(name = "mainpage", location = "jsp/mainPage.jsp")
            })
    public String loginRedirect() {
        System.out.println("进入转发的login");
        if (isLogin()){
            System.out.println("重复登录，直接进入主页");
            return "mainpage";
        }
        session.put(tokenKey, TokenUtils.getInstance().generateNewToken());
        return "loginRedirect";
    }

    @Action(value="registerRedirect", results={
                    @Result(name="register",location="jsp/register.jsp"),
                    @Result(name = "mainpage",location = "jsp/mainPage.jsp")
            })
    public String registerRedirect() {
        System.out.println("进入registerRedirect");
        if (isLogin()){
            System.out.println("无需注册，直接进入主页");
            return "mainpage";
        }
        session.put(tokenKey, TokenUtils.getInstance().generateNewToken());
        return "register";
    }

    @Action(value="upload", results={@Result(name="fileupload",location="jsp/fileupload.jsp")})
    public String fileupload() {
        return "fileupload";
    }

    @Action(value="QRcodesign", results={@Result(name="QRcodesign",location="QRcodesign.jsp")})
    public String QRcodesign() {
        return "QRcodesign";
    }

    @Action(value="memberInfoManagement", results={@Result(name="personInfoMaintenance",location="memberInfoManagement.jsp")})
    public String personInfoMaintenance() {
        return "personInfoMaintenance";
    }

    /**
     * 判断用户登陆状态
     * 防止用户在登录的情况下仍然访问登录和注册页面
     */
    private boolean isLogin(){
        System.out.println("    进入isLogin");
        if(session.get(loginKey) != null) return true;
        CookieUtils cookieUtil = CookieUtils.getInstance();
        Member member = cookieUtil.getMemberIDAndPasswordFromCookie(request);
        System.out.println("    member 是 "+member);
        if (memberService.exist(member)){
            cookieUtil.updateCookieValidTime(request,response);
            session.put(loginKey,member);
            return true;
        }
        return false;
    }

    /**
     * 三个接口的方法
     */
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
