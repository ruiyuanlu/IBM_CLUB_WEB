package com.istc.action;

/**
 * Created by lurui on 2017/2/24 0024.
 */
import com.istc.Utilities.CookieUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

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

    private static final long serialVersionUID = 186336L;

    private static final String loginKey = "member";
    private static final String tokenKey = "token";

    private Map<String, Object> session;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Action(value="welcome", results={@Result(name="welcome",location="jsp/welcome.jsp")})
    public String welcome() {
        return "welcome";
    }

    @Action(value="mainpage", results={@Result(name="mainpage",location="mainpage.jsp")})
    public String mainpage() {
        try{
            directLogin();
            session.remove(tokenKey);// 用完后删除
        }
        finally {
            return "mainpage";
        }
    }






    /**
     * 防止用户在登录的情况下仍然访问登录和注册页面
     */
    private boolean directLogin(){
        if(session.get(loginKey)!=null) return true;
        CookieUtils cookieUtil = CookieUtils.getInstance();
        if (cookieUtil.checkCookie(request)){
            response=CookieUtils.updateCookie(request,response);
            System.out.println("cookie更新");
            session.put("personInfo",CookieUtils.getPersonInCookie(request));
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
