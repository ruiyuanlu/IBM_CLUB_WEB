package club.istc.action;

import java.util.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import club.istc.bean.*;

public class InterviewAction extends ActionSupport{
	
	/**
	 * 面试模块
	 */
	private static final long serialVersionUID = 1L;
	private List<Person> interviewees;
	private List<String> passed;
	private List<String> notpassed;
	Map<String, Object> session;
	
	public List<String> getPassed(){  
	    return passed;  
	}  
	
	public void setPassed(List<String> passed){  
	    this.passed=passed;  
	} 
	
	public List<String> getNotpassed() {
		return notpassed;
	}
	
	public void setNotpassed(List<String> notpassed) {
		this.notpassed = notpassed;
	}
	
	public InterviewAction() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}
	
	@Override
	public String execute(){
		deletePerson();
		session.put("interviewList", interviewees);
		return INPUT;
	}
	
	private void deletePerson(){
		//将通过面试的用户加入数据库并删除其在面试列表中的记录
		for (int i = 0; i < passed.size(); i++) {
			//将学号对应的人员加入数据库
			//在数据库删除对应人员
		}
		for (int i = 0; i < notpassed.size(); i++) {
			//在数据库删除对应人员
		}
		//从数据库中重新获取Person对象的List
	}

}
