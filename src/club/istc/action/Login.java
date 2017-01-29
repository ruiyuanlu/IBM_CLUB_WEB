package club.istc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 登录。
 * 通过输入的用户名和密码，获取该用户信息并存入session中
 * 该session在浏览网站的过程中全程存在，代表用户处于登录状态
 * 任何涉及数据库的操作均需要查验session
 */

public class Login extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private Map<String, Object> session;
	// 用户登录 
	
	public Login() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		System.out.println(session.get("info"));
	}
	
	public String execute() {
		//通过用户名和密码获取Member类对象信息
		//在这里嵌入数据库相关代码以检测是否成功连入数据库
		//通过用户名和密码获取Member类对象信息
		//设置session	
//		try {
//			curPerson=new Func_for_control().getMember(""+id, password, ""+id);
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
		if ("2141601033".equals(id)) {
			if ("456".equals(password)) {
				return SUCCESS;
			}
		}
		session.put("faulttype", "密码和用户名不匹配！");
		return "input";
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
