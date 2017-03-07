package com.istc.validation;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Morn Wu on 2017/3/3.
 * 用于敏感操作的拦截
 */
public class SensitivityCheck extends MethodFilterInterceptor{

    @SuppressWarnings("unchecked")
    @Override
    protected String doIntercept(ActionInvocation ai) throws Exception {
        HttpSession session= ServletActionContext.getRequest().getSession();
        String actionname=ai.getProxy().getActionName();
        if (actionname.equals("memmodify") || actionname.equals("personinfomanagement")){
            System.out.println("检测到敏感操作，目标action："+actionname);
            session.setAttribute("redirecttarget",actionname);
            return "recheck";
        }
        else{
            return ai.invoke();
        }
    }
}