package com.istc.action;

import com.istc.validation.CookieUtils;
import com.istc.validation.TokenCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Morn Wu on 2017/2/23.
 */

public class RedirectNotNeedCheckAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware {

    Map<String, Object> session;
    private HttpServletResponse response;
    private HttpServletRequest request;
    CookieUtils cu;

    public void setSession(Map<String, Object> arg0) {
        // TODO Auto-generated method stub
        this.session=arg0;
    }
    public String mainpage() {
        if (CookieUtils.checkCookie(request)){
            response=CookieUtils.updateCookie(request,response);
            System.out.println("cookie更新");
            session.put("personInfo",CookieUtils.getPersonInCookie(request));
            return "loginsuccess";
        }
        try{
            session.remove("token");
        }
        catch (NullPointerException e){

        }
        finally {
            return "mainpage";
        }
    }

    public String login() {
        session.put("token", TokenCheck.generateNewToken());
        return "login";
    }

    public String register() {
        session.put("token", TokenCheck.generateNewToken());
        return "register";
    }

    public String error() {return "error";}

    public String exit() {return "exit";}

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }

}
