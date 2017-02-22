package club.istc.action;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import club.istc.bean.*;
import club.istc.validation.IDCheck;
import club.istc.validation.InjectionCheck;
import club.istc.validation.PasswordCheck;
import club.istc.validation.PhoneNumberCheck;
import club.istc.validation.QQCheck;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于管理成员信息的增删改查，其中手动增加成员仅可以由部长级以上成员完成
 */

public class MemberAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private int age;
	private String oldpassword;
	private String newpassword;
	private String renewpassword;
	private String QQ;
	private String phoneNumber;
	private String gender;
	private Person curPerson=new Person();
	private Member curMember=new Member();
	private String[] targetid;
	private Set<Department> dept;
	private Map<String, Object> session;
	
	public MemberAction() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	/**
	 * 手动添加成员<br/>
	 * 为了简化操作，部长增加成员只需要设置学号和姓名，其余信息可由部员自行修改
	 */
	public String add() {
		try {
			curPerson.setAge(18);
			curPerson.setGender(true);
			curPerson.setPassword("111111");
			curPerson.setID(id);
			curPerson.setName(name);
		} catch (NullPointerException e) {
			// TODO: handle exception
			addFieldError("addpersonerror", "用户创建失败！");
			return "adderror";
		}
		return "addsuccess";
	}
	
	public void validateAdd(){
		if (id==null || id=="") {
			addFieldError("id", "请输入学号！");
		}
		else {
//			if (condition) {
//			addFieldError("id", "您的学号已经被注册过！");
//		}
			if (!new IDCheck(id).getResult()) {
				addFieldError("setnewid", "学号输入有误，请检查并重新输入。");
			}
		}
		if (name==null || name=="") {
			addFieldError("setnewname", "请输入您的姓名！");
		}
		else {
			if (!new InjectionCheck(name).getResult()) {
				addFieldError("setnewname", "请输入正确的姓名信息！");
			}
		}
	}
	/**
	 * 手动删除成员<br/>
	 * 一般来说只能由本部门部长删除自己部门的成员
	 */	
	public String delete(){
		for (int i = 0; i < targetid.length; i++) {	
			try {
				//这里写数据库操作的代码
			} catch (Exception e) {
				// TODO: handle exception
				addFieldError("deleteperson", "在删除用户【"+targetid[i]+"】时删除失败，请检查！");
				return "deleteerror";
			}
		}
		return "deletesuccess";
	}
	/**
	 * 修改个人信息<br/>
	 */		
	public String modify(){
		try {
			//这里直接把curPerson写入数据库即可。
		} catch (NullPointerException e) {
			// TODO: handle exception
			addFieldError("modifypersonerror", "用户创建失败！");
			return "modifyerror";
		}
		return "modifysuccess";
	}
	
	public void validateModify(){
		//从数据库中取得数据
//		curPerson=
		if (id!=null || id!="") {
			if (!new IDCheck(id).getResult()) {
				addFieldError("id", "您的学号输入有误，请检查并重新输入。");
			}
//			else if (condition) {
//			addFieldError("id", "该学号已被注册过！请登录或尝试找回密码。");
//		}
			else {
				curPerson.setID(this.id);
			}
		}
		if (oldpassword!=null || oldpassword!="") {
			if (!new PasswordCheck(newpassword).getResult()) {
				addFieldError("password", "密码中只允许使用数字、字母和下划线，长度不小于6位，不大于30位。");
			}
			else if (!newpassword.equals(renewpassword)) {
				addFieldError("repassword", "两次输入的密码不一致！");
			} 
			else {
				curPerson.setPassword(newpassword);
			}
		}
		if (name!=null || name!="") {
			if (!new InjectionCheck(name).getResult()) {
				addFieldError("name", "请输入正确的姓名信息！");
			}
			else {
				curPerson.setName(name);
			}
		}
		if (phoneNumber!=null || phoneNumber!="") {
			if (!new PhoneNumberCheck(phoneNumber).getResult()) {
				addFieldError("phoneNumber", "请输入有效的大陆手机号码！");
			}
			else {
				curPerson.setPhoneNumber(phoneNumber);
			}
		}
		if (QQ!=null || QQ!="") {
			if (!new QQCheck(QQ).getResult()) {
				addFieldError("QQ", "请输入正确的QQ号码！");
			}
			else {
				curPerson.setQQ(this.QQ);
			}
		}
		//西安交通大学招生简章规定，少年班的入学年龄不得低于14岁
		if(age!=0){
			if (age<14 || age>100) {
				addFieldError("age", "您输入的年龄信息有误，请重新输入！");
			}
			else {
				curPerson.setAge(age);
			}
		}
		if (gender!="0") {
			if (gender.equals("0")) {
				curPerson.setGender(false);
			}
		}

	}
	
	public String get(){
		try {
			ArrayList<Meeting> memberlist = null;
			session.put("memberlist", memberlist);
		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("getmembererror", "获取成员信息失败！");
			return "geterror";
		}
		return "getsuccess";
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getRenewpassword() {
		return renewpassword;
	}

	public void setRenewpassword(String renewpassword) {
		this.renewpassword = renewpassword;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String[] getTargetid() {
		return targetid;
	}

	public void setTargetid(String[] targetid) {
		this.targetid = targetid;
	}	
	
}
