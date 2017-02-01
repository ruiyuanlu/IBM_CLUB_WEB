package club.istc.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于实现页面的重定向
 */

public class Redirect extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
}
