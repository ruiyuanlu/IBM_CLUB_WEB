package club.istc.action;


import java.util.Map;
import club.istc.bean.*;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 注册
 */

public class Register extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String id;
	private int age;
	private String name;
	private String password;
	private String QQ;
	private String phoneNumber;
	private int gender;
	private Person curPerson;
	private Map<String, Object> session;
	// 用户登录 
	
	@Override
	public String execute() {
		//加上数据库的操作
		
		try {
			if (!personSet()) {
				session.put("faulttype", "IllegalDefault");
				return "input";
			}
//			if(new Func_for_control().registerPerson(curPerson)){
//				session.put("personInfo", curPerson);
//				return SUCCESS;
//			}
		}
//		catch (IllegalAccessException e) {
//			// TODO: handle exception
//			session.put("faulttype", "Illegal");
//			return "illegal";
//		}
		catch (Exception e) {
			// TODO: handle exception
			session.put("faulttype", "Unknown");
			return ERROR;
		}
		
		return SUCCESS;
		//System.out.println("gender is"+gender);

	}
	

	public boolean personSet(){
		try {
			curPerson.setID(this.id);
			curPerson.setAge(age);
			curPerson.setName(name);
			curPerson.setQQ(this.QQ);
			curPerson.setPhoneNumber(phoneNumber);
			curPerson.setPassword(password);
			if (gender==0) {
				curPerson.setGender(false);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			return false;
		}
		return true;
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

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
}
