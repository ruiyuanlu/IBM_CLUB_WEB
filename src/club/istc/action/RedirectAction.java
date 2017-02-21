package club.istc.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于实现页面的重定向
 */
@AllowedMethods({"mainpage","welcome","error","login","register"})
public class RedirectAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Action(value="mainpage", results={@Result(name="mainpage",location="mainpage.jsp")}) 
	public String mainpage() {
		return "mainpage";
	}
	
	@Action(value="welcome", results={@Result(name="welcome",location="welcome.jsp")}) 
	public String welcome() {
		return "welcome";
	}
	
	@Action(value="error", results={@Result(name="error",location="error.jsp")}) 
	public String error() {
		return "error";
	}
	
	@Action(value="login", results={@Result(name="login",location="login.jsp")}) 
	public String login() {
		return "login";
	}
	
	@Action(value="register", results={@Result(name="register",location="register.jsp")}) 
	public String register() {
		return "register";
	}
	
	@Action(value="fileupload", results={@Result(name="fileupload",location="fileupload.jsp")}) 
	public String fileupload() {
		return "fileupload";
	}
	
}
