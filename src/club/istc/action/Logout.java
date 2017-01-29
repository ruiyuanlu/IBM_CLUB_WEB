package club.istc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登出，销毁session即可
 */

@SuppressWarnings("serial")
public class Logout extends ActionSupport {
	
	Map<String, Object> session;
	public Logout(){
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}
	
	public String execute(){   
	    System.out.println("退出系统"); 
	    //登录即意味着session存在于当前页面，退出时销毁session
	    session.clear();
	    return "exit";
	}
}
