package com.istc.validation;

import java.util.Map;

import com.istc.bean.Person;

/**
 * 当前的会话有效性查验，以及获取其中的密码和学号信息
 */

public class SessionValidation {
	
    //@Column(length = 20)
    protected String  ID;    
    //@Column(length = 20)
    protected String  password;
	
	
	public SessionValidation(Map<String, Object> session) {
		// TODO Auto-generated constructor stub
		check(session);
		
	}
	
	private void check(Map<String, Object> session) {
		//获得包含当前登录用户信息的session，用于判断权限
		try {
			Person cur=(Person)session.get("PersonInfo");
			ID=cur.getID();
			password=cur.getPassword();
		} catch (NullPointerException e) {
			// TODO: handle exception
			throw new SessionErrorException();
		}
	}

	public String getID() {
		return ID;
	}

	public String getPassword() {
		return password;
	}
	
}