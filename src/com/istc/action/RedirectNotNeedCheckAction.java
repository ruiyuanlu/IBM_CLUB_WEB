package com.istc.action;

import com.istc.validation.TokenCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by Morn Wu on 2017/2/23.
 */

public class RedirectNotNeedCheckAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;

    public void setSession(Map<String, Object> arg0) {
        // TODO Auto-generated method stub
        this.session=arg0;
    }
    public String mainpage() {
        try{
            session.remove("token");
            session.remove("tokennull");
        }
        catch (NullPointerException e){

        }
        finally {
            return "mainpage";
        }
    }

    public String login() {
        String curtoken= TokenCheck.generateNewToken();
        return "login";
    }

    public String register() {
        String curtoken= TokenCheck.generateNewToken();
        return "register";
    }

    public String error() {return "error";}

    public String exit() {return "exit";}

}
