package com.istc.action;


import java.util.Map;

import com.istc.bean.Person;
import com.istc.validation.*;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 注册
 */
@ParentPackage("needajax")
@Action(
		value="Register", 
        results={
				@Result(name="input", type="json", params={"ignoreHierarchy", "false"})
        }
) 
public class RegisterAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private String id;
	private int age;
	private String name;
	private String password;
	private String repassword;
	private String QQ;
	private String phoneNumber;
	private String gender;
	private String token;
	private Person curPerson=new Person();
	private boolean passed=true;
	private Map<String, Object> session;
	// 用户登录 
	
	public RegisterAction() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	@Override
	public String execute() {
		tokenCheck();
		System.out.println("execute执行");
		//加上数据库的操作
		
		try {
//			if(new Func_for_control().registerPerson(curPerson)){
				session.put("personInfo", curPerson);
//				return SUCCESS;
//			}
		}
//		catch (IllegalAccessException e) {
//			// TODO: handle exception
//			addFieldError("registerfault", "数据库写入失败！请稍后再试。");
//		}
		catch (Exception e) {
			// TODO: handle exception
			this.addActionError("出现了未知错误，请稍后再试。");
			return INPUT;
		}
		
		//这是一小段测试代码
		return INPUT;
		//System.out.println("gender is"+gender);

	}
	
	
	@Override
	public void validate(){
		System.out.println("验证器");
		//
		if (id==null || id=="") {
			addFieldError("id", "请输入您的学号！");
			passed=false;
		}
		else {
//			if (condition) {
//			addFieldError("id", "您的学号已经被注册过！请登录或尝试找回密码。");
//		}
			if (!new IDCheck(id).getResult()) {
				addFieldError("id", "您的学号输入有误，请检查并重新输入。");
				passed=false;
			}
			else {
				curPerson.setID(this.id);
			}
		}
		if (password==null || password=="") {
			addFieldError("password", "请设置您的密码！");
			passed=false;
		}
		else {
			if (!new PasswordCheck(password).getResult()) {
				addFieldError("password", "密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位。");
				passed=false;
			}
			else if (!password.equals(repassword)) {
				addFieldError("repassword", "两次输入的密码不一致！");
				passed=false;
			} 
			else {
				curPerson.setPassword(password);
			}
		}
		if (name==null || name=="") {
			addFieldError("name", "请输入您的姓名！");
			passed=false;
		}
		else {
			if (!new InjectionCheck(name).getResult()) {
				addFieldError("name", "请输入正确的姓名信息！");
				passed=false;
			}
			else {
				curPerson.setName(name);
			}
		}
		if (phoneNumber==null || phoneNumber=="") {
			addFieldError("phoneNumber", "请告诉我们您的手机号以方便联系您。");
			passed=false;
		}
		else {
			if (!new PhoneNumberCheck(phoneNumber).getResult()) {
				addFieldError("phoneNumber", "请输入有效的大陆手机号码！");
				passed=false;
			}
			else {
				curPerson.setPhoneNumber(phoneNumber);
			}
		}
		if (QQ==null || QQ=="") {
			addFieldError("QQ", "请告诉我们您的QQ号以方便日后您与社团其他成员的互动。");
			passed=false;
		}
		else {
			if (!new QQCheck(QQ).getResult()) {
				addFieldError("QQ", "请输入正确的QQ号码！");
				passed=false;
			}
			else {
				curPerson.setQQ(this.QQ);
			}
		}
		//西安交通大学招生简章规定，少年班的入学年龄不得低于14岁
		if (age<14 || age>100) {
			addFieldError("age", "您输入的年龄信息有误，请重新输入！");
			passed=false;
		}
		else {
			curPerson.setAge(age);
		}
		if (gender.equals("0")) {
			curPerson.setGender(false);
		}
		session.put("personInfo", curPerson);
		if (!passed){
			tokenCheck();
		}
		return;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	public String getQQ() {
		return QQ;
	}

	public void setQQ(String QQ) {
		this.QQ = QQ;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	private void tokenCheck(){
		System.out.println(token);
		System.out.println(session.get("token"));
		String curtoken= TokenCheck.generateNewToken();
		try{
			if (!TokenCheck.checkToken(session,token) && session.get("tokennull").equals("false")){
				session.remove("token");
				addActionMessage(curtoken);
				session.put("token",curtoken);
				//System.out.println(curtoken);
				addActionError("请勿重复提交！");
				return;
			}
		}
		catch (NullPointerException e){
			session.put("tokennull","false");
			addActionMessage(curtoken);
			session.put("token", curtoken);
			return;
		}
		session.remove("token");
		addActionMessage(curtoken);
		session.put("token", curtoken);
	}
	
}
