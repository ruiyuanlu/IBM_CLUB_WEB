package org.istc.action;

import com.opensymphony.xwork2.ActionSupport;;

/**
 * 登录
 * 通过输入的用户名和密码，获取该用户信息并存入session中
 * 该session在浏览网站的过程中全程存在，代表用户处于登录状态
 * 任何涉及数据库的操作均需要查验session
 */

public class Login extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	// 用户登录 
	
	public String execute() {
		//通过用户名和密码获取Member类对象信息
		//在这里嵌入数据库相关代码以检测是否成功连入数据库
		if ("123".equals(id)) {
			if ("456".equals(password)) {
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
