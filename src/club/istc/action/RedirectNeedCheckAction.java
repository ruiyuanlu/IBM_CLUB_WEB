package club.istc.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于实现页面的重定向
 */
@AllowedMethods({"welcome","fileupload"})
public class RedirectNeedCheckAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Action(value="welcome", results={@Result(name="welcome",location="welcome.jsp")}) 
	public String welcome() {
		return "welcome";
	}
	
	@Action(value="fileupload", results={@Result(name="fileupload",location="fileupload.jsp")}) 
	public String fileupload() {
		return "fileupload";
	}
	
}
