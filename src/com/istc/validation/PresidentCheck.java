package com.istc.validation;

import com.istc.bean.Person;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * Created by Morn Wu on 2017/3/7.
 */
public class PresidentCheck extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        HttpSession session= ServletActionContext.getRequest().getSession();
        String id=((Person)session.getAttribute("personInfo")).getID();
        if (AuthorityUtils.getInstance().operateByPresidentGroup(session)){
            return actionInvocation.invoke();
        }
        return "exit";
    }
}
