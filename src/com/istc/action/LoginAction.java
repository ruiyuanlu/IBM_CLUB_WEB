package com.istc.action;

import java.util.Map;

import com.istc.validation.CookieUtils;
import com.istc.validation.Crypto;
import com.istc.validation.InjectionCheck;
import com.istc.validation.TokenCheck;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.istc.bean.Person;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录。<br>
 * 通过输入的用户名和密码，获取该用户信息并存入session中。<br>
 * 该session在浏览网站的过程中全程存在，代表用户处于登录状态。<br>
 */
@ParentPackage("needajax")
@Action(
		value="Login",
        results={
				@Result(name="input", type="json", params={"ignoreHierarchy", "false"}),
				@Result(name="invalid.token", location="login.jsp")
        }
)
public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private String token;
	private boolean passed=true;
	private Map<String, Object> session;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String remember;
	CookieUtils cu;
	// 用户登录
	public LoginAction() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}

	/**
	 * 通过用户名和密码检验身份并产生session
	 * @throws Exception 
	 */

	public String execute(){
		tokenCheck();

		//在这里嵌入数据库相关代码
//		try {
//			SessionCheck ck=new SessionCheck(session);
//			curPerson=new Func_for_control().getMember(id, password, id);
//			if (curPerson==null) {
//				//登录失败，重定向至原页面，发送一个session表示登录失败
//				session.put("faulttype", "NoPerson");
//				return ERROR;
//			}
//			else {
//				//登录成功，在前端通过memberInfo这个key即可获得session，并将其保存，进行请求时原样发送回来
//				session.put("memberInfo", curPerson);
//				return SUCCESS;
//			}
//		}
//		catch (IllegalAccessException e) {
//			// TODO: handle exception
//			session.put("faulttype", "Illegal");
//			return "illegal";
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			session.put("faulttype", "Unknown");
//			return ERROR;
//		}
		//自定义一个session信息，用于测试从action到jsp的session传输
		
		//session.put("infofromAction2jsp", "这是一段测试从servlet到jsp能否正常发送session的文字，如果该段文字无乱码地正常显示则没有问题。");
		if ("2141601033".equals(id)) {
				if (!password.equals(Crypto.toSHA1("456789"))){
					this.addActionError("学号和密码不匹配，请重新检查后输入！");
					return INPUT;
				}
		}
		else {
			this.addActionError("学号和密码不匹配，请重新检查后输入！");
			return INPUT;
		}
		//这是一小段测试代码
		Person person=new Person();
		person.setID(id);
		person.setPassword(password);
		session.put("personInfo", person);
		if (remember.equals("true")){
			cu=new CookieUtils(request);
			Cookie[] newcookie=cu.generateCookie(this.id,this.password);
			response.addCookie(newcookie[0]);
			response.addCookie(newcookie[1]);
			System.out.println("cookie新增");
		}
		return INPUT;
	}
	//用于进行token验证
	private void tokenCheck(){
		System.out.println(token);
		System.out.println(session.get("token"));
		String curtoken= TokenCheck.generateNewToken();
		try{
			if (!TokenCheck.checkToken(session,token)){
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

	@Override
	public void validate(){
		if (id==null || id.equals("")) {
			addFieldError("id", "请输入学号！");
			passed=false;
		}
		if (password==null || password.equals("")) {
			addFieldError("password", "请输入密码！");
			passed=false;
		}
		if (!new InjectionCheck(id).getResult()) {
			addFieldError("id", "请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
			passed=false;
		}
		if (!new InjectionCheck(password).getResult()) {
			addFieldError("password","请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
			passed=false;
		}
		if(!passed) {
			tokenCheck();
		}

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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response=httpServletResponse;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}
}
