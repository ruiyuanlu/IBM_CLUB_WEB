package com.istc.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * 用于实现页面的重定向
 */
@AllowedMethods({"welcome","fileupload"})
public class RedirectNeedCheckAction  extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session;

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	@Action(value="welcome", results={@Result(name="welcome",location="welcome.jsp")}) 
	public String welcome() {
		try{
			session.remove("token");
			session.remove("tokennull");
		}
		catch (NullPointerException e){

		}
		finally {
			return "welcome";
		}
	}
	
	@Action(value="fileupload", results={@Result(name="fileupload",location="fileupload.jsp")}) 
	public String fileupload() {
		return "fileupload";
	}
	
}
