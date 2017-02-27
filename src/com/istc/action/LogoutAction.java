package com.istc.action;

import java.util.Map;

import com.istc.validation.CookieUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登出，销毁session即可
 */

@SuppressWarnings("serial")
@Action(
		value="Logout", 
        results={ 
				@Result(name="exit",location="timeout.jsp")
        }
) 
public class LogoutAction implements SessionAware,ServletResponseAware,ServletRequestAware {
	
	Map<String, Object> session;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private CookieUtils cu;

	public LogoutAction(){

	}
	
	public String execute(){
		cu=new CookieUtils(request);
	    System.out.println("退出系统");
	    //登录即意味着session存在于当前页面，退出时销毁session
	    session.clear();
		Cookie[] emptycookie=cu.clearCookie(request);
		if (emptycookie!=null) {
			response.addCookie(emptycookie[0]);
			response.addCookie(emptycookie[1]);
			System.out.println("cookie初始化");
		}
	    return "exit";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response=httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;
	}
}
