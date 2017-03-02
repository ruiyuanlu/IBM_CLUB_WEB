package com.istc.action;

/**
 * Created by lurui on 2017/2/24 0024.
 */
import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityService.MemberService;
import com.istc.Utilities.CookieUtils;
import com.istc.Utilities.TokenUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用于实现页面的重定向
 */
@ParentPackage("all")
//有的部署时没有 @AllowedMethods 时无法找到注册的方法
//@AllowedMethods({"mainpage","welcome","login","register","fileupload"})
public class RedirectAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    @Resource(name = "memberService")
    private MemberService memberService;

    private static final long serialVersionUID = 186336L;

    private static final String loginKey = "member";
    private static final String tokenKey = "token";

    private Map<String, Object> session;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Action(value="welcome", results={@Result(name="welcome",location="jsp/welcome.jsp")})
    public String welcome() {
        if(session.get(tokenKey) != null)
            session.remove(tokenKey);
        return "welcome";
    }

    @Action(value="mainpage", results={@Result(name="mainpage",location="mainPage.jsp")})
    public String mainpage() {
        if(isLogin())
            session.remove(tokenKey);// 用完后删除token
        return "mainpage";
    }

    @Action(value="login", results={
                    @Result(name="login",location="login.jsp"),
                    @Result(name = "mainpage",location = "mainPage.jsp")
            })
    public String login() {
        if (isLogin()){
            System.out.println("重复登录，直接进入主页");
            return "mainpage";
        }
        session.put("token", TokenUtils.getInstance().generateNewToken());
        return "login";
    }

    @Action(value="register", results={
                    @Result(name="register",location="register.jsp"),
                    @Result(name = "mainpage",location = "mainPage.jsp")
            })
    public String register() {
        if (isLogin()){
            System.out.println("无需注册，直接进入主页");
            return "mainpage";
        }
        session.put("token", TokenUtils.getInstance().generateNewToken());
        return "register";
    }

    @Action(value="fileupload", results={@Result(name="fileupload",location="fileupload.jsp")})
    public String fileupload() {
        return "fileupload";
    }

    @Action(value="QRcodesign", results={@Result(name="QRcodesign",location="QRcodesign.jsp")})
    public String QRcodesign() {
        return "QRcodesign";
    }



    /**
     * 判断用户登陆状态
     * 防止用户在登录的情况下仍然访问登录和注册页面
     */
    private boolean isLogin(){
        if(session.get(loginKey) != null) return true;
        CookieUtils cookieUtil = CookieUtils.getInstance();
        Member member = cookieUtil.getMemberIDAndPasswordFromCookie(request);
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
