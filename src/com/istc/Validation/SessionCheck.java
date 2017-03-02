package com.istc.Validation;

/**
 * Created by lurui on 2017/2/24 0024.
 */

import com.istc.Entities.Entity.Member;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 一个超时登录的拦截器
 */

@SuppressWarnings("serial")

public class SessionCheck extends MethodFilterInterceptor {

    @SuppressWarnings("unchecked")
    @Override
    public String doIntercept(ActionInvocation ai) throws Exception {
        System.out.println("全局 SessionCheck 拦截器启动。");
        Member userinfo = null;
        //验证Session是否过期
        if (!ServletActionContext.getRequest().isRequestedSessionIdValid()) {
            //session过期,转向session过期提示页,最终跳转至登录页面
            System.out.println("session已过期。转入loginRedirect");
            return "loginRedirect";
        }
        //验证是否已经登录
        userinfo = (Member) ServletActionContext.getRequest().getSession().getAttribute("member");
        if (userinfo == null) {
            //尚未登录,跳转至登录页面
            System.out.println("session非法。转入loginRedirect");
            return "loginRedirect";
        }
        System.out.println("session正常。退出");
        return ai.invoke();
    }
}
