package club.istc.action;


import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.SessionAware;
import club.istc.bean.*;
import club.istc.validation.*;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 注册
 */
@ParentPackage("needajax")
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
	private Person curPerson=new Person();
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
		if (id==null || id=="") {
			addFieldError("id", "请输入您的学号！");
		}
		else {
//			if (condition) {
//			addFieldError("id", "您的学号已经被注册过！请登录或尝试找回密码。");
//		}
			if (!new IDCheck(id).getResult()) {
				addFieldError("id", "您的学号输入有误，请检查并重新输入。");
			}
			else {
				curPerson.setID(this.id);
			}
		}
		if (password==null || password=="") {
			addFieldError("password", "请设置您的密码！");
		}
		else {
			if (!new PasswordCheck(password).getResult()) {
				addFieldError("password", "密码中只允许使用数字、字母和下划线。长度不小于6位，不大于30位。");
			}
			else if (!password.equals(repassword)) {
				addFieldError("repassword", "两次输入的密码不一致！");
			} 
			else {
				curPerson.setPassword(password);
			}
		}
		if (name==null || name=="") {
			addFieldError("name", "请输入您的姓名！");
		}
		else {
			if (!new InjectionCheck(name).getResult()) {
				addFieldError("name", "请输入正确的姓名信息！");
			}
			else {
				curPerson.setName(name);
			}
		}
		if (phoneNumber==null || phoneNumber=="") {
			addFieldError("phoneNumber", "请告诉我们您的手机号以方便联系您。");
		}
		else {
			if (!new PhoneNumberCheck(phoneNumber).getResult()) {
				addFieldError("phoneNumber", "请输入有效的大陆手机号码！");
			}
			else {
				curPerson.setPhoneNumber(phoneNumber);
			}
		}
		if (QQ==null || QQ=="") {
			addFieldError("QQ", "请告诉我们您的QQ号以方便日后您与社团其他成员的互动。");
		}
		else {
			if (!new QQCheck(QQ).getResult()) {
				addFieldError("QQ", "请输入正确的QQ号码！");
			}
			else {
				curPerson.setQQ(this.QQ);
			}
		}
		//西安交通大学招生简章规定，少年班的入学年龄不得低于14岁
		if (age<14 || age>100) {
			addFieldError("age", "您输入的年龄信息有误，请重新输入！");
		}
		else {
			curPerson.setAge(age);
		}
		if (gender.equals("0")) {
			curPerson.setGender(false);
		}
		session.put("personInfo", curPerson);
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
	
}
