package com.istc.action;

import com.istc.Utilities.TokenUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

import static com.opensymphony.xwork2.Action.INPUT;


/**
 * Created by lurui on 2017/3/15 0015.
 */
@Controller("tokenAction")
@Scope(scopeName = "prototype")
@ParentPackage("ajax")
@SuppressWarnings("serial")
@AllowedMethods({"generateToken"})
public class TokenAction extends ActionSupport implements SessionAware{

    private String token;
    private Map<String, Object> session;
    private final String tokenKey = "token";

    @Action(value = "generateToken", results =  @Result(name = INPUT, type = "json", params = {"ignoreHierarchy", "false"}))
    public String generateToken(){
        token = TokenUtils.getInstance().generateNewToken();
        session.put(tokenKey, token);
        return INPUT;
    }

    public String getToken() {
        if(token == null) this.generateToken();
        return token;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
