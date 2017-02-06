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
	private static List<Person> interviewees=new ArrayList<Person>();
	private String[] passed;
	Map<String, Object> session;
	
	public String[] getPassed(){  
	    return passed;  
	}  
	
	public void setPassed(String[] passed){  
	    this.passed=passed;  
	} 

	
	public InterviewAction() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}
	
	/**
	 * 用于获得用户提交的面试结果信息，并对数据库进行相应的处理
	 */
	public String check() throws Exception{
		try {
			deletePerson();
			//从数据库中重新获取Person对象的List
			session.put("interviewList", interviewees);
			if (interviewees.size()==0) {
				addFieldError("getIntervieweeError", "面试已结束！");
			}
			return INPUT;

		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("getIntervieweeError", "获取面试人员信息失败！");
			return INPUT;
		}
	}
	
	/**
	 * 从数据库获取面试人员列表
	 */
	public String get() throws Exception{
		addtemp();
		try {
			session.put("interviewList", interviewees);
			return INPUT;
		} catch (Exception e) {
			// TODO: handle exception
			addFieldError("getIntervieweeError", "获取面试人员信息失败！");
			return INPUT;
		}
	}
	
	/**
	 * 将通过面试的用户加入数据库并删除其在面试列表中的记录
	 */
	private void deletePerson(){
		try {
			for (int i = 0; i < passed.length; i++) {
				//将学号对应的人员加入数据库
				//在数据库中删除对应人员
				for (int j = 0; j < interviewees.size(); j++) {
					
					if(passed[i].trim().equals(interviewees.get(j).getID().trim())){
						interviewees.remove(j);
						break;
					}	
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			
		}

	}
	
/*	private ArrayList<Person> getIntervieweesFromDatabase() {
		try {
			ArrayList<Person> curIntervieweesList = new ArrayList<Person>();
			

			
			return curIntervieweesList;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}*/
	
	public static void addtemp() {
		//原则上是从数据库中获取数据，这里为了测试用假数据
		Person curPerson = new Person();
		curPerson.setID("2141601033");
		curPerson.setName("张三");
		interviewees.add(curPerson);
		
		curPerson=new Person();
		curPerson.setID("2141601022");
		curPerson.setName("李四");
		interviewees.add(curPerson);
		
		curPerson=new Person();
		curPerson.setID("2141601011");
		curPerson.setName("王五");
		interviewees.add(curPerson);
	}

}
