package club.istc.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

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
public class LogoutAction extends ActionSupport implements SessionAware{
	
	Map<String, Object> session;
	
	public LogoutAction(){

	}
	
	public String execute(){   
	    System.out.println("退出系统"); 
	    //登录即意味着session存在于当前页面，退出时销毁session
	    session.clear();
	    return "exit";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
}
