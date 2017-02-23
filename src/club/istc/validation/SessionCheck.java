package club.istc.validation;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import club.istc.bean.Person;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 一个超时登录的拦截器
 */ 

@SuppressWarnings("serial")

public class SessionCheck extends AbstractInterceptor {
	
	@SuppressWarnings("unchecked")
	@Override
	
	public String intercept(ActionInvocation ai) throws Exception {
		System.out.println("拦截器启动。");
		Person userinfo = null;
			//验证Session是否过期
		if (!ServletActionContext.getRequest().isRequestedSessionIdValid()) {
			//session过期,转向session过期提示页,最终跳转至登录页面
			System.out.println("session已过期。");
			return "exit";
		}
		else {
			//验证是否已经登录
			userinfo = (Person) ServletActionContext.getRequest().getSession().getAttribute("personInfo");
			if (userinfo == null) {
				//尚未登录,跳转至登录页面
				System.out.println("session非法。");
				return "exit";
			} else {
				System.out.println("session正常。");
				return ai.invoke();
			}
		}
	}
}