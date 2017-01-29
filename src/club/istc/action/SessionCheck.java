package club.istc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class SessionCheck {
	
	
	public SessionCheck(Map<String, Object> session) {
		// TODO Auto-generated constructor stub
		check(session);
		
	}
	
	private boolean check(Map<String, Object> session) {
		//获得包含当前登录用户信息的session，用于判断权限
		try {
			session.get("PersonInfo");
		} catch (NullPointerException e) {
			// TODO: handle exception
			return false;
		}
		//验证部分，if中填写验证代码，涉及到从数据库中的验证
		if (true) {
			return true;
		} else {
			return false;
		}
	}

}
