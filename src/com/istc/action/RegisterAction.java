package com.istc.action;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.istc.bean.Person;
import com.istc.validation.*;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 注册
 */
@ParentPackage("needajax")
@AllowedMethods({"memberRegister"})
public class RegisterAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private String id;
	private String birthday;
	private String name;
	private String password;
	private String repassword;
	private String QQ;
	private String phoneNumber;
	private boolean gender;
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

    @Action(
            value="memberRegister",
            results={
                    @Result(name="input", type="json", params={"ignoreHierarchy", "false"})
            }
    )
    public String memberRegister() {
		tokenCheck();
		//以下是测试代码
		System.out.println("Gender"+gender);
		System.out.println(birthday);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Calendar curbirthday = Calendar.getInstance();
		Calendar curtime = Calendar.getInstance();
		curtime.setTime(new Date());
		try {
			curbirthday.setTime(sdf.parse(birthday));
			System.out.println("年龄："+(curtime.get(Calendar.YEAR)-curbirthday.get(Calendar.YEAR)));
		}
		catch (Exception e){

		}
		//System.out.println("execute执行");
		//加上数据库的操作
		
		try {
//			if(new Func_for_control().registerPerson(curPerson)){
				//session.put("personInfo", curPerson);
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
	

	public void validateMemberRegister(){
		System.out.println("验证器");
		//
		if (id==null || id.equals("")) {
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
		if (password==null || password.equals("")) {
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
		if (name==null || name.equals("")) {
			addFieldError("name", "请输入您的姓名！");
			passed=false;
		}
		else {
			if (!new NameCheck(name).getResult()) {
				addFieldError("name", "请输入正确的姓名信息！");
				passed=false;
			}
			else {
				curPerson.setName(name);
			}
		}
		if (phoneNumber==null || phoneNumber.equals("")) {
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
		if (QQ==null || QQ.equals("")) {
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
		Calendar curtime = Calendar.getInstance();
		curtime.setTime(new Date());
		if (birthday.equals("")) {
			addFieldError("birthday", "请输入您的生日！");
			passed=false;
		}
		else {
			if(!new DateCheck(birthday).getResult()){
				addFieldError("birthday", "输入的出生日期不得晚于"+(curtime.get(Calendar.YEAR)-14)+"年1月1日，不得早于1970年1月1日");
				passed=false;
			}
			else {
				//curPerson.setAge(birthday);
			}
		}
		if (!passed){
			tokenCheck();
		}
		return;
	}
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
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
			if (!TokenCheck.checkFormToken(session,token)){
				session.remove("token");
				addActionMessage(curtoken);
				session.put("token",curtoken);
				//System.out.println(curtoken);
				addActionError("请勿重复提交！");
				return;
			}
		}
		catch (NullPointerException e){
			addActionMessage(curtoken);
			session.put("token", curtoken);
			addActionError("请勿重复提交！");
			return;
		}
		session.remove("token");
		addActionMessage(curtoken);
		session.put("token", curtoken);
	}
	
}
