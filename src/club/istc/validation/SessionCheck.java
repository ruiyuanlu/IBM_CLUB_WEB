package club.istc.validation;

import java.util.Hashtable;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

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
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Hashtable<String, Object> userinfo = null;
		//对登录与注销请求直接放行，不予拦截
		if (url.indexOf("loginAction") != -1 || url.indexOf("logoutAction") != -1) {
			return ai.invoke();
			}
		else {
			//验证Session是否过期
			if (!ServletActionContext.getRequest().isRequestedSessionIdValid()) {
				//session过期,转向session过期提示页,最终跳转至登录页面
				return "exit";
			} 
			else {
				//验证是否已经登录
				userinfo = (Hashtable<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("personInfo");
				if (userinfo == null) {
					//尚未登录,跳转至登录页面
					return "exit";
				} else {
					return ai.invoke();
				}
			}
		}
	}
}

