package com.istc.validation;

/**
 * 检查注册时输入的学号格式是否正确
 */

public class IDCheck {
	
	String id;
	boolean result;
	
	public IDCheck(String id) {
		// TODO Auto-generated constructor stub
		this.id=id.trim();
		result=checkID();
	}
	
	private boolean checkID() {
		//此处需要参考学号规则
		try {
			Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
		if (id.length()!=10) {
			return false;
		}
		return true;
	}
	
	public boolean getResult(){
		return result;
	}
}
