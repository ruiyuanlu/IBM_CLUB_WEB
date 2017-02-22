package club.istc.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import club.istc.bean.Person;
import club.istc.validation.*;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 登录。<br>
 * 通过输入的用户名和密码，获取该用户信息并存入session中。<br>
 * 该session在浏览网站的过程中全程存在，代表用户处于登录状态。<br>
 */
@Action(
		value="Login",
        results={ 
				@Result(name="input", location="login.jsp"),
				@Result(name="success", location="welcome", type="redirect"),
				@Result(name="invalid.token", location="login", type="redirect"),
        },
        interceptorRefs={ 
			    @InterceptorRef("tokenSession"),  
			    @InterceptorRef("defaultStack")  
        }
)
public class LoginAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private Map<String, Object> session;
	// 用户登录 
	
	public LoginAction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
		//测试代码
		System.out.println(session.get("infofromjsp2Action"));
	}
	
	/**
	 * 通过用户名和密码检验身份并产生session
	 * @throws Exception 
	 */

	public String execute(){
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
		
		session.put("infofromAction2jsp", "这是一段测试从servlet到jsp能否正常发送session的文字，如果该段文字无乱码地正常显示则没有问题。");
		
		System.out.println(id);
		System.out.println(password);
		
		if ("2141601033".equals(id)) {
			if (!"456789".equals(password)) {
				addFieldError("loginfault", "学号和密码不匹配，请重新检查后输入！");
				return INPUT;
			}
		}
		else {
			addFieldError("loginfault", "学号和密码不匹配，请重新检查后输入！");
			return INPUT;
		}
		//这是一小段测试代码
		Person person=new Person();
		person.setAge(15);
		session.put("personInfo", person);
		return SUCCESS;
	}
	
	@Override
	public void validate(){
		if (id==null || id=="") {
			addFieldError("id", "请输入学号！");
		}
		if (password==null || password=="") {
			addFieldError("password", "请输入密码！");
		}
		if (!new InjectionCheck(id).getResult()) {
			addFieldError("id", "请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
		}
		if (!new InjectionCheck(password).getResult()) {
			addFieldError("password","请不要在输入的信息中包含特殊符号（* ' ; - + / % #）");
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


}
