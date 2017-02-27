package com.istc.validation;

import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import com.istc.bean.Person;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 一个登录的拦截器
 */ 

@SuppressWarnings("serial")

public class SessionCheck extends MethodFilterInterceptor {
	
	@SuppressWarnings("unchecked")
	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();

		System.out.println("拦截器启动。");
		Person userinfo = null;
		//验证Session是否过期
		if (!request.isRequestedSessionIdValid()) {
			System.out.println("session已过期。");
			if (CookieUtils.checkCookie(request)){
				updateNewSession(request,response,session);
				return  ai.invoke();
			}
			return "exit";
		}
		else {
			//验证是否已经登录
			userinfo = (Person) session.getAttribute("personInfo");
			if (userinfo == null) {
				//尚未登录,跳转至登录页面
				System.out.println("session非法。");
				if (CookieUtils.checkCookie(request)){
					updateNewSession(request,response,session);
					return  ai.invoke();
				}
				return "exit";
			} else {
				System.out.println("session正常。");
				return ai.invoke();
			}
		}
	}

	private void updateNewSession(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		System.out.println("通过cookie重新生成了session");
		//设置session
		session.setAttribute("personInfo",CookieUtils.getPersonInCookie(request));
		//更新cookie的超时
		Cookie[] old=CookieUtils.updateCookie(request);
		response.addCookie(old[0]);
		response.addCookie(old[1]);
	}
}