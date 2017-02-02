package club.istc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于实现页面的重定向
 */

public class Redirect extends ActionSupport{
	
	/**
	 * 
	 */
	private Map<String, Object> session;
	private static final long serialVersionUID = 1L;
	
	public Redirect() {
		// TODO Auto-generated constructor stub
	}

	public String mainpage() {
		return "mainpage";
	}
	
	public String interview() {
		return "interview";
	}
	
	public String fileupload() {
		return "fileupload";
	}
	
	public String login() {
		return "login";
	}
	
	public String register() {
		return "register";
	}
	
	public String timeout() {
		return "timeout";
	}
	
	public String welcome() {
		return "welcome";
	}
	
}
